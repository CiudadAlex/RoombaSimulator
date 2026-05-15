package org.leviatanplatform.roombasimulator.engine.domain;

public class ScalableChart {

    private Cell[][] chart;

    public ScalableChart(int rows, int columns) {
        this.chart = new Cell[rows][columns];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                this.chart[r][c] = new Cell();
            }
        }
    }

    // FIXME finish
}
