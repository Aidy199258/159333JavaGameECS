package com.ECS;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Stack;


public abstract class Component {

}


class CoinComponent extends Component{ 
    //Each Coin counts as 1 point
    private final int mDEFAULT_POINT = 1;
    public int CoinPoint;
    // Shape
    protected float mW, mH, mX, mY;

    public float getX() {
        return mX;
    }

    public float getY() {
        return mY;
    }
    //Constructor
    public CoinComponent(int DefaultPoint, float x, float y, float w, float h){
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


    }
}



class PlatformComponent extends Component {
    // Shape
    protected float mW, mH, mX, mY;

    public float getX() {
        return mX;
    }

    public float getY() {
        return mY;
    }
    public float getW() {
        return mW;
    }

    public float getH() {
        return mH;
    }
    //Constructor
    public PlatformComponent(float x, float y, float w, float h){
        mW=w;
        mH=h;
        mX=x;
        mY=y;

    }
}



//Audio Clip
class AudioComponent extends Component{

    private String _filePath;

    public void setFilePath(String filePath){
        _filePath = filePath;
    }
    public String getFilePath(){
        return _filePath;
    }
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

    //Empty Constructor for initiation
    public AudioComponent(){

    }



    //Adding AudioComponent AudioInputStream constructor
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

    //Load Audio function
    public AudioComponent LoadAudio(String filename){

        try {
            // Open File
            File file = new File(filename);

            // Open Audio Input Stream
            Game_Panel.audioIS = javax.sound.sampled.AudioSystem.getAudioInputStream(file);

            // Create Audio Clip
            AudioComponent audioComponent = new AudioComponent(Game_Panel.audioIS);
            return audioComponent;

        } catch(Exception e) {
            // Catch Exception
            java.lang.System.out.println("Error: cannot open Audio File " + filename + "\n");
        }
        return null;
    }
    public void PlayAudio(AudioComponent audioComponent){
        // Check audioClip for null
        if(audioComponent == null) {
            // Print error message
            java.lang.System.out.println("Error: audioComponent is null\n");

            // Return
            return;
        }

        try {
            // Create a Clip
            Clip clip = javax.sound.sampled.AudioSystem.getClip();

            // Load data
            clip.open(audioComponent.getAudioFormat(), audioComponent.getData(), 0, (int)audioComponent.getBufferSize());

            // Play Clip
            clip.start();
        } catch(Exception exception) {

            // Display Error Message
            java.lang.System.out.println("Error playing Audio Clip\n");
            exception.printStackTrace();
        }

    }


}

class PointComponent extends Component{
    private int mPoints;

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



    public void updateImage(Image image) {
        mImage = image;
    }


    // This function translates the drawing context by (x,y)
    void translate(double x, double y) {
        // Translate the drawing context
        mGraphics.translate(x,y);
    }


}

class GraphicComponent extends Component{

    Graphics2D mGraphics;
    //Constructor
    public GraphicComponent(Graphics graphics){
        mGraphics = (Graphics2D) graphics;
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
