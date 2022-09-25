package org.example.main.ui.elements;

import javax.swing.*;
import java.awt.*;

public class Score extends JComponent {

    private final int courtWidth;
    private int playerOneScore;
    private int playerTwoScore;

    public Score(int courtWidth) {
        this.courtWidth = courtWidth;
    }

    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        g.drawString(getPlayerOneScoreString(), courtWidth / 4 - 50, 20);
        g.drawString(getPlayerTwoScoreString(), 3 * courtWidth / 4 - 50, 20);
        super.paintComponent(g);
    }

    private String getPlayerOneScoreString() {
        return "Player One: " + playerOneScore;
    }


    private String getPlayerTwoScoreString() {
        return "Player Two: " + playerTwoScore;
    }

    public void playerOneSore() {
        playerOneScore++;
    }

    public void playerTwoScore() {
        playerTwoScore++;
    }
}
