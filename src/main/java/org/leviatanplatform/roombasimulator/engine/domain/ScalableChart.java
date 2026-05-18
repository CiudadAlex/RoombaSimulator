package org.leviatanplatform.roombasimulator.engine.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ScalableChart {

    private final int rows;
    private final int columns;
    private final int margin;
    private final Cell[][] chart;
    private Position position;
    private Map<String, Position> mapTraceableLocations = new HashMap<>();

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

        ScalableChart newScalableChart = new ScalableChart(this.rows + rowsToAdd, this.columns, this.margin);
        newScalableChart.copyCells(this.chart, 0, this.rows, 0, this.columns, rowsToAdd, 0);
        newScalableChart.setPosition(this.position.getRow() + rowsToAdd, this.position.getColumn());
        newScalableChart.copyMapTraceableLocations(mapTraceableLocations, loc -> new Position(loc.getRow() + rowsToAdd, loc.getColumn()));
        return newScalableChart;
    }

    private ScalableChart scaleDown(int rowsToAdd) {

        ScalableChart newScalableChart = new ScalableChart(this.rows + rowsToAdd, this.columns, this.margin);
        newScalableChart.copyCells(this.chart, 0, this.rows, 0, this.columns, 0, 0);
        newScalableChart.setPosition(this.position.getRow(), this.position.getColumn());
        newScalableChart.copyMapTraceableLocations(mapTraceableLocations, loc -> loc);
        return newScalableChart;
    }

    private ScalableChart scaleLeft(int columnsToAdd) {

        ScalableChart newScalableChart = new ScalableChart(this.rows, this.columns + columnsToAdd, this.margin);
        newScalableChart.copyCells(this.chart, 0, this.rows, 0, this.columns, 0, columnsToAdd);
        newScalableChart.setPosition(this.position.getRow(), this.position.getColumn() + columnsToAdd);
        newScalableChart.copyMapTraceableLocations(mapTraceableLocations, loc -> new Position(loc.getRow() , loc.getColumn() + columnsToAdd));
        return newScalableChart;
    }

    private ScalableChart scaleRight(int columnsToAdd) {

        ScalableChart newScalableChart = new ScalableChart(this.rows, this.columns + columnsToAdd, this.margin);
        newScalableChart.copyCells(this.chart, 0, this.rows, 0, this.columns, 0, 0);
        newScalableChart.setPosition(this.position.getRow(), this.position.getColumn());
        newScalableChart.copyMapTraceableLocations(mapTraceableLocations, loc -> loc);
        return newScalableChart;
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public PositionInfo getPositionInfo() {
        return this.position;
    }

    public CellInfo getCellInfo(int row, int column) {
        return chart[row][column];
    }

    public void setTraceableLocation(String name, PositionInfo location) {
        this.mapTraceableLocations.put(name, new Position(location));
    }

    public Position getTraceableLocation(String name) {
        return this.mapTraceableLocations.get(name);
    }

    private void copyMapTraceableLocations(Map<String, Position> mapTraceableLocations, Function<Position, Position> transformPosition) {

        for (Map.Entry<String, Position> entry : mapTraceableLocations.entrySet()) {
            String locationName = entry.getKey();
            Position location = entry.getValue();
            Position transformedLocation = transformPosition.apply(location);
            this.mapTraceableLocations.put(locationName, transformedLocation);
        }
    }
}
