package mainTest;

import com.project.RabbitRun.main.GamePanel;
import com.project.RabbitRun.ui.UI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@code UI} class in the RabbitRun game.
 * This class tests various functionalities of the {@code UI} class,
 * including displaying messages, setting start times, image loading,
 * and resetting UI elements.
 */
class UITest {
    private UI ui;
    private GamePanel gamePanel;

    /**
     * Sets up the test environment by initializing a {@code GamePanel} instance
     * and a {@code UI} instance associated with it. This method runs before each test.
     */
    @BeforeEach
    void setUp() {
        gamePanel = new GamePanel();
        ui = new UI(gamePanel);
    }

    /**
     * Tests the {@code showMessage} method in the {@code UI} class.
     * Verifies that the message, message color, and message timer are set correctly.
     */
    @Test
    void testShowMessage() {

        String testMessage = "Test Message";
        Color testColor = Color.RED;

        ui.showMessage(testMessage, testColor);

        // Verify that the message and color are correctly set
        assertEquals("Test Message", ui.getMessage());
        assertTrue(ui.isDispMessage());
        assertEquals(Color.RED, ui.getMessageColor());
        assertEquals(0, ui.getMessageTimer());
    }

    /**
     * Tests the {@code setStartTime} method in the {@code UI} class.
     * Verifies that the start time is set correctly.
     */
    @Test
    void testSetStartTime() {
        long expectedStartTime = System.currentTimeMillis();

        ui.setStartTime(expectedStartTime);

        // Verify that the start time is set correctly
        assertEquals(expectedStartTime, ui.getStartTime());
    }

    /**
     * Tests the image loading functionality in the {@code UI} class.
     * Verifies that all necessary images can be loaded without exceptions.
     */
    @Test
    public void testImageLoading() {
        // Verify that images load without throwing exceptions
        assertDoesNotThrow(() -> ui.getCloverImage(), "Loading clover image should not throw an exception");
        assertDoesNotThrow(() -> ui.getGuidePageImage(), "Loading guide page image should not throw an exception");
        assertDoesNotThrow(() -> ui.getMenuPageImage(), "Loading menu page image should not throw an exception");
        assertDoesNotThrow(() -> ui.getPointsImage(), "Loading points image should not throw an exception");
        assertDoesNotThrow(() -> ui.getYouWonPageImage(), "Loading you won page image should not throw an exception");
        assertDoesNotThrow(() -> ui.getYouLostPageImage(), "Loading you lost image should not throw an exception");
    }

    /**
     * Tests the {@code restart} method in the {@code UI} class.
     * Verifies that UI elements such as message timer, display message, and message text are reset correctly.
     */
    @Test
    public void testRestart() {
        ui.restart();

        // Verify that the UI is reset after restart
        assertEquals(0, ui.getMessageTimer(), "Message timer must be reset to 0");
        assertFalse(ui.isDispMessage(), "Display message flag must be reset to false");
        assertEquals("", ui.getMessage(), "Message must be reset to an empty string");
    }
}
