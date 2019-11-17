import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.*;

public class OperationTest {

    Operation operation;

    @Rule
    public ExpectedException exceptionRule;

    @Before
    public void setUp() throws Exception {
        operation = new Operation();
        exceptionRule = ExpectedException.none();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testComputeWithValidParams() {
        String result = operation.compute("1");
        assertEquals("1",result);

        result = operation.compute("21");
        assertEquals("FooQix",result);

        result = operation.compute("15");
        assertEquals("FooBarBar",result);


        result = operation.compute("101");
        assertEquals("1*1",result);

        result = operation.compute("303");
        assertEquals("FooFoo*Foo",result);

        result = operation.compute("105");
        assertEquals("FooBarQix*Bar",result);

        result = operation.compute("10101");
        assertEquals("FooQix**",result);

        result = operation.compute("109");
        assertEquals("1*9",result);

        result = operation.compute("107");
        assertEquals("1*Qix",result);

        result = operation.compute("1071053411");
        assertEquals("1*Qix1*BarFoo411",result);

    }

    @Test
    public void testComputeWithInvalidParams() {
        String result = operation.compute("AB65A");
        assertEquals("",result);

        result = operation.compute("");
        assertEquals("",result);

        result = operation.compute(null);
        assertEquals("",result);

    }

    @Test
    public void testComputePerf() {
        LocalTime startTime = LocalTime.now();

        for(int i=0; i<=100000; i++){
            operation.compute(String.valueOf(i*100));
        }

        LocalTime endTime = LocalTime.now();
        Long executionTime = (startTime.until(endTime, ChronoUnit.MILLIS));
        System.out.println( "Execution time = " + executionTime + " milliseconds");
        assertTrue(executionTime<2000);

    }
}