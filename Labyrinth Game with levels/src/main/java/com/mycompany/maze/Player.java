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

public class Player extends Sprite {
    private boolean checkToMove = true;
    private double X = 0;
    private double Y = 0;
    public String direction = "x";
    public double value = 0; 
    
    public Player(Image image, int xCoor, int yCoor, int height, int width) {
        super(image, xCoor, yCoor, height, width);
        
    }
    
    public void moveXDirection() {
        if ((X < 0 && xCoor > 0) || (X > 0 && xCoor + width <= 780)) {
            xCoor += X;
        }
    }
     
    public void moveYDirection()
    {
        if ((Y < 0 && yCoor > 0) || (Y > 0 && yCoor + height <= 740)) {
            yCoor += Y;
        }
    }
	public void setY(double Y) {
        this.Y = Y;
        value = Y;
        direction = "y";
        checkToMove = false;
	}

	public void setX(double X) {
        this.X = X;
        value = X;
        direction ="x";
        checkToMove = true;
    }
    
    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    public void move()
    {
        if (checkToMove) {
            moveXDirection();
        } 
        else {
            moveYDirection();
        }
    }

    public boolean winLevel() {
        Rectangle r1 = new Rectangle(xCoor , yCoor, width, height);
        Rectangle r2 = new Rectangle(770, 0, 40, 40); 
        return r1.intersects(r2);
    }

    @Override
    public boolean checkCollision(Sprite newS) {
        if(direction.equals("y")) {
            int doubleToIntY = (int) Y;

            Rectangle r1 = new Rectangle(xCoor , yCoor + doubleToIntY, width, height);
            Rectangle r2 = new Rectangle(newS.xCoor, newS.yCoor, newS.width, newS.height);        
            return r1.intersects(r2);
        } else {
            int doubleToIntX = (int) X;

            Rectangle r1 = new Rectangle(xCoor + doubleToIntX, yCoor, width, height);
            Rectangle r2 = new Rectangle(newS.xCoor, newS.yCoor, newS.width, newS.height);        
            return r1.intersects(r2);
        }
    }
}
