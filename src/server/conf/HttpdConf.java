package server.conf;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import server.Constants;
import server.Utils;

public class HttpdConf {
	Map<String, String> aliases;
	Map<String, String> scriptAliases;

	String serverRoot = null;
	String documentRoot = null;
	int port = -1;
	String logFile = null;
	String accessFileName = null;
	String directoryIndex = null;

	String file = null;

	public HttpdConf(String fileName) {
		URL path = HttpdConf.class.getResource(fileName);
		file = Utils.readFile(path.getPath());
	}

	public void load() {
		String[] lines = file.split("\\r?\\n");
		for (String line : lines) {
			String[] content = line.split(" ");
			String value1 = content[0].trim();
			String value2 = content[1];
			value2 = value2.replace("\"", "").trim();
			String value3 = null;
			if (content.length > 2) {
				value3 = content[2];
				value3 = value3.replace("\"", "").trim();
			}
			setValues(value1, value2, value3);
		}
	}

	private void setValues(String key, String value1, String value2) {
		switch (key) {
		case Constants.SERVER_ROOT_TAG:
			serverRoot = value1;
			break;
		case Constants.DOCUMENT_ROOT_TAG:
			documentRoot = value1;
			break;
		case Constants.PORT_TAG:
			port = Integer.parseInt(value1);
			break;
		case Constants.LOG_FILE_TAG:
			logFile = value1;
			break;
		case Constants.SCRIPT_ALIAS_TAG:
			if (scriptAliases == null)
				scriptAliases = new HashMap<String, String>();
			scriptAliases.put(value1, value2);
			break;
		case Constants.ALIAS_TAG:
			if (aliases == null)
				aliases = new HashMap<String, String>();
			aliases.put(value1, value2);
			break;
		case Constants.ACCESS_FILE_NAME:
			accessFileName = value1;
			break;
		case Constants.DIRECTORY_INDEX:
			directoryIndex = value1;
			break;
		default:
			System.out.println("Invalid");
			break;
		}
	}

	public int getPort() {
		return port;
	}

	public String getServerRoot() {
		return serverRoot;
	}

	public String getDocumentRoot() {
		return documentRoot;
	}

	public Map<String, String> getAliases() {
		return aliases;
	}

	public Map<String, String> getScriptAliases() {
		return scriptAliases;
	}
	
	public String getLogFilePath() {
		return logFile;
	}
	
	public String getAccessFileName() {
		return accessFileName;
	}
	
	public String getDirectoryIndex() {
		return directoryIndex;
	}
}
