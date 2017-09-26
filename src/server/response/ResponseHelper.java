package server.response;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import server.Constants;
import server.Resource;
import server.request.Request;

public class ResponseHelper {

	public static boolean createFile() {
		File file = new File(Constants.TEST_FILE_PATH);
		if(file.mkdirs()) {
			try {
				file.createNewFile();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static boolean deleteFile() {
		File file = new File(Constants.TEST_FILE_PATH);
		if(file.exists()) {
			file.delete();
			return true;
		}
		return false;
	}

	public static String executeScript(Request request, Resource resource) {
		try {
			String scriptPath = resource.getAbsolutePath(true);
			Process process = Runtime.getRuntime().exec(scriptPath);
			process.waitFor();
			StringBuffer output = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = "";
			while((line = reader.readLine()) != null)
				output.append(line + "\n");
			return output.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}
}
 