package mainTest;

import com.project.RabbitRun.main.GamePanel;
import com.project.RabbitRun.eventHandlers.MouseListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.MouseEvent;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@code mouseListener} class.
 * This class tests the behavior of the mouse listener in handling
 * mouse interactions with buttons in the {@code GamePanel}.
 */
public class MouseListenerTest {

    private GamePanel gamePanel;
    private MouseListener mouseListener;

    /**
     * Sets up the test environment by initializing a {@code GamePanel} instance
     * and associating it with a {@code mouseListener}.
     * This method runs before each test to ensure a fresh setup.
     */
    @BeforeEach
    public void setUp() {
        gamePanel = new GamePanel();
        mouseListener = new MouseListener(gamePanel);
    }

    /**
     * Tests whether clicking the guide button in the menu state changes the game state
     * to {@code guideState}.
     */
    @Test
    public void testGuideButtonClickInMenuState() {
        // Set initial game state to menuState
        gamePanel.setGameState(gamePanel.menuState);

        // Simulate mouse click within guide button bounds
        MouseEvent clickEvent = new MouseEvent(gamePanel, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, 60, 270, 1, false);
        mouseListener.mousePressed(clickEvent);

        // Verify that the game state changes to guideState
        assertEquals(gamePanel.guideState, gamePanel.getGameState(), "Game state should be set to guideState after clicking guide button");
    }

    /**
     * Tests whether clicking the start button in the menu state changes the game state
     * to {@code playState}.
     */
    @Test
    public void testStartButtonClickInMenuState() {
        // Set initial game state to menuState
        gamePanel.setGameState(gamePanel.menuState);

        // Simulate mouse click within start button bounds
        MouseEvent clickEvent = new MouseEvent(gamePanel, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, 310, 270, 1, false);
        mouseListener.mousePressed(clickEvent);

        // Verify that the game state changes to playState
        assertEquals(gamePanel.playState, gamePanel.getGameState(), "Game state should be set to playState after clicking start button");
    }

    /**
     * Tests whether clicking the play again button in the {@code youLostState}
     * changes the game state to {@code playState}.
     */
    @Test
    public void testPLayAgainButtonClickInYouLostState() {
        // Set initial game state to youLostState
        gamePanel.setGameState(gamePanel.youLostState);

        // Simulate mouse click within play again button bounds
        MouseEvent clickEvent = new MouseEvent(gamePanel, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, 130, 520, 1, false);
        mouseListener.mousePressed(clickEvent);

        // Verify that the game state changes to playState
        assertEquals(gamePanel.playState, gamePanel.getGameState(), "Game state should be set to playState after clicking play again button");
    }
}
