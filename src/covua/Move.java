package covua;

import Piece.QuanCo;

public class Move {
    int oldCol;
    int newCol;
    int oldRow;
    int newRow;
    
    QuanCo quanDuocChon;
    QuanCo quanBiBat;
    QuanCo quanThangCap = null;
    QuanCo quanNhapThanh = null;
    boolean thangCap = false;

    public Move(XuLyGame xuly, QuanCo quan,int newCol, int newRow) {
        this.oldCol = quan.col; // vị trí hiện tại
        this.newCol = newCol;   // vị trí mới
        this.oldRow = quan.row;
        this.newRow = newRow;
        
        this.quanDuocChon = quan;
        this.quanBiBat = xuly.getQuan(newCol,newRow);
    }

   
}
