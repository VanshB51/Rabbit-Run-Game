package com.project.RabbitRun.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Represents an entity within the game, including attributes for
 * position, speed, directional images, and collision handling.
 */
public class Entity {

    /**
     * default constructor for tile class
     */
    public Entity() {}

    /** X-coordinate of the entity's position in the game world. */
    protected int worldX;

    /** Y-coordinate of the entity's position in the game world. */
    protected int worldY;

    /** Speed at which the entity moves. */
    protected int speed;

    /** Image for moving up (first frame). */
    public BufferedImage up1;

    /** Image for moving down (first frame). */
    public BufferedImage down1;

    /** Image for moving left (first frame). */
    protected BufferedImage left1;

    /** Image for moving right (first frame). */
    protected BufferedImage right1;

    /** Image for moving up (second frame). */
    protected BufferedImage up2;

    /** Image for moving down (second frame). */
    protected BufferedImage down2;

    /** Image for moving left (second frame). */
    protected BufferedImage left2;

    /** Image for moving right (second frame). */
    protected BufferedImage right2;

    /** Current direction the entity is facing, such as "up", "down", "left", or "right". */
    protected String direction;

    /** Counter for managing sprint animations. */
    protected int sprintCounter = 0;

    /** Current frame of the sprite animation (either 1 or 2). */
    protected int spriteNumber = 1;

    /** Rectangle representing the solid area for collision detection. */
    public Rectangle solidArea;

    /** Default X-coordinate of the solid area's position. */
    protected int solidAreaDefaultX;

    /** Default Y-coordinate of the solid area's position. */
    protected int solidAreaDefaultY;

    /** Indicates whether a collision is currently occurring. */
    protected boolean collisionOn = false;

    /**
     * Retrieves the X-coordinate of the object's position in the game world.
     *
     * @return the X-coordinate of the object in the game world.
     */
    public int getWorldX() {
        return worldX;
    }

    /**
     * Sets the X-coordinate of the object's position in the game world.
     *
     * @param worldX the X-coordinate to set for the object in the game world.
     */
    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    /**
     * Retrieves the Y-coordinate of the object's position in the game world.
     *
     * @return the Y-coordinate of the object in the game world.
     */
    public int getWorldY() {
        return worldY;
    }

    /**
     * Sets the Y-coordinate of the object's position in the game world.
     *
     * @param worldY the Y-coordinate to set for the object in the game world.
     */
    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    /**
     * Retrieves the movement speed of the object.
     *
     * @return the speed of the object.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets the movement speed of the object.
     *
     * @param speed the speed to set for the object.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Retrieves the current direction of the object.
     *
     * @return the current direction as a {@code String}.
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Sets the current direction of the object.
     *
     * @param direction the direction to set, represented as a {@code String}.
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * Retrieves the solid area of the object used for collision detection.
     *
     * @return a {@code Rectangle} representing the object's solid area.
     */
    public Rectangle getSolidArea() {
        return solidArea;
    }

    /**
     * Sets the solid area of the object used for collision detection.
     *
     * @param solidArea a {@code Rectangle} representing the solid area to set.
     */
    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    /**
     * Retrieves the default X-coordinate of the solid area's position.
     *
     * @return the default X-coordinate of the solid area.
     */
    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    /**
     * Sets the default X-coordinate of the solid area's position.
     *
     * @param solidAreaDefaultX the default X-coordinate to set for the solid area.
     */
    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }

    /**
     * Retrieves the default Y-coordinate of the solid area's position.
     *
     * @return the default Y-coordinate of the solid area.
     */
    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    /**
     * Sets the default Y-coordinate of the solid area's position.
     *
     * @param solidAreaDefaultY the default Y-coordinate to set for the solid area.
     */
    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }

    /**
     * Checks whether collision detection is currently enabled for the object.
     *
     * @return {@code true} if collision detection is enabled; {@code false} otherwise.
     */
    public boolean isCollisionOn() {
        return collisionOn;
    }

    /**
     * Enables or disables collision detection for the object.
     *
     * @param collisionOn {@code true} to enable collision detection; {@code false} to disable it.
     */
    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    /**
     * Returns the bounding rectangle of the entity.
     *
     * @return The Rectangle representing the entity's bounds.
     */
    public Rectangle getBounds() {
        return new Rectangle(worldX + solidArea.x, worldY + solidArea.y, solidArea.width, solidArea.height);
    }

}
