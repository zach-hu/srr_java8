package com.tsa.puridiom;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tsagate.foundation.utility.Log;

public class ResetPasswordRedirectServlet extends HttpServlet
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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException
    {
        this.resetPasswordRedirect(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException
    {
    	//this.approveMe(request, response);
    	System.out.println("post");
    }

    public void resetPasswordRedirect(HttpServletRequest request, HttpServletResponse response) throws ServletException
    {
    	long startRequest = System.currentTimeMillis();
        Log.debug(this, request.getParameter("userId") + " - HiltonServletController.doPost BEGIN");
        try
        {
        	String toPage = "/user/chg_pswd_redirect.jsp";
            String link = request.getRequestURI();
            request.setAttribute("requestURI", link);
            int lastSlash = link.lastIndexOf("ResetPasswordRedirectServlet/");
            if(lastSlash > 0)
            {
            	String parameters = link.substring(lastSlash + 16);
            	String parametersArray[] = parameters.split("&");
            	for (int i = 0; i < parametersArray.length; i++)
				{
					String temp[] = parametersArray[i].split("=");
					request.setAttribute(temp[0], temp[1]);
					if(temp[0].equalsIgnoreCase("to"))
					{
						toPage = temp[1];
					}
				}
            }

            System.out.println("requestURI" + request.getRequestURI());

            getServletContext().getRequestDispatcher(toPage).forward(request, response);


        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        Log.debug(this, request.getParameter("userId") + " - HiltonServletController.doPost END ");
        long endRequest = System.currentTimeMillis();
        Log.debug(this, request.getParameter("userId") + " - servlet request (" + request.getParameter("handler") + ") COMPLETE in " + (endRequest - startRequest) + " milliseconds.");
    }
}
