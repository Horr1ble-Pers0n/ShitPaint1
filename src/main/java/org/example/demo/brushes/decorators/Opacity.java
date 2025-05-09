package org.example.demo.brushes.decorators;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.demo.brushes.Brush;
import org.example.demo.colours.Colour;

public class Opacity extends BrushDecorator {
    private double opacity;

    public Opacity(Brush decoratedBrush, double opacity) {
        super(decoratedBrush);
        this.opacity = opacity;
    }

    public Opacity(Brush decoratedBrush) {
        super(decoratedBrush);
    }

    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        Color color = Colour.getInstance().getCurrentColor();
        Color newColor = color.deriveColor(0, 1, 1, opacity);
        Colour.getInstance().setCurrentColor(newColor);

        decoratedBrush.draw(gc, x, y);

        Colour.getInstance().setCurrentColor(color);
    }
}
