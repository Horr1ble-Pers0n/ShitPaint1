package org.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.example.demo.brushes.decorators.Opacity;
import org.example.demo.brushes.decorators.Size;
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
    private double currentBrushSize;

    @Override
    public void start(Stage stage) {
        drawingCanvas = new DrawingCanvas(600, 400);

        Slider sizeSlider = new Slider(1, 30, 15);
        Label sizeLabel = new Label("Size:");
        sizeSlider.setShowTickLabels(true);
        sizeSlider.setMajorTickUnit(5);
        sizeSlider.setMinorTickCount(4);

        Slider opacitySlider = new Slider(0, 1, 1);
        Label opacityLabel = new Label("Opacity:");
        opacitySlider.setShowTickLabels(true);
        opacitySlider.setMajorTickUnit(0.1);
        opacitySlider.setMinorTickCount(10);

        Canvas canvas = drawingCanvas.getCanvas();
        canvas.setOnMouseDragged(e -> {
            DrawCommand command = new BrushCommand(drawingCanvas, currentBrush, e.getX(), e.getY());
            command.execute();
            commandHistory.push(command);
        });

        Button circleBrushBtn = new Button("Circle Brush");
        circleBrushBtn.setOnAction(e -> currentBrush = new Size(new CircleBrush(), currentBrushSize));

        Button squareBrushBtn = new Button("Square Brush");
        squareBrushBtn.setOnAction(e -> currentBrush = new Size(new SquareBrush(), currentBrushSize));

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

        sizeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            currentBrushSize = newVal.doubleValue();
            if (currentBrush instanceof Size s) {
                s.setSize(currentBrushSize);
            } else {
                currentBrush = new Size(currentBrush, currentBrushSize);
            }
        });

        opacitySlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            double opacityValue = newVal.doubleValue();
            if (currentBrush instanceof Opacity opacityBrush) {
                opacityBrush.setOpacity(opacityValue);
            } else {
                currentBrush = new Opacity(currentBrush, opacityValue);
            }
        });

        VBox root = new VBox(10,
                canvas,
                circleBrushBtn,
                squareBrushBtn,
                undoBtn,
                colorPicker,

                sizeLabel,
                sizeSlider,

                opacityLabel,
                opacitySlider
        );
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Paint App");
        stage.show();
    }
}

