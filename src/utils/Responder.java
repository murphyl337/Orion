package utils;

import main.OrionRequest;
import response.FileResponse;
import response.OrionResponse;
import response.StatusCodeResponse;

public class Responder {

	private String root;

	public Responder(String root) {
		this.setRoot(root);
	}

	public OrionResponse respond(OrionRequest request) {
		FileChecker fileChecker = new FileChecker(root);
		if(fileChecker.fileExists(request.getRoute())){
			System.out.println("FOUND FILE");
			return new FileResponse(root, request.getRoute());
		}
		return new StatusCodeResponse(404);
	}
	
	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}


}
