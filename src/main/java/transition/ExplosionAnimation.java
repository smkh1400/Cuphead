package transition;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.Boss;
import model.Explosion;

public class ExplosionAnimation extends Transition {

    private Explosion explosion;

    public ExplosionAnimation(Explosion explosion) {
        this.explosion = explosion;
        setCycleDuration(Duration.millis(500));
        setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 2) + 1;
        explosion.setBackGround("../resources/transition/Explosion/boom" + frame + ".png");
    }
}
