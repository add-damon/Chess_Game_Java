/**
 * 
 */
package p1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ChessPiece class is an abstract class that contains six PieceTypes that
 * represent respectively ROOK, KNIGHT, BISHOP, QUEEN, KING, and PAWN. Each of
 * the PieceTypes has its own Unicode so that it can be drawn on a graphical
 * user interface. The subclasses will inherit the protected class variables,
 * such as color, current coordinates, owner, and have the access to the methods
 * in this ChessPiece.
 * 
 * @author damonren
 * @version 1.0
 */
public abstract class ChessPiece implements Serializable {
    
    /**
     * Auto generated.
     */
    private static final long serialVersionUID = 220237323608845011L;

    /**
     * Six roles in a chess game.
     */
    protected enum PieceType {
        KING("\u2654"),
        QUEEN("\u2655"),
        ROOK("\u2656"),
        BISHOP("\u2657"),
        KNIGHT("\u2658"),
        PAWN("\u2659");
        
        /** Unicode */
        private String code;
        
        /** Assigns the unicode. */
        private PieceType(String s) {
            code = s;
        }
        
        /**
         * Returns the unicode of this PieceType.
         * 
         * @return An String.
         */
        private String getCode() {
            return code;
        }
        
    }
    
    /** The x-coordinate of the next position. */
    protected int nextX;
    
    /** The y-coordinate of the next position. */
    protected int nextY;
    
    /** The x-coordinate of the current position. */
    protected int curX;
    
    /** The y-coordinate of the current position. */
    protected int curY;
    
    /** The color of this ChessPiece. */
    protected String color;
    
    /** The Unicode value of this ChessPiece. */
    protected String unicode;
    
    /** The player to whom this ChessPiece belongs to. */
    protected Player owner;
    
    /** 
     * A 2-D List that contains coordinations of the valid moves of 
     * this ChessPiece.
     * */
    protected List<List<Integer>> validMoves;
    
    /**
     * Initializes the class variables of this ChessPiece.
     * 
     * @param ownerID The ID of the player to whom this ChessPiece belongs to.
     * @param p       An PieceType, can be one of the sixteen roles.
     * @param c       An String represents the color of this ChessPiece.
     */
    public ChessPiece(Player player, PieceType pt, String c) {
        unicode = pt.getCode();
        color = c.toUpperCase();
        owner = player;
        validMoves = new ArrayList<List<Integer>>();
    }
    
    /**
     * Sets the row index, or x-coordinate of this ChessPiece to the specified
     * value.
     * 
     * @param x An integer.
     */
    public void setCurX(int x) {
        curX = x;
    }
    
    /**
     * Sets the column index, or y-coordinate of this ChessPiece to the
     * specified value.
     * 
     * @param y An integer.
     */
    public void setCurY(int y) {
        curY = y;
    }
    
    /**
     * Returns the color of this ChessPiece.
     * @return A String.
     */
    public String getColor() {
        return color;
    }
    
    /**
     * Returns the player to whom this ChessPiece belongs.
     * @return A Player.
     */
    public Player getPlayer() {
        return owner;
    }
    
    /**
     * Returns true if another ChessPiece has the same owner as this ChessPiece,
     * false otherwise.
     * 
     * @param cp A ChessPiece.
     * @return   A boolean.
     */
    public boolean isSamePlayer(ChessPiece cp) {
        return owner == cp.owner;
    }
    
    /**
     * Generates a List that contains the two passed-in integer values, like a
     * coordination.
     * 
     * @param x The x-coordinate of a move.
     * @param y The y-coordinate of a move.
     * @return  A List.
     */
    public List<Integer> listGenerator(int x, int y) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(x);
        list.add(y);
        return list;
    }
    
    /**
     * Returns true if x- and y- coordinates of the next move is in the
     * validMoves List, false otherwise.
     * 
     * @param toX The x-coordinate of the next position.
     * @param toY The y-coordinate of the next position.
     * @return    A boolean.
     */
    public boolean isValidMove(int toX, int toY) {
        return validMoves.contains(listGenerator(toX, toY));
    }
    
    /**
     * Sets the firstMove to false after this Pawn made a move and returns true
     * if the current row and column indexes are successfully updated. 
     *  
     * @param toX The row index, x-coordinate, to be updated to.
     * @param toY The column index, y-coordinate, to be updated to.
     * @return    A boolean.
     */
    public boolean updateCurXY(int toX, int toY) {
        if (isValidMove(toX, toY)) {
            curX = toX;
            curY = toY;
            return true;
        } else {
            System.out.println("ChessPiece.java line 162: update failed.");
            return false;
        }
    }
    
    /**
     * <p>
     * Return a 2-D List that contains x- and y- coordinates of valid moves
     * according to the rules of the PieceType of this ChessPiece.
     * PieceType.
     * </p>
     * <p>
     * The validation of the next position will be done in this method, such
     * that if the next position complies to the rule of the movement of this
     * ChessPiece and:
     * <ul>
     * <li>is null, the x- and y- coordinates will be added to the returned
     * 2-D list.</li>
     * <li>is not null and the ChessPiece in the position belongs to the 
     * opponent, the x- and y- coordinates will be added to the returned
     * 2-D list.</li>
     * <li>is not null but the ChessPiece in the position belongs to the player
     * self, the x- and y- coordinates will not be added to the returned
     * 2-D list.</li>
     * 
     * @param cp A 2-D ChessPiece array.
     * @return   A 2-D List.
     */
    abstract List<List<Integer>> getValidMoves(final ChessPiece[][] cp);
    
    /**
     * Returns the Unicode representation of this ChessPiece.
     * @return A String.
     */
    public String toString() {
        return unicode; 
    }

}

