package org.leviatanplatform.roombasimulator;

import org.leviatanplatform.roombasimulator.engine.Roomba;
import org.leviatanplatform.roombasimulator.engine.domain.Environment;
import org.leviatanplatform.roombasimulator.exampleenvironments.RectangleEnvironment;
import org.leviatanplatform.roombasimulator.visualizer.RoombaVisualizer;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        int w = 1000;
        int h = 800;
        int pixelScale = 5;
        int radius = 3;
        boolean dextroRotatory = false;

        Environment env = new RectangleEnvironment(30, 30);
        Roomba roomba = new Roomba(radius, env, dextroRotatory);
        roomba.explore();

        SwingUtilities.invokeLater(() -> {
            RoombaVisualizer roombaVisualizer = new RoombaVisualizer(roomba, w, h, pixelScale);
            roombaVisualizer.show();
        });
    }

    // FIXME finish
}
