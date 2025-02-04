package com.check;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppTest 
{

    @Test
    public void sampleTest()
    {
        assertTrue( true );
    }

    @Test
    public void returnNumberTest()
    {
        App app = new App();
        int value = app.returnNumber();
        assertEquals(6, value);
    }
}
