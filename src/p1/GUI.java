/**
 * 
 */
package p1;

import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * GUI class contains a 8X8 GridPane. Each of the cells has a Text node on top
 * of a Rectangle node. The String values of the Text nodes will be synced with
 * the String values of each of the ChessPieces in the 2-D ChessPiece array in
 * the Board class. The String value could either be either empty, or the 
 * Unicode of the corresponding piece. When players move their Chess Pieces,
 * the corresponding pieces in the 2-D ChessPiece array is moved, or set to
 * null.
 * 
 * @author damonren
 * @version 1.0
 */
public class GUI extends Application implements Serializable{

    /**
     * Auto generated.
     */
    private static final long serialVersionUID = -4986250437897341868L;

    /** Red inner shadow effect when a valid rectangle is selected. */
    private transient final InnerShadow selectedShadow
        = new InnerShadow(20d, Color.RED);
    
    /** Green inner shadow effect shows valid moves. */
    private transient final InnerShadow validShadow
        = new InnerShadow(50d, Color.GREEN);
    
    /** Clear inner shadow effect to clear other shadow effects. */
    private transient final InnerShadow clearShadow
        = new InnerShadow(0d, Color.TRANSPARENT);
    
    /** A board instance. */
    private static Board board;
    
    /** The Size of all the Rectangles. */
    private static int squareSize;
    
    /** 
     * A GridPane instance to contain all the Rectangle nodes and the Text
     * nodes.
     * */
    private static GridPane grid;
    
    /** A 2-D Rectangle array contains all the 64 rectangles. */
    private static Rectangle[][] square;
    
    /**
     * A 2-D Text array contains all the Text nodes that will be laid on top
     * of the Rectangle nodes.
     */
    private static Text[][] text;
    
    /** 
     * True if a valid Rectangle is clicked, a valid Rectangle means the
     * String value of Text node on the Rectangle is not empty and the 
     * ChessPiece at the coordinates of the Rectangle is one of the current
     * player's piece. */
    private Boolean validFirstClick = false;
    
    /** X-coordinate of the last valid click. */
    private static int preX;
    
    /** Y-coordinate of the last valid click. */
    private static int preY;
    
    /** Font size of each ChessPiece. */
    private static Font font;
    
    /** Color of the squares that are not white. */
    private static String boardColor;
    
    /** A toolBar containing the menus. */
    private ToolBar toolBar;
    
    /** A message informing the players whose turn it is. */
    private Text message;
    
    /** A 2-D List containing valid moves of a ChessPiece. */
    private static List<List<Integer>> validList;
    
    /**
     * <p>Presents a GUI like a typical Chess Board and all the Chess Pieces.
     * </p>
     * 
     * @param primaryStage a Stage
     */
    public void start(Stage primaryStage){
        Button save = new Button("Save");
        Button load = new Button("Load");
        Button restart = new Button("\u21BB");
        restart.setTooltip(new Tooltip("Restart Game"));

        message = new Text("Game Starts, Red Moves First");
        toolBar = new ToolBar(save, load, restart, new Separator(), message);
        
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(toolBar);
        borderPane.setCenter(grid);
        
        //refer to 
        //https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/file-chooser.htm
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("SER", "*.ser"));
        
        //Open a save dialog, and serialize the board object.
        save.setOnAction(e -> {
            try {
                File savedFile = fileChooser.showSaveDialog(primaryStage);
                if (savedFile != null) {
                    ObjectOutputStream boardOut = new ObjectOutputStream(
                            new FileOutputStream(savedFile));
                    boardOut.writeObject(board);
                    boardOut.flush();
                    boardOut.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        });
        
        //Open a load dialog, and read the serialized board object.
        load.setOnAction( e -> {
            try {
                 File file = fileChooser.showOpenDialog(primaryStage);
                 if (file != null) {
                    ObjectInputStream boardOut = new ObjectInputStream(
                            new FileInputStream(file));
                    Board b2 = (Board) boardOut.readObject();
                    boardOut.close();
                    board = b2;
                    grid.getChildren().clear();
                    renderBoard();
                    validFirstClick = false;
                    message.setText(board.getTurnMessage());
                }
            } catch (ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        });
        
        //call the resetBoard() in the Board class to reset the board.
        restart.setOnAction(e -> {
             board.resetBoard();
             grid.getChildren().clear();
             renderBoard();
             message.setText("Game Starts, Red Moves First");
        });
        
        Scene scene = new Scene(borderPane);
        
        scene.setOnMouseClicked(this::processMouseClick);
        
        primaryStage.setTitle("Chess Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * Retrieves the coordinates of the clicks made by users and converts them
     * to row and column indexes. It then uses the coordinates to call the
     * corresponding method to manipulate the elements in the 2-D ChessPiece
     * array in the board.
     * 
     * @param e A click mouseEvent
     */
    public void processMouseClick(MouseEvent e) {
        int curX 
        = (int) Math.floor((e.getY() - toolBar.getHeight()) / squareSize);
        int curY = (int) Math.floor(e.getX() / squareSize);
        //if the click is within the length of the board, and
        //if the square clicked has a ChessPiece in it, or the validFisrtClick
        //is true.
        if (((curX < Board.X_SIZE) && (curY < Board.Y_SIZE)) &&
                (!text[curX][curY].getText().equals("") || validFirstClick)) {
            //if the square clicked has a ChessPiece in it, and the
            //validFisrtClick is false, and
            //if it is the current player, these are the conditions for a valid
            //first click.
            if ((!text[curX][curY].getText().equals("") && !validFirstClick)
                    &&
                    (board.getCurPlayer() == board.getPlayerAt(curX, curY))) {
                
                preX = curX;
                preY = curY;
                validList = board.getValidMoves(curX, curY);
                applyHilight(curX, curY);
                showValidEffect(validList);
                validFirstClick = true;
                
            } else if (validFirstClick) {
                
                ChessPiece curPiece = board.getPieceAt(curX, curY);
                ChessPiece prePiece = board.getPieceAt(preX, preY);
                //if the square clicked after the valid click has the green
                //shadow effect, it is a valid move.
                if (prePiece.isValidMove(curX, curY)) {
                    board.movePieceTo(curX, curY, preX, preY);
                    cutTextTo(curX, curY, preX, preY);
                    removeHilight(preX, preY);
                    removeValidEffect(validList);
                    board.getTurn();
                    message.setText(board.getTurnMessage());
                } else if (curPiece == null 
                        || !prePiece.isSamePlayer(curPiece)) {
                    //if the square clicked after the valid click is null or
                    //does not belong to the same player, do nothing.
                    return;
                } else if(prePiece.isSamePlayer(curPiece)) {
                    //if the square clicked after the valid click belongs to the
                    //same player, remove the shadow effects.
                    removeHilight(preX, preY);
                    removeValidEffect(validList);
                }
                validFirstClick = false;
                if ( board.whoWon() != null) {
                    message.setText(board.whoWon().getPieceColor() + " WON!");
                };

            }
        }

    }
    
    /**
     * Applies inner shadow effect to the rectangle at the specific location of
     * the 2-D Rectangle array.
     * 
     * @param x The row index of the rectangle.
     * @param y The column index of the rectangle.
     */
    public void applyHilight(int x, int y) {
        square[x][y].setEffect(selectedShadow);
    }
    
    /**
     * Removes inner shadow effect to the rectangle at the specific location of
     * the 2-D Rectangle array.
     * 
     * @param x The row index of the rectangle.
     * @param y The column index of the rectangle.
     */
    public void removeHilight(int x, int y) {
        square[x][y].setEffect(clearShadow);
    }
    
    /**
     * Applies green inner shadow effect to all the valid rectangles at the
     * coordinates contained in the argument 2-D Integer List.
     * 
     * @param validXY An 2-D Integer List contains x- and y- coordinates of all
     *                the valid next moves.
     */
    public void showValidEffect(List<List<Integer>> validXY) {
        int x;
        int y;
        if (validXY != null) {
            for (int i = 0; i < validXY.size(); i++) {
                x = validXY.get(i).get(0);
                y = validXY.get(i).get(1);
                square[x][y].setEffect(validShadow);
            }
        }
    }
    
    /**
     * Removes green inner shadow effect to all the valid rectangles at the
     * coordinates contained in the argument 2-D Integer List.
     * 
     * @param validXY An 2-D Integer List contains x- and y- coordinates of all
     *                the valid next moves.
     */
    public void removeValidEffect(List<List<Integer>> validXY) {
        int x;
        int y;
        if (validXY != null) {
            for (int i = 0; i < validXY.size(); i++) {
                x = validXY.get(i).get(0);
                y = validXY.get(i).get(1);
                square[x][y].setEffect(clearShadow);
            }
        }
    }
    
    /**
     * Cuts the String value of the previous Text node at location
     * (fromX, fromY) of the grid pane to the next Text node at location
     * (toX, toY) first and then sets the value of the previous Text node to an
     * empty String.
     * 
     * @param toX   The row index of the Rectangle where to be 
     * @param toY   The column index of the next Rectangle.
     * @param fromX The row index of the previous Rectangle.
     * @param fromY The column index of the previous Rectangle.
     */
    public void cutTextTo(int toX, int toY, int fromX, int fromY) {
        text[toX][toY].setText(text[fromX][fromY].getText());
        text[toX][toY].setFill(text[fromX][fromY].getFill());
        text[fromX][fromY].setText("");
    }
    
    /**
     * Render all the pieces of the 2-D ChessPiece array in the board onto the
     * 8X8 grid pane, including their associated colors, font size and position.
     * If the piece is null, the String value of the corresponding Text node is
     * set to empty. Otherwise, the String value will be set to the String value
     * of the piece, which is the Unicode.
     */
    public void renderBoard() {
        ChessPiece tempCP; 
        for (int x = 0; x < Board.X_SIZE; x++) {
            for(int y = 0; y < Board.Y_SIZE; y++) {
                Rectangle newRec = new Rectangle(x * squareSize, y * squareSize,
                        squareSize, squareSize);
                if ((x + y) % 2 == 0) {
                    newRec.setFill(Color.web(boardColor));
                } else {
                    newRec.setFill(Color.WHITE);
                }
                square[x][y] = newRec;
                grid.add(square[x][y], y, x);
                
                tempCP = board.getPieceAt(x, y);
                if (tempCP != null) {
                    text[x][y] = new Text(tempCP.toString());
                    text[x][y].setFill(Color.web(tempCP.getColor()));
                    grid.add(text[x][y], y, x);
                } else {
                    text[x][y] = new Text("");
                    grid.add(text[x][y], y, x);
                }
                text[x][y].setFont(font);
                GridPane.setValignment(text[x][y], VPos.CENTER);
                GridPane.setHalignment(text[x][y], HPos.CENTER);
            }
        }
        
    }
    
    /**
     * Initializes all the class variables, call the renderBoard() method to lay
     * out all the ChessPieces on the 8X8 grid pane, and launch this GUI.
     * 
     * @param boardColor The color of the game board.
     * @param fontSize   The size of each ChessPieces.
     * @param pixels     The size of each Rectangles.
     * @param b          An instance of the Board class.
     */
    public void run(String bColor, int fontSize, int pixels, Board b) {
        grid = new GridPane();
        board = b;
        boardColor = bColor;
        validList = new ArrayList<List<Integer>>();
        font = new Font(fontSize);
        squareSize = pixels;
        square = new Rectangle[Board.X_SIZE][Board.Y_SIZE];
        text = new Text[Board.X_SIZE][Board.Y_SIZE];
        renderBoard();
        launch();
    }
}
