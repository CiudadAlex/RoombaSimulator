package org.leviatanplatform.roombasimulator.visualizer;

import org.leviatanplatform.roombasimulator.engine.Roomba;
import org.leviatanplatform.roombasimulator.engine.domain.CellInfo;
import org.leviatanplatform.roombasimulator.engine.domain.Movement;
import org.leviatanplatform.roombasimulator.engine.domain.PositionInfo;

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

            frame = new JFrame("Roomba Simulator");
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

    public void moveRoomba(Movement movement) {
        roomba.move(movement);
        changePixelScaleIfNeeded();
        refreshAll();
    }

    private void changePixelScaleIfNeeded() {
        int rows = roomba.getChartRows();
        int columns = roomba.getChartColumns();
        int pixelScale = pixelCanvas.getPixelScale();

        if (rows * pixelScale > h || columns * pixelScale > w) {
            pixelCanvas.addToPixelScale(-1);
        }
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
                Color color = getColor(r, c);
                pixelCanvas.setRectangle(c, r, color);
            }
        }

        pixelCanvas.repaint();
    }

    private Color getColor(int r, int c) {
        CellInfo cellInfo = roomba.getChartCellInfo(r, c);
        PositionInfo positionInfo = roomba.getChartPositionInfo();

        int row = positionInfo.getRow();
        int column = positionInfo.getColumn();

        if (r == row && c == column) {
            return Color.RED;
        }

        return getColor(cellInfo);
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
