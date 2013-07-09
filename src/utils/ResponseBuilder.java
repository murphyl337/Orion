package utils;


public class ResponseBuilder {
	public static final String NEWLINE = "\r\n";

	public String generateStatus(int code) {
		String status = "";
		switch(code){
		case 200:
			status = "HTTP/1.1 " + code + " OK" + NEWLINE;
			break;
		
		case 404:
			status = "HTTP/1.1 " + code + " Not Found" + NEWLINE;
			break;
			
		case 500:
			status = "HTTP/1.1 " + code + " Internal Server Error" + NEWLINE;
		}
		return status;
	}

}
