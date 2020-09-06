package com.ECS;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;


public  abstract class Component {

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



