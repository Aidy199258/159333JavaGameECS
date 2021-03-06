package com.ECS;

import javax.imageio.ImageIO;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.lang.*;
import java.lang.System;
import java.util.Random;

import static com.ECS.VelocityComponent.gravity;


public class Game_Panel extends JPanel {


    // Render System
    RenderSystem renderSystem;
    MovementSystem movementSystem;
    KeyEventSystem keyEventSystem;
    ScoreSystem scoreSystem;
    AudioManagement audioManagement;
    static AudioInputStream audioIS;

    GraphicComponent graphicComponent;


    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 1500, HEIGHT = 750;
    public static int width;
    public static int height;
    private Thread thread ;
    private BufferedImage img;
    private Graphics2D g2d;
    private boolean running = false;
    public static boolean left= false;
    public static boolean right = false;

    public static float player_inix = 100;//initial x position
    public static float player_iniy = 400;// initital y position

    public AudioManagement am = new AudioManagement();

    public static String Audio_Clip = "Audios/BackgroundAudio.wav";
    private int x = 0;
    private int winningScore = 6;
    private int curScore = -1;
    private String msg = "";

    public void restart() {
        Main.restart();
        curScore = -1;
        msg = "";
    }

    public Game_Panel(int width, int height) {


        setFocusable(true);

        this.width = width; this.height = height;


        setPreferredSize(new Dimension(width,height));
        //System.out.println(isInMenu);

        requestFocus();



        // ========================================
        // Set up Entities and Systems
        // ========================================


        renderSystem = new RenderSystem();
        movementSystem = new MovementSystem();
        keyEventSystem = new KeyEventSystem();
        scoreSystem = new ScoreSystem();
        audioManagement = new AudioManagement();
        audioManagement.AudioManagement(Audio_Clip);
        audioManagement.play();

    }

    public void addNotify(){
        super.addNotify();
        if (thread == null){
            thread = new Thread(this::run,"Gamethread");
            thread.start();
        }
    }

    public void init(){
        running = true;
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = (Graphics2D)img.getGraphics();
    }
    public void run(){
        init();
        final double GAME_HERTZ = 60.0;
        final double TBU = 1000000000/GAME_HERTZ;//Time before Update
        final int MUBR = 5;//Must Update Before Render

        double LastUpdateTime = java.lang.System.nanoTime();
        double LastRenderTime;
        final double TARGET_FPS = 60;
        final double TTBR = 1000000000/TARGET_FPS;//Total time before render

        int FrameCount = 0;
        int LastSecondTime = (int)(LastUpdateTime/1000000000);
        int OldFrameCount = 0;


        while(running){
            double now = java.lang.System.nanoTime();
            int UpdateCount = 0;


            while((now - LastUpdateTime) < TBU) {
                now = java.lang.System.nanoTime();
            }
            double dt = (now - LastUpdateTime) / 1000000000.0;


            LastUpdateTime = now;


            if (curScore < winningScore) {
                update(dt);
            }
            input();
            draw();

            FrameCount++;


        }
    }

    public void update(double dt){

        movementSystem.Process(Main.entities, dt);
        scoreSystem.ScoreUpdate(Main.entities,dt);

        for(Entity entity: Main.entities){
            if(entity.hasComponent(PointComponent.class)) {
                PointComponent score=(PointComponent) entity.getComponent(PointComponent.class);
                int newScore = score.GetPoint();

                if(curScore < 0){
                    curScore = 0;
                    msg = "Game just started. Time to collect coins!";
                    System.out.println(msg);
                } else if ( newScore > 0 && curScore != newScore){
                    curScore = newScore;
                    if (newScore == 1) {
                        msg = "Good Start";
                        System.out.println(msg);
                    } else if (newScore == 2) {
                        msg = "Nice!";
                        System.out.println(msg);
                    } else if (newScore == 3) {
                        msg = "Well Done!";
                        System.out.println(msg);
                    } else if (newScore == 4) {
                        msg = "Almost There!";
                        System.out.println(msg);
                    } else if (newScore == 5) {
                        msg = "One More!";
                        System.out.println(msg);
                    } else if (newScore == 6) {
                        msg = "OMG (@ v @) You WON!";
                        System.out.println(msg);
                    }
                }
            }
        }
    }



    public void draw(){

        repaint();
    }

    public void paintComponent(Graphics graphics) {

        renderSystem.Process(Main.entities, (Graphics2D)graphics);
        graphicComponent = new GraphicComponent(graphics);
        graphicComponent.drawText(50, 50, "Score: " + String.valueOf(curScore));
        if (curScore >= winningScore) {
            graphicComponent.drawText(350, 50, msg);
            graphicComponent.drawText(500, 500, "Press R to start a new game");
        } else {
            graphicComponent.drawText(350, 50, msg);
        }
    }

    public void input(){

    }

    public void Key_Released(KeyEvent e) {

        keyEventSystem.KeyReleased(Main.entities, e);
    }

   public void Key_Press(KeyEvent e) {
        keyEventSystem.KeyPressed(Main.entities, e);

    }

    public void startGamePanel(Game_Panel gamepanel){


        JFrame frame = new JFrame();

        frame.add(gamepanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("159333 Java Game");

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);


        gamepanel.setDoubleBuffered(true);

        // Register a key event dispatcher to get a turn in handling all
        // key events, independent of which component currently has the focus
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {
                        switch (e.getID()) {
                            case KeyEvent.KEY_PRESSED:
                                // keyEventSystem.KeyPressed(entities, e);
                                Key_Press(e);
                                if((e.getKeyCode() == KeyEvent.VK_R)) {
                                    restart();
                                }
                                // System.out.println("Pressed");
                                // GameEngine.this.keyPressed(e);
                                return true;
                            case KeyEvent.KEY_RELEASED:
                                // GameEngine.this.keyReleased(e);
                                Key_Released(e);
                                return true;
                            case KeyEvent.KEY_TYPED:
                                // GameEngine.this.keyTyped(e);
                                return false;
                            default:
                                return false; // do not consume the event
                        }
                    }
                });




    }

}
