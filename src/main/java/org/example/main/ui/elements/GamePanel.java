package org.example.main.ui.elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;

public class GamePanel extends JPanel implements Runnable {

    public static final int GAME_WIDTH = 1000;
    public static final int GAME_HEIGHT = (int) (GAME_WIDTH * (5 / 9f));
    public static final int BALL_SIZE = 20;
    public final Dimension screenSize;
    private final transient Thread gameThread;
    private Score score;
    private Ball ball;
    private Paddle paddle1;
    private Paddle paddle2;
    private boolean isQuit;

    public GamePanel() {
        newBall();
        newPaddles();
        newScore();
        this.setVisible(true);
        this.addKeyListener(new AL());
        this.setFocusable(true);
        this.setBackground(Color.black);
        screenSize = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        this.setPreferredSize(screenSize);
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBall() {
        ball = new Ball(GAME_WIDTH / 2, GAME_HEIGHT / 2, BALL_SIZE, BALL_SIZE);
    }

    public void newPaddles() {
        paddle1 = new Paddle(0, 0, 20, 200);
        paddle2 = new Paddle(GAME_WIDTH - 20, 0, 20, 200);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.green);
        g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);
        g.drawLine(0, 0, GAME_WIDTH, 0);
        g.drawLine(0, 0, 0, GAME_HEIGHT);
        g.drawLine(GAME_WIDTH, GAME_HEIGHT, 0, GAME_HEIGHT);
        g.drawLine(GAME_WIDTH, GAME_HEIGHT, GAME_WIDTH, 0);
    }

    public void newScore() {
        score = new Score(GAME_WIDTH);
    }

    @Override
    public void paint(Graphics g) {
        this.paintComponent(g);
        this.draw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    public void draw(Graphics g) {
        ball.draw(g);
        score.draw(g);
        paddle1.draw(g);
        paddle2.draw(g);
    }

    public void move() {
        ball.move();
        paddle1.move();
        paddle2.move();
        checkCollision();
    }

    public void checkCollision() {
        ball.bounceFrom(this.getBounds());
        if (ball.getX() <= 0) {
            score.playerTwoScore();
            newBall();
        }
        if (ball.getX() >= GAME_WIDTH) {
            score.playerOneSore();
            newBall();
        }
        ball.bounceFrom(paddle2);
        ball.bounceFrom(paddle1);
    }

    public boolean isQuit() {
        return isQuit;
    }

    public void setQuit(boolean quit) {
        isQuit = quit;
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (!isQuit()) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                synchronized (this) {
                    move();
                    repaint();
                }
                delta--;
            }
        }


    }

    public class AL implements KeyListener {

        @Override
        public void keyTyped(KeyEvent keyEvent) {
            //omit does nothing
        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == KeyEvent.VK_A) {
                paddle1.setYDirection(-1);
            } else if (keyEvent.getKeyCode() == KeyEvent.VK_Z) {
                paddle1.setYDirection(1);
            }

            if (keyEvent.getKeyCode() == KeyEvent.VK_K) {
                paddle2.setYDirection(-1);
            } else if (keyEvent.getKeyCode() == KeyEvent.VK_M) {
                paddle2.setYDirection(1);
            }

            if (keyEvent.getKeyCode() == KeyEvent.VK_Q) {
                setQuit(true);
            }
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {
            if (Set.of(KeyEvent.VK_A, KeyEvent.VK_Z).contains(keyEvent.getKeyCode())) {
                paddle1.setYDirection(0);
            }
            if (Set.of(KeyEvent.VK_K, KeyEvent.VK_M).contains(keyEvent.getKeyCode())) {
                paddle2.setYDirection(0);
            }
        }
    }

}
