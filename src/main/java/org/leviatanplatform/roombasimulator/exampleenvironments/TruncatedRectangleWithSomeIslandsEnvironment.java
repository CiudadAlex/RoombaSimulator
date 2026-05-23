package org.leviatanplatform.roombasimulator.exampleenvironments;

import org.leviatanplatform.roombasimulator.engine.domain.Position;
import org.leviatanplatform.roombasimulator.exampleenvironments.parent.AbstractWallPositionsEnvironment;
import org.leviatanplatform.roombasimulator.exampleenvironments.utils.WallRectangle;

import java.util.List;

public class TruncatedRectangleWithSomeIslandsEnvironment extends AbstractWallPositionsEnvironment {

    public TruncatedRectangleWithSomeIslandsEnvironment(int rows, int columns, int truncationLength, int islandLength) {
        super(new Position(islandLength + 3, 0),
                buildListPositionsWall(rows, columns, truncationLength, islandLength));
    }

    public static List<Position> buildListPositionsWall(int rows, int columns, int truncationLength, int islandLength) {

        int islandHalf = islandLength/2;

        List<Position> listWallPositions = TruncatedRectangleEnvironment.buildListPositionsWall(rows, columns, truncationLength);
        listWallPositions.addAll(WallRectangle.generateWallRectangle(islandHalf, -islandHalf, -2 * islandHalf, -islandHalf));
        listWallPositions.addAll(WallRectangle.generateWallRectangle(islandHalf, -islandHalf, islandHalf, 2 * islandHalf));
        return listWallPositions;
    }

}
