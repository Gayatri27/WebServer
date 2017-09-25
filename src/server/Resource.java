package server;

import java.io.File;

import server.conf.HttpdConf;

public class Resource {

	String uri;
	HttpdConf httpdConf;

	public Resource(String uri, HttpdConf config) {
		this.uri = uri;
		this.httpdConf = config;
		if(this.uri.length() == 1 && this.uri.equals("/")) {
			String accessFileName = httpdConf.getAccessFileName();
			this.uri = accessFileName == null ? Constants.DEFAULT_INDEX_FILE : accessFileName;
		}
	}

	public String getAbsolutePath() {
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

	public boolean isFile(String path) {
		File file = new File(path);
		return file.isFile() ? true : false;
	}

	private String resolveUriPath() {
		String unmodifiedUri = getUnmodifiedUri();
		String documentRoot = httpdConf.getDocumentRoot();
		if(unmodifiedUri.startsWith("/") & documentRoot.endsWith("/"))
			return documentRoot + unmodifiedUri.substring(1, uri.length());
		return documentRoot + unmodifiedUri;
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
