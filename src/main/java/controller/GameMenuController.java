package controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import model.Airplane;
import model.Boss;
import transition.BossAnimation;

public class GameMenuController {

    public static Pane pane;
    private static float hearts = 5;
    private static float hitPercentage = 100;
    private static float damagePercentage = 100;

    public static void setupDifficulty(int difficulty) {
        switch (difficulty) {
            case (0):
                hearts = 10;
                hitPercentage = 50;
                damagePercentage = 150;
                break;
            case (1):
                hearts = 5;
                hitPercentage = 100;
                damagePercentage = 100;
                break;
            case (2):
                hearts = 2;
                hitPercentage = 150;
                damagePercentage = 50;
                break;
        }
    }

    public static Pane createPane() {
        Pane pane = new Pane();
        pane.setPrefHeight(640);
        pane.setPrefWidth(1080);
        Airplane.updateInstance(hearts, hitPercentage, damagePercentage, pane);
        Airplane airplane = Airplane.getInstance();
        airplane.setBackGround("/view/frames/red.png");
        Boss.updateInstance(25, pane);//TODO configure numbers
        Boss boss = Boss.getInstance();
        BossAnimation bossAnimation = new BossAnimation();
        bossAnimation.play();
        airplane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String keyName = event.getCode().getName();
                switch (keyName){
                    case ("Up"):
                        airplane.moveUp();
                        airplane.setBackGround("/view/frames/redUp.png");
                        break;
                    case ("Down"):
                        airplane.moveDown();
                        airplane.setBackGround("/view/frames/redDown.png");
                        break;
                    case ("Right"):
                        airplane.moveRight();
                        break;
                    case ("Left"):
                        airplane.moveLeft();
                        break;
                    case ("Space"):
                        airplane.shoot(boss);
                        break;
                }
            }
        });
        airplane.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                airplane.setBackGround("/view/frames/red.png");
            }
        });
        pane.getChildren().add(airplane);
        pane.getChildren().add(boss);
        return pane;
    }
}
