package org.example;
import edu.cofc.johnson.Example;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Main {

//    public static final String[] MENU_OPTIONS =
//            {
//               "Write example to file",
//               "Read examples from file",
//               "Quit the program"
//            };
//    public static final char[] MENU_CHARS =
//            {
//               'A',
//               'B',
//               'Q',
//            };
//
//
//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        char selection = validateUserSelection(input);
//        while(selection != 'Q') {
//            switch (selection) {
//                case 'A':
//                    writeNewExampleToFile(input);
//                    break;
//                case 'B':
//                    Optional<Example[]> optionalExamples =
//                            Example.readFromFile(getInputFileName(input));
//                    if(optionalExamples.isPresent()) {
//                        for(int i = 0; i < optionalExamples.get().length; ++i) {
//                            System.out.printf("The example number %d is: id=%d, name=\"%s\",data=%.3f,sym=%c\n",
//                                    i+1,
//                                    optionalExamples.get()[i].getId(),
//                                    optionalExamples.get()[i].getName(),
//                                    optionalExamples.get()[i].getData(),
//                                    optionalExamples.get()[i].getSymbol());
//                        }
//                    }
//                    break;
//            }
//            selection = validateUserSelection(input);
//        }
//        System.out.print("Halting the program...\n");
//        input.close();
//    }
//
//    public static String getInputFileName(Scanner input) {
//        System.out.print("Input a file name to read records from: ");
//        return input.next();
//    }
//    public static void writeNewExampleToFile(Scanner input) {
//        String path;
//        System.out.print("Input a file name for your example: ");
//        path = input.next();
//        String name = getRecordName(input);
//        int id = validateId(input);
//        float data = validateData(input);
//        char symbol = getRecordSymbol(input);
//        Example.writeToFile(new Example(id, data, name, symbol), path);
//    }
//    public static String getRecordName(Scanner input) {
//        System.out.print("Please input a name for this record: ");
//        return input.next();
//    }
//
//    public static char getRecordSymbol(Scanner input) {
//        System.out.print("Please input a one-character symbol for this record: ");
//        return input.next().charAt(0);
//    }
//
//    public static int validateId(Scanner input) {
//        int result;
//        System.out.print("Please input an integer id for this record: ");
//        try {
//            result = input.nextInt();
//        } catch (InputMismatchException ex) {
//            System.out.println("ERROR: Non-integer input detected, please try again");
//            return validateId(input);
//        }
//        return result;
//    }
//
//    public static float validateData(Scanner input) {
//        float result;
//        System.out.print("Please input a number for the data of this record: ");
//        try {
//            result = input.nextFloat();
//        } catch (InputMismatchException ex) {
//            System.out.println("ERROR: Non-numeric input detected, please try again");
//            return validateData(input);
//        }
//        return result;
//    }
//
//    public static char validateUserSelection(Scanner input) {
//        char selection;
//        displayMenu();
//        System.out.print("Please input your selection: ");
//        selection = input.next().toUpperCase().charAt(0);
//        for(char choice : MENU_CHARS) {
//            if (selection == choice)
//                return choice;
//        }
//        System.out.printf("ERROR: %c is not a valid selection. Please try again\n", selection);
//        return validateUserSelection(input);
//    }
//
//    //  TODO: Change to printToCSV
//    public static void displayMenu() {
//        for(int i = 0; i < MENU_OPTIONS.length; ++i) {
//            System.out.printf("(%c)%8s%s\n",MENU_CHARS[i],"",MENU_OPTIONS[i]);
//        }
//    }
}