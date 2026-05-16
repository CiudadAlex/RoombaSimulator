package org.leviatanplatform.roombasimulator.engine.domain;

public class Position {

    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void move(Movement movement) {
        switch (movement) {
            case UP -> row = row - 1;
            case DOWN -> row = row + 1;
            case LEFT -> column = column - 1;
            case RIGHT -> column = column + 1;
        }
    }

    public Position clonePosition() {
        return new Position(row, column);
    }
}
