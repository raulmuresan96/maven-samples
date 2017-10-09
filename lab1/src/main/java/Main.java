
public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.calculate("1 + 2");
        calculator.calculate("max 2 1");
        calculator.calculate("max 1 2");
        calculator.calculate("min 4 3");
        calculator.calculate("1 / 2");
    }
}
