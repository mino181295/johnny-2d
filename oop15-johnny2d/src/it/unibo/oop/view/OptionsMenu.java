package it.unibo.oop.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Arrays;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.oop.controller.State;
import it.unibo.oop.controller.StateObserver;
import it.unibo.oop.controller.ViewsManager;

/**
 * {@link javax.swing.JPanel} for options Menu-view.
 */
public class OptionsMenu extends MenuPanel {

    private static final long serialVersionUID = -4689323418673684324L;
    private static final int FONT_SIZE = 30;
    private static final int PANEL_WIDTH = 160;
    private static final int PANEL_HEIGHT = 100;

    private enum Button implements StateButton {
        CREDITS("Credits", State.CREDITS), BACK("Back", State.BACK);

        private final String name;
        private final State state;

        Button(final String name, final State state) {
            this.name = name;
            this.state = state;
        }

        public String getName() {
            return this.name;
        }

        public State getState() {
            return this.state;
        }
    }

    /**
     * @param stateObs
     *            a {@link StateObserver} object to send "messages".
     */
    public OptionsMenu(final StateObserver stateObs) {
        this.addObserver(stateObs);

        /* ICON SETTING */
        this.setIcon("/options.png");

        /* MUSIC */
        final JLabel label = new JLabel("Music");
        label.setFont(new Font("MusicStyle", Font.PLAIN, FONT_SIZE));
        label.setForeground(Color.WHITE);

        final JCheckBox check = new JCheckBox();
        check.setOpaque(false);
        check.setSelected(true);
        check.addActionListener(e -> ViewsManager.getInstance().getLevel().enableMusic(check.isSelected()));

        final JPanel musicPane = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 10));
        musicPane.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        musicPane.setOpaque(false);
        musicPane.add(label);
        musicPane.add(check);
        this.addComponent(musicPane, false);
        // /* DIFFICULTY */
        // final JButton diff = new JButton("Difficulty");

        // this.addComponent(diff);

        /* BUTTONS CREATION */
        this.addStateButton(Arrays.asList(Button.values()));
    }
}
