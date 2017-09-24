package server.response;

import server.Resource;

public class NoContent extends Response {

	public NoContent(Resource resource) {
		super(resource);
		statusLine = "HTTP/1.1 204 No Content";
	}

}
