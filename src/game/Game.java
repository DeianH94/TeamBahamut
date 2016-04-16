package game;

import display.Display;
import entities.creatures.Player;
import entities.items.Food;
import entities.items.Rock;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;
import states.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public class Game implements Runnable {
    private String name;
    public int width , height;
    private boolean isRunning = false;

    private Thread thread;

    private InputHandler inputHandler;
    private BufferStrategy bs;
    private Graphics g;
    private Random r;
    private int speedTime;
    private int foodTime;
    private int rockTime;
    private int countFood;

    private Timer timer;
    private Timer foodTimer;
    private Timer rockTimer;

    private ArrayList list;
    private BufferedImage img;
    private SpriteSheet sh;

    //States
    private States gameState;
    private States menuState;
    private States settingsState;

    //Player
    public static Player player;
    public static Food food;
    public static Rock rock;

    private Display display;

    public Game(String name, int width, int height) {
        this.width = width;
        this.height = height;
        this.name = name;
    }

    public void init () {
        this.display = new Display(this.name , this.width , this.height);
        img = ImageLoader.loadImage("/textures/Background.png");
        sh = new SpriteSheet(ImageLoader.loadImage("/textures/Dog.png"));
        this.inputHandler = new InputHandler(this.display);
        r = new Random();
        countFood = 1;

        // Timers for food, speedUp, rocks
        speedTime = 3000;
        foodTime = 5000;
        rockTime = 5000;
        timer = new Timer(speedTime, speedListener);
        foodTimer = new Timer(foodTime, foodListener);
        rockTimer = new Timer(rockTime, rockListener);
        timer.start();
        foodTimer.start();
        rockTimer.start();
        timer.setRepeats(true);
        foodTimer.setRepeats(true);

        Assets.init();

        // Initializing all the states
        gameState = new GameState(this);
        menuState = new MenuState(this);
        settingsState = new SettingsState(this);
        StateManager.setState(gameState);

        // Entities
        player = new Player(200, 300);
        food = new Food(r.nextInt(700), r.nextInt(500));
        list = new ArrayList<Rock>();

    }
    ActionListener speedListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            player.speedUp();
        }
    };
    ActionListener foodListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            food = new Food(r.nextInt(700), r.nextInt(510));
            countFood++;
        }
    };
    ActionListener rockListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            list.add(rock);
            list.remove(rock);
        }
    };


    public void tick () {
        if (StateManager.getState() != null) {
            StateManager.getState().tick();
        }

        if (player.intersects(this.food.boundingBox)){
            player.speedDown();
            food = new Food(r.nextInt(700), r.nextInt(510));
            countFood++;
            if (countFood % 5 == 0){
                rock = new Rock(r.nextInt(700), r.nextInt(500), 34, 34);
                rockTimer.start();
                rock.tick();
            }
        }

        if (this.rock != null && player.intersects(this.rock.boundingBox)) {
            player.speedUpMore();
            rock = null;
        }

        player.tick();
        food.tick();

        Rectangle playerBoundingBox = player.getBoundingBox();
        if (playerBoundingBox.getX() > 745.0 || playerBoundingBox.getX() < 0.0){
            System.out.println("you are death");
            stop();
        }

        if (playerBoundingBox.getY() > 515 || playerBoundingBox.getY() < -10.0){
            System.out.println("You are death");
            stop();
        }

        System.out.println(playerBoundingBox.getY());
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
        food.render(g);

        if (this.rock != null) {
            rock.render(g);
        }

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
