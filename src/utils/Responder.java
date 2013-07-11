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
		return new StatusCodeResponse(200);
		
//		FileChecker fileChecker = new FileChecker(root);
//		if(fileChecker.fileExists(request.getRoute())){
//			return new FileResponse(root, request.getRoute());
//		}
//		return null;
	}
	
	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}


}
