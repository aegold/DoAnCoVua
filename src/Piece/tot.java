package Piece;

import covua.XuLyGame;

public class tot extends QuanCo{

   public tot(XuLyGame xuly,int col, int row,boolean isWhite,Ten ten) {
        super(xuly,col,row,isWhite,ten);
        
        if (isWhite)
            image = getImage("/resources/w_pawn");
        else
            image = getImage("/resources/b_pawn");
        
        point = 100;
    }
    @Override
    public boolean isNuocDiDung(int newCol,int newRow){
        int colorMove = isWhite ? 1 : -1 ;
        // đi 1 ô
        if (this.col == newCol && newRow == this.row - colorMove && xuly.getQuan(newCol, newRow) == null){
            this.di2Nuoc= false;
            return true;
        }
            
        // đi 2 ô
        if (nuocDauTien &&this.col == newCol && newRow == this.row - colorMove *2 && 
                xuly.getQuan(newCol, newRow) == null ){
                this.di2Nuoc = true;
                return true;
        }
            
        // ăn quân chéo
        if (Math.abs(newCol - this.col) == 1 && newRow == this.row - colorMove && xuly.getQuan(newCol, newRow) != null){
            this.di2Nuoc= false;
            return true;
        }
            
        // en passant
        if (newCol == xuly.enPassantCol && newRow == xuly.enPassantRow &&
//                newRow == this.row + this.colorMove 
//               Math.abs(this.col - newCol) == 1 && 
                xuly.getQuan(newCol, newRow + colorMove)!= null &&
                xuly.getQuan(newCol, newRow + colorMove).getColor() != this.isWhite &&
                xuly.getQuan(newCol, newRow + colorMove).di2Nuoc &&
                xuly.getQuan(newCol, newRow + colorMove).row == this.row &&
                Math.abs(newCol - this.col) == 1
                ){
                return true;
        }
        return false;
    }
    
    @Override
    public boolean nuocDiBiChan(int col,int row){
        return false;
    }
    
    public int getPoint(){
        if (!this.isWhite){
            if (this.row > 2 && this.row < 5){
                this.point += 50;
            }
            if (this.row == 6){
                this.point += 100;
            }
            if (this.row == 7){
                this.point += 250;
            }
        } else {
            if (this.row > 2 && this.row < 5){
                this.point += 50;
            }
            if (this.row == 1){
                this.point += 100;
            }
            if (this.row == 0){
                this.point += 250;
            }
        }
        if (this.col > 1 && this.col < 6){
            this.point += 100;
        }
        return this.point;
    }
    
}
