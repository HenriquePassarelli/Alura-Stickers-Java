import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class StickerGenerator {


    public void create(InputStream inputStream, String FileName) throws IOException {

        // read image
        BufferedImage originalImg = ImageIO.read(inputStream);

        // create new image, transparency and  new size
        int width = originalImg.getWidth();
        int height = originalImg.getHeight();
        int newHeight = height + 200;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // copy original image to new image (in memory)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImg, 0, 0, null);

        // type a phrase to the image
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setFont(font);
        graphics.setColor(Color.BLUE);
        graphics.drawString("Amazing", 100, newHeight - 100);

        // write the image to a file
        ImageIO.write(newImage, "png", new File("src/main/java/output/" + FileName));
    }
}
