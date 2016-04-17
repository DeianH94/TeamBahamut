package states;

import entities.creatures.Player;
import entities.items.Food;
import entities.items.Rock;
import game.Game;
import gfx.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GameState extends States{
    public static Player player;
    public static Food food;
    public static Rock rock;

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

    public GameState(Game game) {
        super(game);
        r = new Random();
        countFood = 1;

        // Timers for food, speedUp, rocks
        speedTime = 3000;
        foodTime = 5000;
        rockTime = 5000;
        timer = new Timer(speedTime, speedListener);
        foodTimer = new Timer(foodTime, foodListener);
        rockTimer = new Timer(rockTime, rockListener);
        timer.setRepeats(true);
        foodTimer.setRepeats(true);
        // Entities
        GameState.player = new Player(200, 300);
        GameState.food = new Food(r.nextInt(700), r.nextInt(500));
        list = new ArrayList<Rock>();
        img = ImageLoader.loadImage("/textures/Background.png");
    }


    ActionListener speedListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            GameState.player.speedUp();
        }
    };
    ActionListener foodListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            GameState.food = new Food(r.nextInt(700), r.nextInt(510));
            countFood++;
        }
    };
    ActionListener rockListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            list.add(GameState.rock);
            list.remove(GameState.rock);
        }
    };

    @Override
    public void tick() {
        if (StateManager.getState().equals(game.gameState))
        {
            timer.start();
            foodTimer.start();
            rockTimer.start();
        }

        if (GameState.player.intersects(GameState.food.boundingBox)){
            GameState.player.speedDown();
            GameState.food = new Food(r.nextInt(700), r.nextInt(510));
            countFood++;
            if (countFood % 5 == 0){
                GameState.rock = new Rock(r.nextInt(700), r.nextInt(500), 34, 34);
                rockTimer.start();
                GameState.rock.tick();
            }
        }

        if (GameState.rock != null && GameState.player.intersects(GameState.rock.boundingBox)) {
            GameState.player.speedUpMore();
            GameState.rock = null;
        }

        GameState.player.tick();
        GameState.food.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, 0, 0, game.width, game.height, null);
        GameState.player.render(g);
        GameState.food.render(g);

        if (GameState.rock != null) {
            GameState.rock.render(g);
        }
    }
}
