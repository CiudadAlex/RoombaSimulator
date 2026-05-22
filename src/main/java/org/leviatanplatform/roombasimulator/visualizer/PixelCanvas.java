package org.leviatanplatform.roombasimulator.visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PixelCanvas extends JPanel {

    private BufferedImage canvas;
    private int pixelScale;
    private int w;
    private int h;

    public PixelCanvas(int w, int h, int pixelScale) {
        this.w = w;
        this.h = h;
        this.pixelScale = pixelScale;
        this.canvas = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    }

    public void setRectangle(int x, int y, Color color) {

        Graphics2D g = this.canvas.createGraphics();

        g.setColor(color);
        g.fillRect(x * pixelScale, y * pixelScale, pixelScale, pixelScale);
    }

    public void reset() {

        Graphics2D g = this.canvas.createGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, w, h);
    }

    public void addToPixelScale(int pixelsToAdd) {
        this.pixelScale = this.pixelScale + pixelsToAdd;

        if (this.pixelScale < 1) {
            this.pixelScale = 1;
        }
    }

    public int getPixelScale() {
        return pixelScale;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(canvas, 0, 0, null);
    }
}
