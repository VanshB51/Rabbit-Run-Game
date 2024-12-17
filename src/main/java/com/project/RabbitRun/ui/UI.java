package com.project.RabbitRun.ui;

import com.project.RabbitRun.exceptions.ImageLoadingException;
import com.project.RabbitRun.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * The UI class is responsible for rendering all the user interface components in the RabbitRun game.
 * It includes the main menu, play state, pause state, win, and lose screens, as well as the points and messages display.
 */
public class UI {
    private final GamePanel gamePanel;

    private final Font openSans;
    private final Font ariel;

    private BufferedImage points;
    private BufferedImage clover;
    private BufferedImage menuPage;
    private BufferedImage guidePage;
    private BufferedImage youWonPage;
    private BufferedImage youLostPage;

    private Color messageColor = Color.WHITE;
    private boolean dispMessage = false;
    private String message = "";
    private int messageTimer = 0;

    private long startTime;
    private long endTime;

    private final Map<Integer, Consumer<Graphics>> stateDrawActions = new HashMap<>();

    /**
     * Constructs the UI, initializing fonts and loading images for the menu, win, and lose screens.
     *
     * @param gamePanel The GamePanel instance that this UI is part of.
     */
    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        openSans = new Font("Open Sans", Font.BOLD, 20);
        ariel = new Font("Ariel", Font.BOLD, 15);
        loadUIImages();
        setupStateDrawActions();
    }

    private void loadUIImages() {
        try {
            points = loadImage("/OnScreenIcons/XP.png");
            clover = loadImage("/Reward/clover.png");
            menuPage = loadImage("/States/MenuPage.png");
            guidePage = loadImage("/States/Guide.png");
            youLostPage = loadImage("/States/YOULOST.png");
            youWonPage = loadImage("/States/YOUWON.png");
        }catch (ImageLoadingException e) {
            System.err.println("Exception caught while trying to load Images in UI");
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
    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        } catch (NullPointerException e) {
            throw new ImageLoadingException("Image not found: " + path, e, path);
        } catch (IOException e) {
            throw new ImageLoadingException("Failed to load image: " + path, e, path);
        }
    }

    private void setupStateDrawActions() {
        stateDrawActions.put(gamePanel.menuState, this::drawMenuState);
        stateDrawActions.put(gamePanel.playState, this::drawPlayState);
        stateDrawActions.put(gamePanel.pauseState, this::drawPauseState);
        stateDrawActions.put(gamePanel.youWonState, this::drawYouWonState);
        stateDrawActions.put(gamePanel.youLostState, this::drawYouLostState);
        stateDrawActions.put(gamePanel.guideState, this::drawGuideState);
    }

    private void drawPlayerStats(Graphics g2) {
        g2.drawImage(points, gamePanel.getTileSize() / 2, gamePanel.getTileSize() / 2, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
        g2.drawString("x " + gamePanel.player.getPoints(), 74, 60);
        g2.drawImage(clover, gamePanel.getScreenWidth() / 2 - 60, gamePanel.getTileSize() / 2, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
        g2.drawString("x " + gamePanel.player.getHasClover() + " / 8", gamePanel.getScreenWidth() / 2, 60);
        long elapsedTime = (System.currentTimeMillis() - startTime) / 1000; // Convert to seconds
        g2.drawString("Time Elapsed: " + elapsedTime + "s", gamePanel.getTileSize() * 12, 60);
        endTime = elapsedTime;
    }

    /**
     * Draws a temporary message on the screen, if set.
     *
     * @param g2 The Graphics object used for drawing.
     */
    private void displayMessage(Graphics g2) {
        if (dispMessage) {
            g2.setFont(g2.getFont().deriveFont(15f));
            g2.setColor(messageColor);
            g2.drawString(message, gamePanel.getTileSize() / 2, gamePanel.getTileSize() * 6);
            messageTimer++;
            if (messageTimer > 70) {
                messageTimer = 0;
                dispMessage = false;
            }
        }
    }

    /**
     * Handles the placement and removal of bonus objects based on elapsed time.
     */
    private void handleBonusObjects() {
        long elapsedTime = endTime;

        if (elapsedTime == 5) {
            gamePanel.object[2].setWorldX(24 * gamePanel.getTileSize());
            gamePanel.object[2].setWorldY(6 * gamePanel.getTileSize());
        }
        if (elapsedTime == 20) {
            gamePanel.object[2] = null;
        }
        if (elapsedTime == 15) {
            gamePanel.object[7].setWorldX(11 * gamePanel.getTileSize());
            gamePanel.object[7].setWorldY(33 * gamePanel.getTileSize());
        }
        if (elapsedTime == 30) {
            gamePanel.object[7] = null;
        }
        if (elapsedTime == 25) {
            gamePanel.object[11].setWorldX(38 * gamePanel.getTileSize());
            gamePanel.object[11].setWorldY(8 * gamePanel.getTileSize());
        }
        if (elapsedTime == 40) {
            gamePanel.object[11] = null;
        }
        if (elapsedTime == 35) {
            gamePanel.object[15].setWorldX(25 * gamePanel.getTileSize());
            gamePanel.object[15].setWorldY(12 * gamePanel.getTileSize());
        }
        if (elapsedTime == 50) {
            gamePanel.object[15] = null;
        }
        if (elapsedTime == 45) {
            gamePanel.object[16].setWorldX(32 * gamePanel.getTileSize());
            gamePanel.object[16].setWorldY(18 * gamePanel.getTileSize());
        }
        if (elapsedTime == 60) {
            gamePanel.object[16] = null;
        }
        if (elapsedTime == 55) {
            gamePanel.object[17].setWorldX(20 * gamePanel.getTileSize());
            gamePanel.object[17].setWorldY(33 * gamePanel.getTileSize());
        }
        if (elapsedTime == 70) {
            gamePanel.object[17] = null;
        }
    }

    /**
     * Displays a temporary message on the screen in the specified color.
     *
     * @param message The message to display.
     * @param color The color of the message text.
     */
    public void showMessage(String message, Color color) {
        this.message = message;
        dispMessage = true;
        this.messageColor = color;
        messageTimer = 0;
    }

    /**
     * Draws the UI components based on the current game state.
     *
     * @param g2 The Graphics object used for drawing.
     */
    public void draw(Graphics g2) {
        g2.setFont(openSans);
        g2.setColor(Color.white);
        Consumer<Graphics> drawAction = stateDrawActions.get(gamePanel.getGameState());
        if (drawAction != null) {
            drawAction.accept(g2);
        }
    }

    /**
     * Draws the menu screen.
     *
     * @param g2 The Graphics object used for drawing.
     */
    public void drawMenuState(Graphics g2) {
        g2.drawImage(menuPage, 0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight(), null);
    }

    /**
     * Draws the Guide screen.
     *
     * @param g2 The Graphics object used for drawing.
     */
    public void drawGuideState(Graphics g2) {
        g2.drawImage(guidePage, 0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight(), null);
    }
    /**
     * Draws the play screen, including the player's points and elapsed time.
     *
     * @param g2 The Graphics object used for drawing.
     */
    public void drawPlayState(Graphics g2) {
        drawPlayerStats(g2);
        displayMessage(g2);
        handleBonusObjects();
    }

    /**
     * Draws the pause screen, displaying "PAUSED" and instructions to resume the game.
     *
     * @param g2 The Graphics object used for drawing.
     */
    public void drawPauseState(Graphics g2) {
        drawPlayerStats(g2);
        drawPauseMessage(g2);
    }

    /**
     * Draws the "PAUSED" message with instructions to resume.
     *
     * @param g2 The Graphics object used for drawing.
     */
    private void drawPauseMessage(Graphics g2) {
        String message = "PAUSED";
        String toUnPause = "Press 'P' to unpause";
        int x = getScreenCentreX(message, g2);
        int y = gamePanel.getScreenHeight() / 2;
        g2.setFont(g2.getFont().deriveFont(40f));
        g2.drawString(message, x, y);

        g2.setFont(ariel);
        g2.drawString(toUnPause, x + 5, y + 25);
    }

    /**
     * Draws the "You Won" screen, displaying the player's points and the total elapsed time.
     *
     * @param g2 The Graphics object used for drawing.
     */
    public void drawYouWonState(Graphics g2) {
        g2.drawImage(youWonPage, 0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight(), null);
        int x = getScreenCentreX("h", g2);
        g2.drawImage(points, x - 48, 221, gamePanel.getTileSize(), gamePanel.getTileSize(), null);

        g2.setFont(g2.getFont().deriveFont(25f));
        g2.drawString("x " + gamePanel.player.getPoints(), x - 48 + gamePanel.getTileSize(), 221 + 38);
        g2.drawString("Time Elapsed: " + endTime + "s", getScreenCentreX("Time Elapsed: " + endTime + "s", g2) + 90, 221 + 38 + gamePanel.getTileSize());
    }

    /**
     * Draws the "You Lost" screen, displaying the player's points and the total elapsed time.
     *
     * @param g2 The Graphics object used for drawing.
     */
    public void drawYouLostState(Graphics g2) {
        g2.drawImage(youLostPage, 0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight(), null);
        int x = getScreenCentreX("h", g2);
        g2.drawImage(points, x - 48, 221, gamePanel.getTileSize(), gamePanel.getTileSize(), null);

        g2.setFont(g2.getFont().deriveFont(25f));
        g2.drawString("x " + gamePanel.player.getPoints(), x - 48 + gamePanel.getTileSize(), 221 + 38);
        g2.drawString("Time Elapsed: " + endTime + "s", getScreenCentreX("Time Elapsed: " + endTime + "s", g2) + 90, 221 + 38 + gamePanel.getTileSize());
    }

    /**
     * Calculates the x-coordinate to center a given string horizontally on the screen.
     *
     * @param s The string to center.
     * @param g2 The Graphics object used for calculating string width.
     * @return The x-coordinate to center the string.
     */
    public int getScreenCentreX(String s, Graphics g2) {
        int length = (int) g2.getFontMetrics().getStringBounds(s, g2).getWidth();
        return gamePanel.getScreenWidth() / 2 - length;
    }

    /**
     * Resets the UI for a new game, resetting the start time and clearing messages.
     */
    public void restart() {
        startTime = System.currentTimeMillis();
        dispMessage = false;
        message = "";
        messageTimer = 0;
    }

    /**
     * Checks whether a message is currently set to be displayed.
     *
     * @return {@code true} if a message is set to be displayed; {@code false} otherwise.
     */
    public boolean isDispMessage() {
        return dispMessage;
    }

    /**
     * Sets whether a message should be displayed.
     *
     * @param dispMessage {@code true} to display a message; {@code false} otherwise.
     */
    public void setDispMessage(boolean dispMessage) {
        this.dispMessage = dispMessage;
    }

    /**
     * Retrieves the current message to be displayed.
     *
     * @return the message as a {@code String}.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message to be displayed.
     *
     * @param message the message as a {@code String}.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Retrieves the color of the message to be displayed.
     *
     * @return the {@code Color} of the message.
     */
    public Color getMessageColor() {
        return messageColor;
    }

    /**
     * Sets the color of the message to be displayed.
     *
     * @param messageColor the {@code Color} to set for the message.
     */
    public void setMessageColor(Color messageColor) {
        this.messageColor = messageColor;
    }

    /**
     * Retrieves the timer value for the message display duration.
     *
     * @return the message timer as an {@code int}.
     */
    public int getMessageTimer() {
        return messageTimer;
    }

    /**
     * Sets the timer value for the message display duration.
     *
     * @param messageTimer the duration in milliseconds as an {@code int}.
     */
    public void setMessageTimer(int messageTimer) {
        this.messageTimer = messageTimer;
    }

    /**
     * Retrieves the image representing points in the game.
     *
     * @return a {@code BufferedImage} representing the points image.
     */
    public BufferedImage getPointsImage() {
        return points;
    }

    /**
     * Retrieves the image representing a clover in the game.
     *
     * @return a {@code BufferedImage} representing the clover image.
     */
    public BufferedImage getCloverImage() {
        return clover;
    }

    /**
     * Retrieves the image for the menu page.
     *
     * @return a {@code BufferedImage} representing the menu page image.
     */
    public BufferedImage getMenuPageImage() {
        return menuPage;
    }

    /**
     * Retrieves the image for the guide page.
     *
     * @return a {@code BufferedImage} representing the guide page image.
     */
    public BufferedImage getGuidePageImage() {
        return guidePage;
    }

    /**
     * Retrieves the image for the "You Won" page.
     *
     * @return a {@code BufferedImage} representing the "You Won" page image.
     */
    public BufferedImage getYouWonPageImage() {
        return youWonPage;
    }

    /**
     * Retrieves the image for the "You Lost" page.
     *
     * @return a {@code BufferedImage} representing the "You Lost" page image.
     */
    public BufferedImage getYouLostPageImage() {
        return youLostPage;
    }

    /**
     * Retrieves the start time.
     *
     * @return the start time as a long value, typically representing a timestamp
     *         in milliseconds since the epoch.
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time.
     *
     * @param startTime the start time to set, typically as a timestamp in
     *                  milliseconds since the epoch.
     */
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    /**
     * Retrieves the end time.
     *
     * @return the end time as a long value, typically representing a timestamp
     *         in milliseconds since the epoch.
     */
    public long getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time.
     *
     * @param endTime the end time to set, typically as a timestamp in
     *                milliseconds since the epoch.
     */
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

}
