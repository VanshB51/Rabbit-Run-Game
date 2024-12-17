package com.project.RabbitRun.main;

import javax.swing.*;

/**
 * The Main class initializes and displays the main window for the RabbitRun game.
 * It sets up the JFrame window with basic configurations such as title, close operation, and size.
 * A GamePanel is added to the window to manage and render game content.
 */
public class Main {

    /**
     * default constructor for tile class
     */
    public Main() {}

    /**
     * The main entry point for the RabbitRun game application.
     * It creates the game window, configures its settings, and starts the game.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // Create the main game window with the title "RabbitRun"
        JFrame window = new JFrame("RabbitRun");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit the application when the window is closed
        window.setResizable(false); // Make the window non-resizable

        // Create a new instance of GamePanel to manage game components and add it to the window
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); // Adjust the window size to fit the preferred size of the GamePanel

        // Center the window on the screen
        window.setLocationRelativeTo(null);
        window.setVisible(true); // Make the window visible

        // Set up the initial game state and start the game loop
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
