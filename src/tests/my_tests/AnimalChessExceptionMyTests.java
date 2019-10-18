package tests.my_tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import exceptions.AnimalChessException;
import org.junit.Test;

/**
 * This class tests some of the AnimalException functionality.
 */
public class AnimalChessExceptionMyTests {

    /**
     * This method tests if the reportInvalidPlayerError throws an exception when called.
     */
    @Test
    public void testReportInvalidPlayerError() {
        try {
            AnimalChessException animalChessException = new AnimalChessException("Oups");
            animalChessException.reportInvalidPlayerError();
            fail("this should throw an exception");
        } catch (IllegalArgumentException exception) {
            assertNotNull(exception);
            assertEquals(exception.getMessage(), "Oups");
        }
    }

    /**
     * This method tests if the reportInvalidSquare throws an exception when called.
     */
    @Test
    public void testReportInvalidSquare() {
        try {
            AnimalChessException animalChessException = new AnimalChessException("Oups");
            animalChessException.reportInvalidSquare();
            fail("this should throw an exception");
        } catch (IllegalArgumentException exception) {
            assertNotNull(exception);
            assertEquals(exception.getMessage(), "Oups");
        }
    }
}
