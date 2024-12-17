package mainTest;

import com.project.RabbitRun.main.GamePanel;
import com.project.RabbitRun.eventHandlers.KeyHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@code KeyHandler} class.
 * This class tests the functionality of keyboard input handling, including key presses
 * and releases, and game state toggles triggered by specific keys.
 */
public class KeyHandlerTest {

    private GamePanel gamePanel;
    private KeyHandler keyHandler;

    /**
     * Sets up the test environment by initializing a {@code GamePanel} instance
     * and associating it with a {@code KeyHandler}.
     * This method runs before each test to ensure a clean slate.
     */
    @BeforeEach
    public void setUp() {
        gamePanel = new GamePanel();
        keyHandler = new KeyHandler(gamePanel);
    }

    /**
     * Tests the behavior of the {@code keyPressed} method by simulating key presses
     * for movement and game state toggling.
     */
    @Test
    public void testKeyPressed() {
        // Simulate pressing the up key
        KeyEvent upKeyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        keyHandler.keyPressed(upKeyEvent);
        assertTrue(keyHandler.isUpPressed(), "Up key should be pressed");

        // Simulate pressing the down key
        KeyEvent downKeyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S');
        keyHandler.keyPressed(downKeyEvent);
        assertTrue(keyHandler.isDownPressed(), "Down key should be pressed");

        // Simulate pressing the pause key
        gamePanel.setGameState(gamePanel.playState);
        KeyEvent pauseKeyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_P, 'P');
        keyHandler.keyPressed(pauseKeyEvent);
        assertEquals(gamePanel.pauseState, gamePanel.getGameState(), "Game should be in pause state after pressing 'P'");
    }

    /**
     * Tests the behavior of the {@code keyReleased} method to ensure keys are correctly
     * marked as released after being pressed.
     */
    @Test
    public void testKeyReleased() {
        // Simulate pressing and releasing the right key
        keyHandler.keyPressed(new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D'));
        keyHandler.keyReleased(new KeyEvent(gamePanel, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D'));
        assertFalse(keyHandler.isRightPressed(), "Right key should be released");
    }

    /**
     * Tests toggling between play and pause states using the pause key ('P').
     */
    @Test
    public void testPauseToggle() {
        // Test initial play to pause toggle
        gamePanel.setGameState(gamePanel.playState);
        KeyEvent pauseKeyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_P, 'P');
        keyHandler.keyPressed(pauseKeyEvent);
        assertEquals(gamePanel.pauseState, gamePanel.getGameState(), "Game state should toggle to pause");

        // Test pause to play toggle
        keyHandler.keyPressed(pauseKeyEvent);
        assertEquals(gamePanel.playState, gamePanel.getGameState(), "Game state should toggle to play");
    }

    /**
     * Tests switching from the guide state back to the menu state when the 'B' key is pressed.
     */
    @Test
    public void testGuideStateKeyPress() {
        // Set game state to guide and press 'B'
        gamePanel.setGameState(gamePanel.guideState);
        KeyEvent guideKeyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_B, 'B');
        keyHandler.keyPressed(guideKeyEvent);
        assertEquals(gamePanel.menuState, gamePanel.getGameState(), "Game state should switch to menu when 'B' is pressed in guide state");
    }
}
