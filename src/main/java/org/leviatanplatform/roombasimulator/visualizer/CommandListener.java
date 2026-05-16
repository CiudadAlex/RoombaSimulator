package org.leviatanplatform.roombasimulator.visualizer;

import org.leviatanplatform.roombasimulator.engine.domain.Movement;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CommandListener extends KeyAdapter {

    private RoombaVisualizer roombaVisualizer;

    public CommandListener(RoombaVisualizer roombaVisualizer) {
        this.roombaVisualizer = roombaVisualizer;
    }

    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP -> up();
            case KeyEvent.VK_DOWN -> down();
            case KeyEvent.VK_LEFT -> left();
            case KeyEvent.VK_RIGHT -> right();
            case KeyEvent.VK_2 -> zoomIn();
            case KeyEvent.VK_1 -> zoomOut();
            case KeyEvent.VK_H -> help();
        }
    }

    private void up() {
        roombaVisualizer.moveRoomba(Movement.UP);
    }

    private void down() {
        roombaVisualizer.moveRoomba(Movement.DOWN);
    }

    private void left() {
        roombaVisualizer.moveRoomba(Movement.LEFT);
    }

    private void right() {
        roombaVisualizer.moveRoomba(Movement.RIGHT);
    }

    private void zoomIn() {
        roombaVisualizer.zoom(1);
    }

    private void zoomOut() {
        roombaVisualizer.zoom(-1);
    }

    private void help() {

        System.out.println("====================================================");
        System.out.println();
        System.out.println("Useful keys:");
        System.out.println(" - Arrows to navigate the roomba");
        System.out.println(" - H: help");
        System.out.println();
        System.out.println("====================================================");
    }

}
