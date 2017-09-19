
package Config;


import web_server.ServerConstants;
import web_server.ServerUtils; 

public class htaccess {

	String userFile;
	String authType;
	String authName;
	String require;

	public void load() {
		String file = ServerUtils.readFile(ServerConstants.HTACCESS_FILE_LOCATION);
		String[] lines = file.split("\\r?\\n");
		for (String line : lines) {
			String[] content = line.split(" ");
			String value = content[1];
			value = value.replace("\"", "").trim();
			setValues(content[0], value);
		}
	}

	public boolean isAuthorized(String username, String password) {
		return false;
	}

	private void setValues(String key, String value) {
		switch (key) {
		case ServerConstants.AUTH_USER_FILE_TAG:
			userFile = value;
			break;
		case ServerConstants.AUTH_TYPE_TAG:
			authType = value;
			break;
		case ServerConstants.AUTH_NAME_TAG:
			authName = value;
			break;
		case ServerConstants.REQUIRE_TAG:
			require = value;
			break;
		default:
			System.out.println("Invalid");
			break;
		}
	}
}

