package com.ECS;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;


public class Game_Panel extends JPanel {

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 1500, HEIGHT = 900;
    public Game_Panel() {


        setFocusable(true);
        setBackground(Color.black);

        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        //System.out.println(isInMenu);
    }
}