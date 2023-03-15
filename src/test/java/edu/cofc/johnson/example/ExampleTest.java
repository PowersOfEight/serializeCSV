package edu.cofc.johnson.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class ExampleTest {

    String testFileName;
    String fileExtension;// NOTE: set this each test for the given test
    @BeforeEach
    void setUp() {
        String filePrefix = "testFile";
        testFileName = String.format("%s",filePrefix);
        int count = 0;
        //  Determine if this file exists in the user's system
        //  If it does, mutate the name until an unused name is found
        while (Files.exists(Paths.get(testFileName))) {
            testFileName = String.format("%s%d", filePrefix, count++);
        }
    }
    @Test
    void serializeToCSVFile() throws IOException{
        fileExtension = ".csv";
        final String FILE_NAME = testFileName + fileExtension;
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
    }

    @Test
    void serializeToXMLFile() throws IOException {
        fileExtension =  ".xml";
        final String FILE_NAME = testFileName + fileExtension;
        Path path = Paths.get(FILE_NAME);
        Example example = new Example(1029,
                5.876f,
                "146ef1",
                'U');
        Example.serializeToXML(example, FILE_NAME);
        Assertions.assertTrue(Files.exists(path));
        Assertions.assertFalse(Files.isDirectory(path));
        Example other = Example.deserializeFromXML(FILE_NAME);
        Assertions.assertNotNull(other);
        Assertions.assertNotSame(example, other);
        Assertions.assertEquals(example, other);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(testFileName + fileExtension));
    }

}