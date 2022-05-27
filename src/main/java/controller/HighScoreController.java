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

        HBox h = new HBox();
        h.setAlignment(Pos.CENTER);
        h.setSpacing(10);
        Text player = new Text();
        player.setStyle("-fx-font-family: \"Times New Roman\";\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 28;\n" +
                "    -fx-fill: black;");
        player.setText("Player");
        Text score = new Text();
        score.setStyle("-fx-font-family: \"Times New Roman\";\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 28;\n" +
                "    -fx-fill: black;");
        score.setText("Score");

        h.getChildren().add(player);
        h.getChildren().add(score);
        vBox.getChildren().add(h);

        List<User> data =  UserDatabaseController.getInstance().getDate();
        for (int i = 0; i < Math.min(10, data.size()); i++) {
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(100);
            Text name = new Text();
            name.setStyle("-fx-font-family: \"Times New Roman\";\n" +
                    "    -fx-font-weight: bold;\n" +
                    "    -fx-font-size: 26;\n" +
                    "    -fx-fill: black;");
            name.setText(data.get(i).getUsername());
            Text scoreText = new Text();
            scoreText.setStyle("-fx-font-family: \"Times New Roman\";\n" +
                    "    -fx-font-weight: bold;\n" +
                    "    -fx-font-size: 26;\n" +
                    "    -fx-fill: black;");
            scoreText.setText(String.valueOf(data.get(i).getScore()));
            if (i == 0) {
                name.setStyle("-fx-fill: gold");
                scoreText.setStyle("-fx-fill: gold");
            } else if (i == 1) {
                name.setStyle("-fx-fill: silver");
                scoreText.setStyle("-fx-fill: silver");
            } else if (i == 2) {
                name.setStyle("-fx-fill: #B08D57");
                scoreText.setStyle("-fx-fill: #B08D57");
            }
            hBox.getChildren().add(name);
            hBox.getChildren().add(scoreText);
            vBox.getChildren().add(hBox);
        }

        Button backToMainMenu = new Button();
        backToMainMenu.setStyle("-fx-font-family: Gabriola;\n" +
                "    -fx-pref-width: 342;\n" +
                "    -fx-background-color: #efe4b0;\n" +
                "    -fx-font-size: 18;");
        backToMainMenu.setText("back to main menu");
        backToMainMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Game.setScene("mainMenu");
            }
        });

        borderPane.setTop(vBox);
        borderPane.setCenter(backToMainMenu);

        Scene scoreBoard = new Scene(borderPane);
        Game.addScene("scoreBoard", scoreBoard);
        Game.setScene("scoreBoard");

    }

}
