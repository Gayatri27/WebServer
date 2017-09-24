package server.response;

import server.Constants;
import server.Resource;

public class NoContent extends Response {

	public NoContent(Resource resource) {
		super(resource);

	}

	@Override
	public int getCode() {
		return 204;
	}

	@Override
	public String getReasonPhrase() {
		return ResponseCode._204.getResponseCodeStr();
	}

	@Override
	public String getHeaders() {
		headers.put(Constants.CONTENT_LENGTH, String.valueOf(getBody().length()));
		StringBuilder sb = new StringBuilder();
		for (String key : headers.keySet()) {
			sb.append(key + ": " + headers.get(key) + "\n");
		}
		return sb.toString();
	}

	@Override
	public String getBody() {
		return "<h1>204</h1><h2>No Content</h2>";
	}
}
