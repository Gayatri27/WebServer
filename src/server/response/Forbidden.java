package server.response;

import server.Resource;

public class Forbidden extends Response {

	public Forbidden(Resource resource) {
		super(resource);
		statusLine = "HTTP/1.1 403 Forbidden";
	}

}
