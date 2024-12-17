package entityTest;

import com.project.RabbitRun.assetHandler.PickObjectHandler;
import com.project.RabbitRun.entity.Player;
import com.project.RabbitRun.object.*;
import com.project.RabbitRun.main.GamePanel;
import com.project.RabbitRun.eventHandlers.KeyHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@code Player} class.
 * This class tests the functionality of the {@code Player} class, including movement,
 * interaction with objects, and state changes.
 */
public class PlayerTest {

    private GamePanel gamePanel;
    private KeyHandler keyHandler;
    private Player player;
    private PickObjectHandler pickObjectHandler;

    /**
     * Sets up the test environment by initializing the {@code GamePanel},
     * {@code KeyHandler}, and {@code Player} instances. Mock objects are also
     * prepared in the {@code GamePanel}.
     */
    @BeforeEach
    public void setUp() {
        // Initialize GamePanel and KeyHandler objects
        gamePanel = new GamePanel();

        keyHandler = new KeyHandler(gamePanel);
        player = new Player(gamePanel, keyHandler);
        this.pickObjectHandler = new PickObjectHandler(gamePanel, player);

        // Mock objects in the gamePanel
        gamePanel.object = new SuperObject[10];
        gamePanel.object[0] = new ObjReward();
        gamePanel.object[1] = new ObjBonusReward();
        gamePanel.object[2] = new ObjPunishment();
        gamePanel.object[3] = new ObjExitDoor(true);

        // Set up any necessary state for the player or GamePanel
        player.setDefaultValues();
    }

    /**
     * Tests if the {@code Player} object is initialized with the correct default values.
     */
    @Test
    public void testPlayerInitialization() {
        // Check if player is initialized correctly
        assertNotNull(player, "Player should be initialized");
        assertEquals(gamePanel.getTileSize() * 10, player.getWorldX(), "Player's initial X position should be correct");
        assertEquals(gamePanel.getTileSize() * 6, player.getWorldY(), "Player's initial Y position should be correct");
        assertEquals(4,player.getSpeed(), "Player's initial speed should be 4");

    }

    /**
     * Verifies the {@code Player}'s movement in response to directional key presses.
     */
    @Test
    public void testPlayerMovement() {
        // Simulate pressing the right arrow key
        keyHandler.setRightPressed(true);
        int initialX = player.getWorldX();

        // Update the player state
        player.update();

        // Verify the player moved to the right
        assertTrue(player.getWorldX() > initialX, "Player should move to the right when the right key is pressed");

        // Reset keys and move left
        keyHandler.setRightPressed(false);
        keyHandler.setLeftPressed(true);
        initialX = player.getWorldX();
        player.update();
        assertTrue(player.getWorldX() < initialX, "Player should move to the left when the left key is pressed");

        keyHandler.setLeftPressed(false);
        keyHandler.setUpPressed(true);
        int initialY = player.getWorldY();
        player.update();
        assertTrue(player.getWorldY() < initialY, "Player should move to the north when the up key is pressed");

        keyHandler.setUpPressed(false);
        keyHandler.setDownPressed(true);
        initialY = player.getWorldY();
        player.update();
        assertTrue(player.getWorldY() > initialY, "Player should move to the south when the down key is pressed");

    }

    /**
     * Ensures that the {@code Player}'s state is properly reset when the restart method is called.
     */
    @Test
    public void testPlayerRestart() {
        // Simulate the player collecting items and gaining points
        player.setPoints(100);
        player.setHasClover(3);
        player.setHasCarrot(2);

        // Restart the player
        player.restart();

        // Verify player state is reset
        assertEquals(0, player.getPoints(), "Player points should be reset to 0");
        assertEquals(0, player.getHasClover(), "Player clover count should be reset to 0");
        assertEquals(0, player.getHasCarrot(), "Player carrot count should be reset to 0");
    }

    /**
     * Verifies that loading player images does not throw any exceptions.
     */
    @Test
    void testPlayerImageLoading() {
        assertDoesNotThrow(() -> player.getPlayerImage(), "Loading player images should not throw an exception");
    }

    /**
     * Tests that the {@code Player}'s direction changes correctly when corresponding keys are pressed.
     */
    @Test
    public void testDirectionChange() {
        keyHandler.setRightPressed(true);
        player.update();
        assertEquals("right", player.getDirection(), "Player's direction should be 'right' when right key is pressed");

        keyHandler.setRightPressed(false);
        keyHandler.setLeftPressed(true);
        player.update();
        assertEquals("left", player.getDirection(), "Player's direction should be 'left' when left key is pressed");
    }

    /**
     * Tests that picking up a clover increments the clover count, awards points,
     * and removes the clover from the game.
     */
    @Test
    void testPickClover() {
        pickObjectHandler.pickObject(0);
        assertEquals(1, player.getHasClover(), "Clover count should increment");
        assertEquals(50, player.getPoints(), "Points should increase by 50");
        assertNull(gamePanel.object[0], "Clover should be removed from objects");
    }

    /**
     * Tests that picking up a carrot increments the carrot count, awards points,
     * and removes the carrot from the game.
     */
    @Test
    void testPickCarrot() {
        gamePanel.object[1] = new ObjBonusReward();
        pickObjectHandler.pickObject(1);

        assertEquals(1, player.getHasCarrot(), "Carrot count should increment");
        assertEquals(100, player.getPoints(), "Points should increase by 100");
        assertNull(gamePanel.object[1], "Carrot should be removed from objects");
    }

    /**
     * Tests that picking up a mushroom decreases points and removes the mushroom from the game.
     */
    @Test
    void testPickMushroom() {
        player.setPoints(200);
        pickObjectHandler.pickObject(2);

        assertEquals(100, player.getPoints(), "Points should decrease by 100");
        assertNull(gamePanel.object[2], "Mushroom should be removed from objects");
    }

    /**
     * Verifies that the game state changes to "you won" when the player interacts with
     * the exit door with sufficient points and clovers.
     */
    @Test
    void testExitDoorWin() {
        player.setPoints(500);
        player.setHasClover(8);
        gamePanel.playMusic(0);
        pickObjectHandler.pickObject(3);
        assertEquals(gamePanel.youWonState, gamePanel.getGameState(), "Game state should change to 'you won'");
    }

    /**
     * Tests the scenario where the player interacts with the exit door but has insufficient points.
     */
    @Test
    void testExitDoorInsufficientPoints() {
        player.setPoints(300);
        player.setHasClover(8);
        pickObjectHandler.pickObject(3);

         assertEquals("YOU NEED MORE POINTS TO WIN!", gamePanel.ui.getMessage());
    }

    /**
     * Tests the scenario where the player interacts with the exit door but has insufficient clovers.
     */
    @Test
    void testExitDoorInsufficientClovers() {
        player.setHasClover(5);
        player.setPoints(500);
        pickObjectHandler.pickObject(3);
         assertEquals("YOU NEED 3 MORE CLOVERS TO EXIT!", gamePanel.ui.getMessage());
    }

    /**
     * Ensures no changes occur when the player interacts with an invalid object index.
     */
    @Test
    void testInvalidIndex() {
        pickObjectHandler.pickObject(999);

        // Ensure nothing changes for invalid index.
        assertEquals(0, player.getPoints(), "Points should remain unchanged");
        assertEquals(0, player.getHasClover(), "Clover count should remain unchanged");
        assertEquals(0, player.getHasCarrot(), "Carrot count should remain unchanged");
    }

    /**
     * Tests that the game state changes to "you lost" when the player's points are negative.
     */
    @Test
    void testNegativePoints(){
        player.setPoints(-100);
        gamePanel.playMusic(0);
        player.update();
        assertEquals(gamePanel.youLostState, gamePanel.getGameState(), "Game state should change to 'you lost'");
    }

    /**
     * Verifies that the exit door opens when the player has sufficient points and clovers.
     */
    @Test
    void testExitDoorWithSufficientConditions() {
        player.setPoints(400);
        player.setHasClover(8);
        player.update();
        assertEquals(player.getOpenDoor(),gamePanel.object[5],"Door must be opened");
    }

}
