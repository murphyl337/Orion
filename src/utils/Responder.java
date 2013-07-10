package utils;

import response.OrionResponse;
import response.StatusCodeResponse;
import main.OrionRequest;

public class Responder {

	private String root;

	public Responder(String docRoot) {
		this.setRoot(docRoot);
	}

	public OrionResponse respond(OrionRequest request) {
		return new StatusCodeResponse(200);
	}
	
	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}


}
