package java_http_server.com.dev.response;

import java.io.BufferedWriter;
import java.io.IOException;

public class AboutResponseProvider implements ResponseProvider {

	@Override
	public BufferedWriter response(BufferedWriter writer) throws IOException {
		String requestBody = "<html><h1>About Page</h1><h2>This is an application I created to learn Http Server Mechanism</h2></html>";
		writer.write("HTTP/1.1 200 OK\r\n");
		writer.write("Content-Type: text/html\r\n");
		writer.write("Content-Length: " + requestBody.getBytes().length + "\r\n");
		writer.write("\r\n");
		writer.write(requestBody);
		return writer;
	}

}
