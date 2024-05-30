package Piece;

import covua.XuLyGame;
import covua.BanCo;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class QuanCo {
    BufferedImage image;
    public int x,y,col,row; //x,y trả về toạ độ quân cờ trên frame
    public int oldCol, oldRow;
    boolean isWhite;
    XuLyGame xuly;
    BanCo b = new BanCo();
    public boolean nuocDauTien = true;
    public boolean di2Nuoc = false;
    public Ten ten;
    //Nước đặc biệt
    public boolean isEnpassant = false;
    public int colorMove = isWhite ? 1 : -1 ;
   
    public QuanCo(XuLyGame xuly,int col,int row,boolean isWhite,Ten ten){
        this.col = col;
        this.row = row;
        this.isWhite = isWhite;
        x = getX();
        y = getY();
        this.xuly = xuly;
        this.ten = ten;
    }
    
    
    BufferedImage getImage(String path){
        image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path+".png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return image;
        
    }
    
    
    public int getX(){
        return col * BanCo.SQUARE_SIZE;
    }
    
    public int getY(){
        return row * BanCo.SQUARE_SIZE;
    }
    
    public boolean getColor(){
        return isWhite;
    }
    
    public boolean isNuocDiDung(int col,int row){
        return true;
    }
    
    public boolean nuocDiBiChan(int col,int row){
        return false;
    }
    public boolean diCungO(int newCol,int newRow){
        return newCol == this.col && newRow == this.row;
    }
    
    public void draw(Graphics2D g2){
       g2.drawImage(image,x+5,y+5,80,80,null);
    }
    
    public boolean biChieu(int col,int row){
        return xuly.check.isVuaBiChieu(col,row,this.isWhite);
    }
    
    public boolean coTheDi(QuanCo quanXY,int col,int row){
        return isNuocDiDung(col, row) && (quanXY != null || !xuly.quanCungMau(quanXY, this) &&
                !nuocDiBiChan(col, row) && !biChieu(col,row)) && xuly.trongBanCo(col*BanCo.SQUARE_SIZE,row*BanCo.SQUARE_SIZE)
                && !diCungO(col, row);
    }
    
}
