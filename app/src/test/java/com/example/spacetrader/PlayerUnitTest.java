package com.example.spacetrader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.example.spacetrader.entities.Player;
import com.example.spacetrader.entities.SolarSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.spacetrader.entities.ShipType.Gnat;
import static org.junit.Assert.*;

/**
 * JUnit test for the Player's set Skill points method
 *
 * @author Jingyang Sui
 */
public class PlayerUnitTest {
    Player player;
    @Before
    public void pretest() {
        ArrayList<Integer> sp = new ArrayList<>(4);
        sp.add(16);
        sp.add(0);
        sp.add(0);
        sp.add(0);
        SolarSystem ss = new SolarSystem();
        player = new Player("ABC", "beginner", sp, ss);
    }

    @Test
    public void setSkillPointsTest() {
        Assert.assertEquals(16, (long) player.getSkillPoint("Pilot"));
        Assert.assertEquals(0, (long) player.getSkillPoint("Fighter"));
        Assert.assertEquals(0, (long) player.getSkillPoint("Trader"));
        Assert.assertEquals(0, (long) player.getSkillPoint("Engineer"));
        Map<String, Integer> sp = new HashMap<>();
        sp.put("Pilot", 4);
        sp.put("Fighter", 3);
        sp.put("Trader", 5);
        sp.put("Engineer", 4);
        player.setSkillPoints(sp);
        Assert.assertEquals(4, (long) player.getSkillPoint("Pilot"));
        Assert.assertEquals(3, (long) player.getSkillPoint("Fighter"));
        Assert.assertEquals(5, (long) player.getSkillPoint("Trader"));
        Assert.assertEquals(4, (long) player.getSkillPoint("Engineer"));
    }
}
