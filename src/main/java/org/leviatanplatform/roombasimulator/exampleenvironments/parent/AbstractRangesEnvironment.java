package org.leviatanplatform.roombasimulator.exampleenvironments.parent;

import org.leviatanplatform.roombasimulator.engine.domain.Environment;
import org.leviatanplatform.roombasimulator.engine.domain.Movement;
import org.leviatanplatform.roombasimulator.engine.domain.MovementResult;
import org.leviatanplatform.roombasimulator.engine.domain.Position;
import org.leviatanplatform.roombasimulator.exampleenvironments.utils.HorizontalRange;
import org.leviatanplatform.roombasimulator.exampleenvironments.utils.MovementStatus;
import org.leviatanplatform.roombasimulator.exampleenvironments.utils.Range;
import org.leviatanplatform.roombasimulator.exampleenvironments.utils.VerticalRange;

import java.util.List;

public abstract class AbstractRangesEnvironment implements Environment {

    private Position position;
    private final List<VerticalRange> listVerticalRange;
    private final List<HorizontalRange> listHorizontalRange;

    public AbstractRangesEnvironment(Position position, List<VerticalRange> listVerticalRange, List<HorizontalRange> listHorizontalRange) {
        this.position = position;
        this.listVerticalRange = listVerticalRange;
        this.listHorizontalRange = listHorizontalRange;
    }

    @Override
    public MovementResult tryToMove(Movement movement) {

        switch (movement) {
            case UP, DOWN -> {

                for (VerticalRange verticalRange : listVerticalRange) {
                    if (verticalRange.applies(position)) {
                        return evaluateMovement(verticalRange, movement);
                    }
                }

            }
            case LEFT, RIGHT -> {

                for (HorizontalRange horizontalRange : listHorizontalRange) {
                    if (horizontalRange.applies(position)) {
                        return evaluateMovement(horizontalRange, movement);
                    }
                }
            }
        }

        return null;
    }

    private MovementResult evaluateMovement(Range range, Movement movement) {
        MovementStatus movementStatus = range.evaluateMovement(position, movement);
        this.position = movementStatus.getNewPosition();
        return movementStatus.getMovementResult();
    }

}
