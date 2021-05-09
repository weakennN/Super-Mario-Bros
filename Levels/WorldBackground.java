package Levels;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class WorldBackground {

    private Image image;
    private double sizeX;
    private double sizeY;

    public WorldBackground(String image1, double sizeX, double sizeY) {

        try {
            this.image = new Image(new FileInputStream(image1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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
