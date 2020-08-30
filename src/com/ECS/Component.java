package com.ECS;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public  abstract class Component {
    private Entity entity;

    public class MoveComponent{

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
    public class Input{
        public boolean right;
        public boolean left;
        public boolean jump;
        public Input(boolean R, boolean L, boolean J){
            right = R;
            left = L;
            jump = J;
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
