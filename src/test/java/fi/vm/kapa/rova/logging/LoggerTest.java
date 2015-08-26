package fi.vm.kapa.rova.logging;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoggerTest {

    @Test
    public void testMaskHetuEnding1() {
        String logString = "121212A123A";
        assertEquals(Logger.maskHetuEnding(logString), "121212AXXXX");
    } 

    @Test
    public void testMaskHetuEnding2() {
        String logString = "121212-123A asdf";
        assertEquals(Logger.maskHetuEnding(logString), "121212-XXXX asdf");
    } 

    @Test
    public void testMaskHetuEnding3() {
        String logString = "asdf 121212+123A";
        assertEquals(Logger.maskHetuEnding(logString), "asdf 121212+XXXX");
    } 

    @Test
    public void testMaskHetuEnding4() {
        String logString = "121212A123ABC";
        assertEquals(Logger.maskHetuEnding(logString), "121212A123ABC");
    } 

}

