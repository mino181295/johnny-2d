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

public class OptionsMenu extends BaseMenu {
    
    private enum Button implements StateButton {
        CREDITS("Credits", State.CREDITS),
        BACK("Back", State.BACK);
        
        private final String name;
        private final State state;
        
        private Button(final String name, final State state) {
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
    
    private static final String TITLE = "Options";
    
    public OptionsMenu(final StateObserver stateObs) {
        super(TITLE);
        this.addObserver(stateObs);
        
        /* ICON SETTING */
        this.setIcon("/options.png");
        
        /* MUSIC */
        final JLabel label = new JLabel("Music");
        label.setFont(new Font("MusicStyle", Font.PLAIN, 30));
        label.setForeground(Color.WHITE);
        
        final JCheckBox check = new JCheckBox();
        check.setOpaque(false);
        
        final JPanel musicPane = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 10));
        musicPane.setPreferredSize(new Dimension(160, 100));
        musicPane.setOpaque(false);
        musicPane.add(label);
        musicPane.add(check);
        this.addComponent(musicPane, false);
//        /* DIFFICULTY */
//        final JButton diff = new JButton("Difficulty");

//        this.addComponent(diff);
        
        /* BUTTONS CREATION */
        this.addStateButton(Arrays.asList(Button.values()));
    }  
}
