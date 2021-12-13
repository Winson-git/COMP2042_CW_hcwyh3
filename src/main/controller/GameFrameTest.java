package main.controller;

import main.controller.GameFrame.*;
import static org.junit.jupiter.api.Assertions.*;

class GameFrameTest {

    GameFrame var = new GameFrame();

    @org.junit.jupiter.api.Test
    void initialize() {
        assertEquals("Pass", var.initialize());
    }

    @org.junit.jupiter.api.Test
    void enableGameBoard() {
        assertEquals("Pass", var.enableGameBoard());
    }

    @org.junit.jupiter.api.Test
    void enableHomeScreen() {
        assertEquals("Pass", var.enableGameBoard());
    }

    @org.junit.jupiter.api.Test
    void enableInfo() {
        assertEquals("Pass", var.enableInfo());
    }
}