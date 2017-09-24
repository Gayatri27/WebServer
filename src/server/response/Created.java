package server.response;

import server.Constants;
import server.Utils;
import server.request.Request;

public class Created extends Response {

	public Created(Request request) {
		super(request);
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
		return Utils.getHeaderString(headers);
	}

	@Override
	public String getBody() {
		return "<h1>201</h1><h2>Created</h2>";
	}
}
