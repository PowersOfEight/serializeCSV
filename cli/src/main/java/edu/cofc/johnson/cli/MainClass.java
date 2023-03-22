package edu.cofc.johnson.cli;

import edu.cofc.johnson.core.example.Example;

public class MainClass {
    public static void main(String[] args) {
        Example example = new Example(13, 34.56f, "Hello", 'A');
        System.out.printf("The current example: %s\n", example.printToCSVRecord());
    }
}
