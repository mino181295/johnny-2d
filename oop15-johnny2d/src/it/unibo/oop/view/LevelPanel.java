package it.unibo.oop.view;

import static it.unibo.oop.utilities.CharactersSettings.BASIC_ENEMY;
import static it.unibo.oop.utilities.CharactersSettings.MAIN_CHARACTER;
import static it.unibo.oop.utilities.Direction.*;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Map;

import javax.swing.JLabel;

import it.unibo.oop.model.GameState;
import it.unibo.oop.model.GameStateImpl;
import it.unibo.oop.utilities.Direction;

/**
 * The {@link javax.swing.JPanel} containing the graphics elements of the game's
 * main level.
 */
public class LevelPanel extends BackgroundPanel {

    private static final long serialVersionUID = 8057405927611227670L;

    private final Map<Direction, BufferedImage> mainCharacterSprites;
    private final Map<Direction, BufferedImage> enemySprites;
    private final JLabel stats;
    private final GameState gs;

    /**
     * Builds the {@link javax.swing.JPanel} and loads every
     * {@link SpriteSheet}.
     */
    public LevelPanel() {
        super("/background.jpg");
        this.gs = GameStateImpl.getInstance();
        final SpriteSheet mainCharacterSheet = new SpriteSheet("/mainCharacter.png");
        this.mainCharacterSprites = mainCharacterSheet.split(MAIN_CHARACTER.getWidth(), MAIN_CHARACTER.getHeight());
        final SpriteSheet enemySheet = new SpriteSheet("/enemy.png");
        this.enemySprites = enemySheet.split(BASIC_ENEMY.getWidth(), BASIC_ENEMY.getHeight());
        this.stats = new JLabel();
        this.stats.setFont(new Font("Verdana", 1, 20));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(this.stats);
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        this.drawMainCharacter(g);
        //this.drawMovables(g);
        //this.drawStables(g);
        this.drawStats(g);
    }

    private void drawMainCharacter(final Graphics g) {
        g.drawImage(this.mainCharacterSprites.get(gs.getMainChar().get().getFaceDirection()), (gs.getMainChar().get().getPosition().getIntX()),
        		(gs.getMainChar().get().getPosition().getIntY()), this);
    }

    	
    private void drawMovables(final Graphics g) {
    	
    }

    private void drawStables(final Graphics g) {
    	
    }

    private void drawStats(final Graphics g) {
        g.drawImage(this.mainCharacterSprites.get(DOWN), this.getX(), this.getY(), this);
        this.stats.setText("    Life: 100    Score: 0");
    }
}