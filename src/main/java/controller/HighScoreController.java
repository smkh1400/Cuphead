package controller;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.User;
import view.Game;

import java.util.HashMap;
import java.util.List;

public class HighScoreController {

    public static void createPane() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(1080);
        borderPane.setPrefHeight(640);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        List<User> data =  UserDatabaseController.getInstance().getDate();
        for (int i = 0; i < data.size(); i++) {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(100);
            Text name = new Text();
            name.setText(data.get(i).getUsername());
            Text scoreText = new Text();
            scoreText.setText(String.valueOf(data.get(i).getScore()));
            hBox.getChildren().add(name);
            hBox.getChildren().add(scoreText);
            vBox.getChildren().add(hBox);
        }

        Button backToMainMenu = new Button();
        backToMainMenu.setText("back to main menu");
        backToMainMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Game.setScene("mainMenu");
            }
        });
        vBox.getChildren().add(backToMainMenu);

        borderPane.setCenter(vBox);

        Scene scoreBoard = new Scene(borderPane);
        Game.addScene("scoreBoard", scoreBoard);
        Game.setScene("scoreBoard");

    }

}
