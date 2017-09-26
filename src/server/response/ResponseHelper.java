package server.response;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import server.Constants;
import server.Resource;
import server.Utils;
import server.WebServer;
import server.conf.MimeTypes;
import server.request.Request;

public class ResponseHelper {

	public static boolean createFile() {
		File file = new File(Constants.TEST_FILE_PATH);
		if (file.mkdirs()) {
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
		if (file.exists()) {
			file.delete();
			return true;
		}
		return false;
	}

	public static String executeScript(Request request, Resource resource) {
		try {
			String scriptPath = request.getAbsolutePath();
			if(scriptPath == null) {
				scriptPath = resource.getAbsolutePath(true);
				request.setAbsolutePath(scriptPath);
			}
			Process process = Runtime.getRuntime().exec(scriptPath);
			process.waitFor();
			StringBuffer output = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null)
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

	public static boolean needsUpdate(Request request, Resource resource) {
		if (request.containsIsModified()) {
			String filePath = request.getAbsolutePath();
			if(filePath == null) {
				filePath = resource.getAbsolutePath(false);
				request.setAbsolutePath(filePath);
			}
			File file = new File(filePath);
			long fileTime = Utils.getTimeMilliseconds(Utils.getDate(file.lastModified()));
			long headerTime = Utils.getTimeMilliseconds(request.getLastModified());
			return fileTime != headerTime;
		}
		return true;
	}

	public static String getContentType(Request request) {
		String requestContentType = request.getContentType();
		if (requestContentType != null)
			return requestContentType;

		MimeTypes mimes = WebServer.getMimes();
		String mimeType = mimes.getMime(getUriExtension(request.getAbsolutePath()));
		return mimeType != null ? mimeType : Constants.DEFAULT_MIME_TYPE;
	}
	
	public static String getUriExtension(String uri) {
		if(uri != null) {
			String[] content = uri.split("\\.");
			int length = content.length;
			if(length > 1)
				return content[length - 1];
		}
		return null;
	}
}
