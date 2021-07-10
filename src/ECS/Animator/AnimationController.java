package ECS.Animator;

import java.util.HashMap;
import java.util.Map;

public class AnimationController {

    private Map<String, Animation> animations;
    private Animation current;

    public AnimationController() {

        this.animations = new HashMap<>();
    }

    public void createAnimation(String id, Animation animation) {

        this.animations.put(id, animation);
    }

    public void playAnimation(String animation) {

        if (current != null) {
            this.current.stop();
        }

        this.current = this.animations.get(animation);

        this.current.play();
    }

    public void stop() {
        if (this.current != null) {
            this.current.stop();
        }
    }

    public Animation getAnimation(String animation) {
        return this.animations.get(animation);
    }
}
