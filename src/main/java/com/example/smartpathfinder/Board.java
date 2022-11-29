package com.example.smartpathfinder;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;

public class Board extends GridPane {

    /**
     * This class represents the map and its implementation follows the singleton pattern
     */
    private static Board graph = new Board();
    private static final int CELL_WIDTH = 15;
    private static final int CELL_HEIGHT = 15;
    private static final int GAP = 1;
    private static int count = 0;


    //empty constructor
    private Board(){

    }

    public static Board initializeGraph(){
        graph.setGridLinesVisible(true);
        graph.setHgap(GAP);
        graph.setVgap(GAP);

        for(int i = 0; i < (int) (WelcomeScreen.mainStage.getHeight()/15); i++){
            for(int j = 0; j < (int) (WelcomeScreen.mainStage.getWidth()/15); j++)
            {
                Rectangle rect = new Rectangle(CELL_WIDTH, CELL_HEIGHT);
                rect.setVisible(true);
                rect.setFill(Color.BEIGE);
                graph.add(rect, j, i);
            }
        }
        return graph;
    }

    //***************  getters for all static variables  ***************/

    public static int getCellWidth(){
        return CELL_WIDTH;
    }

    public static int getCellHeight(){
        return CELL_HEIGHT;
    }

    public static int getGap(){
        return GAP;
    }
}
