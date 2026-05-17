package org.leviatanplatform.roombasimulator.exampleenvironments.utils;

import org.leviatanplatform.roombasimulator.engine.domain.MovementResult;
import org.leviatanplatform.roombasimulator.engine.domain.Position;

public class MovementStatus {

    private MovementResult movementResult;
    private Position newPosition;

    public MovementStatus(MovementResult movementResult, Position newPosition) {
        this.movementResult = movementResult;
        this.newPosition = newPosition;
    }

    public MovementResult getMovementResult() {
        return movementResult;
    }

    public Position getNewPosition() {
        return newPosition;
    }
}
