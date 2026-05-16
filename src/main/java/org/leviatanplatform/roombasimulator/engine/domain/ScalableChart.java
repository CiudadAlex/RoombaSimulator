package org.leviatanplatform.roombasimulator.engine.domain;

public class ScalableChart {

    private final int rows;
    private final int columns;
    private final int margin;
    private final Cell[][] chart;
    private Position position;

    public ScalableChart() {
        this(20, 20, 5);
    }

    public ScalableChart(int rows, int columns, int margin) {
        this.rows = rows;
        this.columns = columns;
        this.margin = margin;
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

    private void copyCells(Cell[][] externalChart, int initRow, int endRowExcluded, int initColumn, int endColumnExcluded, int deltaRow, int deltaColumn) {

        for (int r = initRow; r < endRowExcluded; r++) {
            for (int c = initColumn; c < endColumnExcluded; c++) {
                this.chart[r+deltaRow][c+deltaColumn] = externalChart[r][c];
            }
        }
    }

    public void stepCurrentPosition() {
        Cell currentCell = chart[position.getRow()][position.getColumn()];
        currentCell.setStepped(true);
    }

    public void actuateCurrentPosition(int radius) {

        int row = position.getRow();
        int column = position.getColumn();

        for (int r = row - radius; r <= row + radius; r++) {
            for (int c = column - radius; c <= column + radius; c++) {

                if (r >= 0 && r < rows && c >= 0 && c < columns) {
                    Cell cell = chart[r][c];
                    cell.setActuated(true);
                }
            }
        }
    }

    public void move(Movement movement) {
        position.move(movement);
    }

    public void setWall(Movement movement) {

        Position positionOfWall = this.position.clonePosition();
        positionOfWall.move(movement);

        Cell wallCell = chart[positionOfWall.getRow()][positionOfWall.getColumn()];
        wallCell.setWall(true);
    }

    private void setPosition(int row, int column) {
        this.position = new Position(row, column);
    }

    public ScalableChart scaleIfNeeded() {

        int row = this.position.getRow();
        int column = this.position.getColumn();

        if (row < margin) {
            return scaleUp(margin);
        }

        if (row >= rows - margin) {
            return scaleDown(margin);
        }

        if (column < margin) {
            return scaleLeft(margin);
        }

        if (column >= columns - margin) {
            return scaleRight(margin);
        }

        // No escalation done
        return this;
    }

    private ScalableChart scaleUp(int rowsToAdd) {

        ScalableChart newScalableChart = new ScalableChart(rows + rowsToAdd, columns, margin);
        newScalableChart.copyCells(this.chart, 0, rows, 0, columns, rowsToAdd, 0);
        newScalableChart.setPosition(this.position.getRow() + rowsToAdd, this.position.getColumn());
        return newScalableChart;
    }

    private ScalableChart scaleDown(int rowsToAdd) {

        ScalableChart newScalableChart = new ScalableChart(rows + rowsToAdd, columns, margin);
        newScalableChart.copyCells(this.chart, 0, rows, 0, columns, 0, 0);
        newScalableChart.setPosition(this.position.getRow(), this.position.getColumn());
        return newScalableChart;
    }

    private ScalableChart scaleLeft(int columnsToAdd) {

        ScalableChart newScalableChart = new ScalableChart(rows, columns + columnsToAdd, margin);
        newScalableChart.copyCells(this.chart, 0, rows, 0, columns, 0, columnsToAdd);
        newScalableChart.setPosition(this.position.getRow(), this.position.getColumn() + columnsToAdd);
        return newScalableChart;
    }

    private ScalableChart scaleRight(int columnsToAdd) {

        ScalableChart newScalableChart = new ScalableChart(rows, columns + columnsToAdd, margin);
        newScalableChart.copyCells(this.chart, 0, rows, 0, columns, 0, 0);
        newScalableChart.setPosition(this.position.getRow(), this.position.getColumn());
        return newScalableChart;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public CellInfo getCellInfo(int row, int column) {
        return chart[row][column];
    }
}
