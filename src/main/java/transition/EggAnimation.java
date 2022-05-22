package transition;

import javafx.animation.Transition;
import javafx.util.Duration;
import model.Airplane;
import model.Egg;

public class EggAnimation extends Transition {

    private Airplane airplane;
    private Egg egg;
    private int speed;

    public EggAnimation(Egg egg, Airplane airplane) {
        this.airplane = airplane;
        this.egg = egg;
        setCycleDuration(Duration.millis(3));
        setCycleCount(1);
        this.speed = 10;

    }


    @Override
    protected void interpolate(double v) {
        egg.setX(egg.getX() - speed);

        if (egg.hasCollision(airplane)) {
            airplane.getHit();
        }

        if (v == 1) {
            //TODO destroy egg
        }
    }
}
