package com.ECS;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import static com.ECS.Game_Panel.entities;
import static com.ECS.VelocityComponent.gravity;

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

    Graphics2D mGraphics;
    public LoadImageSystem(){

    }
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

    // Draws an image on the screen at position (x,y)
    public void drawImage(Image image, double x, double y) {
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
    public void drawImage(Image image, double x, double y, double w, double h) {
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

class DrawImageSystem extends System{
    Graphics2D mGraphics;

    //Load images at initialisation of Game Panel
    public DrawImageSystem(ArrayList<Entity> entities){
        //Draw Character


        //Draw Platforms

        //Draw Coins
    }

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



    public TimeSystem GetTimer(){
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
        return timer;
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

class KeyEventSystem extends System {


    public KeyEventSystem() {

    }

    public void KeyReleased(ArrayList<Entity> entities, KeyEvent e) {
        for (Entity entity : entities) {
            // Need a Position & a Velocity Component
            if (entity.hasComponent(KeyComponent.class) && entity.hasComponent(VelocityComponent.class)) {
                // Get Position & Velocity Components
                // PositionComponent position = (PositionComponent)entity.getComponent(PositionComponent.class);

                // KeyComponent key = (KeyComponent)entity.getComponent(KeyComponent.class);
                VelocityComponent velocity = (VelocityComponent) entity.getComponent(VelocityComponent.class);


                if ((e.getKeyCode() == KeyEvent.VK_RIGHT)) {
                    // velocity.setX(velocity.Get_VelocityX() * -1);
                    Game_Panel.right = false;
                    java.lang.System.out.println("New Velocity: " + velocity.Get_VelocityX());
                    VelocityComponent.setX(0);
                }
                if ((e.getKeyCode() == KeyEvent.VK_LEFT)) {
                    // velocity.setX(velocity.Get_VelocityX() * -1);
                    Game_Panel.left = false;
                    //java.lang.System.out.println("New Velocity: " + velocity.Get_VelocityX());
                    VelocityComponent.setX(0);
                }
                if ((e.getKeyCode() == KeyEvent.VK_SPACE)) {
                    // velocity.setX(velocity.Get_VelocityX() * -1);
                    gravity = false;
                    java.lang.System.out.println("Releasing");
                    java.lang.System.out.println("Gravity is now:" + gravity);
                    //VelocityComponent.setY(100);

                }


                java.lang.System.out.println("New Velocity: " + velocity.Get_VelocityX());

            }
        }
    }


    public void KeyPressed(ArrayList<Entity> entities, KeyEvent e) {
        for (Entity entity : entities) {
            // Need a Position & a Velocity Component
            if(entity.hasComponent(KeyComponent.class) && entity.hasComponent(VelocityComponent.class)) {
                // Get Position & Velocity Components
                // PositionComponent position = (PositionComponent)entity.getComponent(PositionComponent.class);

                // KeyComponent key = (KeyComponent)entity.getComponent(KeyComponent.class);
                VelocityComponent velocity = (VelocityComponent)entity.getComponent(VelocityComponent.class);



                if((e.getKeyCode() == KeyEvent.VK_LEFT)) {
                    // velocity.setX(velocity.Get_VelocityX() * -1);
                    Game_Panel.left = true;
                    java.lang.System.out.println("New Velocity: " + velocity.Get_VelocityX());
                    VelocityComponent.setX(-50);
                }
                if((e.getKeyCode() == KeyEvent.VK_RIGHT)) {
                    // velocity.setX(velocity.Get_VelocityX() * -1);
                    Game_Panel.right = true;
                    VelocityComponent.setX(50);
                }

                if((e.getKeyCode() == KeyEvent.VK_SPACE)) {
                    gravity = true;
                    java.lang.System.out.println("Gravity is now:" + gravity);
                }

                java.lang.System.out.println("New Velocity: " + velocity.Get_VelocityX());

            }
        }
    }




    // public KeyEventSystem(ArrayList<Entity> entities, KeyEvent event){
    //     //keyReleased-Called whenever a key is released
    //     // Left Arrow
    //     if(event.getKeyCode() == KeyEvent.VK_LEFT) {
    //         // Record Left Movement
    //         //left = false;
    //     }
    //     // Right Arrow
    //     if(event.getKeyCode() == KeyEvent.VK_RIGHT) {
    //         // Record Right Movement
    //         //right = false;
    //     }



    //     // Register a key event dispatcher to get a turn in handling all
    //     // key events, independent of which component currently has the focus
    //     KeyboardFocusManager.getCurrentKeyboardFocusManager()
    //             .addKeyEventDispatcher(new KeyEventDispatcher() {
    //                 @Override
    //                 public boolean dispatchKeyEvent(KeyEvent e) {
    //                     switch (e.getID()) {
    //                         case KeyEvent.KEY_PRESSED:
    //                             GameEngine.this.keyPressed(e);
    //                             return false;
    //                         case KeyEvent.KEY_RELEASED:
    //                             GameEngine.this.keyReleased(e);
    //                             return false;
    //                         case KeyEvent.KEY_TYPED:
    //                             GameEngine.this.keyTyped(e);
    //                             return false;
    //                         default:
    //                             return false; // do not consume the event
    //                     }
    //                 }
    //             });




    // }
}

//Counting points/scores
//Incomplete
class ScoreSystem extends System{
    public void AddScore(ArrayList<Entity> entities){
        for(Entity entity: entities){
            //Player gains one point if touches rainbow/coin
            if(entity.hasComponent(CoinComponent.class)&&(entity.hasComponent(PointComponent.class))){
                CoinComponent coinComponent = (CoinComponent)entity.getComponent(CoinComponent.class);
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

    public static int ScoreUpdate(ArrayList<Entity> entities){
        //Score will be -1 if not initiated
        int score = -1;
        for(Entity entity: entities){
            //Player gains one point if touches rainbow/coin
            if(entity.hasComponent(CoinComponent.class)&&(entity.hasComponent(PointComponent.class))){
                CoinComponent coinComponent = (CoinComponent)entity.getComponent(CoinComponent.class);
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
    Stack<AffineTransform> mTransforms;

    //Run first to Call Create Game
    public void createGame(GameSystem gameSystem) {

        // Call CreateGame
        createGame(gameSystem,30);


    }

    //Run second
    public void createGame(GameSystem gameSystem, int framerate) {
        // Initialise Game
        gameSystem.InitiateGame();


        //gameSystem.CreateEntities();

        //Loading Pictures to Window
        //RenderSystem renderSystem = new RenderSystem();
        //renderSystem.Process(entities,mGraphics);
        //renderSystem.Process(entities, (Graphics2D)graphics);

        ActionListener listener = null;

        TimeSystem timeSystem = new TimeSystem(framerate,listener);

        // Start the Game
        //Testing Loop?duplicate loop with Game_Panel?
        //gameSystem.GameLoop(timeSystem.GetTimer(),framerate);
    }

    //Initiate Default Stats apart from Entities
    public void InitiateGame(){

        // Initialise Score
        Main.mScore = 0;
        Main.mGameOver = false;

        //LoadImageSystem loadImageSystem = new LoadImageSystem();
        // Load background
        //Main.mBackground = loadImageSystem.loadImage("Pictures/background/background.png");

    }


    public static void TestingDrawPic(ArrayList<Entity> entities,Graphics2D g){

        PositionComponent playerPosition=(PositionComponent)entities.get(0).getComponent(PositionComponent.class);
        RenderComponent playerRender=(RenderComponent) entities.get(0).getComponent(RenderComponent.class);
        playerRender.getImage();
        // Save current transform
        AffineTransform transform = g.getTransform();

        // Move into correct position
        g.translate(playerPosition.getX(), playerPosition.getY());

        // Draw the Render component
        g.drawImage(playerRender.getImage(), 0, 0, null);

        // Reset Transform
        g.setTransform(transform);

    }

    //Entities Stats default - NEEDS IMPLEMENTATION
    public void CreateEntities(){
        //Add PlayerEntity - Index 0
        AddPlayerEntity();

        //Create platforms - Index 1
        AddPlatformEntity();

        //Create Coins - Index 2
        AddCoinEntity();

        //Create Background - Index 3
        AddBackgroundEntity();




    }
    public void AddPlayerEntity(){
        //Needs PlayerComponent,PositionComponent,VelocityComponent,RenderComponent
        Entity Player = new Entity();

        Player.addComponent(new PlayerComponent(10));
        Player.addComponent(new VelocityComponent(0,0,false));
        Player.addComponent(new PositionComponent(100, 590, 0));
        Player.addComponent(new KeyComponent());
        //Player images divided into three - resting, walking, jumping
        //NEEDS IMPLEMENT
        RenderSystem.LoadPicturesToEntity(Player,"Pictures/player/idle1.png");
        entities.add(Player);


    }
    public void AddPlatformEntity(){

        Entity Platform1 = new Entity();
        Entity Platform2 = new Entity();
        Entity Platform3 = new Entity();
        Entity Platform4 = new Entity();
        Entity Platform5 = new Entity();
        Entity Platform6 = new Entity();
        Platform1.addComponent(new PositionComponent(1100, 300, 0));
        Platform2.addComponent(new PositionComponent(700, 300, 0));
        Platform3.addComponent(new PositionComponent(300, 300, 0));
        Platform4.addComponent(new PositionComponent(0, 500, 0));
        Platform5.addComponent(new PositionComponent(100, 300, 20));
        Platform6.addComponent(new PositionComponent(200,50,0));
        //Below is equal to Platforms.addComponent(new RenderComponent(SelectedImage));
        RenderSystem.LoadPicturesToEntity(Platform1,"Pictures/platform/platform.png");
        RenderSystem.LoadPicturesToEntity(Platform2,"Pictures/platform/platform.png");
        RenderSystem.LoadPicturesToEntity(Platform3,"Pictures/platform/platform.png");
        RenderSystem.LoadPicturesToEntity(Platform4,"Pictures/platform/platform.png");
        RenderSystem.LoadPicturesToEntity(Platform5,"Pictures/platform/platform.png");
        RenderSystem.LoadPicturesToEntity(Platform6,"Pictures/platform/platform.png");
        entities.add(Platform1);
        entities.add(Platform2);
        entities.add(Platform3);
        //entities.add(Platform4);
        //entities.add(Platform5);
        //entities.add(Platform6);

    }
    public void AddCoinEntity(){
        Entity Coin1 = new Entity();
        Entity Coin2 = new Entity();
        Entity Coin3 = new Entity();
        Entity Coin4 = new Entity();
        Entity Coin5 = new Entity();
        Coin1.addComponent(new CoinComponent(5,5,5,5,5));
        Coin1.addComponent(new PositionComponent(700, 350,  0));
        Coin2.addComponent(new PositionComponent(300,350,  0));
        Coin3.addComponent(new PositionComponent(500, 350,  0));
        Coin4.addComponent(new PositionComponent(200,  100,  2));
        Coin5.addComponent(new PositionComponent(600, 200,  10));

        //Below is equal to Platforms.addComponent(new RenderComponent(SelectedImage));
        RenderSystem.LoadPicturesToEntity(Coin1,"Pictures/coin/coin.png");
        RenderSystem.LoadPicturesToEntity(Coin2,"Pictures/coin/coin.png");
        RenderSystem.LoadPicturesToEntity(Coin3,"Pictures/coin/coin.png");
        RenderSystem.LoadPicturesToEntity(Coin4,"Pictures/coin/coin.png");
        RenderSystem.LoadPicturesToEntity(Coin5,"Pictures/coin/coin.png");
        entities.add(Coin1);
        entities.add(Coin2);
        entities.add(Coin3);
        //entities.add(Coin4);
        //entities.add(Coin5);


    }

    public void AddBackgroundEntity(){
        Entity Background = new Entity();
        Background.addComponent(new PositionComponent(0, 0, 0));
        RenderSystem.LoadPicturesToEntity(Background,"Pictures/background/background.png");
        entities.add(Background);

    }

    public void AddFloorEntity(){
        Entity Floor = new Entity();
        Floor.addComponent(new PositionComponent(0, 700, 0));
        RenderSystem.LoadPicturesToEntity(Floor,"Pictures/platform/floor.png");
        entities.add(Floor);


    }
    public void paintComponent(Graphics graphics) {
        // Get the graphics object
        mGraphics = (Graphics2D)graphics;

        // Reset all transforms
        mTransforms.clear();
        mTransforms.push(mGraphics.getTransform());

        // Rendering settings
        mGraphics.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));


    }



    //Constructor - TO IMPLEMENT
    public void GameSystem(Entity entity){
        if(entity.hasComponent(RenderComponent.class)&&entity.hasComponent(TransformComponent.class)){
            RenderComponent renderComponent = (RenderComponent) entity.getComponent(RenderComponent.class);
            TransformComponent transformComponent = (TransformComponent)entity.getComponent((TransformComponent.class));



            this.mTransforms = transformComponent.GetTransform();
            mGraphics = renderComponent.Graphics();
            // Create graphics transform stack
            mTransforms = new Stack<AffineTransform>();


        }



        // This gets called any time the Operating System
        // tells the program to paint itself
        // Paint the game
        RenderSystem renderSystem = new RenderSystem();

        if (initialised) {
            GameSystem.this.paintComponent(renderSystem.mGraphics);
        }



    }




    public void GameLoop(TimeSystem timer, int framerate){
        initialised = true; // assume init has been called or won't be called

        timer.setFramerate(framerate);
        timer.setRepeats(true);

        // Main loop runs until program is closed
        timer.start();

    }
}



class MovementSystem extends System {

    //public static long jumpingTime = 200;


    public void Process(ArrayList<Entity> entities, double dt) {
        for (Entity entity : entities) {
            // Need a Position & a Velocity Component
            if(entity.hasComponent(PositionComponent.class) && entity.hasComponent(VelocityComponent.class)) {
                // Get Position & Velocity Components
                PositionComponent position = (PositionComponent)entity.getComponent(PositionComponent.class);
                VelocityComponent velocity = (VelocityComponent)entity.getComponent(VelocityComponent.class);

                // Change Position based on Velocity
                position.setX(position.getX() + velocity.Get_VelocityX() * (float)dt);
                position.setY(position.getY() + velocity.Get_VelocityY() * (float)dt);

                //java.lang.System.out.println("Velocity: " + velocity.Get_VelocityX());


                if(gravity == true) {
                    // velocity.setY(-10);
                    VelocityComponent.setY(-300);
                    //if(position.getY() < 320 ){
                      //  VelocityComponent.setY(100);
                        //java.lang.System.out.println("velocity Y: " +velocity.Get_VelocityY() );
                    //}
                }
                if(gravity == false){
                    if(position.getY() < 100 ){
                        VelocityComponent.setY(300);
                        //await
                        // java.lang.System.out.println("velocity Y: " +velocity.Get_VelocityY() );
                    }
                    if(position.getY() > 590 && velocity.Get_VelocityY() > 0){
                        velocity.setY(0);
                    }
                }
                   //java.lang.System.out.println("jumping");
                   //new Thread(new thread()).start();
                    //VelocityComponent.setY(-100);
                    //VelocityComponent.setY(-100);
                    //VelocityComponent.setY(-100);
                    //java.lang.System.out.println("Player'x: " + Entity);

                    /* try
                    {
                       // VelocityComponent.setY(-100);
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException ex)
                    {
                        Thread.currentThread().interrupt();
                    }*/
                   //  VelocityComponent.setY(+100);
                    //VelocityComponent.setY(+100);







                // if(position.getX() > 250 && velocity.Get_VelocityX() > 0) {
                //     velocity.setX(velocity.Get_VelocityX() * -1);
                // }

                // if(position.getX() < 50 && velocity.Get_VelocityX() < 0) {
                //     velocity.setX(velocity.Get_VelocityX() * -1);
                // }
            }
        }
    }
}

class RenderSystem extends System {

    public static void LoadPicturesToEntity(Entity entity,String filepath){
        Image image=null;
        try {
            image = ImageIO.read(new File(filepath));

        }catch(IOException e) {
            java.lang.System.out.println("Ops..Problem loading picture");
        }
        entity.addComponent(new RenderComponent(image));


    }
    public void Process(ArrayList<Entity> entities, Graphics2D g) {
        for (Entity entity : entities) {
            // Need a Position & a Render Component
            if(entity.hasComponent(PositionComponent.class) && entity.hasComponent(RenderComponent.class)) {
                //java.lang.System.out.println("This Entity has an image:"+entity.getComponent(RenderComponent.class));
                // Get Position & Render Components
                PositionComponent position = (PositionComponent)entity.getComponent(PositionComponent.class);
                RenderComponent render     = (RenderComponent)entity.getComponent(RenderComponent.class);

                // Save current transform
                AffineTransform transform = g.getTransform();

                // Move into correct position
                g.translate(position.getX(), position.getY());

                // Draw the Render component
                g.drawImage(render.getImage(), 0, 0, null);

                // Reset Transform
                g.setTransform(transform);
            }



        }
    }
    int mWidth, mHeight;
    Graphics2D mGraphics;
    boolean initialised = false;


    //Constructor
    public void RenderSystem() {


    }

}

class CollideSystem extends System{
    Area E_boundingbox,E_Area;
}