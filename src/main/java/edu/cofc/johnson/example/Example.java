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

    /**
     * Returns true if the other object is not null, shares
     * the same class, and all fields are equal
     * @param other The other object to compare
     * @return True if the other object is equal to this one, false if it is not
     */
    @Override
    public boolean equals(Object other) {
        boolean equal;
        if (this == other) {
            equal = true;
        } else if (other == null || getClass() != other.getClass()) {
            equal = false;
        } else {
            Example example = (Example) other;
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
     * Formats the instance into a comma separated value record.
     * @return A string representing a comma separated value record
     */
    public String printToCSVRecord() {
        return String.format("%d,%f,%s,%c%n",id,data,name,symbol);
    }

    /**
     * Serializes the object into a comma separated value file
     * @param example The object instance to be serialized into a file
     * @param fileName The name of the file to serialize
     */
    public static void serializeToCSVFile(Example example, String fileName) {
        Path path = Paths.get(fileName);
        try (OutputStream stream = Files.newOutputStream(path)) {
            stream.write(example.printToCSVRecord().getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Deserializes an object from a comma separated value file and returns it.
     * If a file is not found, will return <code>null</code>.
     * @param fileName The name of the comma separated value file to deserialize from
     * @return An instance of an object if the file is found, or null
     */
    public static Example deserializeFromCSVFile(String fileName) {
        Path path = Paths.get(fileName);
        Example example = null;
        try (InputStream stream = Files.newInputStream(path)) {
            String[] record = new String(stream.readAllBytes()).split(",");
            example = new Example(Integer.parseInt(record[0]),
                    Float.parseFloat(record[1]),
                    record[2],
                    record[3].charAt(0));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return example;
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
        Path outFile = Paths.get(fileName);// Do this for Object instead of Example
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

