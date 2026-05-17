package org.leviatanplatform.roombasimulator.exampleenvironments;

import org.leviatanplatform.roombasimulator.engine.domain.Position;
import org.leviatanplatform.roombasimulator.exampleenvironments.parent.AbstractRangesEnvironment;
import org.leviatanplatform.roombasimulator.exampleenvironments.utils.HorizontalRange;
import org.leviatanplatform.roombasimulator.exampleenvironments.utils.VerticalRange;

import java.util.List;

public class RectangleEnvironment extends AbstractRangesEnvironment {

    public RectangleEnvironment(int rows, int columns) {
        super(new Position(0, 0),
                buildListVerticalRange(rows, columns),
                buildListHorizontalRange(rows, columns));
    }

    public static List<VerticalRange> buildListVerticalRange(int rows, int columns) {

        int halfRows = rows / 2;
        int halfColumns = columns / 2;
        VerticalRange verticalRange = new VerticalRange(halfRows, -halfRows, -halfColumns, halfColumns);
        return List.of(verticalRange);
    }

    public static List<HorizontalRange> buildListHorizontalRange(int rows, int columns) {

        int halfRows = rows / 2;
        int halfColumns = columns / 2;
        HorizontalRange horizontalRange = new HorizontalRange(halfRows, -halfRows, -halfColumns, halfColumns);
        return List.of(horizontalRange);
    }
}
