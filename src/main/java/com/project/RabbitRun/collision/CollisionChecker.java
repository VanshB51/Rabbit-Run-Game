package com.project.RabbitRun.collision;

import com.project.RabbitRun.entity.Entity;
import com.project.RabbitRun.main.GamePanel;

/**
 * Handles collision detection in the "Rabbit Run" game, checking if entities
 * collide with tiles or objects on the game board.
 */
public class CollisionChecker {

    /** Reference to the main game panel, which contains game state and settings. */
    GamePanel gamePanel;

    /**
     * Constructs a CollisionChecker with a reference to the game panel.
     *
     * @param gamePanel the game panel containing game settings and state
     */
    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * Checks if the specified entity collides with any tiles based on its direction and position.
     *
     * @param entity the entity (e.g., player or enemy) to check for tile collision
     */
    public void checkTile(Entity entity)
    {
        /** the left coordinate of an entity solid area */
        int entityLeftworldX = entity.getWorldX() + entity.solidArea.x;
        /**
         * the right coordinate of an entity solid area
         */
        int entityRightworldX = entity.getWorldX() + entity.solidArea.x + entity.solidArea.width;
        /**
         * the top coordinate of an entity solid area
         */
        int entityTopWorldY = entity.getWorldY() + entity.solidArea.y;
        /**
         * the bottom coordinate of an entity solid area
         */
        int entityBottomWorldY = entity.getWorldY() + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftworldX/gamePanel.getTileSize();
        int entityRightCol = entityRightworldX/gamePanel.getTileSize();
        int entityTopRow = entityTopWorldY/gamePanel.getTileSize();
        int entityBottomRow = entityBottomWorldY/gamePanel.getTileSize();

        int tileNum1;
        int tileNum2;

        switch(entity.getDirection()) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.getSpeed())/gamePanel.getTileSize();
                tileNum1 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gamePanel.tileM.tile[tileNum1].collision || gamePanel.tileM.tile[tileNum2].collision)
                {
                    entity.setCollisionOn(true);
                }

                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed())/gamePanel.getTileSize();
                tileNum1 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gamePanel.tileM.tile[tileNum1].collision || gamePanel.tileM.tile[tileNum2].collision)
                {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftworldX - entity.getSpeed())/gamePanel.getTileSize();
                tileNum1 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gamePanel.tileM.tile[tileNum1].collision || gamePanel.tileM.tile[tileNum2].collision)
                {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightworldX + entity.getSpeed())/gamePanel.getTileSize();
                tileNum1 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gamePanel.tileM.tile[tileNum1].collision || gamePanel.tileM.tile[tileNum2].collision)
                {
                    entity.setCollisionOn(true);
                }
                break;
        }
    }
}
