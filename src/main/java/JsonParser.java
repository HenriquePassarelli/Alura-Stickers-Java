import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {

    private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
    private static final Pattern REGEX_ATTR_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");


    public List<Map<String, String>> parse(String json){
        Matcher matcher = REGEX_ITEMS.matcher(json);
        if (!matcher.find()) {

            throw new IllegalArgumentException("item not found.");
        }

        String[] items = matcher.group(1).split("\\},\\{");

        List<Map<String, String>> data = new ArrayList<>();

        for (String item : items) {

            Map<String, String> attrItems = new HashMap<>();

            Matcher matcherATTRJson = REGEX_ATTR_JSON.matcher(item);
            while (matcherATTRJson.find()) {
                String attr = matcherATTRJson.group(1);
                String valor = matcherATTRJson.group(2);
                attrItems.put(attr, valor);
            }

            data.add(attrItems);
        }

        return data;
    }
}
