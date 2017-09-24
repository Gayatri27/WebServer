package server.response;

public enum ResposeCode {

	_200("OK"), _201("Created"), _204("No Content"), _304("Not Modified"), _400("Bad Request"), _401(
			"Unauthorized"), _403("Forbidden"), _404("Not Found"), _500("Internal Server Error");

	private String responseCode;

	ResposeCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseCode() {
		return responseCode;
	}
}
