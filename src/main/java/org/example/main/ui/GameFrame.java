package org.example.main.ui;

import org.example.main.ui.elements.GamePanel;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    GamePanel gamePanel = new GamePanel();

    public GameFrame() throws HeadlessException {
        this.getContentPane().add(gamePanel);
        this.setBackground(Color.BLACK);
        this.setResizable(false);
        this.pack();
        this.setSize(GamePanel.GAME_WIDTH + 1, GamePanel.GAME_HEIGHT + 25);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
