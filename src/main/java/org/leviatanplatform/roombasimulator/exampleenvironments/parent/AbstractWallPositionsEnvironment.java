package org.leviatanplatform.roombasimulator.exampleenvironments.parent;

import org.leviatanplatform.roombasimulator.engine.domain.Environment;
import org.leviatanplatform.roombasimulator.engine.domain.Movement;
import org.leviatanplatform.roombasimulator.engine.domain.MovementResult;
import org.leviatanplatform.roombasimulator.engine.domain.Position;

import java.util.List;

public class AbstractWallPositionsEnvironment implements Environment {

    private Position position;
    private final List<Position> listWallPositions;

    public AbstractWallPositionsEnvironment(Position position, List<Position> listWallPositions) {
        this.position = position;
        this.listWallPositions = listWallPositions;
    }

    @Override
    public MovementResult tryToMove(Movement movement) {

        Position positionMoved = position.clonePosition();
        positionMoved.move(movement);

        for (Position positionWall : listWallPositions) {
           if (positionMoved.equals(positionWall)) {
               return MovementResult.WALL;
           }
        }

        this.position = positionMoved;
        return MovementResult.SUCCESS;
    }

}
