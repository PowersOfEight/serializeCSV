package edu.cofc.johnson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.ByteBuffer;
public class Example  {
    public static final String FILE_PATH = "./example.csv";
    private int id;
    private float data;
    private String name;
    private char symbol;

    public Example(int id,
                   float data,
                   String name,
                   char symbol) {
        this.id = id;
        this.data = data;
        this.name = name;
        this.symbol = symbol;
    }
    public static void main(String[] args) {
        Example myExample = new Example(0,
                0.1234f,
                "myExample",
                'G');
        System.out.printf("The example class we made is myExample{%s}\n", myExample);


    }
    // Getters
    public int getId() {
        return id;
    }

    public char getSymbol() {
        return symbol;
    }

    public float getData() {
        return data;
    }

    public String getName() {
        return name;
    }

//    public static void writeToCSV(Example example) {
//        Path path = new Path.get(FILE_PATH);
//        if (!Files.exists(path)) {
//            try {
//                path = Files.createFile(path);
//            }catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//        try {
//            Files.write(path, Integer.toString(id).getBytes(), new)
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }

    @Override
    public String toString() {
        return String.format("id=%08d,data=%8.4f,name=%15s,symbol=%c", id, data, name, symbol);
    }

}
