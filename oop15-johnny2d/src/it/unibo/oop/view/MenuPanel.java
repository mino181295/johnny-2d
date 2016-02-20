package it.unibo.oop.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import it.unibo.oop.controller.StateObserver;

/**
 * Custom JPanel used as template for game's menu. It defines standard JButtons
 * layout, LayoutManager and background image.
 */
public class MenuPanel extends BackgroundPanel implements MenuInterface {

    private static final long serialVersionUID = 1L;
    private static final Color COMPONENTS_COLOR = new Color(255, 220, 130);
    private static final int TOP_INSET = 5;
    private static final int LEFT_INSET = 5;
    private static final int BOTTOM_INSET = 5;
    private static final int RIGHT_INSET = 5;
    private final Dimension prefComponentSize = new Dimension(160, 40);
    private final GridBagConstraints cnst = new GridBagConstraints();

    private final List<StateObserver> obsList;

    /**
     * Class's constructor.
     */
    public MenuPanel() {
        super("/background.jpg");

        this.cnst.insets = new Insets(TOP_INSET, LEFT_INSET, BOTTOM_INSET, RIGHT_INSET);
        this.cnst.gridy = 0;
        this.setLayout(new GridBagLayout());
        this.obsList = new ArrayList<>();
    }

    @Override
    public void addComponent(final JComponent cmp, final boolean customize) {
        if (customize) {
            cmp.setPreferredSize(this.prefComponentSize);
            cmp.setBackground(COMPONENTS_COLOR);
        }
        this.add(cmp, cnst);
        cnst.gridy++;

    }

    @Override
    public void addObserver(final StateObserver obs) {
        this.obsList.add(obs);
    }

    @Override
    public void addStateButton(final List<StateButton> btns) {
        btns.forEach(btn -> {
            final JButton jBtn = new JButton(btn.getName());
            jBtn.addActionListener(
                    (e) -> this.doObsAction(obs -> new Thread(() -> obs.stateAction(btn.getState())).start()));
            this.addComponent(jBtn, true);
        });
    }

    @Override
    public void setIcon(final String path) {
        final URL imgURL = this.getClass().getResource(path);
        final ImageIcon icon = new ImageIcon(imgURL);
        final JLabel label = new JLabel(icon);
        this.addComponent(label, false);
    }

    @Override
    public void doObsAction(final Consumer<StateObserver> action) {
        this.obsList.forEach(action);
    }
}