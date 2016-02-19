package it.unibo.oop.view;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Optional;
import javax.swing.JFrame;
import it.unibo.oop.controller.State;
import it.unibo.oop.controller.StateObserver;
import it.unibo.oop.controller.ViewsManager;

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
    public void changeView(final State state) {
        try {
            MenuPanelFactory.makePanel(Objects.requireNonNull(state)).ifPresent(p -> this.frame.setContentPane(p));
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        this.frame.repaint();
        this.setVisible(true);
    }

    public void setVisible(final boolean val) {
        this.frame.setVisible(val);
    }
    
    static private class MenuPanelFactory {
        
        static Optional<MenuPanel> makePanel(final State state) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
            
            Class<? extends MenuPanel> outPanel;
            
            switch (state) {
            case LAUNCHING:
                outPanel = Launcher.class;
                break;
            case QUIT:
                outPanel = QuitMenu.class;
                break; 
            case OPTIONS:
                outPanel = OptionsMenu.class;
                break;
            case PAUSE:
                outPanel = PauseMenu.class;
                break;
            case CREDITS:
                outPanel = CreditsMenu.class;
                break;
            default:
                return Optional.empty();
            }
            return Optional.of(outPanel.getConstructor(StateObserver.class).newInstance(ViewsManager.getInstance()));
        }
    }
}

