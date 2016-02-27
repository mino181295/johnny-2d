package main;

import it.unibo.oop.controller.AppState;
import it.unibo.oop.view.ViewImpl;

/**
 * Entry for the application.
 */
public final class App {

    private App() {}
    
    /**
     * @param args
     *            ignored.
     */
    public static void main(final String... args) {
        ViewImpl.getInstance().showView(AppState.LAUNCHING);
    }
}
