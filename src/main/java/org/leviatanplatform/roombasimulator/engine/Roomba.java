package org.leviatanplatform.roombasimulator.engine;

import org.leviatanplatform.roombasimulator.engine.domain.*;

public class Roomba {

    private static final String LOCATION_INIT_WALL_EXPLORING = "LOCATION_INIT_WALL_EXPLORING";

    private final int radius;
    private final Environment env;
    private final boolean dextroRotatory;
    private ScalableChart chart;

    private MovementListener movementListener;

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

        Position position = new Position(chart.getPositionInfo());
        boolean isFullyEncircled = ChartExplorer.checkIfPositionIsFullyEncircledByWall(position, chart);
        System.out.println("isFullyEncircled = " + isFullyEncircled);

        // FIXME check rectangles of void of action
    }

    private void move(Movement movement) {

        chart.move(movement);
        chart.stepCurrentPosition();
        chart.actuateCurrentPosition(radius);

        chart = chart.scaleIfNeeded();

        if (movementListener != null) {
            movementListener.movementDone();
        }
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

    private MovementAndResult getMovementSidewaysAndResult(MovementAndResult movementAndResult, boolean inverse) {

        Movement movement = movementAndResult.getMovement();
        Movement movementSideways = getMovementSideways(movement, inverse);
        MovementResult movementResult = exploreMove(movementSideways);
        return new MovementAndResult(movementSideways, movementResult);
    }

    private void exploreWall(Movement initialMovementThatHitsWithWall) {

        PositionInfo initialPositionInfo = chart.getPositionInfo().clonePositionInfo();
        chart.setTraceableLocation(LOCATION_INIT_WALL_EXPLORING, initialPositionInfo);

        MovementAndResult lastMovementAndResult = new MovementAndResult(initialMovementThatHitsWithWall, MovementResult.WALL);

        while (true) {

            switch (lastMovementAndResult.getMovementResult()) {
                case WALL -> {
                    lastMovementAndResult = getMovementSidewaysAndResult(lastMovementAndResult, false);
                }
                case SUCCESS -> {
                    lastMovementAndResult = getMovementSidewaysAndResult(lastMovementAndResult, true);
                }
            }

            PositionInfo transformedInitialPositionInfo = chart.getTraceableLocation(LOCATION_INIT_WALL_EXPLORING);
            PositionInfo currentPositionInfo = chart.getPositionInfo().clonePositionInfo();

            if (transformedInitialPositionInfo.equals(currentPositionInfo)) {
                // All the wall has been wandered
                break;
            }
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

    public void setMovementListener(MovementListener movementListener) {
        this.movementListener = movementListener;
    }
}
