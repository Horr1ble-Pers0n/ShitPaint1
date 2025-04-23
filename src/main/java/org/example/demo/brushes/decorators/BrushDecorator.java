package org.example.demo.brushes.decorators;

import javafx.scene.canvas.GraphicsContext;
import org.example.demo.brushes.Brush;

abstract class BrushDecorator implements Brush {
    protected Brush decoratedBrush;

    public BrushDecorator(Brush decoratedBrush) {
        this.decoratedBrush = decoratedBrush;
    }

    public void draw(GraphicsContext gc, double x, double y) {
        decoratedBrush.draw(gc, x, y);
    }
}
