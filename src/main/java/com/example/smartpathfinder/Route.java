package com.example.smartpathfinder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Route{

    private ArrayList<Location> unvisitedLocations;
    private ArrayList<Location> visitedLocations;
    private Location headOfRoute;
    private int calculatedDistance = 0;

    public Route(ArrayList<Location> unvisitedLocations){
        this.unvisitedLocations = unvisitedLocations;
        visitedLocations = new ArrayList<>();
        headOfRoute = unvisitedLocations.get(0);
    }

    public void calculateRoute(){
        calculatedDistance = 0;
        for(int i = 0; i < unvisitedLocations.size()-1; i++){
            int col = Board.getColumnIndex(unvisitedLocations.get(i));
            int row = Board.getRowIndex(unvisitedLocations.get(i));
            int runnerCol = Board.getColumnIndex(unvisitedLocations.get(i+1));
            int runnerRow = Board.getRowIndex(unvisitedLocations.get(i+1));
            int dist = Math.abs(col - runnerCol) + Math.abs(row - runnerRow);
            System.out.println("distance is " + dist);
            System.out.println("total distance was " + calculatedDistance);
            calculatedDistance += dist;
            System.out.println("total distance is " + calculatedDistance);
        }
    }

    public void calculateSmartRoute(){
        calculatedDistance = 0;
        visitedLocations.add(unvisitedLocations.get(0));
        unvisitedLocations.remove(0);
        int closest = 0;
        while(unvisitedLocations.size() > 0){

            int next = Integer.MAX_VALUE;
            for(int i = 0; i < unvisitedLocations.size(); i++){
                int col = Board.getColumnIndex(visitedLocations.get(visitedLocations.size() - 1));
                int row = Board.getRowIndex(visitedLocations.get(visitedLocations.size() - 1));
                int runnerCol = Board.getColumnIndex(unvisitedLocations.get(i));
                int runnerRow = Board.getRowIndex(unvisitedLocations.get(i));
                int dist = Math.abs(col - runnerCol) + Math.abs(row - runnerRow);
                if(dist < next){
                    next = dist;
                    closest = i;
                    calculatedDistance += next;
                }
            }

            visitedLocations.add(unvisitedLocations.get(closest));
            unvisitedLocations.remove(closest);
        }
    }
    public void print(){
        for(int i = 0;  i < unvisitedLocations.size(); i++){
            System.out.println(unvisitedLocations.get(i).getLocationID());
        }
        System.out.println("total distance travelled is: " + calculatedDistance);
    }

    public void printRoute(){
        for(int i = 0;  i < visitedLocations.size(); i++){
            System.out.println(visitedLocations.get(i).getLocationID());
        }
        System.out.println("total distance travelled is: " + calculatedDistance);
    }


    public void draw() {
        if(!unvisitedLocations.isEmpty()){

        }
    }

    void paint(Location from, Location to){
        //paint horizontal
        int origin_col = Board.getColumnIndex(from);
        int origin_rows = Board.getColumnIndex(from);
        int targetCol = Board.getColumnIndex(to);
        int targetRow = Board.getRowIndex(to);

        while(origin_col < targetCol){
           Rectangle spot = new Rectangle(15,15, Color.YELLOW);

        }

        //paint vertical


    }
}
