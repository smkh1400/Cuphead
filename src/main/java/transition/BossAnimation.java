package transition;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.Boss;

public class BossAnimation extends Transition {

    private Boss boss;

    public BossAnimation(Boss boss) {
        this.boss = boss;
        setCycleDuration(Duration.millis(1000));//TODO configure number
        setCycleCount(-1);
    }


    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 10) + 1;
        boss.setBackGround("/transition/Boss/" + Math.min(frame, 12 - frame) + ".png");
        System.out.println(boss.getHealth());
        if (boss.getHealth() <= 0) {
            boss.getPane().getChildren().remove(boss);
        }
    }
}
