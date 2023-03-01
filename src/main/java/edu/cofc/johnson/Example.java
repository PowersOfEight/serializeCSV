package edu.cofc.johnson;
//  AUTHOR: James Daniel Johnson
import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public static final String FILE_NAME = "example.ser";
    /**
     * For serializable implementation
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private float data;
    private String name;
    private  char symbol;

    public static void main(String[] args) throws IOException{
        //  Create the example
        Example example = new Example();
        example.setData(1.234f);
        example.setId(8);
        example.setName("Example");
        example.setSymbol('@');
        //  Write to file
        Example.serialize(example);
        Example readExample = Example.deserialize();
        // Make sure examples are equal
        assert(example.equals(readExample));
        //  Clean up the file
        Path path = Paths.get(FILE_NAME);
        if (Files.exists(path) && Files.isRegularFile(path)) {
            Files.delete(path);
        }
    }

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

    public boolean equals(Example other) {
        return (this.id == other.getId() &&
                this.data == other.getData() &&
                this.name.equals(other.getName()) &&
                this.symbol == other.getSymbol());
    }

    public static void serialize(Example example) {
        Path outFile = Paths.get(FILE_NAME);
        try (ObjectOutputStream stream =
                     new ObjectOutputStream(Files.newOutputStream(outFile))) {
            stream.writeObject(example);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Example deserialize() {
        Path inFile = Paths.get(FILE_NAME);
        Example example = new Example();
        try(ObjectInputStream stream =
                new ObjectInputStream(Files.newInputStream(inFile))) {
            example = (Example) stream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return example;
    }
}

