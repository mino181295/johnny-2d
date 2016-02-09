package it.unibo.oop.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainKeyListener implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
        final int id = e.getID();
     //   if (id == KeyEvent.KEY_TYPED) {
            System.out.println(e.getKeyChar());
       // }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
