package ECS.SprtieRenderer;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpriteSheet {

    private Image spriteSheet;
    private List<Sprite> sprites;

    public SpriteSheet(String sprite, double width, double height, double spacing) {

        this.spriteSheet = new Image(getClass().getClassLoader().getResourceAsStream("Resources/" + sprite));
        this.initSprites(width, height, spacing, sprite);
    }

    private void initSprites(double width, double height, double spacing, String sprite) {

        this.sprites = new ArrayList<>();

        double x = 0;

        while (x < spriteSheet.getWidth()) {

            try {

                BufferedImage bufferedImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Resources/" + sprite));
                BufferedImage subImage = bufferedImage.getSubimage((int) x, 0, (int) width, (int) height);
                Image image = SwingFXUtils.toFXImage(subImage, null);
                this.sprites.add(new Sprite(image));
            } catch (IOException e) {
                e.printStackTrace();
            }

            x += width + spacing;
        }

    }

    public Image getSpriteSheet() {
        return spriteSheet;
    }

    public List<Sprite> getSprites() {
        return this.sprites;
    }
}
