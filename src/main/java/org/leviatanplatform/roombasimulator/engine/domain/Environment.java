package org.leviatanplatform.roombasimulator.engine.domain;

public interface Environment {

    MovementResult tryToMove(Movement movement);
}
