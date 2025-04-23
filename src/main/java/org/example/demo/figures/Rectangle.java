package org.example.demo.figures;

import javafx.scene.canvas.GraphicsContext;
import org.example.demo.colours.Colour;

public class Rectangle implements Figure {
    @Override
    public void draw(GraphicsContext gc, double startX, double startY, double endX, double endY) {
        gc.setFill(Colour.getInstance().getCurrentColor());
        gc.fillRect(
                Math.min(startX, endX),
                Math.min(startY, endY),
                Math.abs(endX - startX),
                Math.abs(endY - startY)
        );
    }
}
