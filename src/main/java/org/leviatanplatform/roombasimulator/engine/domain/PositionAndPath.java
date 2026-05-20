package org.leviatanplatform.roombasimulator.engine.domain;

import java.util.List;

public class PositionAndPath {

    private Position position;
    private List<Movement> path;

    public PositionAndPath(Position position, List<Movement> path) {
        this.position = position;
        this.path = path;
    }

    public Position getPosition() {
        return position;
    }

    public List<Movement> getPath() {
        return path;
    }
}
