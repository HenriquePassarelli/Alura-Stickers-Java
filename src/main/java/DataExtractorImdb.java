import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataExtractorImdb implements DataExtractor {
    Gson gson = new Gson();

    public List<Content> extractor(String json) {

        LinkedTreeMap data = (LinkedTreeMap) gson.fromJson(json, Map.class);

        List<Content> newData = new ArrayList<>();

        Content content;
        for (LinkedTreeMap item : (List<LinkedTreeMap>)data.get("items")) {
            content = new Content((String) item.get("title"), (String) item.get("image"));
            newData.add(content);
        }

        return newData;
    }
}
