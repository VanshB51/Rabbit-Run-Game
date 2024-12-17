package tileTest;

import com.project.RabbitRun.main.GamePanel;
import com.project.RabbitRun.tile.TileManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileManagerTest {

    GamePanel gamePanel;
    TileManager tileManager;


    @BeforeEach
    public void setUp() {
        gamePanel = new GamePanel();
        tileManager = new TileManager(gamePanel);
    }

    @Test
    /** tests loadmap() method in the tile manager class.
     * if the method works correctly the mapTileNum array will contain the value 1 at every index
     */
    void testloadMap() {
        tileManager.loadMap("/Maps/testmap01.txt");
        for(int i = 0; i < gamePanel.getMaxWorldCol(); i++)
        {
            for(int j = 0; j < gamePanel.getMaxWorldRow(); j++)
            {
                assertTrue(tileManager.mapTileNum[i][j] == 1, "The values in" +
                        " the mapTileNum array" +
                        "are all 1 as read from the input file");
            }
        }
    }


    @Test
    /**
     * tests loadmap() method in the tile manager class with a different map with different data
     * if the method works correctly the mapTileNum array will contain the value 0 at every index
     */
    void testloadMap2() {
        tileManager.loadMap("/Maps/testmap02.txt");
        for(int i = 0; i < gamePanel.getMaxWorldCol(); i++)
        {
            for(int j = 0; j < gamePanel.getMaxWorldRow(); j++)
            {
                assertTrue(tileManager.mapTileNum[i][j] == 0, "The values in" +
                        " the mapTileNum array" +
                        "are all 0 as read from the input file");
            }
        }
    }

    /**
     * tests whether gettileimage() was successful in opening the tile images
     */
    @Test
    void testgetTileImage()
    {
        tileManager.getTileImage();
        assertNotNull(tileManager.tile[0]);
        assertNotNull(tileManager.tile[1]);
    }



}