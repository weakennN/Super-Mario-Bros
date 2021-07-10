package Game.Common;

import ECS.SprtieRenderer.SpriteSheet;
import Game.Animator.Animator;

import java.util.HashMap;
import java.util.Map;

public class SpriteSheetContainer {

    private static Map<String, SpriteSheet> spriteSheets;

    public static void init() {

        spriteSheets = new HashMap<>();
        spriteSheets.put(GlobalVariables.MARIO_SPRITE_SHEET, new SpriteSheet(Animator.MARIO_SPRITE_SHEET, 50, 51, 0));
        spriteSheets.put(GlobalVariables.GOOMBA_SPRITE_SHEET_KEY, new SpriteSheet(Animator.GOOMBA_SPRITE_SHEET, 50, 50, 0));
        spriteSheets.put(GlobalVariables.ITEM_BOX_SPITE_SHEET_KEY, new SpriteSheet(Animator.ITEM_BOX_SPRITE_SHEET, 50, 50, 0));
        spriteSheets.put(GlobalVariables.KOOPA_SPRITE_SHEET_KEY, new SpriteSheet(Animator.KOOPA_SPRITE_SHEET, 50, 75, 0));
        spriteSheets.put(GlobalVariables.BIG_MARIO_SPRITE_SHEET_KEY, new SpriteSheet(Animator.BIG_MARIO_SPRITE_SHEET, 45, 90, 0));
        spriteSheets.put(GlobalVariables.FIRE_MARIO_SPRITE_SHEET_KEY, new SpriteSheet(Animator.FIRE_MARIO_SPRITE_SHEET, 45, 90, 0));
        spriteSheets.put(GlobalVariables.COIN_SPRITE_SHEET_KEY, new SpriteSheet(Animator.COIN_SPRITE_SHEET, 50, 47, 0));
    }

    public static SpriteSheet getSpriteSheet(String spriteSheet) {
        return spriteSheets.get(spriteSheet);
    }
}
