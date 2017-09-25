package server.response;

import server.Constants;
import server.Utils;
import server.request.Request;

public class NotModified extends Response {

	public NotModified(Request request) {
		super(request);
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
		return Utils.getHeaderString(headers);
	}

	@Override
	public String getBody() {
		return "<h1>304</h1><h2>Not Modified</h2>";
	}
}
