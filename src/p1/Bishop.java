/**
 * 
 */
package p1;

import java.util.List;

/**
 * Bishop class extends ChessPiece.
 * @author damonren
 * @version 1.0
 */
public class Bishop extends ChessPiece {
    
    /**
     * Auto generated.
     */
    private static final long serialVersionUID = 8268215863407883470L;

    /**
     * Instantiates a new ChessPiece with PieceType BISHOP.
     * @param ownerID An integer.
     * @param color   The color of this ChessPiece.
     */
    public Bishop(Player player, String color) {
        super(player, PieceType.BISHOP, color);
    }
    
    /**
     * Bishop can move on the diagonal unlimited number of spaces as long as the
     * way is clear of other pieces.
     * 
     * @see p1.ChessPiece#move()
     */
    @Override
    List<List<Integer>> getValidMoves(final ChessPiece[][] cp) {
        validMoves.clear();
        //up left
        nextX = curX - 1;
        nextY = curY - 1;
        while ((nextX < Board.X_SIZE && nextX >= 0)
            && (nextY < Board.Y_SIZE && nextY >= 0)
            && cp[nextX][nextY] == null) {
            validMoves.add(listGenerator(nextX--, nextY--));

        }
        if ((nextX < Board.X_SIZE && nextX >= 0)
            && (nextY < Board.Y_SIZE && nextY >= 0) 
            && cp[nextX][nextY] != null 
            && !isSamePlayer(cp[nextX][nextY])) {
            validMoves.add(listGenerator(nextX, nextY));
        }
        
        //down left
        nextX = curX + 1;
        nextY = curY - 1;
        while ((nextX < Board.X_SIZE && nextX >= 0)
            && (nextY < Board.Y_SIZE && nextY >= 0)
            && cp[nextX][nextY] == null) {
            validMoves.add(listGenerator(nextX++, nextY--));

        }
        if ((nextX < Board.X_SIZE && nextX >= 0)
            && (nextY < Board.Y_SIZE && nextY >= 0)
            && cp[nextX][nextY] != null
            && !isSamePlayer(cp[nextX][nextY])) {
            validMoves.add(listGenerator(nextX, nextY));
        }
        
        //down right
        nextX = curX + 1;
        nextY = curY + 1;
        while ((nextX < Board.X_SIZE && nextX >= 0)
            && (nextY < Board.Y_SIZE && nextY >= 0)
            && cp[nextX][nextY] == null) {
            validMoves.add(listGenerator(nextX++, nextY++));

        }
        if ((nextX < Board.X_SIZE && nextX >= 0)
            && (nextY < Board.Y_SIZE && nextY >= 0)
            && cp[nextX][nextY] != null 
            && !isSamePlayer(cp[nextX][nextY])) {
            validMoves.add(listGenerator(nextX, nextY));
        }
        
        //up right
        nextX = curX - 1;
        nextY = curY + 1;
        while ((nextX < Board.X_SIZE && nextX >= 0)
            && (nextY < Board.Y_SIZE && nextY >= 0)
            && cp[nextX][nextY] == null) {
            validMoves.add(listGenerator(nextX--, nextY++));

        }
        if ((nextX < Board.X_SIZE && nextX >= 0)
            && (nextY < Board.Y_SIZE && nextY >= 0)
            && cp[nextX][nextY] != null
            && !isSamePlayer(cp[nextX][nextY])) {
            validMoves.add(listGenerator(nextX, nextY));
        }
        
        return validMoves;
    }

}
