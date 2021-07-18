package Game.Levels.GameObjectFactory;

import Game.GameObjects.GameObject;
import Game.Levels.GameObjectFactory.GameObjectCreators.*;
import Game.Levels.Level;

import java.util.HashMap;
import java.util.Map;

public class GameObjectFactory {

    private static Map<String, GameObjectCreator> creators;

    public static void init(Level level) {
        creators = new HashMap<>();
        creators.put("Mario", new MarioCreator(level));
        creators.put("Goomba", new GoombaCreator(level));
        creators.put("BrickBox", new BrickCreator(level));
        creators.put("Block", new BlockCreator(level));
        creators.put("Koopa", new KoopaCreator(level));
        creators.put("Mushroom", new MushroomCreator(level));
        creators.put("Cloud", new CloudCreator(level));
        creators.put("Coin", new CoinCreator(level));
        creators.put("ItemBox", new ItemBoxCreator(level));
        creators.put("Pipe", new PipeCreator(level));
        creators.put("FireBall", new FireBallCreator(level));
        creators.put("Flower", new FlowerCreator(level));
        creators.put("Castle", new CastleCreator(level));
        creators.put("TileMap", new TileMapCreator(level));
        creators.put("DeathWall", new DeathWallCreator(level));
        creators.put("Bush", new BushCreator(level));
        creators.put("Mountain", new MountainCreator(level));
    }

    public static GameObject create(String[] params, String creator) {
        GameObjectCreator gameObjectCreator = creators.get(creator);
        return gameObjectCreator.create(params);
    }
}
