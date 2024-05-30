package Piece;

import covua.XuLyGame;

public class xe extends QuanCo{
    public xe(XuLyGame xuly, int col, int row,boolean isWhite,Ten ten) {
        super(xuly,col,row,isWhite,ten);
        
        if (isWhite)
            image = getImage("/resources/w_rook");
        else
            image = getImage("/resources/b_rook");
        
        point = 500;
    }
    @Override
    public boolean isNuocDiDung(int newCol,int newRow){
        return (newCol == this.col || newRow == this.row);
    }
    
    @Override
    public boolean nuocDiBiChan(int newCol,int newRow){
        //trái
        if (this.col > newCol){
            for (int c = this.col - 1;c > newCol;c--){
                if (xuly.getQuan(c, this.row) != null)
                    return true;
            }
        }
            
        //phải
        if (this.col < newCol){
            for (int c = this.col + 1;c < newCol;c++){
               if (xuly.getQuan(c, this.row) != null)
                    return true;
            }
        }
        
        //trên
        if (this.row > newRow){
            for (int r = this.row - 1;r > newRow;r--){
                if (xuly.getQuan(this.col, r) != null)
                    return true;
            }
        }
        
        //dưới
        if (this.row < newRow){
            for (int r = this.row + 1;r < newRow;r++){
                if (xuly.getQuan(this.col, r) != null)
                    return true;
            }
        }
        return false;
    }

    @Override
    public int getPoint() {
        if (this.col != 0 || this.col != 7){
            this.point += 25;
        }
        if (this.col == 0 || this.col == 7){
            this.point -= 50;
        }
        return this.point;
    }
    
}
