package edu.cofc.johnson;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

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
        assertTrue(Files.exists(Paths.get(Example.BINARY_FILE_NAME)));
        assertFalse(Files.isDirectory(Paths.get(Example.BINARY_FILE_NAME)));
        //  Deserialize another instance and compare them
        Example other = Example.deserializeFromBinaryFile();
        assertEquals(example, other);
        assertNotSame(example, other);
        //  Destroy the file
        assertTrue(Files.deleteIfExists(Paths.get(Example.BINARY_FILE_NAME)));
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
        assertTrue(Files.exists(path));
        assertFalse(Files.isDirectory(path));
        Example other = Example.deserializeFromBinaryFile(FILE_NAME);
        assertEquals(example, other);
        assertNotSame(example, other);
        assertTrue(Files.deleteIfExists(path));
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
        assertTrue(Files.exists(path));
        assertFalse(Files.isDirectory(path));
        Example other = Example.deserializeFromCSVFile(FILE_NAME);
        assertNotNull(other);
        assertNotSame(example, other);
        assertEquals(example, other);
        assertTrue(Files.deleteIfExists(path));
    }
}