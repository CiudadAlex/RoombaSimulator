package org.leviatanplatform.roombasimulator.engine;

import org.leviatanplatform.roombasimulator.engine.domain.Environment;
import org.leviatanplatform.roombasimulator.engine.domain.Movement;
import org.leviatanplatform.roombasimulator.engine.domain.MovementResult;
import org.leviatanplatform.roombasimulator.engine.domain.ScalableChart;

public class Roomba {

    private final int radius;
    private final Environment env;
    private ScalableChart chart;

    public Roomba(int radius, Environment env) {
        this.radius = radius;
        this.env = env;
        this.chart = new ScalableChart();
    }

    public void explore() {
        // FIXME finish
    }

    private void exploreStraightLineUntilWall(Movement movement) {

        MovementResult movementResult;

        while (true) {

            movementResult = env.tryToMove(movement);

            if (movementResult != MovementResult.WALL) {

            } else {
                chart.stepCurrentPosition();
                chart.actuateCurrentPosition(radius);
            }
        }



        // FIXME finish
    }
}
