package com.ECS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class System {

    class GameSystem{
        public void StartGame(){

        }
    }
}

class User_Input extends System implements KeyListener {

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
    }

}

