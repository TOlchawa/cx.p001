package pl.cx.p001.gui;

/**
 * PixelModel provides basic parameters and color mapping for pixel-based rendering.
 * Used for GUI visualizations and gradient examples.
 */
public class PixelModel {
    public final int width = 300;
    public final int height = 300;

    public javafx.scene.paint.Color getColor(int x, int y) {
        return javafx.scene.paint.Color.color((double)x/width, (double)y/height, 0.5);
    }
}

