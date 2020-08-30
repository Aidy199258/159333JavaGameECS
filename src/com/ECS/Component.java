package com.ECS;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public  abstract class Component {
    private Entity entity;

    interface MoveComponent{
        // Called whenever a key is pressed
        void keyPressed(KeyEvent event);

        // Called whenever a key is released
        void keyReleased(KeyEvent event);

        // Called whenever a key is pressed and immediately released
        void keyTyped(KeyEvent event);
    }

    public class Velocity{
        public float speed_x;
        public float speed_y;
        public Velocity(float x, float y){
            speed_x = x;
            speed_y = y;
        }
    }

    public class PositionComponent{
        public  float mX, mY, mA;
        public PositionComponent(float X, float Y, float A){
            mX = X;
            mY = Y;
            mA = A;
        }
    }

    }

    public class Collide{
        //get bounding shape of Entity
        Area getShape();

        //two object collide, eg: player collide item, platforms..
        void collide(Collidable other);
    }



    public class RenderComponent{

    }
}
