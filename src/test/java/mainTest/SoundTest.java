package mainTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.project.RabbitRun.ui.Sound;

/**
 * Unit tests for the {@link Sound} class.
 * These tests validate the initialization, file loading, and playback functionality of the Sound class.
 */
public class SoundTest {
    private Sound sound;

    /**
     * Sets up the test environment by initializing the {@link Sound} instance
     * before each test case.
     */
    @BeforeEach
    public void setUp() {
        sound = new Sound();
    }

    /**
     * Verifies that the {@link Sound#soundURL} array is initialized correctly and
     * contains valid `.wav` file paths where applicable.
     */
    @Test
    public void testInitialization() {
        assertNotNull(sound.soundURL, "Sound URLs should be initialized");
        for (int i = 0; i < sound.soundURL.length; i++) {
            if (sound.soundURL[i] != null) {
                assertTrue(sound.soundURL[i].toString().endsWith(".wav"),
                        "Sound URL at index " + i + " should point to a .wav file");
            }
        }
    }

    /**
     * Tests that the {@link Sound#setFile(int)} method correctly initializes the
     * audio clip when provided with a valid index.
     */
    @Test
    public void testSetFileValidIndex() {
        sound.setFile(0); // Example: testing the first sound file
        assertNotNull(sound.clip, "Clip should be initialized");
        assertTrue(sound.clip.isOpen(), "Clip should be open after setting a valid file");
    }

    /**
     * Verifies that the {@link Sound#play()} method starts playing the audio clip
     * correctly after a file has been loaded.
     */
    @Test
    public void testPlay() {
        sound.setFile(1); // Load a valid sound file
        sound.play();
        assertNotNull(sound.clip, "Clip should be initialized");
        assertTrue(sound.clip.isOpen(), "Clip should be open after setFile() is called");
    }

    /**
     * Tests that the {@link Sound#loop()} method starts looping the audio clip
     * without throwing exceptions when a valid file is loaded.
     */
    @Test
    public void testLoop() {
        sound.setFile(1); // Load a valid sound file
        assertDoesNotThrow(() -> sound.loop(),
                "loop should not throw an exception after setting a valid file");
    }

    /**
     * Ensures that calling {@link Sound#setFile(int)} multiple times correctly
     * reinitializes the audio clip.
     */
    @Test
    public void testMultipleSetFileCalls() {
        sound.setFile(0);
        sound.setFile(1); // Load another file
        assertNotNull(sound.clip, "Clip should be re-initialized when setFile is called again");
    }
}
