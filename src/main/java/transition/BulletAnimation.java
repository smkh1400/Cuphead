package transition;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.Airplane;
import model.Boss;
import model.Bullet;
import model.MiniBoss;

public class BulletAnimation extends Transition {
    private Bullet bullet;
    private int speed;
    private float damagePercentage;
    private boolean resetCoolDown;

    public BulletAnimation(Bullet bullet, float damagePercentage) {
        this.bullet = bullet;
        this.damagePercentage = damagePercentage;
        setCycleDuration(Duration.millis(3000));
        setCycleCount(1);
        this.speed = 10;
        this.resetCoolDown = false;
    }

    @Override
    protected void interpolate(double v) {
        bullet.setX(bullet.getX() + speed);
        if (Boss.getInstance() != null && bullet.hasCollision(Boss.getInstance()) && !bullet.isDone()) {
            bullet.getPane().getChildren().remove(bullet);
            bullet.done();
            Boss.getInstance().getHit(damagePercentage);
        }

        for (int i = 0; i < 3; i++) {
            MiniBoss miniBoss = MiniBoss.getMiniBosses().get(i);
            if (bullet.hasCollision(miniBoss) && !bullet.isDone()) {
                bullet.getPane().getChildren().remove(bullet);
                bullet.done();
                miniBoss.getHit(damagePercentage);
                break;
            }
        }

        if (v * 36 >= 1 && !resetCoolDown) {
            Airplane.coolDown = true;
            resetCoolDown = true;
        }
        if (v == 1) {
            bullet.getPane().getChildren().remove(bullet);
            stop();
        }
    }
}
