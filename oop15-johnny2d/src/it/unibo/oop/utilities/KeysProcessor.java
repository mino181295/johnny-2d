package it.unibo.oop.utilities;

import java.awt.event.KeyEvent;
import java.util.List;

/**
 * 
 * @author Paolo
 *
 * class used by the controller to process the key list.
 */
public class KeysProcessor {

    public Direction processKeys(final List<Integer> pressedKeys, final List<Character> typedKeys) {       
        
        if(pressedKeys.contains(KeyEvent.VK_W)) {
            
        }   
        if (this.pgDir == NONE) { /* ---> implemento una sorta di invokeAndWait */
            switch (keyCode) { /* switch for a pg move */
            case KeyEvent.VK_W:
                System.out.println("mosso in alto");
                this.pgDir = Direction.UP;
                break;
            case KeyEvent.VK_A:
                System.out.println("mosso a sx");
                this.pgDir = Direction.LEFT;
                break;
            case KeyEvent.VK_S:
                System.out.println("mosso in basso");
                this.pgDir = DOWN;
                break;
            case KeyEvent.VK_D:
                System.out.println("mosso a dx");
                this.pgDir = RIGHT;
                break;
            default:
            }
        }
          switch (keyCode) { 
          case KeyEvent.VK_ESCAPE:
              System.out.println("show menu");
              this.launcher.showIt(); /* da sostituire col menu di pausa */
              break;
          case KeyEvent.VK_SPACE:
              System.out.println("shoot");
              this.pgIsShooting = true;
              break;
          default:
          }
      }    
    }
}
