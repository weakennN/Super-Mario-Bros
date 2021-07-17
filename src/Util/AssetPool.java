package Util;

import ECS.Renderer.SprtieRenderer.SpriteSheet;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class AssetPool {

    private static Map<String, SpriteSheet> spriteSheets = new HashMap<>();
    private static Map<String, Image> textures = new HashMap<>();

    public static void addSpriteSheet(String key, SpriteSheet spriteSheet) {
        spriteSheets.put(key, spriteSheet);
    }

    public static SpriteSheet getSpriteSheet(String key) {
        return spriteSheets.get(key);
    }

    public static void removeSpriteSheet(String key) {
        spriteSheets.remove(key);
    }

    public static Image getTexture(String textureKey) {
        return textures.get(textureKey);
    }

    public static void addTexture(String textureKey, Image texture) {
        textures.put(textureKey, texture);
    }

    public static void removeTexture(String textureKey) {
        textures.remove(textureKey);
    }
}
