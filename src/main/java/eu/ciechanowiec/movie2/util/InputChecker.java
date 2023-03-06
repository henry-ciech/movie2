package eu.ciechanowiec.movie2.util;

import java.util.Scanner;

public class InputChecker {

    private final Printer printer;
    private final Scanner scanner;

    public InputChecker() {
        printer = new Printer();
        scanner = new Scanner(System.in);
    }

    private boolean isNumber(String valueToCheck) {
        try {
            Integer.parseInt(valueToCheck);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean checkIsInputNotValid(String input, int maxValidNumber) {
        if (isNumber(input) && Integer.parseInt(input) <= maxValidNumber && Integer.parseInt(input) > 0) {
            return false;
        } else {
            printer.println("Invalid input");
            return true;
        }
    }

    public boolean isContinueOrNot() {
        String askContinue;

        while (true) {
            printer.println("Do you want to continue [Y/n]?");
            askContinue = scanner.nextLine();
            if (askContinue.equalsIgnoreCase("Y") || askContinue.isEmpty()) {
                return true;
            } else if (askContinue.equalsIgnoreCase("n")) {
                return false;
            } else {
                printer.println("Invalid input");
            }
        }
    }

    public boolean isYesOrNo(String inputToCheck) {
        return inputToCheck.equalsIgnoreCase("yes") || inputToCheck.equalsIgnoreCase("no");
    }
}
