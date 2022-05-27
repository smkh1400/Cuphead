package controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import view.Game;

public class MainMenuController {

    public static String username;

    public static void startNewGameFromEndMenu() {
        Pane gameMenuPane = GameMenuController.createPane();
        Scene gameMenuScene = new Scene(gameMenuPane);
        gameMenuPane.getChildren().get(2).requestFocus();
        Game.getScenes().remove("gameMenu");
        Game.getScenes().put("gameMenu", gameMenuScene);
        Game.setScene("gameMenu");
    }

    public void startNewGame() {
        GameMenuController.username = username;
        Pane gameMenuPane = GameMenuController.createPane();
        Scene gameMenuScene = new Scene(gameMenuPane);
        gameMenuPane.getChildren().get(2).requestFocus();
        Game.getScenes().remove("gameMenu");
        Game.getScenes().put("gameMenu", gameMenuScene);
        Game.setScene("gameMenu");
    }

    public void openProfile() {
        ProfileMenuController.username = username;
        Game.setScene("profileMenu");
    }

    public void showScoreBoard() {
        HighScoreController.createPane();
    }

    public void exit() {
        System.exit(0);
    }

    public void goToSettings() {
        Game.setScene("settings");
    }
}
;