package it.unibo.oop.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;

import it.unibo.oop.model.GameStateImpl;
import it.unibo.oop.model.Score;
import it.unibo.oop.utilities.Settings;

/**
 * class implementing the Controller of the MVC model.
 */
public final class ControllerImpl implements Controller {

    private static Optional<ControllerImpl> singleton = Optional.empty();
    private Optional<AgentInterface> gLAgent = Optional.empty();
    private boolean isRecord;

    private ControllerImpl() {
        this.createStatFile();
        ViewsManagerImpl.getInstance().showView(AppState.LAUNCHING);
    }

    /**
     * @return the SINGLETON instance of the class.
     */
    public static synchronized Controller getInstance() {
        if (!singleton.isPresent()) {
            singleton = Optional.of(new ControllerImpl());
        }
        return singleton.get();
    }

    @Override
    public void start() { // launcher -> play / pause -> replay
        GameStateImpl.getInstance().initialize(1);
        KeysManagerImpl.getInstance().reset();
        this.play();
    }

    @Override
    public void play() { // pause -> play
        this.isRecord = false;
        ViewsManagerImpl.getInstance().hideView();
        if (!this.gLAgent.isPresent()) {
            this.gLAgent = Optional.ofNullable(new GameLoopAgent());
            new Thread(this.gLAgent.get()).start();
        } else {
            this.gLAgent.get().play();
        }
    }

    private void createStatFile() {
        final File statDir = new File(Settings.HIGHSCORE_FOLDER);
        final File statFile = new File(Settings.HIGHSCORE_FOLDER + Settings.HIGHSCORE_FILE);
        try {
            statDir.mkdir();
            statFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Score getStatFromFile() {
        Score topScore = new Score();
        try (ObjectInputStream inStream = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(Settings.HIGHSCORE_FOLDER + Settings.HIGHSCORE_FILE)))) {
            topScore = (Score) inStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return topScore;
    }

    public void putStatToFile(final Score topScore) {
        this.isRecord = true;
        try (ObjectOutputStream outStream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(Settings.HIGHSCORE_FOLDER + Settings.HIGHSCORE_FILE)))) {

            outStream.writeObject(topScore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isRecord() {
        return this.isRecord;
    }
    
    /**
     * @param args
     *            ignored.
     */
    public static void main(final String... args) {
        new ControllerImpl();
    }
}