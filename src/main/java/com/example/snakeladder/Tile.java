package com.example.snakeladder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    public Tile(int width, int height){              // make constructor - Tile()
        setWidth(width);
        setHeight(height);
        setFill(Color.RED);                          // set color for Tile
        setStroke(Color.BLACK);                      // set color for Boundaries

    }
}
