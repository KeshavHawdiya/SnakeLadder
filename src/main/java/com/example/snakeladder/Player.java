package com.example.snakeladder;

import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class Player {

    private Circle gamePiece;
    int xPosition;
    int yPosition;
    int currentPiecePosition;

    static GameBoard gameBoard = new GameBoard();

    Player (int tileSize, Color pieceColor){                              // constructor to initialize all parameters needed for Player
        this.currentPiecePosition = 1;                                    // initially assigned position
        this.xPosition = gameBoard.getXValue(currentPiecePosition);
        this.yPosition = gameBoard.getYValue(currentPiecePosition);
        // for these both values of X & Y, we have created methods in GameBoard section earlier -- public int getXValue(int piecePosition)

        gamePiece = new Circle(tileSize/2);
        gamePiece.setFill(pieceColor);
        gamePiece.setTranslateX(this.xPosition);
        gamePiece.setTranslateY(this.yPosition);

    }

    public void movePlayer(int diceValue){
        if(currentPiecePosition + diceValue <= 100){
            currentPiecePosition += diceValue;
            translatePlayer();
        }
    }

    private void translatePlayer(){
        this.xPosition = gameBoard.getXValue(this.currentPiecePosition);
        this.yPosition = gameBoard.getYValue(this.currentPiecePosition);

        // some extra animation adding - to maximize speed of moving of dice... now it will take few millisecond... not move instantly
        TranslateTransition animate = new TranslateTransition(Duration.millis(2600), this.gamePiece);
        animate.setToX(this.xPosition);
        animate.setToY(this.yPosition);
        animate.setAutoReverse(false);
        animate.play();


 //       gamePiece.setTranslateX(this.xPosition);
 //       gamePiece.setTranslateY(this.yPosition);
    }

    public void playerAtSnakeOrLadder(){
        int newPosition = gameBoard.playerPositionAtSnakeOrLadder(this.currentPiecePosition);
        if(newPosition != -1){
            this.currentPiecePosition = newPosition;
            translatePlayer();
        }
    }



    // when we create a player, we need this GAMEPIECE, so we can put this on our Board, so we create method below
    public Circle getGamePiece(){
        return this.gamePiece;
    }

}
