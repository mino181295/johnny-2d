package it.unibo.oop.view;

import java.util.Arrays;
import it.unibo.oop.controller.State;
import it.unibo.oop.controller.StateObserver;

public class PauseMenu extends BaseMenu {

    private enum Button implements StateButton {
        REPLAY("Replay", State.START),
        RESUME("Resume", State.PLAY),
        OPTIONS("Options", State.OPTIONS),
        QUIT("Quit", State.QUIT);
        
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
    
    private static final String TITLE = "Pause";

    public PauseMenu(final StateObserver stateObs) {
        super(TITLE);
        this.addObserver(stateObs);
      
        /* ICON SETTING */
        this.setIcon("/pause.png");
        
        /* BUTTONS CREATION */
        this.addStateButton(Arrays.asList(Button.values()));
    }
}    

//
//          this.setFrameFeature(customization);
//        
//        /* MUSIC */
//        final JButton replay = new JButton("Replay");
//
//        /* RESUME */
//        final JButton resume = new JButton("Resume");
//
//        /* OPTIONS */
//        final JButton options = new JButton("Options");
//
//        /* MAIN MENU */
//        final JButton mainMenu = new JButton("Main Menu");
//
//        this.addComponent(replay);
//        this.addComponent(resume);
//        this.addComponent(options);
//        this.addComponent(mainMenu);
//        
//        replay.addActionListener((e) -> this.doObsAction(obs -> new Thread(()-> obs.stateAction(State.START)).start()));
//        resume.addActionListener((e) -> this.doObsAction(obs -> new Thread(()-> obs.stateAction(State.PLAY)).start()));
//        options.addActionListener((e) -> this.doObsAction(obs -> new Thread(()-> obs.stateAction(State.OPTIONS)).start()));
//        mainMenu.addActionListener((e) -> this.doObsAction(obs -> new Thread(()-> obs.stateAction(State.LAUNCHING)).start()));
//     
//    }
//    
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
//}