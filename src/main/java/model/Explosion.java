package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Explosion extends Rectangle {

    public Explosion(double x, double y) {
        super(200,180, x, y);
    }

    public void setBackGround(String URL) {
        this.setFill(new ImagePattern(new Image(getClass().getResource(URL).toExternalForm())));
    }

}
