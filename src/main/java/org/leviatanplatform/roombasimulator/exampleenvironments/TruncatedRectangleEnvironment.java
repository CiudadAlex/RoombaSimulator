package org.leviatanplatform.roombasimulator.exampleenvironments;

import org.leviatanplatform.roombasimulator.engine.domain.Position;
import org.leviatanplatform.roombasimulator.exampleenvironments.parent.AbstractRangesEnvironment;
import org.leviatanplatform.roombasimulator.exampleenvironments.utils.HorizontalRange;
import org.leviatanplatform.roombasimulator.exampleenvironments.utils.VerticalRange;

import java.util.List;

public class TruncatedRectangleEnvironment extends AbstractRangesEnvironment {

    public TruncatedRectangleEnvironment(int rows, int columns, int truncationLength) {
        super(new Position(0, 0),
                buildListVerticalRange(rows, columns, truncationLength),
                buildListHorizontalRange(rows, columns, truncationLength));
    }

    private static List<VerticalRange> buildListVerticalRange(int rows, int columns, int truncationLength) {

        int rowLong = rows / 2;
        int columnLong = columns / 2;

        int rowShort = rowLong - truncationLength;
        int columnShort = columnLong - truncationLength;

        VerticalRange verticalRange1 = new VerticalRange(rowShort, -rowShort, -columnLong, -columnShort);
        VerticalRange verticalRange2 = new VerticalRange(rowLong, -rowLong, -columnShort, columnShort);
        VerticalRange verticalRange3 = new VerticalRange(rowShort, -rowShort, columnShort, columnLong);
        return List.of(verticalRange1, verticalRange2, verticalRange3);
    }

    private static List<HorizontalRange> buildListHorizontalRange(int rows, int columns, int truncationLength) {

        int rowLong = rows / 2;
        int columnLong = columns / 2;

        int rowShort = rowLong - truncationLength;
        int columnShort = columnLong - truncationLength;

        HorizontalRange horizontalRange1 = new HorizontalRange(rowLong, rowShort, -columnShort, columnShort);
        HorizontalRange horizontalRange2 = new HorizontalRange(rowShort, -rowShort, -columnLong, columnLong);
        HorizontalRange horizontalRange3 = new HorizontalRange(-rowShort, -rowLong, -columnShort, columnShort);
        return List.of(horizontalRange1, horizontalRange2, horizontalRange3);
    }
}
