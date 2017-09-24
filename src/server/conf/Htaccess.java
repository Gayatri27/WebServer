package server.conf;

import server.Constants;
import server.Utils;

public class Htaccess {

	HttpdConf configuration;

	String userFile;
	String authType;
	String authName;
	String require;

	boolean isAvailable = true;

	public Htaccess(HttpdConf configuration) {
		this.configuration = configuration;
	}

	public void load() {
		String fileLocation = configuration.accessFileName == null ? Constants.HTACCESS_FILE_LOCATION
				: configuration.getDocumentRoot() + Constants.CONFIG_FILES_LOCATION + configuration.accessFileName;
		String file = Utils.readFile(fileLocation);

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
