package transition;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.Airplane;
import model.Boss;
import model.MiniBoss;

public class MiniBossAnimation extends Transition {

    private MiniBoss miniBoss;

    public MiniBossAnimation(MiniBoss miniBoss) {
        this.miniBoss = miniBoss;
        setCycleDuration(Duration.millis(1000));
        setCycleCount(-1);
    }


    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 6) + 1;
        miniBoss.setBackGround("../transition/MiniBoss/" + Math.min(frame, 8 - frame) + ".png");
        miniBoss.moveLeft();
        if (miniBoss.getHealth() <= 0 || miniBoss.getX() <= -150) {
            miniBoss.getPane().getChildren().remove(miniBoss);
            MiniBoss.getMiniBosses().remove(miniBoss);
            miniBoss.setX(1600);
            miniBoss.getPane().getChildren().add(miniBoss);
            MiniBoss.getMiniBosses().add(miniBoss);
        }

        if (Airplane.getInstance() != null && miniBoss.hasCollision(Airplane.getInstance())) {
            miniBoss.getPane().getChildren().remove(miniBoss);
            MiniBoss.getMiniBosses().remove(miniBoss);
            miniBoss.setX(1600);
            miniBoss.getPane().getChildren().add(miniBoss);
            MiniBoss.getMiniBosses().add(miniBoss);
            Airplane.getInstance().getHit();
        }

    }
}
