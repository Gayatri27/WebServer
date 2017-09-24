package server.response;

import server.Constants;
import server.conf.MimeTypes;
import server.request.Request;

public class Created extends Response {

	public Created(MimeTypes mimes, Request request) {
		super(mimes, request);
	}

	@Override
	public int getCode() {
		return 201;
	}

	@Override
	public String getReasonPhrase() {
		return ResponseCode._201.getResponseCodeStr();
	}

	@Override
	public String getHeaders() {
		headers.put(Constants.CONTENT_LENGTH, String.valueOf(getBody().length()));
		StringBuilder sb = new StringBuilder();
		for (String key : headers.keySet()) {
			sb.append(key + ": " + headers.get(key) + "\n");
		}
		sb.append("\n");
		return sb.toString();
	}

	@Override
	public String getBody() {
		return "<h1>201</h1><h2>Created</h2>";
	}
}
