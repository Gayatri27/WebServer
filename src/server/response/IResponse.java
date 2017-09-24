package server.response;

public interface IResponse {

	public int getCode();

	public String getReasonPhrase();

	public String getHeaders();
	
	public String getBody();
}
