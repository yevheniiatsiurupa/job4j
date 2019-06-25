package ru.job4j.sql;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

/**
 * Класс для преобразования списков объектов Entry в xml-файл.
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 24/06/2019
 */

public class StoreXML {
    /**
     * Поле хранит файл, куда будут записываться данные.
     */
    private File target;

    public StoreXML(File target) {
        this.target = target;
    }

    /**
     * Метод для маршаллирования объекта Entries в файл target.
     * @param list список передается в качестве параметра при создании объекта Entries.
     */
    public void save(List<Entries.Entry> list) {
        Entries ent = new Entries(list);
        try {
            JAXBContext context = JAXBContext.newInstance(Entries.class);
            Marshaller marsh = context.createMarshaller();
            marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marsh.marshal(ent, this.target);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Config config = new Config();
        config.init();
        StoreSQL store = new StoreSQL(config);
        store.init("store");
        store.generate(5);
        List<Entries.Entry> result = store.load();
        File file = new File("C:\\projects\\job4j\\chapter_007\\src\\main\\resources\\storeXML.xml");
        file.createNewFile();
        StoreXML st = new StoreXML(file);
        st.save(result);
    }
}
