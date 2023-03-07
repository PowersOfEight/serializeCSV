package edu.cofc.johnson;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
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
        Example.serializeToBinary(example);
        //  Check that the file name created
        assertTrue(Files.exists(Paths.get(Example.BINARY_FILE_NAME)));
        assertFalse(Files.isDirectory(Paths.get(Example.BINARY_FILE_NAME)));
        //  Deserialize another instance and compare them
        Example other = Example.deserializeFromBinary();
        assertEquals(example, other);
        assertNotSame(example, other);
        //  Destroy the file
        assertTrue(Files.deleteIfExists(Paths.get(Example.BINARY_FILE_NAME)));
    }

}