package server;

import server.conf.Htaccess;
import server.conf.Htpassword;

public class Main {

	public static void main(String[] args) {
		Htaccess htaccess = new Htaccess();
		htaccess.load();

		Htpassword htpassword = new Htpassword();
		htpassword.load(htaccess.getUserFile());
		
		System.out.println("Test");
	}
}
