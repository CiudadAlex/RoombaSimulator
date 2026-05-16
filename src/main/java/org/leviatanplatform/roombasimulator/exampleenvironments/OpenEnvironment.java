package org.leviatanplatform.roombasimulator.exampleenvironments;

import org.leviatanplatform.roombasimulator.engine.domain.Environment;
import org.leviatanplatform.roombasimulator.engine.domain.Movement;
import org.leviatanplatform.roombasimulator.engine.domain.MovementResult;

public class OpenEnvironment implements Environment {

    @Override
    public MovementResult tryToMove(Movement movement) {
        return MovementResult.SUCCESS;
    }
}
