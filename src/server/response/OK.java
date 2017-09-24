package server.response;

import server.Resource;

public class OK extends Response {

	public OK(Resource resource) {
		super(resource);
		statusLine = "HTTP/1.1 200 OK";
	}

}
