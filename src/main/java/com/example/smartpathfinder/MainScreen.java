package com.example.smartpathfinder;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

public class MainScreen extends HBox {

    private static final MainScreen mainScreen = new MainScreen();
    private Button createRoute, smartRoute, reset, exit;
    private Board map;
    private int cell_width = Board.getCellWidth();
    private int cell_height = Board.getCellHeight();
    private int cell_gap = Board.getGap();
    private ArrayList<Location> unvisitedLocations;

    private MainScreen(){
        unvisitedLocations = new ArrayList<>();
        createRoute = new Button("Create Route");
        smartRoute = new Button("Smart Route");
        reset = new Button("Reset");
        exit = new Button("Exit");
    }

    public static MainScreen getInstance(){
        mainScreen.setSpacing(2);
        VBox controlPanel = mainScreen.createControlPanel();
        Board map = mainScreen.getMap();
        mainScreen.getChildren().addAll(controlPanel, map);
        return mainScreen;
    }

    public Board getMap(){
        map = Board.initializeGraph();
        map.setOnMousePressed(mainScreen::onMapMousePressed);
        map.setOnMouseDragged(mainScreen::onLocationDragged);
        return map;
    }


    public VBox createControlPanel(){
        VBox controlPanel = new VBox();
        controlPanel.setSpacing(30);
        controlPanel.setAlignment(Pos.CENTER);
        buttonFunctions();
        controlPanel.getChildren().addAll(createRoute, smartRoute, reset, exit);
        return controlPanel;
    }

    void buttonFunctions(){
        createRoute.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Regular route: ");
                Route route = new Route(unvisitedLocations);
                route.calculateRoute();
                route.print();
            }
        });
        smartRoute.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Here is the shortest path: ");
                Route route = new Route(unvisitedLocations);
                route.calculateSmartRoute();
                route.printRoute();
            }
        });
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                WelcomeScreen.mainStage.close();
            }
        });
    }

    private void onMapMousePressed(MouseEvent mouseEvent){
        int rows = (int)(mouseEvent.getX()/(cell_width + cell_gap));
        int cols = (int)(mouseEvent.getY()/(cell_height + cell_gap));

        Location location = new Location();
        map.add(location, rows, cols);

        Text lbl = new Text("" + location.getLocationID());
        lbl.setFill(Color.BLACK);
        lbl.setTextAlignment(TextAlignment.CENTER);
        map.setAlignment(Pos.CENTER);
        map.add(lbl, rows, cols, 2, 1);
        unvisitedLocations.add(location);
    }

    private void onLocationDragged(MouseEvent e) {
        int rows = (int)(e.getX()/(cell_width + cell_gap));
        int cols = (int)(e.getY()/(cell_height + cell_gap));

    }
}

