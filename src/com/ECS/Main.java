package com.ECS;

import javax.swing.*;
import java.lang.System;
import java.util.ArrayList;
import java.util.List;
import  java.lang.*;

public class Main {


    public static  Game_Panel game_panel;

    public Main() {

        //Testing

        JFrame frame = new JFrame();
        game_panel = new Game_Panel();



        frame.add(game_panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("159333 Java Game");

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }


    // Main Function
    public static void main(String args[]) {

        // Create the Game

        //Run Game Panel
        new Main();


        // Create an entity for the player
        Entity entity = new Entity();

        // Add a player component
        entity.addComponent(new PlayerComponent(50));
        //entity.addComponent(new PositionComponent(100, 100));

        // Get Player Life
        PlayerComponent player = (PlayerComponent)entity.getComponent(PlayerComponent.class);
        System.out.println("Player's Life: " + player.getLife());

        PositionComponent position = (PositionComponent)entity.getComponent(PositionComponent.class);
        System.out.println("Player's Position: " + position.getX() + ", " + position.getY());





    }
}
