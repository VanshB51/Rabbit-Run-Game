package com.project.RabbitRun.main;

import com.project.RabbitRun.assetHandler.AssetSetter;
import com.project.RabbitRun.collision.CollisionChecker;
import com.project.RabbitRun.collision.CollisionCheckerObject;
import com.project.RabbitRun.entity.Player;
import com.project.RabbitRun.entity.Enemy;
import com.project.RabbitRun.eventHandlers.KeyHandler;
import com.project.RabbitRun.eventHandlers.MouseListener;
import com.project.RabbitRun.object.SuperObject;
import com.project.RabbitRun.tile.TileManager;
import com.project.RabbitRun.ui.Sound;
import com.project.RabbitRun.ui.UI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main game panel that handles game rendering, updating, and main game loop.
 */
public class GamePanel extends JPanel implements Runnable {

    // Screen Settings
    /**
     * Original tile size before scaling.
     */
    final int originalTileSize = 16; // 32x32
    /**
     * Scaling factor for tile size.
     */
    final int scale = 3;
    /**
     * Scaled tile size.
     */
    private final int tileSize = originalTileSize * scale; // 64
    /**
     * Maximum number of columns on screen.
     */
    private final int maxScreenCol = 16;
    /**
     * Maximum number of rows on screen.
     */
    private final int maxScreenRow = 12;

    /**
     * Width of the screen in pixels.
     */
    private final int screenWidth = maxScreenCol * tileSize;
    /**
     * Height of the screen in pixels.
     */
    private final int screenHeight = maxScreenRow * tileSize;

    // World Settings
    /**
     * Maximum columns in the world map. Adjust to change map size.
     */
    private final int maxWorldCol = 50;
    /**
     * Maximum rows in the world map. Adjust to change map size.
     */
    private final int maxWorldRow = 40;


    // Game States
    /**
     * Current state of the game.
     */
    private int gameState;
    /**
     * Menu state identifier.
     */
    public final int menuState = 0;
    /**
     * Play state identifier.
     */
    public final int playState = 1;
    /**
     * Pause state identifier.
     */
    public final int pauseState = 2;
    /**
     * Winning state identifier.
     */
    public final int youWonState = 3;
    /**
     * Losing state identifier.
     */
    public final int youLostState = 4;
    /**
     * Guide state identifier.
     */
    public final int guideState = 5;

    /**
     * Frames per second target for game loop.
     */
    private final int FPS = 60;

    // System Components
    /**
     * Manages game tiles for rendering and collision.
     */
    public TileManager tileM = new TileManager(this);
    /**
     * Sets and manages asset objects.
     */
    public AssetSetter aSetter = new AssetSetter(this);
    /**
     * Array to hold game objects like items or obstacles.
     */
    public SuperObject[] object = new SuperObject[20];
    /**
     * Handles key inputs for player movement and actions.
     */
    KeyHandler keyHandler = new KeyHandler(this);
    /**
     * Handles mouse inputs in menu and other UI interactions.
     */
    MouseListener mouseListener = new MouseListener(this);
    /**
     * Checks for collisions between player, enemies.
     */
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    /**
     * Checks for collisions between player, objects.
     */
    public CollisionCheckerObject collisionCheckerObject = new CollisionCheckerObject(this);
    /**
     * Player character for the game.
     */
    public Player player = new Player(this, keyHandler);
    /**
     * Handles the game music and calls the sound class.
     */
    Sound gameMusic = new Sound();
    /**
     * Handles the object sound effects.
     */
    Sound soundEffect = new Sound();
    /**
     * List of enemies in the game.
     */
    public List<Enemy> enemies = new ArrayList<>();
    /**
     * User interface manager for game status and messages.
     */
    public UI ui = new UI(this);
    /**
     * Thread to run the game loop.
     */
    Thread gameThread;

    /**
     * Initializes the game panel with default settings.
     */
    public GamePanel() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        enemies = Enemy.initializeEnemies(this);
        this.addMouseListener(mouseListener);
    }

    /**
     * Sets up the initial state of the game, including assets and game state.
     */
    public void setupGame() {
        aSetter.setObject();
        playMusic(0);
        gameState = menuState;
    }

    /**
     * Restarts the game, resetting player, enemies, and other elements to default.
     */
    public void restartGame() {
        playMusic(0);
        player.restart();
        aSetter.setObject();
        ui.restart();
        enemies.forEach(Enemy::restart);
        gameState = playState;
    }

    /**
     * Starts the game thread to run the game loop.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Main game loop to control game updates and rendering.
     */
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        double lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    /**
     * Updates the game elements based on the current game state.
     */
    public void update() {
        if (gameState == playState) {
            player.update();
            // Update each enemy
            for (Enemy enemy : enemies) {
                enemy.updateEnemy(player);
            }
        }
    }

    /**
     * Paints and draws all game elements on the screen based on the game state.
     *
     * @param g the graphics object used to draw game elements
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw UI based on the game state
        if (gameState == menuState || gameState == youLostState || gameState == youWonState
                || gameState == pauseState || gameState == guideState) {
            ui.draw(g2);
        }

        // Draw game elements when in play state
        if (gameState == playState) {
            tileM.draw(g2);
            for (Enemy enemy : enemies) {
                enemy.draw(g2);
            }
            for (SuperObject superObject : object) {
                if (superObject != null) {
                    superObject.draw(g2, this);
                }
            }
            player.draw(g2);
            ui.draw(g2);
        }
        g2.dispose();
    }

    /**
     * Plays the background music specified by the given index.
     * The music will loop continuously.
     *
     * @param index the index of the music file to play
     */
    public void playMusic(int index) {
        gameMusic.setFile(index);
        gameMusic.play();
        gameMusic.loop();
    }

    /**
     * Stops the currently playing background music.
     */
    public void stopMusic() {
        gameMusic.stop();
    }

    /**
     * Plays a sound effect specified by the given index.
     *
     * @param index the index of the sound effect file to play
     */
    public void playSoundEffect(int index) {
        soundEffect.setFile(index);
        soundEffect.play();
    }

    /**
     * Returns the width of the screen.
     *
     * @return the screen width.
     */
    public int getScreenWidth() {
        return screenWidth;
    }

    /**
     * Returns the size of a tile.
     *
     * @return the tile size.
     */
    public int getTileSize() {
        return tileSize;
    }

    /**
     * Returns the height of the screen.
     *
     * @return the screen height.
     */
    public int getScreenHeight() {
        return screenHeight;
    }

    /**
     * Returns the maximum number of columns in the world.
     *
     * @return the maximum number of columns.
     */
    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    /**
     * Returns the maximum number of rows in the world.
     *
     * @return the maximum number of rows.
     */
    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    /**
     * Returns the current game state.
     *
     * @return the current game state.
     */
    public int getGameState() {
        return gameState;
    }

    /**
     * Sets the game state to a new value.
     *
     * @param gameState the new game state value to set.
     */
    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    /**
     * Retrieves the current game thread.
     *
     * @return the {@link Thread} instance representing the game thread.
     */
    public Thread getGameThread() {
        return gameThread;
    }

    /**
     * Sets the game thread to the specified thread.
     *
     * @param gameThread the {@link Thread} instance to set as the game thread.
     */
    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
    }

}