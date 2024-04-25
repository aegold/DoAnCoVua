package covua;

import Piece.QuanCo;
import Piece.Ten;

public class Checker {
    XuLyGame xuly;
    
    public Checker(XuLyGame xuly){
        this.xuly = xuly;
    }
    
    public boolean isVuaBiChieu(int newCol,int newRow,boolean isWhite){
        QuanCo vua = xuly.timVua(isWhite);
        assert vua != null;
        
        int vuaCol = vua.col;
        int vuaRow = vua.row;
        
        if (xuly.quanDuocChon != null && xuly.quanDuocChon.ten == Ten.VUA){
            vuaCol = newCol;
            vuaRow = newRow;
        }
          
        return  anBoiXe(newCol,newRow,vua,vuaCol,vuaRow,0,1) || //trên    
                anBoiXe(newCol,newRow,vua,vuaCol,vuaRow,1,0) || //phải
                anBoiXe(newCol,newRow,vua,vuaCol,vuaRow,0,-1) || //dưới
                anBoiXe(newCol,newRow,vua,vuaCol,vuaRow,-1,0) || //trái
                
                anBoiTuong(newCol,newRow,vua,vuaCol,vuaRow,-1,-1) || // trên trái
                anBoiTuong(newCol,newRow,vua,vuaCol,vuaRow,1,-1) || // trên phải
                anBoiTuong(newCol,newRow,vua,vuaCol,vuaRow,1,1) || //dưới phải
                anBoiTuong(newCol,newRow,vua,vuaCol,vuaRow,-1,1) || // dưới trái
                
                anBoiMa(newCol,newRow, vua, vuaCol, vuaRow) ||
                anBoiTot(newCol, newRow, vua, vuaCol, vuaRow) ||
                anBoiVua(vua, vuaCol, vuaRow);
    }
    
    private boolean anBoiXe(int newCol,int newRow,QuanCo vua,int vuaCol,int vuaRow,int colVal,int rowVal){
        for (int i = 1;i < 8;i++){
            if (vuaCol + (i * colVal) == newCol && vuaRow + (i * rowVal) == newRow){
                break;
            }
                
            QuanCo quan = xuly.getQuan(vuaCol + (i*colVal), vuaRow + (i*rowVal));
            if (quan != null && quan != xuly.quanDuocChon){
                if (!xuly.quanCungMau(quan, vua) && (quan.ten == Ten.XE || quan.ten == Ten.HAU)){
                    return true;
                }
                break;
            }
        }
        return false;
    }
    
    private boolean anBoiTuong(int newCol,int newRow,QuanCo vua,int vuaCol,int vuaRow,int colVal,int rowVal){
        for (int i = 1;i < 8;i++){
            if (vuaCol - (i*colVal) == newCol && vuaRow - (i*rowVal) == newRow){
                break;
            }
                
            QuanCo quan = xuly.getQuan(vuaCol - (i*colVal), vuaRow - (i*rowVal));
            if (quan != null && quan != xuly.quanDuocChon){
                if (!xuly.quanCungMau(quan, vua) && (quan.ten == Ten.TUONG || quan.ten == Ten.HAU)){
                    return true;
                }
                break;
            }
        }
        return false;
    }
    
    private boolean anBoiMa(int colVal,int rowVal,QuanCo vua,int vuaCol,int vuaRow){
        return checkMa(xuly.getQuan(vuaCol -1, vuaRow -2), vua, colVal, rowVal) ||
               checkMa(xuly.getQuan(vuaCol +1, vuaRow -2), vua, colVal, rowVal) ||
               checkMa(xuly.getQuan(vuaCol +2, vuaRow -1), vua, colVal, rowVal) ||
               checkMa(xuly.getQuan(vuaCol +2, vuaRow +1), vua, colVal, rowVal) ||
               checkMa(xuly.getQuan(vuaCol +1, vuaRow +2), vua, colVal, rowVal) ||
               checkMa(xuly.getQuan(vuaCol -1, vuaRow +2), vua, colVal, rowVal) ||
               checkMa(xuly.getQuan(vuaCol -2, vuaRow +1), vua, colVal, rowVal) ||
               checkMa(xuly.getQuan(vuaCol -2, vuaRow -1), vua, colVal, rowVal);
    }
    
    private boolean checkMa(QuanCo q, QuanCo vua,int col,int row){
        return q != null && !xuly.quanCungMau(q, vua) && q.ten == Ten.MA && !(q.col == col && q.row == row);
    }
    
    private boolean anBoiVua(QuanCo vua, int vuaCol, int vuaRow){
        return checkVua(xuly.getQuan(vuaCol -1 , vuaRow -1),vua) ||
               checkVua(xuly.getQuan(vuaCol +1 , vuaRow -1),vua) ||
               checkVua(xuly.getQuan(vuaCol , vuaRow -1),vua) ||
               checkVua(xuly.getQuan(vuaCol -1 , vuaRow),vua) ||
               checkVua(xuly.getQuan(vuaCol +1 , vuaRow),vua) ||
               checkVua(xuly.getQuan(vuaCol -1 , vuaRow -1),vua) ||
               checkVua(xuly.getQuan(vuaCol -1 , vuaRow +1),vua) ||
               checkVua(xuly.getQuan(vuaCol +1 , vuaRow +1),vua) ||
               checkVua(xuly.getQuan(vuaCol , vuaRow +1),vua) ;
    }
    
    private boolean checkVua(QuanCo q, QuanCo vua){
        return q != null && !xuly.quanCungMau(q, vua) && q.ten == Ten.VUA;
    }
    
    private boolean anBoiTot(int newCol,int newRow,QuanCo vua, int vuaCol, int vuaRow){
        int colorVal = vua.getColor() ? -1 : 1;
        return checkTot(xuly.getQuan(vuaCol + 1, vuaRow + colorVal), vua, newCol, newRow) ||
               checkTot(xuly.getQuan(vuaCol - 1, vuaRow + colorVal), vua, newCol, newRow);
    }
    
    private boolean checkTot(QuanCo q, QuanCo vua,int col, int row){
        return q != null && !xuly.quanCungMau(q, vua) && q.ten == Ten.TOT && !(q.col == col && q.row == row);
    }
    
    public boolean khongConNuocDi(boolean isWhite){
        QuanCo vua = xuly.timVua(isWhite);
        if (vua != null){
            for (QuanCo quan : xuly.q1){
                xuly.quanDuocChon = quan == vua ? vua : null;
                if (xuly.quanCungMau(vua, quan)){
                    for (int r = 0;r < BanCo.MAX_ROW;r++){
                        for (int c = 0; c < BanCo.MAX_COL;c++){
                            //QuanCo quanXY = xuly.getQuan(c, r);
                            Move nuocDi = new Move (xuly,quan,c,r);
                            if (xuly.isNuocDiHopLe(nuocDi)){
                                String mau = quan.getColor() ? "Trang" : "Den";
                                System.out.println(quan.ten +" "+mau+ " Con Nuoc Di");
                                //System.out.println("Con nuoc Di");
                                return false;
                            }    
                        }
                    }
                }
            }
        }
        return true;
    }
    
}
