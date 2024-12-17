package mainTest;

import com.project.RabbitRun.main.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.project.RabbitRun.assetHandler.AssetSetter;
import com.project.RabbitRun.object.ObjBonusReward;
import com.project.RabbitRun.object.ObjPunishment;
import com.project.RabbitRun.object.ObjReward;

/**
 * Unit tests for the {@link AssetSetter} class.
 * This class ensures that game objects are properly initialized and placed on the game board.
 */
public class AssetSetterTest {

    private GamePanel gamePanel;
    private AssetSetter assetSetter;

    /**
     * Sets up the test environment by initializing the {@link GamePanel}
     * and {@link AssetSetter} instances before each test case.
     */
    @BeforeEach
    public void setUp() {
        gamePanel = new GamePanel();
        assetSetter = new AssetSetter(gamePanel);
    }

    /**
     * Verifies that the {@link AssetSetter#setObject()} method correctly
     * places objects of the expected types in the {@link GamePanel#object} array.
     */
    @Test
    public void testSetObjectPlacesCorrectTypes() {
        assetSetter.setObject();

        // Check that specific indices in the object array contain the expected object types
        assertInstanceOf(ObjReward.class, gamePanel.object[0], "First object should be an ObjReward");
        assertInstanceOf(ObjPunishment.class, gamePanel.object[1], "Second object should be an ObjPunishment");
        assertInstanceOf(ObjBonusReward.class, gamePanel.object[2], "Third object should be an ObjBonusReward");
        assertInstanceOf(ObjReward.class, gamePanel.object[3], "Fourth object should be an ObjReward");
    }

    /**
     * Verifies that the {@link AssetSetter#setObject()} method places objects
     * at the correct coordinates on the game board.
     */
    @Test
    public void testSetObjectPlacesCorrectCoordinates() {
        assetSetter.setObject();

        // Verify object coordinates
        assertEquals(17 * gamePanel.getTileSize(), gamePanel.object[0].getWorldX(), "ObjReward X coordinate should match");
        assertEquals(9 * gamePanel.getTileSize(), gamePanel.object[0].getWorldY(), "ObjReward Y coordinate should match");

        assertEquals(16 * gamePanel.getTileSize(), gamePanel.object[1].getWorldX(), "ObjPunishment X coordinate should match");
        assertEquals(17 * gamePanel.getTileSize(), gamePanel.object[1].getWorldY(), "ObjPunishment Y coordinate should match");

        assertEquals(0, gamePanel.object[2].getWorldX(), "ObjBonusReward X coordinate should match");
        assertEquals(0, gamePanel.object[2].getWorldY(), "ObjBonusReward Y coordinate should match");

        assertEquals(14 * gamePanel.getTileSize(), gamePanel.object[3].getWorldX(), "ObjReward X coordinate should match");
        assertEquals(21 * gamePanel.getTileSize(), gamePanel.object[3].getWorldY(), "ObjReward Y coordinate should match");
    }

    /**
     * Ensures that the {@link AssetSetter#setObject()} method does not exceed the maximum
     * number of allowed objects in the {@link GamePanel#object} array.
     */
    @Test
    public void testSetObjectDoesNotExceedMaxObjects() {
        assetSetter.setObject();

        // Verify that only the allocated array length is used
        assertEquals(20, gamePanel.object.length, "GamePanel object array should have a maximum of 20 slots");
        for (int index = 18; index < gamePanel.object.length; index++) {
            assertNull(gamePanel.object[index], "Remaining slots in the object array should be null");
        }
    }

    /**
     * Verifies that the {@link AssetSetter#setObject()} method initializes
     * all required objects in the {@link GamePanel#object} array.
     */
    @Test
    public void testObjectsAreNotNull() {
        assetSetter.setObject();

        // Ensure objects are initialized
        for (int index = 0; index < 15; index++) {
            if (index == 5)
                continue;
            assertNotNull(gamePanel.object[index], "Object at index " + index + " should not be null after initialization");
        }
    }
}
