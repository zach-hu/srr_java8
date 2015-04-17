package com.tsa.puridiom.util;
/*
 * PunchOutRequest.java
 *
 * Original Created on January 30, 2002
 * Revised for Hilton on June 8, 2004
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.jdom.Document;

public class WSProxyCall {
	public Object wsProxyCall(String targetUrl, Map parameters) throws Exception{
		URL url = null;
		HttpURLConnection connection = null;
		Object object = null ;

		try {
			url = new URL(targetUrl );
		}
		catch(Exception e) {
			throw e;
		}

		try {

			if (url.openConnection() instanceof HttpsURLConnection)
			{
				connection = (HttpsURLConnection) url.openConnection();
			} else
			{
				connection = (HttpURLConnection) url.openConnection();
			}

			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST") ;
			connection.setUseCaches(false);
			connection.setAllowUserInteraction(false);
			connection.setRequestProperty("Content-Type","application/octet-stream");

			ObjectOutputStream oos = new	ObjectOutputStream(connection.getOutputStream());
			oos.writeObject(parameters) ;
			oos.flush();
			oos.close();

			// Read input stream
			ObjectInputStream ois = new ObjectInputStream(connection.getInputStream());
	        object = ois.readObject();
	        ois.close();

	        oos = null;
	        ois = null;
		}

		catch(IOException e) {
			throw e;
		}

		return object ;
	}
}