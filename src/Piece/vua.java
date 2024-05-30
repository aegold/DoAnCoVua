package Piece;

import covua.XuLyGame;

public class vua extends QuanCo{
    public vua(XuLyGame xuly,int col, int row,boolean isWhite,Ten ten) {
        super(xuly,col,row,isWhite,ten);
        
        if (isWhite)
            image = getImage("/resources/w_king");
        else
            image = getImage("/resources/b_king");
        
        point = 0;
    }
    @Override
    public boolean isNuocDiDung(int newCol,int newRow){
        if (Math.abs(newCol-this.col) * Math.abs(newRow-this.row) == 1 ||
                Math.abs(newCol-this.col) + Math.abs(newRow-this.row) == 1)
            return true;
        
        //Nhập thành phai
        if (newCol == this.col + 2 && newRow == this.row && !nuocDiBiChan(newCol, newRow) &&
                nuocDauTien){
            if(xuly.getQuan(this.col + 3, this.row) != null &&
                    xuly.getQuan(this.col + 3, this.row).nuocDauTien){
                return true;
            }  
        }
        
        //Nhập thành trái
        if (newCol == this.col - 2 && newRow == this.row && !nuocDiBiChan(newCol, newRow) &&
                nuocDauTien){
            if (xuly.getQuan(this.col - 4, this.row) != null &&
                    xuly.getQuan(this.col - 4, this.row).nuocDauTien){
                if(xuly.getQuan(this.col-3, this.row) == null){
                    return true;
                }
            }
        }
        return false;   
    }
    
    @Override
    public boolean nuocDiBiChan(int newCol,int newRow){
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
    public int getPoint(){
        if (!this.isWhite){
            if (    ((this.col >=0 && this.col < 2) ||
                     (this.col >=5 && this.col <=7))  && this.row < 2){
                this.point += 30;
            }
            if (this.row == 2){
                this.point -= 20;
            }
            if (this.row >= 3){
                this.point -= 50;
            }
        } else {
            if (    ((this.col >=0 && this.col < 2) ||
                     (this.col >=5 && this.col <=7))  && this.row > 5){
                this.point += 30;
            }
            if (this.row == 5){
                this.point -= 20;
            }
            if (this.row <= 4){
                this.point -= 50;
            }
        }
        return point;
    }
    
    public boolean isChecked(int col,int row){
        return xuly.check.isVuaBiChieu(col, row, this.isWhite);
    }
}
