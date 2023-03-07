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
    /**
     * For serializable implementation
     */
    // @Serial
    // private static final long serialVersionUID = -295161158544973069;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Example example = (Example) o;
        return id == example.id && Float.compare(example.data, data) == 0 && symbol == example.symbol && name.equals(example.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data, name, symbol);
    }


    /**
     * Serializes the object to a binary file
     * @param example The object to serialize
     */
    public static void serializeToBinary(Example example) {
        Path outFile = Paths.get(BINARY_FILE_NAME);
        try (ObjectOutputStream stream =
                     new ObjectOutputStream(Files.newOutputStream(outFile))) {
            stream.writeObject(example);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *  Deserializes an object from a binary file
      * @return The object deserialized from file
     */
    public static Example deserializeFromBinary() {
        Path inFile = Paths.get(BINARY_FILE_NAME);
        Example example = new Example();
        try(ObjectInputStream stream =
                new ObjectInputStream(Files.newInputStream(inFile))) {
            example = (Example) stream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return example;
    }

    //  This is used to replace serialization as per Bowring
    private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {
        stream.defaultReadObject();

        ObjectStreamClass myObject = ObjectStreamClass.lookup(
                Class.forName(Example.class.getCanonicalName()));
        long theSUID = myObject.getSerialVersionUID();

        System.err.println("Customized De-serialization of Baseline "
                + theSUID);
    }
}

