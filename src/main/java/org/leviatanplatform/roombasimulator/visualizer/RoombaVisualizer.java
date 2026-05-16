package org.leviatanplatform.roombasimulator.visualizer;

import org.leviatanplatform.roombasimulator.engine.Roomba;
import org.leviatanplatform.roombasimulator.engine.domain.Cell;

import javax.swing.*;
import java.awt.*;

public class RoombaVisualizer {

    private final PixelCanvas pixelCanvas;
    private Roomba roomba;
    private final int w;
    private final int h;
    private JFrame frame;

    public RoombaVisualizer(Roomba roomba, int w, int h) {

        this.roomba = roomba;
        this.w = w;
        this.h = h;

        this.pixelCanvas = new PixelCanvas(w, h);
    }

    public void show() {

        if (frame == null) {

            frame = new JFrame("Fractal");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(w + 30, h + 50);
            frame.setVisible(true);
            frame.setResizable(false);
            frame.addKeyListener(new CommandListener(this));

            frame.add(pixelCanvas);
        }

        paintCanvas();
    }

    private void refreshAll() {
        paintCanvas();
    }


    public void paintCanvas() {
        SwingUtilities.invokeLater(() -> {
            innerPaintCanvas();
            pixelCanvas.invalidate();
            pixelCanvas.validate();
            pixelCanvas.repaint();
        });
    }

    public void innerPaintCanvas() {

        frame.setTitle("Roomba simulator");

        // FIXME finish

        for (int r = 0; r < w; r++) {
            for (int i = 0; i < h; i++) {
                int escapedIteration = complexPlane.getValue(r, i);
                Color color = getColor(escapedIteration);
                pixelCanvas.setPixel(r, h - 1 - i, color);
            }
        }

        pixelCanvas.repaint();
    }

    private Color getColor(Cell cell) {

        boolean stepped = cell.isStepped();
        boolean actuated = cell.isActuated();
        boolean wall = cell.isWall();

        if (wall) {
            return Color.BLACK;
        }

        if (stepped) {
            return Color.GREEN;
        }

        if (actuated) {
            return Color.CYAN;
        }

        return Color.WHITE;
    }

}
