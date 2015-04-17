/**
 *	PuridiomOrderAck (Servlet)
 */
package com.tsa.puridiom;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import java.util.Date;
import java.text.SimpleDateFormat ;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.properties.Dictionary;

/**
 * @author Jeff
 */
public class PuridiomOrderAck extends HttpServlet
{

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	    PrintWriter out = response.getWriter();
	    response.setContentType("text/xml;charset=utf-8");

		String	status = "1" ;  // Success
		String 	retMessage = "" ;
		StringBuffer  xmlData= new StringBuffer("") ;

	    try {
	        ServletInputStream inputStream =request.getInputStream();

	        byte[] buffer = new byte[8192];
	        int count = 0;
	        while ((count = inputStream.read(buffer, 0, 8192)) != -1) {
		          xmlData.append(new String(buffer,0,count));
	        }

	        if(inputStream != null)
	        {
	            try
	            {
	              	inputStream.close();
	             }
	            catch (IOException io)
	            {
	                // io.printStackTrace();
	                status = "0" ;
	                retMessage = io.getMessage() ;
	            }
	        }
	    }
	    catch (Exception e) {
			status = "0" ;
	        retMessage = e.getMessage() ;
	    }

	    if (status.equals("1")) {

	    }

	    this.writeOrderAck(xmlData) ;

	retMessage = retMessage.replaceAll("<BR>"," ");
	retMessage = retMessage.replaceAll("&","&amp;");
	retMessage = retMessage.replaceAll("'", "&apos;");
	retMessage = retMessage.replaceAll("\"","&quot;") ;
	retMessage = retMessage.replaceAll("<", "&lt;");
	retMessage = retMessage.replaceAll(">", "&gt;");

	// Write status
	StringBuffer	outMessage = new StringBuffer() ;
	outMessage.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>") ;
	outMessage.append("<!-- The Order Ack Response is sent by the Puridiom system. -->") ;
	outMessage.append("<!DOCTYPE cXML SYSTEM \"http://xml.cXML.org/schemas/cXML/1.2.014/cXML.dtd\">") ;
	outMessage.append("<cXML payloadID=\"" + String.valueOf(UniqueKeyGenerator.getInstance().getUniqueKey()) + "@puridiom.com\" xml:lang=\"en-CA\"  timestamp=\"") ;
	outMessage.append(this.getcXMLTimeStamp()) ;
	outMessage.append("\">") ;
	outMessage.append("<Response>");

	if (status.equals("1")) {
		outMessage.append("<Status code=\"200\" text=\"OK\"/>");
	} else {
		outMessage.append("<Status code=\"400\" text=\"FAILED\"/>");
	}
	outMessage.append("</Response>");
	outMessage.append("</cXML>") ;

	out.print(outMessage.toString()) ;


	out.close() ;
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on
	// the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	  /** Handles the HTTP <code>GET</code> method.
	   * @param request servlet request
	   * @param response servlet response
	   */
	  protected void doGet(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, java.io.IOException {
	      processRequest(request, response);
	  }

	  public void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
	      processRequest(request, response);
	  }

	  public static String getcXMLTimeStamp() {
		    return new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").format(new Date())
		    + new Integer(java.util.TimeZone.getDefault().getRawOffset()/3600000).toString()
		    + ":00";
		  }


	  /** Returns a short description of the servlet.
	   */
	  public String getServletInfo() {
	      return "Puridiom Order Acknowledgement";
	  }

	  private int writeOrderAck(StringBuffer xmlData) {

			Calendar cal = Calendar.getInstance() ;
			Dictionary	d = new Dictionary() ;
			String path = d.getProperty("temporary-document-path") ;
			String fileName = "ACK" + String.valueOf(cal.getTimeInMillis()) + ".xml";
			if (path.endsWith("\\")  || path.endsWith("/")) {
				fileName = path + fileName ;
			} else {
				fileName = path + "/" + fileName ;
			}

			try {
				if (fileName != null) {
			        BufferedWriter o = new BufferedWriter(new FileWriter(fileName));
			        o.write(xmlData.toString());
			        o.close();
				}
		    } catch (IOException e) {
		    	e.printStackTrace() ;
		    }

		  return 1 ;
	  }
}
