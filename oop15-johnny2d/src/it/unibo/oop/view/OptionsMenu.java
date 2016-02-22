package it.unibo.oop.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import it.unibo.oop.controller.AppState;
import it.unibo.oop.controller.StateObserver;
import it.unibo.oop.controller.ViewsManagerImpl;

/**
 * {@link javax.swing.JPanel} for options Menu-view.
 */
public class OptionsMenu extends MenuPanel {

    private static final long serialVersionUID = -4689323418673684324L;
    private static final int FONT_SIZE = 30;
    private static final int PANEL_WIDTH = 160;
    private static final int PANEL_HEIGHT = 100;

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
        label.setFont(new Font("AppStyle", Font.PLAIN, FONT_SIZE));
        label.setForeground(Color.WHITE);

        final JCheckBox check = new JCheckBox();
        check.setOpaque(false);
        check.setSelected(true);
        check.addActionListener(e -> ViewsManagerImpl.getInstance().getView().enableMusic(check.isSelected()));

        final JPanel musicPane = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        musicPane.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        musicPane.setOpaque(false);
        musicPane.add(label);
        musicPane.add(check);
        this.addComponent(musicPane, false);

        /* BUTTONS CREATION */
        this.addStateButton(new MenuPanel.StateButton("Credits", AppState.CREDITS),
                            new MenuPanel.StateButton("Back", AppState.BACK));
    }
}