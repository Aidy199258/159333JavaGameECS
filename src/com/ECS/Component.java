package com.ECS;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


public abstract class Component {

}


//this component called when a key is pressed
/*class MoveComponent extends Component {
    // Called whenever a key is pressed
    void keyPressed(KeyEvent event);

    // Called whenever a key is released
    void keyReleased(KeyEvent event);

    // Called whenever a key is pressed and immediately released
    void keyTyped(KeyEvent event);
}*/


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



//Velocity of
class Velocity extends Component {
    public float speed_x;
    public float speed_y;

    public float Get_VelocityX() {
        return speed_x;
    }

    public float Get_VelocityY() {
        return speed_y;
    }

    public Velocity(float x, float y) {
        speed_x = x;
        speed_y = y;
    }
}



class PositionComponent extends  Component {
    public int mX, mY, mA;


    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }

    public PositionComponent(int X, int Y, int A) {
        mX = X;
        mY = Y;
        mA = A;




        //Player's Position
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

        public PlatformComponent(Entity entity, double x, double y, double w, double h) {
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

