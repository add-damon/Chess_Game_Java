/**
 * 
 */
package p1;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Player class contains an array list filled with sixteen ChessPieces
 * initially: Rook X 2, Knight X 2, Bishop X 2, Queen X 1, King X 1, Pawn X 6.
 * It also has methods that can remove and get elements at a specified index,
 * and can check whether the King ChessPiece has been captured.
 * 
 * @author damonren
 * @version 1.0
 */
public class Player implements Serializable {
    
    /**
     * Auto generated.
     */
    private static final long serialVersionUID = -1322436847688491980L;

    /** The color of each ChessPiece of this Player. */
    private String playerColor;
    
    /** An array list that contains all the sixteen ChessPieces. */
    private ArrayList<ChessPiece> playerPieces = new ArrayList<ChessPiece>();
    
    /** Incremented by 1 each time a new player is instantiated. */
    private static int numOfPlayers = 0;
    
    /** The ID of this Player. */
    private final int playerID;
    
    /** Reference to the King ChessPiece. */
    private final ChessPiece refToKing;
    
    /**
     * Initializes the class variables and generates a playerID. Then It calls
     * initPieces() to adds the sixteen ChessPieces to this Player.
     * 
     * @param color The color of each pieces.
     */
    public Player(String color) {
        playerID = ++numOfPlayers;
        playerColor = color;
        refToKing = new King(this, playerColor);
        initPieces();
    }
    
    /**
     * Adds all the sixteen ChessPieces to this player. If the ID of this player
     * is even number, adds the Queen first and the King. Otherwise, adds the
     * King first and the Queen.
     */
    public void initPieces() {
        playerPieces.clear();
        playerPieces.add(new Rook(this, playerColor));
        playerPieces.add(new Knight(this, playerColor));
        playerPieces.add(new Bishop(this, playerColor));
        if (playerID % 2 != 0) {
            playerPieces.add(new Queen(this, playerColor));
            playerPieces.add(refToKing);
        } else {
            playerPieces.add(refToKing);
            playerPieces.add(new Queen(this, playerColor));
        }
        playerPieces.add(new Bishop(this, playerColor));
        playerPieces.add(new Knight(this, playerColor));
        playerPieces.add(new Rook(this, playerColor));
        for (int i = 0; i < 8; i++) {
            playerPieces.add(new Pawn(this, playerColor));
        }
    }
    
    /**
     * Returns the ChessPiece at the specified index.
     * 
     * @param i The index where the ChessPiece to be returned.
     * @return  A ChessPiece.
     */
    public ChessPiece getPieceAt(int i) {
        return playerPieces.get(i);
    }
    
    /**
     * Returns true if successfully removed a ChessPiece from this player, false
     * otherwise.
     * 
     * @param cp The ChessPiece to be removed.
     * @return   An boolean value.
     */
    public ChessPiece removePiece(ChessPiece cp) {
        System.out.println(cp + "  removed from " + playerColor);
        return playerPieces.remove(cp) ? cp : null;
    }
    
    /**
     * Returns this player's ID.
     * @return An integer.
     */
    public int getPlayerID() {
        return playerID;
    }
    
    /**
     * Returns this player's color.
     * 
     * @return A String.
     */
    public String getPieceColor() {
        return playerColor.toUpperCase();
    }
    /**
     * Returns true if this player has the King ChessPiece removed, which means
     * this player lose the game, false otherwise.
     * 
     * @return An boolean value.
     */
    public boolean hasLost() {
        return !playerPieces.contains(refToKing);
    }
    
    /**
     * Returns the number of remaining ChessPiece that this player has.
     * 
     * @return An integer.
     */
    public int remainPs() {
        return playerPieces.size();
    }
    
}
