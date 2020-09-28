package com.ECS;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class System {

}

/*class User_Input extends System implements KeyListener {

public  static boolean right = false;
public  static boolean left = false;

public static boolean jumping = false;
public static long jumpingTime = 200;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        e.getKeyCode() == KeyEvent.VK_RIGHT){
            right = true;
            //mX++;//this suppose to go right when right key press however no drawing att the moment so i just comment out
        }
        e.getKeyCode() == KeyEvent.VK_LEFT){
            left = true;
            //mX--;//this suppose to go left when right key press however no drawing att the moment so i just comment out
        }
        e.getKeyCode() == KeyEvent.VK_SPACE){
            jumping = true;
            new Thread(new thread()).start();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        e.getKeyCode() == KeyEvent.VK_RIGHT){
            right = false;

        }
        e.getKeyCode() == KeyEvent.VK_LEFT){
            left = false;
        }
        e.getKeyCode() == KeyEvent.VK_SPACE){
            jumping = false;
        }
    }

    public class thread implements Runnable{

        @Override
        public void run() {
            try{
                Thread.sleep(jumpingTime);
                jumping = false;
            } catch (Exception e){
                e.printStackTrace();
                new Thread(this).start();
                System.exit(0);
            }
        }
    }*/


//System to load image
class LoadImageSystem extends System{

    //Load image by providing filename
    public Image LoadImageSystem(String filename) {
        try {
            // Load Image
            Image image = ImageIO.read(new File(filename));

            // Return Image
            return image;
        } catch (IOException e) {
            // Show Error Message
            java.lang.System.out.println("Error: could not load image " + filename);
            java.lang.System.exit(1);
        }
        return null;
    }

    // Loads a sub-image out of an image
    public Image LoadImageSystem(Image source, int x, int y, int w, int h){
        // Check if image is null
        if(source == null) {
            // Print Error message
            java.lang.System.out.println("Error: cannot extract a subImage from a null image.\n");

            // Return null
            return null;
        }

        // Convert to a buffered image
        BufferedImage buffered = (BufferedImage)source;

        // Extract sub image
        Image image = buffered.getSubimage(x, y, w, h);

        // Return image
        return image;

    }

}

class DrawImageSystem extends System{
    Graphics2D mGraphics;

    // Draws an image on the screen at position (x,y)
    public DrawImageSystem(Image image, double x, double y){
        // Check if image is null
        if(image == null) {
            // Print Error message
            java.lang.System.out.println("Error: cannot draw null image.\n");
            return;
        }

        // Draw image on screen at (x,y)
        mGraphics.drawImage(image, (int)x, (int)y, null);

    }

    // Draws an image on the screen at position (x,y)
    public DrawImageSystem(Image image, double x, double y, double w, double h) {
        // Check if image is null
        if(image == null) {
            // Print Error message
            java.lang.System.out.println("Error: cannot draw null image.\n");
            return;
        }
        // Draw image on screen at (x,y) with size (w,h)
        mGraphics.drawImage(image, (int)x, (int)y, (int)w, (int)h, null);
    }
}



 class TimeSystem extends Timer {
    //?Codes to keep tract of time
    //Codes from original game need to be implement for functions

    private static final long serialVersionUID = 1L;
    private int framerate;


    //TimeSystem for GameTimer
    protected TimeSystem(int framerate, ActionListener listener) {
        super(1000/framerate, listener);
        this.framerate = framerate;
    }

    protected void setFramerate(int framerate) {
        if (framerate < 1) framerate = 1;
        this.framerate = framerate;

        int delay = 1000 / framerate;
        setInitialDelay(0);
        setDelay(delay);
    }

    protected int getFramerate() {
        return framerate;
    }
}


//Update Image
//?Update velocity of player
class UpdateSystem extends System{


}

//Needs implementation
class AudioSystem extends System{
    public AudioSystem(ArrayList<Entity> entities){

        for(Entity entity : entities) {

            if(entity.hasComponent(AudioComponent.class)) {

                AudioComponent audioComponent = (AudioComponent)entity.getComponent(AudioComponent.class);






            }

        }
    }
}

//?To deal with Key pressed events
//Incomplete
class EventSystem extends System{


    public EventSystem(ArrayList<Entity> entities, KeyEvent event){
        //keyReleased-Called whenever a key is released
        // Left Arrow
        if(event.getKeyCode() == KeyEvent.VK_LEFT) {
            // Record Left Movement
            //left = false;
        }
        // Right Arrow
        if(event.getKeyCode() == KeyEvent.VK_RIGHT) {
            // Record Right Movement
            //right = false;
        }

    }
}

//Counting points/scores
//Incomplete
class ScoreSystem extends System{
    public void AddScore(ArrayList<Entity> entities){
        for(Entity entity: entities){
            //Player gains one point if touches rainbow/coin
            if(entity.hasComponent(RainbowComponent.class)&&(entity.hasComponent(PointComponent.class))){
                RainbowComponent rainbowComponent = (RainbowComponent)entity.getComponent(RainbowComponent.class);
                PointComponent pointComponent = (PointComponent)entity.getComponent(PointComponent.class);

                pointComponent.GainOnePoint();
            }

            /*
            //??minus points if falling off the platform
            if(entity.hasComponent(?FallComponent.class)&&(entity.hasComponent(PointComponent.class))){
               PointComponent pointComponent = (PointComponent)entity.getComponent(PointComponent.class);

                pointComponent.LoseOnePoint();
            }*/
        }

    }

    public int ShowScore(ArrayList<Entity> entities){
        //Score will be -1 if not initiated
        int score = -1;
        for(Entity entity: entities){
            //Player gains one point if touches rainbow/coin
            if(entity.hasComponent(RainbowComponent.class)&&(entity.hasComponent(PointComponent.class))){
                RainbowComponent rainbowComponent = (RainbowComponent)entity.getComponent(RainbowComponent.class);
                PointComponent pointComponent = (PointComponent)entity.getComponent(PointComponent.class);

                score = pointComponent.GetPoint();
            }

        }
        return score;



    }

}


class GameSystem extends System{
    boolean initialised;

    public long getTime() {
        // Get the current time from the system
        return java.lang.System.currentTimeMillis();
    }

    // Waits for ms milliseconds
    public void sleep(double ms) {
        try {
            // Sleep
            Thread.sleep((long)ms);
        } catch(Exception e) {
            // Do Nothing
        }
    }

    //-------------------------------------------------------
    // Functions to control the framerate
    //-------------------------------------------------------
    // Two variables to keep track of how much time has passed between frames
    long time = 0, oldTime = 0;

    // Returns the time passed since this function was last called.
    public long measureTime() {
        time = getTime();
        if(oldTime == 0) {
            oldTime = time;
        }
        long passed = time - oldTime;
        oldTime = time;
        return passed;
    }




    //Constructor - TO IMPLEMENT
    public void GameSystem(){

    }
    public void InitiateGame(){
        TimeSystem timer = new TimeSystem(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Determine the time step
                double passedTime = measureTime();
                double dt = passedTime / 1000.;

                // Update the Game
                //update(dt);

                // Tell the Game to draw
                //mPanel.repaint();
            }
        });

    }

    public void GameLoop(TimeSystem timer, int framerate){
        initialised = true; // assume init has been called or won't be called

        timer.setFramerate(framerate);
        timer.setRepeats(true);

        // Main loop runs until program is closed
        timer.start();

    }



}