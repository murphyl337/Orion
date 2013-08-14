package com.cengage.apprentice.app.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cengage.apprentice.app.main.OrionRequest;
import com.cengage.apprentice.app.response.FileResponse;
import com.cengage.apprentice.app.response.OrionResponse;
import com.cengage.apprentice.app.utils.RequestParser;

public class GameControllerTest {
    GameController controller;
    
    @Before
    public void setup(){
        controller = new GameController();
    }
}
