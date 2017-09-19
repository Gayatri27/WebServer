
package Config;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import web_server.ServerConstants;
import web_server.ServerUtils;

public class htpasswd {

	private Map<String, String> users;

	public void load() {
		String file = ServerUtils.readFile(ServerConstants.HTPASSWORD_FILE_LOCATION);
		String[] lines = file.split("\\r?\\n");
		for (String line : lines) {
			if (users == null)
				users = new HashMap<String, String>();
			String[] content = line.split(":");
			users.put(content[0], content[1].replace("{SHA}", "").trim());
		}
	}

	public boolean isAuthorized(String authInfo) {
		// authInfo is provided in the header received from the client
		// as a Base64 encoded string.
		String credentials = new String(Base64.getDecoder().decode(authInfo), Charset.forName("UTF-8"));
		// The string is the key:value pair username:password
		String[] content = credentials.split(":");
		return isAuthorized(content[0], content[1]);
	}

	public boolean isAuthorized(String username, String password) {
		if (users.containsKey(username)) {
			return users.get(username).equals(encryptPassword(password));
		}
		return false;
	}

	private String encryptPassword(String password) {
		try {
			MessageDigest mDigest = MessageDigest.getInstance("SHA-1");
			byte[] result = mDigest.digest(password.getBytes());
			return Base64.getEncoder().encodeToString(result);
		} catch (Exception e) {
			return "";
		}
	}
}
