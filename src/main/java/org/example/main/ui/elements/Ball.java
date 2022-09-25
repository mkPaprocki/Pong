package org.example.main.ui.elements;

import java.awt.*;

public class Ball extends Rectangle {

    private int xDirection;
    private int yDirection;

    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        xDirection = (Math.random() > 0.5 ? 1 : -1);
        yDirection = (Math.random() > 0.5 ? 1 : -1);
    }

    public void bounceFrom(Object object) {
        if (object instanceof Paddle) {
            Paddle paddle = (Paddle) object;
            if(this.intersects(paddle)) {
                setXDirection(-1);
            }
        } else if (object instanceof Rectangle) {
            Rectangle court = (Rectangle) object;
            if ((this.getMinY() <= (court.getMinY()) || this.getMaxY() >= court.getMaxY())) {
                setYDirection(-1);
            }
        }
    }

    public void setYDirection(int randomYDirection) {
        yDirection = (randomYDirection * this.yDirection);
    }

    public void setXDirection(int randomXDirection) {
        xDirection = (randomXDirection * this.xDirection);
    }

    public int getxDirection() {
        return xDirection;
    }

    public int getyDirection() {
        return yDirection;
    }

    public void move() {
        this.move((int) this.getX() + getxDirection(), (int) this.getY() + getyDirection());
    }

    public void draw(Graphics g) {
        g.setColor(Color.blue);
        g.fillOval((int) this.getX(), (int) this.getY(), (int) this.getHeight(), (int) this.getWidth());
    }
}
