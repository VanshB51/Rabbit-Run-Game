package com.project.RabbitRun.collision;

import com.project.RabbitRun.entity.Entity;
import com.project.RabbitRun.main.GamePanel;


/**
 * Handles collision detection between entities and objects in the game.
 * Provides the index of a collided object or 999 if no collision occurs.
 */
public class CollisionCheckerObject {

    /** Reference to the main game panel, which contains game state and settings. */
    GamePanel gamePanel;

    /**
     * Constructs a CollisionChecker with a reference to the game panel.
     *
     * @param gamePanel the game panel containing game settings and state
     */
    public CollisionCheckerObject(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    /**
     * Checks if the specified entity collides with any objects in the game.
     *
     * @param entity the entity to check for object collision
     * @param player specifies if the entity is the player character
     * @return the index of the object that the entity collides with, or 999 if no collision occurs
     */
    public int checkObject (Entity entity, boolean player) {

        int index = 999;

        for (int i = 0; i < gamePanel.object.length; i++) {
            if (gamePanel.object[i] != null) {

                // Getting entity's solid area position
                entity.solidArea.x = entity.getWorldX() + entity.solidArea.x;
                entity.solidArea.y = entity.getWorldY() + entity.solidArea.y;

                // Getting object's solid area position
                gamePanel.object[i].setSolidAreaX(gamePanel.object[i].getWorldX() + gamePanel.object[i].getSolidAreaX());
                gamePanel.object[i].setSolidAreaY(gamePanel.object[i].getWorldY() + gamePanel.object[i].getSolidAreaY());

                switch (entity.getDirection()) {
                    case "up":
                        entity.solidArea.y -= entity.getSpeed();
                        if (entity.solidArea.intersects(gamePanel.object[i].getSolidArea())) {
                            if (gamePanel.object[i].isCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.getSpeed();
                        if (entity.solidArea.intersects(gamePanel.object[i].getSolidArea())) {
                            if (gamePanel.object[i].isCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.getSpeed();
                        if (entity.solidArea.intersects(gamePanel.object[i].getSolidArea())) {
                            if (gamePanel.object[i].isCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.getSpeed();
                        if (entity.solidArea.intersects(gamePanel.object[i].getSolidArea())) {
                            if (gamePanel.object[i].isCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                }
                // Reset solid area positions to defaults
                entity.solidArea.x = entity.getSolidAreaDefaultX();
                entity.solidArea.y = entity.getSolidAreaDefaultY();
                gamePanel.object[i].setSolidAreaX(gamePanel.object[i].getSolidAreaDefaultX());
                gamePanel.object[i].setSolidAreaY(gamePanel.object[i].getSolidAreaDefaultY());
            }
        }
        return index;
    }
}


