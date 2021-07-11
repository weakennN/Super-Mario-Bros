package RenderEngine;

import ECS.SprtieRenderer.SortingLayer;
import ECS.SprtieRenderer.SortingLayersContainer;
import ECS.SprtieRenderer.SpriteRenderer;
import ECS.Transform;
import Game.GameObjects.GameObject;
import UIEngine.Designer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mikera.vectorz.Vector2;

public class RenderEngine {

    public RenderEngine() {

    }

    public void render(Image image, Transform position) {

        Vector2 pos = position.getPos();

        Designer.gc.drawImage(image, pos.x, pos.y);
    }

    public static void showScore(int score, int coins, int world,
                                 int level, int time, int lives) {

        Designer.score.setText(score + "");
        Designer.score.setLayoutX(Designer.scoreLabel.getLayoutX() + Designer.scoreLabel.getWidth() / 2 - Designer.score.getWidth() / 2 + 5);
        Designer.score.setLayoutY(50);

        Designer.coins.setText(coins + "");
        Designer.coins.setLayoutX(Designer.coinsLabel.getLayoutX() + Designer.coinsLabel.getWidth() / 2 - Designer.coins.getWidth() / 2 + 5);
        Designer.coins.setLayoutY(50);

        Designer.world.setText(world + "-" + level);
        Designer.world.setLayoutX(Designer.worldLabel.getLayoutX() + Designer.worldLabel.getWidth() / 2 - Designer.world.getWidth() / 2 + 5);
        Designer.world.setLayoutY(50);

        Designer.time.setText(time + "");
        Designer.time.setLayoutX((Designer.timeLabel.getLayoutX() + Designer.timeLabel.getWidth() / 2 - Designer.time.getWidth() / 2) + 5);
        Designer.time.setLayoutY(50);

        Designer.lives.setText(lives + "");
        Designer.lives.setLayoutX(Designer.livesLabel.getLayoutX() + Designer.livesLabel.getWidth() / 2 - Designer.lives.getWidth() / 2 + 5);
        Designer.lives.setLayoutY(50);

    }

    public void renderImage(String strImage, double startX, double startY
            , double endX, double endY) {

        Image image = new Image(strImage);
        Designer.gc.drawImage(image, startX, startY, endX, endY);

    }

    public void renderImage(String strImage, double startX, double startY) {

        Image image = new Image(strImage);
        Designer.gc.drawImage(image, startX, startY);

    }

    public void render() { ;
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
        Designer.gc.drawImage(spriteRenderer.getSprite().getTexture(), transform.getPos().x, transform.getPos().y,
                spriteRenderer.getSprite().getTexture().getWidth() * transform.getScale().x,
                spriteRenderer.getSprite().getTexture().getHeight() * transform.getScale().y);
    }
}
