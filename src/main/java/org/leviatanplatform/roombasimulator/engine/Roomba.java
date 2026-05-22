package org.leviatanplatform.roombasimulator.engine;

import org.leviatanplatform.roombasimulator.engine.domain.*;
import org.leviatanplatform.roombasimulator.engine.tools.ChartExplorer;
import org.leviatanplatform.roombasimulator.engine.tools.LackOfActionRectangleFinder;
import org.leviatanplatform.roombasimulator.engine.tools.PathFinder;

import java.util.List;

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

        exploreUntilFullyEncircledByWall(Movement.UP);
        actuateInAllAreas();

        System.out.println("Actuation finished");
    }

    private void actuateInAllAreas() {

        while (true) {

            Position position = new Position(chart.getPositionInfo());
            Rectangle biggestRectangle = LackOfActionRectangleFinder.findBiggestRectangle(position, chart);

            if (biggestRectangle == null) {
                break;
            }

            List<Movement> path = PathFinder.findPath(position, biggestRectangle.getAllCorners(), chart);

            followPath(path);
            actuateInRectangle(biggestRectangle);
        }
    }

    private void followPath(List<Movement> path) {

        for (Movement movement : path) {
            move(movement);
        }
    }

    private void actuateInRectangle(Rectangle rectangle) {

        Position position = new Position(chart.getPositionInfo());

        // FIXME finish
    }

    private void exploreUntilFullyEncircledByWall(Movement initialMovement) {

        boolean isFullyEncircled = false;
        Movement currentMovement = initialMovement;

        while (!isFullyEncircled) {

            exploreStraightLineUntilWall(currentMovement);
            exploreWall(currentMovement);

            Position position = new Position(chart.getPositionInfo());
            isFullyEncircled = ChartExplorer.checkIfPositionIsFullyEncircledByWall(position, chart);
            currentMovement = currentMovement.getDextroRotatorySubsequent();
        }

        System.out.println("isFullyEncircled = " + isFullyEncircled);
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
