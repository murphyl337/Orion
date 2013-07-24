package com.cengage.apprentice.app.utils;

import com.cengage.apprentice.app.main.OrionRequest;
import com.cengage.apprentice.app.response.OrionResponse;
import com.cengage.apprentice.app.response.StatusCodeResponse;

public class Responder {

	private String root;

	public Responder(String root) {
		this.setRoot(root);
	}

	public OrionResponse respond(OrionRequest request) {
		FileChecker fileChecker = new FileChecker(root);
		if(request.getRoute().equals("/"))
			return new StatusCodeResponse(200);
		return new StatusCodeResponse(404);
	}
	
	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}


}
