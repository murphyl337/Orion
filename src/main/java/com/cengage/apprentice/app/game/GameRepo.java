package com.cengage.apprentice.app.game;

import java.util.HashMap;

import source.TTT.Game;

public class GameRepo {
    private static int gameId;
    private static HashMap<Integer, Game> repo;

    public GameRepo(){
        gameId = 1;
        repo = new HashMap<Integer, Game>();
    }
    
    public void store(final Game game) {
        game.setId(gameId);
        repo.put(gameId, game);
        gameId++;
    }

    public int getNextGameId() {
        return gameId;
    }

    public Game fetch(final int id) {
        return repo.get(id);
    }
    
}
