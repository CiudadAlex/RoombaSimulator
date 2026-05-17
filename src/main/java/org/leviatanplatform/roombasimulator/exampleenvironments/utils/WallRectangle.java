package org.leviatanplatform.roombasimulator.exampleenvironments.utils;

import org.leviatanplatform.roombasimulator.engine.domain.Position;

import java.util.ArrayList;
import java.util.List;

public class WallRectangle {

    public static List<Position> generateWallRectangle(int top, int bottom, int left, int right) {

        List<Position> listWallPositions = new ArrayList<>();

        listWallPositions.addAll(generateHorizontalWall(top, left, right));
        listWallPositions.addAll(generateHorizontalWall(bottom, left, right));

        listWallPositions.addAll(generateVerticalWall(top, bottom, left));
        listWallPositions.addAll(generateVerticalWall(top, bottom, right));

        return listWallPositions;
    }

    public static List<Position> generateVerticalWall(int top, int bottom, int col) {

        List<Position> listWallPositions = new ArrayList<>();

        for (int i = bottom; i <= top; i++) {
            listWallPositions.add(new Position(i, col));
        }

        return listWallPositions;
    }

    public static List<Position> generateHorizontalWall(int row, int left, int right) {

        List<Position> listWallPositions = new ArrayList<>();

        for (int i = left; i <= right; i++) {
            listWallPositions.add(new Position(row, i));
        }

        return listWallPositions;
    }
}
