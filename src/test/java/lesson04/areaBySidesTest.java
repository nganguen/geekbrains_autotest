package lesson04;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class areaBySidesTest {

    private static Logger logger = LoggerFactory.getLogger(areaBySidesTest.class);

    @Test
    public void areaResultTest() {
        assertAll("number",
                () -> assertEquals(6, Triangle.areaBySides(3, 4, 5)),
                () -> assertEquals("No triangle exists", Assertions.assertThrows(Exception.class,
                        () -> Triangle.areaBySides(1, 1, 2)).getMessage())
        );
        /*assertEquals(6, Triangle.areaBySides(3, 4, 5));
        assertThrows(Exception.class,
                () -> { Triangle.areaBySides(3, 4, 5); });*/
    }
}
