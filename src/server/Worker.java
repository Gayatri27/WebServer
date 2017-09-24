package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

import server.conf.HttpdConf;
import server.conf.MimeTypes;
import server.request.Request;
import server.response.Response;
import server.response.ResponseFactory;

public class Worker extends Thread {

	Socket client;
	HttpdConf config;
	MimeTypes mimes;

	public HashMap<String, String> default_headers = new HashMap<String, String>();

	public Worker(Socket socket, HttpdConf config, MimeTypes mimes) {
		this.client = socket;
		this.config = config;
		this.mimes = mimes;
	}

	@Override
	public void run() {
		PrintWriter out = null;
		BufferedReader in = null;

		try {
			out = new PrintWriter(client.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			Request request = new Request(in);
			Resource resource = new Resource(request.getUri(), config);
			ResponseFactory responseFactory = new ResponseFactory();
			Response response = responseFactory.getResponse(request, resource);
			out.print(response.writeString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
