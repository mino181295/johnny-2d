package it.unibo.oop.view;

import static it.unibo.oop.utilities.CharactersSettings.*;
import static it.unibo.oop.utilities.Direction.*;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.swing.JLabel;

import it.unibo.oop.model.Bullet;
import it.unibo.oop.model.Collectable;
import it.unibo.oop.model.Enemy;
import it.unibo.oop.model.GameState;
import it.unibo.oop.model.GameStateImpl;
import it.unibo.oop.model.Wall;
import it.unibo.oop.utilities.Direction;

/**
 * The {@link javax.swing.JPanel} containing the graphics elements of the game's
 * main level.
 */
public class LevelPanel extends BackgroundPanel {

    private static final long serialVersionUID = 8057405927611227670L;

    private final Map<Direction, BufferedImage> mainCharacterSprites;
    private final Map<Direction, BufferedImage> enemySprites;
    private BufferedImage bonus;
    private BufferedImage bullet;
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
        try {
            this.bonus = ImageLoader.load("/coin.png");
            this.bullet = ImageLoader.load("/bullet.png");
        } catch (IOException e) {
            System.out.println("Error loading the sprites");
        }
        this.stats = new JLabel();
        this.stats.setFont(new Font("Verdana", 1, 20));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(this.stats);
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(255, 255, 255));
        this.drawMainCharacter(g);
        this.drawMovables(g);
        this.drawStables(g);
        this.drawStats(g);
    }

    private void drawMainCharacter(final Graphics g) {
        g.drawImage(this.mainCharacterSprites.get(gs.getMainChar().get().getFaceDirection()), (gs.getMainChar().get().getPosition().getIntX()),
        		(gs.getMainChar().get().getPosition().getIntY()), this);
    }

    	
    private void drawMovables(final Graphics g) {
    	gs.getMovableList().forEach(e -> {
    	    if (e instanceof Enemy) {
    	        g.drawImage(this.enemySprites.get(e.getFaceDirection()), e.getPosition().getIntX(), e.getPosition().getIntY(), this);
    	    }
    	    if (e instanceof Bullet) {
    	        g.drawImage(this.bullet, e.getPosition().getIntX(), e.getPosition().getIntY(), this);
    	    }
    	});
    }

    private void drawStables(final Graphics g) {
        gs.getStableList().forEach(e -> {
            if (e instanceof Wall) {
                g.drawRect(e.getPosition().getIntX(), e.getPosition().getIntY(), WALL.getWidth(), WALL.getHeight());
            }
            if (e instanceof Collectable) {
                g.drawImage(this.bonus, e.getPosition().getIntX(), e.getPosition().getIntY(), this);
            }
        });
    }

    private void drawStats(final Graphics g) {
        g.drawImage(this.mainCharacterSprites.get(DOWN), this.getX(), this.getY(), this);
        this.stats.setText("    Life: 100    Score: 0");
    }
}