package org.example.main.ui.elements;

import java.awt.*;

import static org.example.main.ui.elements.GamePanel.GAME_HEIGHT;

public class Paddle extends Rectangle {

    private int yDirection;

    public Paddle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void setYDirection(int yDirection) {
        this.yDirection = yDirection;
    }

    public int getyDirection() {
        return yDirection;
    }

    public void move() {
        if ((this.getMinY() < 0 && getyDirection() < 0) || (this.getMaxY() >= GAME_HEIGHT && getyDirection() > 0))
            setYDirection(0);
        this.move((int) this.getX(), (int) this.getY() + getyDirection());
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight());
    }
}
