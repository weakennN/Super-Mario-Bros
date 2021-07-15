package ECS.Animator;

import ECS.Animator.Animation.Animation;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class AnimationController {

    private Map<String, Animation> animations;
    private Map<String, Animation> currentPlaying;

    public AnimationController() {
        this.animations = new HashMap<>();
        this.currentPlaying = new LinkedHashMap<>();
    }

    public void createAnimation(String id, Animation animation) {
        this.animations.put(id, animation);
    }

    public void playAnimation(String animation) {
        if (this.animations.get(animation).isActive()) {
            return;
        }
        this.currentPlaying.put(animation, this.animations.get(animation));
        this.animations.get(animation).play();
    }

    public void stop() {
        for (Map.Entry<String, Animation> entry : this.currentPlaying.entrySet()) {
                entry.getValue().stop();
        }
    }

    public Animation getAnimation(String animation) {
        return this.animations.get(animation);
    }
}
