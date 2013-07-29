package com.cengage.apprentice.app.response;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

public interface OrionResponse extends Serializable{
	void setHeader();
	void setBody() throws FileNotFoundException;
	void write(OutputStream output, Object body) throws IOException;
	String getHeader();
	Object getBody();
}
