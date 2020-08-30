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


        Entity player = new Entity();
        //Adding playerEntity part to the Entity
        Entity.PlayerEntity playerE = new Entity.PlayerEntity();
        player.addEntity(playerE, Entity.PlayerEntity.STARTLIFE);

        //Adding coinEntity part to the Entity
        player.addEntity(new Entity.CoinEntity(0));


        //Testing getting life value for the player
        System.out.println("Player's life: "+ player.getEntity(playerE));
        

        //List of entities - game players
        List<Entity> entities = new ArrayList<Entity>();


        entities.add(player);







    }
}
