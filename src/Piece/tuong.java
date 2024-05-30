package Piece;

import covua.XuLyGame;

public class tuong extends QuanCo{
    public tuong(XuLyGame xuly, int col, int row,boolean isWhite,Ten ten) {
        super(xuly,col,row,isWhite,ten);
        
        if (isWhite)
            image = getImage("/resources/w_bishop");
        else
            image = getImage("/resources/b_bishop");
        
        point = 300;
    }
    @Override
    public boolean isNuocDiDung(int newCol,int newRow){
        return Math.abs(this.col-newCol) == Math.abs(this.row - newRow);
    }
    
    @Override // co the thay doi
    public boolean nuocDiBiChan(int newCol,int newRow){
        //trên trái
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
        return false;
    }

    @Override
    public int getPoint() {
        if (this.row != 0 || this.row != 7){
            this.point += 25;
        }
        return point;
    }
    
    
}
