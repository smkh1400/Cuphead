package transition;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.Airplane;
import model.Boss;
import model.Egg;

public class BossShootAnimation extends Transition {

    private boolean shooted;

    public BossShootAnimation() {
        setCycleDuration(Duration.millis(1000));
        setCycleCount(1);
        this.shooted = false;
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 11) + 1;
        Boss.getInstance().setBackGround("/transition/Boss/BossShoot/" + frame + ".png");
        if (frame == 5 && !shooted) {
            shooted = true;
            Boss.getInstance().shootEgg((int) Boss.getInstance().getX(), (int) (Boss.getInstance().getY() + 100));
        }
        if (v == 1) {
            Boss.getInstance().getBossAnimation().play();
            shooted = false;
        }
    }
}
