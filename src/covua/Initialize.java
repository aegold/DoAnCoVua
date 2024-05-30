package covua;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Initialize {
    JFrame frame = new JFrame("Cờ Vua");
    ThongTinGame info = new ThongTinGame();
    XuLyGame panel = XuLyGame.getInstance(info);
    TableRow trow = new TableRow();
    TableRow trow1 = new TableRow();
    TableCol tcol = new TableCol();
    TableCol tcol1 = new TableCol();
    
    private static Initialize instance;
    
    private Initialize() {
        
        panel.setBounds(30, 30, panel.CDAI, panel.CRONG);
        info.setBounds(780, 0, 300, 817);
        trow.setBounds(0, 0, trow.width, trow.height);
        trow1.setBounds(0, 750, trow1.width, trow1.height);
        tcol.setBounds(0, 30, tcol.width, tcol.height);
        tcol1.setBounds(750, 30, tcol1.width, tcol1.height);
        frame.setResizable(false);
        frame.add(panel);
        frame.add(info);
        frame.add(trow);
        frame.add(trow1);
        frame.add(tcol);
        frame.add(tcol1);
        //frame.pack();
        //frame.setSize(new Dimension(1020,760));
        frame.setSize(new Dimension(1080,817));
        
        frame.setLayout(null);
        frame.setBackground(Color.lightGray);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);   
    }
    
    public static Initialize getInstance(){
        if (instance == null)
            instance = new Initialize();
        return instance;
    }
    
    public void closing(){
        frame.getContentPane().removeAll();
        frame.setVisible(false);
        MenuFrame menu = new MenuFrame();
        menu.setVisible(true);
    }
}
