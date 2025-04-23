package org.example.demo.colours;

import javafx.scene.paint.Color;

public class Colour {
    private static Colour instance;
    private Color currentColor ;

    private Colour(){}

    public static synchronized Colour getInstance() {
        if(instance == null) {
            instance = new Colour();
        }
        return instance;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }
}
