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

    public static List<Movement> findPath(Position position1, Position position2, ScalableChart chart) {

        // PositionAndPath
        PositionAndPath initialPositionAndPath = new PositionAndPath(position1.clonePosition(), new ArrayList<>());
        Set<PositionAndPath> setNotWallPositions = new HashSet<>();
        setNotWallPositions.add(initialPositionAndPath);

        while (true) {

            List<Position> listNearbyIteration = new ArrayList<>();

            for (Position positionNotWall : setNotWallPositions) {

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



        // FIXME finish

        return null;
    }
}
