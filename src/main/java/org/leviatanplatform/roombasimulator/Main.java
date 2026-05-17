package org.leviatanplatform.roombasimulator;

import org.leviatanplatform.roombasimulator.engine.Roomba;
import org.leviatanplatform.roombasimulator.engine.domain.Environment;
import org.leviatanplatform.roombasimulator.exampleenvironments.OpenEnvironment;
import org.leviatanplatform.roombasimulator.exampleenvironments.RectangleEnvironment;
import org.leviatanplatform.roombasimulator.exampleenvironments.TruncatedRectangleEnvironment;
import org.leviatanplatform.roombasimulator.visualizer.RoombaVisualizer;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        int w = 1000;
        int h = 800;
        int pixelScale = 15;
        int radius = 3;
        boolean dextroRotatory = false;

        Environment env = getEnvironment();

        Roomba roomba = new Roomba(radius, env, dextroRotatory);

        SwingUtilities.invokeLater(() -> {
            RoombaVisualizer roombaVisualizer = new RoombaVisualizer(roomba, w, h, pixelScale);
            roombaVisualizer.show();
        });
    }

    private static Environment getEnvironment() {
        Environment openEnvironment = new OpenEnvironment();
        Environment rectangleEnvironment = new RectangleEnvironment(30, 30);
        Environment truncatedRectangleEnvironment = new TruncatedRectangleEnvironment(30, 30, 7);
        return rectangleEnvironment;
    }
}
