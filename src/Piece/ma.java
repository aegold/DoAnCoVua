package Piece;

import covua.XuLyGame;

public class ma extends QuanCo{
    public ma(XuLyGame xuly, int col, int row,boolean isWhite,Ten ten) {
        super(xuly,col,row,isWhite,ten);
        
        if (isWhite)
            image = getImage("/resources/w_knight");
        else
            image = getImage("/resources/b_knight");
       
        point = 300;
    }
    
    @Override
    public boolean isNuocDiDung(int newCol,int newRow){
        return Math.abs(newCol-this.col)*Math.abs(newRow-this.row) == 2;
    }
    
    @Override
    public boolean nuocDiBiChan(int newCol,int newRow){
        return false;
    }

    @Override
    public int getPoint() {
        if (this.col >= 2 && this.col <=5 && this.row >= 2 && this.row <= 5){
            this.point += 50;
        }
        if (this.col == 0 || this.col == 7 || this.row == 0 || this.row == 7){
            this.point -= 55;
        }
        return this.point;
    }
    
}
