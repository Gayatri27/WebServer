package server.response;

import server.Resource;

public class BadRequest extends Response {

	public BadRequest(Resource resource) {
		super(resource);
		statusLine = "HTTP/1.1 400 Bad Request";
	}

}
