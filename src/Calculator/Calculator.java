package Calculator;

import java.io.IOException;
import java.util.Arrays;
import java.util.*;


public class Calculator {
    public static void main(String[] args) throws IOException {
        Scanner inputScanner = new Scanner(System.in);
          System.out.println("введите выражение");
        String input = inputScanner.nextLine();
        System.out.println(calculation(input));
    }

    public static String calculation(String input) throws IOException {
        String[] inputElements = input.split(" ");
        IOException inputFormatException = new IOException("Неверный формат ввода данных. Корректный пример: \"I + II\"");
        if(inputElements.length != 3){
            throw inputFormatException;
        }
        String numberOne = inputElements[0];
        String numberTwo = inputElements[2];
        String action = inputElements[1];
        String[] arabicNumbers = {"1","2","3","4","5","6","7","8","9","10"};
        if(Arrays.asList(arabicNumbers).contains(numberOne) && Arrays.asList(arabicNumbers).contains(numberTwo)) {
            int arabicNumberOne = Integer.parseInt(numberOne);
            int arabicNumberTwo = Integer.parseInt(numberTwo);
            return switch (action) {
                case "+" -> String.valueOf(arabicNumberOne + arabicNumberTwo);
                case "-" -> String.valueOf(arabicNumberOne - arabicNumberTwo);
                case "*" -> String.valueOf(arabicNumberOne * arabicNumberTwo);
                case "/" -> String.valueOf(arabicNumberOne / arabicNumberTwo);
                default -> throw new IOException("Неверный операнд");
                };
            }

        String[] romanNumbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        if(Arrays.asList(romanNumbers).contains(numberOne) && Arrays.asList(romanNumbers).contains(numberTwo)) {
            int romanNumberOne = Arrays.asList(romanNumbers).indexOf(numberOne) + 1;
            int romanNumberTwo = Arrays.asList(romanNumbers).indexOf(numberTwo) + 1;
            if(romanNumberTwo >= romanNumberOne && (Objects.equals(action, "-") | Objects.equals(action, "/"))) {
                throw new IOException("Недопустимый результат для римских чисел");
            }
            return switch (action) {
                case "+" -> IntegerConverter.intToRoman(romanNumberOne + romanNumberTwo);
                case "-" -> IntegerConverter.intToRoman(romanNumberOne - romanNumberTwo);
                case "*" -> IntegerConverter.intToRoman(romanNumberOne * romanNumberTwo);
                default -> IntegerConverter.intToRoman(romanNumberOne / romanNumberTwo);
            };
        }
        throw inputFormatException; // exception
        }

}

class IntegerConverter {

    public static String intToRoman(int number) {
        if (number > 100 || number <= 0)
            return null;
        StringBuilder result = new StringBuilder();
        for(Integer key : units.descendingKeySet()) {
            while (number >= key) {
                number -= key;
                result.append(units.get(key));
            }
        }
        return result.toString();
    }

    private static final NavigableMap<Integer, String> units;
    static {
        NavigableMap<Integer, String> initMap = new TreeMap<>();
        initMap.put(100, "C");
        initMap.put(90, "XC");
        initMap.put(50, "L");
        initMap.put(40, "XL");
        initMap.put(10, "X");
        initMap.put(9, "IX");
        initMap.put(5, "V");
        initMap.put(4, "IV");
        initMap.put(1, "I");
        units = Collections.unmodifiableNavigableMap(initMap);
    }
}

