package edu.cofc.johnson;

import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExampleTest {

    @Test
    void serialize() {
        Example example = new Example();
        example.setSymbol('A');
        example.setId(123456789);
        example.setName("abcdefg");
        example.setData(1.234f);
        Example.serialize(example);
        Example other = Example.deserialize();
        assertTrue(example.equals(other));
    }

    @Test
    void deserialize() {
    }
}