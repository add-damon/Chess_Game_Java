/**
 * 
 */
package p1;

import java.util.List;

/**
 * Pawn class extends ChessPiece.
 * @author damonren
 * @version 1.0
 */
public class Pawn extends ChessPiece {
    
    /**
     * Auto generated.
     */
    private static final long serialVersionUID = 155191990418111655L;
    
    /**
     * True when this Pawn has not made any move.
     */
    private boolean firstMove = true;
    
    /**
     * Instantiates a new ChessPiece with PieceType PAWN.
     * @param ownerID An integer.
     * @param color   The color of this ChessPiece.
     */
    public Pawn(Player player, String color) {
        super(player, PieceType.PAWN, color);
    }
    
    /**
     * Pawn can move forward 1 or 2 on that piece’s FIRST move, 1 forward
     * afterwards.
     * 
     * @see p1.ChessPiece#getValidMoves()
     */
    @Override
    List<List<Integer>> getValidMoves(final ChessPiece[][] cp) {
        validMoves.clear();
        // player on the bottom of the board.
        if (owner.getPlayerID() % 2 == 0) {
            //up left
            nextX = curX - 1;
            nextY = curY - 1;
            if ((nextX < Board.X_SIZE && nextX >= 0)
                    && (nextY < Board.Y_SIZE && nextY >= 0)
                    && cp[nextX][nextY] != null
                    && !isSamePlayer(cp[nextX][nextY])) {
                validMoves.add(listGenerator(nextX, nextY));
            }
            //up
            nextX = curX - 1;
            nextY = curY;
            if (firstMove) {
                int i = 0;
                while ((nextX < Board.X_SIZE && nextX >= 0)
                        && (nextY < Board.Y_SIZE && nextY >= 0)
                        && (cp[nextX][nextY] == null)
                        && i++ < 2) {
                    validMoves.add(listGenerator(nextX--, nextY));
                }
            } else {
                if ((nextX < Board.X_SIZE && nextX >= 0)
                        && (nextY < Board.Y_SIZE && nextY >= 0)
                        && (cp[nextX][nextY] == null)) {
                    validMoves.add(listGenerator(nextX, nextY));
                }
            }


            //up right
            nextX = curX - 1;
            nextY = curY + 1;
            if ((nextX < Board.X_SIZE && nextX >= 0)
                    && (nextY < Board.Y_SIZE && nextY >= 0)
                    && cp[nextX][nextY] != null
                    && !isSamePlayer(cp[nextX][nextY])) {
                validMoves.add(listGenerator(nextX, nextY));
            }
        } else { //player on the top of the board.
            //down left
            nextX = curX + 1;
            nextY = curY - 1;
            if ((nextX < Board.X_SIZE && nextX >= 0)
                    && (nextY < Board.Y_SIZE && nextY >= 0)
                    && cp[nextX][nextY] != null
                    && !isSamePlayer(cp[nextX][nextY])) {
                validMoves.add(listGenerator(nextX, nextY));
            }
            //down
            nextX = curX + 1;
            nextY = curY;
            if (firstMove) {
                int i = 0;
                while ((nextX < Board.X_SIZE && nextX >= 0)
                        && (nextY < Board.Y_SIZE && nextY >= 0)
                        && (cp[nextX][nextY] == null)
                        && i++ < 2) {
                    validMoves.add(listGenerator(nextX++, nextY));
                }
            } else {
                if ((nextX < Board.X_SIZE && nextX >= 0)
                        && (nextY < Board.Y_SIZE && nextY >= 0)
                        && (cp[nextX][nextY] == null)) {
                    validMoves.add(listGenerator(nextX, nextY));
                }
            }

            //down right
            nextX = curX + 1;
            nextY = curY + 1;
            if ((nextX < Board.X_SIZE && nextX >= 0)
                    && (nextY < Board.Y_SIZE && nextY >= 0)
                    && cp[nextX][nextY] != null
                    && !isSamePlayer(cp[nextX][nextY])) {
                validMoves.add(listGenerator(nextX, nextY));
            }
        }
        return validMoves;
    }
    
    /**
     * Sets the firstMove to false after this Pawn made a move and returns true
     * if the current row and column indexes are successfully updated. 
     *  
     * @param toX The row index to be updated to.
     * @param toY The column index to be updated to.
     * @return    A boolean.
     */
    @Override
    public boolean updateCurXY(int toX, int toY) {
        firstMove = false;
        return super.updateCurXY(toX, toY);
    }

}
