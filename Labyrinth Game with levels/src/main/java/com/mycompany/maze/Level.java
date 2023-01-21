/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maze;

/**
 *
 * @author farid
 */
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Level {
    private int HEIGHT = 40;
    private int WIDTH = 40;
    public ArrayList<Wall> walls;

    public Level(String filaName) throws IOException {
        playLevel(filaName);
    }

    public void playLevel(String fileName) throws FileNotFoundException, IOException {
        //reading a filename
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        //array to save walls
        walls = new ArrayList<Wall>();
        String readLineInFIle;
        
        
        int y = 0;
        while ((readLineInFIle = br.readLine()) != null) {
            int x = 0;
            for (char blockType : readLineInFIle.toCharArray()) {
                if (Character.isDigit(blockType)) {
                    Image image = new ImageIcon("photos/dark.png").getImage();
                    walls.add(new Wall(image, x * WIDTH, y * HEIGHT, HEIGHT, WIDTH));
                }
                x++;
            }
            y++;
        }
    }
    
    public void drawMyWall(Graphics g) {
        walls.forEach(wall -> {
            wall.drawMyElement(g);
        });
    }
    
    public boolean checkCollisionWithPlayer(Player p) {
        Wall collided = null;

        for(int i = 0; i < walls.size(); i++) {
            if(p.checkCollision(walls.get(i))){
                collided = walls.get(i);
                break;
            }
        }
        return collided != null;
	}

    
    
    public boolean checkCollisionWithDragon(Dragon d) {
        Wall collided = null;

        for(int i = 0; i < walls.size(); i++) {
            if(d.checkCollision(walls.get(i))){
                collided = walls.get(i);
                break;
            }
        }
        return collided != null;
    }
}

