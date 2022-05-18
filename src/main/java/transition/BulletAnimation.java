package transition;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.Airplane;
import model.Bullet;

import java.awt.*;

public class BulletAnimation extends Transition {

    private Bullet bullet;
    private int speed;
    private boolean resetCoolDown;

    public BulletAnimation(Bullet bullet) {
        this.bullet = bullet;
        setCycleDuration(Duration.millis(3000));
        setCycleCount(1);
        this.speed = 10;
        this.resetCoolDown = false;
    }

    @Override
    protected void interpolate(double v) {
        bullet.setX(bullet.getX() + speed);
        if (v * 36 >= 1 && !resetCoolDown) {
            Airplane.coolDown = true;
            resetCoolDown = true;
        }
        if (v == 1) {
            //TODO destroy bullet
        }
    }
}
