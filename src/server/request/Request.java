package server.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import server.Constants;
import server.Verb;

public class Request {

	String uri;
	String body;
	String verb;
	String httpVersion;
	Map<String, String> headers;

	boolean isValid = true;

	public Request(BufferedReader in) {
		String line;
		try {
			line = in.readLine();
			parseVerbUriAndVersion(line);

			isValid = isRequestValid(line);
			if (!isValid)
				return;

			while (!line.isEmpty()) {
				line = in.readLine();
				parseHeader(line);
			}

			// parseBody
			if (headers.containsKey(Constants.CONTENT_LENGTH)) {
				int length = Integer.valueOf(headers.get(Constants.CONTENT_LENGTH));
				char[] buffer = new char[length];
				in.read(buffer, 0, length);
				body = new String(buffer, 0, buffer.length);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean isRequestValid(String line) {
		boolean isVersionValid = httpVersion.equals(Constants.HTTP_VERSION_1_0)
				|| httpVersion.equals(Constants.HTTP_VERSION_1_1);
		if (!isVersionValid || !Verb.isValid(verb))
			return false;
		return true;
	}

	private void parseVerbUriAndVersion(String input) {
		String[] content = input.split(" ");
		if (content.length == 3) {
			verb = content[0].trim();
			uri = content[1].trim();
			httpVersion = content[2].trim();
		}
	}

	private void parseHeader(String input) {
		if (headers == null)
			headers = new HashMap<String, String>();
		String[] content = input.split(": ");
		if (content.length == 2)
			headers.put(content[0].trim(), content[1].trim());
	}

	public String getUri() {
		return uri;
	}

	public boolean isValid() {
		return isValid;
	}

	public boolean isAuthHeaderAvailable() {
		return headers.containsKey(Constants.AUTHORIZATION) ? true : false;
	}

	public String getAuthInfo() {
		return headers.get(Constants.AUTHORIZATION);
	}

	public Verb getVerb() {
		return Verb.valueOf(verb);
	}

	public boolean isModified() {
		return !headers.containsKey(Constants.IF_MODIFIED_SINCE);
	}
}
