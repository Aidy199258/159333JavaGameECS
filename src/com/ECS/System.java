package com.ECS;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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



class TimeSystem extends System{
    //?Codes to keep tract of time
    //Codes from original game need to be implement for functions
}


