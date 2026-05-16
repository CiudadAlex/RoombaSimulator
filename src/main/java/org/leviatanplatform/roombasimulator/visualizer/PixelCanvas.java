package org.leviatanplatform.roombasimulator.visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PixelCanvas extends JPanel {

    private BufferedImage canvas;

    public PixelCanvas(int w, int h) {
        canvas = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    }

    public void setPixel(int x, int y, Color color) {
        canvas.setRGB(x, y, color.getRGB());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(canvas, 0, 0, null);
    }
}
