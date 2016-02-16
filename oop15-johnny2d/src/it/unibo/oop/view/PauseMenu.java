package it.unibo.oop.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import it.unibo.oop.controller.State;
import it.unibo.oop.controller.StateObserver;

public class PauseMenu extends BaseMenu {

    private static final String TITLE = "Pause";

    public PauseMenu(final StateObserver stateObs) {
        super(TITLE);
        this.addObserver(stateObs);

        /*
         * BUTTONS CREATION
         */

        /* MUSIC */
        final JButton replay = new JButton("Replay");

        /* RESUME */
        final JButton resume = new JButton("Resume");

        /* OPTIONS */
        final JButton options = new JButton("Options");

        /* MAIN MENU */
        final JButton mainMenu = new JButton("Main Menu");

        this.addComponent(replay);
        this.addComponent(resume);
        this.addComponent(options);
        this.addComponent(mainMenu);
    }
    
//    /* ACTIONS */
//    private class MyActionListener implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            final Object src = e.getSource();
//            Optional<State> state = Optional.empty();
//            if (src == Launcher.this.quit) {
//                final int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
//                                "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//                if (response == JOptionPane.YES_OPTION) {
//                    state = Optional.of(State.EXIT);
//                }
//            } else if (src == Launcher.this.options) {
//                state = Optional.of(State.OPTIONS);
//            } else if (src == Launcher.this.play){
//                state = Optional.of(State.PLAY);
//            }
//            state.ifPresent(st -> Launcher.this.doObsAction(
//                            obs -> new Thread(()-> obs.stateAction(st)).start()));
//        }
//    }
}
