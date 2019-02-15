/**
 * 
 */
package p1;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Board class mainly contains an two-D array of all the ChessPieces in a
 * typical chess game. The index of each pieces corresponds to the x- and y-
 * coordinates on a chess board. The filled two-D array will look like the array
 * below:
 * </p>
 * <p>
 * {
 * { Rook, Knight, Bishop, Queen, King, Bishop, Knight, Rook },
 * { Pawn, Pawn  , Pawn  , Pawn , Pawn, Pawn  , Pawn  , Pawn },
 * { null, null  , null  , null , null, null  , null  , null },
 * { null, null  , null  , null , null, null  , null  , null },
 * { null, null  , null  , null , null, null  , null  , null },
 * { null, null  , null  , null , null, null  , null  , null },
 * { Pawn, Pawn  , Pawn  , Pawn , Pawn, Pawn  , Pawn  , Pawn },
 * { Rook, Knight, Bishop, Queen, King, Bishop, Knight, Rook }
 * }
 * </p>
 * @author damonren
 * @version 1.0
 */
public class Board implements Serializable{

    /**
     * Auto generated.
     */
    private static final long serialVersionUID = -41297641917908715L;

    /** Maximum number of horizontal squares of this chess board. */
    public static final int X_SIZE = 8;
    
    /** Maximum number of vertical squares of this chess board. */
    public static final int Y_SIZE = 8;
    
    /** 
     * An 2-D array contains all the chess pieces in the chess game, including
     * the empty squares in the middle of this board which is represented as
     * null.
     */
    private ChessPiece[][] pieces;
    
    /** The GUI interface. */
    private GUI gui;
    
    /** The player whose chess pieces are laid on the top of this board. */
    private Player player1;
    
    /** The player whose chess pieces are laid on the bottom of this board. */
    private Player player2;
    
    /** The player who moves next. */
    private Player curPlayer;
    
//    /** Each player moves once in turn. */
//    private boolean turn = false;
//    
    /**
     * Initializes all the class variables and calls layOutPieces() to fill the
     * 2-D ChessPiece array.
     * 
     * @param width  The length of the 2-D array.
     * @param length The length of each element inside the 2-D array.
     * @param p1     The player on the top of this board.
     * @param p2     The player on the bottom of this board.
     * @param g      The GUI interface.
     */
    public Board(Player p1, Player p2, GUI g) {
        gui = g;
        player1 = p1;
        player2 = p2;
        curPlayer = player1;
        pieces = new ChessPiece[X_SIZE][Y_SIZE];
        layOutPieces();
        
    }
    
    public void layOutPieces() {
        int i = 0;
        for (int x = 0; x < X_SIZE; x++) {
            for (int y = 0; y < Y_SIZE; y++) {
               //places the first player's chess piece on the top of this chess
               //board.
               if (i < player1.remainPs() ) {
                   pieces[x][y] = player1.getPieceAt(i);
                   pieces[x][y].setCurX(x);
                   pieces[x][y].setCurY(y);
               }
               //places the second player's chess pieces on the bottom of this
               //board in reverse order.
               if (i >= (X_SIZE * Y_SIZE) - player2.remainPs()) {
                   pieces[x][y] = player2.getPieceAt(X_SIZE * Y_SIZE - i - 1);
                   pieces[x][y].setCurX(x);
                   pieces[x][y].setCurY(y);
               }
               i++;
            }
        }
    }
    
    /**
     * <p>
     * Moves one piece to another position on this board. If another position
     * has no piece, simply moves the previous piece over and set the value of
     * the previous piece to null. If another position has a piece that belongs
     * to another player, removes the piece from the player to whom the piece
     * belongs first. 
     * </p>
     * 
     * @param toX   The row index, or x-coordinate, of the next position.
     * @param toY   The column index, or y-coordinate, of the next position.
     * @param fromX The row index, or x-coordinate, of the previous position.
     * @param fromY The column index, or y-coordinate, of the previous position.
     */
    public void movePieceTo(int toX, int toY, int fromX, int fromY) {
        if (pieces[toX][toY] != null
                && pieces[fromX][fromY].isSamePlayer(pieces[toX][toY])) {
            pieces[toX][toY].getPlayer().removePiece(pieces[toX][toY]);
        }
        pieces[toX][toY] = pieces[fromX][fromY];
        pieces[fromX][fromY] = null;
        pieces[toX][toY].updateCurXY(toX, toY);
    }

    /**
     * Returns a ChessPiece at the specified position.
     * 
     * @param x The row index of the chess piece to be returned.
     * @param y The column index of the chess piece to be returned.
     * @return  The chess piece at the specified position.
     */
    public ChessPiece getPieceAt(int x, int y) {
        return pieces[x][y];
    }
    
    public Player getCurPlayer() {
        return curPlayer;
    }
    
    /**
     * Returns the player who moves next.
     * 
     * @return An integer.
     */
    public Player getTurn() {
         curPlayer = (curPlayer == player1) ? player2 : player1;
         return curPlayer;
    }
    
    /**
     * Return true if the specified position has a ChessPiece in it, false
     * otherwise.
     * 
     * @param x The row index of the position.
     * @param y The column index of the position.
     * @return  An boolean value.
     */
    public boolean isEmpty(int x, int y) {
        return pieces[x][y] == null;
    }
    
    /**
     * Returns true if either player's King has been captured/removed from the
     * players' hand, false otherwise.
     * 
     * @return An boolean value.
     */
    public Player whoWon() {
        if (player1.hasLost()) {
            return player2;
        }
        if (player2.hasLost()) {
            return player1;
        }
        return null;
    }
    
    /**
     * 
     * @param x 
     * @param y
     * @return A Player Object.
     */
    public Player getPlayerAt(int x, int y) {
        return pieces[x][y] == null ? null : pieces[x][y].getPlayer();
    }
    
    /**
     * Reset this board, including the pieces on each player's hand, current
     * player to player1 (at the bottom of this Board), a new 2-D ChessPiece
     * array.
     */
    public void resetBoard() {
        player1.initPieces();
        player2.initPieces();
        curPlayer = player1;
        pieces = new ChessPiece[X_SIZE][Y_SIZE];
        layOutPieces();
    }
    
    /**
     * Return a 2-D List storing all the possible moves of the chosen piece.
     * 
     * @param validPieceX The x-coordinate of the chosen valid piece.
     * @param validPieceY The y-coordinate of the chosen valid piece.
     * @return A 2-D List.
     */
    public List<List<Integer>> getValidMoves(int validPieceX, int validPieceY) {
        return pieces[validPieceX][validPieceY] == null
            ? null : pieces[validPieceX][validPieceY].getValidMoves(pieces);
    }
    
    /**
     * Return a String telling players whose turn it is.
     * @return A String.
     */
    public String getTurnMessage() {
        return new String(curPlayer.getPieceColor() + "'s  Turn!");
    }
    

}
