package states;

import entities.creatures.Player;
import entities.items.Food;
import entities.items.Skull;
import game.Game;
import gfx.Assets;

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
    private static Skull skull;

    private Random r;
    private int speedTime;
    private int foodTime;
    private int rockTime;
    private int countFood;
    public int score;

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
        list = new ArrayList<Skull>();
        img = Assets.background;
        score = 0;
    }

    public int getScore() {
        return score;
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
            list.add(skull);
            list.remove(skull);
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
            player.setScore(score);
            if (countFood % 5 == 0){
                skull = new Skull(r.nextInt(700), r.nextInt(500));
                rockTimer.start();
                skull.tick();
            }
        }

        if (skull != null && player.intersects(skull.boundingBox)) {
            player.speedUpMore();
            skull = null;
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
            g.drawString(String.format("%s: %d", "Strength", player.getVelocity()), 10, 40);
        } else{
            timer.stop();
            foodTimer.stop();
            rockTimer.stop();
            player.setVelocity(0);
            // Ranking.ranking("test", player.getScore());
            StateManager.setState(new GameOverState(game));
        }

        if (skull != null) {
            skull.render(g);
        }
    }
}
