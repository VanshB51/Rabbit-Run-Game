package com.project.RabbitRun.exceptions;

/**
 * Custom exception class for errors encountered during sound loading.
 *
 * This exception provides detailed error information related to sound loading issues,
 * including an optional sound file path for context.
 */
public class SoundLoadingException extends RuntimeException{

    /** Optional field to store the name or path of the sound causing the issue */
    private final String soundPath;

    /**
     * Constructor with message and cause.
     *
     * @param message Detailed error message.
     * @param cause   Underlying exception cause.
     */
    public SoundLoadingException(String message, Throwable cause) {
        super(message, cause);
        this.soundPath = null; // No specific sound path provided
    }

    /**
     * Constructor with message, cause, and the sound path.
     *
     * @param message   Detailed error message.
     * @param cause     Underlying exception cause.
     * @param soundPath Path of the sound causing the exception.
     */
    public SoundLoadingException(String message, Throwable cause, String soundPath) {
        super(message, cause);
        this.soundPath = soundPath;
    }

    /**
     * Constructor with message only.
     *
     * @param message Detailed error message.
     */
    public SoundLoadingException(String message) {
        super(message);
        this.soundPath = null; // No specific sound path provided
    }

    /**
     * Gets the path of the sound that caused the exception.
     *
     * @return The sound path, or null if not specified.
     */
    public String getSoundPath() {
        return soundPath;
    }

    /**
     * Returns a string representation of the exception, including the sound path if available.
     *
     * @return A string describing the exception, with the sound path appended if specified.
     */
    @Override
    public String toString() {
        return super.toString() + (soundPath != null ? " [Sound Path: " + soundPath + "]" : "");
    }

}
