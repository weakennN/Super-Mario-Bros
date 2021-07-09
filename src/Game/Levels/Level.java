package Game.Levels;

import ECS.Collider;
import ECS.Rigidbody;
import ECS.Transform;
import Game.Camera;
import Game.Common.GlobalVariables;
import Game.GameObjects.*;
import Game.GameObjects.Mario.Mario;
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

                GameObject gameObject = null;
                double x = Double.parseDouble(words[1]);
                double y = Double.parseDouble(words[2]);

                Transform transform = new Transform(new Vector2(x, y), gameObject);

                if (words[0].equals("Mario")) {

                    gameObject = new Mario(GlobalVariables.marioTag);
                    gameObject.addComponent(new Rigidbody(gameObject, transform));
                    gameObject.addComponent(new Collider(gameObject,
                            GlobalVariables.defaultMarioColliderX, GlobalVariables.defaultMarioColliderY, transform));

                    Transform cameraPos = new Transform(new Vector2(960, 0));
                    this.mario = (Mario) gameObject;
                    this.camera = new Camera((Mario) gameObject, cameraPos);

                } else if (words[0].equals("Goomba")) {

                    gameObject = new Goomba(GlobalVariables.goombaTag);
                    gameObject.addComponent(new Rigidbody(gameObject, transform));
                    gameObject.addComponent(new Collider(gameObject,
                            GlobalVariables.defaultColliderSizeX, GlobalVariables.defaultColliderSizeY, transform));

                } else if (words[0].equals("ItemBox")) {

                    GameObject itemBoxObject = null;
                    Transform position = new Transform(new Vector2(transform.getPos().x, transform.getPos().y - 50), itemBoxObject);
                    if (words[3].equals("Mushroom")) {
                        itemBoxObject = new Mushroom(GlobalVariables.mushroomTag);
                    } else if (words[3].equals("Coin")) {
                        itemBoxObject = new Coin(GlobalVariables.coinTag);
                    } else if (words[3].equals("FireFlower")) {
                        itemBoxObject = new Flower(GlobalVariables.fireFlowerTag);
                        Transform position2 = new Transform(new Vector2(position.getPos().x, position.getPos().y - 50), itemBoxObject);
                        itemBoxObject.addComponent(position2);
                    }

                    gameObject = new ItemBox(GlobalVariables.itemBoxTag, itemBoxObject);
                    gameObject.addComponent(new Collider(gameObject,
                            GlobalVariables.defaultColliderSizeX, GlobalVariables.defaultColliderSizeY, transform));

                } else if (words[0].equals("Mushroom")) {

                    gameObject = new Mushroom(GlobalVariables.mushroomTag);
                    gameObject.addComponent(new Rigidbody(gameObject, transform));
                    gameObject.addComponent(new Collider(gameObject,
                            GlobalVariables.defaultColliderSizeX, GlobalVariables.defaultColliderSizeY, transform));

                } else if (words[0].equals("BrickBox")) {

                    gameObject = new BrickBox(GlobalVariables.brickBoxTag);
                    gameObject.addComponent(new Collider(gameObject,
                            GlobalVariables.defaultColliderSizeX, GlobalVariables.defaultColliderSizeY, transform));

                } else if (words[0].equals("Pipe")) {

                    gameObject = new Pipe(GlobalVariables.pipeTag);
                    gameObject.addComponent(new Collider(gameObject, 100, 135, transform));

                } else if (words[0].equals("Koopa")) {

                    gameObject = new Koopa(GlobalVariables.koopaTag);
                    gameObject.addComponent(new Rigidbody(gameObject, transform));
                    gameObject.addComponent(new Collider(gameObject,
                            GlobalVariables.defaultColliderSizeX, GlobalVariables.defaultColliderSizeY, transform));
                } else if (words[0].equals("Cloud")) {

                    gameObject = new Cloud(GlobalVariables.cloudTag);
                } else if (words[0].equals("Castle")) {

                    gameObject = new Castle(GlobalVariables.castleTag);
                } else if (words[0].equals("Block")) {

                    gameObject = new Block(GlobalVariables.blockTag);
                    gameObject.addComponent(new Collider(gameObject,
                            GlobalVariables.defaultColliderSizeX, GlobalVariables.defaultColliderSizeY, transform));
                }

                gameObject.addComponent(transform);
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
}
