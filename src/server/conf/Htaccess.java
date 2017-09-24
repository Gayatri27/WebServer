package server.conf;

import server.Constants;
import server.Utils;

public class Htaccess {

	String userFile;
	String authType;
	String authName;
	String require;

	boolean isAvailable = true;

	public void load() {
		String file = Utils.readFile(Constants.HTACCESS_FILE_LOCATION);

		if (file == null) {
			isAvailable = false;
			return;
		}

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
		case Constants.AUTH_USER_FILE_TAG:
			userFile = value;
			break;
		case Constants.AUTH_TYPE_TAG:
			authType = value;
			break;
		case Constants.AUTH_NAME_TAG:
			authName = value;
			break;
		case Constants.REQUIRE_TAG:
			require = value;
			break;
		default:
			System.out.println("Invalid");
			break;
		}
	}

	public String getUserFile() {
		return userFile;
	}

	public boolean isAvailable() {
		return isAvailable;
	}
}
