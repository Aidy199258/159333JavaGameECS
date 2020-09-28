package com.ECS;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;


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

    //-------------------------------------------------------
    // Image Functions
    //-------------------------------------------------------

    // Loads an image from file
    public Image loadImage(String filename) {
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

        // Return null
        return null;
    }
    /*
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

     */

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

    //-------------------------------------------------------
     // Time-Related functions
     //-------------------------------------------------------

     // Returns the time in milliseconds
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
class KeyEventSystem extends System{


    public KeyEventSystem(ArrayList<Entity> entities, KeyEvent event){
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


        /*
        // Register a key event dispatcher to get a turn in handling all
        // key events, independent of which component currently has the focus
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {
                        switch (e.getID()) {
                            case KeyEvent.KEY_PRESSED:
                                GameEngine.this.keyPressed(e);
                                return false;
                            case KeyEvent.KEY_RELEASED:
                                GameEngine.this.keyReleased(e);
                                return false;
                            case KeyEvent.KEY_TYPED:
                                GameEngine.this.keyTyped(e);
                                return false;
                            default:
                                return false; // do not consume the event
                        }
                    }
                });


         */

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
    Graphics2D mGraphics;
    Stack<AffineTransform> Transforms;




    public void paintComponent(Graphics graphics) {
        // Get the graphics object
        mGraphics = (Graphics2D)graphics;

        // Reset all transforms
        Transforms.clear();
        Transforms.push(mGraphics.getTransform());

        // Rendering settings
        mGraphics.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));


    }


    //Constructor - TO IMPLEMENT
    public void GameSystem(Entity entity){
        if(entity.hasComponent(RenderComponent.class)&&entity.hasComponent(TransformComponent.class)){
            RenderComponent renderComponent = (RenderComponent) entity.getComponent(RenderComponent.class);
            TransformComponent transformComponent = (TransformComponent)entity.getComponent((TransformComponent.class));

            this.Transforms = transformComponent.GetTransform();
            mGraphics = renderComponent.Graphics();

        }


        //entity.addComponent(RenderSystem renderSystem);
        // This gets called any time the Operating System
        // tells the program to paint itself
        // Paint the game
        RenderSystem renderSystem = new RenderSystem();

        if (initialised) {
            GameSystem.this.paintComponent(renderSystem.mGraphics);
        }



    }


    public void InitiateGame(Entity entity){

        // Initialise Score
        Main.mScore = 0;
        Main.mGameOver = false;

        LoadImageSystem loadImageSystem = new LoadImageSystem();
        // Load background
        Main.mBackground = loadImageSystem.loadImage("Pictures/background/background.png");






    }

    public void GameLoop(TimeSystem timer, int framerate){
        initialised = true; // assume init has been called or won't be called

        timer.setFramerate(framerate);
        timer.setRepeats(true);

        // Main loop runs until program is closed
        timer.start();

    }



}


class RenderSystem extends System {
    public void Process(ArrayList<Entity> entities, Graphics2D g) {

        for (Entity entity : entities) {

            if (entity.hasComponent(TransformComponent.class) && entity.hasComponent(RenderComponent.class)) {

                TransformComponent transform = (TransformComponent) entity.getComponent(TransformComponent.class);

                TransformComponent render = (TransformComponent) entity.getComponent(RenderComponent.class);

                /*
                g.rotate(transform.mA);

                g.translate(transform.mX, transform.mY);

                g.drawImage(render.mImage);



                 */

            }
        }
    }
    JFrame mFrame;
    GamePanel mPanel;
    int mWidth, mHeight;
    Graphics2D mGraphics;
    boolean initialised = false;

    //Constructor
    public void RenderSystem(ArrayList<Entity> entities) {
        setupWindow(1500, 750);
        for (Entity entity : entities) {

            if (entity.hasComponent(TransformComponent.class) && entity.hasComponent(RenderComponent.class)) {

                TransformComponent transform = (TransformComponent) entity.getComponent(TransformComponent.class);

                TransformComponent render = (TransformComponent) entity.getComponent(RenderComponent.class);

            }
        }
    }

    //-------------------------------------------------------
    // Functions for setting up the window
    //-------------------------------------------------------
    // Function to create the window and display it
    public void setupWindow(int width, int height) {
        mFrame = new JFrame();
        mPanel = new GamePanel();

        mWidth = width;
        mHeight = height;

        mFrame.setSize(width, height);
        mFrame.setLocation(200,200);
        mFrame.setTitle("Window");
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.add(mPanel);
        mFrame.setVisible(true);
        mFrame.setResizable(false);

        mPanel.setDoubleBuffered(true);
        mPanel.addMouseListener((MouseListener) this);
        mPanel.addMouseMotionListener((MouseMotionListener) this);


        // Resize the window (insets are just the boarders that the Operating System puts on the board)
        Insets insets = mFrame.getInsets();
        mFrame.setSize(width + insets.left + insets.right, height + insets.top + insets.bottom);
    }

    public void setWindowSize(final int width, final int height) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Resize the window (insets are just the boarders that the Operating System puts on the board)
                Insets insets = mFrame.getInsets();
                mWidth = width;
                mHeight = height;
                mFrame.setSize(width + insets.left + insets.right, height + insets.top + insets.bottom);
                mPanel.setSize(width, height);
            }
        });
    }

    // Return the width of the window
    public int width() {
        return mWidth;
    }

    // Return the height of the window
    public int height() {
        return mHeight;
    }


}