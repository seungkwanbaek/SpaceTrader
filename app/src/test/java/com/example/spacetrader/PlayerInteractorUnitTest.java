package com.example.spacetrader;

import com.example.spacetrader.entities.Player;
import com.example.spacetrader.entities.SolarSystem;
import com.example.spacetrader.model.Repository;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PlayerInteractorUnitTest {
    @Test
    public void playerDoesExist() {

        Repository tempRepo = new Repository();

        ArrayList<Integer> skillPoints = new ArrayList<>();
        skillPoints.add(4);
        skillPoints.add(4);
        skillPoints.add(4);
        skillPoints.add(4);

        SolarSystem solar = new SolarSystem();
        Player tempPlayer = new Player("BobWater", "Beginner", skillPoints, solar);

        tempRepo.addPlayer(tempPlayer);

        List<Player> playerList = tempRepo.getAllPlayers();
        String expectedName = "BobWater";
        String returnedName = playerList.get(0).getUserName();
        Assert.assertEquals(expectedName, returnedName);
    }
}