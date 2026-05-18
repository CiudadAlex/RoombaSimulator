package org.leviatanplatform.roombasimulator.engine.domain;

public interface PositionInfo {

    int getRow();

    int getColumn();

    PositionInfo clonePositionInfo();
}
