package org.leviatanplatform.roombasimulator.exampleenvironments.utils;

import org.leviatanplatform.roombasimulator.engine.domain.Movement;
import org.leviatanplatform.roombasimulator.engine.domain.Position;

public interface Range {

    boolean applies(Position position);

    MovementStatus evaluateMovement(Position position, Movement movement);
}
