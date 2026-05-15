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

        chart.stepCurrentPosition();
        chart.actuateCurrentPosition(radius);

        exploreStraightLineUntilWall(Movement.UP);

        // FIXME finish
    }

    private void move(Movement movement) {

        chart.move(movement);
        chart.stepCurrentPosition();
        chart.actuateCurrentPosition(radius);
    }

    private MovementResult exploreMove(Movement movement) {

        MovementResult movementResult = env.tryToMove(movement);

        switch (movementResult) {
            case WALL -> chart.setWall(movement);
            case SUCCESS -> move(movement);
        };

        return movementResult;
    }

    private void exploreStraightLineUntilWall(Movement movement) {

        while (true) {

            MovementResult movementResult = exploreMove(movement);

            if (movementResult == MovementResult.WALL) {
                break;
            }
        }
    }

    private void exploreWall(Movement movementThatHitsWithWall) {
        Movement movementSideways = movementThatHitsWithWall.getLevoRotatorySubsequent();
    }
}
