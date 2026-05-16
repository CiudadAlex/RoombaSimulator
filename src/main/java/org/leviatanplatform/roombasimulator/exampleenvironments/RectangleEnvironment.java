package org.leviatanplatform.roombasimulator.exampleenvironments;

import org.leviatanplatform.roombasimulator.engine.domain.Environment;
import org.leviatanplatform.roombasimulator.engine.domain.Movement;
import org.leviatanplatform.roombasimulator.engine.domain.MovementResult;
import org.leviatanplatform.roombasimulator.engine.domain.Position;

public class RectangleEnvironment implements Environment {

    private Position position = new Position(0, 0);

    private final int halfRows;
    private final int halfColumns;

    public RectangleEnvironment(int rows, int columns) {
        halfRows = rows / 2;
        halfColumns = columns / 2;
    }

    @Override
    public MovementResult tryToMove(Movement movement) {

        Position positionMoved = this.position.clonePosition();
        positionMoved.move(movement);

        int row = positionMoved.getRow();
        int column = positionMoved.getColumn();

        switch (movement) {
            case UP, DOWN -> {
                if (row < -halfRows || row > halfRows) {
                    return MovementResult.WALL;
                } else {
                    this.position = positionMoved;
                    return MovementResult.SUCCESS;
                }
            }
            case LEFT, RIGHT -> {
                if (column < -halfColumns || column > halfColumns) {
                    return MovementResult.WALL;
                } else {
                    this.position = positionMoved;
                    return MovementResult.SUCCESS;
                }
            }
        }

        return null;
    }
}
