package controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.Game;

import java.util.Random;

public class ProfileMenuController {

    public static String username;

    public TextField newUsername;
    public TextField newPassword;
    public Label message;
    public Rectangle avatar;

    public void changeUsername() {
        if (username.equals(newUsername.getText())) {
            message.setText("set a new username");
        } else if (UserDatabaseController.getInstance().getPasswordWithUsername(newUsername.getText()) != null){
            message.setText("username already existed");
        } else {
            UserDatabaseController.getInstance().changeUsername(username, newUsername.getText());
            message.setText("username changed successfully");
        }
        username = newUsername.getText();
        newUsername.setText("");
    }

    public void changePassword() {
        String password = UserDatabaseController.getInstance().getPasswordWithUsername(username);
        if (password.equals(newPassword.getText())) {
            message.setText("set a new password");
        } else {
            UserDatabaseController.getInstance().changePassword(username, newPassword.getText());
            message.setText("password changed successfully");
        }
        newPassword.setText("");
    }

    public void logout() {
        Game.setScene("loginMenu");
    }

    public void deleteAccount() {
        UserDatabaseController.getInstance().removeUser(username);
        this.logout();
    }

    public void backToMainMenu() {
        Game.setScene("mainMenu");
    }

    public void changeAvatar() {
        Random random = new Random();
        int a = random.nextInt(3) + 1;
        this.avatar.setFill(new ImagePattern(new Image(getClass().getResource("/view/frames/avatars/avatar" + a + ".png").toExternalForm())));
    }
}
