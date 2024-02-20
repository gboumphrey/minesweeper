import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameBoardTest {

    @Test
    public void testMinesAround() {
        GameBoard a = new GameBoard(3, 3, 1);
        Assertions.assertTrue((a.minesAround(1,1)==1 || a.minesAround(1,1)==9));
        //either is a mine or has a mine around
    }

    @Test
    public void testRevealTile() {
        GameBoard a = new GameBoard(3,3,1);
        a.revealTile(1,1);
        Assertions.assertEquals(0, GameBoard.board[1][1].reveal(), "tile isnt revealed");
    }

    @Test
    public void testRevealAround() {
        GameBoard a = new GameBoard(3,3,1);
        GameBoard.board[1][1].setNumber(0);
        a.revealTile(1,1);
        Assertions.assertEquals(0, GameBoard.board[0][0].reveal(), "tile isnt revealed");
        Assertions.assertEquals(0, GameBoard.board[0][1].reveal(), "tile isnt revealed");
        Assertions.assertEquals(0, GameBoard.board[0][2].reveal(), "tile isnt revealed");
        Assertions.assertEquals(0, GameBoard.board[1][0].reveal(), "tile isnt revealed");
        Assertions.assertEquals(0, GameBoard.board[1][1].reveal(), "tile isnt revealed");
        Assertions.assertEquals(0, GameBoard.board[1][2].reveal(), "tile isnt revealed");
        Assertions.assertEquals(0, GameBoard.board[2][0].reveal(), "tile isnt revealed");
        Assertions.assertEquals(0, GameBoard.board[2][1].reveal(), "tile isnt revealed");
        Assertions.assertEquals(0, GameBoard.board[2][2].reveal(), "tile isnt revealed");
    }
}
