package org.leviatanplatform.roombasimulator.engine.domain;

public class Cell implements CellInfo {

    private boolean stepped;
    private boolean actuated;
    private boolean wall;

    public boolean isStepped() {
        return stepped;
    }

    public void setStepped(boolean stepped) {
        this.stepped = stepped;
    }

    public boolean isActuated() {
        return actuated;
    }

    public void setActuated(boolean actuated) {
        this.actuated = actuated;
    }

    public boolean isWall() {
        return wall;
    }

    public void setWall(boolean wall) {
        this.wall = wall;
    }
}
