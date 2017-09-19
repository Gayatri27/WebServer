package Config;

import java.io.File;

public class ConfigurationReader {

	File file;

	public ConfigurationReader(String fileName) {
		file = new File(fileName);
	}

	public boolean hasMoreLines() {
		return false;
	}

	public String nextLine() {
		return null;
	}

	public void load() {

	}
}
    
}
