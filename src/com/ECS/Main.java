package com.ECS;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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

        player.add(new Entity.PlayerEntity(1));
        player.add(new Entity.CoinEntity(0));


        //List of entities - game players
        List<Entity> entities = new ArrayList<Entity>();

        entities.add(player);






    }
}
