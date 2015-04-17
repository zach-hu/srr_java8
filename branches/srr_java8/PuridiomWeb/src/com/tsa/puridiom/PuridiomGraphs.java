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

import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.graphs.DashBoard;
import com.tsa.puridiom.graphs.DashboardFactory;
import com.tsa.puridiom.graphs.Graph;
import com.tsa.puridiom.usermanager.UserManager;
/**
 * @author Administrator
 */
public class PuridiomGraphs extends HttpServlet
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
    public String getUserFilter(String graphName)
    {
    	return  (graphName + "dashWhere").toUpperCase();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        long startRequest = System.currentTimeMillis();
        Log.debug(this, request.getParameter("userId") + " - HiltonServletController.doPost BEGIN");
        try
        {
        	String oid = (String)request.getParameter("oid");
        	String uid = (String)request.getParameter("uid");
        	String graphName = request.getParameter("graphName");
        	String userFilter = request.getParameter("userFilter");
			if(!HiltonUtility.isEmpty(userFilter))
			{
				DashBoard.saveUserWhere(this.getUserFilter(graphName), userFilter, oid, uid);
			}
        	DashBoard dashboard = DashboardFactory.getNewInstance(oid, "buyer", uid);
        	Graph graph = dashboard.getGraph(graphName);

			dashboard.setGraph(graphName);
        	response.setContentType("text/xml");
        	response.getWriter().write(graph.getGraphInfo());
        	System.out.println(graph.getGraphInfo());

        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }

    }
}
