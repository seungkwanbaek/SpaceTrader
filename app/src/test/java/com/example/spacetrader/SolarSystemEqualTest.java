package com.example.spacetrader;

import com.example.spacetrader.entities.SolarSystem;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * The SolarSystemEqualTest class
 */
public class SolarSystemEqualTest {
    /**
     * Test the equal method in SolarSystem
     */
    @Test
    public void equal_isCorrect() {
        SolarSystem s1 = new SolarSystem("s1", "test s1", "Agriculture", 2, 5);
        SolarSystem s2 = new SolarSystem("s1", "test s1", "Agriculture", 2, 5);
        SolarSystem s3 = new SolarSystem("s3", "test s3", "Agriculture", 2, 5);
        SolarSystem s4 = new SolarSystem("s4", "test s4", "Agriculture", 8, 5);
        SolarSystem s5 = new SolarSystem("s5", "test s5", "Industrial", 5, 10);

        assertEquals(s1.equals(s2), true);
        assertEquals(s1.equals(s3), true);
        assertNotEquals(s1.equals(s4), true);
        assertNotEquals(s1.equals(s5), true);
    }
}
