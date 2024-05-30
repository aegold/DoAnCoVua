package covua;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class TableRow extends JPanel{
    public int width = BanCo.SQUARE_SIZE * BanCo.MAX_ROW + 60;
    public int height = 30;
    int pos = 25 + BanCo.HALF_SQUARE_SIZE;
    public TableRow() {
        
        setSize(width, height);
        
    }
    
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(new Color(167, 88, 44));
        g2d.fillRect(0, 0, width, height);
        
        //g2d.setFont(g2d.getFont().deriveFont(22f));
        g2d.setFont(new Font("League Spartan", Font.PLAIN,20));
        g2d.setColor(Color.black);
        for (int c = 0; c < BanCo.MAX_ROW;c++){
            g2d.drawString(String.valueOf((char) ('a' + c)),pos + (c*BanCo.SQUARE_SIZE),23);
        }
    }
}
