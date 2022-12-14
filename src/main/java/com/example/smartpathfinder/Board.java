package com.example.smartpathfinder;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

public class Board extends GridPane {

    /**
     * This class represents the map and its implementation follows the singleton pattern
     */
    private int cellWidth;
    private int cellHeight;
    private final int width = 50;
    private final int height = 30;

    public Board(int cellWidth, int cellHeight, int gap){
        this.setVgap(gap);
        this.setHgap(gap);
        this.setGridLinesVisible(true);
        this.cellHeight = cellHeight;
        this.cellWidth = cellWidth;
    }

    public void initializeGraph(){

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++)
            {
                Rectangle rect = new Rectangle(cellWidth, cellHeight);
                rect.setVisible(true);
                rect.setFill(Color.BEIGE);
                add(rect, i, j);
            }
        }
    }

    public ArrayList<Location> findPathNodes(Location startNode, ArrayList<Location> allStops){
        ArrayList<Location> pathNodes = new ArrayList<>();
        Location firstNode = startNode;
        while(firstNode.nextStop != null){
            int originRow = firstNode.getRow(), targetRow = firstNode.nextStop.getRow();
            int originCol = firstNode.getColumn(), targetCol = firstNode.nextStop.getColumn();
            //
            while(originRow != targetRow){
                if(originRow > targetRow){
                    Location node = new Location(originRow--, originCol, cellWidth, cellHeight, 2);
                    if(!isLocation(node.getRow(), node.getColumn(), allStops))
                        pathNodes.add(node);
                }

                else{
                    Location node = new Location(originRow++, originCol, cellWidth, cellHeight, 2);
                    if(!isLocation(node.getRow(), node.getColumn(), allStops))
                        pathNodes.add(node);
                }

            }
            while(originCol != targetCol){
                if(originCol > targetCol){
                    Location node = new Location(originRow, originCol--, cellWidth, cellHeight, 2);
                    if(!isLocation(node.getRow(), node.getColumn(), allStops))
                        pathNodes.add(node);
                }

                else
                {
                    Location node = new Location(originRow, originCol++, cellWidth, cellHeight, 2);
                    if(!isLocation(node.getRow(), node.getColumn(), allStops))
                        pathNodes.add(node);
                }
            }
            firstNode = firstNode.nextStop;
        }
        return pathNodes;
    }

    public void clearPath(ArrayList<Location> allStops){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++)
            {
                if(!isLocation(i, j, allStops)){
                    Rectangle rect = new Rectangle(cellWidth, cellHeight);
                    rect.setVisible(true);
                    rect.setFill(Color.BEIGE);
                    add(rect, i, j);
                }
            }
        }
    }

    public void paint(ArrayList<Location> pathNodes){
        for(int i = 0; i < pathNodes.size(); i++)
            add(pathNodes.get(i), pathNodes.get(i).getRow(), pathNodes.get(i).getColumn());
    }
    
    private boolean isLocation(int row, int col, ArrayList<Location> allStops){
        for(int i = 0; i < allStops.size(); i++){
            if(allStops.get(i).getRow() == row && allStops.get(i).getColumn() == col)
                return true;
        }
        return false;
    }

}
