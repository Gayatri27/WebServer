package server.response;

import server.Resource;

public class Response {

	int code;
	String reasonPhrase;
	Resource resource;
	
	String statusLine = "HTTP/1.1 200 SUCCESS";

	public Response(Resource resource) {
		this.resource = resource;
	}

	public String writeString() {
		String body = "<h1>404</h1> <h2>Mayuresh Raut</h2>";
		String str = statusLine + "\n"
				+ "Date: Todays Date\n"
				+ "Server: Mayuresh Server\n"
				+ "Content-Type: text/html\n"
				+ "Content-Length: " + body.length() + "\n"
				+ "Message-Body: " + body;
		return str;
	}
}
