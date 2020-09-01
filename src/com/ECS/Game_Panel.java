package com.ECS;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;



public class Game_Panel extends JPanel {

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 900, HEIGHT = 600;
    public static int width;
    public static int height;
    private Thread thread ;
    private BufferedImage img;
    private Graphics2D g2d;
    private boolean running = false;
    public Game_Panel(int width, int height) {


        setFocusable(true);
        //setBackground(Color.black);

        this.width = width; this.height = height;


        setPreferredSize(new Dimension(width,height));
        //System.out.println(isInMenu);

        requestFocus();


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
            while(((now - LastUpdateTime)>TBU)&&(UpdateCount<MUBR)){
                update();
                input();
                render();
                draw();

            }
            if((now - LastUpdateTime)>TBU){
                LastUpdateTime = now - TBU;
            }

            input();
            render();
            draw();
            LastRenderTime = now;
            FrameCount++;

            int ThisSecond = (int) (LastUpdateTime/1000000000);
            if (ThisSecond > LastSecondTime){
                if(FrameCount!=OldFrameCount){
                    java.lang.System.out.println("New Second"+ThisSecond+" "+FrameCount);
                    OldFrameCount = FrameCount;
                }
                FrameCount = 0;
                LastSecondTime = ThisSecond;
            }



        }
    }
    private int x = 0;

    public void update(){

    }

    public void render(){
        if(g2d!=null){
            g2d.setColor(new Color(66,136,244));
            g2d.fillRect(0,0,width,height);

        }
    }

    public void draw(){
        Graphics g2 = (Graphics)this.getGraphics();
        g2.drawImage(img,0,0,width,height,null);
        g2.dispose();
    }

    public void input(){

    }
}