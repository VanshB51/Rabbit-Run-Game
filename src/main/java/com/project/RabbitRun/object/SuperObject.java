package com.project.RabbitRun.object;

import com.project.RabbitRun.exceptions.ImageLoadingException;
import com.project.RabbitRun.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Represents a generic game object in the "Rabbit Run" game.
 * This class serves as a superclass for various objects that appear on the game board,
 * including rewards, punishments, and obstacles.
 */
public class SuperObject {
    /** Image representing this object. */
    protected BufferedImage image;
    /** Name of the object, typically used for identification. */
    protected String name;
    /** Path of the object Image */
    protected String path;
    /** Determines if the object has collision properties. */
    private boolean collision = false;
    /** X & Y -coordinates of the object in the game world. */
    private int worldX, worldY;
    /** Defines the object's solid area for collision detection. */
    protected Rectangle solidArea = new Rectangle(0, 0, 32, 32);
    /** Default X-coordinate of the solid area relative to the object. */
    private int solidAreaDefaultX = 0;
    /** Default Y-coordinate of the solid area relative to the object. */
    private int solidAreaDefaultY = 0;

    /**
     * Default constructor for creating a generic game object.
     *
     * This constructor initializes an instance of {@code SuperObject} without setting
     * any specific properties such as name or image path. It is typically used when
     * properties will be set later through setter methods.
     */
    public SuperObject() {}

    /**
     * Constructs a {@code SuperObject} with the specified name and image path.
     *
     * This constructor initializes the game object with a name for identification
     * and an image path to load its associated visual representation.
     *
     * @param name The name of the game object, typically used for identification.
     * @param path The path to the image resource representing the object.
     * @throws ImageLoadingException if the image cannot be loaded.
     */
    public SuperObject(String name, String path) {
        this.name = name;
        this.path = path;
        loadImage(path);
    }

    /**
     * Draws the object on the game screen if it is within the visible screen area.
     *
     * @param g2         the {@code Graphics2D} context used for drawing
     * @param gamePanel  the game panel containing the game state and player information
     */
    public void draw (Graphics2D g2, GamePanel gamePanel) {

        int screenX = worldX - gamePanel.player.getWorldX() + gamePanel.player.getScreenX();
        int screenY = worldY - gamePanel.player.getWorldY() + gamePanel.player.getScreenY();

        if (worldX + gamePanel.getTileSize() > gamePanel.player.getWorldX() - gamePanel.player.getScreenX() &&
            worldX - gamePanel.getTileSize() < gamePanel.player.getWorldX() + gamePanel.player.getScreenX() &&
            worldY + gamePanel.getTileSize() > gamePanel.player.getWorldY() - gamePanel.player.getScreenY() &&
            worldY - gamePanel.getTileSize() < gamePanel.player.getWorldY() + gamePanel.player.getScreenY()) {

            g2.drawImage(image, screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
        }
    }

    /**
     * Helper method to load an image and include the path in exceptions.
     *
     * @param path The path of the image to load.
     * @throws ImageLoadingException If the image cannot be loaded.
     */
    public void loadImage(String path) {
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        }  catch (NullPointerException e) {
            throw new ImageLoadingException("Image not found: " + path, e, path);
        } catch (IOException e) {
            throw new ImageLoadingException("Failed to load image: " + path, e, path);
        }
    }

    /**
     * Retrieves the name of the object.
     *
     * @return the name of the object
     */
    public String getName() { return name; }

    /**
     * Sets the name of the object.
     *
     * @param name the name to set
     */
    public void setName(String name) { this.name = name; }

    /**
     * Checks whether the object has collision properties.
     *
     * @return {@code true} if the object has collision properties, {@code false} otherwise
     */
    public boolean isCollision() { return collision; }

    /**
     * Sets whether the object has collision properties.
     *
     * @param collision {@code true} to enable collision, {@code false} to disable it
     */
    public void setCollision(boolean collision) { this.collision = collision; }

    /**
     * Retrieves the X-coordinate of the object in the game world.
     *
     * @return the X-coordinate of the object
     */
    public int getWorldX() { return worldX; }

    /**
     * Sets the X-coordinate of the object in the game world.
     *
     * @param worldX the X-coordinate to set
     */
    public void setWorldX(int worldX) { this.worldX = worldX; }

    /**
     * Retrieves the Y-coordinate of the object in the game world.
     *
     * @return the Y-coordinate of the object
     */
    public int getWorldY() { return worldY; }

    /**
     * Sets the Y-coordinate of the object in the game world.
     *
     * @param worldY the Y-coordinate to set
     */
    public void setWorldY(int worldY) { this.worldY = worldY; }

    /**
     * Retrieves the solid area rectangle of the object.
     *
     * @return the solid area of the object
     */
    public Rectangle getSolidArea() { return solidArea; }

    /**
     * Sets the solid area rectangle of the object.
     *
     * @param solidArea the solid area to set
     */
    public void setSolidArea(Rectangle solidArea) { this.solidArea = solidArea; }

    /**
     * Retrieves the X-coordinate of the solid area.
     *
     * @return the X-coordinate of the solid area
     */
    public int getSolidAreaX() { return solidArea.x; }

    /**
     * Sets the X-coordinate of the solid area.
     *
     * @param x the X-coordinate to set
     */
    public void setSolidAreaX(int x) { this.solidArea.x = x; }

    /**
     * Retrieves the Y-coordinate of the solid area.
     *
     * @return the Y-coordinate of the solid area
     */
    public int getSolidAreaY() { return solidArea.y; }

    /**
     * Sets the Y-coordinate of the solid area.
     *
     * @param y the Y-coordinate to set
     */
    public void setSolidAreaY(int y) { this.solidArea.y = y; }

    /**
     * Retrieves the default X-coordinate of the solid area relative to the object.
     *
     * @return the default X-coordinate of the solid area
     */
    public int getSolidAreaDefaultX() { return solidAreaDefaultX; }

    /**
     * Sets the default X-coordinate of the solid area relative to the object.
     *
     * @param solidAreaDefaultX the default X-coordinate to set
     */
    public void setSolidAreaDefaultX(int solidAreaDefaultX) { this.solidAreaDefaultX = solidAreaDefaultX; }

    /**
     * Retrieves the default Y-coordinate of the solid area relative to the object.
     *
     * @return the default Y-coordinate of the solid area
     */
    public int getSolidAreaDefaultY() { return solidAreaDefaultY; }

    /**
     * Sets the default Y-coordinate of the solid area relative to the object.
     *
     * @param solidAreaDefaultY the default Y-coordinate to set
     */
    public void setSolidAreaDefaultY(int solidAreaDefaultY) { this.solidAreaDefaultY = solidAreaDefaultY; }
}
