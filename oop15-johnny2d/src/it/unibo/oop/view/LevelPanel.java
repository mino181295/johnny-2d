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

import it.unibo.oop.model.BasicMonster;
import it.unibo.oop.model.Bullet;
import it.unibo.oop.model.GameState;
import it.unibo.oop.model.GameStateImpl;
import it.unibo.oop.model.HealthBonus;
import it.unibo.oop.model.Wall;
import it.unibo.oop.utilities.Direction;

/**
 * The {@link javax.swing.JPanel} containing the graphics elements of the game's
 * main level.
 */
public class LevelPanel extends BackgroundPanel {

    private static final long serialVersionUID = 8057405927611227670L;
    private static final int DEFAULT_SPACING = 10;

    private final Map<Direction, BufferedImage> mainCharacterSprites;
    private final Map<Direction, BufferedImage> enemySprites;
    private BufferedImage arena;
    private BufferedImage bonus;
    private BufferedImage bullet;
    private final JLabel stats;
    private final GameState gs;

    /**
     * Builds the {@link javax.swing.JPanel} and loads every {@link SpriteSheet}.
     */
    public LevelPanel() {
        super("/level.jpg");
        this.gs = GameStateImpl.getInstance();
        final SpriteSheet mainCharacterSheet = new SpriteSheet("/mainCharacter.png");
        this.mainCharacterSprites = mainCharacterSheet.split(MAIN_CHARACTER.getWidth(), MAIN_CHARACTER.getHeight());
        final SpriteSheet enemySheet = new SpriteSheet("/enemy.png");
        this.enemySprites = enemySheet.split(BASIC_ENEMY.getWidth(), BASIC_ENEMY.getHeight());
        try {
        	this.arena = ImageLoader.load("/fields/grass_paths/1.jpg");
            this.bonus = ImageLoader.load("/coin.png");
            this.bullet = ImageLoader.load("/bullet.png");
        } catch (IOException e) {
            System.out.println("Error loading the sprites");
        }
        this.stats = new JLabel();
        this.stats.setFont(new Font("Verdana", 1, 30));
        this.stats.setForeground(Color.RED);
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(this.stats);
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawImage(this.arena, this.gs.getArena().getPlayableRectangle().x, this.gs.getArena().getPlayableRectangle().y,
        		this.gs.getArena().getPlayableRectangle().width, this.gs.getArena().getPlayableRectangle().height, this);
        this.drawMainCharacter(g);
        this.drawMovables(g);
        this.drawStables(g);
        this.drawStats(g);
    }

    private void drawMainCharacter(final Graphics g) {
        g.drawImage(this.mainCharacterSprites.get(this.gs.getMainChar().get().getFaceDirection()),
        		this.gs.getMainChar().get().getTopLeftPos().getIntX(), this.gs.getMainChar().get().getTopLeftPos().getIntY(), this);
    }

    private void drawMovables(final Graphics g) {
        if (!this.gs.getMovableList().isEmpty()) {
        	this.gs.getMovableList().forEach(e -> {
                if (e instanceof BasicMonster) {
                    g.drawImage(this.enemySprites.get(e.getFaceDirection()), e.getTopLeftPos().getIntX(), e.getTopLeftPos().getIntY(), this);
                }
                if (e instanceof Bullet) {
                    g.drawImage(this.bullet, e.getTopLeftPos().getIntX(), e.getTopLeftPos().getIntY(), this);
                }
            });
        }
    }

    private void drawStables(final Graphics g) {
        if (!this.gs.getStableList().isEmpty()) {
        	this.gs.getStableList().forEach(e -> {
                if (e instanceof Wall) {
                    g.drawRect(e.getTopLeftPos().getIntX(), e.getTopLeftPos().getIntY(), WALL.getWidth(), WALL.getHeight());
                }
                if (e instanceof HealthBonus) {
                    g.drawImage(this.bonus, e.getTopLeftPos().getIntX(), e.getTopLeftPos().getIntY(), this);
                }
            });
        }
    }

    private void drawStats(final Graphics g) {
        g.drawImage(this.mainCharacterSprites.get(DOWN), this.getX() + DEFAULT_SPACING, this.getY() + DEFAULT_SPACING, this);
        this.stats.setText(this.gs.getMainChar().get().getScore().toString());
    }
}