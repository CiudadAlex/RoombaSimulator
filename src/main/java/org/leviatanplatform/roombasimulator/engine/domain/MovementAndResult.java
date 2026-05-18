package org.leviatanplatform.roombasimulator.engine.domain;

public class MovementAndResult {

    private Movement movement;
    private MovementResult movementResult;

    public MovementAndResult(Movement movement, MovementResult movementResult) {
        this.movement = movement;
        this.movementResult = movementResult;
    }

    public Movement getMovement() {
        return movement;
    }

    public MovementResult getMovementResult() {
        return movementResult;
    }
}
