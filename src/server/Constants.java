package server;

public class Constants {

	// file locations
	public static final String HTTPD_CONF_FILE_LOCATION = "src/server/conf/httpd.conf";
	public static final String MIME_TYPES_FILE_LOCATION = "src/server/conf/mime.types";
	public static final String HTACCESS_FILE_LOCATION = "src/server/conf/_.htaccess";
	public static final String HTPASSWORD_FILE_LOCATION = "src/server/conf/_.htpasswd";
	public static final String CONFIG_FILES_LOCATION = "config/assets";

	// httpd.conf tags
	public static final String SERVER_ROOT_TAG = "ServerRoot";
	public static final String DOCUMENT_ROOT_TAG = "DocumentRoot";
	public static final String PORT_TAG = "Listen";
	public static final String LOG_FILE_TAG = "LogFile";
	public static final String SCRIPT_ALIAS_TAG = "ScriptAlias";
	public static final String ALIAS_TAG = "Alias";

	// _.htaccess tags
	public static final String AUTH_USER_FILE_TAG = "AuthUserFile";
	public static final String AUTH_TYPE_TAG = "AuthType";
	public static final String AUTH_NAME_TAG = "AuthName";
	public static final String REQUIRE_TAG = "Require";

	// header identifiers
	public static final String CONTENT_LENGTH_TAG = "Content-Length";
	public static final String HTTP_VERSION_1_0 = "HTTP/1.0";
	public static final String HTTP_VERSION_1_1 = "HTTP/1.1";
	public static final String AUTHORIZATION = "Authorization";

	// resource constants
	public static final String DEFAULT_INDEX_FILE = "index.html";
}
