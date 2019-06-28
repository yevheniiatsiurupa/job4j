package exam;

import java.util.HashMap;
import java.util.Map;

public class MonthConvert {
    public Map<String, String> monthMap = new HashMap<>();

    public MonthConvert() {
        monthMap.put("янв", "01");
        monthMap.put("фев", "02");
        monthMap.put("мар", "03");
        monthMap.put("апр", "04");
        monthMap.put("май", "05");
        monthMap.put("июн", "06");
        monthMap.put("июл", "07");
        monthMap.put("авг", "08");
        monthMap.put("сен", "09");
        monthMap.put("окт", "10");
        monthMap.put("ноя", "11");
        monthMap.put("дек", "12");
    }
}
