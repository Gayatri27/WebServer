package server.response;

import server.Constants;
import server.Resource;

public class NotModified extends Response {

	public NotModified(Resource resource) {
		super(resource);

	}

	@Override
	public int getCode() {
		return 304;
	}

	@Override
	public String getReasonPhrase() {
		return ResponseCode._304.getResponseCodeStr();
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
		return "<h1>304</h1><h2>Not Modified</h2>";
	}
}
