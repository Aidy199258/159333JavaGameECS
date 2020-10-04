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
    // Array of entities
    //public static ArrayList<Entity> entities= new ArrayList<Entity>();

    //Initialising default Entities via GameSystem

    //GameSystem gameSystem;


    //Create a local reference for main Entities ListArray from main Game
    //ArrayList<Entity> entities;


    // Render System
    RenderSystem renderSystem;
    MovementSystem movementSystem;
    KeyEventSystem keyEventSystem;
    ScoreSystem scoreSystem;
    static AudioInputStream audioIS;





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

        String Audio_Clip = "Audios/BackgroundAudio.wav";
        am.AudioManagement(Audio_Clip);
        am.play();

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

            // while(((now - LastUpdateTime)>TBU)&&(UpdateCount<MUBR)){
            // update();
            // input();
            // render();
            // draw();

            while((now - LastUpdateTime) < TBU) {
                now = java.lang.System.nanoTime();
            }
            double dt = (now - LastUpdateTime) / 1000000000.0;


            LastUpdateTime = now;
            // if((now - LastUpdateTime)>TBU){
            //     LastUpdateTime = now - TBU;
            // }

            update(dt);
            input();
            draw();

            // LastRenderTime = now;
            FrameCount++;

            // int ThisSecond = (int) (LastUpdateTime/1000000000);
            // if (ThisSecond > LastSecondTime){
            //     if(FrameCount!=OldFrameCount){
            //         java.lang.System.out.println("New Second"+ThisSecond+" "+FrameCount);
            //         OldFrameCount = FrameCount;
            //     }
            //     FrameCount = 0;
            //     LastSecondTime = ThisSecond;
            // }



        }
    }
    private int x = 0;

    public void update(double dt){

        //entities= GameSystem.GetGameEntities();
        movementSystem.Process(Main.entities, dt);
        scoreSystem.ScoreUpdate(Main.entities,dt);
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


    }

    public void render(){
        // if(g2d!=null){
        //     g2d.setColor(new Color(66,136,244));
        //     g2d.fillRect(0,0,width,height);

        //     // Ask RenderSystem to render all the entities
        //     renderSystem.Process(entities, g2d);
        // }
    }

    public void draw(){
        // // System.out.println("Drawing");
        // Graphics g2 = (Graphics)this.getGraphics();
        // g2.drawImage(img,0,0,width,height,null);
        // g2.dispose();
        repaint();
    }

    public void paintComponent(Graphics graphics) {
        //entities=GameSystem.GetGameEntities();
        renderSystem.Process(Main.entities, (Graphics2D)graphics);
    }

    public void input(){

    }

    public void Key_Released(KeyEvent e) {
        //keyEventSystem.KeyPressed(entities, e);
        keyEventSystem.KeyReleased(Main.entities, e);
    }

   public void Key_Press(KeyEvent e) {
        keyEventSystem.KeyPressed(Main.entities, e);
        //keyEventSystem.KeyReleased(entities, e);
    }

    public void startGamePanel(Game_Panel gamepanel){
        //Testing


        JFrame frame = new JFrame();
        ////Default WIDTH and HEIGHT
        //gamepanel = new Game_Panel(Game_Panel.WIDTH,Game_Panel.HEIGHT);
        //audioSystem = new AudioSystem();




        frame.add(gamepanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("159333 Java Game");

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        // Able to resize for testing for now
        // Frame.setResizable(false);

        gamepanel.setDoubleBuffered(true);
        //game_panel.addMouseListener(this);
        //game_panel.addMouseMotionListener(this);


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
        // // Resize the window (insets are just the boarders that the Operating System puts on the board)
        // Insets insets = Frame.getInsets();
        // Frame.setSize(width + insets.left + insets.right, height + insets.top + insets.bottom);





    }

}