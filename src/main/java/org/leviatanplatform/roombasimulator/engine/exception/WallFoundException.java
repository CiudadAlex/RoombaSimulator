package org.leviatanplatform.roombasimulator.engine.exception;

import org.leviatanplatform.roombasimulator.engine.domain.Movement;

public class WallFoundException extends RuntimeException {

    private Movement movementThatHitTheWall;

    public WallFoundException(Movement movementThatHitTheWall) {
        this.movementThatHitTheWall = movementThatHitTheWall;
    }

    public Movement getMovementThatHitTheWall() {
        return movementThatHitTheWall;
    }
}
