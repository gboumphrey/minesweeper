import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileTest {
    @Test
    public void testCheck() {
        Tile a = new Tile();
        Assertions.assertTrue(a.check());
        a.toggleFlagged();
        Assertions.assertFalse(a.check());
    }
    @Test
    public void testGetSet() {
        Tile a = new Tile();
        a.setNumber(2);
        Assertions.assertEquals(2,a.getNumber(),"2 wasnt 2");
    }

    @Test
    public void testFlag() {
        Tile a = new Tile();
        Assertions.assertEquals(1, a.toggleFlagged(), "didnt toggle");
        Assertions.assertEquals(-1, a.toggleFlagged(), "didnt toggle");
        a.reveal();
        Assertions.assertEquals(0, a.toggleFlagged(), "didnt reveal");
    }

    @Test
    public void testDraw() {
        Tile a = new Tile();
        Assertions.assertEquals(' ',a.drawTile(), "didnt match space");
        a.toggleFlagged();
        Assertions.assertEquals('F', a.drawTile(), "flag didnt match");
        a.toggleFlagged();
        a.setNumber(3);
        a.reveal();
        Assertions.assertEquals('3',a.drawTile(),"didnt match number");
    }

    @Test
    public void testReveal() {
        Tile a = new Tile();
        a.setNumber(3);
        a.toggleFlagged();
        Assertions.assertEquals(-1,a.reveal(), "didnt return flagged");
        a.toggleFlagged();
        Assertions.assertEquals(3,a.reveal(),"didnt reveal");
        Assertions.assertEquals(0,a.reveal(),"revealed when shouldnt");

    }
}
