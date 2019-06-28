package exam;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class SqlRuParser {
    private long timeCompare;

    /**
     * Метод для запуска парсера вакансий.
     *
     * Порядок действий.
     * Запускаем базу данных VacanciesSQL.
     * Получаем время, с которым будут сравниваться вакансии timeCompare (время записи последней вакансии
     * или первый день текущего года).
     * Получаем количество страниц с вакансиями для просмотра (getPageNumber).
     * Запускаем цикл просмотра страниц.
     *
     * На странице проходим по вакансиям, получаем их имя и время последнего редактирвания.
     * Если имя начинается с "Важно:", то запись пропускается (начальные записи на странице без вакансий);
     * Если дата редактирования (timeEditVac) меньше timeCompare, то цикл просмотра страниц и записи вакансий прерывается.
     *
     * Получаем ссылку на вакансию, по ней находим описание вакансии и время создания.
     * Если время создания меньше timeCompare, то просматриваем следующую вакансию.
     * Если описание вакансии проходит проверку checkString (на наличие слова java и отсутствие javascript),
     * то добавляем вакансию в базу данных (вносим имя, описание, ссылку, время создания).
     *
     * @param propertiesFile файл настроек.
     */
    public void init(String propertiesFile) {
        try {
            VacanciesSQL db = new VacanciesSQL();
            db.init(propertiesFile);
            timeCompare = db.getDefTime();

            String website = "https://www.sql.ru/forum/job-offers/";
            int pageNumber = this.getPageNumber(website);

            page_loop:
            for (int i = 1; i < pageNumber; i++) {
                String onePage = website + i;
                Document doc = Jsoup.connect(onePage).get();
                Elements tableElem = doc.getElementsByAttributeValue("class", "forumTable");
                Elements trElem = tableElem.first().getElementsByTag("tr");

                for (int j = 1; j < trElem.size(); j++) {
                    Element tmp = trElem.get(j);
                    Element timeElem = tmp.child(5);
                    Element aElem = tmp.child(1).getElementsByTag("a").first();

                    String startName = tmp.child(1).text();
                    String name = aElem.text();
                    if (startName.startsWith("Важно:")) {
                        continue;
                    }

                    long timeEditVac = this.convertString(timeElem.text());
                    if (timeEditVac < this.timeCompare) {
                        break page_loop;
                    }

                    String url = aElem.attr("href");
                    String text = this.getVacancyText(url);
                    long timeVac = this.convertString(this.getVacancyDate(url));

                    if (timeVac < this.timeCompare) {
                        continue;
                    }

                    if (this.checkString(text)) {
                        db.addVacancy(name, text, url, timeVac);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Метод получает ссылку на вакансию, возвращает описание вакансии (первое сообщение в теме форума).
     * @param url ссылка на вакансию.
     * @return описание вакансии.
     * @throws IOException
     */
    private String getVacancyText(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Element divElem = doc.getElementsByAttributeValue("class", "msgTable").first();
        Element tdElem = divElem.getElementsByAttributeValue("class", "msgBody").last();
        return tdElem.text();
    }

    /**
     * Метод получает ссылку на вкансию, возвращает время создания вакансии (время первого сообщения).
     * @param url ссылка на вакансию.
     * @return время создания вакансии.
     */
    private String getVacancyDate(String url) throws Exception {
        Document doc = Jsoup.connect(url).get();
        Element divElem = doc.getElementsByAttributeValue("class", "msgTable").first();
        Element tdElem = divElem.getElementsByAttributeValue("class", "msgFooter").last();
        String line = tdElem.text();
        return line.substring(0, line.indexOf("[") - 1);
    }

    /**
     * Метод находит количество страниц с вакансиями.
     * @param url адрес сайта.
     * @return возвращает количество страниц.
     */
    private int getPageNumber(String url) throws Exception {
        Document doc = Jsoup.connect(url).get();
        Elements tableElem = doc.getElementsByAttributeValue("class", "sort_options");
        Element aElem = tableElem.last().getElementsByTag("a").last();
        return Integer.parseInt(aElem.text());
    }

    /**
     * Метод проверяет наличие слова java и отсутствие слова javascript в строке.
     * @param text входящая строка.
     */
    private boolean checkString(String text) {
        boolean contJava = text.contains("java") || text.contains("Java");
        boolean contJavaScript = text.contains("JavaScript") || text.contains("Java Script");
        return contJava && !contJavaScript;
    }

    /**
     * Метод преобразует строку с датой в соответствующее число формата long.
     * Учитывает если в названии есть слово "сегодня" или "вчера".
     * @param time строка с временем.
     * @return возвращает число long.
     */
    public long convertString(String time) throws Exception {
        if (time.startsWith("сегодня")) {
            String[] separated = time.split(", ");
            String[] hourMinutes = separated[1].split(":");
            Calendar today = Calendar.getInstance();
            int hour =  Integer.parseInt(hourMinutes[0]);
            int minute = Integer.parseInt(hourMinutes[1]);
            int year = today.get(Calendar.YEAR);
            int month = today.get(Calendar.MONTH);
            int day = today.get(Calendar.DAY_OF_MONTH);
            Calendar result = new GregorianCalendar(year, month, day, hour, minute);
            return result.getTimeInMillis();
        }
        if (time.startsWith("вчера")) {
            String[] separated = time.split(", ");
            String[] hourMinutes = separated[1].split(":");
            Calendar yesterday = Calendar.getInstance();
            int hour =  Integer.parseInt(hourMinutes[0]);
            int minute = Integer.parseInt(hourMinutes[1]);
            int year = yesterday.get(Calendar.YEAR);
            int month = yesterday.get(Calendar.MONTH);
            int day = yesterday.get(Calendar.DAY_OF_MONTH);
            Calendar result = new GregorianCalendar(year, month, day, hour, minute);
            result.add(Calendar.DAY_OF_MONTH, -1);
            return result.getTimeInMillis();
        }

        MonthConvert map = new MonthConvert();
        Set<String> months = map.monthMap.keySet();
        String result = "";
        for (String tmp : months) {
            if (time.contains(tmp)) {
                result = time.replace(tmp, map.monthMap.get(tmp));
                break;
            }
        }
        DateFormat df = new SimpleDateFormat("dd MM yy, HH:mm");
        Date date = df.parse(result);
        return date.getTime();
    }

    public static void main(String[] args) throws Exception {
        SqlRuParser testParser = new SqlRuParser();
        testParser.init("app.properties");
    }
}

