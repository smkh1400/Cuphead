package controller;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.Game;

import java.io.File;
import java.util.Map;

public class PauseMenuController {


    public static void createPane() {
        for (Map.Entry<String, Transition> set : GameMenuController.animations.entrySet()) {
            if (set.getValue().getStatus().equals(Animation.Status.RUNNING))
            set.getValue().pause();
        }
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(1080);
        borderPane.setPrefHeight(640);
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);

        Button resume = new Button();
        resume.setStyle("-fx-font-family: Gabriola;\n" +
                "    -fx-pref-width: 342;\n" +
                "    -fx-background-color: #efe4b0;\n" +
                "    -fx-font-size: 24;");
        resume.setText("resume");
        resume.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resume();
            }
        });

        Label label = new Label();
        label.setStyle("-fx-font-weight: bold;\n" +
                "    -fx-font-family: Gabriola;\n" +
                "    -fx-font-size: 20;");
        label.setText("sound");

        CheckBox checkBox = new CheckBox();
        checkBox.setStyle("-fx-font-family: Gabriola; -fx-font-size: 22");
        checkBox.setText("on");
        checkBox.setSelected(Game.isAudioOn);
        checkBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!checkBox.isSelected()) {
                    Game.turnOffAudio();
                } else {
                    Game.turnOnAudio("gameMenu");
                }
            }
        });

        hBox.getChildren().add(label);
        hBox.getChildren().add(checkBox);

        Button restartGame = new Button();
        restartGame.setStyle("-fx-font-family: Gabriola;\n" +
                "    -fx-pref-width: 342;\n" +
                "    -fx-background-color: #efe4b0;\n" +
                "    -fx-font-size: 24;");
        restartGame.setText("restart game");
        restartGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                restartGame();
            }
        });

        Button backToMainMenu = new Button();
        backToMainMenu.setStyle("-fx-font-family: Gabriola;\n" +
                "    -fx-pref-width: 342;\n" +
                "    -fx-background-color: #efe4b0;\n" +
                "    -fx-font-size: 24;");
        backToMainMenu.setText("back to main menu");
        backToMainMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                backToMainMenu();
            }
        });

        vBox.getChildren().add(resume);
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(restartGame);
        vBox.getChildren().add(backToMainMenu);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        borderPane.setCenter(vBox);


        Scene pauseScene = new Scene(borderPane);

        Game.addScene("pauseMenu", pauseScene);
        Game.setScene("pauseMenu");
    }

    public static void restartGame() {
        MainMenuController.startNewGameFromEndMenu();
    }

    public static void backToMainMenu() {
        Game.setScene("mainMenu");
    }


    public static void resume() {
        for(Map.Entry<String , Transition> set: GameMenuController.animations.entrySet()) {
            if (set.getValue().getStatus().equals(Animation.Status.PAUSED))
            set.getValue().play();
        }
        Game.setScene("gameMenu");
    }
}
