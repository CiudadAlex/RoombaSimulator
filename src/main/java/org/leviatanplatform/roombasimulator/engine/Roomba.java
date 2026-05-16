package org.leviatanplatform.roombasimulator.engine;

import org.leviatanplatform.roombasimulator.engine.domain.Environment;
import org.leviatanplatform.roombasimulator.engine.domain.Movement;
import org.leviatanplatform.roombasimulator.engine.domain.MovementResult;
import org.leviatanplatform.roombasimulator.engine.domain.ScalableChart;

public class Roomba {

    private final int radius;
    private final Environment env;
    private final boolean dextroRotatory;
    private ScalableChart chart;

    public Roomba(int radius, Environment env) {
        this(radius, env, false);
    }

    public Roomba(int radius, Environment env, boolean dextroRotatory) {
        this.radius = radius;
        this.env = env;
        this.dextroRotatory = dextroRotatory;
        this.chart = new ScalableChart();
    }

    public void explore() {

        chart.stepCurrentPosition();
        chart.actuateCurrentPosition(radius);

        exploreStraightLineUntilWall(Movement.UP);
        exploreWall(Movement.UP);

        // FIXME check wall completed
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

    private Movement getMovementSideways(Movement movement, boolean inverse) {

        boolean dextroRotatoryEffective = dextroRotatory ^ inverse;

        if (dextroRotatoryEffective) {
            return movement.getDextroRotatorySubsequent();
        } else {
            return movement.getLevoRotatorySubsequent();
        }
    }

    private void exploreWall(Movement movementThatHitsWithWall) {

        Movement movementSideways = getMovementSideways(movementThatHitsWithWall, false);

        MovementResult movementResult = exploreMove(movementSideways);

        switch (movementResult) {
            case WALL ->  exploreWall(movementSideways);
            case SUCCESS -> exploreWallSideWays(movementSideways);
        };
    }

    private void exploreWallSideWays(Movement movementSideways) {

        Movement movementSidewaysInverse = getMovementSideways(movementSideways, true);

        MovementResult movementResult = exploreMove(movementSidewaysInverse);

        switch (movementResult) {
            case WALL ->  exploreWall(movementSidewaysInverse);
            case SUCCESS -> exploreWallSideWays(movementSidewaysInverse);
        };
    }
}
