package server.response;

import server.Constants;
import server.conf.MimeTypes;
import server.request.Request;

public class Forbidden extends Response {

	public Forbidden(MimeTypes mimes, Request request) {
		super(mimes, request);
	}

	@Override
	public int getCode() {
		return 403;
	}

	@Override
	public String getReasonPhrase() {
		return ResponseCode._403.getResponseCodeStr();
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
		return "<h1>403</h1><h2>Forbidden</h2>";
	}
}
