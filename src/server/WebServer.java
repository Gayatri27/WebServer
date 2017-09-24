package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import server.conf.Htaccess;
import server.conf.Htpassword;
import server.conf.HttpdConf;
import server.conf.MimeTypes;

public class WebServer {

	HttpdConf configuration;
	MimeTypes mimeTypes;
	ServerSocket socket;

	private int clientNumber = 0;

	public void start() {
		configuration = new HttpdConf(Constants.HTTPD_CONF_FILE_LOCATION);
		configuration.load();

		mimeTypes = new MimeTypes(Constants.MIME_TYPES_FILE_LOCATION);
		mimeTypes.load();

		Htaccess htaccess = new Htaccess();
		htaccess.load();

		Htpassword htpassword = new Htpassword();
		htpassword.load(htaccess.getUserFile());

		try {
			socket = new ServerSocket(configuration.getPort());
			Socket client = null;
			while (true) {
				client = socket.accept();
				System.out.println("Client number: " + clientNumber++);
				new Thread(new Worker(client, configuration, mimeTypes)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
