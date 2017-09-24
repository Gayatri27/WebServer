package server.response;

import server.Resource;

public class InternalServerError extends Response {

	public InternalServerError(Resource resource) {
		super(resource);
		statusLine = "HTTP/1.1 500 Internal Server Error";
	}

}
