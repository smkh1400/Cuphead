package transition;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.Airplane;
import model.Boss;
import model.Bullet;

public class BulletAnimation extends Transition {

    private Boss boss;
    private Bullet bullet;
    private int speed;
    private int damagePercentage;
    private boolean resetCoolDown;

    public BulletAnimation(Bullet bullet, Boss boss, int damagePercentage) {
        this.bullet = bullet;
        this.boss = boss;
        this.damagePercentage = damagePercentage;
        setCycleDuration(Duration.millis(3000));
        setCycleCount(1);
        this.speed = 10;
        this.resetCoolDown = false;
    }

    @Override
    protected void interpolate(double v) {
        bullet.setX(bullet.getX() + speed);
        if (bullet.hasCollision(boss)) {
            bullet.getPane().getChildren().remove(bullet);
            stop();
            boss.getHit(damagePercentage);
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
