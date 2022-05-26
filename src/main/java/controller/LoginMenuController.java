package controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sun.applet.Main;
import view.Game;

public class LoginMenuController {
    public TextField password;
    public TextField username;
    public Label message;

    public void loginPlayer() {
        if (!checkUsernameAndPasswordValidity()) {
            message.setText("Username and password didn't match!");
        } else {
            startMenu(username.getText());
        }

    }

    public void registerPlayer() {
        if (userExisted())
            message.setText("Username existed");
        else if (username.getText().length() == 0)
            message.setText("Invalid Username");
        else if (password.getText().length() == 0)
            message.setText("Invalid password");
        else {
            UserDatabaseController.getInstance().addUser(username.getText(), password.getText());
            startMenu(username.getText());
        }
    }

    public void playAsGuest() {
        startMenu("");
    }

    public boolean checkUsernameAndPasswordValidity() {
        if (!this.userExisted())
            return false;
        else if (UserDatabaseController.getInstance().getPasswordWithUsername(username.getText()).equals(password.getText()))
            return true;
        return false;
    }

    public boolean userExisted() {
        String x = UserDatabaseController.getInstance().getPasswordWithUsername(username.getText());
        return x != null;
    }

    private void startMenu(String username) {
        this.username.setText("");
        this.password.setText("");
        MainMenuController.username = username;
        Game.setScene("mainMenu");
    }
}
