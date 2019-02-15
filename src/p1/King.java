/**
 * 
 */
package p1;

import java.util.List;

/**
 * King class extends ChessPiece.
 * @author damonren
 * @version 1.0
 */
public class King extends ChessPiece {

    /**
     * Auto generated.
     */
    private static final long serialVersionUID = 8063007536110882283L;

    /**
     * Instantiates a new ChessPiece with PieceType KING.
     * @param ownerID An integer.
     * @param color   The color of this ChessPiece.
     */
    public King(Player player, String color) {
        super(player, PieceType.KING, color);
    }
    
    /**
     * King can move in any direction 1 space.
     * 
     * @see p1.ChessPiece#move()
     */
    @Override
    List<List<Integer>> getValidMoves(final ChessPiece[][] cp) {
        validMoves.clear();
        //up left
        nextX = curX - 1;
        nextY = curY - 1;
        if ((nextX < Board.X_SIZE && nextX >= 0)
            && (nextY < Board.Y_SIZE && nextY >= 0)
            && ((cp[nextX][nextY] == null) 
            || (cp[nextX][nextY] != null && !isSamePlayer(cp[nextX][nextY])))) {
            validMoves.add(listGenerator(nextX, nextY));
        }
        //left
        nextX = curX;
        nextY = curY - 1;
        if ((nextX < Board.X_SIZE && nextX >= 0)
            && (nextY < Board.Y_SIZE && nextY >= 0)
            && ((cp[nextX][nextY] == null) 
            || (cp[nextX][nextY] != null && !isSamePlayer(cp[nextX][nextY])))) {
                validMoves.add(listGenerator(nextX, nextY));
            }
        
        //down left
        nextX = curX + 1;
        nextY = curY - 1;
        if ((nextX < Board.X_SIZE && nextX >= 0)
            && (nextY < Board.Y_SIZE && nextY >= 0)
            && ((cp[nextX][nextY] == null) 
            || (cp[nextX][nextY] != null && !isSamePlayer(cp[nextX][nextY])))) {
            validMoves.add(listGenerator(nextX, nextY));
        }
        //down
        nextX = curX + 1;
        nextY = curY;
        if ((nextX < Board.X_SIZE && nextX >= 0)
            && (nextY < Board.Y_SIZE && nextY >= 0)
            && ((cp[nextX][nextY] == null) 
            || (cp[nextX][nextY] != null && !isSamePlayer(cp[nextX][nextY])))) {
                validMoves.add(listGenerator(nextX, nextY));
            }
        
        //down right
        nextX = curX + 1;
        nextY = curY + 1;
        if ((nextX < Board.X_SIZE && nextX >= 0)
            && (nextY < Board.Y_SIZE && nextY >= 0)
            && ((cp[nextX][nextY] == null) 
            || (cp[nextX][nextY] != null && !isSamePlayer(cp[nextX][nextY])))) {
            validMoves.add(listGenerator(nextX, nextY));
        }
        //right
        nextX = curX;
        nextY = curY + 1;
        if ((nextX < Board.X_SIZE && nextX >= 0)
            && (nextY < Board.Y_SIZE && nextY >= 0)
            && ((cp[nextX][nextY] == null) 
            || (cp[nextX][nextY] != null && !isSamePlayer(cp[nextX][nextY])))) {
                validMoves.add(listGenerator(nextX, nextY));
            }
        
        //up right
        nextX = curX - 1;
        nextY = curY + 1;
        if ((nextX < Board.X_SIZE && nextX >= 0)
            && (nextY < Board.Y_SIZE && nextY >= 0)
            && ((cp[nextX][nextY] == null) 
            || (cp[nextX][nextY] != null && !isSamePlayer(cp[nextX][nextY])))) {
            validMoves.add(listGenerator(nextX, nextY));
        }
        //up
        nextX = curX - 1;
        nextY = curY;
        if ((nextX < Board.X_SIZE && nextX >= 0)
            && (nextY < Board.Y_SIZE && nextY >= 0)
            && ((cp[nextX][nextY] == null) 
            || (cp[nextX][nextY] != null && !isSamePlayer(cp[nextX][nextY])))) {
                validMoves.add(listGenerator(nextX, nextY));
            }
        
        return validMoves;
    }
    

}
