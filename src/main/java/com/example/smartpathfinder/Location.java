package com.example.smartpathfinder;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;

/**
 * This class represents each location on the map including the start and end points of the route.
 * Locations are being set on the map by a mouse click.
 */
public class Location extends Rectangle {

    private static final double WIDTH = 15;
    private static final double HEIGHT = 15;
    private final Color color = Color.LIGHTBLUE;
    Location nextStop;

    private static int count = 0;
    public int locationID;

    public Location() {

        setWidth(WIDTH);
        setHeight(HEIGHT);
        setFill(color);
        locationID = count++;
        this.nextStop = null;
    }

    public Color getColor() {
        return color;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Location.count = count;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public Location getNextStop() {
        return nextStop;
    }

    public void setNextStop(Location nextStop) {
        this.nextStop = nextStop;
    }
}
