package server.response;

import server.Resource;

public class NotFound extends Response {

	public NotFound(Resource resource) {
		super(resource);
		statusLine = "HTTP/1.1 404 Not Found";
	}

}
