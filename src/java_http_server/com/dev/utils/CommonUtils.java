package java_http_server.com.dev.utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CommonUtils {
	
	
	public static void safeClose(Socket socket) {
		try {
			if (socket != null && !socket.isClosed()) {
				socket.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void safeClose(ServerSocket serverSocket) {
		try {
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
