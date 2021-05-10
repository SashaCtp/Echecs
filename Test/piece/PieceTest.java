package piece;

import game.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    @Test
    void testEquals(){

        Piece p1 = new King(Color.WHITE);
        Piece p2 = new King(Color.WHITE);
        Piece p3 = new King(Color.BLACK);

        assertNotEquals(p1, p2);
        assertNotEquals(p2, p3);
        assertNotEquals(p1, p3);

    }

    @Test
    void isBlocked(){

        

    }

    @Test
    void isAttacked() {
    }

}