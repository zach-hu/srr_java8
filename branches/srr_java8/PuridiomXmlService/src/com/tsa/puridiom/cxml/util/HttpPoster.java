package com.tsa.puridiom.cxml.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.tsa.puridiom.cxml.exceptions.PunchCatalogException;
import com.tsagate.foundation.utility.Log;

public class HttpPoster
{
	private final static HttpPoster sInstance = new HttpPoster();

	private HttpPoster()
	{
		super();
	}

	public final static HttpPoster getInstance()
	{
		return sInstance;
	}

	public String postXML(String pURL, String pXML) throws Exception
	{
		String returnValue = null;

		if (pURL.startsWith("https") == true)
		{
			returnValue = this.postHttps(pURL, pXML);
		} else
		{
			returnValue = this.postHttp(pURL, pXML);
		}

		return returnValue;
	}

	public String postHttp(String pURL, String pXML) throws Exception
	{
		Log.debug(this, "Posting HTTP starts to: " + pURL);

		if (pXML == null)
		{
			throw new PunchCatalogException("Null request!");
		}
		StringBuffer response = new StringBuffer();
		OutputStreamWriter outputstreamwriter = null;
		try
		{
			URL url = new URL(pURL);
			HttpURLConnection httpurlconnection = (HttpURLConnection) url.openConnection();
			httpurlconnection.setDoOutput(true);
			httpurlconnection.setDoInput(true);
			httpurlconnection.setUseCaches(false);
			httpurlconnection.setAllowUserInteraction(false);
			httpurlconnection.setRequestProperty("CONTENT-TYPE", "text/xml");

			outputstreamwriter = new OutputStreamWriter(new BufferedOutputStream(httpurlconnection.getOutputStream()));
			outputstreamwriter.write(pXML, 0, pXML.length());

			InputStream inputstream = httpurlconnection.getInputStream();
			InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
			BufferedReader bufferedreader1 = new BufferedReader(inputstreamreader);

			for (String s2 = bufferedreader1.readLine(); s2 != null; s2 = bufferedreader1.readLine())
				response.append(s2);

			bufferedreader1.close();
			httpurlconnection.disconnect();
		} catch (MalformedURLException malformedURLException)
		{
			malformedURLException.printStackTrace();
			Log.error(this, "Error MalformedURLException  \r\n" + malformedURLException.getMessage());
			throw malformedURLException;
		} catch (IOException anException)
		{
			anException.printStackTrace();
			Log.error(this, "Error IOException  \r\n" + anException.getMessage());
			throw anException;

		} finally
		{
			if (outputstreamwriter != null)
			{
				try
				{
					outputstreamwriter.flush();
					outputstreamwriter.close();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		Log.debug(this, "Posting HTTP done: " + response.toString());

		return response.toString();
	}

	public String postHttps(String pURL, String pXML) throws Exception
	{
		StringBuffer response = new StringBuffer();
		Log.debug(this, "Posting Https starts to: " + pURL);

		if (pXML == null)
		{
			throw new PunchCatalogException("Null request!");
		}

		try
		{
			HostnameVerifier hv = new HostnameVerifier()
			{
				public boolean verify(String urlHostName, SSLSession session)
				{
					Log.debug(this, "Warning: URL Host: " + urlHostName + " vs. " + session.getPeerHost());
					return true;
				}
			};

			HttpsURLConnection.setDefaultHostnameVerifier(hv);

//			 Create a trust manager that does not validate certificate chains
		    TrustManager[] trustAllCerts = new TrustManager[]{
		        new X509TrustManager() {
		            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		                return null;
		            }
		            public void checkClientTrusted(
		                java.security.cert.X509Certificate[] certs, String authType) {
		            }
		            public void checkServerTrusted(
		                java.security.cert.X509Certificate[] certs, String authType) {
		            }
		        }
		    };

		    // Install the all-trusting trust manager
		    try {
		        SSLContext sc = SSLContext.getInstance("SSL");
		        sc.init(null, trustAllCerts, new java.security.SecureRandom());
		        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		    } catch (Exception e) {
		    	Log.error(this, "Unique Exception  \r\n" + e.getMessage());
		    }

			URL url = new URL(pURL);
			HttpsURLConnection httpurlconnection = (HttpsURLConnection) url.openConnection();
			httpurlconnection.setDoOutput(true);
			httpurlconnection.setDoInput(true);
			httpurlconnection.setRequestMethod("POST");
			httpurlconnection.setUseCaches(false);
			httpurlconnection.setAllowUserInteraction(false);
			httpurlconnection.setRequestProperty("Content-Type", "text/xml");

			OutputStreamWriter outputstreamwriter = new OutputStreamWriter(new BufferedOutputStream(httpurlconnection.getOutputStream()));
			outputstreamwriter.write(pXML, 0, pXML.length());

			outputstreamwriter.flush();
			outputstreamwriter.close();
			InputStream inputstream = httpurlconnection.getInputStream();
			InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
			BufferedReader bufferedreader1 = new BufferedReader(inputstreamreader);

			for (String s2 = bufferedreader1.readLine(); s2 != null; s2 = bufferedreader1.readLine())
				response.append(s2);

			inputstream.close();
			bufferedreader1.close();
			httpurlconnection.disconnect();
		} catch (MalformedURLException malformedURLException)
		{
			malformedURLException.printStackTrace();
			Log.error(this, "Error MalformedURLException  \r\n" + malformedURLException.getMessage());
			throw malformedURLException;
		} catch (IOException anException)
		{
			anException.printStackTrace();
			Log.error(this, "Error IOException  \r\n" + anException.getMessage());
			throw anException;
		} catch (Exception anException)
		{
			anException.printStackTrace();
			Log.error(this, "Error Exception  \r\n" + anException.getMessage());
			throw anException;
		}

		Log.debug(this, "postHttps done : " + response.toString());

		return response.toString();
	}

}
