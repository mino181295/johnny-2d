package it.unibo.oop.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.oop.controller.GameLoop;

public class MainKeyListener implements KeyListener {

    private final GameLoop gL;
    
    public MainKeyListener(final GameLoop gL) {
        this.gL = gL;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
//        final int id = e.getID();
//        System.out.println("ciao");
//        if (id == KeyEvent.KEY_TYPED) {
//            System.out.println(e.getKeyChar());
//        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        // TODO Auto-generated method stub
//        System.out.println(e.getKeyChar());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        new Thread(() -> {
           // System.out.println(e.getKeyChar());
            this.gL.keySwitcher(e.getKeyChar());
        });   
    }
}
