package com.ECS;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;


public class Game_Panel extends JPanel {

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 1500, HEIGHT = 900;
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
        while(running){
            update();
            render();
            draw();

        }
    }
    private int x = 0;

    public void update(){

    }
    public void render(){

    }
    public void draw(){

    }
}