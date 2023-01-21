/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maze;

/**
 *
 * @author farid
 */
import java.awt.*;

public class Sprite {
    protected Image image;
    protected int xCoor;
    protected int yCoor;
    protected int height;
    //protected int size = height * width;
    protected int width;

    //Constuctor for Sprite
    public Sprite(Image image, int xCoor, int yCoor, int height, int width) {
        this.image = image;
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        this.height = height;
        this.width = width;
    }    
    
    public void drawMyElement(Graphics g) {
        g.drawImage(image, xCoor, yCoor, width, height, null);
    }
    
    public boolean checkCollision(Sprite newS) {
        //Creating first rect
        Rectangle r1 = new Rectangle(xCoor, yCoor, width, height);
        //Creating second rect
        Rectangle r2 = new Rectangle(newS.xCoor, newS.yCoor, newS.width, newS.height);        
        //Comparing them
        return r1.intersects(r2);
    }

    public int getXCoor() {
        return xCoor;
    }

    public int getYCoor() {
        return yCoor;
    }

    public void setXCoor(int xCoor) {
        this.xCoor = xCoor;
    }

    public void setYCoor(int yCoor) {
        this.yCoor = yCoor;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }   

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}