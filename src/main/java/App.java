import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.lang.model.type.NullType;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // get api data
        String url = "https://raw.githubusercontent.com/alexfelipe/imersao-java/json/top250.json";
        URI address = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(address).GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String body = response.body();

        System.out.println(body);

        // get only desirable data - title, poster, rating

        class FilmList {
            String title;
            String image;
            String imDbRating;

        }

        class DataList {
            String errorMessage;
            List<Map<String, String>> items;
        }

        Gson gson = new Gson();

//
//        DataList test = (DataList) gson.fromJson(body, Map.class);
//        System.out.println(test);

        JsonParser parser = new JsonParser();


        List<Map<String, String>> filmList = parser.parse(body);

//        System.out.println(filmList.size());

        // show and handle the data

        for (Map<String, String> films : filmList) {
            System.out.println(films.get("title"));
        }
    }
}
