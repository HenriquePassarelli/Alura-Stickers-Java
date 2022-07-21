import com.google.gson.Gson;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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

        StickerGenerator stickerGenerator = new StickerGenerator();

        // get only desirable data - title, poster, rating

        Gson gson = new Gson();

        Map data = gson.fromJson(body, Map.class);

        List<Map<String, String>> filmList = (List<Map<String, String>>) data.get("items");

        System.out.println(filmList.size());

        // show and handle the data

        for (Map<String, String> film : filmList) {
            String imageUrl = film.get("image").replaceAll("(@+)(.*).jpg$","$1.jpg");
            String title = film.get("title");
            InputStream inputStream = new URL(imageUrl).openStream();
            stickerGenerator.create(inputStream, title + ".png");
            System.out.println(film.get("title"));
        }
    }
}
