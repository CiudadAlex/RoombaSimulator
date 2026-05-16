package org.leviatanplatform.roombasimulator.visualizer;

import org.leviatanplatform.roombasimulator.engine.Roomba;
import org.leviatanplatform.roombasimulator.engine.domain.Cell;
import org.leviatanplatform.roombasimulator.engine.domain.CellInfo;

import javax.swing.*;
import java.awt.*;

public class RoombaVisualizer {

    private final PixelCanvas pixelCanvas;
    private Roomba roomba;
    private final int w;
    private final int h;
    private JFrame frame;

    public RoombaVisualizer(Roomba roomba, int w, int h, int pixelScale) {

        this.roomba = roomba;
        this.w = w;
        this.h = h;

        this.pixelCanvas = new PixelCanvas(w, h, pixelScale);
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

    public void zoom(int pixelsToAdd) {
        pixelCanvas.addToPixelScale(pixelsToAdd);
        refreshAll();
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

        int rows = roomba.getChartRows();
        int columns = roomba.getChartColumns();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                CellInfo cellInfo = roomba.getChartCellInfo(r, c);
                Color color = getColor(cellInfo);
                pixelCanvas.setRectangle(r, columns - 1 - c, color);
            }
        }

        pixelCanvas.repaint();
    }

    private Color getColor(CellInfo cellInfo) {

        boolean stepped = cellInfo.isStepped();
        boolean actuated = cellInfo.isActuated();
        boolean wall = cellInfo.isWall();

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
