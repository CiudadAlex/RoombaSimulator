package org.leviatanplatform.roombasimulator.exampleenvironments;

import org.leviatanplatform.roombasimulator.engine.domain.Position;
import org.leviatanplatform.roombasimulator.exampleenvironments.parent.AbstractWallPositionsEnvironment;
import org.leviatanplatform.roombasimulator.exampleenvironments.utils.WallRectangle;

import java.util.ArrayList;
import java.util.List;

public class TruncatedRectangleEnvironment extends AbstractWallPositionsEnvironment {

    public TruncatedRectangleEnvironment(int rows, int columns, int truncationLength) {
        super(new Position(0, 0),
                buildListPositionsWall(rows, columns, truncationLength));
    }

    public static List<Position> buildListPositionsWall(int rows, int columns, int truncationLength) {

        int halfRows = rows / 2;
        int halfColumns = columns / 2;

        List<Position> listWallPositions = new ArrayList<>();
        listWallPositions.addAll(WallRectangle.generateWallRectangle(halfRows, -halfRows, -halfColumns, halfColumns));

        listWallPositions.addAll(WallRectangle.generateWallRectangle(halfRows, halfRows-truncationLength, -halfColumns, -halfColumns+truncationLength));
        listWallPositions.addAll(WallRectangle.generateWallRectangle(halfRows, halfRows-truncationLength, halfColumns-truncationLength, halfColumns));


        listWallPositions.addAll(WallRectangle.generateWallRectangle(-halfRows+truncationLength, -halfRows, -halfColumns, -halfColumns+truncationLength));
        listWallPositions.addAll(WallRectangle.generateWallRectangle(-halfRows+truncationLength, -halfRows, halfColumns-truncationLength, halfColumns));

        return listWallPositions;
    }

}
