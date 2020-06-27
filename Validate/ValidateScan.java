package Validate;

import java.util.Scanner;
public class ValidateScan
{
    /**
     * Checks if input has any numbers in it. If it does, it prompts the user with an error
     * message, and asks for a new String until it contains no numbers
     * @param sc Scanner
     * @param input String to be parsed
     * @param errorMsg Error message displayed
     * @return True if input has no numbers in it
     */
    public static boolean hasNoNumbers(Scanner sc, String input, String errorMsg)
    {
        while (!Validate.gui.hasNoNumbers(input))
        {
            System.out.print(errorMsg);
            input = sc.next();
        }
        return true;
    }

    /**
     * Checks if input has no numbers in it. If it does, it prompts the user with an error
     * message, and asks for a new String until it contains no numbers
     * @param sc Scanner
     * @param input String to be parsed
     * @param errorMsg Error message displayed
     * @return True if input has only numbers in it
     */
    public static boolean hasOnlyNumbers(Scanner sc, String input, String errorMsg)
    {
        while (!Validate.gui.hasOnlyNumbers(input))
        {
            System.out.print(errorMsg);
            input = sc.next();
        }
        return true;
    }

    /**
     * Checks if input is an email address. If it isn't, it prompts the user with an error
     * message, and asks for a new String until it is an email address
     * @param sc Scanner
     * @param input String to be parsed
     * @param errorMsg  Error message displayed
     * @return True if input is an email address
     */
    public static boolean isEmail(Scanner sc, String input, String errorMsg)
    {
        while (!Validate.gui.isEmail(input))
        {
            System.out.print(errorMsg);
            input = sc.next();
        }
        return true;
    }

    /**
     * Checks if input is the correct length. If it isn't, it prompts the user with an error
     * message, and asks for a new String until it is the correct length
     * @param sc Scanner
     * @param input String to be parsed
     * @param errorMsg Error message displayed
     * @param targetLength The correct length of the String
     * @return True if input is the same as targetLength
     */
    public static boolean isCorrectLength(Scanner sc, String input, String errorMsg, int targetLength)
    {
        while (!Validate.gui.isCorrectLength(input, targetLength))
        {
            System.out.print(errorMsg);
            input = sc.next();
        }
        return true;
    }

    /**
     * Checks if input is equal to or greater than the specificed length. If it isn't, it prompts the user with an error
     * message, and asks for a new String until it is equal to or greater than the specificed length
     * @param sc Scanner
     * @param input String to be parsed
     * @param errorMsg Error message displayed
     * @param minLength The minimum length of input
     * @return True is input is equal to or greater than minLength
     */
    public static boolean isLongEnough(Scanner sc, String input, String errorMsg, int minLength)
    {
        while (!Validate.gui.isLongEnough(input, minLength))
        {
            System.out.print(errorMsg);
            input = sc.next();
        }
        return true;
    }

    /**
     * Checks if input contains only letters and numbers. If it doesn't, it prompts the user with an error
     * message, and asks for a new String until it contains only letters and numbers
     * @param sc Scanner
     * @param input String to be parsed
     * @param errorMsg Error message displayed
     * @return True if input has only letter and numbers
     */
    public static boolean hasOnlyLettersNumbers(Scanner sc, String input, String errorMsg)
    {
        while (!Validate.gui.hasOnlyLettersNumbers(input))
        {
            System.out.print(errorMsg);
            input = sc.next();
        }
        return true;
    }

    /**
     * Checks if input contains only letters. If it doesn't, it prompts the user with an error
     * message, and asks for a new String until it contains only letters
     * @param sc Scanner
     * @param input String to be parsed
     * @param errorMsg Error message displayed
     * @return True if input has only letter
     */
    public static boolean hasOnlyLetters(Scanner sc, String input, String errorMsg)
    {
        while (!Validate.gui.hasOnlyLetters(input))
        {
            System.out.print(errorMsg);
            input = sc.next();
        }
        return true;
    }

    /**
     * Checks if input contains only letters, numbers, and spaces. It it doesn't, it prompts the user with an error
     * message, and asks for a new String until it contains only letters, numbers, and spaces
     * @param sc Scanner
     * @param input String to be parsed
     * @param errorMsg Error message displayed
     * @return True if input has only letters, numbers, and spaces
     */
    public static boolean hasOnlyLettersNumbersSpaces(Scanner sc, String input, String errorMsg)
    {
        while (!Validate.gui.hasOnlyLettersNumbersSpaces(input))
        {
            System.out.print(errorMsg);
            input = sc.next();
        }
        return true;
    }

    /**
     * Checks if input contains only lettersand spaces. It it doesn't, it prompts the user with an error
     * message, and asks for a new String until it contains only letters and spaces
     * @param sc Scanner
     * @param input String to be parsed
     * @param errorMsg Error message displayed
     * @return True if input has only letters and spaces
     */
    public static boolean hasOnlyLettersSpaces(Scanner sc, String input, String errorMsg)
    {
        while (!Validate.gui.hasOnlyLettersSpaces(input))
        {
            System.out.print(errorMsg);
            input = sc.next();
        }
        return true;
    }
}
