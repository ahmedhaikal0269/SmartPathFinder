package com.example.smartpathfinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Route{

    private ArrayList<Location> visitedLocations;
    private Location headOfRoute;
    private int calculatedDistance = 0;
    private HashSet<Location> visitedStops;
    private HashMap<Integer, HashSet<Integer>> all_stops; // this is to store all stops so that they don't get colored with the route

    public Route(){
        visitedLocations = new ArrayList<>();
        all_stops = new HashMap<>();
        headOfRoute = null;
    }

    public void getSmartRoute(ArrayList<Location> all_locations){
        visitedStops = new HashSet<>();
        headOfRoute = all_locations.get(0);
        Location runner = headOfRoute;
        visitedStops.add(runner);
        while(visitedStops.size() != all_locations.size()){
            int distanceFound = Integer.MAX_VALUE;
            for(int i = 0; i < all_locations.size(); i++){
                Location currentLoc = all_locations.get(i);
                //add location to all stops hashmap
                if(!all_stops.containsKey(currentLoc.getRow())){
                    all_stops.put(currentLoc.getRow(), new HashSet<>());
                }
                if(!visitedStops.contains(currentLoc)){
                    int distance = getDistance(runner, currentLoc);
                    if(distance < runner.getDistanceToNextStop() && distance < distanceFound){
                        distanceFound = distance;
                        runner.nextStop = currentLoc;
                    }
                }
            }
            runner.setDistanceToNextStop(distanceFound);
            runner = runner.nextStop;
            visitedStops.add(runner);
        }
    }

    public int getDistance(Location origin, Location target){
        int originCol = Board.getColumnIndex(origin);
        int originRow = Board.getRowIndex(origin);
        int targetCol = Board.getColumnIndex(target);
        int targetRow = Board.getRowIndex(target);
        return Math.abs(originCol - targetCol) + Math.abs(originRow - targetRow);
    }

    public void printRoute(){
        Location runThrow = headOfRoute;
        while(runThrow != null){
            System.out.println(runThrow.getLocationID());
            runThrow = runThrow.nextStop;
        }
    }

    public Location getHeadOfRoute() {
        return headOfRoute;
    }
/*
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

    public void getEmBoy(){
        if(unvisitedLocations.get(0).getLocationID() == 0){
            headOfRoute = unvisitedLocations.get(0);
            visitedStops.add(unvisitedLocations.get(0));
        }
        for(int i = 0; i < unvisitedLocations.size(); i++){
            Location runner = headOfRoute;
            Location closest = headOfRoute;
            Location stop = unvisitedLocations.get(i);
            int lowest_distance_found = Integer.MAX_VALUE;
            if(!visitedStops.contains(stop)){
                visitedStops.add(stop);
                while(runner != null){
                    int stopRow = Board.getRowIndex(stop);
                    int stopCol = Board.getColumnIndex(stop);
                    int runnerRow = Board.getRowIndex(runner);
                    int runnerCol = Board.getColumnIndex(runner);
                    int distance = Math.abs(stopRow - runnerRow) + Math.abs(stopCol - runnerCol);
                    if(distance < runner.getDistanceToNextStop() && distance < lowest_distance_found){
                        runner.setDistanceToNextStop(distance);
                        closest = runner;
                        lowest_distance_found = distance;
                    }
                    runner = runner.nextStop;
                }
                stop.nextStop = closest.nextStop;
                closest.nextStop = stop;
            }
        }
    }
    public void createRoute(){
        for(int i = 1; i < unvisitedLocations.size(); i++){
            System.out.println("inside for loop");
            Location loc = unvisitedLocations.get(i);
            int nextStopCol = Board.getColumnIndex(loc);
            int nextStopRow = Board.getRowIndex(loc);
            //int next = Integer.MAX_VALUE;
            Location runner = headOfRoute;
            Location closest = headOfRoute;

            while(runner != null){
                System.out.println("inside while loop");
                int runnerCol = Board.getColumnIndex(runner);
                int runnerRow = Board.getRowIndex(runner);
                int distance = Math.abs(nextStopCol - runnerCol) + Math.abs(nextStopRow - runnerRow);
                if(distance <= runner.getDistanceToNextStop()){
                    runner.setDistanceToNextStop(distance);
                    closest = runner;
                }
                runner = runner.nextStop;
            }
            loc.nextStop = closest.nextStop;
            closest.nextStop = loc;
        }
        Location runThrow = headOfRoute;
        while(runThrow != null){
            System.out.println(runThrow.getLocationID());
            runThrow = runThrow.nextStop;
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



    public void getRoute() {
        headOfRoute = unvisitedLocations.get(0);
        Location loc = headOfRoute;
        visitedStops.add(loc);
        while(visitedStops.size() != unvisitedLocations.size()){
            int closestDistance = Integer.MAX_VALUE;
            int row = Board.getRowIndex(loc);
            int col = Board.getColumnIndex(loc);
            for(int i = 0; i < unvisitedLocations.size(); i++){
                Location currentLoc = unvisitedLocations.get(i);
                if(!visitedStops.contains(currentLoc)){
                    int currentRow = Board.getRowIndex(currentLoc);
                    int currentCol = Board.getColumnIndex(currentLoc);
                    int distance = Math.abs(row - currentRow) + Math.abs(col - currentCol);
                    if(distance < closestDistance) {
                        closestDistance = distance;
                        loc.nextStop = currentLoc;
                    }
                }
            }
            loc.setDistanceToNextStop(closestDistance);
            loc = loc.nextStop;
            visitedStops.add(loc);
        }
        Location runThrow = headOfRoute;
        while(runThrow != null){
            System.out.println(runThrow.getLocationID());
            runThrow = runThrow.nextStop;
        }

    }

    public void updateRoute(){
         for(int i = 0; i < unvisitedLocations.size(); i++){
             Location loc = headOfRoute;
             Location closest = headOfRoute;
             Location currentLoc = unvisitedLocations.get(i);
             if(!visitedStops.contains(currentLoc)){
                 visitedStops.add(currentLoc);
                 int currentRow = Board.getRowIndex(currentLoc);
                 int currentCol = Board.getColumnIndex(currentLoc);
                 int dist = Integer.MAX_VALUE;
                 System.out.println("i is: " + i + " and location id: " + currentLoc.getLocationID());
                 while(loc != null){
                     System.out.println("loc is: " + loc.getLocationID());
                     int row = Board.getRowIndex(loc);
                     int col = Board.getColumnIndex(loc);
                     int closestDistance = loc.getDistanceToNextStop();
                     int distance = Math.abs(row - currentRow) + Math.abs(col - currentCol);
                     if(distance < closestDistance && distance < dist) {
                         dist = distance;
                         closest = loc;
                     }
                     loc = loc.nextStop;
                 }
                 closest.setDistanceToNextStop(dist);
                 currentLoc.nextStop = closest.nextStop;
                 if(currentLoc.nextStop != null){
                     Location nextNext = currentLoc.nextStop;
                     int row = Board.getRowIndex(nextNext);
                     int col = Board.getColumnIndex(nextNext);
                     int distance = Math.abs(row - currentRow) + Math.abs(col - currentCol);
                     currentLoc.setDistanceToNextStop(distance);
                 }
                 closest.nextStop = currentLoc;
             }
         }
         Location runThrough = headOfRoute;
         while(runThrough != null){
             System.out.println(runThrough.getLocationID());
             System.out.println("loc = " + runThrough.getLocationID() + " and dist to next stop is: " + runThrough.getDistanceToNextStop());
             runThrough = runThrough.nextStop;
         }
    }

 */
}
