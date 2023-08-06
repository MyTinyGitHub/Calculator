import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OperationTest {
        @Test
    void testAddition() {
        Expression operation = new Expression("add", 4);

        assertEquals(7, operation.execute(3));
        assertEquals(1, operation.execute(-3));
    }

    @Test
    void testSubtraction() {
        Expression operation = new Expression("subtract", 4);

        assertEquals(-1, operation.execute(3));
        assertEquals(-7, operation.execute(-3));
    }

    @Test
    void testMultiplication() {
        Expression operation = new Expression("multiply", 4);

        assertEquals(12, operation.execute(3));
        assertEquals(-12, operation.execute(-3));
    }

    @Test
    void testDivision() {
        Expression operation = new Expression("divide", 4);

        assertEquals(3, operation.execute(12));
        assertEquals(-3, operation.execute(-12));
    }

    @Test()
    void testWrongOperationInput() {
        assertThrows(IllegalArgumentException.class, () -> new Expression("", 12));
        assertThrows(IllegalArgumentException.class, () -> new Expression("as", 4));
    }
}
