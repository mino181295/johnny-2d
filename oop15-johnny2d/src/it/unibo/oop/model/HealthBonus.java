package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.BONUS;

/**
 * {@link Collectable} item that heals the {@link MainCharacter} of a set value.
 *
 */
public class HealthBonus extends AbstractEntity implements Collectable {

    private static final int HEAL_VALUE = 1;

    public HealthBonus(final double startingX, final double startingY) {
        super(startingX, startingY);
    }

    protected int getEntityHeight() {
        return BONUS.getHeight();
    }

    protected int getEntityWidth() {
        return BONUS.getWidth();
    }

    /**
     * The {@link MainCharacter} passed collects the {@link HealthBonus} and
     * gets healed.
     */
    public void collect(final MainCharacter bonusCollector) {
        bonusCollector.getHealth().increaseHealth(HEAL_VALUE);
    }

}
