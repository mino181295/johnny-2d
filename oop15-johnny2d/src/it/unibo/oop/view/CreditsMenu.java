package it.unibo.oop.view;

import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import java.util.List;

import javax.swing.JLabel;

import it.unibo.oop.controller.AppState;
import it.unibo.oop.controller.StateObserver;

/**
 * {@link javax.swing.JPanel} for credits Menu-view.
 */
public class CreditsMenu extends MenuPanel {

    private static final long serialVersionUID = 7436402336801219924L;
    private static final int FONT_SIZE = 25;
    private static final List<String> MAKERS = Arrays.asList("Model: Matteo Minardi",
                                                             "View: Pasini Giacomo",
                                                             "Controller: Paolo Venturi");
    /**
     * @param stateObs
     *            a {@link StateObserver} object to send "messages".
     */
    public CreditsMenu(final StateObserver stateObs) {
        this.addObserver(stateObs);

        /* ICON SETTING */
        this.setIcon("/credits.png");

        /* LABELS ADDING */
        for (final String text: MAKERS) {
            final JLabel label = new JLabel(text);
            label.setFont(new Font("MusicStyle", Font.PLAIN, FONT_SIZE));
            label.setForeground(Color.WHITE);
            this.addComponent(label, false);
        }

        /* BUTTONS CREATION */
        this.addStateButton(new MenuPanel.StateButton("Back", AppState.BACK));
    }
}
