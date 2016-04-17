package game;

import display.Display;
import states.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    public InputHandler(Display display) {
        display.getCanvas().addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            GameState.player.goingUp = true;
            GameState.player.goingDown = false;
            GameState.player.goingLeft = false;
            GameState.player.goingRight = false;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            GameState.player.goingDown = true;
            GameState.player.goingUp = false;
            GameState.player.goingLeft = false;
            GameState.player.goingRight = false;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            GameState.player.goingLeft = true;
            GameState.player.goingUp = false;
            GameState.player.goingDown = false;
            GameState.player.goingRight = false;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            GameState.player.goingRight = true;
            GameState.player.goingUp = false;
            GameState.player.goingDown = false;
            GameState.player.goingLeft = false;
        }
        if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}
