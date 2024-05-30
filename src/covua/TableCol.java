package covua;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class TableCol extends JPanel{
    int width = 30;
    int height = BanCo.SQUARE_SIZE * BanCo.MAX_COL;
    int pos =BanCo.HALF_SQUARE_SIZE + 10;
    public TableCol() {
        
        setSize(width, height);
        
    }
    
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(new Color(167, 88, 44));
        g2d.fillRect(0, 0, width, height);
        
        //g2d.setFont(g2d.getFont().deriveFont(22f));
        g2d.setFont(new Font("League Spartan", Font.PLAIN,20));
        g2d.setColor(Color.black);
        for (int r = 0; r < BanCo.MAX_ROW;r++){
            g2d.drawString(String.valueOf(String.valueOf(BanCo.MAX_ROW - r)),10,pos + (r*BanCo.SQUARE_SIZE));
        }
    } 
}
