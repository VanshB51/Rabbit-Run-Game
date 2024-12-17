package mainTest;

import com.project.RabbitRun.entity.Player;
import com.project.RabbitRun.main.GamePanel;
import com.project.RabbitRun.eventHandlers.KeyHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class CollisionCheckerTest {

    GamePanel gamePanel;
    KeyHandler keyHandler;
    Player player;

    @BeforeEach
    public void setUp() {
        // Initialize GamePanel and KeyHandler objects
        gamePanel = new GamePanel();
        keyHandler = new KeyHandler(gamePanel);
        player = new Player(gamePanel, keyHandler);
        // Set up any necessary state for the player or GamePanel
        player.setDefaultValues();
    }

    /**
     * tests collision detection with the checktile(entity) method with player right movement.
     * if the expected player movement if greater than the actual player movement then the player must have collided with a barrier and collision was successful
     */
    @Test
    public void testCollisionWithPlayerRightMovement()
    {
        //the starting position of the player character
        int initialX = player.getWorldX();

        //the expected change in the player position with 1000 keypresses
        int keyPresses = 1000;
        int worldX_change_with_1_keypress = player.getSpeed();
        int expected_change_WorldX = keyPresses*(worldX_change_with_1_keypress);

        //presses the right arrow key 1000 times and updates the player position
        for (int i = 0; i < keyPresses; i++)
        {
            keyHandler.setRightPressed(true);
            player.update();
        }

        //gets the new player position and the change in position
        int newWorldX = player.getWorldX();
        int change_in_world_X = newWorldX - initialX;

        //compares the actual change in position with expected change in position. if true then a barrier was hit and collision was successful
        assertTrue(change_in_world_X < expected_change_WorldX, "The actual change in worldX was greater than or equal to the expected change");
    }

    /**
     * tests collision detection with the checktile(entity) method with player left movement.
     * if the expected player movement if greater than the actual player movement then the player must have collided with a barrier and collision was successful
     */
    @Test
    public void testCollisionWithPlayerLeftMovement()
    {
        //the starting position of the player character
        int initialX = player.getWorldX();

        //the expected change in the player position with 1000 keypresses
        int keyPresses = 1000;
        int worldX_change_with_1_keypress = player.getSpeed();
        int expected_change_WorldX = keyPresses*(worldX_change_with_1_keypress);

        //presses the left arrow key 1000 times and updates the player position
        for (int i = 0; i < keyPresses; i++)
        {
            keyHandler.setLeftPressed(true);
            player.update();
        }

        //gets the new player position and the change in position
        int newWorldX = player.getWorldX();
        int change_in_world_X = initialX - newWorldX;

        //compares the actual change in position with expected change in position. if true then a barrier was hit and collision was successful
        assertTrue(change_in_world_X < expected_change_WorldX, "The actual change in worldX was greater than or equal to the expected change");
    }

    /**
     * tests collision detection with the checktile(entity) method with player up movement.
     * if the expected player movement if greater than the actual player movement then the player must have collided with a barrier and collision was successful
     */
    @Test
    public void testCollisionWithPlayerUpMovement()
    {
        //the starting position of the player character
        int initialY = player.getWorldY();

        //the expected change in the player position with 1000 keypresses
        int keyPresses = 1000;
        int worldY_change_with_1_keypress = player.getSpeed();
        int expected_change_WorldY = keyPresses*(worldY_change_with_1_keypress);

        //presses the up arrow key 1000 times and updates the player position
        for (int i = 0; i < keyPresses; i++)
        {
            keyHandler.setUpPressed(true);
            player.update();
        }

        //gets the new player position and the change in position
        int newWorldY = player.getWorldY();
        int change_in_world_Y = initialY - newWorldY;

        //compares the actual change in position with expected change in position. if true then a barrier was hit and collision was successful
        assertTrue(change_in_world_Y < expected_change_WorldY, "The actual change in worldY was greater than or equal to the expected change");
    }

    /**
     * tests collision detection with the checktile(entity) method with player down movement.
     * if the expected player movement if greater than the actual player movement then the player must have collided with a barrier and collision was successful
     */
    @Test
    public void testCollisionWithPlayerDownMovement()
    {
        //the starting position of the player character
        int initialY = player.getWorldY();

        //the expected change in the player position with 1000 keypresses
        int keyPresses = 1000;
        int worldY_change_with_1_keypress = player.getSpeed();
        int expected_change_WorldY = keyPresses*(worldY_change_with_1_keypress);

        //presses the down arrow key 1000 times and updates the player position
        for (int i = 0; i < keyPresses; i++)
        {
            keyHandler.setDownPressed(true);
            player.update();
        }

        //gets the new player position and the change in position
        int newWorldY = player.getWorldY();
        int change_in_world_Y = newWorldY - initialY;

        //compares the actual change in position with expected change in position. if true then a barrier was hit and collision was successful
        assertTrue(change_in_world_Y < expected_change_WorldY, "The actual change in worldY was greater than or equal to the expected change");
    }

}