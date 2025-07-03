package java_http_server.com.dev.application;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java_http_server.com.dev.utils.CommonUtils;

public class ServerSocketImage {

	private ExecutorService executorService = Executors.newCachedThreadPool();
	private Set<Socket> availableSockets = ConcurrentHashMap.newKeySet();

	public void listen() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(7070);
			addShutdownHook(serverSocket);
			while (true) {
				Socket socket = serverSocket.accept();
				availableSockets.add(socket);
				executorService.submit(new ServiceRequest(socket, availableSockets));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!executorService.isShutdown()) {
				executorService.shutdown();
			}
			CommonUtils.safeClose(serverSocket);
		}

	}

	private void addShutdownHook(ServerSocket serverSocket) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				System.out.println("<---------------Shutdown Hook Called!-------------->");
				for (Socket socket : availableSockets) {
					CommonUtils.safeClose(socket);
				}
				CommonUtils.safeClose(serverSocket);
				if (!executorService.isShutdown()) {
					executorService.shutdown();
				}
			}

		});
	}
	

}
