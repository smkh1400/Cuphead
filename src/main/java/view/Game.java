package view;

import controller.GameMenuController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Game extends Application {

    static BorderPane sPane;
    static HashMap<String, Scene> scenes = new HashMap<>();
    static HashMap<String, AudioClip> audios = new HashMap<>();
    static Stage stage;
    public static boolean isAudioOn = true;

    @Override
    public void start(Stage stage) throws Exception {

        stage.getIcons().add(new Image(getClass().getResource("/view/frames/red.png").toExternalForm()));

        Game.stage = stage;
        stage.setTitle("CupHead");
        URL loginMenuAddress = new URL(view.Game.class.getResource("fxml/LoginMenu.fxml").toExternalForm());
        URL mainMenuAddress = new URL(view.Game.class.getResource("fxml/MainMenu.fxml").toExternalForm());
        URL profileMenuAddress = new URL(view.Game.class.getResource("fxml/ProfileMenu.fxml").toExternalForm());
        URL settingsAddress = new URL(view.Game.class.getResource("fxml/settings.fxml").toExternalForm());
        BorderPane loginMenuPane = FXMLLoader.load(loginMenuAddress);
        BorderPane mainMenuPane = FXMLLoader.load(mainMenuAddress);

        VBox v = (VBox) mainMenuPane.getTop();
        HBox h = (HBox) v.getChildren().get(7);
        Rectangle r1 = (Rectangle) h.getChildren().get(0);
        r1.setFill(new ImagePattern(new Image(getClass().getResource("/view/frames/red.png").toExternalForm())));
        Rectangle r2 = (Rectangle) h.getChildren().get(2);
        r2.setFill(new ImagePattern(new Image(getClass().getResource("/transition/Boss/1.png").toExternalForm())));

        BorderPane profileMenuPane = FXMLLoader.load(profileMenuAddress);

        HBox h1 = (HBox) profileMenuPane.getTop();
        Rectangle r = (Rectangle) h1.getChildren().get(0);
        r.setFill(new ImagePattern(new Image(getClass().getResource("/view/frames/avatars/10.jpg").toExternalForm())));

        BorderPane settingsPane = FXMLLoader.load(settingsAddress);

        sPane = settingsPane;

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
        audioClip1.setCycleCount(-1);
        Media media2 = new Media(new File("src/main/resources/music/music2.mp3").toURI().toString());
        AudioClip audioClip2 = new AudioClip(media2.getSource());
        audioClip2.setCycleCount(-1);

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

        VBox v = (VBox) sPane.getCenter();
        HBox h = (HBox) v.getChildren().get(1);
        CheckBox checkBox = (CheckBox) h.getChildren().get(1);
        checkBox.setSelected(isAudioOn);

        audios.get("otherMenu").stop();
        audios.get("gameMenu").stop();
    }

    public static void turnOnAudio(String menuName) {
        isAudioOn = true;

        VBox v = (VBox) sPane.getCenter();
        HBox h = (HBox) v.getChildren().get(1);
        CheckBox checkBox = (CheckBox) h.getChildren().get(1);
        checkBox.setSelected(isAudioOn);

        audios.get(menuName).play();
    }

    public static HashMap<String, Scene> getScenes() {
        return scenes;
    }

    public static void addScene(String name, Scene scene) {
        scenes.put(name, scene);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
