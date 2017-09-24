package server.response;

import java.util.HashMap;
import java.util.Map;

import server.Constants;
import server.Resource;
import server.Utils;

public class Response implements IResponse {

	int code;
	String reasonPhrase;
	Resource resource;
	Map<String, String> headers;

	public Response(Resource resource) {
		this.resource = resource;
		if (headers == null)
			headers = new HashMap<String, String>();
		headers.put(Constants.DATE, Utils.getDate());
		headers.put(Constants.SERVER, Constants.SERVER_NAME);
		headers.put(Constants.CONTENT_TYPE, Constants.HTML_MIME_TYPE);
	}

	public String writeString() {
		return getStatusLine() + getHeaders() + getBody();
	}

	@Override
	public int getCode() {
		return 500;
	}

	@Override
	public String getReasonPhrase() {
		return ResponseCode._500.getResponseCodeStr();
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
		return "<h1>500</h1><h2>Internal Server Error</h2>";
	}

	private String getStatusLine() {
		return Constants.HTTP_VERSION_1_1 + " " + getCode() + " " + getReasonPhrase() + "\n";
	}
}
