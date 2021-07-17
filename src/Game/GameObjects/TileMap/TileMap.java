package Game.GameObjects.TileMap;

import Game.Collision.Collision;
import Game.Collision.Collisions;
import Game.GameObjects.GameObject;

import java.util.ArrayList;
import java.util.List;

public class TileMap extends GameObject {

    private List<Tile> tiles;

    public TileMap(String tag) {
        super(tag);

        this.tiles = new ArrayList<>();
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        if (collision.getHitDirection().y == -1) {
            Collisions.defaultOnGroundCollision(this, other, collision);
        }
    }

    public List<Tile> getTiles() {
        return this.tiles;
    }

    public void addTile(Tile tile) {
        this.tiles.add(tile);
    }
}
