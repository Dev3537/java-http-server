package java_http_server.com.dev.application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Set;

import java_http_server.com.dev.response.AboutResponseProvider;
import java_http_server.com.dev.response.HelloResponseProvider;
import java_http_server.com.dev.response.InvalidResponseProvider;
import java_http_server.com.dev.utils.CommonUtils;

public class ServiceRequest implements Runnable {

	private Socket socket;

	private Set<Socket> provisionedSockets;

	public ServiceRequest(Socket connection) {
		socket = connection;
	}

	public ServiceRequest(Socket connection, Set<Socket> provisionedSockets) {
		socket = connection;
		this.provisionedSockets = provisionedSockets;
	}

	@Override
	public void run() {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
			String inputLine;
			String requestPath = null;
			int requestLineCounter = 0;
			while (((inputLine = reader.readLine()) != null) && !inputLine.equals("")) {
				if (requestLineCounter == 0) {
					requestPath = inputLine;
					requestLineCounter++;
				}
				System.out.println(inputLine);

			}
			returnAppropriateResponse(writer, requestPath);

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			CommonUtils.safeClose(socket);
			provisionedSockets.remove(socket);
		}
	}

	private void returnAppropriateResponse(BufferedWriter writer, String requestPath) {
		try {
			if (requestPath != null) {
				String path = requestPath.split(" ")[1];
				if (path.equals("/hello")) {
					new HelloResponseProvider().response(writer);
				} else if (path.equals("/about")) {
					new AboutResponseProvider().response(writer);
				} else if (path.equals("/kill")) {
					// This is a temporary kill switch to be used while development
					System.exit(0);
				} else {
					new InvalidResponseProvider().response(writer);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
