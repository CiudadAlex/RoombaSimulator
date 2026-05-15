package org.leviatanplatform.roombasimulator.engine.domain;

public class ScalableChart {

    private final int rows;
    private final int columns;
    private final Cell[][] chart;
    private Position position;

    public ScalableChart() {
        this(10, 10);
    }

    public ScalableChart(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.chart = new Cell[rows][columns];
        this.position = new Position(rows/2, columns/2);

        fillNullCells();
    }

    private void fillNullCells() {

        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.columns; c++) {

                if (this.chart[r][c] == null) {
                    this.chart[r][c] = new Cell();
                }
            }
        }
    }

    // FIXME finish
}
