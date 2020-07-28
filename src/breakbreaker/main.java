/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakbreaker;

import javax.swing.JFrame;

/**
 *
 * @author pc
 */
public class main {
    public static void main(String[] args) {
        JFrame obj=new JFrame();
        gamePlay gPlay;
        gPlay = new gamePlay();
        obj.setBounds(10,20,750,650);
        obj.setTitle("Breaking ball");
        obj.setVisible(true);
        obj.setResizable(false);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gPlay);
        
        
        
        
    }
    
}
