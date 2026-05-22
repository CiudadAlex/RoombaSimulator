package org.leviatanplatform.roombasimulator.engine.domain;

import java.util.List;

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
        return getHeight() * getWidth();
    }

    public int getHeight() {

        int minRow = minPosition.getRow();
        int maxRow = maxPosition.getRow();

        return maxRow - minRow + 1;
    }

    public int getWidth() {

        int minColumn = minPosition.getColumn();
        int maxColumn = maxPosition.getColumn();

        return maxColumn - minColumn + 1;
    }

    public Position getCenter() {

        int minRow = minPosition.getRow();
        int minColumn = minPosition.getColumn();
        int maxRow = maxPosition.getRow();
        int maxColumn = maxPosition.getColumn();

        return new Position((maxRow + minRow)/2, (maxColumn + minColumn)/2);
    }

    public List<Position> getAllCorners() {

        int minRow = minPosition.getRow();
        int minColumn = minPosition.getColumn();
        int maxRow = maxPosition.getRow();
        int maxColumn = maxPosition.getColumn();

        return List.of(
                minPosition.clonePosition(),
                maxPosition.clonePosition(),
                new Position(minRow, maxColumn),
                new Position(maxRow, minColumn)
        );
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
