
package org.example.demo.brushes;

import javafx.scene.canvas.GraphicsContext;

public interface Brush {
    void draw(GraphicsContext gc, double x, double y);
}