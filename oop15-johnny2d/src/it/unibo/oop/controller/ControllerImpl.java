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
    private volatile boolean isRecord;
    private volatile boolean isReset;

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
        this.play();
    }

    @Override
    public void play() { // pause -> play
        MovementKeysManager.getInstance().reset();
        ActionKeysManager.getInstance().reset();
        this.isReset = false;
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
    
    public synchronized void resetStatFile() {
        this.isReset = true;
        try (ObjectOutputStream outStream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(Settings.HIGHSCORE_FOLDER + Settings.HIGHSCORE_FILE)))) {
            outStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized Score getStatFromFile() {
        Score topScore = new Score();
        try (ObjectInputStream inStream = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(Settings.HIGHSCORE_FOLDER + Settings.HIGHSCORE_FILE)))) {
            topScore = (Score) inStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("File was empty or doesn't exist.");
            this.createStatFile();
        }
        return topScore;
    }

    public synchronized void putStatToFile(final Score topScore) {
        this.isRecord = true;
        try (ObjectOutputStream outStream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(Settings.HIGHSCORE_FOLDER + Settings.HIGHSCORE_FILE)))) {
            outStream.writeObject(topScore);
        } catch (IOException e) {
            System.out.println("File doesn't exist.");
        }
    }

    public boolean isRecord() {
        return this.isRecord;
    }
    
    public boolean isScoreReset() {
        return this.isReset;
    }
    
    /**
     * @param args
     *            ignored.
     */
    public static void main(final String... args) {
        ControllerImpl.getInstance();
    }
}