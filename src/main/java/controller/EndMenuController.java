package controller;

import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import view.Game;

import javax.swing.*;
import java.util.Map;

public class EndMenuController {

    public static String message;
    public static int score;

    public static void createPane() {
        for (Map.Entry<String, Transition> set : GameMenuController.animations.entrySet()) {
            set.getValue().stop();
        }
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefHeight(640);
        borderPane.setPrefWidth(1080);
        VBox vBox = new VBox();
        Text text = new Text(message);
        text.setStyle("-fx-font-family: \"Times New Roman\";\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 26;\n" +
                "    -fx-fill: black;");
        Text scoreText = new Text(String.valueOf(score));
        scoreText.setStyle("-fx-font-family: \"Times New Roman\";\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 26;\n" +
                "    -fx-fill: black;");
        Button restart = new Button();
        restart.setStyle("-fx-font-family: Gabriola;\n" +
                "    -fx-pref-width: 342;\n" +
                "    -fx-background-color: #efe4b0;\n" +
                "    -fx-font-size: 18;");
        restart.setText("restart game");
        restart.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                restartGame();
            }
        });

        Button mainMenu = new Button();
        mainMenu.setStyle("-fx-font-family: Gabriola;\n" +
                "    -fx-pref-width: 342;\n" +
                "    -fx-background-color: #efe4b0;\n" +
                "    -fx-font-size: 18;");
        mainMenu.setText("back to main menu");
        mainMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                backToMainMenu();
            }
        });
        vBox.getChildren().add(text);
        vBox.getChildren().add(scoreText);
        vBox.getChildren().add(restart);
        vBox.getChildren().add(mainMenu);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        borderPane.setCenter(vBox);
        Scene scene = new Scene(borderPane);
        Game.addScene("endMenu", scene);
        Game.setScene("endMenu");
    }

    public static void restartGame() {
        MainMenuController.startNewGameFromEndMenu();
    }

    public static void backToMainMenu() {
        Game.setScene("mainMenu");
    }
}
