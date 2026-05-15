package org.leviatanplatform.roombasimulator.engine.domain;

public enum Movement {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public Movement getDextroRotatorySubsequent() {
        return switch (this) {
            case UP -> RIGHT;
            case DOWN -> LEFT;
            case LEFT -> UP;
            case RIGHT -> DOWN;
        };
    }

    public Movement getLevoRotatorySubsequent() {
        return switch (this) {
            case UP -> LEFT;
            case DOWN -> RIGHT;
            case LEFT -> DOWN;
            case RIGHT -> UP;
        };
    }
}
