package org.leviatanplatform.roombasimulator.exampleenvironments.utils;

import org.leviatanplatform.roombasimulator.engine.domain.Movement;
import org.leviatanplatform.roombasimulator.engine.domain.MovementResult;
import org.leviatanplatform.roombasimulator.engine.domain.Position;

public class VerticalRange {

    private int top;
    private int bottom;
    private int left;
    private int right;

    public VerticalRange(int top, int bottom, int left, int right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public boolean applies(Position position) {

        int column = position.getColumn();

        if (column <= right && column >= left) {
            return true;
        }

        return false;
    }

    public MovementStatus evaluateMovement(Position position, Movement movement) {

        Position positionMoved = position.clonePosition();
        positionMoved.move(movement);

        int row = positionMoved.getRow();

        if (row < bottom || row > top) {
            return new MovementStatus(MovementResult.WALL, position);
        } else {
            return new MovementStatus(MovementResult.SUCCESS, positionMoved);
        }
    }
}
