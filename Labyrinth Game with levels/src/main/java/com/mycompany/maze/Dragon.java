/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maze;

/**
 *
 * @author farid
 */
import java.awt.Image;
import java.util.Random;
import java.awt.Rectangle;


public class Dragon extends Sprite {
    private double x = 1;
    private double y;
    private boolean CheckX = true;
    public String direction = "x";
    public double value = 0; 

    public Dragon(Image image, int xCoor, int yCoor, int height, int width) {
        super(image, xCoor, yCoor, height, width);
        
    }

    public void moveXDirection() {
        if ((x < 0 && xCoor > 0) || (x > 0 && xCoor + width <= 780)) {
            xCoor += x;
        } else {
            CollisionWIthWall();
        }
    }

    public void moveYDirection() {
        if ((y < 0 && yCoor > 0) || (y > 0 && yCoor + height <= 740)) {
            yCoor += y;
        } else {
            CollisionWIthWall();
        }
    }
    
    public void CollisionWIthWall(Level l) {
        Random randNum = new Random();
        int myNum = randNum.nextInt(4);
        
        if(myNum == 0) {
            boolean temporaryCheck = true;
            Rectangle r1 = new Rectangle(xCoor, yCoor - 1, width, height);

            for(int i = 0; i < l.walls.size(); i++) {
                Rectangle r2 = new Rectangle(l.walls.get(i).xCoor, l.walls.get(i).yCoor, l.walls.get(i).width, l.walls.get(i).height);
                if (r1.intersects(r2)) {
                    temporaryCheck = false;
                }
            }

            if(temporaryCheck) {
            y = -1;
            value = y;
            direction = "y";
            CheckX = false;
            } else {
                CollisionWIthWall(l);
            }

        } else if(myNum == 1) {
            boolean temporaryCheck = true;
            Rectangle r1 = new Rectangle(xCoor, yCoor + 1 , width, height);

            for(int i = 0; i < l.walls.size(); i++) {
                Rectangle r2 = new Rectangle(l.walls.get(i).xCoor, l.walls.get(i).yCoor, l.walls.get(i).width, l.walls.get(i).height);
                if (r1.intersects(r2)) {
                    temporaryCheck = false;
                }
            }

            if(temporaryCheck) {
            y = 1;
            value = y;
            direction = "y";
            CheckX = false;
            } else {
                CollisionWIthWall(l);
            }
            
        } else if(myNum == 2) {
            boolean temporaryCheck = true;
            Rectangle r1 = new Rectangle(xCoor - 1, yCoor, width, height);

            for(int i = 0; i < l.walls.size(); i++) {
                Rectangle r2 = new Rectangle(l.walls.get(i).xCoor, l.walls.get(i).yCoor, l.walls.get(i).width, l.walls.get(i).height);
                if (r1.intersects(r2)) {
                    temporaryCheck = false;
                }
            }

            if(temporaryCheck) {
            x = -1;
            value = x;
            direction ="x";
            CheckX = true;
            } else {
                CollisionWIthWall(l);
            }
            
        } else {
            boolean temporaryCheck = true;
            Rectangle r1 = new Rectangle(xCoor + 1, yCoor, width, height);

            for(int i = 0; i < l.walls.size(); i++) {
                Rectangle r2 = new Rectangle(l.walls.get(i).xCoor, l.walls.get(i).yCoor, l.walls.get(i).width, l.walls.get(i).height);
                if (r1.intersects(r2)) {
                    temporaryCheck = false;
                }
            }

            if(temporaryCheck) {
            x = 1;
            value = x;
            direction ="x";
            CheckX = true;
            } else {
                CollisionWIthWall(l);
            }
        }
        
    }

    public void CollisionWIthWall() {
        
        Random rand = new Random();
        int myNum = rand.nextInt(4);

        if(myNum == 0) {
            y = -1;
            value = y;
            direction = "y";
            CheckX = false;
            
        } else if(myNum == 1) {
           
            y = 1 ;
            value = y;
            direction = "y";
            CheckX = false;
           
            
        } else if(myNum == 2) {
            x = -1;
            value = x;
            direction ="x";
            CheckX = true;
            
        } else {
            x = 1;
            value = x;
            direction ="x";
            CheckX = true;
        }
    }

    public void move()
    {
        if(CheckX == true) {
            moveXDirection();
        } else {
            moveYDirection();
        }
    }

    @Override
    public boolean checkCollision(Sprite newS) {
        if(direction.equals("x")) {
            Rectangle r1 = new Rectangle(xCoor + (int) x, yCoor, width, height);
            Rectangle r2 = new Rectangle(newS.xCoor, newS.yCoor, newS.width, newS.height);        
            return r1.intersects(r2);
        } else {
            Rectangle r1 = new Rectangle(xCoor, yCoor + (int) y, width, height);
            Rectangle r2 = new Rectangle(newS.xCoor, newS.yCoor, newS.width, newS.height);        
            return r1.intersects(r2);
        }
        
    }
}

