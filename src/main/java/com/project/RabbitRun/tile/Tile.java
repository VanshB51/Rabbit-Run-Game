package com.project.RabbitRun.tile;

import java.awt.image.BufferedImage;

/**
 * Represents a tile in the "Rabbit Run" game.
 * Each tile can have an image and a collision property
 * indicating if it can be passed through or not.
 */
public class Tile {
    /**
     * The image representing the visual appearance of the tile.
     */
    public BufferedImage image;

    /**
     * Determines whether the tile has collision properties.
     * {@code true} if the tile is solid and cannot be traversed;
     * {@code false} otherwise. Default is {@code false}.
     */
    public boolean collision = false;

    /**
     * default constructor for tile class
     */
    public Tile() {}
}
