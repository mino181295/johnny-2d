package it.unibo.oop.controller;

/**
 * Interface for an agent b.p. GameLoopAgent
 */
public interface AgentInterface extends Runnable {

    /**
     * Allows the agent to play his action.
     */
    void play();
}
