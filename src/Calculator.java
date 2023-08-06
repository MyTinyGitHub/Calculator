import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Calculator {
    private static final Pattern emptyLinePattern = Pattern.compile("\\r\\n[\\r\\n]+");

    public List<Double> calculate(String content) throws IOException {
        if(content.isEmpty()) {
            return List.of();
        }

        return Stream.of(emptyLinePattern.split(content))
                    .map(this::createExpressions)
                    .map(this::calculateExpression)
                    .collect(Collectors.toList());
    }

    public ArrayDeque<Expression> createExpressions(String content) {
        return content.lines()
                .filter( line -> !line.isEmpty())
                .map(this::createExpression)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }
    public Expression createExpression(String line) {
        String[] a = line.split(" ");
        return new Expression(a[0], Integer.parseInt(a[1]));
    }

    public double calculateExpression(ArrayDeque<Expression> expressions) {
        Expression applyExpression = expressions.getLast();
        return expressions.stream()//
                 .reduce(applyExpression.getValue(), (a, b) -> b.execute(a), Double::sum);
    }

    public String readFile(String path) throws IOException {
        return Files.readString(Path.of(path));
    }
}
