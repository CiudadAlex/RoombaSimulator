package org.leviatanplatform.roombasimulator.engine;

import org.leviatanplatform.roombasimulator.engine.domain.Environment;
import org.leviatanplatform.roombasimulator.engine.domain.ScalableChart;

public class Roomba {

    private final Environment env;
    private ScalableChart chart;

    public Roomba(Environment env) {
        this.env = env;
        this.chart = new ScalableChart();
    }

    public void explore() {
        // FIXME finish
    }

    private void exploreStraightLineUntilWall() {
        // FIXME finish
    }
}
