package Game.GameObjects.TileMap;

import ECS.Renderer.SprtieRenderer.Sprite;
import mikera.vectorz.Vector2;

public class Tile {

    private Sprite sprite;
    private Vector2 pos;

    public Tile(Sprite sprite, Vector2 pos) {
        this.sprite = sprite;
        this.pos = pos;
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public Vector2 getPos() {
        return this.pos;
    }
}
