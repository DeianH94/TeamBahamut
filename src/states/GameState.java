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
    private static Food food;
    private static Rock rock;

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
        player = new Player(200, 300);
        food = new Food(r.nextInt(700), r.nextInt(500));
        list = new ArrayList<Rock>();
        img = ImageLoader.loadImage("/textures/Background.png");
    }


    private ActionListener speedListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            GameState.player.speedUp();
        }
    };

    private ActionListener foodListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            food = new Food(r.nextInt(700), r.nextInt(510));
            countFood++;
        }
    };

    private ActionListener rockListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            list.add(rock);
            list.remove(rock);
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

        if (player.intersects(food.boundingBox)){
            player.speedDown();
            food = new Food(r.nextInt(700), r.nextInt(510));
            countFood++;
            if (countFood % 5 == 0){
                rock = new Rock(r.nextInt(700), r.nextInt(500), 34, 34);
                rockTimer.start();
                rock.tick();
            }
        }

        if (rock != null && player.intersects(rock.boundingBox)) {
            player.speedUpMore();
            rock = null;
        }

        player.tick();
        food.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, 0, 0, game.width, game.height, null);
        player.render(g);
        food.render(g);

        if (rock != null) {
            rock.render(g);
        }
    }
}
