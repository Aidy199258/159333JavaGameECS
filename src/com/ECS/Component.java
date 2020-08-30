package com.ECS;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public  abstract class Component {
    public static int GRAVITY = 1500;

    public class MoveComponent{

    }

    public class Velocity{
        public int speed_x;
        public int speed_y;
    }

    public class PositionComponent{
        public  float mX, mY;
        public PositionComponent(float X, float Y){
            mX = X;
            mY = Y;
        }
    }
    public class Input{
        public boolean right;
        public boolean left;
        public boolean jump;
    }

    public class Collide{

    }



    public class RenderComponent{

    }
}
