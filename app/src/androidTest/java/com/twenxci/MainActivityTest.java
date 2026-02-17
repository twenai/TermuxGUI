package com.twenxci;

import static androidx.test.core.app.ActivityScenario.launch;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Test
    public void launchesMainActivity() {
        launch(MainActivity.class).close();
    }
}
