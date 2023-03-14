package edu.cofc.johnson;

import edu.cofc.johnson.example.Example;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class ExampleTest {

    @Test
    void serializeToBinary() throws IOException {
        Example example = new Example(
                123456789,
                1.234f,
                "abcdefg",
                'A'
        );
        Example.serializeToBinaryFile(example);
        //  Check that the file name created
        Assertions.assertTrue(Files.exists(Paths.get(Example.BINARY_FILE_NAME)));
        Assertions.assertFalse(Files.isDirectory(Paths.get(Example.BINARY_FILE_NAME)));
        //  Deserialize another instance and compare them
        Example other = Example.deserializeFromBinaryFile();
        Assertions.assertEquals(example, other);
        Assertions.assertNotSame(example, other);
        //  Destroy the file
        Assertions.assertTrue(Files.deleteIfExists(Paths.get(Example.BINARY_FILE_NAME)));
    }

    @Test
    void serializeToBinaryFile() throws IOException {
        final String FILE_NAME = "example.ser";
        Path path = Paths.get(FILE_NAME);
        Example example = new Example(
                54321,
                4.321f,
                "hijklmnop",
                'Z'
        );
        Example.serializeToBinaryFile(example, FILE_NAME);
        Assertions.assertTrue(Files.exists(path));
        Assertions.assertFalse(Files.isDirectory(path));
        Example other = Example.deserializeFromBinaryFile(FILE_NAME);
        Assertions.assertEquals(example, other);
        Assertions.assertNotSame(example, other);
        Assertions.assertTrue(Files.deleteIfExists(path));
    }

    @Test
    void serializeToCSVFile() throws IOException{
        final String FILE_NAME = "example.csv";
        Path path = Paths.get(FILE_NAME);
        Example example = new Example(
                45678,
                9.321f,
                "qwerty",
                'p'
        );
        Example.serializeToCSVFile(example, FILE_NAME);
        Assertions.assertTrue(Files.exists(path));
        Assertions.assertFalse(Files.isDirectory(path));
        Example other = Example.deserializeFromCSVFile(FILE_NAME);
        Assertions.assertNotNull(other);
        Assertions.assertNotSame(example, other);
        Assertions.assertEquals(example, other);
        Assertions.assertTrue(Files.deleteIfExists(path));
    }
}