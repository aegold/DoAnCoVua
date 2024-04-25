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

public class thongTinGame extends JPanel implements ActionListener{
    JLabel l = new JLabel("Information");
    JLabel luotdi = new JLabel("Đến lượt của Trắng");
    JLabel thangHang = new JLabel("Thăng hạng tốt");
    JLabel chieuTuong = new JLabel("Chiếu tướng");
    JLabel winner = new JLabel("");
    JLabel credit = new JLabel("Nguyễn Thành Trung - 0073667 - 67IT3");
    public JButton maButton = new JButton("Mã");
    public JButton xeButton = new JButton("Xe");
    public JButton tuongButton = new JButton("Tượng");
    public JButton hauButton = new JButton("Hậu");
    public JButton unMake = new JButton("Đi lại");
   
    XuLyGame xuly = XuLyGame.getInstance(this);
    
    public thongTinGame(){
        setBackground(Color.lightGray);
        setLayout(null);
        l.setBounds(80,0,200,40);
        l.setFont(new Font("League Spartan",Font.PLAIN,20));
        luotdi.setBounds(50 ,200,200,40);
        luotdi.setFont(new Font("Times New Roman",Font.BOLD,23));
        thangHang.setBounds(70,500,200,40);
        thangHang.setFont(new Font("Times New Roman",Font.BOLD,23));
        chieuTuong.setBounds(85, 265, 200, 40);
        chieuTuong.setFont(new Font("Times New Roman",Font.BOLD,23));
        chieuTuong.setForeground(Color.RED);    
        chieuTuong.setVisible(false);
        winner.setBounds(80, 155, 200, 40);
        winner.setFont(new Font("Times New Roman",Font.BOLD,23));
        credit.setBounds(30, 750, 300, 40);
        
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
        
        maButton.setLocation(60, 560);
        xeButton.setLocation(150, 560);
        tuongButton.setLocation(60, 600);
        hauButton.setLocation(150, 600);
        unMake.setLocation(105, 660);
        
        maButton.setActionCommand("ma");
        xeButton.setActionCommand("xe");
        tuongButton.setActionCommand("tuong");
        hauButton.setActionCommand("hau");
        unMake.setActionCommand("unmake");
        
        maButton.setBackground(Color.decode("#9ee0e8"));
        xeButton.setBackground(Color.decode("#9ee0e8"));
        tuongButton.setBackground(Color.decode("#9ee0e8"));
        hauButton.setBackground(Color.decode("#9ee0e8"));
        unMake.setBackground(Color.decode("#f5dda5"));
        
        maButton.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        xeButton.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        tuongButton.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        hauButton.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        unMake.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        
        add(maButton);
        add(xeButton);
        add(tuongButton);
        add(hauButton);
        add(unMake);
        
        maButton.setEnabled(false);
        xeButton.setEnabled(false);
        tuongButton.setEnabled(false);
        hauButton.setEnabled(false);
        
        maButton.addActionListener(this);
        xeButton.addActionListener(this);
        tuongButton.addActionListener(this);
        hauButton.addActionListener(this);
        unMake.addActionListener(this);
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
            System.out.println("Unmake Move");
            xuly.unDoMove();
        }
    }
    
    class MouseTest extends MouseAdapter{
        @Override
        public void mouseReleased(MouseEvent e){
            System.out.println("X:"+e.getX()+ " Y:" + e.getY());
        }
    }
}
