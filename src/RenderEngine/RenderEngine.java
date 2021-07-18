package RenderEngine;

import ECS.Renderer.SprtieRenderer.SortingLayer;
import ECS.Renderer.SprtieRenderer.SortingLayersContainer;
import Game.Camera;
import UIEngine.Designer;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

public class RenderEngine {

    private Camera camera;

    public RenderEngine() {
    }

    public void render() {
        for (int i = 0; i < SortingLayersContainer.sortingLayers.size(); i++) {
            SortingLayer sortingLayer = SortingLayersContainer.sortingLayers.get(i);
            for (int j = 0; j < sortingLayer.getRenderers().size(); j++) {
                sortingLayer.getRenderers().get(j).render();
            }
        }
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void clear() {
        Designer.gc.setFill(Color.BLACK);
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
        Designer.gc.fillRect(this.camera.getPosition().getPos().x - screenWidth / 2, this.camera.getPosition().getPos().y
                , this.camera.getPosition().getPos().x + screenWidth, this.camera.getPosition().getPos().y + screenHeight);
    }

    public void render(Image image, double posX, double posY, double width, double height) {
        Designer.gc.drawImage(image, posX, posY, width, height);
    }
}
