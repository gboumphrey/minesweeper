import java.util.Scanner;

public class Main {
    public static Scanner reader = new Scanner(System.in);
    public static boolean gameOver = false;

    public static void interpret(String input, GameBoard board) {
        try {
            String[] split = input.split(" ");
            if (split[0].equalsIgnoreCase("flag")) {
                board.flagTile(split[1].charAt(1)-65,split[1].charAt(0)-65);
            } else if (split[0].equalsIgnoreCase("reveal")) {
                board.revealTile(split[1].charAt(1)-65,split[1].charAt(0)-65);
            } else {
                System.out.println("Command wasn't recognised.");
            }
        } catch (Exception e) {
            System.out.println("Coordinate wasn't recognised. " + e);
        }
    }
    private static int tryParseInt(String s) {
        try {
            return Math.abs(Integer.parseInt(s));
        } catch (Exception e) {
            System.out.println("That value isn't allowed");
            return 0;
        }
    }

    public static void main(String[] args) {

        // set variables
        boolean setting = true;
        int rows = 8;
        int columns = 10;
        int mines = 10;
        while(setting) {
            System.out.print("Enter number of rows: ");
            rows = tryParseInt(reader.next());
            System.out.print("Enter number of columns: ");
            columns = tryParseInt(reader.next());
            System.out.print("Enter number of mines: ");
            mines = tryParseInt(reader.next());
            if(2<=rows && rows<=30 && 2<=columns && columns<=30 && mines<rows*columns) {
                setting = false;
            } else {
                System.out.println("Something went wrong, try again. ");
            }
        }
        GameBoard board = new GameBoard(rows, columns, mines);

        /* Loop for playing via terminal
        while(!gameOver) {
            GameBoard.drawBoard();
            System.out.println("Type flag/reveal then coordinate (horizontal then vertical)");
            interpret(reader.nextLine(), board);
            if (board.flags == mines) {
                board.revealAll();
                GameBoard.drawBoard();
                if(!gameOver) {
                    System.out.println("!!!!!!!!!!!!!!!");
                    System.out.println("CONGRATULATIONS");
                    System.out.println("!!!!!!!!!!!!!!!");
                    gameOver = true;
                }
            }

        }
         */

    }
}
