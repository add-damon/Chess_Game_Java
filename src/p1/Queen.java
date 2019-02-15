/**
 * 
 */
package p1;

import java.util.List;

/**
 * Extends ChessPiece.
 * @author damonren
 * @version 1.0
 */
public class Queen extends ChessPiece {
    
    /**
     * Auto generated.
     */
    private static final long serialVersionUID = 9203140777049276261L;

    /**
     * Instantiates a new ChessPiece with PieceType QUEEN.
     * @param player An integer.
     * @param color   The color of this ChessPiece.
     */
    public Queen(Player player, String color) {
        super(player, PieceType.QUEEN, color);
    }
    
    /**
     * Queen can move in any direction unlimited number of spaces, like Rook
     * plus Bishop, as long as the way is clear of other pieces.
     * 
     * @see p1.ChessPiece#move()
     */
    @Override
    List<List<Integer>> getValidMoves(final ChessPiece[][] cp) {
          validMoves.clear();
          ChessPiece rookCP = new Rook(owner, "transparent");
          rookCP.setCurX(curX);
          rookCP.setCurY(curY);
          List<List<Integer>> tempList1 = rookCP.getValidMoves(cp);
          for (int i = 0; i < tempList1.size(); i++) {
              validMoves.add(tempList1.get(i));
          }
          ChessPiece bishopCP = new Bishop(owner, "transparent");
          bishopCP.setCurX(curX);
          bishopCP.setCurY(curY);
          List<List<Integer>> tempList2 = bishopCP.getValidMoves(cp);
          for (int i = 0; i < tempList2.size(); i++) {
              validMoves.add(tempList2.get(i));
          }
          return validMoves;
    }
    
}
