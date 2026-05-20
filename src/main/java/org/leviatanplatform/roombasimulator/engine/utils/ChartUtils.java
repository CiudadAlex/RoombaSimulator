package org.leviatanplatform.roombasimulator.engine.utils;

import org.leviatanplatform.roombasimulator.engine.domain.*;

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

    public static List<PositionAndPath> getNearbyPositionAndPathNotWall(ScalableChart chart, PositionAndPath positionNotWall) {

        List<PositionAndPath> listNearbyPositionsNotWall = new ArrayList<>();

        addNearbyPositionAndPathIfNotWall(listNearbyPositionsNotWall, chart, positionNotWall, Movement.UP);
        addNearbyPositionAndPathIfNotWall(listNearbyPositionsNotWall, chart, positionNotWall, Movement.DOWN);
        addNearbyPositionAndPathIfNotWall(listNearbyPositionsNotWall, chart, positionNotWall, Movement.LEFT);
        addNearbyPositionAndPathIfNotWall(listNearbyPositionsNotWall, chart, positionNotWall, Movement.RIGHT);

        return listNearbyPositionsNotWall;
    }

    private static void addNearbyPositionAndPathIfNotWall(List<PositionAndPath> listNearbyPositionsNotWall, ScalableChart chart, PositionAndPath positionNotWall, Movement movement) {

        Position positionNearby = positionNotWall.getPosition().clonePosition();
        positionNearby.move(movement);
        CellInfo cellInfo = chart.getCellInfo(positionNearby);

        if (!cellInfo.isWall()) {
            List<Movement> path = new ArrayList<>(positionNotWall.getPath());
            path.add(movement);
            PositionAndPath positionAndPathNearby = new PositionAndPath(positionNearby, path);
            listNearbyPositionsNotWall.add(positionAndPathNearby);
        }
    }
}
