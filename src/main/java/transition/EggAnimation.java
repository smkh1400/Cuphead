package transition;

import controller.GameMenuController;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Airplane;
import model.Boss;
import model.Egg;

public class EggAnimation extends Transition {

    private Pane pane = Boss.getInstance().getPane(); // TODO find better way to find pane to remove egg
    private Egg egg;
    private int speed;

    public EggAnimation(Egg egg) {
        GameMenuController.animations.put("eggAnimation", this);
        this.egg = egg;
        setCycleDuration(Duration.millis(10));
        setCycleCount(-1);
        this.speed = 1;

    }


    @Override
    protected void interpolate(double v) {
        egg.setX(egg.getX() - speed);

        if (Airplane.getInstance() != null &&  egg.hasCollision(Airplane.getInstance()) && !egg.isDone()) {
            pane.getChildren().remove(egg);
            egg.done();
            Airplane.getInstance().getHit();
        }

        if (v == 1) {
            //TODO destroy egg if needed
        }
    }
}
