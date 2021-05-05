package GameObjects;

import Animator.Animator;
import CollisionInfo.Collision;
import Designer.Designer;
import Rigidbody.Position;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BrickBox extends GameObject {

    public BrickBox(Position position, String tag, double sizeX, double sizeY) {
        super(position, tag);

      /*  super.getRigidbody().setCollider(new BrickBoxCollider(position, sizeX, sizeY));
        super.getRigidbody().setGameObject(this);

       */


    }

    public BrickBox(Position position, String tag) {

        this(position, tag, 50, 50);
    }

    @Override
    public void update() {

        super.updateComponents();
    }

    @Override
    public Image render() {

        return super.getCurrentAnimation();
    }

    @Override
    public void start() {

        super.changeImage(Animator.brickBox);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {

    }

   /* public static void drawBox(double x, double y) {

        try {
            Designer.gc.drawImage(new Image(new FileInputStream(Animator.brickBox)), x, y);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    */
}
