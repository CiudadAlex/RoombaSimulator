package org.leviatanplatform.roombasimulator.engine.utils;

import org.leviatanplatform.roombasimulator.engine.domain.CellInfo;
import org.leviatanplatform.roombasimulator.engine.domain.Movement;
import org.leviatanplatform.roombasimulator.engine.domain.Position;
import org.leviatanplatform.roombasimulator.engine.domain.ScalableChart;

import java.util.ArrayList;
import java.util.List;

public class ChartUtils {

    public static boolean isPositionInChartEdge(Position position, ScalableChart chart) {

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

    public static List<Position> getNearbyPositionsNotWall(ScalableChart chart, Position positionNotWall) {

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
