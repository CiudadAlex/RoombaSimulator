package org.leviatanplatform.roombasimulator.engine;

import org.leviatanplatform.roombasimulator.engine.domain.*;

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

        chart = chart.scaleIfNeeded();
    }

    public MovementResult exploreMove(Movement movement) {

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
        }
    }

    public int getChartRows() {
        return chart.getRows();
    }

    public int getChartColumns() {
        return chart.getColumns();
    }

    public CellInfo getChartCellInfo(int row, int column) {
        return chart.getCellInfo(row, column);
    }

    public PositionInfo getChartPositionInfo() {
        return chart.getPositionInfo();
    }
}
