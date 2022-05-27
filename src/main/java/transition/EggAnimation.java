package transition;

import controller.GameMenuController;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Airplane;
import model.Boss;
import model.Egg;

public class EggAnimation extends Transition {

    private Pane pane = Boss.getInstance().getPane();
    private Egg egg;
    private int speed;

    public EggAnimation(Egg egg) {
        GameMenuController.animations.put("eggAnimation", this);
        this.egg = egg;
        setCycleDuration(Duration.millis(10));
        setCycleCount(-1);
        this.speed = 3;

    }


    @Override
    protected void interpolate(double v) {
        egg.setX(egg.getX() - speed);

        if (Airplane.getInstance() != null &&  egg.hasCollision(Airplane.getInstance()) && !egg.isDone()) {
            pane.getChildren().remove(egg);
            egg.done();
            Airplane.getInstance().getHit();
        }
    }
}
