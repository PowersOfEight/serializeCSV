package edu.cofc.johnson.example;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

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
 * @author James Daniel Johnson
 */
@XStreamAlias("example")
public class Example implements Serializable  {
    @XStreamOmitField
    @Serial
    private static final long serialVersionUID =  6995375697756630868L;


    private final int id;
    private final float data;
    private final String name;
    private final char symbol;



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
    public static void serializeToCSVFile(Example example, String fileName)
        throws IOException {
        Path path = Paths.get(fileName);
        OutputStream stream = Files.newOutputStream(path);
        stream.write(example.printToCSVRecord().getBytes());
        stream.close();
    }

    /**
     * Deserializes an object from a comma separated value file and returns it.
     * If a file is not found, will return <code>null</code>.
     * @param fileName The name of the comma separated value file to deserialize from
     * @return An instance of an object if the file is found, or null
     * @throws IOException If the file doesn't exist or can't be opened
     */
    public static Example deserializeFromCSVFile(String fileName)
        throws IOException {
        Path path = Paths.get(fileName);
        Example example = null;
        InputStream stream = Files.newInputStream(path);
        String[] record = new String(stream.readAllBytes()).split(",");
        stream.close();
        example = new Example(Integer.parseInt(record[0]),
               Float.parseFloat(record[1]),
               record[2],
               record[3].charAt(0));
        return example;
    }

    /**
     * <p>
     *     Serializes the example to an XML file.<br/>
     *     <a href="https://www.baeldung.com/xstream-serialize-object-to-xml">
     *         Serializing an object to XML with XStream</a>
     * </p>
     * @param example The example object to serialize
     * @param fileName The name of the file to serialize the object to
     * @throws IOException If the event of a problem creating or writing the file
     * @throws XStreamException If there is a problem with the xml encoding
     */
    public static void serializeToXML(Example example, String fileName)
            throws IOException, XStreamException {
        XStream xStream = new XStream();
        xStream.processAnnotations(Example.class);
        OutputStream stream =
                Files.newOutputStream(Paths.get(fileName));
        stream.write(xStream.toXML(example).getBytes());
        stream.close();
    }

    /**
     * <p>
     *      Deserializes and returns an instance of <code>Example</code> from
     *      the given file.<br/>
     *      <a href="https://www.baeldung.com/xstream-deserialize-xml-to-object">
     *          Deserializing an object from XML using XStream</a>
     * </p>
     * @param fileName The name of the file to deserialize from
     * @return A deserialized instance of the <code>Example</code> class
     * @throws IOException If the file doesn't exist or cannot be opened
     * @throws XStreamException If there is a problem parsing the XML
     */
    public static Example deserializeFromXML(String fileName)
        throws IOException, XStreamException {
        InputStream stream =
                Files.newInputStream(Paths.get(fileName));
        XStream xStream = new XStream();
        xStream.allowTypesByWildcard(new String[]{"edu.cofc.johnson.**"});
        xStream.processAnnotations(Example.class);
        Example example = (Example) xStream.fromXML(stream);
        return example;
    }

}

