package edu.cofc.johnson.core.utils;

import edu.cofc.johnson.core.example.Example;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class BinarySerializationEngineTest {

    private String testFileName;
    @BeforeEach
    void setUp() {
        String filePrefix = "testBinaryFile";
        String fileExtension = ".ser";
        testFileName = String.format("%s%s",filePrefix,fileExtension);
        int count = 0;
        //  Determine if this file exists in the user's system
        //  If it does, mutate the name until an unused name is found
        while (Files.exists(Paths.get(testFileName))) {
            testFileName = String.format("%s%d%s", filePrefix, count++, fileExtension);
        }
    }

    @Test
    void binarySerializeAndDeserializeExample()
            throws IOException, ClassNotFoundException {
        Example example = new Example(
                1029,
                4.37f,
                "tyruily",
                'Y'
        );
        BinarySerializationEngine.serializeToBinary(example,testFileName);
        Example other = (Example) BinarySerializationEngine.
                deserializeFromBinary(testFileName);
        assertEquals(example, other);
        assertNotSame(example, other);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(testFileName));
    }
}