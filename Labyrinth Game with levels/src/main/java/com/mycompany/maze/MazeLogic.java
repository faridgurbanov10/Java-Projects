/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.maze;

/**
 *
 * @author farid
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;


public final class MazeLogic extends JPanel {
    
    private int speed = 100;
    private int step = 2;
    
    private Image bg;
    //private boolean moving = true;
    private boolean paused = false;
    public int levelSize = 1;
    public int point = 0;
    
    private Level l;
    private Dragon d;
    private Player obiWan;
    private Player skywalker;
    //private p paddle;
    private Timer newFrameTimer;
    
    public MazeLogic() {
        super();
        bg = new ImageIcon("photos/tattoine.jpg").getImage();

        this.getInputMap().put(KeyStroke.getKeyStroke("W"), "UP");
        this.getActionMap().put("UP", new AbstractAction() {
        @Override
            public void actionPerformed(ActionEvent ae) {obiWan.setY(-step);}
        });

        this.getInputMap().put(KeyStroke.getKeyStroke("S"), "DOWN");
        this.getActionMap().put("DOWN", new AbstractAction() {
        @Override
            public void actionPerformed(ActionEvent ae) {obiWan.setY(step);}
        });

        this.getInputMap().put(KeyStroke.getKeyStroke("D"), "RIGHT");
        this.getActionMap().put("RIGHT", new AbstractAction() {
        @Override
            public void actionPerformed(ActionEvent ae) {obiWan.setX(step);}
        });

        this.getInputMap().put(KeyStroke.getKeyStroke("A"), "LEFT");
        this.getActionMap().put("LEFT", new AbstractAction() {
        @Override

            public void actionPerformed(ActionEvent ae) {obiWan.setX(-step);}
        });
        
        this.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "escape");
        this.getActionMap().put("escape", new AbstractAction() {
        @Override
            public void actionPerformed(ActionEvent ae) {paused = !paused;}
        });
    
        restartGame();
        newFrameTimer = new Timer(1000 / speed, new NewFrameListener(this));
        newFrameTimer.start();
    }
    
    public void restartGame() {
        try {
            l = new Level("levels/level" + levelSize + ".txt");
        } catch (IOException ex) {
            Logger.getLogger(MazeLogic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        createDragon(l);
        Image kenobi = new ImageIcon("photos/obiWan.png").getImage();
        obiWan = new Player(kenobi, 0, 700, 35, 35);
        
        Image anakin = new ImageIcon("photos/anakin.png").getImage();
        skywalker = new Player(anakin, 750, 5, 40, 40);
    }      
    
    private Dragon createDragon(Level l) {
        Random randomNumber = new Random();
        boolean isfound = false;
        while (!isfound) {
            int border1 = randomNumber.nextInt(234); 
            int border2 = randomNumber.nextInt(312) + 40;
            boolean temporaryCheck = true;

            for(int i = 0; i < l.walls.size(); i++) {
                Rectangle r1 = new Rectangle(border1, border2, 40, 40);
                Rectangle r2 = new Rectangle(l.walls.get(i).xCoor, l.walls.get(i).yCoor, l.walls.get(i).width, l.walls.get(i).height);
                if (r1.intersects(r2)) {
                    temporaryCheck = false;
                }
            }

            if(temporaryCheck == true) {
                Image dImage = new ImageIcon("photos/dartVader.png").getImage();
                d = new Dragon(dImage, border1, border2, 40, 40);
                isfound = true;
                return d;
            }
            
        }
        return null;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, 800, 800, null);
        l.drawMyWall(g);
        obiWan.drawMyElement(g);
        skywalker.drawMyElement(g);
        d.drawMyElement(g);
    }

    class NewFrameListener implements ActionListener {
        public JPanel panel;

        NewFrameListener(JPanel panel) {
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!paused) {
                long start = System.currentTimeMillis();
                d.move();
                if (d.checkCollision(obiWan)) {
                    long finish = System.currentTimeMillis();
                    long time = finish - start;
                    String data="";
                    try {
                        File myObj = new File("database.txt");
                        Scanner myReader = new Scanner(myObj);
                        while (myReader.hasNextLine()) {
                            data += myReader.nextLine();
                        }
                        myReader.close();
                    } catch (Exception e) { }

                    try {
                        FileWriter myWriter = new FileWriter("database.txt");
                        myWriter.write(data + "\n" + "You lost in level " +  (point + 1)  + "! Your score is: " + (point));
                        myWriter.close();
                    } catch (Exception e) { }
                    
                    
                    JOptionPane.showMessageDialog(panel, "You thought you could defeat me, Obi-Wan?! You have failed, Kenobi!\nLevel " +  (point + 1)  + "! Your score is: " + (point) + "\n");
                    point = 0;
                    levelSize = 1;
                    restartGame();    
                }
    
                
                if(obiWan.winLevel()){
                    point = point + 1;
                    JOptionPane.showMessageDialog(panel, "You are the chosen one!\nYour score is: " + point + " ",
                    "Done!", JOptionPane.PLAIN_MESSAGE);
                    
                    if(levelSize <= 5) {
                        levelSize = levelSize + 1;
                    } else {
                        levelSize = 1;
                    }
                    
                    if(levelSize == 6) {
                        long finish = System.currentTimeMillis();
                        long time = finish - start;
                        String data="";
                        try {
                            File myObj = new File("database.txt");
                            Scanner myReader = new Scanner(myObj);
                            while (myReader.hasNextLine()) {
                                data += myReader.nextLine();
                            }
                            myReader.close();
                        } catch (Exception e) { }

                        try {
                            FileWriter myWriter = new FileWriter("database.txt");
                            myWriter.write("\n" + data + "\n" + "You won the game");
                            myWriter.close();
                        } catch (Exception e) { }
                    }
                    
                    restartGame();
                    
                    
                }

                if (l.checkCollisionWithDragon(d)) {
                    d.CollisionWIthWall(l);
                }

                if (l.checkCollisionWithPlayer(obiWan)) {
                    obiWan.setX(0);
                    obiWan.setY(0);
                }
                obiWan.move();
            }
            repaint();
        }
    }        
}
