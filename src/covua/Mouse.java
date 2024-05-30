package covua;

import Piece.QuanCo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter{
    XuLyGame xuly;
    public Mouse(XuLyGame xuly){
        this.xuly = xuly;
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        int col = e.getX() / BanCo.SQUARE_SIZE;
        int row = e.getY() / BanCo.SQUARE_SIZE;
        
        QuanCo selectedPiece = xuly.getQuan(col, row);
        if (selectedPiece != null && isRightTurn(selectedPiece)){
            xuly.quanDuocChon = selectedPiece;
        }
    }
    
    @Override
    public void mouseDragged(MouseEvent e){
        if (xuly.quanDuocChon != null){
            xuly.quanDuocChon.x = e.getX() - BanCo.HALF_SQUARE_SIZE;
            xuly.quanDuocChon.y = e.getY() - BanCo.HALF_SQUARE_SIZE;
            
            xuly.repaint();
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e){
        int col = e.getX() / BanCo.SQUARE_SIZE;
        int row = e.getY() / BanCo.SQUARE_SIZE;
        
        if (xuly.quanDuocChon != null){
            Move nuocdi = new Move(xuly, xuly.quanDuocChon, col, row);
            
            if(xuly.isNuocDiHopLe(nuocdi) && xuly.trongBanCo(xuly.quanDuocChon.x, xuly.quanDuocChon.y)){
                xuly.taoNuocDi(nuocdi);
            }else { // reset toạ độ quân nếu nước đi không hợp lệ
                xuly.quanDuocChon.x = xuly.quanDuocChon.col * BanCo.SQUARE_SIZE;
                xuly.quanDuocChon.y = xuly.quanDuocChon.row * BanCo.SQUARE_SIZE;
            }
            //System.out.println("X:" +e.getX() +" Y:" +e.getY());
//            System.out.println("quan "+xuly.quanDuocChon.ten.toString() + " da di cot "+xuly.quanDuocChon.col
//            + " hang " +xuly.quanDuocChon.row);
        }
        
        xuly.quanDuocChon = null;
        xuly.repaint();
    }

    private boolean isRightTurn(QuanCo selectedPiece) {
        if(xuly.currentTurn == 1){
           if (selectedPiece.getColor())
               return true;
            else
               return false;
        }
        if (xuly.currentTurn == -1){
            if (!selectedPiece.getColor())
                return true;
            else
                return false;
        }
        return false;    
    }

    

}
