package org.leviatanplatform.roombasimulator.engine;

import org.leviatanplatform.roombasimulator.engine.domain.CellInfo;
import org.leviatanplatform.roombasimulator.engine.domain.Movement;
import org.leviatanplatform.roombasimulator.engine.domain.Position;
import org.leviatanplatform.roombasimulator.engine.domain.ScalableChart;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChartExplorer {

    public static boolean checkIfPositionIsFullyEncircledByWall(Position position, ScalableChart chart) {

        Position initialPosition = position.clonePosition();
        Set<Position> setNotWallPositions = new HashSet<>();
        setNotWallPositions.add(initialPosition);

        while (true) {

            List<Position> listNearbyIteration = new ArrayList<>();

            for (Position positionNotWall : setNotWallPositions) {

                if (isPositionInChartEdge(position, chart)) {
                    return false;
                }

                List<Position> listNearby = getNearbyPositionsNotWall(chart, positionNotWall);
                listNearbyIteration.addAll(listNearby);
            }

            int numAllPositionsBefore = setNotWallPositions.size();
            setNotWallPositions.addAll(listNearbyIteration);
            int numAllPositionsAfter = setNotWallPositions.size();

            if (numAllPositionsBefore == numAllPositionsAfter) {
                // No more to add so finished search
                break;
            }
        }

        return true;
    }

    private static boolean isPositionInChartEdge(Position position, ScalableChart chart) {

        int rows = chart.getRows();
        int columns = chart.getColumns();

        int row = position.getRow();
        int column = position.getColumn();

        if (row <= 0 || row >= rows - 1) {
            return true;
        }

        if (column <= 0 || column >= columns - 1) {
            return true;
        }

        return false;
    }

    private static List<Position> getNearbyPositionsNotWall(ScalableChart chart, Position positionNotWall) {

        List<Position> listNearbyPositionsNotWall = new ArrayList<>();

        addNearbyPositionIfNotWall(listNearbyPositionsNotWall, chart, positionNotWall, Movement.UP);
        addNearbyPositionIfNotWall(listNearbyPositionsNotWall, chart, positionNotWall, Movement.DOWN);
        addNearbyPositionIfNotWall(listNearbyPositionsNotWall, chart, positionNotWall, Movement.LEFT);
        addNearbyPositionIfNotWall(listNearbyPositionsNotWall, chart, positionNotWall, Movement.RIGHT);

        return listNearbyPositionsNotWall;
    }

    private static void addNearbyPositionIfNotWall(List<Position> listNearbyPositionsNotWall, ScalableChart chart, Position positionNotWall, Movement movement) {

        Position positionNearby = positionNotWall.clonePosition();
        positionNearby.move(movement);
        CellInfo cellInfo = chart.getCellInfo(positionNearby);

        if (!cellInfo.isWall()) {
            listNearbyPositionsNotWall.add(positionNearby);
        }
    }
}
