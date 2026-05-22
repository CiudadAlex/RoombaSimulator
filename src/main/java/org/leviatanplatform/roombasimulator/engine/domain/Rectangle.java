package org.leviatanplatform.roombasimulator.engine.domain;

public class Rectangle {

    private Position minPosition;
    private Position maxPosition;

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
}
