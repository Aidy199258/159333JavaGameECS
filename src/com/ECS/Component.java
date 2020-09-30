package com.ECS;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Stack;


public abstract class Component {

}



// Player Component - Holds Player specific information
class PlayerComponent extends Component {
    private int mLife;

    public int getLife() {
        return mLife;
    }

    public PlayerComponent(int life) {
        mLife = life;
    }
}

class CoinComponent extends Component{
    //Each Coin counts as 1 point
    private final int mDEFAULT_POINT = 1;
    public int CoinPoint;
    // Shape
    protected double mW, mH, mX, mY;
    //Constructor
    public CoinComponent(int DefaultPoint, double x, double y, double w, double h){
        if(DefaultPoint<=0) {//Invalid input
            CoinPoint = mDEFAULT_POINT;
        }else {
            CoinPoint = DefaultPoint;
        }
        mW=w;
        mH=h;
        mX=x;
        mY=y;

    }
}

// Rainbow instead of coin
class RainbowComponent extends Component{
    Entity mEntity;

    // Position
    protected double mW, mH;

    // Bounding Box
    Area mBoundingBox, mArea;
    AffineTransform mTransform;

    // Coin Sprite
    Image spritesheet;
    Image sprites[];
    int frame;
    public RainbowComponent(Entity entity, double x, double y, double w, double h){
        // Set Game/Entity
        mEntity = entity;
        // Set Position and Size
        //mX = x;
        //mY = y;
        mW = w;
        mH = h;

        // Initialise Bounding Box
        mBoundingBox = new Area(new Ellipse2D.Double(-mW/2, -mH/2, mW, mH));
        mArea = new Area();
        mTransform = new AffineTransform();
        //mTransform.translate(mX, mY);

        // Load Spritesheet
        //spritesheet = loadImage("Pictures/coin/coin.png");

        // Create sprites
        sprites = new Image[16];

        // Load sprites
        for(int i = 0; i < 16; i++) {
            // Calculate x,y
            int sx = (i % 4) * 32;
            int sy = (i / 4) * 32;

            // Load sprite
            //sprites[i] = subImage(spritesheet, sx, sy, 32, 32);
        }

    }
}


class KeyComponent extends Component {
    public KeyComponent() {

    }
}


//Velocity of
class VelocityComponent extends Component {
    public static float speed_x;
    public static float speed_y;
    public static boolean gravity;

    public  float Get_VelocityX() {
        return speed_x;
    }

    public float Get_VelocityY() {
        return speed_y;
    }

    public static void setX(float x) {
        speed_x = x;
    }
    public static void setY(float y) {
        speed_y = y;
    }

    public VelocityComponent(float x, float y, boolean g) {
        speed_x = x;
        speed_y = y;
        gravity = g;
    }
}



class PositionComponent extends  Component {
    public  float mX;
    public  float mY;
    public  float mA;


    public float getX() {
        return mX;
    }

    public float getY() {
        return mY;
    }

    public  void setX(float x) {
        mX = x;
    }

    public  void setY(float y) {
        mY = y;
    }

    public PositionComponent(float X, float Y, float A) {
        mX = X;
        mY = Y;
        mA = A;




        //Player's Position - Codes from original game for guidance
        /*
        // Direction
        enum Direction {Left, Right};
        Direction mDirection;

        // State
        enum State {Standing, Running, Jumping};
        State mState;

        // Size
        double mW, mH;

        // Position (inherited from Entity)
        // double mX, mY;

        // Velocity
        double mVX, mVY;

        // Bounding Box
        Area mBoundingBox, mArea;
        AffineTransform mTransform;

    Needs implementation for image
    // Sprites
    Image resting[];
    Image walking[];
    Image jumping[];

    // Frame Counters
    int restingFrame;
    int walkingFrame;
    int jumpingFrame;


        // Jump Timer
        long jumpStart;
        boolean left, right;

        //public PlayerComponent(Game game, double x, double y, double w, double h) {
    public PlayerComponent(double x, double y, double w, double h) {
            // Player Constructor

            // Initialise Position and Size
            mX = x;
            mY = y;
            mW = w;
            mH = h;

            // Initialise Bounding Box
            mBoundingBox = new Area(new Rectangle2D.Double(-mW/3, -mH/2, mW*2.0/3.0, mH));
            mArea = new Area();
            mTransform = new AffineTransform();

            // Initialise Velocity
            mVX = 0;
            mVY = 0;

            // Initialise left/right
            left  = false;
            right = false;

            // Initialise State
            mDirection = Direction.Left;
            mState = State.Standing;

            // Resting Sprits
            //resting = new Image[10];
            //restingFrame = 0;

            // Load Sprites
            for(int i = 0; i < 10; i++) {
                // Sprite X,Y
                int sx = (i % 4) * 256;
                int sy = (i / 4) * 256;

                // Get Sprite
                //resting[i] = loadImage("Pictures/player/idle" + (i+1) + ".png");
            }

            // Walking Sprits
            //walking = new Image[8];
            //walkingFrame = 0;

            // Load Sprites
            for(int i = 0; i < 8; i++) {
                // Sprite X,Y
                int sx = (i % 4) * 256;
                int sy = (i / 4) * 256;

                // Get Sprite
                //walking[i] = loadImage("Pictures/player/run" + (i+1) + ".png");
            }

            // Jumping Sprits
            //jumping = new Image[10];
            //jumpingFrame = 0;

            // Load Sprites
            for(int i = 0; i < 10; i++) {
                // Sprite X,Y
                int sx = (i % 4) * 256;
                int sy = (i / 4) * 256;

                // Get Sprite
                //jumping[i] = loadImage("Pictures/player/jump" + (i+1) + ".png");
            }
        }

         */
    }
}


/*class Collide extends  Component{
        //get bounding shape of Entity
        Area shape;
        public Area getShape() {
            return shape;
        }

        //two object collide, eg: player collide item, platforms..
        void collide(Collidable other);
}*/



class PlatformComponent extends Component {

    // Shape
    protected double mW, mH, mX, mY;
    // Bounding Box
    Area mBoundingBox, mArea;
    AffineTransform mTransform;

    // Sprite
    Image sprite;

    public PlatformComponent(double x, double y, double w, double h) {
        // Set Position and Size
        mX = x;
        mY = y;
        mW = w;
        mH = h;

        // Initialise Bounding Box
        mBoundingBox = new Area(new Rectangle2D.Double(-mW / 2, -mH / 2, mW, mH));
        mArea = new Area();
        mTransform = new AffineTransform();
        mTransform.translate(mX, mY);

        // Load sprite
        //sprite = LoadImageSystem("Pictures/platform/platform.png");
    }


}



//Audio Clip
class AudioComponent extends Component{
    // Format
    AudioFormat mFormat;

    // Audio Data
    byte[] mData;

    // Buffer Length
    long mLength;

    // Loop Clip
    Clip mLoopClip;

    public Clip getLoopClip() {
        // return mLoopClip
        return mLoopClip;
    }

    public void setLoopClip(Clip clip) {
        // Set mLoopClip to clip
        mLoopClip = clip;
    }

    public AudioFormat getAudioFormat() {
        // Return mFormat
        return mFormat;
    }

    public byte[] getData() {
        // Return mData
        return mData;
    }

    public long getBufferSize() {
        // Return mLength
        return mLength;
    }

    public String AudioComponent(){
        return "AudioComponent";
    }
    //Needs implementation
    public AudioComponent(AudioInputStream stream) {
        // Get Format
        mFormat = stream.getFormat();

        // Get length (in Frames)
        mLength = stream.getFrameLength() * mFormat.getFrameSize();

        // Allocate Buffer Data
        mData = new byte[(int)mLength];

        try {
            // Read data
            stream.read(mData);
        } catch(Exception exception) {
            // Print Error
            java.lang.System.out.println("Error reading Audio File\n");

            // Exit
            java.lang.System.exit(1);
        }

        // Set LoopClip to null
        mLoopClip = null;
    }

}

class PointComponent extends Component{
    private int mPoints;
    private final int mSTART_POINTS = 10;
    private final int mLOSE_POINTS =0;

    public PointComponent(int points){

        mPoints = points;
    }

    public int GetPoint(){
        return mPoints;
    }
    public void LoseOnePoint(){
        mPoints-=1;
    }

    public void GainOnePoint(){
        mPoints+=1;
    }
}

class TransformComponent extends Component{
    // Stack of transforms
    Stack<AffineTransform> mTransforms;


    public void SetTransform(Stack<AffineTransform> transforms){
        mTransforms = transforms;
    }
    public Stack<AffineTransform> GetTransform (){
        return mTransforms;
    }

    public void ResetTransform(Entity entity){
        // Reset all transforms
        mTransforms.clear();
        if(entity.hasComponent(RenderComponent.class)){
            RenderComponent renderComponent=(RenderComponent)entity.getComponent(RenderComponent.class);
            // Push transform onto the stack
            mTransforms.push(renderComponent.Graphics().getTransform());
        }
    }

    // Save the current transform
    public void saveCurrentTransform(Entity entity) {
        if(entity.hasComponent(RenderComponent.class)){
            RenderComponent renderComponent=(RenderComponent)entity.getComponent(RenderComponent.class);
            // Push transform onto the stack
            mTransforms.push(renderComponent.Graphics().getTransform());
        }

    }

    // Restores the last transform
    public void restoreLastTransform(Entity entity) {
        // Set current transform to the top of the stack.
        if(entity.hasComponent(RenderComponent.class)){
            RenderComponent renderComponent=(RenderComponent)entity.getComponent(RenderComponent.class);
            // Push transform onto the stack
            renderComponent.Graphics().setTransform(mTransforms.peek());
        }


        // If there is more than one transform on the stack
        if(mTransforms.size() > 1) {
            // Pop a transform off the stack
            mTransforms.pop();
        }
    }

}


class RenderComponent extends Component {
    private Image mImage;

    public Image getImage() {
        return mImage;
    }

    public RenderComponent(Image image) {
        mImage = image;
    }



    //Window image
    private Graphics2D mGraphics;

    public Graphics2D Graphics(){
        return mGraphics;
    }


    //Constructor
    public RenderComponent(Entity entity, Graphics graphics) {
        // Get the graphics object
        mGraphics = (Graphics2D) graphics;
    }
    public void RenderSettings(){
        // Rendering settings
        mGraphics.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

    }


    // This function translates the drawing context by (x,y)
    void translate(double x, double y) {
        // Translate the drawing context
        mGraphics.translate(x,y);
    }



    // This function rotates the drawing context by a degrees
    void rotate(double a) {
        // Rotate the drawing context
        mGraphics.rotate(Math.toRadians(a));
    }

    // This function scales the drawing context by (x,y)
    void scale(double x, double y) {
        // Scale the drawing context
        mGraphics.scale(x, y);
    }

    // This function shears the drawing context by (x,y)
    void shear(double x, double y) {
        // Shear the drawing context
        mGraphics.shear(x, y);
    }
}

class GraphicComponent extends Component{

    Graphics2D mGraphics;
    //Constructor
    public GraphicComponent(){

    }
    //-------------------------------------------------------
    // Graphics Functions
    //-------------------------------------------------------

    // My Definition of some colors
    Color black = Color.BLACK;
    Color orange = Color.ORANGE;
    Color pink = Color.PINK;
    Color red = Color.RED;
    Color purple = new Color(128, 0, 128);
    Color blue = Color.BLUE;
    Color green = Color.GREEN;
    Color yellow = Color.YELLOW;
    Color white = Color.WHITE;

    // Changes the background Color to the color c
    public void changeBackgroundColor(Color c) {
        // Set background colour
        mGraphics.setBackground(c);
    }

    // Changes the background Color to the color (red,green,blue)
    public void changeBackgroundColor(int red, int green, int blue) {
        // Clamp values
        if(red < 0)   {red = 0;}
        if(red > 255) {red = 255;}

        if(green < 0)   {green = 0;}
        if(green > 255) {green = 255;}

        if(blue < 0)   {blue = 0;}
        if(blue > 255) {blue = 255;}

        // Set background colour
        mGraphics.setBackground(new Color(red,green,blue));
    }

    // Clears the background, makes the whole window whatever the background color is
    public void clearBackground(int width, int height) {
        // Clear background
        mGraphics.clearRect(0, 0, width, height);
    }

    // Changes the drawing Color to the color c
    public void changeColor(Color c) {
        // Set colour
        mGraphics.setColor(c);
    }

    // Changes the drawing Color to the color (red,green,blue)
    public void changeColor(int red, int green, int blue) {
        // Clamp values
        if(red < 0)   {red = 0;}
        if(red > 255) {red = 255;}

        if(green < 0)   {green = 0;}
        if(green > 255) {green = 255;}

        if(blue < 0)   {blue = 0;}
        if(blue > 255) {blue = 255;}

        // Set colour
        mGraphics.setColor(new Color(red,green,blue));
    }

    // Draws a line from (x1,y2) to (x2,y2)
    void drawLine(double x1, double y1, double x2, double y2) {
        // Draw a Line
        mGraphics.draw(new Line2D.Double(x1, y1, x2, y2));
    }

    // Draws a line from (x1,y2) to (x2,y2) with width l
    void drawLine(double x1, double y1, double x2, double y2, double l) {
        // Set the stroke
        mGraphics.setStroke(new BasicStroke((float)l));

        // Draw a Line
        mGraphics.draw(new Line2D.Double(x1, y1, x2, y2));

        // Reset the stroke
        mGraphics.setStroke(new BasicStroke(1.0f));
    }

    // This function draws a rectangle at (x,y) with width and height (w,h)
    void drawRectangle(double x, double y, double w, double h) {
        // Draw a Rectangle
        mGraphics.draw(new Rectangle2D.Double(x, y, w, h));
    }

    // This function draws a rectangle at (x,y) with width and height (w,h)
    // with a line of width l
    void drawRectangle(double x, double y, double w, double h, double l) {
        // Set the stroke
        mGraphics.setStroke(new BasicStroke((float)l));

        // Draw a Rectangle
        mGraphics.draw(new Rectangle2D.Double(x, y, w, h));

        // Reset the stroke
        mGraphics.setStroke(new BasicStroke(1.0f));
    }

    // This function fills in a rectangle at (x,y) with width and height (w,h)
    void drawSolidRectangle(double x, double y, double w, double h) {
        // Fill a Rectangle
        mGraphics.fill(new Rectangle2D.Double(x, y, w, h));
    }

    // This function draws a circle at (x,y) with radius
    void drawCircle(double x, double y, double radius) {
        // Draw a Circle
        mGraphics.draw(new Ellipse2D.Double(x-radius, y-radius, radius*2, radius*2));
    }

    // This function draws a circle at (x,y) with radius
    // with a line of width l
    void drawCircle(double x, double y, double radius, double l) {
        // Set the stroke
        mGraphics.setStroke(new BasicStroke((float)l));

        // Draw a Circle
        mGraphics.draw(new Ellipse2D.Double(x-radius, y-radius, radius*2, radius*2));

        // Reset the stroke
        mGraphics.setStroke(new BasicStroke(1.0f));
    }

    // This function draws a circle at (x,y) with radius
    void drawSolidCircle(double x, double y, double radius) {
        // Fill a Circle
        mGraphics.fill(new Ellipse2D.Double(x-radius, y-radius, radius*2, radius*2));
    }

    // This function draws text on the screen at (x,y)
    public void drawText(double x, double y, String s) {
        // Draw text on the screen
        mGraphics.setFont(new Font("Arial", Font.PLAIN, 40));
        mGraphics.drawString(s, (int)x, (int)y);
    }

    // This function draws bold text on the screen at (x,y)
    public void drawBoldText(double x, double y, String s) {
        // Draw text on the screen
        mGraphics.setFont(new Font("Arial", Font.BOLD, 40));
        mGraphics.drawString(s, (int)x, (int)y);
    }

    // This function draws text on the screen at (x,y)
    // with Font (font,size)
    public void drawText(double x, double y, String s, String font, int size) {
        // Draw text on the screen
        mGraphics.setFont(new Font(font, Font.PLAIN, size));
        mGraphics.drawString(s, (int)x, (int)y);
    }

    // This function draws bold text on the screen at (x,y)
    // with Font (font,size)
    public void drawBoldText(double x, double y, String s, String font, int size) {
        // Draw text on the screen
        mGraphics.setFont(new Font(font, Font.BOLD, size));
        mGraphics.drawString(s, (int)x, (int)y);
    }



}