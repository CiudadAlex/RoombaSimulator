package org.leviatanplatform.roombasimulator;

import org.leviatanplatform.roombasimulator.engine.Roomba;
import org.leviatanplatform.roombasimulator.engine.domain.Environment;
import org.leviatanplatform.roombasimulator.exampleenvironments.*;
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

    // FIXME consider possible wall always even cleaning (throw exception, paint wall and resume task)

    private static Environment getEnvironment() {
        Environment openEnv = new OpenEnvironment();
        Environment rectEnv = new RectangleEnvironment(30, 30);
        Environment truncEnv = new TruncatedRectangleEnvironment(30, 30, 7);
        Environment truncIslandEnv = new TruncatedRectangleWithIslandEnvironment(30, 30, 7, 6);
        Environment truncIslandEnvBig = new TruncatedRectangleWithIslandEnvironment(100, 100, 10, 10);
        Environment truncSomeIslandsEnvBig = new TruncatedRectangleWithSomeIslandsEnvironment(100, 100, 10, 10);
        return truncSomeIslandsEnvBig;
    }
}
