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
 * 
 * @author Paolo
 *
 * Custom JFrame used as template for game's menu.
 * It defines standard JButtons layout and the LayoutManager.
 * 
 */
public class MenuPanel extends BackgroundPanel implements MenuInterface {

    private static final long serialVersionUID = 1L;
    private static final Color COMPONENTS_COLOR = new Color(255, 220, 130);
    private final Dimension prefComponentSize = new Dimension(160, 40);
    private final GridBagConstraints cnst = new GridBagConstraints();

    private final List<StateObserver> obsList;

    public MenuPanel() {
        super("/background.jpg");
        
        this.cnst.insets = new Insets(5,5,5,5);
        this.cnst.gridy = 0; 
        this.setLayout(new GridBagLayout());
        this.obsList = new ArrayList<>();
    }

    public void addComponent(final JComponent cmp, final boolean customize) {
        if (customize) {
            cmp.setPreferredSize(this.prefComponentSize);
            cmp.setBackground(COMPONENTS_COLOR);
        }
        this.add(cmp, cnst);
        cnst.gridy++;

    }

    public void addObserver(final StateObserver obs) {
        this.obsList.add(obs);
    }

    public void addStateButton(final List<StateButton> btns) {
        btns.forEach(btn -> {
            final JButton jBtn = new JButton(btn.getName());
            jBtn.addActionListener((e) -> this.doObsAction(obs -> new Thread(
                            () -> obs.stateAction(btn.getState())).start()));
            this.addComponent(jBtn, true);
        });
    }

    public void setIcon(final String path) {
        final URL imgURL = this.getClass().getResource(path);
        final ImageIcon icon = new ImageIcon(imgURL);
        final JLabel label = new JLabel(icon);
        this.addComponent(label, false);
    }

    protected void doObsAction(final Consumer<StateObserver> action) {
        this.obsList.forEach(action);
    }
}