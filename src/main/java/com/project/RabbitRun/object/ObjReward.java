package com.project.RabbitRun.object;

/**
 * Represents a regular reward object in the "Rabbit Run" game.
 * This object adds points to the player's score when collected.
 */
public class ObjReward extends SuperObject{

    /**
     * Initializes a reward object with a "Clover" image.
     * This reward contributes to the player's score upon collection.
     */
    public ObjReward() {
        super("Clover", "/Reward/clover.png");
    }
}
