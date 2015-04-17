/*
 * Created on March 11, 2004
 */
package com.tsa.puridiom.supplierportal;

import com.tsa.puridiom.vendorregistration.VendorRegistrationUserManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import javax.servlet.*;

import java.io.*;
import java.util.*;
import java.sql.*;

/**
 * @author Kelli
 */
public class PuridiomSupplierPortalServletController extends HttpServlet
{
	private Connection connection = null;

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
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			throw new Exception("doGet is disabled - you must call the server from a Hilton Form");
		}
		catch (Exception e)
		{
			System.err.println("error: " + e);
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//system.out.println("HiltonBidboardServletController.doPost BEGIN " + Calendar.getInstance().getTime().toString());
		try
		{
			PuridiomSupplierPortalController controller = new PuridiomSupplierPortalController();
			request.setAttribute("requestURI", request.getRequestURI());
            request.setAttribute("ipAddress", request.getRemoteAddr());
			Map requestParameters = getRequestParametersAsHashMap(request);

            if (!requestParameters.containsKey("ipAddress")) {
                requestParameters.put("ipAddress", request.getRemoteAddr());
            }

			Map outgoingRequest = controller.handleRequest(requestParameters);
			String userId = (String) request.getParameter("userId");
			String organizationId = (String) requestParameters.get("organizationId");

			if(outgoingRequest == null)
			{
				outgoingRequest = requestParameters;
			}
			outgoingRequest.put("userId", userId);
			setRequestParametersFromHashMap(outgoingRequest, request);

			String viewPage = (String) request.getAttribute("viewPage");
			if ((viewPage == null) || (viewPage.equals("")))
			{
				viewPage = "index.jsp";
			}

			boolean userAuthenticated = false;
			try {
			    userAuthenticated = VendorRegistrationUserManager.getInstance().getUserInCache(organizationId, userId).isAuthenticated();
			} catch(Exception e) {
			}

//			if (!userAuthenticated && viewPage.indexOf("index.jsp") < 0 && viewPage.indexOf("error.jsp") < 0) {
//			    request.setAttribute("errorCode", "notAuthorized");
//			    getServletContext().getRequestDispatcher("/system/error.jsp").forward(request, response);
//			} else
			if (viewPage.indexOf("http://") == 0 || viewPage.indexOf("https://") == 0 || viewPage.indexOf("ftp://") == 0) {
			    response.sendRedirect(viewPage);
			} else {
                if (viewPage.indexOf("/") != 0) {
                    viewPage = "/" + viewPage;
                }
                if (viewPage.indexOf("/supplierportal") != 0) {
                    viewPage = "/supplierportal" + viewPage;
                }
                getServletContext().getRequestDispatcher(viewPage).forward(request, response);
			}
		}
		catch (Exception e)
		{
			System.err.println("error: " + e);
			getServletContext().getRequestDispatcher("/supplierportal/system/error.jsp");
		}
		//system.out.println("HiltonBidboardServletController.doPost END " + Calendar.getInstance().getTime().toString());
	}
	protected Map getRequestParametersAsHashMap(HttpServletRequest request) throws java.lang.Exception
	{
		Map requestParameters = new HashMap();
		String key, value;

		try
		{
			Enumeration enu = request.getParameterNames();
			while (enu.hasMoreElements())
			{
				key = (String) enu.nextElement();
				String values[] = request.getParameterValues(key);
				if (values.length == 1)
				{
					value = values[0];
					requestParameters.put(key, value);
				}
				else
				{
					requestParameters.put(key, values);
				}
			}
		}
		catch (Exception exception)
		{
			throw exception;
		}
		finally
		{
			return requestParameters;
		}
	}
	protected void setRequestParametersFromHashMap(Map map, HttpServletRequest request) throws java.lang.Exception
	{
		try
		{
			String k = null;
			Object o = null;
			if (map != null)
			{
				Set keys = map.keySet();
				Iterator i = keys.iterator();
				while (i.hasNext())
				{
					k = (String) i.next();
					o = (Object) map.get(k);
					request.setAttribute(k, o);
				}
			}
		}
		catch (Exception e)
		{
			System.err.println("ERROR: HiltonSupplierPortalServletController.setRequestParametersFromHashMap: " + e.toString());
			throw e;
		}
	}
}
