package com.example.smartpathfinder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeScreen extends Application {

    public static Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        mainStage.centerOnScreen();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcome-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        mainStage.setTitle("Smart Path Finder");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void onGetStartedButtonClick() throws IOException {
        MainScreen mainScreen = MainScreen.getInstance();
        Scene scene = new Scene(mainScreen);
        mainStage.setScene(scene);
    }

    public static void changeScene(Scene scene){
        mainStage.setScene(scene);
    }

    public void exit() throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("welcome-screen.fxml"));
        changeScene(new Scene(root.load()));
    }


}
