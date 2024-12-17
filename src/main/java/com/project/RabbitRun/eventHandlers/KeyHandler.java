package com.project.RabbitRun.eventHandlers;

import com.project.RabbitRun.main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The KeyHandler class handles keyboard inputs for controlling player movement and game state changes.
 * It listens for key events and updates corresponding movement flags based on the keys pressed or released.
 */
public class KeyHandler implements KeyListener {

    /** Flags indicating the direction keys' states (pressed or released) */
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    /** Reference to the main GamePanel to access and modify game state */
    GamePanel gamePanel;

    /**
     * Constructs a KeyHandler with the specified GamePanel.
     * Initializes the direction flags to false.
     *
     * @param gamePanel the GamePanel instance that this KeyHandler controls
     */
    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.upPressed = false;
        this.downPressed = false;
        this.leftPressed = false;
        this.rightPressed = false;
    }

    /**
     * Invoked when a key is typed. This implementation is empty as typed events are not used.
     *
     * @param e the KeyEvent
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    /**
     * Invoked when a key is pressed. Updates movement flags and handles pause/unpause functionality.
     *
     * @param e the KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            upPressed = true;
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            downPressed = true;
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            leftPressed = true;
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }

        // Toggle between play and pause states when 'P' is pressed
        if (key == KeyEvent.VK_P) {
            gamePanel.playSoundEffect(5);
            if (gamePanel.getGameState() == gamePanel.playState) {
                gamePanel.setGameState(gamePanel.pauseState);
            } else if (gamePanel.getGameState() == gamePanel.pauseState) {
                gamePanel.setGameState(gamePanel.playState);
            }
        }
        if(gamePanel.getGameState() == gamePanel.guideState) {
            if(key == KeyEvent.VK_B) {
                gamePanel.playSoundEffect(5);
                gamePanel.setGameState(gamePanel.menuState);
            }
        }
    }

    /**
     * Invoked when a key is released. Updates movement flags accordingly.
     *
     * @param e the KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            upPressed = false;
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            downPressed = false;
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            leftPressed = false;
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }

    /**
     * Returns whether the up key is pressed.
     *
     * @return true if the up key is pressed, false otherwise.
     */
    public boolean isUpPressed() {
        return upPressed;
    }

    /**
     * Returns whether the down key is pressed.
     *
     * @return true if the down key is pressed, false otherwise.
     */
    public boolean isDownPressed() {
        return downPressed;
    }

    /**
     * Returns whether the left key is pressed.
     *
     * @return true if the left key is pressed, false otherwise.
     */
    public boolean isLeftPressed() {
        return leftPressed;
    }

    /**
     * Returns whether the right key is pressed.
     *
     * @return true if the right key is pressed, false otherwise.
     */
    public boolean isRightPressed() {
        return rightPressed;
    }

    /**
     * Sets the state of the "up" button.
     *
     * @param upPressed true if the "up" button is pressed, false otherwise
     */
    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }

    /**
     * Sets the state of the "down" button.
     *
     * @param downPressed true if the "down" button is pressed, false otherwise
     */
    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }

    /**
     * Sets the state of the "left" button.
     *
     * @param leftPressed true if the "left" button is pressed, false otherwise
     */
    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

    /**
     * Sets the state of the "right" button.
     *
     * @param rightPressed true if the "right" button is pressed, false otherwise
     */
    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }

}
