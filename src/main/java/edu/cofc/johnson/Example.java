package edu.cofc.johnson;
//  AUTHOR: James Daniel Johnson
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

/**
 *  This is class serves as an example of writing a class to a file.
 *  It features 2 static methods: one to write an object to a file, and
 *  another to read an array of objects from a file.
 */
public class Example  {
    private static final int NUMBER_OF_FIELDS = 4;
    private static final int ID_INDEX = 0;
    private static final int DATA_INDEX = 1;
    private static final int NAME_INDEX = 2;
    private static final int SYMBOL_INDEX = 3;
    private int id;
    private float data;
    private String name;
    private  char symbol;

    public Example() {
        this.id = 0;
        this.name = "";
        this.data = 0.0f;
        this.symbol = ' ';
    }
    public Example(int id,
                   float data,
                   String name,
                   char symbol) {
        this.id = id;
        this.data = data;
        this.name = name;
        this.symbol = symbol;
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

    //  Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
    public void setData(float data) {
        this.data = data;
    }
    public static boolean writeToFile(Example example, String pathToFile) {
        try(FileChannel channel = FileChannel.open(Paths.get(pathToFile),
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,
                StandardOpenOption.APPEND)){
            byte[] record = example.toString().getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(record.length);
            buffer.put(record);
            buffer.flip();
            channel.write(buffer);
            channel.close();
            return true;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    /**
     *
     * @param pathToFile The path to the file
     * @return
     */
    public static Optional<Example[]> readFromFile(String pathToFile) {
        Optional<String[]> records = getRecordsFromFile(pathToFile);
        if (records.isPresent()) {
            Example[] examples = new Example[records.get().length];
            for(int i = 0; i < records.get().length; ++i) {
                String[] fields = records.get()[i].split(",");
                try {
                    if (fields.length != NUMBER_OF_FIELDS) {
                        throw new IllegalArgumentException();
                    }
                    examples[i] = new Example(
                            Integer.parseInt(fields[ID_INDEX]),
                            Float.parseFloat(fields[DATA_INDEX]),
                            fields[NAME_INDEX],
                            fields[SYMBOL_INDEX].charAt(0));
                } catch (IllegalArgumentException ex) {
                    System.err.printf("Invalid record: line %d", i + 1);
                }
            }
            return Optional.of(examples);
        }
        return Optional.empty();
    }

    private static Optional<String[]> getRecordsFromFile(String pathToFile) {

        try(FileChannel channel =
                    FileChannel.open(Paths.get(pathToFile),
                            StandardOpenOption.READ)) {
            StringBuilder fileContents = new StringBuilder();
            ByteBuffer buffer = ByteBuffer.allocate(1);
            while (channel.read(buffer) != -1) {
                buffer.flip();
                fileContents.append((char) buffer.get());
                buffer.clear();
            }
            return Optional.of(fileContents.toString().split("\n"));
        } catch (IOException ex) {
            return Optional.empty();
        }
    }
    @Override
    public String toString() {
        return String.format("%d,%f,%s,%c%n", id, data, name, symbol);
    }

}
