package server;

import java.io.File;

import server.conf.HttpdConf;

public class Resource {

	String uri;
	HttpdConf httpdConf;

	public Resource(String uri, HttpdConf config) {
		this.uri = uri;
		this.httpdConf = config;
	}

	private String getAbsolutePath() {
		String absolutePath = resolveUriPath();
		return isFile(absolutePath) ? absolutePath : appendDirIndex(absolutePath);
	}

	public boolean isProtected() {
		File file = new File(httpdConf.getServerRoot() + Constants.CONFIG_FILES_LOCATION + "/_.htaccess");
		return file.exists() ? true : false;
	}

	private boolean isUriAliased() {
		return httpdConf.getAliases().containsKey(uri) ? true : false;
	}

	public boolean isUriScriptAliased() {
		return httpdConf.getScriptAliases().containsKey(uri) ? true : false;
	}

	private boolean isFile(String path) {
		File file = new File(path);
		return file.isFile() ? true : false;
	}

	private String resolveUriPath() {
		return httpdConf.getDocumentRoot() + getUnmodifiedUri();
	}

	private String getUnmodifiedUri() {
		if (isUriAliased()) {
			return httpdConf.getAliases().get(uri);
		} else if (isUriScriptAliased()) {
			return httpdConf.getScriptAliases().get(uri);
		}
		return uri;
	}

	private String appendDirIndex(String path) {
		String content[] = path.split("/");
		String indexFile = httpdConf.getDirectoryIndex();
		content[content.length - 1] = indexFile == null ? Constants.DEFAULT_INDEX_FILE : indexFile;
		StringBuilder builder = new StringBuilder();
		for (String str : content) {
			if (builder.length() > 0)
				builder.append("/");
			builder.append(str);
		}
		return builder.toString();
	}

	public HttpdConf getHttpConf() {
		return httpdConf;
	}
}
