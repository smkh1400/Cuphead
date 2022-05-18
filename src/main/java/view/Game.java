package view;

import controller.GameMenuController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Game extends Application {

    static HashMap<String, Scene> scenes = new HashMap<>();
    static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Game.stage = stage;
        stage.setTitle("CupHead");
        URL loginMenuAddress = new URL(view.Game.class.getResource("fxml/LoginMenu.fxml").toExternalForm());
        URL mainMenuAddress = new URL(view.Game.class.getResource("fxml/MainMenu.fxml").toExternalForm());
        URL profileMenuAddress = new URL(view.Game.class.getResource("fxml/ProfileMenu.fxml").toExternalForm());
        URL settingsAddress = new URL(view.Game.class.getResource("fxml/settings.fxml").toExternalForm());
        BorderPane loginMenuPane = FXMLLoader.load(loginMenuAddress);
        BorderPane mainMenuPane = FXMLLoader.load(mainMenuAddress);
        BorderPane profileMenuPane = FXMLLoader.load(profileMenuAddress);
        BorderPane settingsPane = FXMLLoader.load(settingsAddress);
        Pane gameMenuPane = GameMenuController.createPane();
        Scene loginMenuScene = new Scene(loginMenuPane);
        Scene mainMenuScene = new Scene(mainMenuPane);
        Scene profileMenuScene = new Scene(profileMenuPane);
        Scene settingsScene = new Scene(settingsPane);
        Scene gameMenuScene = new Scene(gameMenuPane);
        gameMenuPane.getChildren().get(0).requestFocus();
        scenes.put("loginMenu", loginMenuScene);
        scenes.put("mainMenu", mainMenuScene);
        scenes.put("profileMenu", profileMenuScene);
        scenes.put("settings", settingsScene);
        scenes.put("gameMenu", gameMenuScene);
        setScene("loginMenu");
    }

    public static void setScene(String menuName) {
        Scene currentMenu = scenes.get(menuName);
        stage.setScene(currentMenu);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
