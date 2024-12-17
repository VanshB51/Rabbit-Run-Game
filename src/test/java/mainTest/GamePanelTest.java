package mainTest;

import com.project.RabbitRun.main.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@code GamePanel} class.
 * This class tests the initialization, game state management, and other core functionalities
 * of the {@code GamePanel}.
 */
public class GamePanelTest {

    private GamePanel gamePanel;

    /**
     * Sets up the test environment by initializing a {@code GamePanel} instance.
     * This method runs before each test to ensure a clean slate.
     */
    @BeforeEach
    public void setUp() {
        gamePanel = new GamePanel();
    }

    /**
     * Tests the proper initialization of the {@code GamePanel}, including game state,
     * screen settings, and essential components.
     */
    @Test
    public void testInitialization() {
        // Test initial game state
        assertEquals(gamePanel.menuState, gamePanel.getGameState(), "Game should start in the menu state.");

        // Test screen settings
        assertEquals(768, gamePanel.getScreenWidth(), "Screen width should be correctly set.");
        assertEquals(576, gamePanel.getScreenHeight(), "Screen height should be correctly set.");

        // Test that essential components are initialized
        assertNotNull(gamePanel.player, "Player should be initialized.");
        assertNotNull(gamePanel.enemies, "Enemies list should be initialized.");
    }

    /**
     * Tests changing the game state to ensure that state transitions are handled correctly.
     */
    @Test
    public void testGameStateChange() {
        // Change to play state
        gamePanel.setGameState(gamePanel.playState);
        assertEquals(gamePanel.playState, gamePanel.getGameState(), "Game state should be set to play state.");

        // Change to pause state
        gamePanel.setGameState(gamePanel.pauseState);
        assertEquals(gamePanel.pauseState, gamePanel.getGameState(), "Game state should be set to pause state.");
    }

    /**
     * Tests the initialization and functionality of the game thread to ensure it starts
     * correctly and is alive after starting.
     */
    @Test
    public void testStartGameThread() {
        // Start the game thread
        gamePanel.startGameThread();
        assertNotNull(gamePanel.getGameThread(), "Game thread should be initialized after starting.");
        assertTrue(gamePanel.getGameThread().isAlive(), "Game thread should be alive after starting.");
    }

    /**
     * Tests the game setup method to ensure the game initializes with the correct
     * state and components, including objects in the game world.
     */
    @Test
    public void testGameSetUp() {
        gamePanel.setupGame();
        gamePanel.stopMusic();
        assertEquals(gamePanel.menuState, gamePanel.getGameState(), "Game should start in the menu state.");
        assertNotNull(gamePanel.object[0], "Objects in the game world should be initialized.");
    }
}
