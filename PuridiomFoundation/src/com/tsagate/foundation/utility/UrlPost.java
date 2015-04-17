/*
 * Created on Feb 24, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.tsagate.foundation.utility;

/**
 * @author JEFF
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;


/**
 * @author JEFF
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
/**
 * @author JEFF
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class UrlPost {

	private HttpsURLConnection httpsConnection = null ;
	private HttpURLConnection httpConnection = null ;
	private String connectionType = "http" ;
	private boolean secureConnection = false ;
    private URL url = null;
    private HttpURLConnection connection = null;

	public UrlPost()
	{
	}

	public UrlPost(String targetUrl) {
		System.out.println(targetUrl) ;
		openConnection(targetUrl) ;
		System.out.println("SecureConnect=" + this.secureConnection) ;
//		if (! secureConnection) {
//			httpConnection.addRequestProperty("appName","Puridiom") ;
//		}
	}

	public UrlPost(String targetUrl,String postString) {
		this(targetUrl) ;
		post(postString) ;
	}

	public void ticketPost(String targetUrl,String ticket, String appName) {

		openConnection(targetUrl) ;
		String postString = "userTicket=" + ticket + "&appName=" + appName ;
		post(postString) ;
	}

	public Object openConnection(String targetUrl) {


        try {
            url = new URL(targetUrl );
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
			connection.setRequestProperty("Content-Type","text/xml");
        }
		catch(Exception e)
		{
			System.out.println("Connection Exception: " + e.toString());
			e.printStackTrace() ;
		}

		return connection ;
	}



	/**
	 * @param postString
	 * @return
	 */
	public int post(String postString) {

		int status = -1 ;
		try {
			OutputStream os = null ;
			os = this.connection.getOutputStream() ;
			OutputStreamWriter osw = new OutputStreamWriter(os);
			osw.write(postString);
			osw.flush();
			osw.close();
			status = -1 ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status ;
	}

	/**
	 * @return
	 */
	public String getResult() {

		String returnString = null ;

		try {
			InputStream inputStream = null ;
			inputStream = this.connection.getInputStream() ;
			returnString = streamToString(inputStream) ;
			inputStream.close() ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnString ;
	}

	/**
	 * @param inputStream
	 * @return
	 */
	public String streamToString(InputStream inputStream)
	{
		DataInputStream iData = new DataInputStream(inputStream);
		String iLine = null;
		StringBuffer buf = new StringBuffer("");
		do
		{
			try
			{
				iLine = iData.readLine();
			}
			catch(IOException e)
			{
				System.out.println("readLine Exception: " + e.toString());
			}
			if(iLine == null)
				break;
			if(iLine.trim().length() > 0)
				buf.append(iLine);
		} while(true);

		return buf.toString();
	}
}
