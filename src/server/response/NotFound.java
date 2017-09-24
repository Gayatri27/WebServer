package server.response;

import server.Constants;
import server.Resource;

public class NotFound extends Response {

	public NotFound(Resource resource) {
		super(resource);

	}

	@Override
	public int getCode() {
		return 404;
	}

	@Override
	public String getReasonPhrase() {
		return ResponseCode._404.getResponseCodeStr();
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
		return "<h1>404</h1><h2>Not Found</h2>";
	}
}
