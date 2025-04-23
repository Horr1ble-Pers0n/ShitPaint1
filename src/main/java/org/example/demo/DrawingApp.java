package org.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.example.demo.brushes.decorators.Opacity;
import org.example.demo.brushes.decorators.Size;
import org.example.demo.canvas.DrawingCanvas;
import org.example.demo.brushes.*;
import org.example.demo.colours.Colour;
import org.example.demo.command.*;

import javafx.scene.paint.Color;
import org.example.demo.figures.Circle;
import org.example.demo.figures.Figure;
import org.example.demo.figures.Rectangle;

import java.util.Stack;

public class DrawingApp extends Application {
    private final Stack<DrawCommand> commandHistory = new Stack<>();
    private DrawingCanvas drawingCanvas;
    private Brush currentBrush = new CircleBrush();
    private Brush baseBrush = new CircleBrush();
    private Figure currentFigure;
    private double startX, startY;

    @Override
    public void start(Stage stage) {
        drawingCanvas = new DrawingCanvas(600, 400);

        ComboBox<String> brushComboBox = new ComboBox<>();
        brushComboBox.getItems().addAll("◯ Circle Brush", "☐ Square Brush");
        brushComboBox.setValue("◯ Circle Brush");


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

        canvas.setOnMousePressed(e -> {
            if (currentFigure != null) {
                currentBrush = new Opacity(new Size(baseBrush, 0), 0);
            }

            startX = e.getX();
            startY = e.getY();
        });
        canvas.setOnMouseReleased(e -> {
            if(currentFigure != null) {
                currentFigure.draw(drawingCanvas.getCanvas().getGraphicsContext2D(), startX, startY, e.getX(), e.getY());
            }
        });

        brushComboBox.setOnAction(e -> {
            String selected = brushComboBox.getValue();
            baseBrush = switch (selected) {
                case "☐ Square Brush" -> new SquareBrush();
                default -> new CircleBrush();
            };
            currentBrush = new Opacity(new Size(baseBrush, sizeSlider.getValue()), opacitySlider.getValue());
        });

        Button rectangleBtn = new Button("Rectangle");
        rectangleBtn.setOnAction(e -> {
            currentFigure = new Rectangle();
        });

        Button circleBtn = new Button("Circle");
        circleBtn.setOnAction(e -> {
            currentFigure = new Circle();
        });

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
            currentBrush = new Opacity(new Size(baseBrush, newVal.doubleValue()), opacitySlider.getValue());
        });

        opacitySlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            currentBrush = new Opacity(new Size(baseBrush, sizeSlider.getValue()), newVal.doubleValue());
        });

        VBox root = new VBox(10,
                canvas,
                brushComboBox,
                rectangleBtn,
                circleBtn,
                undoBtn,
                colorPicker,
                sizeLabel, sizeSlider,
                opacityLabel, opacitySlider
        );
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Paint App");
        stage.show();
    }
}

