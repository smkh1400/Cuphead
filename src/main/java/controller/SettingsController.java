package controller;

import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import view.Game;

public class SettingsController {

    public CheckBox easy;
    public CheckBox medium;
    public CheckBox hard;
    public CheckBox sound;

    public void setEasy() {
        medium.setSelected(false);
        hard.setSelected(false);
        if (!easy.isSelected())
            easy.setSelected(true);
        GameMenuController.setupDifficulty(0);
    }

    public void setMedium() {
        easy.setSelected(false);
        hard.setSelected(false);
        if (!medium.isSelected())
            medium.setSelected(true);
        GameMenuController.setupDifficulty(1);
    }

    public void setHard() {
        easy.setSelected(false);
        medium.setSelected(false);
        if (!hard.isSelected())
            hard.setSelected(true);
        GameMenuController.setupDifficulty(2);
    }

    public void setOn() {
        if (!sound.isSelected()) {
            Game.turnOffAudio();
        } else {
            Game.turnOnAudio("otherMenu");
        }
    }

    public void backToMainMenu() {
        Game.setScene("mainMenu");
    }
}
