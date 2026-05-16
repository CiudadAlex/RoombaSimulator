package org.leviatanplatform.roombasimulator.visualizer;

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
        // FIXME implement
    }

    private void down() {
        // FIXME implement
    }

    private void left() {
        // FIXME implement
    }

    private void right() {
        // FIXME implement
    }

    private void zoomIn() {
        // FIXME implement
    }

    private void zoomOut() {
        // FIXME implement
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
