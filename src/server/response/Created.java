package server.response;

import server.Resource;

public class Created extends Response {

	public Created(Resource resource) {
		super(resource);
		statusLine = "HTTP/1.1 201 Created";
	}

}
