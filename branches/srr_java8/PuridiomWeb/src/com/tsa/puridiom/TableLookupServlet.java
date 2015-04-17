/*
 * Created on Jul 14, 2003
 */
package com.tsa.puridiom;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.common.lookup.* ;

import com.tsa.puridiom.graphs.DashBoard;
import com.tsa.puridiom.graphs.DashboardFactory;
import com.tsa.puridiom.graphs.Graph;
import com.tsa.puridiom.usermanager.UserManager;
/**
 * @author Administrator
 */
public class TableLookupServlet extends HttpServlet
{

	private String organizationId;
	private String userId;
	private DBSession dbsession;

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
        try
        {
        	doPost(request, response);
           // throw new Exception("doGet is disabled - you must call the server from a Puridiom Form");
        }
        catch (Exception e)
        {
            System.err.println("error: " + e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        long startRequest = System.currentTimeMillis();
        Log.debug(this, request.getParameter("userId") + " - HiltonServletController.doPost BEGIN");
        try
        {
        	this.userId = (String)request.getParameter("uid");
        	this.organizationId = (String)request.getParameter("oid");

			Map incomingRequest = new HashMap();

        	Enumeration e = request.getParameterNames();
        	while (e.hasMoreElements()) {
        	  String name = (String) e.nextElement();
        	  String []values = (String []) request.getParameterValues(name);
        	  String value = values[0];
        	  for (int i = 1; i < values.length; i++)
        	    value += ", " + values[i];
        	  	incomingRequest.put(name,value) ;
        	}

       		TableLookup  lookUp = new TableLookup(this.organizationId, this.userId) ;
       		String out = lookUp.processLookup(incomingRequest) ;

       		if (lookUp.getLookupStatus() == Status.SUCCEEDED) {
	       		// XML formatted response:
	       		response.setContentType("text/xml");
	       		response.setHeader("Cache-Control", "no-cache");
	       		response.getWriter().write(out);
       		} else {
      			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
       		}
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR) ;
        }
    }
}
