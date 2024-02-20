import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileTest {
    @Test
    public void testCheck() {
        Tile a = new Tile();
        Assertions.assertTrue(a.check());

    }
}
