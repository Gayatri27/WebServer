package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Utils {

	public static String readFile(String fileLocation) {
		File file = new File(fileLocation);
		if(!file.exists())
			return null;
		
		String returnStr = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileLocation));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			returnStr = sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return returnStr;
	}
	
	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static String getHeaderString(Map<String, String> headers) {
		StringBuilder sb = new StringBuilder();
		for (String key : headers.keySet()) {
			sb.append(key + ": " + headers.get(key) + "\n");
		}
		sb.append("\n");
		return sb.toString();
	}
}
