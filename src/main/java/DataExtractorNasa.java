import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DataExtractorNasa implements DataExtractor {
    Gson gson = new Gson();

    public List<Content> extractor(String json) {

        List<LinkedTreeMap> data = gson.fromJson(json, List.class);

        List<Content> newData = new ArrayList<>();

        Content content;
        for (LinkedTreeMap item : data) {
            content = new Content((String) item.get("title"), (String) item.get("url"));
            newData.add(content);
        }

        return newData;
    }
}
