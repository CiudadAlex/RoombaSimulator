package org.leviatanplatform.roombasimulator;

import org.leviatanplatform.roombasimulator.engine.Roomba;
import org.leviatanplatform.roombasimulator.engine.domain.Environment;
import org.leviatanplatform.roombasimulator.exampleenvironments.RectangleEnvironment;

public class Main {

    public static void main(String[] args) {

        int radius = 3;
        boolean dextroRotatory = false;

        Environment env = new RectangleEnvironment(30, 30);
        Roomba roomba = new Roomba(radius, env, dextroRotatory);
        roomba.explore();
    }

    // FIXME finish
}
