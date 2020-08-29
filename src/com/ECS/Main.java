package com.ECS;

import javax.swing.*;

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


        Entity.PlayerEntity player = new Entity.PlayerEntity();





    }
}
