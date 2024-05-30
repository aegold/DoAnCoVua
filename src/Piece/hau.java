package Piece;

import covua.XuLyGame;

public class hau extends QuanCo{
    public hau(XuLyGame xuly, int col, int row,boolean isWhite,Ten ten) {
        super(xuly,col,row,isWhite,ten);
        
        if (isWhite)
            image = getImage("/resources/w_queen");
        else
            image = getImage("/resources/b_queen");
       
        point = 900;
    }
    @Override
    public boolean isNuocDiDung(int newCol,int newRow){
        return (Math.abs(this.col-newCol) == Math.abs(this.row - newRow) ||
                newCol == this.col || newRow == this.row);
    }
    
    @Override // co the thay doi
    public boolean nuocDiBiChan(int newCol,int newRow){
        if(Math.abs(this.col-newCol) == Math.abs(this.row - newRow)){
            if (this.col > newCol && this.row > newRow){
                for(int i = 1;i<Math.abs(this.col - newCol);i++){
                    if (xuly.getQuan(this.col - i, this.row - i) != null)
                        return true;
                }
            }
        //trên phải
            if (this.col < newCol && this.row > newRow){
                for(int i = 1;i<Math.abs(this.col - newCol);i++){
                    if (xuly.getQuan(this.col + i, this.row - i) != null)
                        return true;
                }
            }
        //dưới trái
            if (this.col > newCol && this.row < newRow){
                for(int i = 1;i<Math.abs(this.col - newCol);i++){
                    if (xuly.getQuan(this.col - i, this.row + i) != null)
                        return true;
                }
            }
        // dưới phải
            if (this.col < newCol && this.row < newRow){
                for(int i = 1;i<Math.abs(this.col - newCol);i++){
                    if (xuly.getQuan(this.col + i, this.row + i) != null)
                        return true;
                }
            }
        }else {
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
        }
        
        return false;
    }

    @Override
    public int getPoint() {
        if (this.row == 1 || this.row == 6){
            this.point += 25;
        }
        if (this.row > 1 && this.row < 6){
            this.point += 75;
        }
        return point;
    }
    
}
