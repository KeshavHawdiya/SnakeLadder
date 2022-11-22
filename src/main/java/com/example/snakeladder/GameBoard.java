package com.example.snakeladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class GameBoard {

    static int tileSize = 40;
    static int height = 10;
    static int width = 10;
    static ArrayList<Pair<Integer, Integer>>positionCoordinates;                    // to store X & Y coordinates (x,y) of all blocks
    static ArrayList<Integer>snakeLadderPosition;                                   // to go up and down, on places - where snake bite & ladder occurs

    public GameBoard(){                                                    // constructor
        populatePositionCoordinates();                                     // calling method in constructor
        setPositionCoordinates();                                          // calling method in constructor
    }

    public int getXValue(int piecePosition){
        return positionCoordinates.get(piecePosition).getKey();
    }
    public int getYValue(int piecePosition){
        return positionCoordinates.get(piecePosition).getValue();
    }

    public int playerPositionAtSnakeOrLadder(int piecePosition){
        if(piecePosition != snakeLadderPosition.get(piecePosition)){
            return snakeLadderPosition.get(piecePosition);
        }
        return -1;
    }

    private static void populatePositionCoordinates(){
        positionCoordinates = new ArrayList<Pair<Integer, Integer>>();
        positionCoordinates.add(new Pair<Integer, Integer>(20,380));

        // 20, 380 --- range - because we encounter center co-ordinates of every tile in form of Pair
        // so let's create loops so we can assign values/co-ordinates to every (100) tiles

        int xTilePos, yTilePos;                     // width relate to X and height relate to yTilePos
        for (int i = height-1; i >= 0 ; i--) {
            for (int j = width-1; j >= 0 ; j--) {
                if( i%2 != 0){
                    xTilePos = tileSize*width - (tileSize/2 + j*tileSize);
                               //    400      - (    20     +    360    )
                               // => 20 (as we consider above in Range)
                }
                else{
                    xTilePos = tileSize/2 + j*tileSize;
                               //  20     +   360
                               // => 380 (as we consider above in Range)
                }

                yTilePos = tileSize/2 + i*tileSize;
                positionCoordinates.add(new Pair<Integer, Integer>(xTilePos, yTilePos));

            }

        }

        for (int i = 0; i < positionCoordinates.size(); i++) {
            System.out.println(i + " x: " + positionCoordinates.get(i).getKey()
                                + " y: " + positionCoordinates.get(i).getValue());

        }
    }

    // to move up and down... new method
    private void setPositionCoordinates(){
        snakeLadderPosition = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            snakeLadderPosition.add(i);
        }
        snakeLadderPosition.set(4,25);
        snakeLadderPosition.set(13,46);
        snakeLadderPosition.set(27,5);
        snakeLadderPosition.set(33,49);
        snakeLadderPosition.set(40,3);
        snakeLadderPosition.set(42,63);
        snakeLadderPosition.set(43,18);
        snakeLadderPosition.set(50,69);
        snakeLadderPosition.set(54,31);
        snakeLadderPosition.set(62,81);
        snakeLadderPosition.set(66,45);
        snakeLadderPosition.set(74,92);
        snakeLadderPosition.set(76,58);
        snakeLadderPosition.set(89,53);
        snakeLadderPosition.set(99,41);
    }

    public static void main(String[] args) {
    //    populatePositionCoordinates();
        for (int i = 0; i < 100; i++) {
            System.out.println(Math.random() + " # " + (int)(Math.random()*6+1));
        }
    }
}
