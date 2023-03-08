package edu.cofc.johnson;
//  AUTHOR: James Daniel Johnson
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * <h1>edu.cofc.johnson.Example</h1>
 * <p>
 *  This is class serves as an example of writing a class to a file.
 *  It features 2 static methods: one to write an object to a file, and
 *  another to read an array of objects from a file.
 * </p>
 * <p>
 *  <a target="blank" href="https://www.baeldung.com/java-serialization">
 *      Baeldung on Java Serialization
 *  </a><br/>
 *  <a target="blank" href="https://www.baeldung.com/java-path-vs-file>
 *      Baeldung on the differences between java.nio.file.Path and java.io.File
 *  </a><br/>
 *  <a target="blank" href="https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html">
 *      Java.io.Serializable documentation
 *  </a><br/>
 * </p>
 */
public class Example implements Serializable  {
    public static final String BINARY_FILE_NAME = "example.ser";

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

    public float getData() { return data; }

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

    @Override
    public boolean equals(Object o) {
        boolean equal = false;
        if (this == o) {
            equal = true;
        } else if (o == null || getClass() != o.getClass()) {
            equal = false;
        } else {
            Example example = (Example) o;
            equal = id == example.getId() &&
                    Float.compare(example.getData(), data) == 0 &&
                    symbol == example.getSymbol() &&
                    name.equals(example.getName());
        }
        return equal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data, name, symbol);
    }


    /**
     * Serializes the object to a binary file
     * @param example The object to serialize
     */
    public static void serializeToBinaryFile(Example example) {
        serializeToBinaryFile(example, BINARY_FILE_NAME);
    }

    /**
     * Serializes the example object to binary file. Uses the name of the
     * file provided in the second argument
     * @param example The example object to serialize
     * @param fileName The file to serialize the object to
     */
    public static void serializeToBinaryFile(Example example, String fileName) {
        Path outFile = Paths.get(fileName);
        try (ObjectOutputStream stream =
                new ObjectOutputStream(Files.newOutputStream(outFile))) {
            stream.writeObject(example);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Deserializes the stored object from a binary file.  Uses the name
     * of the file provided in the second argument
     * @param fileName The name of the file to deserialize from
     * @return An instance of the example class from the file, or <code>null</code>
     */
    public static Example deserializeFromBinaryFile(String fileName) {
        Path inFile = Paths.get(fileName);
        Example example = null;
        try (ObjectInputStream stream =
                new ObjectInputStream(Files.newInputStream(inFile))) {
            example = (Example) stream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return example;
    }
    /**
     *  Deserializes an object from the default file
      * @return The object deserialized from the default file
     */
    public static Example deserializeFromBinaryFile() {
        return Example.deserializeFromBinaryFile(BINARY_FILE_NAME);
    }


}

