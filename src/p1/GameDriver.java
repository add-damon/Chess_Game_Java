/**
 * 
 */
package p1;

/**
 * GameDriver class has the properties for the chess game. Users can set
 * squares' size and color, and choose chess pieces' colors for each player.
 * 
 * @author damon ren
 * @version 1.0
 */
public class GameDriver {

    /**
     * Drives the game
     * @param args unused.
     */
    public static void main(String[] args) {
        /** Color of the tiles. */
        final String boardColor = "grey";
        
        /** Each square size. */
        final int squarePixels = 100;
        
        /** Each chess piece size. */
        final int fontSize = (int) (squarePixels / 1.5);
        
        GUI ui = new GUI();
        Player p1 = new Player("red");
        Player p2 = new Player("blue");
        
        Board gameBoard = new Board(p1, p2, ui);
        ui.run(boardColor, fontSize, squarePixels, gameBoard);
        
        
        

    }
}
