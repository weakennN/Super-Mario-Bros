package Levels;

import Animator.Animator;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Overworld extends World {

    public Overworld() {

        super();

        try {
            super.setBackGround(new Image(new FileInputStream(Animator.background)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        super.addLevel(new Level1());
    }
}
