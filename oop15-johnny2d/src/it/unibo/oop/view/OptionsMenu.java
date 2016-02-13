package it.unibo.oop.view;

import javax.swing.JButton;

public class OptionsMenu extends BaseMenu {
    
    public OptionsMenu() {
        super("Options");
        
        /*
         * BUTTONS CREATION
         */
        
        /* MUSIC */
        final JButton music = new JButton("Music");
        
        /* DIFFICULTY */
        final JButton diff = new JButton("Difficulty");
        
        /* TEXTURES */
        final JButton textures = new JButton("Textures");
        
        /* BACK */
        final JButton back = new JButton("back");
        
        this.addComponent(music);
        this.addComponent(diff);
        this.addComponent(textures);
        this.addComponent(back);
        
//        /* ACTIONS */
//        quit.addActionListener(e -> {
//            final int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
//                    "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//            if (response == JOptionPane.YES_OPTION) {
//            //    this.doObsAction(obs -> obs.stateAction(State.EXIT));
//            }
//        });
    }  
}
