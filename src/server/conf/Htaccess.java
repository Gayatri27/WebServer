package server.conf;

import java.net.URL;

import server.Constants;
import server.Utils;

public class Htaccess {

	HttpdConf configuration;

	static String userFile;
	static String authType;
	static String authName;
	static String require;

	boolean isAvailable = true;

	public Htaccess(HttpdConf configuration) {
		this.configuration = configuration;
	}

	public void load() {
		URL path = Htaccess.class.getResource(Constants.HTACCESS_FILE_LOCATION);
		String file = Utils.readFile(path.getPath());

		if (file == null) {
			isAvailable = false;
			return;
		}

		String[] lines = file.split("\\r?\\n");
		for (String line : lines) {
			String[] content = line.split(" ");
			String value;
			if (content.length == 2)
				value = content[1];
			else {
				StringBuffer sb = new StringBuffer();
				for(int i = 1; i < content.length; i++)
					sb.append(content[i] + " ");
				value = sb.toString();
			}
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

	public String getAuthType() {
		return authType;
	}

	public String getAuthName() {
		return authName;
	}
}
