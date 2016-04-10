package game;

import display.Display;

/**
 * Created by Misho on 9.4.2016 г..
 */
public class Game implements Runnable {
    private String name;
    private int widht , height;
    private boolean isRunning;

    private Thread thread;

    private Display display;
    public Game(String name , int widht , int height) {
        this.name =  name;
        this.widht = widht;
        this.height = height;
    }

    public void init () {
        this.display = new Display(this.name , this.widht , this.height);

    }

    public void tick () {

    }

    public void render () {

    }

    @Override
    public void run() {
        this.init();

        while (isRunning) {
            tick();
            render();
        }
        try {
            this.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void start () {
        this.isRunning = true;
        thread = new Thread(this);
        thread.start();
    }
    public synchronized void stop () throws InterruptedException {
        try {
            this.isRunning = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
