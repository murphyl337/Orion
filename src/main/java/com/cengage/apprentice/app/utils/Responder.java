package com.cengage.apprentice.app.utils;

import com.cengage.apprentice.app.main.OrionRequest;
import com.cengage.apprentice.app.response.OrionResponse;
import com.cengage.apprentice.app.response.StatusCodeResponse;

public class Responder {

	@SuppressWarnings("unused")
	private String root;

	public Responder(String root) {
		this.root = root;
	}

	public OrionResponse respond(OrionRequest request) {
		//FileChecker fileChecker = new FileChecker(root);
		if(request.getRoute().equals("/"))
			return new StatusCodeResponse(200);
		return new StatusCodeResponse(404);
	}
}
