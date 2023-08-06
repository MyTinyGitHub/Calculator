import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {
    Calculator calculator = new Calculator();
    private ArrayList<Double> results;

    @BeforeEach
    public void setUp() {
        results = new ArrayList<>();
    }

    @Test
    void testOperationAddition() throws IOException {
        String fileContent = """
                add 3
                apply 4
                \r\n            
                add -3
                apply 4
                \r\n
                add -3
                apply -4
                """;

        results.add(7.0);
        results.add(1.0);
        results.add(-7.0);

        var actualResult = calculator.calculate(fileContent);
        assertEquals(results, actualResult);
    }

    @Test
    public void testOperationsSubtract() throws IOException {
        String testString = """
                subtract 3
                apply 4
                \r\n            
                subtract -3
                apply 4
                \r\n        
                subtract -3
                apply -4
                """;

        results.add(1.0);
        results.add(7.0);
        results.add(-1.0);

        var actualResult = calculator.calculate(testString);
        assertEquals(results, actualResult);
    }

    @Test
    public void testOperationMultiplication() throws IOException {
        String testString = """
                multiply 3
                apply 4
                \r\n            
                multiply -3
                apply 4
                \r\n            
                multiply -3
                apply -4
                """;

        results.add(12.0);
        results.add(-12.0);
        results.add(12.0);

        var actualResult = calculator.calculate(calculator.readFile("TestFiles/Operations/Multiplication.txt"));
        assertEquals(results, actualResult);
    }

    @Test
    void testOperationsDivision() throws IOException {
        String testString = """
                divide 4
                apply 12
                \r\n        
                divide -4
                apply 12
                \r\n                
                divide -4
                apply -12
                """;

        results.add(3.0);
        results.add(-3.0);
        results.add(3.0);

        var actualResult = calculator.calculate(calculator.readFile("TestFiles/Operations/Division.txt"));
        assertEquals(results, actualResult);
    }

    @Test
    void testTestMultipleEquationsFiles() throws IOException {
        var fileContent = calculator.readFile("TestFiles/MultiEquation/Test.txt");

        results.add(2.0);
        results.add(5.0);
        results.add(32.0);
        results.add(-1.0);
        results.add(-0.8);
        results.add(-1.0);

        var actualResult = calculator.calculate(fileContent);
        assertEquals(results, actualResult);
    }

    @Test
    void testTestEmptyFile() throws IOException {
        var fileContent = calculator.readFile("TestFiles/testFileEmpty.txt");
        var actualResult = calculator.calculate(fileContent);
        assertEquals(results, actualResult);
    }
}