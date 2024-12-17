package com.project.RabbitRun.object;

/**
 * Represents a bonus reward object in the "Rabbit Run" game.
 * This object provides extra points to the player when collected.
 */
public class ObjBonusReward extends SuperObject{

    /**
     * Initializes a bonus reward object with a "Carrot" image.
     * This reward grants additional points to the player upon collection.
     */
    public ObjBonusReward() {
        super("Carrot", "/Reward/carrot.png");
    }
}
