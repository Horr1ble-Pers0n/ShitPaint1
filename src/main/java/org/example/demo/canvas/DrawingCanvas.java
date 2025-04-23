package org.example.demo.canvas;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import org.example.demo.command.DrawCommand;

import java.util.Stack;

public class DrawingCanvas {
    private final Canvas canvas;
    private final GraphicsContext gc;

    public DrawingCanvas(double width, double height) {
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GraphicsContext getGraphicsContext() {
        return gc;
    }

    public void redrawAll(Stack<DrawCommand> commands) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (DrawCommand cmd : commands) {
            cmd.execute();
        }
    }
}
