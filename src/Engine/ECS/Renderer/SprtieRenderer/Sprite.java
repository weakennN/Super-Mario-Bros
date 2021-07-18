package Engine.ECS.Renderer.SprtieRenderer;

import javafx.scene.image.Image;

public class Sprite {

    private Image texture;

    public Sprite(Image texture) {
        this.texture = texture;
    }

    public Image getTexture() {
        return this.texture;
    }
}
