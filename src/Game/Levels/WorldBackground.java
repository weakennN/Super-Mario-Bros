package Game.Levels;

import javafx.scene.image.Image;

public class WorldBackground {

    private Image image;
    private double sizeX;
    private double sizeY;

    public WorldBackground(String image1, double sizeX, double sizeY) {
        this.image = new Image(image1);

        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public Image getImage() {
        return this.image;
    }

    public double getSizeX() {
        return sizeX;
    }

    public double getSizeY() {
        return sizeY;
    }
}
