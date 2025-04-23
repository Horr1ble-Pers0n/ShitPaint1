package org.example.demo.brushes;

import javafx.scene.canvas.GraphicsContext;
import org.example.demo.colours.Colour;

public class CircleBrush implements Brush {
    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        double size = 15;
        gc.setFill(Colour.getInstance().getCurrentColor());
        gc.fillOval(x - size/2, y - size/2, size, size);
    }
}
