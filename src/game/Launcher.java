package game;

import display.Display;

/**
 * Created by Misho on 9.4.2016 г..
 */
public class Launcher {
    public static void main(String[] args) {
        Game game = new Game("Game" , 800 , 600);
        game.start();
    }
}
