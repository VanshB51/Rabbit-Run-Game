package com.project.RabbitRun.assetHandler;

import com.project.RabbitRun.main.GamePanel;
import com.project.RabbitRun.object.ObjBonusReward;
import com.project.RabbitRun.object.ObjPunishment;
import com.project.RabbitRun.object.ObjReward;

/**
 * Responsible for initializing and placing game objects on the game board
 * in the "Rabbit Run" game. This includes rewards, bonus rewards, punishments,
 * and the exit door.
 */
public class AssetSetter {
    /** Reference to the main game panel, which contains the game state and objects. */
    GamePanel gamePanel;

    /**
     * Constructs an {@code AssetSetter} with a reference to the {@code GamePanel}.
     *
     * @param gamePanel the game panel to which objects will be added
     */
    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * Places various game objects at specific coordinates on the game board.
     * Objects include rewards, bonus rewards, and punishments, each assigned
     * a specific location to appear in the game.
     */
    public void setObject() {
        gamePanel.object[0] = new ObjReward();
        gamePanel.object[0].setWorldX(17 * gamePanel.getTileSize());
        gamePanel.object[0].setWorldY(9 * gamePanel.getTileSize());

        gamePanel.object[4] = new ObjReward();
        gamePanel.object[4].setWorldX(22 * gamePanel.getTileSize());
        gamePanel.object[4].setWorldY(16 * gamePanel.getTileSize());

        gamePanel.object[2] = new ObjBonusReward();

        gamePanel.object[3] = new ObjReward();
        gamePanel.object[3].setWorldX(14 * gamePanel.getTileSize());
        gamePanel.object[3].setWorldY(21 * gamePanel.getTileSize());

        gamePanel.object[1] = new ObjPunishment();
        gamePanel.object[1].setWorldX(16 * gamePanel.getTileSize());
        gamePanel.object[1].setWorldY(17 * gamePanel.getTileSize());

        gamePanel.object[6] = new ObjReward();
        gamePanel.object[6].setWorldX(23 * gamePanel.getTileSize());
        gamePanel.object[6].setWorldY(22 * gamePanel.getTileSize());

        gamePanel.object[7] = new ObjBonusReward();

        gamePanel.object[8] = new ObjReward();
        gamePanel.object[8].setWorldX(29 * gamePanel.getTileSize());
        gamePanel.object[8].setWorldY(32 * gamePanel.getTileSize());

        gamePanel.object[9] = new ObjPunishment();
        gamePanel.object[9].setWorldX(34 * gamePanel.getTileSize());
        gamePanel.object[9].setWorldY(27 * gamePanel.getTileSize());

        gamePanel.object[10] = new ObjPunishment();
        gamePanel.object[10].setWorldX(35 * gamePanel.getTileSize());
        gamePanel.object[10].setWorldY(20 * gamePanel.getTileSize());

        gamePanel.object[11] = new ObjBonusReward();

        gamePanel.object[12] = new ObjReward();
        gamePanel.object[12].setWorldX(21 * gamePanel.getTileSize());
        gamePanel.object[12].setWorldY(27 * gamePanel.getTileSize());

        gamePanel.object[13] = new ObjReward();
        gamePanel.object[13].setWorldX(38 * gamePanel.getTileSize());
        gamePanel.object[13].setWorldY(11 * gamePanel.getTileSize());

        gamePanel.object[14] = new ObjReward();
        gamePanel.object[14].setWorldX(29 * gamePanel.getTileSize());
        gamePanel.object[14].setWorldY(8 * gamePanel.getTileSize());

        gamePanel.object[15] = new ObjBonusReward();

        gamePanel.object[16] = new ObjBonusReward();

        gamePanel.object[17] = new ObjBonusReward();
    }
}
