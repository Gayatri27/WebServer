package server.response;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import server.Constants;
import server.Resource;
import server.Utils;
import server.request.Request;

public class OK extends Response {

	Request request;
	Resource resource;
	boolean isFile = false;
	String filePath = null;

	public OK(Request request, Resource resource) {
		super(request, resource);
		this.request = request;
		this.resource = resource;
	}

	public void setFile(boolean value) {
		isFile = value;
	}

	public void setFilePath(String path) {
		filePath = path;
	}

	@Override
	public int getCode() {
		return 200;
	}

	@Override
	public String getReasonPhrase() {
		return ResponseCode._200.getResponseCodeStr();
	}

	@Override
	public String getHeaders() {
		headers.put(Constants.CONTENT_LENGTH, String.valueOf(getBody().length()));
		return Utils.getHeaderString(headers);
	}

	@Override
	public String getBody() {
		if (isFile)
			return Utils.readFile(filePath);
		return "<h1>200</h1><h2>OK</h2>";
	}

	@Override
	public byte[] getData() {
		if (isFile) {
			Path path = Paths.get(filePath);
			try {
				return Files.readAllBytes(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean isFile() {
		return isFile;
	}
}
