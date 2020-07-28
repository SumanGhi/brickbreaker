/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author pc
 */
public class gamePlay extends JPanel implements KeyListener, ActionListener {

    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;
    private Timer timer;
    private final int delay = 8;

    private int playerX = 310;
    private int ballPosX = 120;
    private int ballPosY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private mapGenerator map;

    public gamePlay() {
        map = new mapGenerator(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();

    }

    public void paint(Graphics g) {
        //background
        g.setColor(Color.black);
        g.fillRect(1, 1, 742, 742);

        //drawing map
        map.draw((Graphics2D) g);

        //score
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 30));
        g.drawString("" + score, 590, 30);
        //border
        g.setColor(Color.red);
        g.fillRect(0, 0, 3, 742);
        g.fillRect(0, 0, 742, 3);
        g.fillRect(740, 0, 3, 642);
        //paddle
        g.setColor(Color.GRAY);
        g.fillRect(playerX, 550, 100, 8);

        //ball
        g.setColor(Color.white);
        g.fillOval(ballPosX, ballPosY, 20, 20);
        
        if(totalBricks<=0){
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.green);
            g.setFont(new Font("serif", Font.BOLD, 90));
            g.drawString("You Won", 180, 300);
            
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Press Enter to Restart", 230, 350);
        }

        if (ballPosY > 650) {
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 90));
            g.drawString("Game over", 180, 300);
            
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Press Enter to Restart", 230, 350);
        }

        g.dispose();

    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        timer.start();
        if (play) {
            if (new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
                ballYdir = -ballYdir;
            }

            A:
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    int brickX = j * map.bwidth + 80;
                    int brickY = i * map.bheight + 50;
                    int bwidth = map.bwidth;
                    int bheight = map.bheight;

                    Rectangle rect = new Rectangle(brickX, brickY, bwidth, bheight);
                    Rectangle ballrect = new Rectangle(ballPosX, ballPosY, 20, 20);
                    Rectangle brect = rect;

                    if (ballrect.intersects(brect)) {
                        if (map.map[i][j]==1){
                        map.setBrickValue(0, i, j);
                        totalBricks--;
                        score += 5;
                        if (ballPosX + 19 <= brect.x || (ballPosX + 1) >= (brect.x + brect.width)) {
                            System.out.println("ball  X-change");
                            ballXdir = -ballXdir;
                        } else{
                            System.out.println("ball Y-change");
                            ballYdir = -ballYdir;
                        }

                        break A;
                        }
                        else{
                            
                        }
                    }
                }

            }

            ballPosX += ballXdir;
            ballPosY += ballYdir;
            if (ballPosX < 10) {
                ballXdir = -ballXdir;
            }
            if (ballPosY < 20) {
                ballYdir = -ballYdir;
            }
            if (ballPosX > 730) {
                ballXdir = -ballXdir;
            }

        }
        repaint();

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("right+");
            if (playerX > 629) {
                playerX = 630;
            } else {
                moveRight();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("left+");
            if (playerX < 11) {
                playerX = 10;
            } else {
                moveLeft();
            }

        }
        
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            
            if (!play) {
                play = true;
                score = 0;
                totalBricks = 21;

                playerX = 310;
                ballPosX = 120;
                ballPosY = 350;
                ballXdir = -1;
                ballYdir = -2;
                map = new mapGenerator(3, 7);
                repaint();
                
            }
        }
    }

    public void moveRight() {
        System.out.println("right");
        play = true;
        playerX += 20;

    }

    public void moveLeft() {
        System.out.println("left");
        play = true;
        playerX -= 20;

    }

}
