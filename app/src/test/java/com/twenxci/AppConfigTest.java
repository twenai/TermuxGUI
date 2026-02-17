package com.twenxci;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AppConfigTest {

    @Test
    public void applicationId_isExpected() {
        assertEquals("com.twenxci", BuildConfig.APPLICATION_ID);
    }
}
