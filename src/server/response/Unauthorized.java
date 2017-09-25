package server.response;

import server.Constants;
import server.Resource;
import server.Utils;
import server.request.Request;

public class Unauthorized extends Response {

	public Unauthorized(Request request, Resource resource) {
		super(request, resource);
	}

	@Override
	public int getCode() {
		return 401;
	}

	@Override
	public String getReasonPhrase() {
		return ResponseCode._401.getResponseCodeStr();
	}

	@Override
	public String getHeaders() {
		headers.put(Constants.CONTENT_LENGTH, String.valueOf(getBody().length()));
		return Utils.getHeaderString(headers);
	}

	@Override
	public String getBody() {
		return "<h1>401</h1><h2>Unauthorized</h2>";
	}
}
