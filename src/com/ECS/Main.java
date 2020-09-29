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





        // Create the Game

        //Run Game Panel
        Game_Panel game_panel= new Game_Panel(900,600);
        game_panel.startGamePanel(game_panel); //Velocity problem - not changed with key pressed


        //Create a list of entities
        ArrayList<Entity> entities = new ArrayList<Entity>();


        // Add an entity/player for the game
        Entity entity = new Entity();
        entities.add(entity);

        /*Needs implementation
        Graphics2D graphics=null;
        LoadImageSystem loadImageSystem = new LoadImageSystem();
        mBackground =loadImageSystem.loadImage("src/com.ECS/Pictures/background/background.png");

        //Add RenderComponent and TransformComponent
        entity.addComponent(new RenderComponent(entity, graphics));
        RenderSystem renderSystem = new RenderSystem();
        renderSystem.setWindowSize(1500, 750);

*/

        //Create platforms
        entity.addComponent(new PlatformComponent(entity,  100, 150, 100, 20));
        entity.addComponent(new PlatformComponent(entity,  350, 200, 100, 20));
        entity.addComponent(new PlatformComponent(entity, 900, 250, 100, 20));
        entity.addComponent(new PlatformComponent(entity, 1100, 350, 100, 20));
        entity.addComponent(new PlatformComponent(entity,  600, 300, 100, 20));
        entity.addComponent(new PlatformComponent(entity, 1300, 200, 100, 20));

        // Create a player
        entity.addComponent(new PlayerComponent(50));
        //entity.addComponent(new PositionComponent(100, 100));

        // Create Coins-Changed to rainbow
        entity.addComponent(new RainbowComponent(entity,  100,  114, 32, 32));
        entity.addComponent(new RainbowComponent(entity, 1300,  164, 32, 32));
        entity.addComponent(new RainbowComponent(entity,  350,  164, 32, 32));
        entity.addComponent(new RainbowComponent(entity,   900,  214, 32, 32));
        entity.addComponent(new RainbowComponent(entity,  600,  264, 32, 32));
        entity.addComponent(new RainbowComponent(entity,  1100,  314, 32, 32));


        //TESTING add Audio Component
        //AudioInputStream audioIS = ;
        //entity.addComponent(new AudioComponent(audioIS));


        //TESTING add Point Component
        entity.addComponent(new PointComponent(10));


        // Get Player Life
        PlayerComponent player = (PlayerComponent)entity.getComponent(PlayerComponent.class);
        System.out.println("Player's Life: " + player.getLife());

        PositionComponent position = (PositionComponent)entity.getComponent(PositionComponent.class);
        //NullPointerException
        // System.out.println("Player's Position: " + position.getX() + ", " + position.getY());



        //Testing TimeSystem/GameTimer


        //Testing ScoreSystem
        ScoreSystem scoreSystem = new ScoreSystem();
        scoreSystem.AddScore(entities);
        System.out.println(scoreSystem.ShowScore(entities));





    }
}
