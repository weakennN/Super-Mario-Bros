package Designer;

import GameObjects.Mario;
import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.Translate;
import mikera.vectorz.Vector2;

public class Camera {

    private double sceneWidth;
    private Vector2 pos;
    private Mario mario;
    private PerspectiveCamera camera;

    public Camera(Mario mario) {

        this.pos = new Vector2(0, 0);
        this.mario = mario;
        this.sceneWidth = Designer.scene.getWidth();
        Designer.gc.translate(this.pos.x, this.pos.y);
      /*  this.camera = new PerspectiveCamera();
        Designer.scene.setCamera(this.camera);

       */
    }

    public void follow() {

        if (this.mario.getRigidbody() != null) {

            if (this.mario.getRigidbody().getPos().x >= sceneWidth / 2) {

                this.sceneWidth += 150;
                this.pos.x -= 5;
                //Designer.gc.getTransform().append(new Translate(-this.pos.x,-this.pos.y));
                //  Designer.gc.setTransform(this.pos.x,this.pos.y,1,1,1,1); google the setTransform method tomorrow
                 Designer.gc.translate(this.pos.x, this.pos.y);
                // this.camera.getTransforms().add(new Translate(this.pos.x,this.pos.y));
            }
        }

    }

    public void setMario(Mario mario) {

        this.mario = mario;
    }

}
