package org.leviatanplatform.roombasimulator.engine.domain;

public class Rectangle {

    private final Position minPosition;
    private final Position maxPosition;

    public Rectangle(Position minPosition, Position maxPosition) {
        this.minPosition = minPosition;
        this.maxPosition = maxPosition;
    }

    public Position getMinPosition() {
        return minPosition;
    }

    public Position getMaxPosition() {
        return maxPosition;
    }

    public int getArea() {

        int minRow = minPosition.getRow();
        int minColumn = minPosition.getColumn();
        int maxRow = maxPosition.getRow();
        int maxColumn = maxPosition.getColumn();

        return (maxRow - minRow + 1) * (maxColumn - minColumn + 1);
    }

    public Rectangle expand(boolean rowOrColumn, boolean minOrMax) {

        Position newMinPosition = minPosition.clonePosition();
        Position newMaxPosition = maxPosition.clonePosition();

        if (minOrMax) {
            // MIN

            if (rowOrColumn) {
                // ROW
                newMinPosition.addToRow(-1);

            } else {
                // COLUMN
                newMinPosition.addToColumn(-1);
            }


        } else {
            // MAX
            if (rowOrColumn) {
                // ROW
                newMaxPosition.addToRow(1);

            } else {
                // COLUMN
                newMaxPosition.addToColumn(1);
            }
        }

        return new Rectangle(newMinPosition, newMaxPosition);
    }
}
