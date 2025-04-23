package org.example.demo.command;

import org.example.demo.brushes.Brush;
import org.example.demo.canvas.DrawingCanvas;

public class BrushCommand implements DrawCommand {
    private final DrawingCanvas canvas;
    private final Brush brush;
    private final double x, y;

    public BrushCommand(DrawingCanvas canvas, Brush brush, double x, double y) {
        this.canvas = canvas;
        this.brush = brush;
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute() {
        brush.draw(canvas.getGraphicsContext(), x, y);
    }

    @Override
    public void undo() {

    }
}