package it.unibo.oop.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JFrameBuilder {

    private JFrame outFrame;
    private final List<JComponent> cmps;
    private static final Color BUTTONS_COLOR = new Color(255, 220, 130);
    private final Dimension prefButtonSize = new Dimension(200, 50);
    private final GridBagConstraints cnst = new GridBagConstraints();
    private boolean defaultLayout; /* per determinare i metodi di inserimento dei componente nel panel */

    public JFrameBuilder(final String frameName) {
        this.cmps = new ArrayList<>();
        this.outFrame = new JFrame(frameName);
    }

    public JFrameBuilder setDefaultCustomization() {
        this.defaultLayout = true;
        this.cnst.insets = new Insets(3, 3, 3, 3);
        this.cnst.gridy = 0;
        this.outFrame.setSize(500, 500); 
        // this.outFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.outFrame.setLocationRelativeTo(null);
        this.outFrame.setResizable(false);

        final URL imgURL = JFrameBuilder.class.getResource("/background.jpg");
        final ImageIcon img = new ImageIcon(imgURL);

        final JPanel menuPanel = new ImagePanel(img.getImage()); 
        menuPanel.setLayout(new GridBagLayout());

        this.outFrame.setContentPane(menuPanel);
        return this;
    }
    
    public <T extends JComponent> JFrameBuilder addComponent(final Class<T> cmpType, final String... cmps)
              throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
              IllegalArgumentException, InvocationTargetException {
        for (final String name: cmps) {
            final Constructor<T> construct = cmpType.getConstructor(String.class);
            final T cmp = construct.newInstance(name);
            cmp.setName(name);
            if (this.defaultLayout) {
                this.addCmpWithDefaultLayout(cmp);
            } else {
                this.outFrame.getContentPane().add(cmp);
                this.cmps.add(cmp);
            }
        }
        return this;
    }
    
    private void addCmpWithDefaultLayout(final JComponent cmp) {
        if (cmp instanceof JButton) {
            cmp.setPreferredSize(this.prefButtonSize);
            cmp.setBackground(BUTTONS_COLOR);
        }
        this.outFrame.getContentPane().add(cmp, cnst);
        cnst.gridy++;
    }
    
//    public JFrameBuilder addBtnsAction(final Runnable... action) {
//        this.cmps.stream()
//                 .filter(e -> e instanceof JButton)
//                 .forEach(btn -> ((JButton)btn).addActionListener(e -> action.run()));
//        return this;
//    }

    public JFrameBuilder addBtnsAction(final ActionListener action) {
        this.cmps.stream()
                 .filter(e -> e instanceof JButton)
                 .forEach(btn -> ((JButton)btn).addActionListener(action));
        return this;
    }
    
    public JFrameBuilder setFrameFeature(Consumer<JFrame> customization) {
        customization.accept(this.outFrame);
        return this;
    }
    
    public JFrame build() {
        return this.outFrame;
    }
}
