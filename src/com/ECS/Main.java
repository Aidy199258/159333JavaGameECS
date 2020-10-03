package com.ECS;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.System;
import java.util.ArrayList;
import java.util.List;
import  java.lang.*;


public class Main {


    static int mScore;
    static boolean mGameOver;
    long mStartTime, mEndTime;
    // Array of entities
    static ArrayList<Entity> entities= new ArrayList<Entity>();



    static Image mBackground;



    // Main Function
    public static void main(String args[]) throws IOException {



        // Create the Game
        GameSystem gameSystem = new GameSystem();
        gameSystem.createGame(gameSystem,30);
        gameSystem.AddBackgroundEntity();//Including adding background sound//Index0
        //audioSystem = new AudioSystem();

        gameSystem.AddPlatformEntity();//Index1-3
        gameSystem.AddCoinEntity();//Index4-6
        //gameSystem.AddFloorEntity();
        gameSystem.AddPlayerEntity();//Index7
        int index = Main.entities.indexOf("Background");
        System.out.println("Index of Background Entity:"+index);
        PointComponent player = (PointComponent)Main.entities.get(7).getComponent(PointComponent.class);//Get playerEntity from entities ArrayList
        int gameScore =player.GetPoint();

        if(gameScore==0){
            System.out.println("Game just started. Time to collect coins!");
        }else if (gameScore>0&&gameScore<4){
            if(gameScore==3){
                System.out.println("You won!");
            }else {
                System.out.println("Game running. Enjoy!");
            }
        }else {
            System.out.println("Ops. Game Score Error!");
        }






        //Run Game Panel
        Game_Panel game_panel= new Game_Panel(900,600);
        game_panel.startGamePanel(game_panel);


        //===================Testing======================//


//        //A temporary entity to get values - Functioning
//        Entity playerEntity = new Entity();
//

        //Entities Indexes: 0-Player,1-Platforms,2-Coin
        //Testing - Get Player Life
        //PlayerComponent player=(PlayerComponent)entities.get(0).getComponent(PlayerComponent.class);
        //System.out.println("Player's Life: " + player.getLife());

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

