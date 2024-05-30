package covua;

import Piece.QuanCo;

public class UnDoMove {
    QuanCo quanVuaDi;
    QuanCo quanBiBat;
    int oldcol;
    int oldrow;
    boolean firstMove = false;
    boolean thangCap = false;

    public UnDoMove(QuanCo quanVuaDi,QuanCo quanBiBat, int oldcol, int oldrow) {
        this.quanVuaDi = quanVuaDi;
        this.quanBiBat = quanBiBat;
        this.oldcol = oldcol;
        this.oldrow = oldrow;
    }
    
}
