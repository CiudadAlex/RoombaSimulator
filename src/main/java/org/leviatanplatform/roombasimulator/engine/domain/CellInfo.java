package org.leviatanplatform.roombasimulator.engine.domain;

public interface CellInfo {

    boolean isStepped();

    boolean isActuated();

    boolean isWall();
}
