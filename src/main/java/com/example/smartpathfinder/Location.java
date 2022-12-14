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

    private int column;
    private int row;
    private static int count = 0;
    private int distanceToNextStop;
    Location nextStop;
    public int locationID;

    public Location(int x, int y, int width, int height, int color) {
        row = x;
        column = y;
        setWidth(width);
        setHeight(height);
        if(color == 1) {
            setFill(Color.PURPLE);
            locationID = count++;
        }
        else
            setFill(Color.LIGHTBLUE);
        distanceToNextStop = Integer.MAX_VALUE;
        this.nextStop = null;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public static void setCount(int newCount) {
        count = newCount;
    }

    public static int getCount() {
        return count;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public void setDistanceToNextStop(int distanceToNextStop) {
        this.distanceToNextStop = distanceToNextStop;
    }

    public int getDistanceToNextStop() {
        return distanceToNextStop;
    }

    public Location getNextStop() {
        return nextStop;
    }

    public void setNextStop(Location nextStop) {
        this.nextStop = nextStop;
    }
}
