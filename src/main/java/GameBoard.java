import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameBoard implements MouseListener {

    public static Tile[][] board;
    public static int flags = 0;
    public static int mines;
    private static final JFrame frame = new JFrame();
    public static JButton[][] buttons;

    public GameBoard(int rows, int columns, int m) {
        board = new Tile[rows][columns];
        buttons = new JButton[rows][columns];
        mines = m;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(columns*50,rows*50);
        frame.setTitle("Minesweeper");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = new Tile();
                buttons[i][j] = new JButton();
                buttons[i][j].addMouseListener(this);
                frame.add(buttons[i][j]);
            }
        }
        if(columns<30 && rows<30 && mines<rows*columns) {
            frame.setLayout(new GridLayout(rows, columns));
            frame.setVisible(true);
            populateBoard(rows, columns, mines);
        } else {
            System.out.println("Invalid board configuration");
            Main.gameOver = true;
        }
    }
    private void populateBoard(int rows, int columns, int mines) {
        //populate the board with mines
        for(int i = 0; i<mines; i++) {
            boolean inProgress = true;
            while(inProgress) {
                int x = (int)(Math.random()*rows);
                int y = (int)(Math.random()*columns);
                if(board[x][y].getNumber()!=9) {
                    board[x][y].setNumber(9);
                    inProgress = false;
                }
            }
        }
        //populate the board with numbers
        for(int i = 0; i<board.length; i++) {
            for(int j = 0; j<board[i].length; j++) {
                board[i][j].setNumber(minesAround(i, j));
            }
        }
    }
    private int minesAround(int x, int y) {
        if(board[x][y].getNumber() == 9){return 9;} //do not overwrite mines
        int count = 0;
        for(int i = x-1; i<=x+1; i++) {
            for(int j = y-1; j<=y+1; j++){
                try {
                    if(board[i][j].getNumber() == 9) {
                        count++;
                    }
                } catch(ArrayIndexOutOfBoundsException ignore) { }
            }
        }
        return count;
    }
    public static void drawBoard() {
        System.out.print("# ");
        for (int k = 0; k<board[0].length; k++) {
            System.out.print(Character.toString((char)k+65)+ " ");
        }
        System.out.println();
        for (int i = 0; i<board.length; i++) {
            System.out.print(Character.toString((char)i+65) + " ");
            for (int j = 0; j<board[i].length; j++) {
                System.out.print(board[i][j].drawTile() + " ");
                buttons[i][j].setText(String.valueOf(board[i][j].drawTile()));
            }
            System.out.println();
        }
        System.out.println();
    }
    public void revealTile(int x, int y) {
        int number = board[x][y].reveal();
        if(number==9) {
            buttons[x][y].setBackground(Color.RED);
            buttons[x][y].setIcon(new ImageIcon("mine-icon.png"));
            if (!Main.gameOver) {
                endGame(0);
            }
        }
        if(number==0) {
            revealAround(x, y);
        }
    }

    public void flagTile(int x, int y) {
        int number = board[x][y].toggleFlagged();
        flags += number;
    }
    private void revealAround(int x, int y) {
        for(int i = x-1; i<=x+1; i++) {
            for(int j = y-1; j<=y+1; j++){
                try {
                    if(board[i][j].check()) {
                        revealTile(i, j);
                    }
                }catch (ArrayIndexOutOfBoundsException ignore) { }
            }
        }
    }
    public void revealAll() {
        for (int i = 0; i<board.length; i++) {
            for (int j = 0; j<board[i].length; j++) {
                revealTile(i, j);
            }
        }
    }
    public void endGame(int a) {
        JFrame over = new JFrame();
        over.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        over.setSize(300,100);
        over.setVisible(true);
        over.setTitle("Game Over");
        Main.gameOver = true;
        revealAll();
        if(a==0) {
            over.add(new JLabel("YOU LOSE! Uncovered a mine."));
        } else {
            over.add(new JLabel("YOU WIN! Congratulations!"));
        }
    }
    public void mouseClicked(MouseEvent e) {
        JButton src = (JButton) e.getSource();
        if (SwingUtilities.isLeftMouseButton(e)) {
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j <buttons[i].length; j++) {
                    if (src==buttons[i][j]) {
                    revealTile(i,j);
                }
            }
        }
        } else if (SwingUtilities.isRightMouseButton(e)) {
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j <buttons[i].length; j++) {
                    if (src==buttons[i][j]) {
                        flagTile(i,j);
                    }
                }
            }
        }
        if(flags==mines) {
            System.out.println("All flags placed! Revealing remaining tiles...");
            revealAll();
            if(!Main.gameOver) {
                endGame(1);
            }
        }
        drawBoard();
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}
}
