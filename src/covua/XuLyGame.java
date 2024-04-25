package covua;

import Piece.*; 
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;
import javax.swing.JPanel;

public class XuLyGame extends JPanel{
    // SINGLETON
    private static XuLyGame instance;
    
    // DIMENSION
    public final int CDAI = 720;
    public final int CRONG = 720;
    
    // Khởi tạo bàn cờ
    BanCo banco = new BanCo();
    
    // Khởi tạo quân cờ
    ArrayList <QuanCo> q = new ArrayList<>(); // dự phòng
    public ArrayList <QuanCo> q1 = new ArrayList<>();
    
    // Chọn quân cờ
    QuanCo quanDuocChon;
    
    // Khởi tạo MouseListenerEvent
    Mouse m = new Mouse(this);
    
    // Quân Thăng Cấp
    Ten quanThangCap;
    QuanCo totThangCap;
    
    // Lượt đi
    public final int WHITE = 1;
    public final int BLACK = 2;
    public final int PAUSE = 3;
    public int currentTurn = WHITE; 
    public int prevTurn;
    
    thongTinGame info;
    public int enPassantCol = -1;
    public int enPassantRow = -1;
    
    // check nước đi
    public Checker check = new Checker(this);
    
    // unmake move
    Stack<UnDoMove> oldMoveList = new Stack<>();
    
    private final QuanCo vuaTrang = new vua(this,4,7,true,Ten.VUA);
    private final QuanCo vuaDen = new vua(this,4,0,false,Ten.VUA);
    
    private XuLyGame(thongTinGame info){
        //setPreferredSize(new Dimension(CDAI,CRONG));
        setSize(CDAI,CRONG);
        this.addMouseListener(m);
        this.addMouseMotionListener(m);
        
        //testChieuTuong();
        datQuanCo();
        copyQuan(q, q1);
        this.info = info;
    }
    
    // SINGLETON
    public static XuLyGame getInstance(thongTinGame info){
        if (instance == null)
            instance = new XuLyGame(info);
        return instance;
    }
    
    public void copyQuan(ArrayList<QuanCo> q,ArrayList<QuanCo> q1){
        for (int i = 0; i < q.size();i++){
            q1.add(q.get(i));
        }
    }
    
    public void testChieuTuong(){
        q.add(new hau(this, 2, 1, true, Ten.HAU));
        q.add(vuaDen);
        q.add(vuaTrang);
    }
    
    public void datQuanCo() {
        q.add(new tot(this,0,6,true,Ten.TOT));
        q.add(new tot(this,1,6,true,Ten.TOT));
        q.add(new tot(this,2,6,true,Ten.TOT));
        q.add(new tot(this,3,6,true,Ten.TOT));
        q.add(new tot(this,4,6,true,Ten.TOT));
        q.add(new tot(this,5,6,true,Ten.TOT));
        q.add(new tot(this,6,6,true,Ten.TOT));
        q.add(new tot(this,7,6,true,Ten.TOT));
        q.add(new xe(this,0,7,true,Ten.XE));
        q.add(new xe(this,7,7,true,Ten.XE));
        q.add(new ma(this,1,7,true,Ten.MA));
        q.add(new ma(this,6,7,true,Ten.MA));
        q.add(new tuong(this,5,7,true,Ten.TUONG));
        q.add(new tuong(this,2,7,true,Ten.TUONG));
        q.add(new hau(this,3,7,true,Ten.HAU));
        q.add(vuaTrang);
        //ĐEN
        q.add(new tot(this,0,1,false,Ten.TOT));
        q.add(new tot(this,1,1,false,Ten.TOT));
        q.add(new tot(this,2,1,false,Ten.TOT));
        q.add(new tot(this,3,1,false,Ten.TOT));
        q.add(new tot(this,4,1,false,Ten.TOT));
        q.add(new tot(this,5,1,false,Ten.TOT));
        q.add(new tot(this,6,1,false,Ten.TOT));
        q.add(new tot(this,7,1,false,Ten.TOT));
        q.add(new xe(this,0,0,false,Ten.XE));
        q.add(new xe(this,7,0,false,Ten.XE));
        q.add(new ma(this,1,0,false,Ten.MA));
        q.add(new ma(this,6,0,false,Ten.MA));
        q.add(new tuong(this,2,0,false,Ten.TUONG));
        q.add(new tuong(this,5,0,false,Ten.TUONG));
        q.add(new hau(this,3,0,false,Ten.HAU));
        q.add(vuaDen);
    }
    
    //lấy quân ở vị trí con trỏ chuột
    public QuanCo getQuan(int col,int row){
        for (QuanCo quan:q1){
            if (quan.col == col && quan.row == row){
                return quan;
            }
        }
        return null;
    }
    
    public QuanCo timVua(boolean isWhite){
        return isWhite ? vuaTrang : vuaDen;
    }
    
    public boolean isNuocDiHopLe(Move nuocdi){
        if (quanCungMau(nuocdi.quanDuocChon, nuocdi.quanBiBat)){
            return false;
        }
        if (nuocdi.quanDuocChon.diCungO(nuocdi.newCol, nuocdi.newRow)){
            return false;
        }
        if (!nuocdi.quanDuocChon.isNuocDiDung(nuocdi.newCol, nuocdi.newRow)){
            return false;
        }
        if (nuocdi.quanDuocChon.nuocDiBiChan(nuocdi.newCol, nuocdi.newRow)){
            return false;
        }
        if (check.isVuaBiChieu(nuocdi.newCol,nuocdi.newRow,nuocdi.quanDuocChon.getColor())){
            return false;
        }
        return true;
    }
    
    public boolean quanCungMau(QuanCo q1,QuanCo q2){
        if (q1 == null || q2 == null)
            return false;
        else
            return q1.getColor() == q2.getColor();
    }
    
    public void taoNuocDi(Move nuocdi){
        if (nuocdi.quanDuocChon.ten == Ten.VUA){
            if (Math.abs(nuocdi.quanDuocChon.col - nuocdi.newCol) == 2 ){
                if (nuocdi.newCol == 6 && !check.isVuaBiChieu(6,nuocdi.quanDuocChon.row,nuocdi.quanDuocChon.getColor()))
                    nhapThanhPhai(nuocdi);
                else if (nuocdi.newCol == 2 && !check.isVuaBiChieu(2,nuocdi.quanDuocChon.row,nuocdi.quanDuocChon.getColor()))
                    nhapThanhTrai(nuocdi);    
                
                changeTurn();
            }
            thuchienNuocDi(nuocdi);
        } else         
        if (nuocdi.quanDuocChon.ten == Ten.TOT){
            nuocdiTot(nuocdi);
        } else {
            thuchienNuocDi(nuocdi);
        }
        
        String gameState = getGameState(nuocdi.quanDuocChon.getColor());
        info.winner.setText(gameState);
    }
    
    private void nhapThanhPhai(Move nuocdi) {
        System.out.println("nhap thanh phai");
        if (nuocdi.quanDuocChon.getColor()){
            nuocdi.quanBiBat = getQuan(7, 7);
            q1.add(new xe(this, 5, 7, true, Ten.XE));
        }else {
            nuocdi.quanBiBat = getQuan(7, 0);
            q1.add(new xe(this, 5, 0, false, Ten.XE));
        }
        thuchienNuocDi(nuocdi);
    }
    
    private void nhapThanhTrai(Move nuocdi){
        System.out.println("nhap thanh trai");
        if (nuocdi.quanDuocChon.getColor()){
            nuocdi.quanBiBat = getQuan(0, 7);
            q1.add(new xe(this,3,7,true,Ten.XE));
        }else {
            nuocdi.quanBiBat = getQuan(0, 0);
            q1.add(new xe(this,3,0,false,Ten.XE));
        }
        thuchienNuocDi(nuocdi);
    }
    
    private void nuocdiTot(Move nuocdi){
        //PROMOTION
        int promotionIndex = nuocdi.quanDuocChon.getColor() ? 0 : 7;
        if (nuocdi.newRow == promotionIndex){
            System.out.println("THANG CAP");
            totThangCap = getQuan(quanDuocChon.col, quanDuocChon.row);
            thuchienNuocDi(nuocdi);
            prevTurn = currentTurn;
            currentTurn = PAUSE;
            info.setButtonOn();
            //TODO: SUA ENPASSANT
        }
        if (nuocdi.newRow == enPassantRow && nuocdi.newCol == enPassantCol && 
                Math.abs(nuocdi.newCol - nuocdi.quanDuocChon.col) == 1){
            nuocdi.quanBiBat = getQuan(nuocdi.newCol, nuocdi.quanDuocChon.row);
        }
        if (Math.abs(nuocdi.newRow - nuocdi.quanDuocChon.row) == 2){
            enPassantCol = quanDuocChon.col;
            enPassantRow = nuocdi.newRow + (quanDuocChon.getColor() ? 1 : -1);
        }else {
            enPassantCol = -1;
            enPassantRow = -1;
        }
        thuchienNuocDi(nuocdi);
    }
    
    public void thangCap(){
        switch (quanThangCap) {
            case Ten.MA:
                q1.add(new ma(this, totThangCap.col, totThangCap.row, totThangCap.getColor(), Ten.MA));
                break;
            case Ten.XE:
                q1.add(new xe(this, totThangCap.col, totThangCap.row, totThangCap.getColor(), Ten.XE));
                break;
            case Ten.TUONG:
                q1.add(new tuong(this, totThangCap.col, totThangCap.row, totThangCap.getColor(), Ten.TUONG));
                break;
            case Ten.HAU:
                q1.add(new hau(this, totThangCap.col, totThangCap.row, totThangCap.getColor(), Ten.HAU));
        }
        remove(totThangCap);
        quanThangCap = null;
        currentTurn = prevTurn;
        prevTurn = 0;
        repaint(); 
    }
    
    private void thuchienNuocDi(Move nuocdi){
        UnDoMove move = new UnDoMove(nuocdi.quanDuocChon,nuocdi.quanBiBat, nuocdi.oldCol, nuocdi.oldRow);
        if (move.quanVuaDi.nuocDauTien){
            move.firstMove = true;
        }
        oldMoveList.push(move);
        
        String mau = nuocdi.quanDuocChon.getColor() ? "Trang" : "Den";
        System.out.println("quan " + nuocdi.quanDuocChon.ten.toString() +" "+mau+" da di cot "
        + (nuocdi.newCol+1)  +" hang " +(nuocdi.newRow +1 ) );
        nuocdi.quanDuocChon.col = nuocdi.newCol;
        nuocdi.quanDuocChon.row = nuocdi.newRow;
        nuocdi.quanDuocChon.x = nuocdi.newCol * BanCo.SQUARE_SIZE;
        nuocdi.quanDuocChon.y = nuocdi.newRow * BanCo.SQUARE_SIZE;
            
        nuocdi.quanDuocChon.nuocDauTien = false;
        //nuocdi.quanDuocChon.isEnpassant = false;
        setDi2NuocFalse(nuocdi.quanDuocChon);
        remove(nuocdi.quanBiBat);
        
        changeTurn();
    }
    
    private void changeTurn(){
        if (currentTurn == WHITE){
            currentTurn = BLACK;
            info.luotdi.setText("Đến lượt của Đen");
        }
        else if (currentTurn == BLACK){
            currentTurn = WHITE;
            info.luotdi.setText("Đến lượt của Trắng");
        }
    }
    
    public void remove(QuanCo quanBiBat){
        if (quanBiBat != null){
            System.out.println("Xoa quan" + quanBiBat.ten);
            q1.remove(quanBiBat);
        }
    }
    
    private int getEffect(int z){
        return (z + banco.HALF_SQUARE_SIZE) / banco.SQUARE_SIZE;
    }
    
    public boolean trongBanCo(int x , int y){
        x += 45;
        y += 45;
        if(x >= 0 && x <= 715 && y >= 0 && y <= 715)
            return true;
        else
            return false;
    }
    
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        banco.draw(g2);
        
        if(quanDuocChon != null){ // highlights xanh
            for (int r = 0;r < banco.MAX_ROW;r++){
                for (int c =0; c < banco.MAX_COL;c++){
                    if (isNuocDiHopLe(new Move(this,quanDuocChon,c,r))){
                        g2.setColor(new Color(97, 245, 39));
                        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.7f));
                        g2.fillRect(c*banco.SQUARE_SIZE, r*banco.SQUARE_SIZE, banco.SQUARE_SIZE, banco.SQUARE_SIZE);
                    }  
                }
            }
            //highlight trắng
            if (trongBanCo(quanDuocChon.x, quanDuocChon.y)){
                g2.setColor(Color.WHITE);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.6f));
                g2.fillRect(getEffect(quanDuocChon.x)*banco.SQUARE_SIZE, 
                        getEffect(quanDuocChon.y)*banco.SQUARE_SIZE,BanCo.SQUARE_SIZE, BanCo.SQUARE_SIZE);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
            quanDuocChon.draw(g2);
            }
        }
        for (QuanCo quan :q1){
            quan.draw(g2);
        }
    }
    
    private boolean convertInttoBool(int x){
        if (x == 1)
            return true;
        
        return false;
    }
    
    private boolean khongConNuoc(boolean isWhite){
        return check.khongConNuocDi(isWhite);
    }
    
    public String getGameState(boolean isWhite){
        if (khongConNuoc(!isWhite)){
            System.out.println("True");
            boolean turn = convertInttoBool(currentTurn);
            QuanCo vua = timVua(turn);
            if (vua != null){
                if (vua.biChieu(vua.col, vua.row)){
                    System.out.println(isWhite ? "Trang win" :"Den win");
                    currentTurn = 3;
                    return isWhite ? "Trắng Thắng!" :"Đen Thắng!"; 
                } else {
                    System.out.println("Hoa");
                    currentTurn = 3;
                    return "Hoà";
                }
            }
        }
        System.out.println("Dieu kien Sai");
        return "Tiep";
    }
    
    private void setDi2NuocFalse(QuanCo quanVuaDi){
        for (QuanCo q : q1){
            if (q != quanVuaDi){
                q.di2Nuoc = false;
            }
        }
    }
  
    public void unDoMove(){
        UnDoMove oldMove = null;
        try {
            oldMove = oldMoveList.pop();
        } catch (EmptyStackException e) {
            System.out.println("Khong con nuoc di");
            info.showMessageDialog();
        } 
        if (oldMove != null){
            oldMove.quanVuaDi.col = oldMove.oldcol;
            oldMove.quanVuaDi.row = oldMove.oldrow;
            oldMove.quanVuaDi.x = oldMove.oldcol * BanCo.SQUARE_SIZE;
            oldMove.quanVuaDi.y = oldMove.oldrow * BanCo.SQUARE_SIZE;
            
            if (oldMove.firstMove){
                oldMove.quanVuaDi.nuocDauTien = true;
            }
            
            if (oldMove.quanBiBat != null)
                q1.add(oldMove.quanBiBat);
            
            changeTurn();
            repaint();
        }
    }
}
