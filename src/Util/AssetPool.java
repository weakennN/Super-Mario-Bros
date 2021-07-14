package Util;

import ECS.SprtieRenderer.SpriteSheet;

import java.util.HashMap;
import java.util.Map;

public class AssetPool {

    private static Map<String, SpriteSheet> spriteSheets = new HashMap<>();

    public static void addSpriteSheet(String key, SpriteSheet spriteSheet) {
        spriteSheets.put(key, spriteSheet);
    }

    public static SpriteSheet getSpriteSheet(String key) {
        return spriteSheets.get(key);
    }

    public static void removeSpriteSheet(String key) {
        spriteSheets.remove(key);
    }
}
