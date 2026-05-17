package org.leviatanplatform.roombasimulator.exampleenvironments.utils;

import org.leviatanplatform.roombasimulator.engine.domain.Movement;
import org.leviatanplatform.roombasimulator.engine.domain.MovementResult;
import org.leviatanplatform.roombasimulator.engine.domain.Position;

public class HorizontalRange {

    private int top;
    private int bottom;
    private int left;
    private int right;

    public HorizontalRange(int top, int bottom, int left, int right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public boolean applies(Position position) {

        int row = position.getRow();

        if (row <= top && row >= bottom) {
            return true;
        }

        return false;
    }

    public MovementStatus evaluateMovement(Position position, Movement movement) {

        Position positionMoved = position.clonePosition();
        positionMoved.move(movement);

        int column = positionMoved.getColumn();

        if (column <= left || column >= right) {
            return new MovementStatus(MovementResult.WALL, position);
        } else {
            return new MovementStatus(MovementResult.SUCCESS, positionMoved);
        }
    }
}
