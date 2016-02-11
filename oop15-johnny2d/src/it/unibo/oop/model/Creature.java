package it.unibo.oop.model;
/**
 * Class that rappresents the interface of a monster or creature that can be moved in the environment.
 * @author Matteo Minardi
 *
 */
public interface Creature extends Entity {

	/**
	 * Method that moves the creature basing the movement on the current input or controller decision.
	 */
	public void move();
	
	
}
