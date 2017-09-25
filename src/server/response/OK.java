package server.response;

import server.Constants;
import server.Utils;
import server.request.Request;

public class OK extends Response {

	public OK(Request request) {
		super(request);
	}

	@Override
	public int getCode() {
		return 200;
	}

	@Override
	public String getReasonPhrase() {
		return ResponseCode._200.getResponseCodeStr();
	}

	@Override
	public String getHeaders() {
		headers.put(Constants.CONTENT_LENGTH, String.valueOf(getBody().length()));
		return Utils.getHeaderString(headers);
	}

	@Override
	public String getBody() {
		return "<h1>200</h1><h2>OK</h2>";
	}
}
