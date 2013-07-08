package main;

public class OrionRequest {
	private String[] header;
	private String rawRoute;
	private String route;
	private String method;

	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public String getRawRoute() {
		return rawRoute;
	}

	public void setRawRoute(String rawRoute) {
		this.rawRoute = rawRoute;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

}
