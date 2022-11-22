package com.example.snakeladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {

    public final int tileSize = 40;         // make final, so not everyone can change size
    int height = 10;
    int width = 10;

    int yLine = 430;                        // provide distance to place buttons
                                            // topMost 100 99 98... is at (0) in Y-Axis and place buttons at distance of 430 below/downWard
    Group tileGroup = new Group();          // group is empty for now

    Player playerOne, playerTwo;

    int diceValue;                                                       // Global Variable

    Label randResult;                                                    // Global Variable

    boolean gameStart = true,turnPlayerOne = true, turnPlayerTwo = false;


    public Pane createContent(){                   // Layout Container --- Google it --- Pane in JavaFX
        Pane root = new Pane();
        root.setPrefSize(width*tileSize, height*tileSize+80);   // Providing size to our UI/Pane, so it can Hold 100/hundred Tiles
        root.getChildren().addAll(tileGroup);      // adding Tiles (100) in Pane --- No of Tiles = (10*10) === Grid type Formation
        //// tileGroup is child of our Pane(root)
        //// Now inside Pane, this group will be added



        for (int i = 0; i < height ; i++) {                // (fori) --- shortcut for i loop
            for (int j = 0; j < width; j++) {              // formation of Grid 10*10 to hold all 100 Tiles
                Tile tile = new Tile(tileSize, tileSize);  // Crete object of Tile class,,, width and height === 40 (tileSize)
                tile.setTranslateX(j*tileSize);            // method for X-axis --- (0,0) -> (400,0)
                tile.setTranslateY(i*tileSize);            // method for Y-axis --- (0,0) -> (0,400) -As loop count (upto 10 { i as Height} ) increase, Y axis increase
                tileGroup.getChildren().addAll(tile);
                //// tile (100) are child of our tileGroup
            }
        }

        // add label
        randResult = new Label("Start the Game");
        randResult.setTranslateX(162);
        randResult.setTranslateY(yLine-23);


        // add 3 buttons
        // 1 - playerOne
        Button playerOneButton = new Button("Player One");
        playerOneButton.setTranslateX(20);                            // at distance of 20 in X-direction/axis
        playerOneButton.setTranslateY(yLine);                         // at bottom/ distance of 430 from Topmost ---(yLine=430)
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {    // providing action, when we click/hit/press this button
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart == true){
                    if(turnPlayerOne == true){
                        getDiceValue();
                        playerOne.movePlayer(diceValue);
                        playerOne.playerAtSnakeOrLadder();
                        turnPlayerOne = false;
                        turnPlayerTwo = true;
                    }
                }

            }
        });

        // 2 - playerTwo
        Button playerTwoButton = new Button("Player Two");
        playerTwoButton.setTranslateX(310);
        playerTwoButton.setTranslateY(yLine);
        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {    // providing action, when we click/hit/press this button
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart == true){
                    if(turnPlayerTwo == true){
                        getDiceValue();
                        playerTwo.movePlayer(diceValue);
                        playerTwo.playerAtSnakeOrLadder();
                        turnPlayerTwo = false;
                        turnPlayerOne = true;
                    }
                }

            }
        });

        // 3 - startGame
        Button gameButton = new Button("Start Game");
        gameButton.setTranslateX(165);
        gameButton.setTranslateY(yLine);

        // create player
        playerOne = new Player(tileSize, Color.BLACK);
        playerTwo = new Player(tileSize-10, Color.WHITE);


        // Procedure to paste image over our interface/grid///pane-Layout Container
        Image img = new Image("C:\\Users\\admin\\IdeaProjects\\SnakeLadder\\src\\SnakeLadderBoard123.jpg");
        ImageView boardImage = new ImageView();
        boardImage.setImage(img);
        boardImage.setFitWidth(tileSize*width);
        boardImage.setFitHeight(tileSize*height);

        tileGroup.getChildren().addAll(boardImage, playerOneButton, playerTwoButton, gameButton,
                randResult, playerOne.getGamePiece(), playerTwo.getGamePiece());
        //// boardImage is child of our tileGroup -we can say
        // with boardImage, we also have to include these certain items like- buttons, result etc... in bracket otherwise these all will not appear on our interface


        return root;
    }


    private void getDiceValue(){
        diceValue = (int)(Math.random()*6+1);                       // function that provides random value between 0 & 1
        randResult.setText(Integer.toString(diceValue));
    }

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake and Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}