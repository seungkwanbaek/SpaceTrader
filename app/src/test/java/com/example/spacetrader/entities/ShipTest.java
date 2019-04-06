package com.example.spacetrader.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.example.spacetrader.entities.Ship;

import java.util.HashMap;

import static com.example.spacetrader.entities.ShipType.Gnat;
import static org.junit.Assert.*;

/**
 * JUnit test for the ship's loadCargo function.
 *
 * @author Bryan
 */
public class ShipTest {
    Ship ship;
    @Before
    public void pretest() {
        ship = new Ship(Gnat);
    }

    @Test
    public void loadCargo() {
        ship.loadCargo(null, 5);
        Assert.assertEquals(0, ship.getTotalCargoAmount());

        ship.loadCargo("Water", -1);
        Assert.assertEquals(0, ship.getTotalCargoAmount());

        ship.loadCargo("Water", 0);
        Assert.assertEquals(0, ship.getTotalCargoAmount());

        ship.loadCargo("Water", 2);
        Assert.assertEquals(2, ship.getTotalCargoAmount());

        ship.loadCargo("Water", 6);
        Assert.assertEquals(8, ship.getTotalCargoAmount());
    }
}