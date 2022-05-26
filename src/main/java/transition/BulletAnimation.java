package transition;

import controller.GameMenuController;
import javafx.animation.Transition;
import javafx.util.Duration;
import model.Airplane;
import model.Boss;
import model.Bullet;
import model.MiniBoss;

public class BulletAnimation extends Transition {
    private Bullet bullet;
    private int speed;
    private boolean resetCoolDown;

    public BulletAnimation(Bullet bullet) {
        GameMenuController.animations.put("bulletAnimation", this);
        this.bullet = bullet;
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
            Boss.getInstance().getHit();
            Boss.getInstance().updateHealthBarLength();
        }

        for (int i = 0; i < 3; i++) {
            MiniBoss miniBoss = MiniBoss.getMiniBosses().get(i);
            if (bullet.hasCollision(miniBoss) && !bullet.isDone()) {
                bullet.getPane().getChildren().remove(bullet);
                bullet.done();
                miniBoss.getHitByBullet();
                break;
            }
        }

        if (v * 36 >= 1 && !resetCoolDown) {
            Airplane.coolDown = true;
            resetCoolDown = true;
        }
        if (v == 1) {
            bullet.getPane().getChildren().remove(bullet);
            bullet.done();
        }
    }
}
