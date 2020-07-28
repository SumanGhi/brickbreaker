/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakbreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Graphics2D;

/**
 *
 * @author pc
 */
public class mapGenerator {
    public int map[][];
    public int bheight;
    public int bwidth;
    public mapGenerator(int row, int col){
        map = new int[row][col];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j]=1;
                
            }
            
        }
        bwidth=540/col;
        bheight=150/row;
    }
        public void draw(Graphics2D g){
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    
                    if(map[i][j]>0){
                        g.setColor(white);
                        g.fillRect(j*bwidth+80, i*bheight+50, bwidth, bheight);
                        g.setStroke(new BasicStroke(3));
                        g.setColor(Color.BLACK);
                        g.drawRect(j*bwidth+80, i*bheight+50, bwidth, bheight);
                
                   }
                
                 }
            
            }
        
        }
        
        public void setBrickValue(int value, int row, int col)
        {
            map[row][col]=value;
        }
    
}
