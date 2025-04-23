package org.example.demo.brushes;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SquareBrush implements Brush {
    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        gc.setFill(Color.GREEN);
        gc.fillRect(x - 5, y - 5, 10, 10);
    }
}