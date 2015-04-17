/*
 * Created on March 1, 2003
 */
package com.tsa.puridiom.common.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.StringUtils;

import com.tsa.puridiom.autogen.AutoGenManager;
import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.AutoGen;
import com.tsa.puridiom.entity.Contact;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

/**
 * @author Kelli
 */
public class HiltonUtility
{
	public static String getFiscalYear(String organizationId, String userTimeZone)
	{
		String begin = PropertiesManager.getInstance(organizationId).getProperty("Misc", "fybegin", "1");
		return Dates.getFiscalYear(Integer.parseInt(begin), userTimeZone);
	}

	public static String encodingString(String text)
	{
		text = text.replaceAll("\\r\\n", "\\\\r\\\\n");
		text = text.replaceAll("\"", "\\\\\"");
		return text.replaceAll("\'", "\\\\'");
	}

	public static String encode(String text)
	{
		String textEncoded = null;
		try
		{
			textEncoded = URLEncoder.encode(text, "UTF-8");
		}
		catch (Exception e)
		{
			textEncoded = text;
		}
		return textEncoded;
	}

	public static boolean isQriCanadian(String organizationId, String udf1)
	{
		boolean isCanadian = false;

		if(organizationId.equalsIgnoreCase("QRI06P"))
		{
			String nacProp = PropertiesManager.getInstance(organizationId).getProperty("MISC", "CANADIANUSER", "US");
			if( udf1.equalsIgnoreCase(nacProp)){	isCanadian = true; 	}
		}

		return isCanadian;
	}

	public static String getFormattedDate(Object date, String organization, String pattern)
	{
		String locale = "en";
		if(organization.equals("b2b"))
		{
			locale = "es";
		}
		return HiltonUtility.getFormattedDate(date, organization, pattern, locale);
	}

	public static String getVendContactDetails(String type, String vendorId, String contactCode, String organizationId)
	{
		String detail = "";
		if(Utility.isEmpty(vendorId)){		return "";		}
		if(Utility.isEmpty(contactCode)){		contactCode = "001";	}
		try
		{
			Map incomingRequest = new HashMap();
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
			PuridiomProcess process = processLoader.loadProcess("contact-retrieve-by-code.xml");
			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("Contact_contactCode", contactCode);
			incomingRequest.put("Contact_vendorId", vendorId);
			process.executeProcess(incomingRequest);

			if (process.getStatus() == Status.SUCCEEDED)
			{
				Contact contact = (Contact)incomingRequest.get("contact");
				if(type.equalsIgnoreCase("phone"))
				{
					detail = contact.getPhoneNumber();
				}
				else if(type.equalsIgnoreCase("fax"))
				{
					detail = contact.getFaxNumber();
				}
				else if(type.equalsIgnoreCase("email"))
				{
					detail = contact.getEmailAddr();
				}
				else if(type.equalsIgnoreCase("name"))
				{
					detail = contact.getDisplayName();
				}
				else if(type.equalsIgnoreCase("title"))
				{
					detail = contact.getContactTitle();
				}
			}
			else
			{
				detail = "";
			}
		}
		catch (Exception exception)
		{
			detail = "";
		}

		return detail;
	}

	/**
	 * getFormattedDate
	 * <p>
	 * Accepts a String or Date argument.
	 * Returns the date as a String formatted based on the pattern.
	 * 	If the pattern is null returns the date as a String formatted based on the default pattern for the organization.
	 * </p>
	 * @param date
	 * @param organization
	 * @param pattern
	 * @return Formatted date String
	 */
	public static String getFormattedDate(Object date, String organization, String pattern, String locale) {
		if (date == null) {
			return "";
		}
		if (isEmpty(pattern)) {
			PropertiesManager propertiesManager = PropertiesManager.getInstance(organization);
			pattern = propertiesManager.getProperty("MISC", "DateFormat", "MM-dd-yyyy");
		}

		return Utility.getDateFormat(date, pattern, locale);
	}

	public static String getFormattedMonth(int mon, String oid, String format) {
		Calendar cal = Calendar.getInstance() ;
		mon--;
		if (mon < 0) mon = 0 ;
		cal.set(2001, mon, 1) ;
		return HiltonUtility.getFormattedDate(cal.getTime(), oid, format) ;
	}

	 public static void main(String[] args)
	{
		System.out.println("start");
		System.out.println(HiltonUtility.getFormattedCurrency(new BigDecimal(123654.1234567889), "USD", "qri06p", true));
		System.out.println("end");
	}

	/**
	 * getFormattedDate
	 * <p>
	 * Accepts a String or Date argument.
	 * Returns the date as a String formatted based on the default date format pattern for the orgainization.
	 * </p>
	 * @param date
	 * @param organization
	 * @return Formatted date String
	 */
	public static String getFormattedDate(Object date, String organization) {
		PropertiesManager propertiesManager = PropertiesManager.getInstance(organization);
		String pattern = propertiesManager.getProperty("MISC", "DateFormat", "MM-dd-yyyy");

		if (date == null) {
			return "";
		}
		return Utility.getDateFormat(date, pattern);
	}

	/**
	 * getFormattedDollar
	 * <p>
	 * Accepts an argument that can be casted to a BigDecimal.
	 * Returns the number as a BigDecimal formatted based on the default dollar decimals for the orgainization.
	 * </p>
	 * @param object
	 * @param organization
	 * @return Formatted BigDecimal String
	 */
	public static BigDecimal getFormattedDollar(Object object, String organization) {
		PropertiesManager propertiesManager = PropertiesManager.getInstance(organization);
		String	decimalPlaces = propertiesManager.getProperty("MISC", "DollarDecimals", "2");

		return Utility.getBigDecimalFormatted(object, Integer.valueOf(decimalPlaces).intValue());
	}

	/**
	 * getFormattedPrice
	 * <p>
	 * Accepts an argument that can be casted to a BigDecimal.
	 * Returns the number as a BigDecimal formatted based on the default price decimals for the orgainization.
	 * </p>
	 * @param object
	 * @param organization
	 * @return Formatted BigDecimal String
	 */
	public static BigDecimal getFormattedPrice(Object object, String organization) {
		PropertiesManager propertiesManager = PropertiesManager.getInstance(organization);
		String	decimalPlaces = propertiesManager.getProperty("MISC", "PriceDecimals", "2");

		return Utility.getBigDecimalFormatted(object, Integer.valueOf(decimalPlaces).intValue());
	}

	public static String getCurrency(Object object, String currencyCode, String organizationId)
	{
		return HiltonUtility.getCurrency(object, currencyCode, organizationId, true);
	}

	public static String getCurrencyNS(Object object, String currencyCode, String organizationId)
	{
		return HiltonUtility.getCurrencyNS(object, currencyCode, organizationId, true);
	}
	
	public static String getCurrencyConvert(Object object, String currencyCode, String organizationId)
	{
		return HiltonUtility.getCurrencyConvert(object, currencyCode, organizationId, true);
	}

	public static BigDecimal getConvertBaseTotal(Object object, String currencyCode, String organizationId)
	{
		String formatted = object.toString();
		BigDecimal total = CurrencyManager.getInstance(organizationId).getBaseTotalConvert(formatted, currencyCode);

		return total;
	}

	public static String getFormattedCurrency(Object object, String currencyCode, String organizationId)
	{
		return HiltonUtility.getFormattedCurrency(object, currencyCode, organizationId, true);
	}
	/**
	 * getFormattedCurrency
	 * <p>
	 * Accepts an argument that can be casted to a BigDecimal.
	 * Returns the number as a BigDecimal formatted based on the default currency code for the orgainization.
	 * </p>
	 * @param object
	 * @param currency
	 * @param organization
	 * @param isCurrency -  if true will return a BigDecimal formatted including a currency symbol.
	 * @return Formatted BigDecimal String
	 */

	public static String getCurrency(Object object, String currencyCode, String organizationId, boolean isCurrency)
	{
		String formatted = object.toString();

		String nf = CurrencyManager.getInstance(organizationId).getCurrencychars(formatted,currencyCode);

		return nf;
	}

	public static String getCurrencyNS(Object object, String currencyCode, String organizationId, boolean isCurrency)
	{
		String formatted = object.toString();

		String nf = CurrencyManager.getInstance(organizationId).getCurrencycharsNS(formatted,currencyCode);

		return nf;
	}
	
	public static String getCurrencyConvert(Object object, String currencyCode, String organizationId, boolean isCurrency)
	{
		String convert = object.toString();

		String ccv = CurrencyManager.getInstance(organizationId).getConvertCurrency(convert, currencyCode);

		return ccv;

	}

	public static String getFormattedCurrency(Object object, String currencyCode, String organizationId, boolean isCurrency)
	{
		String	DollarDecimals = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DollarDecimals", "2");
		return HiltonUtility.getFormattedCurrency(object, currencyCode, organizationId, isCurrency, Integer.parseInt(DollarDecimals));
	}

	public static String getFormattedCurrency(Object object, String currencyCode, String organizationId, boolean isCurrency, int decimals)
	{
		String formatted = object.toString();

		NumberFormat nf = CurrencyManager.getInstance(organizationId).getCurrencyFormat(currencyCode, isCurrency);
		nf.setMaximumFractionDigits(decimals);
		nf.setMinimumFractionDigits(decimals);
		formatted =  nf.format(object);

		return formatted;
	}

	public static String getFormattedPriceCurrency(Object object, String currencyCode, String organizationId)
	{
		return HiltonUtility.getFormattedPriceCurrency(object, currencyCode, organizationId, true);
	}
	public static String getFormattedPriceCurrency(Object object, String currencyCode, String organizationId, boolean isCurrency)
	{
		String	DollarDecimals = PropertiesManager.getInstance(organizationId).getProperty("MISC", "PriceDecimals", "2");
		return HiltonUtility.getFormattedCurrency(object, currencyCode, organizationId, isCurrency, Integer.parseInt(DollarDecimals));
	}
	public static String getFormattedQtyCurrency(Object object, String currencyCode, String organizationId)
	{
		String	DollarDecimals = PropertiesManager.getInstance(organizationId).getProperty("MISC", "QtyDecimals", "2");
		return HiltonUtility.getFormattedCurrency(object, currencyCode, organizationId, false, Integer.parseInt(DollarDecimals));
	}

	/**
	 * getFormattedDollar
	 * <p>
	 * Accepts an argument that can be casted to a BigDecimal.
	 * Returns the number as a BigDecimal formatted based on the default dollar decimals for the orgainization.
	 * </p>
	 * @param object
	 * @param organization
	 * @return Formatted BigDecimal String
	 */
	public static BigDecimal getFormattedQuantity(Object object, String organization) {
		PropertiesManager propertiesManager = PropertiesManager.getInstance(organization);
		String	decimalPlaces = propertiesManager.getProperty("MISC", "QtyDecimals", "2");

		return Utility.getBigDecimalFormatted(object, Integer.valueOf(decimalPlaces).intValue());
	}

	/**
	*  isEmpty Method
	*  Tests for an empty string
	*  @param  test String
	*  @return boolean
	*/
	public static boolean isEmpty(String test) {
		boolean lbEmpty = false;

		if (test == null || test.trim().length() <= 0) {
			lbEmpty = true;
		}

		return lbEmpty;
	}

	public static Object getFormattedObject(Object value, String type, String organizationId)
	{
		if(value != null)
		{
			if (value instanceof Date)
			{
				Date tmpDate = (Date) value;
				value = HiltonUtility.getFormattedDate(tmpDate, organizationId);
				value = Dates.getDate((String)value);
			}
			else if (value instanceof BigDecimal)
			{
				BigDecimal tmpBD = (BigDecimal) value;
				if(!HiltonUtility.isEmpty(type))
				{
					if(type.equalsIgnoreCase("price"))
					{
						value = HiltonUtility.getFormattedPrice(tmpBD, organizationId);
					}
					else if(type.equalsIgnoreCase("dollar"))
					{
						value = HiltonUtility.getFormattedDollar(tmpBD, organizationId);
					}
					else if(type.equalsIgnoreCase("quantity"))
					{
						value = HiltonUtility.getFormattedQuantity(tmpBD, organizationId);
					}
					else
					{
						value = HiltonUtility.getFormattedDollar(tmpBD, organizationId);
					}
				}
			}
			else if(type.equalsIgnoreCase("trim"))
			{
				if (value instanceof String)
				{
					String tmpValue = value.toString();
					if(tmpValue.length() > 46)
					{
						value = tmpValue.substring(0, 46);
					}
				}
			}
		}
		return value;
	}

	public static BigDecimal getDateDifference(Date d_date1, Date d_date2) {
		int D1 = d_date1.getDate();
		int M1 = d_date1.getMonth();
		int Y1 = d_date1.getYear() + 1900;

		int D2 = d_date2.getDate();
		int M2 = d_date2.getMonth();
		int Y2 = d_date2.getYear() + 1900;

		GregorianCalendar date1=new GregorianCalendar(Y1,M1,D1);
		GregorianCalendar date2=new GregorianCalendar(Y2,M2,D2);
		Date d1 = date1.getTime();
	    Date d2 = date2.getTime();
		long l1 = d1.getTime();
	    long l2 = d2.getTime();
	    long difference = ((l2 - l1)/(60000 * 60 * 24));
	    BigDecimal test = new BigDecimal(difference);
	    return test;
	}

	/**
		*  ckNull Method
		*  @param  test String value to test - set to an empty string if null
		*  @return String
		*/
	public static String ckNull(String test) {
		if (test == null) {
			test = "";
		} else if (test instanceof String) {
			String testString = (String) test;
			if (testString.length() <= 0) {
				test = "";
			}
		}

		return HiltonUtility.decodeHtml(test);
	}

	/**
		*  ckNullBigDecimal Method
		*  @param  test BigDecimal value to test - set zero if null
		*  @return BigDecimal
		*/
	public static BigDecimal ckNull(BigDecimal test) {
		if (test == null) {
			test = new BigDecimal(0);
		}
		return test;
	}

	public static Integer ckNull(Integer test) {
		if (test == null) {
			test = new Integer(0);
		}
		return test;
	}

	public static java.util.Date ckNull(java.util.Date test) {
		if (test == null) {
			test = new java.util.Date();
		}
		return test;
	}

	public static java.sql.Date ckNull(java.sql.Date test) {
		if (test == null) {
			test = new java.sql.Date((new java.util.Date()).getTime());
		}
		return test;
	}

	public static java.util.List ckNull(java.util.List test) {
		if (test == null) {
			test = new ArrayList();
		}
		return test;
	}

	public static Object ckNull(Object o)
	{
		if(o == null)
		{
			if(o instanceof String)
			{
				o = "";
			}
			else if(o instanceof BigDecimal)
			{
				o = new BigDecimal(0);
			}
			else if(o instanceof Integer)
			{
				o = new Integer(0);
			}
			else if(o instanceof java.util.Date)
			{
				o = new java.util.Date();
			}
			else if(o instanceof java.sql.Date)
			{
				o = new java.sql.Date((new java.util.Date()).getTime());
			}
		}
		return o;
	}

	public static BigDecimal getBigDecimalFormatted(Object object, int decimalPlaces)
	{
		return Utility.getBigDecimalFormatted(object, decimalPlaces);
	}

	public static String	getFullDateTimeString(Object date, String time, String timeZone, String organization)
	{
		String	dateTimeString	 = HiltonUtility.getFormattedDate(date, organization);
		String	s_timeofday = "A.M.";

		if ( !HiltonUtility.isEmpty(time) ) {
			if (time.indexOf(":") > 0) {
				String	s_hour = time.substring(0,time.indexOf(":"));
				int		i_hour = Integer.valueOf(s_hour).intValue();
				String	s_min  = time.substring(time.indexOf(":") + 1, time.length());
				if (i_hour == 12) {
					s_timeofday = "P.M.";
				}
				else if (i_hour > 12) {
					i_hour = i_hour - 12;
					s_timeofday = "P.M.";
				}
				else if (i_hour == 0) {
					i_hour = i_hour + 12;
				}
				time = String.valueOf(i_hour) + ":" + s_min + " " + s_timeofday + " " + timeZone;
			}
		}
		if ( !HiltonUtility.isEmpty(dateTimeString) && !HiltonUtility.isEmpty(time) ) {
			dateTimeString = dateTimeString + " at " + time;
		}

		return dateTimeString;
	}

	public static String getPage(String oid, String pageName, String context)
	{
		if(!HiltonUtility.isEmpty(oid))
		{
			pageName = pageName + "_" + oid;
		}
		else if(!HiltonUtility.isEmpty(context))
		{
			pageName = pageName + "_" + context;
		}

		return pageName;
	}

	public static void setSendQueueMessageLong(StringBuffer msg, Map incomingRequest)
	{
		if(!HiltonUtility.isEmpty(msg.toString()))
		{
			if (msg.length() > 2000)
			{
				incomingRequest.put("SendQueue_messagetext", msg.substring(0, 2000));
				if(msg.length() > 4000)
				{
					incomingRequest.put("SendQueue_messagetext2", msg.substring(2000, 4000));
				}
				else
				{
					incomingRequest.put("SendQueue_messagetext2", msg.substring(2000));
				}
			}
			else
			{
				incomingRequest.put("SendQueue_messagetext", msg.substring(0));
			}
		}
	}

	public static String deleteSendToDuplicates(String toEmailAddress)
	  {
		  if(Utility.isEmpty(toEmailAddress))
			  return "";
		  StringBuffer ret = new StringBuffer();
		  ret.setLength(0);
		  if(toEmailAddress.length() < 1)
		  {
			  toEmailAddress = "";
		  }
		  if(toEmailAddress.length() > 0 && toEmailAddress.charAt(0) == ';')
		  {
			  toEmailAddress = toEmailAddress.substring(1);
		  }
		  if(toEmailAddress.indexOf(";") > -1)
		  {
			  String sAddies[] = toEmailAddress.split(";");
			  List addiesList = new ArrayList();
			  for(int i = 0; i < sAddies.length; i++)
			  {
				  if(!addiesList.contains(sAddies[i]))
				  {
					  addiesList.add(sAddies[i]);
				  }
			  }
			  for(int i = 0; i < addiesList.size(); i++)
			  {
				  ret.append(addiesList.get(i));
				  ret.append(";");
			  }
			  ret.deleteCharAt(ret.length() -1);
		  }
		  else
		  {
			  ret.append(toEmailAddress);
		  }

		  return ret.toString();
	  }
	public static boolean existsURL(String URLName)
	{
		if(URLName.indexOf("https://") < 0)
		{
			  try
			  {
				HttpURLConnection.setFollowRedirects(false);
				// note : you may also need
				//        HttpURLConnection.setInstanceFollowRedirects(false)
				HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
				con.setRequestMethod("HEAD");

				return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
			  }
			  catch (Exception e)
			  {
				   e.printStackTrace();
				   return false;
			  }
		}
		else
		{
			return HiltonUtility.postHttps2(URLName);

		}
	}


	public static boolean postHttps2(String pURL)
	  {

		try
		{
		  TrustManager[] trustAllCerts = new TrustManager[]{
			new X509TrustManager()
			{

			  public java.security.cert.X509Certificate[] getAcceptedIssuers()
			  {

				return null;
			  }

			  public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
			  {
			  }

			  public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
			  {
			  }
			}
		  };

		  // Let us create the factory where we can set some parameters for the connection
		  SSLContext sc = SSLContext.getInstance("SSL");
		  sc.init(null, trustAllCerts, new java.security.SecureRandom());

		  URL url = new URL(pURL);
		  HttpsURLConnection httpurlconnection = (HttpsURLConnection) url.openConnection();
		  httpurlconnection.setDoOutput(true);
		  httpurlconnection.setDoInput(true);
		  httpurlconnection.setRequestMethod("GET") ;
		  httpurlconnection.setUseCaches(false);
		  httpurlconnection.setAllowUserInteraction(false);
		  httpurlconnection.setRequestProperty("Content-Type","text/xml");
		  httpurlconnection.setHostnameVerifier(
											   new HostnameVerifier()
											   {
												 public boolean verify(String rserver, SSLSession sses)
												 {

												   if (!rserver.equals(sses.getPeerHost()))
												   {

													 System.out.println( "certificate <" + sses.getPeerHost() + "> does not match host <" + rserver + "> but " + "continuing anyway" );
												   }

												   return true;
												 }
											   });

		  return (httpurlconnection.getResponseCode() == HttpsURLConnection.HTTP_OK);
		}
		catch (Exception anException)
		{
		  //throw new PunchCatalogException(anException);
			return false;
		}


	  }

	public static String encodeHtml(String myString)
	{
		if (myString==null)
		{
			return null;
		}
		int length = myString.length();
		StringBuffer encodedString = new StringBuffer(2 * length);
		for (int i=0; i<length; i++)
		{
			char c = myString.charAt(i);
			if (c=='<')
			{
				encodedString.append("&lt;");
			}
			else if (c=='>')
			{
				encodedString.append("&gt;");
			}
			else if (c=='&')
			{
				encodedString.append("&amp;");
			}
			else if (c=='"')
			{
				encodedString.append("&quot;");
			}
			else if (c=='\'')
			{
				encodedString.append("&#39;");
			}
			//else if (c==' ')
			//{
				//encodedString.append("&nbsp;");
			//}
			else
			{
				encodedString.append(c);
			}
		}
		return encodedString.toString();
	}
	
	public static String decodeHtml(String myString)
	{
		if (myString==null)
		{
			return null;
		}
		
		String decodedString = myString;
		
		decodedString = decodedString.replaceAll("&lt;", "<");
		decodedString = decodedString.replaceAll("&gt;", ">");
		decodedString = decodedString.replaceAll("&amp;", "&");
		decodedString = decodedString.replaceAll("&quot;", "\"");
		decodedString = decodedString.replaceAll("&#39;", "\'");
		decodedString = decodedString.replaceAll("&#x5c;", "\\");
		decodedString = decodedString.replaceAll("&#x2f;", "/");
		decodedString = decodedString.replaceAll("\\\\x25", "%");
		decodedString = decodedString.replaceAll("[%]+", "%");
		decodedString = decodedString.replaceAll("&#x40;", "@");
		
		return decodedString;
	}

	public static String getAcctString(Account account, String organizationId)
	{
		String sep = PropertiesManager.getInstance(organizationId).getProperty("MISC", "AccountSeparator", "-");
		String sAcctElements = PropertiesManager.getInstance(organizationId).getProperty("FORM", "AccountElements", "15");
		int acctElements = Integer.parseInt(sAcctElements);
		StringBuffer temp = new StringBuffer();
		temp.setLength(0);
		int elements = 0;
		if (elements < acctElements)
		{
			temp = HiltonUtility.addFld(temp, account.getFld1(), sep);
			elements++;
		}
		if (elements < acctElements)
		{
			temp = HiltonUtility.addFld(temp, account.getFld2(), sep);
			elements++;
		}
		if (elements < acctElements)
		{
			temp = HiltonUtility.addFld(temp, account.getFld3(), sep);
			elements++;
		}
		if (elements < acctElements)
		{
			temp = HiltonUtility.addFld(temp, account.getFld4(), sep);
			elements++;
		}
		if (elements < acctElements)
		{
			temp = HiltonUtility.addFld(temp, account.getFld5(), sep);
			elements++;
		}
		if (elements < acctElements)
		{
			temp = HiltonUtility.addFld(temp, account.getFld6(), sep);
			elements++;
		}
		if (elements < acctElements)
		{
			temp = HiltonUtility.addFld(temp, account.getFld7(), sep);
			elements++;
		}
		if (elements < acctElements)
		{
			temp = HiltonUtility.addFld(temp, account.getFld8(), sep);
			elements++;
		}
		if (elements < acctElements)
		{
			temp = HiltonUtility.addFld(temp, account.getFld9(), sep);
			elements++;
		}
		if (elements < acctElements)
		{
			temp = HiltonUtility.addFld(temp, account.getFld10(), sep);
			elements++;
		}
		if (elements < acctElements)
		{
			temp = HiltonUtility.addFld(temp, account.getFld11(), sep);
			elements++;
		}
		if (elements < acctElements)
		{
			temp = HiltonUtility.addFld(temp, account.getFld12(), sep);
			elements++;
		}
		if (elements < acctElements)
		{
			temp = HiltonUtility.addFld(temp, account.getFld13(), sep);
			elements++;
		}
		if (elements < acctElements)
		{
			temp = HiltonUtility.addFld(temp, account.getFld14(), sep);
			elements++;
		}
		if (elements < acctElements)
		{
			temp = HiltonUtility.addFld(temp, account.getFld15(), sep);
			elements++;
		}

		return temp.toString();
	}

	public static StringBuffer addFld(StringBuffer temp, String fld, String sep)
    {
        if (temp.length() > 0)
        {
            temp.append(sep);
        }

        temp.append(fld);

        return temp;
    }

	/**
	  * Fetch the entire contents of a text file, and return it in a String.
	  * This style of implementation does not throw Exceptions to the caller.
	  *
	  * @param aFile is a file which already exists and can be read.
	  */
	  static public String getFileContents(File aFile)
	  {
	    //...checks on aFile are elided
	    StringBuffer contents = new StringBuffer();

	    //declared here only to make visible to finally clause
	    BufferedReader input = null;
	    try {
	      //use buffering, reading one line at a time
	      //FileReader always assumes default encoding is OK!
	      input = new BufferedReader( new FileReader(aFile) );
	      String line = null; //not declared within while loop
	      /*
	      * readLine is a bit quirky :
	      * it returns the content of a line MINUS the newline.
	      * it returns null only for the END of the stream.
	      * it returns an empty String if two newlines appear in a row.
	      */
	      while (( line = input.readLine()) != null){
	        contents.append(line);
	        contents.append(System.getProperty("line.separator"));
	      }
	    }
	    catch (FileNotFoundException ex) {
	      ex.printStackTrace();
	    }
	    catch (IOException ex){
	      ex.printStackTrace();
	    }
	    finally {
	      try {
	        if (input!= null) {
	          //flush and close both "input" and its underlying FileReader
	          input.close();
	        }
	      }
	      catch (IOException ex) {
	        ex.printStackTrace();
	      }
	    }
	    return contents.toString();
	  }

	public static long getBytesSizeFile(String formattedFileSize)
	{
		String mask = "XX";
		float bytesSizeFile = Float.parseFloat(formattedFileSize.substring(0, formattedFileSize.length() - mask.length()));

		if (formattedFileSize.endsWith("GB"))
		{
			bytesSizeFile *= (1024 * 1024 * 1024);
		} else if (formattedFileSize.endsWith("MB"))
		{
			bytesSizeFile *= (1024 * 1024);
		} else if (formattedFileSize.endsWith("KB"))
		{
			bytesSizeFile *= 1024;
		}
		return (long) bytesSizeFile;
	}

	public static String getRealForeignCharacters(String encodedForeingCharacters)
	{
		StringBuffer sb = new StringBuffer();
		char realCharacter = 'X';

		Pattern numCodePattern = Pattern.compile("&#[0-9]*;");
		Matcher matcher = numCodePattern.matcher(encodedForeingCharacters);
		matcher.reset();

		while (matcher.find())
		{
			realCharacter = (char) Integer.parseInt(matcher.group().substring(2, matcher.group().length() - 1));
			matcher.appendReplacement(sb, String.valueOf(realCharacter));
		}
		matcher.appendTail(sb);

		return sb.toString();
	}

	public static String parseFilename(String filename)
	{
		return filename.replaceAll("[\\x2F\\x5C:*?<>|\"]"," ").trim();
	}

	/**
	 *
	 * @param words Words with differents sizes
	 * @param maxSize Max size of each word
	 * @param i First word to begin the process
	 * @return
	 */
	public static String cutWords(String words, int maxSize, int i) {
		String[] tempWords = words.split(" ");
		if (i < tempWords.length && i>=0) {
			if (tempWords[i].length() > maxSize) {
				tempWords[i] = tempWords[i].substring(0,maxSize)+" "+
				tempWords[i].substring(maxSize,tempWords[i].length());
				i++;
				return cutWords(StringUtils.join(tempWords," "), maxSize, i);
			}else{
				i++;
				return cutWords(StringUtils.join(tempWords," "), maxSize, i);
			}
		}else return StringUtils.join(tempWords," ");
	}

	public static String convertToDollarText(BigDecimal number)
	{
		return NumberToWordsConverter.convertToDollarText(number);
	}

	public static String completeCharacters(String initialText, String fillCharacter, int finalSize, int position)
	{
		String relleno = "";
		if (!HiltonUtility.isEmpty(initialText) && initialText.length() > finalSize) {
			initialText = initialText.substring(0, finalSize);
		}

		for (int i = 0; i < (finalSize - initialText.length()); i++)
		{
			relleno = relleno + fillCharacter;
		}
		if (position == 0)
		{
			return  relleno.concat(initialText);
		}
		else if (position == 1)
		{
			return initialText.concat(relleno);
		}
		return initialText;
	}

	 /**
     * Returns a literal replacement <code>String</code> for the specified
     * <code>String</code>.
     *
     * This method produces a <code>String</code> that will work
     * use as a literal replacement <code>s</code> in the
     * <code>appendReplacement</code> method of the {@link Matcher} class.
     * The <code>String</code> produced will match the sequence of characters
     * in <code>s</code> treated as a literal sequence. Slashes ('\') and
     * dollar signs ('$') will be given no special meaning.
     *
     * @param  s The string to be literalized
     * @return  A literal string replacement
     */
    public static String quoteReplacement(String s)
	{
		if ((s.indexOf('\\') == -1) && (s.indexOf('$') == -1))
			return s;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++)
		{
			char c = s.charAt(i);
			if (c == '\\')
			{
				sb.append('\\');
				sb.append('\\');
			} else if (c == '$')
			{
				sb.append('\\');
				sb.append('$');
			} else
			{
				sb.append(c);
			}
		}
		return sb.toString();
	}
    public static String getFormattedTimeZone(String timeZoneId) {
        return Dates.getTimeZoneAbbreviation(timeZoneId);
    }
    public static File getOidFile(String fileName, String Name, String locale, String organizationId)
    {
    	Log.debug(Utility.class, "getOidFile: fileName: " + fileName + ", organizationId" + organizationId);
    	File file = new File(fileName + locale);
    	File filename = new File(Name);
        File ret = null;
        File dir = file.getParentFile();
        try
        {
	        if(dir.exists())
	        {
	        	Log.debug(Utility.class, "directory exists");
	            //look for file in organization's directory
	            File tempDir = null;
	            if (organizationId != null)
	            {
	                tempDir = new File(dir.getPath() + File.separator + organizationId.toString().toLowerCase() + File.separator + file.getName());
	            }
	            if(tempDir == null || !tempDir.exists())
	            {
	                //look for file in puridiom directory
	                tempDir = new File(dir.getPath() + File.separator + file.getName());
	                if(!tempDir.exists())
	                {
	                    //look for file in normal directory
	                    tempDir = new File(dir.getPath() + File.separator + file.getName());
	                    if(!tempDir.exists())
	                    {
	                        tempDir = new File(filename.getName());
	                        Log.error("Utility", fileName + " could not be found!");
	                    }
	                }
	                else
	                {
	                	tempDir = new File(locale);
	                }
	            }
	            else
	            {
	            	tempDir = new File(locale);
	            }
	            ret = tempDir;
	        }
	        else
	        {
	        	Log.debug(Utility.class, "directory does not exists");
	        	 dir = dir.getParentFile();
	        	 Log.debug(Utility.class, "parent is: " + dir.getPath());
	        	 if(dir.exists())
	             {
	        		 File tempDir = new File(dir.getPath() + File.separator + file.getName());
	        		 if(tempDir.exists())
	        		 {
	        			 ret = tempDir;
	        		 }
	        		 else
	        		 {
	        			 Log.error(Utility.class, tempDir.getPath() + " was not found");
	        		 }
	             }
	        	 else
        		 {
        			 Log.error(Utility.class, "Parent " + dir.getPath() + " was not found");
        		 }
	        }
        }
        catch (Exception e) {
			Log.error(Utility.class, fileName + " file was not found.");
		}

        Log.debug(Utility.class, fileName + "found in [" + ret.getAbsolutePath() + "]");

        return ret;
    }


	public static String getFiscalYearOptions(String organizationId, String userTimeZone, String documentType, String defaultFiscalYear) {
		StringBuffer options = new StringBuffer("");
		if (Utility.isEmpty(documentType)) {
			return "";
		}
		try {
			if (HiltonUtility.isEmpty(defaultFiscalYear)) {
				defaultFiscalYear = HiltonUtility.getFiscalYear(organizationId, userTimeZone);
			}

			List autoGenList = AutoGenManager.getInstance().getActiveFiscalYears(organizationId, documentType);
			if (autoGenList != null) {
				for (int i=0; i < autoGenList.size(); i++) {
					AutoGen autoGen = (AutoGen) autoGenList.get(i);
					String year = autoGen.getComp_id().getGenYear();
					if (!year.equals("1994")) {
						if (year.equals(defaultFiscalYear)) {
							options.append("<option value=\"" + year + "\" selected>" + year + "</option>");
						} else {
							options.append("<option value=\"" + year + "\">" + year + "</option>");
						}
					}
				}
			}
		}
		catch (Exception exception) {
		}

		return options.toString();
	}

	public static boolean isInteger(String test)
	{
		try
		{
			Integer.parseInt(test);
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}

	public static boolean isNA(String str) {
		return "N/A".equals(str) || "N&#x2f;A".equals(str);
	}

	public static String convertYtoYesOrNtoNo(String valueUdf) {
		String returnValue = valueUdf;
		if(valueUdf.toUpperCase().equalsIgnoreCase("Y")) returnValue = "YES";
		else if(valueUdf.toUpperCase().equalsIgnoreCase("N")) returnValue = "NO";

		return returnValue;
	}

}
