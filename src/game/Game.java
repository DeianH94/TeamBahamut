package game;

import display.Display;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;
import states.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable {
    private String name;
    public int width , height;
    private boolean isRunning = false;

    private Thread thread;

    private InputHandler inputHandler;
    private BufferStrategy bs;
    private Graphics g;

    private BufferedImage img;
    private SpriteSheet sh;

    //States
    private States gameState;
    private States menuState;
    private States settingsState;

    //Player
    public static Player player;

    private Display display;
    public Game(String name, int width, int height) {
        this.width = width;
        this.height = height;
        this.name = name;
    }

    public void init () {
        this.display = new Display(this.name , this.width , this.height);
        img = ImageLoader.loadImage("/textures/forest.jpg");
        sh = new SpriteSheet(ImageLoader.loadImage("/textures/Dog.png"));
        this.inputHandler = new InputHandler(this.display);
        Assets.init();

        //Initializing all the states
        gameState = new GameState();
        menuState = new MenuState();
        settingsState = new SettingsState();
        StateManager.setState(gameState);
        player = new Player();

    }

    public void tick () {
        if (StateManager.getState() != null) {
            StateManager.getState().tick();
        }

        player.tick();
    }

    public void render () {
        this.bs = display.getCanvas().getBufferStrategy();

        if (bs == null) {
            display.getCanvas().createBufferStrategy(2);
            return;
        }

        g = bs.getDrawGraphics();
        g.clearRect(0, 0, this.width, this.height);
        g.drawImage(img, 0, 0, this.width, this.height, null);
        player.render(g);


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
