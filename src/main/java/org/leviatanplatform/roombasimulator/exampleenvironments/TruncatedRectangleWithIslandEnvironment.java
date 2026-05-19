package org.leviatanplatform.roombasimulator.exampleenvironments;

import org.leviatanplatform.roombasimulator.engine.domain.Position;
import org.leviatanplatform.roombasimulator.exampleenvironments.parent.AbstractWallPositionsEnvironment;
import org.leviatanplatform.roombasimulator.exampleenvironments.utils.WallRectangle;

import java.util.List;

public class TruncatedRectangleWithIslandEnvironment extends AbstractWallPositionsEnvironment {

    public TruncatedRectangleWithIslandEnvironment(int rows, int columns, int truncationLength, int islandLength) {
        super(new Position(0, islandLength + 3),
                buildListPositionsWall(rows, columns, truncationLength, islandLength));
    }

    public static List<Position> buildListPositionsWall(int rows, int columns, int truncationLength, int islandLength) {

        int islandHalf = islandLength/2;

        List<Position> listWallPositions = TruncatedRectangleEnvironment.buildListPositionsWall(rows, columns, truncationLength);
        listWallPositions.addAll(WallRectangle.generateWallRectangle(islandHalf, -islandHalf, -islandHalf, islandHalf));
        return listWallPositions;
    }

}
