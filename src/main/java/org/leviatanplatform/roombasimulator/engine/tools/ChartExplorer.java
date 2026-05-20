package org.leviatanplatform.roombasimulator.engine.tools;

import org.leviatanplatform.roombasimulator.engine.domain.Position;
import org.leviatanplatform.roombasimulator.engine.domain.ScalableChart;
import org.leviatanplatform.roombasimulator.engine.utils.ChartUtils;

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

                if (ChartUtils.isPositionInChartEdge(positionNotWall, chart)) {
                    return false;
                }

                List<Position> listNearby = ChartUtils.getNearbyPositionsNotWall(chart, positionNotWall);
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

}
