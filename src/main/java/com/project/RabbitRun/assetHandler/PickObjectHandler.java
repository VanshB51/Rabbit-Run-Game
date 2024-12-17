package com.project.RabbitRun.assetHandler;

import com.project.RabbitRun.entity.Player;
import com.project.RabbitRun.main.GamePanel;

import java.awt.*;

/**
 * Handles interactions with in-game objects during gameplay.
 *
 * This class is responsible for managing the logic when the player interacts
 * with various objects such as Clover, Carrot, Mushroom, or ExitDoor.
 * It defines behaviors like collecting rewards, handling penalties, and determining
 * game progression based on the player's actions.
 */
public class PickObjectHandler {

    private final GamePanel gamePanel;
    private final Player player;

    /**
     * Constructs a PickObjectHandler with the specified GamePanel and Player.
     *
     * @param gamePanel the main game panel containing game state and UI elements
     * @param player    the player object interacting with the game objects
     */
    public PickObjectHandler(GamePanel gamePanel, Player player) {
        this.gamePanel = gamePanel;
        this.player = player;
    }

    /**
     * Handles picking up an object based on its index.
     *
     * @param index the index of the object in the gamePanel's object array
     */
    public void pickObject(int index) {
        if (index == 999) return;

        String objName = gamePanel.object[index].getName();

        switch (objName) {
            case "Clover" -> handleClover(index);
            case "Carrot" -> handleCarrot(index);
            case "Mushroom" -> handleMushroom(index);
            case "ExitDoor" -> handleExitDoor();
            default -> throw new IllegalArgumentException("Unknown object type: " + objName);
        }
    }

    /**
     * Handles the collection of a "Clover" object.
     * Plays a sound effect, increments the clover count, adds points,
     * and displays a reward message. If the player meets the winning
     * conditions, a special sound effect is played.
     *
     * @param index the index of the Clover object in the gamePanel's object array
     */
    private void handleClover(int index) {
        gamePanel.playSoundEffect(1);
        player.incrementClover();
        gamePanel.object[index] = null;
        player.addPoints(50);
        gamePanel.ui.showMessage("YOU GOT A REWARD!", Color.green);

        if (player.isWinningConditionMet()) {
            gamePanel.playSoundEffect(4);
        }
    }

    /**
     * Handles the collection of a "Carrot" object.
     * Plays a reward sound effect, increments the carrot count,
     * adds bonus points, removes the object, and displays a bonus message.
     *
     * @param index the index of the Carrot object in the gamePanel's object array
     */
    private void handleCarrot(int index) {
        gamePanel.playSoundEffect(2);
        player.incrementCarrot();
        gamePanel.object[index] = null;
        player.addPoints(100);
        gamePanel.ui.showMessage("YOU GOT A BONUS REWARD!", Color.green);
    }

    /**
     * Handles the collection of a "Mushroom" object.
     * Plays a penalty sound effect, reduces points, removes the object,
     * and displays a warning message indicating a poison mushroom was found.
     *
     * @param index the index of the Mushroom object in the gamePanel's object array
     */
    private void handleMushroom(int index) {
        gamePanel.playSoundEffect(3);
        gamePanel.object[index] = null;
        player.subtractPoints(100);
        gamePanel.ui.showMessage("YOU FOUND A POISON MUSHROOM!", Color.red);
    }

    /**
     * Handles interactions with the "ExitDoor" object.
     * Determines if the player has enough points and clovers to win:
     * - If conditions are met, stops the music, plays a winning sound,
     *   and transitions the game to the "You Won" state.
     * - If not enough points, displays a message indicating more points are needed.
     * - If not enough clovers, displays the number of clovers still required.
     */
    private void handleExitDoor() {
        if (player.isWinningConditionMet()) {
            gamePanel.stopMusic();
            gamePanel.playSoundEffect(6);
            gamePanel.setGameState(gamePanel.youWonState);
        } else if (player.getPoints() < player.getWinningPoints()) {
            gamePanel.ui.showMessage("YOU NEED MORE POINTS TO WIN!", Color.red);
        } else {
            int remainingClover = player.getWinningClovers() - player.getHasClover();
            gamePanel.ui.showMessage("YOU NEED " + remainingClover + " MORE CLOVERS TO EXIT!", Color.red);
        }
    }
}
