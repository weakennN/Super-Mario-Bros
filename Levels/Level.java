package Levels;

import Animator.Animator;
import Common.GlobalVariables;
import Components.Collider;
import Components.Rigidbody;
import Designer.Camera;
import GameObjects.*;
import GameObjects.AI.Goomba;
import RenderEngine.RenderEngine;
import Rigidbody.Position;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Level {

    private List<GameObject> gameObjects;
    private Image backGround;
    private Camera camera;

    public Level() {

        this.gameObjects = new ArrayList<>();
    }

    public abstract void initLevel();

    public List<GameObject> getGameObjects() {

        return gameObjects;
    }

    protected void addGameObject(GameObject gm) {

        this.gameObjects.add(gm);
    }

    protected void setBackGround(Image backGround) {

        this.backGround = backGround;
    }

    protected void setCamera(Camera camera) {

        this.camera = camera;
    }

    public Camera getCamera() {

        return this.camera;
    }

    protected void initLevelObjects(String level) {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(level));

            String line = bufferedReader.readLine();

            while (line != null) {

                String[] words = line.split("\\s+");

                if (words[0].equals("GameObjects")) {

                    this.initGameObjects(bufferedReader);
                } else if (words[0].equals("Ground")) {

                    this.initGround(bufferedReader);
                }

                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initGround(BufferedReader bufferedReader) {

        try {
            String word = bufferedReader.readLine();

            while (word != null && !word.equals("end")) {

                String[] words = word.split("\\s+");

                double x = Double.parseDouble(words[0]);
                double y = Double.parseDouble(words[1]);

                RenderEngine.renderImage(Animator.ground, x, y);

                word = bufferedReader.readLine();
            }

            String[] words = bufferedReader.readLine().split("\\s+");

            double posX = Double.parseDouble(words[0]);
            double posY = Double.parseDouble(words[1]);
            double sizeX = Double.parseDouble(words[2]);
            double sizeY = Double.parseDouble(words[3]);

            Position position = new Position(posX, posY);
            Ground ground = new Ground(position, GlobalVariables.groundTag);
            ground.addComponent(new Collider(GlobalVariables.colliderTag, position, sizeX, sizeY, ground));

            this.addGameObject(ground);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initGameObjects(BufferedReader bufferedReader) {

        try {
            String word = bufferedReader.readLine();

            while (word != null && !word.equals("end")) {

                String[] words = word.split("\\s+");

                GameObject gameObject = null;
                double x = Double.parseDouble(words[1]);
                double y = Double.parseDouble(words[2]);

                Position position = new Position(x, y);

                if (words[0].equals("Mario")) {

                    gameObject = new Mario(position, GlobalVariables.marioTag);
                    gameObject.addComponent(new Rigidbody(GlobalVariables.rigidbodyTag, position));
                    gameObject.addComponent(new Collider(GlobalVariables.colliderTag, position,
                            GlobalVariables.defaultColliderSize, GlobalVariables.defaultColliderSize, gameObject));
                    this.camera = new Camera((Mario) gameObject);

                } else if (words[0].equals("Goomba")) {

                    gameObject = new Goomba(position, GlobalVariables.goombaTag);
                    gameObject.addComponent(new Rigidbody(GlobalVariables.rigidbodyTag, position));
                    gameObject.addComponent(new Collider(GlobalVariables.colliderTag, position,
                            GlobalVariables.defaultColliderSize, GlobalVariables.defaultColliderSize, gameObject));

                } else if (words[0].equals("ItemBox")) {

                    gameObject = new ItemBox(position, GlobalVariables.itemBoxTag);
                } else if (words[0].equals("Mushroom")) {

                    gameObject = new Mushroom(position, GlobalVariables.mushroomTag);
                    gameObject.addComponent(new Rigidbody(GlobalVariables.rigidbodyTag, position));
                    gameObject.addComponent(new Collider(GlobalVariables.colliderTag, position,
                            GlobalVariables.defaultColliderSize, GlobalVariables.defaultColliderSize, gameObject));

                } else if (words[0].equals("BrickBox")) {

                    gameObject = new BrickBox(position, GlobalVariables.brickBoxTag);
                    gameObject.addComponent(new Collider(GlobalVariables.colliderTag, position,
                            GlobalVariables.defaultColliderSize, GlobalVariables.defaultColliderSize, gameObject));
                }

                this.addGameObject(gameObject);

                word = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
