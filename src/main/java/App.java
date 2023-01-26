import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

//        String urlImdb = "https://api.mocki.io/v2/549a5d8b/Top250Movies"; // API not working at the moment
        String url = "https://api.nasa.gov/planetary/apod?start_date=2015-09-07&end_date=2015-09-08&api_key=DEMO_KEY";

        HttpApi http = new HttpApi();

        String response = http.dataSearch(url);
        StickerGenerator stickerGenerator = new StickerGenerator();

        DataExtractorNasa dataExtractor = new DataExtractorNasa();

        List<Content> data = dataExtractor.extractor(response);

        for (Content film : data) {
            String imageUrl = film.getImageUrl();
            String title = film.getTitle();
            InputStream inputStream = new URL(imageUrl).openStream();
            stickerGenerator.create(inputStream, title + ".png");
        }
    }
}
