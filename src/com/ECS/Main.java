package com.ECS;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.lang.System;
import java.util.ArrayList;
import java.util.List;
import  java.lang.*;


public class Main {


    static int mScore;
    static boolean mGameOver;
    long mStartTime, mEndTime;


    static Image mBackground;



    // Main Function
    public static void main(String args[]) {


        Graphics2D Graphics;







        //Run Game Panel
        Game_Panel game_panel= new Game_Panel(900,600);
        game_panel.startGamePanel(game_panel); //Velocity problem - not changed with key pressed


        //Create a list of entities
        ArrayList<Entity> entities = new ArrayList<Entity>();

        // Create the Game
        GameSystem gameSystem = new GameSystem();
        gameSystem.CreateEntities(entities);




        //___________________________Testing_________________________
        //A temporary entity to get values
        Entity playerEntity = new Entity();


        //Entities Indexes: 0-Player,1-Platforms,2-Coin
        //Testing - Get Player Life
        PlayerComponent player=(PlayerComponent)entities.get(0).getComponent(PlayerComponent.class);
        System.out.println("Player's Life: " + player.getLife());

        PositionComponent position = (PositionComponent)entities.get(0).getComponent(PositionComponent.class);
        //NullPointerException
        System.out.println("Player's Position: " + position.getX() + ", " + position.getY());



        //Testing ScoreSystem
        ScoreSystem scoreSystem = new ScoreSystem();
        scoreSystem.AddScore(entities);
        System.out.println("Total Score: " + scoreSystem.ShowScore(entities));//-1 as invalid score



        /*=====================Things to Implement=======================*/
        //TESTING add Audio Component
        //AudioInputStream audioIS = ;
        //entity.addComponent(new AudioComponent(audioIS));

        //Testing TimeSystem/GameTimer



    }
}
