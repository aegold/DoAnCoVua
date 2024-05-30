package covua;

import Piece.*;
import java.util.ArrayList;
import java.util.Random;

public class Bot {
    XuLyGame xuLy;
    public final int voCuc = 999999;
    public final int amVoCuc = -voCuc;
    public final int depth = 3;

    
    public Bot(XuLyGame xuLy) {
        this.xuLy = xuLy;
    }
    
    public void botMove(){
        if (xuLy.currentTurn == -1){
            botTaoNuocDi();
        }
    }
    
    public void botTaoNuocDi(){
        
        ArrayList<Move> validMoveList = timCacNuocDiHopLe();
        
        if (validMoveList.size() < 1) {
            // đưa ra dội chiến thắng
            xuLy.currentTurn = 3;
            return;
        }
        
        Random rng = new Random();
        Move nuocDuocChon = validMoveList.get(rng.nextInt(validMoveList.size()));
        
        int best = Integer.MAX_VALUE;
        for (Move move: validMoveList){
            xuLy.taoNuocDi(move);
            int search = search(depth, amVoCuc, voCuc);
            if (move.quanBiBat != null){
                search -= 100;
            }
            if (search < best){
                nuocDuocChon = move;
                best = search;
            }
            xuLy.botXoaNuocAo(move);
        }
        xuLy.taoNuocDi(nuocDuocChon);
        String gameState = xuLy.getGameState(xuLy.getTurn());
        
        if (!gameState.equals("Tiep")){
            xuLy.info.winner.setText(gameState);
            xuLy.currentTurn = 3;
        }
        xuLy.repaint();
        
    }

    public int search(int depth, int alpha, int beta){
        if (depth == 0){
            return evaluate();
        }
        
        ArrayList<Move> movesList = timCacNuocDiHopLe();
        if (movesList.size() == 0){
            QuanCo vua = xuLy.timVua(xuLy.getTurn());
            if (vua.biChieu(vua.col, vua.row)){
                return Integer.MIN_VALUE;
            }
            return 0;
        }
        for (Move move: movesList){
            xuLy.botTaoNuocAo(move);
            int evaluation = search(depth - 1,-beta,-alpha);
            if (move.quanBiBat != null){
                evaluation += 100;
            }
            xuLy.botXoaNuocAo(move);
            if (evaluation >= beta){
                return beta;
            }
            alpha = Math.max(alpha, evaluation);
        }
        return alpha;
    }
    
    private ArrayList<Move> timCacNuocDiHopLe() {
        ArrayList<Move> movesList = new ArrayList<>();
        
        QuanCo vua = xuLy.timVua(xuLy.getTurn());
        if (vua != null){
            for (QuanCo quan : xuLy.q1){
                if (xuLy.quanCungMau(quan, vua)){
                    for (int r = 0; r < BanCo.MAX_ROW; r++){
                        for(int c = 0;c < BanCo.MAX_COL;c++){
                            //QuanCo quanXY = xuLy.getQuan(c, r);
                            Move move = new Move(xuLy, quan, c, r);
                            if (xuLy.isNuocDiHopLe(move)){
                                movesList.add(move);
                            }
                        }
                    }
                }
            }
        }
        return movesList;
    }
    
    public int evaluate(){
        int whiteVal = countMaterial(true);
        int blackVal = countMaterial(false);
        
        int eval = whiteVal - blackVal;
        int perspective = xuLy.getTurn() ? 1 : -1;
        return perspective * eval;
    } 

    private int countMaterial(boolean isWhite) {
        return xuLy.countPoint(isWhite);
    }
}
