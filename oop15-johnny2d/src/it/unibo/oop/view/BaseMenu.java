package it.unibo.oop.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import it.unibo.oop.controller.StateObserver;

public abstract class BaseMenu extends JFrame implements Showable {

    private static final long serialVersionUID = -6224390548062243879L;
    private static final Color BUTTONS_COLOR = new Color(255, 220, 130);
//    private final Set<StateObserver> obs;
//    private final Set<JButton> buttons;
    private final Dimension prefButtonSize = new Dimension(200, 50);
    private final GridBagConstraints cnst = new GridBagConstraints();
    
    public BaseMenu(final String label) {
        
        super(label);
        
//        this.obs = new HashSet<>();
//        this.buttons = new HashSet<>();
 
        this.cnst.insets = new Insets(3, 3, 3, 3);
        this.cnst.gridy = 0;
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        final URL imgURL = Launcher.class.getResource("/background.jpg");
        final ImageIcon img = new ImageIcon(imgURL);

        final JPanel menuPanel = new ImagePanel(img.getImage()); 
        menuPanel.setLayout(new GridBagLayout());
        
        this.setContentPane(menuPanel);
    }
    
    public void addButton(final JButton btn) {
        btn.setPreferredSize(this.prefButtonSize);
        btn.setBackground(BUTTONS_COLOR);
        this.getContentPane().add(btn, cnst);
        cnst.gridy++;    
        
        // this.buttons.add(btn);
    }
    
//    public void addStateObserver(final StateObserver obs) {
//        this.obs.add(obs);
//    }
    
    @Override
    public void showIt() {
        try {
            SwingUtilities.invokeAndWait(()->this.setVisible(true));
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
