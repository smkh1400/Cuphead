package transition;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.Airplane;
import model.Boss;

public class BossAnimation extends Transition {

    private int direction; // 0 means down , 1 means up

    public BossAnimation() {
        this.direction = 1;
        setCycleDuration(Duration.millis(1000));//TODO configure number
        setCycleCount(-1);
    }


    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 10) + 1;

        if (Boss.getInstance() != null) {

            if (Boss.getInstance().hasCollision(Airplane.getInstance()))
                Airplane.getInstance().getHit(); // TODO set cool down for it

            if (Boss.getInstance().getShootCoolDown() == 5) {
                Boss.getInstance().setShootCoolDown(0);
                pause();
                Boss.getInstance().getBossShootAnimation().play();
            }

            Boss.getInstance().setBackGround("/transition/Boss/" + Math.min(frame, 12 - frame) + ".png");
            if (direction == 1) {
                if (!Boss.getInstance().moveUp()) {
                    direction = 0;
                }
            } else if (direction == 0) {
                if (!Boss.getInstance().moveDown()) {
                    direction = 1;
                }
            }
            if (Boss.getInstance().getHealth() <= 0) {
                Boss.getInstance().getPane().getChildren().remove(Boss.getInstance());
                Boss.removeInstance();
            }
        }

        if (v == 1) {
            if (Boss.getInstance() != null)
                Boss.getInstance().setShootCoolDown(Boss.getInstance().getShootCoolDown() + 1);
        }
    }
}
