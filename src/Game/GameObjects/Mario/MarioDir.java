package Game.GameObjects.Mario;

public class MarioDir {

    public static boolean marioIdleFacingRight = false;
    public static boolean marioIdleFacingLeft = false;
    public static boolean marioRunningRight = false;
    public static boolean marioRunningLeft = false;
    public static boolean marioJumpingRight = false;
    public static boolean marioJumpingLeft = false;

    public static void disableDirs() {
        marioIdleFacingRight = false;
        marioIdleFacingLeft = false;
        marioRunningRight = false;
        marioRunningLeft = false;
        marioJumpingRight = false;
        marioJumpingLeft = false;
    }
}
