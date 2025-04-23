package org.example.demo.figures;

import javafx.scene.canvas.GraphicsContext;

public interface Figure {
    void draw(GraphicsContext gc, double startX, double startY, double endX, double endY);
}

