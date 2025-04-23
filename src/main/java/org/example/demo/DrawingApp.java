package org.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.example.demo.canvas.DrawingCanvas;
import org.example.demo.brushes.*;
import org.example.demo.colours.Colour;
import org.example.demo.command.*;

import javafx.scene.paint.Color;

import java.util.Stack;

public class DrawingApp extends Application {
    private final Stack<DrawCommand> commandHistory = new Stack<>();
    private DrawingCanvas drawingCanvas;
    private Brush currentBrush = new CircleBrush();

    @Override
    public void start(Stage stage) {
        drawingCanvas = new DrawingCanvas(600, 400);

        Canvas canvas = drawingCanvas.getCanvas();
        canvas.setOnMouseDragged(e -> {
            DrawCommand command = new BrushCommand(drawingCanvas, currentBrush, e.getX(), e.getY());
            command.execute();
            commandHistory.push(command);
        });

        Button circleBrushBtn = new Button("Circle Brush");
        circleBrushBtn.setOnAction(e -> currentBrush = new CircleBrush());

        Button squareBrushBtn = new Button("Square Brush");
        squareBrushBtn.setOnAction(e -> currentBrush = new SquareBrush());

        Button undoBtn = new Button("Undo");
        undoBtn.setOnAction(e -> {
            if (!commandHistory.isEmpty()) {
                commandHistory.pop().undo();
                drawingCanvas.redrawAll(commandHistory);
            }
        });


        ColorPicker colorPicker = new ColorPicker(Color.BLACK);
        colorPicker.setOnAction(e -> {
                Color selectedColor = colorPicker.getValue();
                Colour.getInstance().setCurrentColor(selectedColor);
                });

        VBox root = new VBox(10, canvas, circleBrushBtn, squareBrushBtn, undoBtn, colorPicker);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Paint App");
        stage.show();
    }
}

