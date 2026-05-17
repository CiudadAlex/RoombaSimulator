package org.leviatanplatform.roombasimulator.exampleenvironments;

import org.leviatanplatform.roombasimulator.engine.domain.Environment;
import org.leviatanplatform.roombasimulator.engine.domain.Movement;
import org.leviatanplatform.roombasimulator.engine.domain.MovementResult;
import org.leviatanplatform.roombasimulator.engine.domain.Position;

public class TruncatedRectangleEnvironment implements Environment {

    private Position position = new Position(0, 0);

    private final int halfRows;
    private final int halfColumns;
    private final int truncationLength;

    public TruncatedRectangleEnvironment(int rows, int columns, int truncationLength) {
        this.halfRows = rows / 2;
        this.halfColumns = columns / 2;
        this.truncationLength = truncationLength;
    }

    @Override
    public MovementResult tryToMove(Movement movement) {
        // FIXME finish

        return null;
    }
}
