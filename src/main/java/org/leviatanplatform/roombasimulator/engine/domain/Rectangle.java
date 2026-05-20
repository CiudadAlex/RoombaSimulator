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
}
