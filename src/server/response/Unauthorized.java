package server.response;

import server.Resource;

public class Unauthorized extends Response {

	public Unauthorized(Resource resource) {
		super(resource);
		statusLine = "HTTP/1.1 401 Unauthorized";
	}

}
