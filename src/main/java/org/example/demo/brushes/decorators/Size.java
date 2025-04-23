package org.example.demo.brushes.decorators;

import javafx.scene.canvas.GraphicsContext;
import org.example.demo.brushes.Brush;
import org.example.demo.brushes.CircleBrush;
import org.example.demo.brushes.SquareBrush;
import org.example.demo.colours.Colour;

public class Size extends BrushDecorator {
    private double size;

    public Size(Brush decoratedBrush, double size) {
        super(decoratedBrush);
        this.size = size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        gc.setFill(Colour.getInstance().getCurrentColor());

        if(decoratedBrush instanceof CircleBrush){
            gc.fillOval(x - size/2, y - size/2, size, size);
        } else if(decoratedBrush instanceof SquareBrush){
            gc.fillRect(x - size/2, y - size/2, size, size);
        } else{
            decoratedBrush.draw(gc, x, y);
        }
    }
}
