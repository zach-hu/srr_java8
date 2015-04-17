package com.tsa.puridiom;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tsagate.properties.DictionaryManager;

public class VersionCheckServlet extends HttpServlet
{
	public void init(ServletConfig config) throws ServletException
    {
        try
        {
            super.init(config);
        }
        catch (Exception exception)
        {
            throw new ServletException(exception.toString());
        }
    }
    public void destroy()
    {
        super.destroy();
        try
        {
        }
        catch (Exception exception)
        {
            System.err.println(exception.toString());
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	String oid = "SRR10P";
    	String jarString = "jar";
    	String jspString = "jsp";
    	String xmlString = "xml";
    	String context = getServletContext().getRealPath(File.separator);
    	 String JAR_PATH = context + "WEB-INF" + File.separator + "lib" + File.separator;
    	 String XML_PATH = context + "WEB-INF" + File.separator + "xml" + File.separator;
    	Map<String, String> changeLogMap = DictionaryManager.getInstance("changelog", oid).getPropertyMap();
    	PrintWriter pw = response.getWriter();
    	pw.println("Patch Verision Check<br />" +
    			"Files:<br />");

    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	Iterator<String> changeLogKeys = changeLogMap.keySet().iterator();
    	while (changeLogKeys.hasNext())
    	{
    		String key = changeLogKeys.next();
    		String fileExt = key.substring((key.length() - 3), key.length());

    		if (fileExt.equals(jarString))
    		{
    			File jarFile = new File(JAR_PATH + key);
    			if (jarFile.exists())
    			{
    				pw.println(key);
    				Long lastModified = jarFile.lastModified();
    				Calendar lastModifiedDate = Calendar.getInstance();
    				lastModifiedDate.setTimeInMillis(lastModified);
    				String lastModifiedDateString = format.format(lastModifiedDate.getTime());
    				if (lastModifiedDateString.equals(changeLogMap.get(key))) {
    					pw.println("&nbsp;&nbsp; <font color=\"green\">DATES MATCH</font><br />");
    				} else {
    					pw.println("&nbsp;&nbsp; <font color=\"red\">PATCH NOT INSTALLED CORRECTLY.</font><br />");
    				}
    			}
    			else
    			{
    				pw.println(key + "&nbsp;&nbsp; COULD NOT FIND FILE IN " + JAR_PATH);
    			}

    		}
    		else if(fileExt.equals(jspString))
    		{
    			File jspFile = new File(context + key);
    			if (jspFile.exists())
    			{
    				pw.println(key);
    				Long lastModified = jspFile.lastModified();
    				Calendar lastModifiedDate = Calendar.getInstance();
    				lastModifiedDate.setTimeInMillis(lastModified);
    				String lastModifiedDateString = format.format(lastModifiedDate.getTime());
    				if (lastModifiedDateString.equals(changeLogMap.get(key))) {
    					pw.println("&nbsp;&nbsp; <font color=\"green\">DATES MATCH</font><br />");
    				} else {
    					pw.println("&nbsp;&nbsp; <font color=\"red\">PATCH NOT INSTALLED CORRECTLY.</font><br />");
    				}
    			}
    			else
    			{
    				pw.println(key + "&nbsp;&nbsp; <font color=\"red\">COUDL NOT FIND JSP FILE IN " + context + "</font>");
    			}
    		}
    		else if(fileExt.equals(xmlString))
    		{
    			File xmlFile = new File(XML_PATH + key);
    			if (xmlFile.exists())
    			{
    				pw.println(key);
    				Long lastModified = xmlFile.lastModified();
    				Calendar lastModifiedDate = Calendar.getInstance();
    				lastModifiedDate.setTimeInMillis(lastModified);
    				String lastModifiedDateString = format.format(lastModifiedDate.getTime());
    				if (lastModifiedDateString.equals(changeLogMap.get(key))) {
    					pw.println("&nbsp;&nbsp; <font color=\"green\">DATES MATCH</font><br />");
    				} else {
    					pw.println("&nbsp;&nbsp;<font color=\"red\">PATCH NOT INSTALLED CORRECTLY.</font><br />");
    				}
    			}
    			else
    			{
    				pw.println(key + "&nbsp;&nbsp; <font color=\"red\">COULD NOT FIND XML FILE IN " + XML_PATH + "</font>");
    			}
    		}
    	}


    }
}
