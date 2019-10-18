package tests.animalchessexception_tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.*;

public class AnimalChessExceptionTest {

    public void badMethod() throws AnimalChessException {
        throw new AnimalChessException("hello world!");
    }


    @Test
    public void testMessage() {
        try {
            badMethod();
            fail("last line was supposed to throw an AnimalChessException");
        } catch (AnimalChessException e) {
            assertNotNull(e);
            assertEquals(e.getMessage(), "hello world!");
        }
    }

    @Test
    public void TestReportInvalidPlayerError() {
        try {
            AnimalChessException animalChessException = new AnimalChessException("Oups");
            animalChessException.reportInvalidPlayerError();
            fail("this should throw an exception");
        }catch (IllegalArgumentException exception)
        {
            assertNotNull(exception);
            assertEquals(exception.getMessage(),"Oups");
        }
    }

    @Test
    public void TestReportInvalidSquare() {
        try {
            AnimalChessException animalChessException = new AnimalChessException("Oups");
            animalChessException.reportInvalidSquare();
            fail("this should throw an exception");
        }catch (IllegalArgumentException exception)
        {
            assertNotNull(exception);
            assertEquals(exception.getMessage(),"Oups");
        }
    }
}
