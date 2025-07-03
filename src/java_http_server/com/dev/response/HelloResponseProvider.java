package java_http_server.com.dev.response;

import java.io.BufferedWriter;
import java.io.IOException;

public class HelloResponseProvider implements ResponseProvider {

	@Override
	public BufferedWriter response(BufferedWriter writer) throws IOException {
		String requestBody = "<html><h1>Hello World!</h1></html>";
		writer.write("HTTP/1.1 200 OK\r\n");
		writer.write("Content-Type: text/html\r\n");
		writer.write("Content-Length: " + requestBody.getBytes().length + "\r\n");
		writer.write("\r\n");
		writer.write(requestBody);
		return writer;
	}
}
