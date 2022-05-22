package transition;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.Boss;

public class BossShootAnimation extends Transition {

    public BossShootAnimation() {
        setCycleDuration(Duration.millis(1000));
        setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 22) + 1;
        if (Boss.getInstance() != null) {
            Boss.getInstance().setBackGround("/transition/BossShoot/1.png");
        }
    }
}
