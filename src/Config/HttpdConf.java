
package Config;

import java.util.HashMap;
import java.util.Map;

import web_server.ServerConstants;
import web_server.ServerUtils;


public class HttpdConf {
    
    public Map<String, String> aliases;
	public Map<String, String> scriptAliases;

	public String serverRoot = null;
	public String documentRoot = null;
	public int port = -1;
	public String logFile = null;

	private String file = null;

	public HttpdConf(String fileName) {
		file = ServerUtils.readFile(fileName);
	}

	public void load() {
		String[] lines = file.split("\\r?\\n");
		for (String line : lines) {
			String[] content = line.split(" ");
			String value1 = content[0].trim();
			String value2 = content[1];
			value2 = value2.replace("\"", "").trim();
			String value3 = null;
			if(content.length > 2) {
				value3 = content[2];
				value3 = value3.replace("\"", "").trim();
			}
			setValues(value1, value2, value3);
		}
	}

	private void setValues(String key, String value1, String value2) {
		switch (key) {
		case ServerConstants.SERVER_ROOT_TAG:
			serverRoot = value1;
			break;
		case ServerConstants.DOCUMENT_ROOT_TAG:
			documentRoot = value1;
			break;
		case ServerConstants.PORT_TAG:
			port = Integer.parseInt(value1);
			break;
		case ServerConstants.LOG_FILE_TAG:
			logFile = value1;
			break;
		case ServerConstants.SCRIPT_ALIAS_TAG:
			if (scriptAliases == null)
				scriptAliases = new HashMap<String, String>();
			scriptAliases.put(value1, value2);
			break;
		case ServerConstants.ALIAS_TAG:
			if (aliases == null)
				aliases = new HashMap<String, String>();
			aliases.put(value1, value2);
			break;
		default:
			System.out.println("Invalid");
			break;
		}
	}
    
}
