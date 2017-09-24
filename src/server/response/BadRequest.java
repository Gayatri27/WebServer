package server.response;

import server.Constants;
import server.Resource;

public class BadRequest extends Response {

	public BadRequest(Resource resource) {
		super(resource);

	}

	@Override
	public int getCode() {
		return 400;
	}

	@Override
	public String getReasonPhrase() {
		return ResponseCode._400.getResponseCodeStr();
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
		return "<h1>400</h1><h2>Bad Request</h2>";
	}
}
