package org.example.main.ui.elements;

import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {

    private final Random randomNumGenerator;
    private int xDirection;
    private int yDirection;

    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.randomNumGenerator = new Random();
        xDirection = (Math.random() > 0.5 ? 1 : -1);
        yDirection = (Math.random() > 0.5 ? 1 : -1);
    }

    private static float revereDirection(int direction) {
        return -1 * Math.signum(direction);
    }

    public void bounceFrom(Object object) {
        if (object instanceof Paddle) {
            Paddle paddle = (Paddle) object;
            if (this.intersects(paddle)) {
                setXDirection((int) ((getRandomInt(10) + 1) * revereDirection(getXirection())));
                setYDirection((getRandomInt(5) + 1));
            }
        } else if (object instanceof Rectangle) {
            Rectangle court = (Rectangle) object;
            if ((this.getMinY() <= (court.getMinY()) || this.getMaxY() >= court.getMaxY())) {
                setYDirection(-1 * getYDirection());
            }
        }
    }

    private int getRandomInt(int range) {
        return this.randomNumGenerator.nextInt(range);
    }

    public void setYDirection(int randomYDirection) {
        yDirection = randomYDirection;
    }

    public void setXDirection(int randomXDirection) {
        xDirection = randomXDirection;
    }

    public int getXirection() {
        return xDirection;
    }

    public int getYDirection() {
        return yDirection;
    }

    public void move() {
        this.translate(getXirection(), getYDirection());
    }

    public void draw(Graphics g) {
        g.setColor(Color.blue);
        g.fillOval((int) this.getX(), (int) this.getY(), (int) this.getHeight(), (int) this.getWidth());
    }
}
