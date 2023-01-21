/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maze;

/**
 *
 * @author farid
 */
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;
import javax.swing.*;

public class Map {
    private JFrame frame;
    private MazeLogic maze;
    
    public Map() {
        frame = new JFrame("Labyrinth");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JLabel scores = new JLabel();
        
        maze = new MazeLogic();
        frame.getContentPane().add(maze);
        frame.setPreferredSize(new Dimension(800, 800));
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Options");
        menuBar.add(gameMenu);
        JMenuItem restartGame = new JMenuItem("Restart the Game");
        gameMenu.add(restartGame);

        restartGame.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                maze.levelSize = 1;
                maze.point = 0;
                maze.restartGame();
            }
        });
        
        JMenuItem showData = new JMenuItem("Show previous results");
        gameMenu.add(showData);
        showData.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String data="";
                try {
                    File myObj = new File("database.txt");
                    Scanner myReader = new Scanner(myObj);
                    while (myReader.hasNextLine()) {
                        data += myReader.nextLine();
                    }
                    myReader.close();
                } catch (Exception e) { 
                
                }
                
                scores.setText(data);
                frame.getContentPane().remove(maze);
                frame.getContentPane().add(scores);

                frame.setResizable(false);
                frame.pack();
                frame.setVisible(true);
            }
        });

        
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    
}
}
