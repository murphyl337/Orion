package com.cengage.apprentice.app.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import source.TTT.Game;



public class GameRepoTest {
    GameRepo repo;
    Game game1;
    
    @Before 
    public void setup(){
        repo = new GameRepo();
        game1 = new Game(null, null, null);
    }
    
    @Test
    public void storeIncrementsGameId() throws Exception {
        repo.store(game1);
        assertEquals(repo.getNextGameId(), 2);
    }
    
    @Test
    public void storeSetsGameIdForGameBeingStored() throws Exception {
        repo.store(game1);
        assertEquals(game1.getId(), 1);
    }
    
    @Test
    public void fetchGetsGameRequestedById() throws Exception {
        repo.store(game1);
        final Game newGame = repo.fetch(1);
        
        assertTrue(game1.getId() == newGame.getId());
    }
}
