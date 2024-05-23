package myFirstJunit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JunitDemoTest {

    @Test
    void testEqualsAndNotEquals() {
        // set up
        JunitDemo junitDemo = new JunitDemo();
        int expected = 6;
        // execute
        int actual = junitDemo.add(2,4);
        // assert
        Assertions.assertEquals(expected, actual, "2 + 4 must be 6");
    }
}
