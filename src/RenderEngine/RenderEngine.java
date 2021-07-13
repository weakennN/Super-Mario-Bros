package RenderEngine;

import ECS.SprtieRenderer.SortingLayer;
import ECS.SprtieRenderer.SortingLayersContainer;
import ECS.SprtieRenderer.SpriteRenderer;
import ECS.Transform;
import Game.GameObjects.GameObject;
import UIEngine.Designer;
import javafx.scene.image.Image;

public class RenderEngine {

    public RenderEngine() {

    }

    public void renderImage(String strImage, double startX, double startY) {
        Image image = new Image(strImage);
        Designer.gc.drawImage(image, startX, startY);
    }

    public void render() {
        ;
        for (int i = 0; i < SortingLayersContainer.sortingLayers.size(); i++) {

            SortingLayer sortingLayer = SortingLayersContainer.sortingLayers.get(i);
            for (int j = 0; j < sortingLayer.getSpriteRenderers().size(); j++) {
                GameObject gameObject = sortingLayer.getSpriteRenderers().get(j).getGameObject();
                if (gameObject.isActive()) {
                    this.render(gameObject);
                }
            }
        }
    }

    private void render(GameObject gameObject) {

        SpriteRenderer spriteRenderer = gameObject.getComponent(SpriteRenderer.class);
        Transform transform = gameObject.getComponent(Transform.class);

        Designer.gc.drawImage(spriteRenderer.getSprite().getTexture(), transform.getScale().x < 0 ?
                        transform.getPos().x + spriteRenderer.getSprite().getTexture().getWidth()
                        : transform.getPos().x, transform.getPos().y,
                spriteRenderer.getSprite().getTexture().getWidth() * transform.getScale().x,
                spriteRenderer.getSprite().getTexture().getHeight() * transform.getScale().y);
    }
}
