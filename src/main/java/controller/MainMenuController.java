package controller;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import view.Game;

public class MainMenuController {

    public static String username;


    public void startNewGame() {
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
