package view;

import controller.GameMenuController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Game extends Application {

    static HashMap<String, Scene> scenes = new HashMap<>();
    static HashMap<String, AudioClip> audios = new HashMap<>();
    static Stage stage;
    static boolean isAudioOn = true;

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
        Scene loginMenuScene = new Scene(loginMenuPane);
        Scene mainMenuScene = new Scene(mainMenuPane);
        Scene profileMenuScene = new Scene(profileMenuPane);
        Scene settingsScene = new Scene(settingsPane);
        scenes.put("loginMenu", loginMenuScene);
        scenes.put("mainMenu", mainMenuScene);
        scenes.put("profileMenu", profileMenuScene);
        scenes.put("settings", settingsScene);

        Media media1 = new Media(new File("src/main/resources/music/music1.mp3").toURI().toString());
        AudioClip audioClip1 = new AudioClip(media1.getSource());
        Media media2 = new Media(new File("src/main/resources/music/music2.mp3").toURI().toString());
        AudioClip audioClip2 = new AudioClip(media2.getSource());


        audios.put("gameMenu", audioClip1);
        audios.put("otherMenu", audioClip2);

        setScene("loginMenu");
    }

    public static void setScene(String menuName) {
        if (isAudioOn) {
            if (menuName.equals("gameMenu")) {
                setAudio("gameMenu");
            } else {
                setAudio("otherMenu");
            }
        }
        Scene currentMenu = scenes.get(menuName);
        stage.setScene(currentMenu);
        stage.show();
    }

    public static void setAudio(String menuName) {
        if (menuName.equals("otherMenu")) {
            audios.get("gameMenu").stop();
        } else {
            audios.get("otherMenu").stop();
        }

        if (!audios.get(menuName).isPlaying()) {
            audios.get(menuName).play();
        }
    }

    public static void turnOffAudio() {
        isAudioOn = false;
        audios.get("otherMenu").stop();
        audios.get("gameMenu").stop();
    }

    public static void turnOnAudio(String menuName) {
        isAudioOn = true;
        audios.get(menuName).play();
    }

    public static HashMap<String, Scene> getScenes() {
        return scenes;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
