package task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input3 {

    private static final int MAX_LENGTH = 100;
    private static final int MIN_LENGTH = 3;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> container = new ArrayList<>();
        String input;
        int countInput;

        try {
            System.out.print("Please, enter number: ");
            try {
                countInput = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                throw new WrongInputFormatException("Первое ввденное значение должно быть целым числом!!!");
            }

            if (countInput < MIN_LENGTH || countInput > MAX_LENGTH) {
                throw new IndexOutOfBoundsException("Число должно быть в диапазоне от " + MIN_LENGTH + " до " + MAX_LENGTH + "!!!");
            }

            container.add("");

            int increment = 0;
            do {
                System.out.print("Please, enter word: ");
                input = scanner.next();

                if (input.length() < MIN_LENGTH || input.length() > MAX_LENGTH) {
                    throw new IndexOutOfBoundsException("ввод должен иметь от 1 до 100 символов!!!");
                }

                Matcher matcherNumber = Pattern.compile("\\d+").matcher(input);
                Matcher matcherLatinLetter = Pattern.compile("[a-z]+", Pattern.CASE_INSENSITIVE).matcher(input);
                if (matcherNumber.find()) {
                    throw new WrongInputFormatException("Только буквенные символы!!!");
                } else if (matcherLatinLetter.find()) {
                    container.add(input);
                } else {
                    throw new WrongInputFormatException("Only latin letter!!!");
                }
            } while (++increment < countInput);

            System.out.println("\n===Result: ===");
            foo(container).forEach(System.out::println);

        } catch (WrongInputFormatException | IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
            System.out.print("Try again ? \nplease enter (y|n) : ");
            String answer = scanner.next();
            if (answer.equals("y")) {
                main(null);
            } else {
                System.exit(1);
            }
        }
    }

    private static List<String> foo(List<String> list) {
        List<String> temp = new ArrayList<>();
        for (String s : list) {
            temp.add(squeezeWord(s));
        }
        return temp;
    }

    private static String squeezeWord(String s) {
        if (s.length() <= 10) {
            return s;
        }

        StringBuilder result = new StringBuilder();
        result.append(s.charAt(0));
        result.append(String.valueOf(s.length() - 2));
        result.append(s.charAt(s.length() - 1));

        return result.toString();
    }
}