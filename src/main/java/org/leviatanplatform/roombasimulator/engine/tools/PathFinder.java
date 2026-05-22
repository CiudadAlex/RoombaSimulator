package org.leviatanplatform.roombasimulator.engine.tools;

import org.leviatanplatform.roombasimulator.engine.domain.Movement;
import org.leviatanplatform.roombasimulator.engine.domain.Position;
import org.leviatanplatform.roombasimulator.engine.domain.PositionAndPath;
import org.leviatanplatform.roombasimulator.engine.domain.ScalableChart;
import org.leviatanplatform.roombasimulator.engine.utils.ChartUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PathFinder {

    /** Finds the first path found from positionStart to one of the positions in the list listPositionEnd  */
    public static List<Movement> findPath(Position positionStart, List<Position> listPositionEnd, ScalableChart chart) {

        PositionAndPath initialPositionAndPath = new PositionAndPath(positionStart.clonePosition(), new ArrayList<>());
        Set<PositionAndPath> setNotWallPositions = new HashSet<>();
        setNotWallPositions.add(initialPositionAndPath);

        while (true) {

            List<PositionAndPath> listNearbyIteration = new ArrayList<>();

            for (PositionAndPath positionNotWall : setNotWallPositions) {

                List<PositionAndPath> listNearby = ChartUtils.getNearbyPositionAndPathNotWall(chart, positionNotWall);
                listNearbyIteration.addAll(listNearby);
            }

            for (PositionAndPath positionNotWall : listNearbyIteration) {
                if (listPositionEnd.contains(positionNotWall.getPosition())) {
                    return positionNotWall.getPath();
                }
            }

            setNotWallPositions.addAll(listNearbyIteration);
        }
    }
}
