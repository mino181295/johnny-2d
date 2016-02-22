package it.unibo.oop.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.oop.controller.AppState;
import it.unibo.oop.controller.StateObserver;
import it.unibo.oop.model.GameStateImpl;

/**
 * {@link javax.swing.JPanel} for pause GameOver-view.
 */
public class GameOverMenu extends MenuPanel {
    
    private static final long serialVersionUID = 5096699286815303104L;
    private static final int FONT_SIZE = 30;
    private static final int PANEL_WIDTH = 160;
    private static final int PANEL_HEIGHT = 100;

    /**
     * @param stateObs
     *            a {@link StateObserver} object to send "messages".
     */
    public GameOverMenu(final StateObserver stateObs) {
        this.addObserver(stateObs);

        /* ICON SETTING */
        this.setIcon("/gameover.png");

        /* SCORE LABEL */
        final JLabel label = new JLabel("" + GameStateImpl.getInstance().getMainChar().get().getScore());
        label.setFont(new Font("AppStyle", Font.PLAIN, FONT_SIZE));
        label.setForeground(Color.WHITE);
        
        final JPanel musicPane = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        musicPane.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        musicPane.setOpaque(false);
        musicPane.add(label);
        this.addComponent(musicPane, false);
        
        /* BUTTONS CREATION */
        this.addStateButton(new MenuPanel.StateButton("Replay", AppState.START), 
                            new MenuPanel.StateButton("Options", AppState.OPTIONS), 
                            new MenuPanel.StateButton("Quit", AppState.QUIT));
    }
}