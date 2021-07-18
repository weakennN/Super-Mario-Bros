package RenderEngine;

import Engine.ECS.Renderer.SprtieRenderer.SortingLayer;
import Engine.ECS.Renderer.SprtieRenderer.SortingLayersContainer;
import Game.Camera;
import UIEngine.Designer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

public class RenderEngine {

    private Camera camera;
    private GraphicsContext gc;

    public RenderEngine(GraphicsContext gc) {
        this.gc = gc;
    }

    public void render() {
        for (int i = 0; i < SortingLayersContainer.sortingLayers.size(); i++) {
            SortingLayer sortingLayer = SortingLayersContainer.sortingLayers.get(i);
            for (int j = 0; j < sortingLayer.getRenderers().size(); j++) {
                sortingLayer.getRenderers().get(j).render(this.gc);
            }
        }
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void clear() {
        this.gc.setFill(Color.BLACK);
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
        this.gc.fillRect(this.camera.getPosition().getPos().x - screenWidth / 2, this.camera.getPosition().getPos().y
                , this.camera.getPosition().getPos().x + screenWidth, this.camera.getPosition().getPos().y + screenHeight);
    }

    public void render(Image image, double posX, double posY, double width, double height) {
        this.gc.drawImage(image, posX, posY, width, height);
    }

    public GraphicsContext getGc() {
        return this.gc;
    }
}
