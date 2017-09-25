package server.response;

import java.io.IOException;

import server.Resource;
import server.request.Request;

public class ResponseHelper {

	public static boolean createFile(Request request, Resource resource) {
		return true;
	}

	public static boolean deleteFile(Request request, Resource resource) {
		return true;
	}

	public static boolean executeScript(Request request, Resource resource) {
		try {
			Runtime.getRuntime().exec("command");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
