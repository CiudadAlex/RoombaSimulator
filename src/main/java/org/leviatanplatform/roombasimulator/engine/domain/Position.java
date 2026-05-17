package org.leviatanplatform.roombasimulator.engine.domain;

public class Position implements PositionInfo {

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

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Position pos) {

            if (pos.row == this.row && pos.column == this.column) {
                return true;
            }
        }

        return false;
    }

    public Position clonePosition() {
        return new Position(row, column);
    }
}
