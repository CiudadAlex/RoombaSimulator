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

        Rectangle biggestRectangle = null;
        int biggestRectangleArea = -1;

        for (Position notActuatedPosition : listNotActuatedPositions) {

            Rectangle rectangle = findMaxRectangleFromNotActuatedPosition(notActuatedPosition, chart);

            if (rectangle.getArea() > biggestRectangleArea) {
                biggestRectangle = rectangle;
                biggestRectangleArea = rectangle.getArea();
            }
        }

        return biggestRectangle;
    }

    private static Rectangle findMaxRectangleFromNotActuatedPosition(Position notActuatedPosition, ScalableChart chart) {

        Rectangle currentRectangle = new Rectangle(notActuatedPosition, notActuatedPosition);

        int lastRectangleArea = -1;

        while (currentRectangle.getArea() > lastRectangleArea) {

            lastRectangleArea = currentRectangle.getArea();
            currentRectangle = tryIncreaseSizeInAllDirections(currentRectangle, chart);
        }

        return currentRectangle;
    }

    private static Rectangle tryIncreaseSizeInAllDirections(Rectangle rectangle, ScalableChart chart) {

        Rectangle currentRectangle = rectangle;
        currentRectangle = tryIncreaseSizeInOneDirection(currentRectangle, chart, true, true);
        currentRectangle = tryIncreaseSizeInOneDirection(currentRectangle, chart, true, false);
        currentRectangle = tryIncreaseSizeInOneDirection(currentRectangle, chart, false, true);
        currentRectangle = tryIncreaseSizeInOneDirection(currentRectangle, chart, false, false);

        return currentRectangle;
    }

    private static Rectangle tryIncreaseSizeInOneDirection(Rectangle rectangle, ScalableChart chart, boolean rowOrColumn, boolean minOrMax) {

        Rectangle rectangleExpanded = rectangle.expand(rowOrColumn, minOrMax);

        boolean rectangleNotActuated = ChartUtils.isRectangleNotActuated(rectangleExpanded, chart);

        if (rectangleNotActuated) {
            return rectangleExpanded;
        } else {
            return rectangle;
        }
    }

}
