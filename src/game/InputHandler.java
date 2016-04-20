package game;

import display.Display;
import entities.creatures.Player;
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
            Player.setGoingUp(true);
            Player.setGoingDown(false);
            Player.setGoingLeft(false);
            Player.setGoingRight(false);
        }

        if (keyCode == KeyEvent.VK_DOWN) {
            Player.setGoingUp(false);
            Player.setGoingDown(true);
            Player.setGoingLeft(false);
            Player.setGoingRight(false);
        }

        if (keyCode == KeyEvent.VK_LEFT) {
            Player.setGoingUp(false);
            Player.setGoingDown(false);
            Player.setGoingLeft(true);
            Player.setGoingRight(false);
        }

        if (keyCode == KeyEvent.VK_RIGHT) {
            Player.setGoingUp(false);
            Player.setGoingDown(false);
            Player.setGoingLeft(false);
            Player.setGoingRight(true);
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
