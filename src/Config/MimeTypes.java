
package Config;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import web_server.ServerUtils;

public class MimeTypes {

	public ArrayList<String> types;

	private String file = null;

	public MimeTypes(String fileName) {
		file = ServerUtils.readFile(fileName);
	}

	public void load() {
		String[] lines = file.split("\\r?\\n");
		for (String line : lines) {
			line.trim();
			if (line.startsWith("#"))
				continue;
			else {
				if (types == null)
					types = new ArrayList<String>();
				types.add(line);
				String[] content = line.split("\\s+");
				if (content.length == 1)
					types.add(content[0].trim());
				else {
					String[] extensionTypes = line.split("/");
					String value = extensionTypes[1].trim();
					String[] content1 = value.split("\\s+");
					if (content1.length == 1)
						types.add(extensionTypes[0] + "/" + content1[0]);
					else {
						for(String tmp: content1)
							types.add(extensionTypes[0] + "/" + tmp.trim());
					}
				}
			}
		}
	}

	public String lookup(String extension) {
		return null;
	}
}
