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



class RenderComponent extends Component{


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
}



