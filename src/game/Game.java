package game;

import display.Display;
import gfx.Assets;
import states.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
    private String name;
    private int width , height;
    private boolean isRunning = false;

    private Thread thread;

    private InputHandler inputHandler;
    private MouseHandler mouseHandler;
    private BufferStrategy bs;
    private Graphics g;

    //States
    public States gameState;
    public States menuState;
    private States settingsState;
    private States gameOverState;
    public States highscoreState;

    private Display display;

    public Game(String name, int width, int height) {
        this.width = width;
        this.height = height;
        this.name = name;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public MouseHandler getMouseHandler() {
        return mouseHandler;
    }

    private void init() {
        this.display = new Display(this.name , this.width , this.height);

        this.inputHandler = new InputHandler(this.display);
        this.mouseHandler = new MouseHandler(this.display);

        Assets.init();

        // Initializing all the states
        gameState = new GameState(this);
        menuState = new MenuState(this);
        settingsState = new SettingsState(this);
        gameOverState = new GameOverState(this);
        highscoreState = new HighscoresState(this);
        StateManager.setState(menuState);
    }

    public void tick () {
        if (StateManager.getState() != null) {
            StateManager.getState().tick();
        }
    }

    public void render () {
        this.bs = display.getCanvas().getBufferStrategy();

        if (bs == null) {
            display.getCanvas().createBufferStrategy(2);
            return;
        }

        g = bs.getDrawGraphics();
        g.clearRect(0, 0, this.width, this.height);


        if (StateManager.getState() != null){
            StateManager.getState().render(this.g);
        }

        bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        init();

        int fps = 30;
        double timePerTick = 1_000_000_000.0 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (isRunning) {
            now = System.nanoTime();
            delta += (now-lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1_000_000_000) {
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    public synchronized void start () {
        if (this.isRunning) {
            return;
        }

        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop()  {
        if(!isRunning) {
            return;
        }

        isRunning = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
