package objectTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.project.RabbitRun.object.SuperObject;

import java.awt.*;

/**
 * Unit tests for the {@link SuperObject} class.
 * These tests validate the behavior of various getters, setters,
 * and initial values for fields in the SuperObject class.
 */
public class SuperObjectTest {
    private SuperObject superObject;

    /**
     * Sets up the test environment by initializing the {@link SuperObject} instance
     * before each test case.
     */
    @BeforeEach
    public void setUp() {
        superObject = new SuperObject();
    }

    /**
     * Verifies that the name of the object can be set and retrieved correctly.
     */
    @Test
    public void testNameSetterGetter() {
        superObject.setName("TestObject");
        assertEquals("TestObject", superObject.getName(), "The name should be correctly set and retrieved");
    }

    /**
     * Verifies that the collision property can be set and retrieved correctly.
     */
    @Test
    public void testCollisionSetterGetter() {
        assertFalse(superObject.isCollision(), "Default collision should be false");
        superObject.setCollision(true);
        assertTrue(superObject.isCollision(), "Collision should be true after setting it to true");
    }

    /**
     * Verifies that the world coordinates (X and Y) can be set and retrieved correctly.
     */
    @Test
    public void testWorldCoordinatesSetterGetter() {
        superObject.setWorldX(100);
        superObject.setWorldY(200);
        assertEquals(100, superObject.getWorldX(), "World X should be correctly set and retrieved");
        assertEquals(200, superObject.getWorldY(), "World Y should be correctly set and retrieved");
    }

    /**
     * Verifies that the solid area can be set and retrieved correctly.
     */
    @Test
    public void testSolidAreaSetterGetter() {
        Rectangle testArea = new Rectangle(10, 10, 50, 50);
        superObject.setSolidArea(testArea);
        assertEquals(testArea, superObject.getSolidArea(), "Solid area should be correctly set and retrieved");
    }

    /**
     * Verifies that the X and Y coordinates of the solid area can be set and retrieved correctly.
     */
    @Test
    public void testSolidAreaCoordinatesSetterGetter() {
        superObject.setSolidAreaX(15);
        superObject.setSolidAreaY(25);
        assertEquals(15, superObject.getSolidAreaX(), "Solid area X should be correctly set and retrieved");
        assertEquals(25, superObject.getSolidAreaY(), "Solid area Y should be correctly set and retrieved");
    }

    /**
     * Verifies that the default X and Y coordinates of the solid area can be set and retrieved correctly.
     */
    @Test
    public void testDefaultSolidAreaCoordinatesSetterGetter() {
        superObject.setSolidAreaDefaultX(5);
        superObject.setSolidAreaDefaultY(10);
        assertEquals(5, superObject.getSolidAreaDefaultX(), "Default solid area X should be correctly set and retrieved");
        assertEquals(10, superObject.getSolidAreaDefaultY(), "Default solid area Y should be correctly set and retrieved");
    }

    /**
     * Verifies the initial values of all fields in the {@link SuperObject} class.
     */
    @Test
    public void testInitialValues() {
        assertNull(superObject.getName(), "Name should initially be null");
        assertFalse(superObject.isCollision(), "Collision should initially be false");
        assertEquals(0, superObject.getWorldX(), "World X should initially be 0");
        assertEquals(0, superObject.getWorldY(), "World Y should initially be 0");
        assertNotNull(superObject.getSolidArea(), "Solid area should be initialized");
        assertEquals(0, superObject.getSolidAreaDefaultX(), "Default solid area X should initially be 0");
        assertEquals(0, superObject.getSolidAreaDefaultY(), "Default solid area Y should initially be 0");
    }
}
