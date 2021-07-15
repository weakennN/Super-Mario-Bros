package Game.Levels;

import ECS.Collider;
import ECS.SprtieRenderer.SortingLayer;
import ECS.SprtieRenderer.SortingLayersContainer;
import ECS.Transform;
import Game.Camera;
import Game.Common.GlobalVariables;
import Game.GameObjects.*;
import Game.GameObjects.Mario.Mario;
import Game.Levels.GameObjectFactory.GameObjectFactory;
import javafx.scene.image.Image;
import mikera.vectorz.Vector2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Level {

    private List<GameObject> gameObjects;
    private Image backGround;
    private Camera camera;
    private Mario mario;

    public Level() {
        this.gameObjects = new ArrayList<>();
        GameObjectFactory.init(this);
    }

    public void initLevel() {
        SortingLayer itemLayer = new SortingLayer("item");
        SortingLayersContainer.setPriority(itemLayer, 1);
        SortingLayer playerLayer = new SortingLayer("player");
        SortingLayersContainer.addSortingLayer(playerLayer);
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    protected void addGameObject(GameObject gm) {
        this.gameObjects.add(gm);
    }

    protected void setBackGround(Image backGround) {
        this.backGround = backGround;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Camera getCamera() {
        return this.camera;
    }

    public Mario getMario() {
        return this.mario;
    }

    protected void initLevelObjects(String level) {
        try {
            File file = new File(level);

            FileReader fileReader = new FileReader(file);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

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
                double sizeX = Double.parseDouble(words[2]);
                double sizeY = Double.parseDouble(words[3]);

                GameObject ground = new Ground(GlobalVariables.groundTag, false);
                Transform transform = new Transform(new Vector2(x, y), ground);
                ground.addComponent(new Collider(ground, sizeX, sizeY, transform));
                ground.addComponent(transform);

                this.addGameObject(ground);
                word = bufferedReader.readLine();
            }

            this.createInvisibleWall();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initGameObjects(BufferedReader bufferedReader) {
        try {
            String word = bufferedReader.readLine();

            while (word != null && !word.equals("end")) {
                String[] words = word.split("\\s+");

                GameObject gameObject = GameObjectFactory.create(words, words[0]);
                this.addGameObject(gameObject);

                word = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createInvisibleWall() {
        InvisibleWall invisibleWall = new InvisibleWall(GlobalVariables.invisibleWallTag, this.camera.getPosition());
        Transform transform = new Transform(new Vector2(0, 0), invisibleWall);
        invisibleWall.addComponent(new Collider(invisibleWall, 50, 1080, transform));
        invisibleWall.addComponent(transform);
        this.addGameObject(invisibleWall);
    }

    public void setMario(Mario mario) {
        this.mario = mario;
    }
}
