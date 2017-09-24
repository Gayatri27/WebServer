package server.response;

import server.Resource;

public class NotModified extends Response {

	public NotModified(Resource resource) {
		super(resource);
		statusLine = "HTTP/1.1 304 Not Modified";
	}

}
