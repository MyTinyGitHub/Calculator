import java.util.function.DoubleBinaryOperator;

public class Expression {
    public enum Type {
        ADD(Double::sum),
        SUBTRACT((a, b) -> a - b),
        MULTIPLY((a, b) -> a * b),
        DIVIDE((a, b) -> a / b ),
        APPLY((a, b) -> a);
        private final DoubleBinaryOperator expression;

        private Type(DoubleBinaryOperator ex) {
            this.expression = ex;
        }
        public double calculate(double val1, double val2) {
            return expression.applyAsDouble(val1, val2);
        }

        public DoubleBinaryOperator getBinaryOperator() {
            return expression;
        }
    }
    private Type expression;
    private double value;

    public Expression(String operation, double number) {
        this.expression = Type.valueOf(operation.toUpperCase());
        this.value = number;
    }

    public double getValue() {
        return this.value;
    }

    public DoubleBinaryOperator operator() {
        return this.expression.getBinaryOperator();
    }
    public double execute(double number) {
        return expression.calculate(number, value);
    }


}
