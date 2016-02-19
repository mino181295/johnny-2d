package it.unibo.oop.view;

import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrameImpl implements MainFrame {

    private final JFrame frame;
    
    public MainFrameImpl() {
        this.frame = new JFrame();
        this.frame.setSize(500, 500);
        this.frame.setUndecorated(true);
        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);
    }

    @Override
    public void setPanel(final JPanel panel) {
        this.frame.setContentPane(Objects.requireNonNull(panel));
        this.frame.setVisible(true);
        this.frame.repaint();
    }
}
