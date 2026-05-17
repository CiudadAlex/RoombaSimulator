package org.leviatanplatform.roombasimulator.exampleenvironments;

import org.leviatanplatform.roombasimulator.engine.domain.Environment;
import org.leviatanplatform.roombasimulator.engine.domain.Movement;
import org.leviatanplatform.roombasimulator.engine.domain.MovementResult;
import org.leviatanplatform.roombasimulator.engine.domain.Position;
import org.leviatanplatform.roombasimulator.exampleenvironments.utils.HorizontalRange;
import org.leviatanplatform.roombasimulator.exampleenvironments.utils.MovementStatus;
import org.leviatanplatform.roombasimulator.exampleenvironments.utils.VerticalRange;

public class RectangleEnvironment implements Environment {

    private Position position = new Position(0, 0);

    private final VerticalRange verticalRange;
    private final HorizontalRange horizontalRange;

    public RectangleEnvironment(int rows, int columns) {
        int halfRows = rows / 2;
        int halfColumns = columns / 2;

        verticalRange = new VerticalRange(halfRows, -halfRows, -halfColumns, halfColumns);
        horizontalRange = new HorizontalRange(halfRows, -halfRows, -halfColumns, halfColumns);
    }

    @Override
    public MovementResult tryToMove(Movement movement) {

        switch (movement) {
            case UP, DOWN -> {
                if (verticalRange.applies(position)) {
                    MovementStatus movementStatus = verticalRange.evaluateMovement(position, movement);
                    this.position = movementStatus.getNewPosition();
                    return movementStatus.getMovementResult();
                }
            }
            case LEFT, RIGHT -> {
                if (horizontalRange.applies(position)) {
                    MovementStatus movementStatus = horizontalRange.evaluateMovement(position, movement);
                    this.position = movementStatus.getNewPosition();
                    return movementStatus.getMovementResult();
                }
            }
        }

        return null;
    }

    // FIXME use VerticalRange
}
