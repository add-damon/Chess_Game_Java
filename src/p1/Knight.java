/**
 * 
 */
package p1;

import java.util.List;

/**
 * Knight class extends ChessPiece.
 * @author damonren
 * @version 1.0
 */
public class Knight extends ChessPiece {

    /**
     * Auto generated.
     */
    private static final long serialVersionUID = 4558992806967268634L;

    /**
     * Instantiates a new ChessPiece with PieceType KNIGHT.
     * @param player An integer.
     * @param color   The color of this ChessPiece.
     */
    public Knight(Player player, String color) {
        super(player, PieceType.KNIGHT, color);
    }
    
    /**
     * Knight moves in an “L” pattern of 2-1 or 1-2 in any direction, the path
     * does not need to be clear.
     * 
     * @see p1.ChessPiece#getValidMoves()
     */
    @Override
    List<List<Integer>> getValidMoves(final ChessPiece[][] cp) {
        validMoves.clear();
        //up left
        nextX = curX - 2;
        nextY = curY - 1;
        if ((nextX < Board.X_SIZE && nextX >= 0)
            && (nextY < Board.Y_SIZE && nextY >= 0)
            && ((cp[nextX][nextY] == null) 
            || (cp[nextX][nextY] != null && !isSamePlayer(cp[nextX][nextY])))) {
            validMoves.add(listGenerator(nextX, nextY));
        }
        nextX = curX - 1;
        nextY = curY - 2;
        if ((nextX < Board.X_SIZE && nextX >= 0)
            && (nextY < Board.Y_SIZE && nextY >= 0)
            && ((cp[nextX][nextY] == null) 
            || (cp[nextX][nextY] != null && !isSamePlayer(cp[nextX][nextY])))) {
                validMoves.add(listGenerator(nextX, nextY));
            }
        
        //up right
        nextX = curX - 2;
        nextY = curY + 1;
        if ((nextX < Board.X_SIZE && nextX >= 0)
            && (nextY < Board.Y_SIZE && nextY >= 0)
            && ((cp[nextX][nextY] == null) 
            || (cp[nextX][nextY] != null && !isSamePlayer(cp[nextX][nextY])))) {
            validMoves.add(listGenerator(nextX, nextY));
        }
        nextX = curX - 1;
        nextY = curY + 2;
        if ((nextX < Board.X_SIZE && nextX >= 0)
            && (nextY < Board.Y_SIZE && nextY >= 0)
            && ((cp[nextX][nextY] == null) 
            || (cp[nextX][nextY] != null && !isSamePlayer(cp[nextX][nextY])))) {
                validMoves.add(listGenerator(nextX, nextY));
            }
        
        //down left
        nextX = curX + 2;
        nextY = curY - 1;
        if ((nextX < Board.X_SIZE && nextX >= 0)
            && (nextY < Board.Y_SIZE && nextY >= 0)
            && ((cp[nextX][nextY] == null) 
            || (cp[nextX][nextY] != null && !isSamePlayer(cp[nextX][nextY])))) {
            validMoves.add(listGenerator(nextX, nextY));
        }
        nextX = curX + 1;
        nextY = curY - 2;
        if ((nextX < Board.X_SIZE && nextX >= 0)
            && (nextY < Board.Y_SIZE && nextY >= 0)
            && ((cp[nextX][nextY] == null) 
            || (cp[nextX][nextY] != null && !isSamePlayer(cp[nextX][nextY])))) {
                validMoves.add(listGenerator(nextX, nextY));
            }
        
        //down right
        nextX = curX + 2;
        nextY = curY + 1;
        if ((nextX < Board.X_SIZE && nextX >= 0)
            && (nextY < Board.Y_SIZE && nextY >= 0)
            && ((cp[nextX][nextY] == null) 
            || (cp[nextX][nextY] != null && !isSamePlayer(cp[nextX][nextY])))) {
            validMoves.add(listGenerator(nextX, nextY));
        }
        nextX = curX + 1;
        nextY = curY + 2;
        if ((nextX < Board.X_SIZE && nextX >= 0)
            && (nextY < Board.Y_SIZE && nextY >= 0)
            && ((cp[nextX][nextY] == null) 
            || (cp[nextX][nextY] != null && !isSamePlayer(cp[nextX][nextY])))) {
                validMoves.add(listGenerator(nextX, nextY));
            }
        
        return validMoves;
    }

}
