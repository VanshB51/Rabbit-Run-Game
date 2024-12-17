package com.project.RabbitRun.object;

import com.project.RabbitRun.exceptions.ImageLoadingException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Represents the exit door object in the "Rabbit Run" game.
 * The door can either be open or closed, and its image changes accordingly.
 */
public class ObjExitDoor extends SuperObject{
    /** Indicates whether the exit door is open. */
    private boolean open;
    /** Image for the closed state of the door. */
    private BufferedImage closedImage;
    /** Image for the open state of the door. */
    private BufferedImage openImage;

    /**
     * Initializes the exit door with a specified state (open or closed).
     * Sets the appropriate image based on the door's state.
     *
     * @param open {@code true} if the door is open; {@code false} if closed
     */
    public ObjExitDoor(boolean open) {
        this.open = open;
        name = "ExitDoor";
        try {
            closedImage = loadImageExitDoor("/Exit/doorclosed.png");
            openImage = loadImageExitDoor("/Exit/dooropened.png");
            if(open) {
                image = openImage;
            }else {
                image = closedImage;
            }
        } catch (ImageLoadingException e) {
            System.err.println("Exception caught while trying to load Images in ObjExitDoor");
            throw e;
        }
    }

    /**
     * Helper method to load an image and include the path in exceptions.
     *
     * @param path The path of the image to load.
     * @return The loaded image.
     * @throws ImageLoadingException If the image cannot be loaded.
     */
    public BufferedImage loadImageExitDoor(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        } catch (NullPointerException e) {
            throw new ImageLoadingException("Image not found: " + path, e, path);
        } catch (IOException e) {
            throw new ImageLoadingException("Failed to load image: " + path, e, path);
        }
    }
}
