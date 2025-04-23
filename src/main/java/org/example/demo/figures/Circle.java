package org.example.demo.figures;

import javafx.scene.canvas.GraphicsContext;
import org.example.demo.colours.Colour;

public class Circle implements Figure {
    @Override
    public void draw(GraphicsContext gc, double startX, double startY, double endX, double endY) {
        gc.setFill(Colour.getInstance().getCurrentColor());

        double radiusX = Math.abs(endX - startX) / 2;
        double radiusY = Math.abs(endY - startY) / 2;
        double centerX = Math.min(startX, endX) + radiusX;
        double centerY = Math.min(startY, endY) + radiusY;

        gc.fillOval(centerX - radiusX, centerY - radiusY, radiusX * 2, radiusY * 2);
    }
}
