package it.unibo.oop.view;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Optional;
import javax.swing.JFrame;
import it.unibo.oop.controller.AppState;
import it.unibo.oop.controller.StateObserver;
import it.unibo.oop.controller.ViewsManagerImpl;

/**
 * Implementation of {@link MainFrame} interface.
 */
public class MainFrameImpl implements MainFrame {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private final JFrame frame;

    /**
     * Class's constructor.
     */
    public MainFrameImpl() {
        this.frame = new JFrame();
        this.frame.setSize(WIDTH, HEIGHT);
        this.frame.setUndecorated(true);
        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);
    }

    @Override
    public void changeView(final AppState state) {
        try {
            MenuPanelFactory.makePanel(Objects.requireNonNull(state)).ifPresent(p -> this.frame.setContentPane(p));
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        this.frame.repaint();
        this.setVisible(true);
    }

    @Override
    public void setVisible(final boolean val) {
        this.frame.setVisible(val);
    }

    private static class MenuPanelFactory {
        
        private MenuPanelFactory() {
        }

        private static Optional<MenuPanel> makePanel(final AppState state) throws InstantiationException, IllegalAccessException,
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
            return Optional.of(outPanel.getConstructor(StateObserver.class).newInstance(ViewsManagerImpl.getInstance()));
        }
    }
}