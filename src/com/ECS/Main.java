package com.ECS;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.IOException;
import java.lang.System;
import java.util.ArrayList;
import  java.lang.*;


public class Main {


    static int mScore;
    static boolean mGameOver;
    long mStartTime, mEndTime;
    static GameSystem gameSystem;
    static Game_Panel game_panel;
    // Array of entities
    static ArrayList<Entity> entities;

    public static void restart() {
        entities= new ArrayList<Entity>();

        gameSystem = new GameSystem();
        gameSystem.createGame(gameSystem,30);
        //Adding Default Entities
        gameSystem.AddBackgroundEntity();//Including adding background sound//Index0
        //AudioSystem audioSystem = new AudioSystem(entities);
        gameSystem.AddPlatformEntity();//Index1-3
        gameSystem.AddCoinEntity();//Index4-6
        //gameSystem.AddFloorEntity();
        gameSystem.AddPlayerEntity();//Index7

    }

    // Main Function
    public static void main(String args[]) throws IOException {
        entities= new ArrayList<Entity>();

        // Create the Game
        gameSystem = new GameSystem();
        gameSystem.createGame(gameSystem,30);
        //Adding Default Entities
        gameSystem.AddBackgroundEntity();//Including adding background sound//Index0
        //AudioSystem audioSystem = new AudioSystem(entities);
        gameSystem.AddPlatformEntity();//Index1-3
        gameSystem.AddCoinEntity();//Index4-6
        //gameSystem.AddFloorEntity();
        gameSystem.AddPlayerEntity();//Index7

        //Run Game Panel
        game_panel= new Game_Panel(1500,750);
        game_panel.startGamePanel(game_panel);


        //===================Testing======================//


        //Testing - Get Player Life

        //Testing Play Audio - get audio from Entities
        //AudioComponent backgroundAudio = (AudioComponent)entities.get(0).getComponent(AudioComponent.class);
        //System.out.println("Audio file path is:"+backgroundAudio.getFilePath());



//        //Testing Positions - functioning
//        PositionComponent position = (PositionComponent)entities.get(0).getComponent(PositionComponent.class);
//        System.out.println("Player's Position: " + position.getX() + ", " + position.getY());
//        //Change Player's Position
//        position.setX(100);
//        position.setY(50);
//        System.out.println("Player's Position after change from Main: " + position.getX() + ", " + position.getY());


//        //Testing ScoreSystem-functioning
//        ScoreSystem scoreSystem = new ScoreSystem();
//        scoreSystem.AddScore(entities);
//        System.out.println("Total Score: " + scoreSystem.ShowScore(entities));//-1 as invalid score
//

        //===================Testing======================//


        /*=====================Things to Implement=======================*/
        //TESTING add Audio Component
        //AudioInputStream audioIS = ;
        //entity.addComponent(new AudioComponent(audioIS));

        //Testing TimeSystem/GameTimer



    }
}

