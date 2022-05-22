package controller;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import view.Game;

public class MainMenuController {

    public static String username;


    public void startNewGame() {
        Pane gameMenuPane = GameMenuController.createPane();
        Scene gameMenuScene = new Scene(gameMenuPane);
        gameMenuPane.getChildren().get(0).requestFocus();
        Game.getScenes().put("gameMenu", gameMenuScene);
        Game.setScene("gameMenu");
    }

    public void openProfile() {
        ProfileMenuController.username = username;
        Game.setScene("profileMenu");
    }

    public void showScoreBoard() {
    }

    public void exit() {
        exit();
    }

    public void goToSettings() {
        Game.setScene("settings");
    }
}
