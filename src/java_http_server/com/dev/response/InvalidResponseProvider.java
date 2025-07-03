package java_http_server.com.dev.response;

import java.io.BufferedWriter;
import java.io.IOException;

public class InvalidResponseProvider implements ResponseProvider {

	@Override
	public BufferedWriter response(BufferedWriter writer) throws IOException {
		String requestBody = "<html><h1>Sorry,Invalid URL!</h1><h2>Try /hello OR /about</h2></html>";
		writer.write("HTTP/1.1 404 Not Found\r\n");
		writer.write("Content-Type: text/html\r\n");
		writer.write("Content-Length: " + requestBody.getBytes().length + "\r\n");
		writer.write("\r\n");
		writer.write(requestBody);
		return writer;
	}

}
