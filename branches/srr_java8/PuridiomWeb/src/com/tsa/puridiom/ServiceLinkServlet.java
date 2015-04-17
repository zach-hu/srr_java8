/**
 *
 */
package com.tsa.puridiom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny Zapana
 */
public class ServiceLinkServlet extends HttpServlet
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
		long startRequest = System.currentTimeMillis();

		Log.debug(this, "ServiceLinkServlet.doGet BEGIN ");

		try
		{
			String toPage = "/service/mail_service.jsp";
			String link = request.getRequestURI();
			request.setAttribute("requestURI", link);
			int lastSlash = link.lastIndexOf("Services/");

			if (lastSlash > 0)
			{
				String parameters = link.substring(lastSlash + 9);
				String parametersArray[] = parameters.split("&");
				for (int i = 0; i < parametersArray.length; i++)
				{
					String temp[] = parametersArray[i].split("=");
					request.setAttribute(temp[0], temp[1]);
				}
			}

			Log.debug(this, "requestURI : " + request.getRequestURI());

			this.getServletContext().getRequestDispatcher(toPage).forward(request, response);

		} catch (Exception e)
		{
			e.printStackTrace();
			Log.error(this, "Error processing Receipt Acknowledgement " + e.getMessage());
		}

		Log.debug(this, "ServiceLinkServlet.doGet END ");

		long endRequest = System.currentTimeMillis();

		Log.debug(this, " ServiceLinkServlet request COMPLETE in " + (endRequest - startRequest) + " milliseconds.");
	}

	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Log.info(this, "ServiceLinkServlet.doPost");
	}

}
