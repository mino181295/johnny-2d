package it.unibo.oop.model;

/**
 * Interface that represents an item placed in the map that can be collected only by the {@link MainCharacter}.
 */
public interface Collectable {
	
	/**
	 * In case of collision with the {@link MainCharacter} this {@link Collectable} gets collected.
	 */
	void collect(MainCharacter bonusCollector);

}
