package Piece;

import covua.XuLyGame;

public class ma extends QuanCo{
    public ma(XuLyGame xuly, int col, int row,boolean isWhite,Ten ten) {
        super(xuly,col,row,isWhite,ten);
        
        if (isWhite)
            image = getImage("/resources/w_knight");
        else
            image = getImage("/resources/b_knight");
       
    }
    
    @Override
    public boolean isNuocDiDung(int newCol,int newRow){
        return Math.abs(newCol-this.col)*Math.abs(newRow-this.row) == 2;
    }
    
    @Override
    public boolean nuocDiBiChan(int newCol,int newRow){
        return false;
    }
}
