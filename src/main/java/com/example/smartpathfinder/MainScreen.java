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

import java.util.ArrayList;
import java.util.HashSet;

public class MainScreen extends VBox {

    private static final MainScreen mainScreen = new MainScreen();
    private Button createRoute, clearPath, clearMap, exit;
    private Board map;
    private final int CELL_WIDTH = 15;
    private final int CELL_HEIGHT = 15;
    private final int CELL_GAP = 1;
    private ArrayList<Location> unvisitedLocations;
    private HashSet<Location> allStops;
    private Route route;

    private MainScreen(){
        unvisitedLocations = new ArrayList<>();
        allStops = new HashSet<>();
        createRoute = new Button("Create Route");
        clearPath = new Button("Clear Path");
        clearMap = new Button("Clear Map");
        exit = new Button("Exit");
    }

    public static MainScreen getInstance(){
        mainScreen.setSpacing(2);
        HBox controlPanel = mainScreen.createControlPanel();
        Board map = mainScreen.getMap();
        map.initializeGraph();
        mainScreen.getChildren().addAll(controlPanel, map);
        return mainScreen;
    }

    public Board getMap(){
        map = new Board(CELL_WIDTH, CELL_HEIGHT, CELL_GAP);
        map.setOnMousePressed(mainScreen::onMapMousePressed);
        map.setOnMouseDragged(mainScreen::onLocationDragged);
        return map;
    }


    public HBox createControlPanel(){
        HBox controlPanel = new HBox();
        controlPanel.setSpacing(30);
        controlPanel.setAlignment(Pos.CENTER);
        buttonFunctions();
        controlPanel.getChildren().addAll(createRoute, clearPath, clearMap, exit);
        return controlPanel;
    }

    void buttonFunctions(){
        createRoute.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("smartest route: ");
                route = new Route();
                route.getSmartRoute(unvisitedLocations);
                System.out.println("all stops size: " + unvisitedLocations.size());
                map.clearPath(unvisitedLocations);
                ArrayList<Location> pathNodes = map.findPathNodes(route.getHeadOfRoute(), unvisitedLocations);
                map.paint(pathNodes);
            }
        });
        clearPath.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                map.clearPath(unvisitedLocations);
            }
        });

        /*
         * This button should restart everything so a new grid map will be created and locations will start from 0
         */

        clearMap.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainScreen.getChildren().remove(map);
                map = mainScreen.getMap();
                map.initializeGraph();
                mainScreen.getChildren().add(map);
                unvisitedLocations.clear();
                Location.setCount(0);
            }
        });
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                WelcomeScreen.mainStage.close();
            }
        });
    }

    void printNodes(ArrayList<Location> pathNodes){
        for(int i = 0; i < pathNodes.size(); i++){
            System.out.println("row: " + pathNodes.get(i).getRow() + " col: " + pathNodes.get(i).getColumn());
        }
    }

    private void onMapMousePressed(MouseEvent mouseEvent){
        int rows = (int)(mouseEvent.getX()/(CELL_WIDTH + CELL_GAP));
        int cols = (int)(mouseEvent.getY()/(CELL_HEIGHT + CELL_GAP));

        //send color code 1 for location color, 2 for path color
        Location location = new Location(rows, cols, CELL_WIDTH, CELL_HEIGHT, 1);
        if(!unvisitedLocations.contains(location)) {
            map.add(location, rows, cols);
            Text lbl = new Text("" + location.getLocationID());
            lbl.setFill(Color.YELLOW);
            map.setAlignment(Pos.CENTER);
            map.add(lbl, rows, cols, 2, 1);
            unvisitedLocations.add(location);
        }

    }

    private void onLocationDragged(MouseEvent e) {

    }
}

