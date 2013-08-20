package com.cengage.apprentice.app.utils;

import java.io.FileNotFoundException;

import com.cengage.apprentice.app.main.OrionRequest;
import com.cengage.apprentice.app.response.OrionResponse;

public interface Respondable {
    OrionResponse respond(OrionRequest request) throws FileNotFoundException;
}
