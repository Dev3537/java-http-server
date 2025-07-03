package java_http_server.com.dev.response;

import java.io.BufferedWriter;
import java.io.IOException;

public interface ResponseProvider {

	BufferedWriter response(BufferedWriter writer) throws IOException;

}
