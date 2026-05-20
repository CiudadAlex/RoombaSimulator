package org.leviatanplatform.roombasimulator.engine.tools;

import org.leviatanplatform.roombasimulator.engine.domain.Position;
import org.leviatanplatform.roombasimulator.engine.domain.Rectangle;
import org.leviatanplatform.roombasimulator.engine.domain.ScalableChart;
import org.leviatanplatform.roombasimulator.engine.utils.ChartUtils;

import java.util.List;
import java.util.Set;

public class LackOfActionRectangleFinder {

    public static Rectangle findBiggestRectangle(Position position, ScalableChart chart) {

        Set<Position> setNotWallPositions = ChartUtils.getAllPositionsInsideWallFromGivenPosition(position, chart);
        List<Position> listNotActuatedPositions = setNotWallPositions.stream().filter(p -> !chart.getCellInfo(p).isActuated()).toList();

        // ChartUtils.isRectangleNotActuated(Rectangle rectangle, ScalableChart chart)

        // FIXME finish
        return null;
    }
}
