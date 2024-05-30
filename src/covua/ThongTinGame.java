package covua;

import Piece.Ten;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.text.DecimalFormat;

public class ThongTinGame extends JPanel implements ActionListener{
    JLabel l = new JLabel("Information");
    JLabel luotdi = new JLabel("Lượt đi của Trắng");
    JLabel thangHang = new JLabel("Thăng hạng tốt");
    JLabel chieuTuong = new JLabel("Chiếu tướng");
    JLabel winner = new JLabel("");
    JLabel dauMayLabel = new JLabel("");
    JLabel credit = new JLabel("Nguyễn Thành Trung - 0073667 - 67IT3");
    public JButton maButton = new JButton("Mã");
    public JButton xeButton = new JButton("Xe");
    public JButton tuongButton = new JButton("Tượng");
    public JButton hauButton = new JButton("Hậu");
    public JButton unMake = new JButton("Đi lại");
    JButton reset = new JButton("RESET");
    Font font1 = new Font("Times New Roman",Font.BOLD,23);
    
    XuLyGame xuly = XuLyGame.getInstance(this);
    boolean dauMay = false;
    
    JLabel wCounterLabel = new JLabel();
    JLabel bCounterLabel = new JLabel();
    JLabel border = new JLabel("-------------------");
    Timer wTimer,bTimer;
    int wSecond, wMinute;
    int bSecond, bMinute;
    String ddWSecond, ddWMinute;	
    String ddBSecond, ddBMinute;	
    DecimalFormat dFormat = new DecimalFormat("00");
    DecimalFormat dBFormat = new DecimalFormat("00");
    
    public ThongTinGame(){
        setBackground(Color.lightGray);
        setLayout(null);
        l.setBounds(80,0,200,40);
        l.setFont(font1);
        luotdi.setBounds(50 ,200,200,40);
        luotdi.setFont(font1);
        thangHang.setBounds(70,500,200,40);
        thangHang.setFont(font1);
        chieuTuong.setBounds(85, 265, 200, 40);
        chieuTuong.setFont(font1);
        chieuTuong.setForeground(Color.RED);    
        chieuTuong.setVisible(false);
        winner.setBounds(80, 155, 200, 40);
        winner.setFont(font1);
        //winner.setHorizontalAlignment(JLabel.CENTER);
        credit.setBounds(30, 750, 300, 40);
        dauMayLabel.setFont(font1);
        dauMayLabel.setBounds(95,240,300,40);
        
        add(l);
        add(luotdi);
        add(thangHang);
        add(chieuTuong);
        add(winner);
        add(credit);
        addMouseListener(new MouseTest());
        
        //PROMOTION
        maButton.setSize(80, 30);
        xeButton.setSize(80, 30);
        tuongButton.setSize(80, 30);
        hauButton.setSize(80, 30);
        unMake.setSize(80, 30);
        reset.setSize(80, 30);
        
        maButton.setLocation(60, 560);
        xeButton.setLocation(150, 560);
        tuongButton.setLocation(60, 600);
        hauButton.setLocation(150, 600);
        unMake.setLocation(105, 660);
        reset.setLocation(105, 45);
        
        maButton.setActionCommand("ma");
        xeButton.setActionCommand("xe");
        tuongButton.setActionCommand("tuong");
        hauButton.setActionCommand("hau");
        unMake.setActionCommand("unmake");
        reset.setActionCommand("reset");
        
        maButton.setBackground(Color.decode("#9ee0e8"));
        xeButton.setBackground(Color.decode("#9ee0e8"));
        tuongButton.setBackground(Color.decode("#9ee0e8"));
        hauButton.setBackground(Color.decode("#9ee0e8"));
        unMake.setBackground(Color.decode("#f5dda5"));
        reset.setBackground(Color.decode("#f73528"));
        
        
        maButton.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        xeButton.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        tuongButton.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        hauButton.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        unMake.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        reset.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        
        add(maButton);
        add(xeButton);
        add(tuongButton);
        add(hauButton);
        add(unMake);
        add(reset);
        add(dauMayLabel);
        
        maButton.setEnabled(false);
        xeButton.setEnabled(false);
        tuongButton.setEnabled(false);
        hauButton.setEnabled(false);
        
        maButton.addActionListener(this);
        xeButton.addActionListener(this);
        tuongButton.addActionListener(this);
        hauButton.addActionListener(this);
        unMake.addActionListener(this);
        reset.addActionListener(this);
        
        wCounterLabel.setText("10:00");
        wCounterLabel.setFont(font1);
        wCounterLabel.setBounds(115, 405, 200, 40);
        
        border.setBounds(105, 375, 200, 40);
        
        bCounterLabel.setText("10:00");
        bCounterLabel.setFont(font1);
        bCounterLabel.setBounds(115, 345, 200, 40);
        //counterLabel.setHorizontalAlignment(JLabel.CENTER);
        
        if (!xuly.dauMay){
            wSecond =1;
            wMinute =10;
            add(wCounterLabel);
            taoWTimer();
            wTimer.start();
            
            add(border);
            
            bSecond = 1;
            bMinute = 10;
            add(bCounterLabel);
            taoBTimer();
            bTimer.stop();
        }
    }
    
    public void setButtonOff(){
        maButton.setEnabled(false);
        xeButton.setEnabled(false);
        tuongButton.setEnabled(false);
        hauButton.setEnabled(false);
        System.out.println("SET BUTTON OFF");
    }
    
    public void setButtonOn(){
        maButton.setEnabled(true);
        xeButton.setEnabled(true);
        tuongButton.setEnabled(true);
        hauButton.setEnabled(true);
        System.out.println("SET BUTTON ON");
    }
    
    public void showMessageDialog(){
        JOptionPane.showMessageDialog(null, "Không còn nước để đi lại", "Lỗi đi lại", JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ma")){
            System.out.println("thang cap ma");
            xuly.quanThangCap = Ten.MA;
            setButtonOff();
            xuly.thangCap();
        }
        if (e.getActionCommand().equals("xe")){
            System.out.println("thang cap xe");
            xuly.quanThangCap = Ten.XE;
            setButtonOff();
            xuly.thangCap();
        }
        if (e.getActionCommand().equals("tuong")){
            System.out.println("thang cap tuong");
            xuly.quanThangCap = Ten.TUONG;
            setButtonOff();
            xuly.thangCap();
        }
        if (e.getActionCommand().equals("hau")){
            System.out.println("thang cap hau");
            xuly.quanThangCap = Ten.HAU;
            setButtonOff();
            xuly.thangCap();
        }
        if (e.getActionCommand().equals("unmake")){
            if (!xuly.dauMay){
                System.out.println("Unmake Move");
                xuly.unDoMove();
            }            
        }
        if (e.getActionCommand().equals("reset")){
            int result = JOptionPane.showConfirmDialog(this,"Khởi tạo lại game?","Reset",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION){
                System.out.println("RESET");
                xuly.resetApplication();
            } else if (result == JOptionPane.NO_OPTION){
                
            }
        }
    }

    private void taoWTimer() {
       wTimer = new Timer(1000, new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               wSecond--;
               if (wSecond == -1){
                    wSecond = 59;
                    wMinute--;
               }
                    ddWSecond = dFormat.format(wSecond);
                    ddWMinute = dFormat.format(wMinute);
                    wCounterLabel.setText(ddWMinute+':'+ddWSecond);
               if (wMinute == 0 && wSecond == 0){
                    xuly.currentTurn = 3;
                    wTimer.stop();
                    winner.setText("Đen thắng!");
               }
           }
       });
    }
    
    private void taoBTimer() {
       bTimer = new Timer(1000, new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               bSecond--;
               if (bSecond == -1){
                    bSecond = 59;
                    bMinute--;
               }
                    ddBSecond = dBFormat.format(bSecond);
                    ddBMinute = dBFormat.format(bMinute);
                    bCounterLabel.setText(ddBMinute+':'+ddBSecond);
               if (bMinute == 0 && bSecond == 0){
                   xuly.currentTurn = 3;
                   bTimer.stop();
                   winner.setText("Trắng thắng!");
               }
           }
       });
    }
    
    public void switchTimer(boolean isWhite){
        if(isWhite){
            bTimer.stop();
            wTimer.start();
        }else{
            wTimer.stop();
            bTimer.start();
        }
    }
    
    public void stopBothTimers(){
        bTimer.stop();
        wTimer.stop();
    }
    
    public void resetTimer(){
        wSecond = 1;
        wMinute = 10;
        bSecond = 0;
        bMinute = 10;
        ddBSecond = dBFormat.format(bSecond);
        ddBMinute = dBFormat.format(bMinute);
        bCounterLabel.setText(ddBMinute+':'+ddBSecond);
        bTimer.stop();
        wTimer.start();
    }
    
    
    class MouseTest extends MouseAdapter{
        @Override
        public void mouseReleased(MouseEvent e){
            System.out.println("X:"+e.getX()+ " Y:" + e.getY());
        }
    }
    
}
