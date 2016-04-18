package states;

import entities.creatures.Player;
import entities.items.Food;
import entities.items.Rock;
import game.Game;
import gfx.ImageLoader;
import javafx.application.Application;

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
    private int gameTime;
    private int countFood;
    private int score;

    private Timer timer;
    private Timer foodTimer;
    private Timer rockTimer;
    private Timer gameTimer;

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
        gameTime = 5000;
        timer = new Timer(speedTime, speedListener);
        foodTimer = new Timer(foodTime, foodListener);
        rockTimer = new Timer(rockTime, rockListener);
        gameTimer = new Timer(gameTime,gameListener);
        timer.setRepeats(true);
        foodTimer.setRepeats(true);
        // Entities
        player = new Player(200, 300);
        food = new Food(r.nextInt(700), r.nextInt(500));
        list = new ArrayList<Rock>();
        img = ImageLoader.loadImage("/textures/Background.png");
    }
    private ActionListener gameListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(10);
        }
    };

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
            score++;
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
        g.drawImage(img, 0, 0, game.getWidth(), game.getHeight(), null);

        player.render(g);
        food.render(g);


        if (player.isAlive()) {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawString("Score:    " + score, 10, 20);
            g.drawString(String.format("%s: %d", "Strenght", player.getVelocity()), 10, 40);

        } else{
            g.setFont(new Font("TimesRoman", Font.BOLD, 80));
            g.drawString("Game Over",175,250);
            g.setFont(new Font("TimesRoman", Font.BOLD, 20));
            g.setColor(Color.RED);
            g.drawString("Your Score is:    " + score,335,300);
            player.setVelocity(0);
            gameTimer.start();

        }


        if (rock != null) {
            rock.render(g);
        }
    }
}
