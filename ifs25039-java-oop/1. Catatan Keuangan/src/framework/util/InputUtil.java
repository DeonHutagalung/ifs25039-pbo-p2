package framework.util;

import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readString(String prompt) {
        System.out.print(prompt);
        if (!scanner.hasNextLine()) {
            throw new EOFException();
        }
        return scanner.nextLine().trim();
    }
}