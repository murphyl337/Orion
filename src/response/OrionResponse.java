package response;

import java.io.OutputStream;
import java.io.Serializable;

public interface OrionResponse extends Serializable{
	void setHeader();
	void setBody();
	void write(OutputStream output);
	String getHeader();
	Object getBody();
}
