/**
 * 
 */
package com.tsa.puridiom;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsa.puridiom.xlsdata.tasks.XlsDataUploadFile;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

/**
 * @author Johnny
 */
public class HiltonXlsDataUploadServlet extends HiltonServletController
{
	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ServletContext servletContext = this.getServletContext();
		RequestDispatcher requestDispatcher;
		String failurePage = "/system/error.jsp";

		try
		{
			Map incomingRequest = new HashMap();
			XlsDataUploadFile xlsDataUploadFile = new XlsDataUploadFile();

			response.setContentType("text/plain");

			incomingRequest.put("documentType", "internal-document-path");

			this.setParameters(request, incomingRequest);

			xlsDataUploadFile.executeTask(incomingRequest);

			this.checkUserSession(request, incomingRequest);

			String successPage = (String) incomingRequest.get("successPage");
			failurePage = (String) incomingRequest.get("failurePage");

			this.setRequestParametersFromHashMap(incomingRequest, request);

			requestDispatcher = servletContext.getRequestDispatcher(successPage);

			if (requestDispatcher != null)
			{
				try
				{
					requestDispatcher.forward(request, response);

				} catch (Exception exception)
				{
					request.setAttribute("errorMsg", "There is a problem with the following page: " + successPage + ".");
					request.setAttribute("exception", exception);

					requestDispatcher = servletContext.getRequestDispatcher(failurePage);
					requestDispatcher.forward(request, response);
				}
			}
		} catch (IOException ioException)
		{
			this.getServletContext().log("There was an error reading or saving the file.");
			request.setAttribute("errorMsg", ioException.getMessage());
			request.setAttribute("exception", ioException);

			requestDispatcher = servletContext.getRequestDispatcher(failurePage);
			requestDispatcher.forward(request, response);
		} catch (Exception exception)
		{
			request.setAttribute("errorMsg", exception.getMessage());
			request.setAttribute("exception", exception);

			requestDispatcher = servletContext.getRequestDispatcher(failurePage);
			requestDispatcher.forward(request, response);
		}
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 * 
	 * @return a String containing servlet description
	 */
	public String getServletInfo()
	{
		return "HiltonXlsDataUploadServlet";
	}

	private String getDirectoryName(String documentType, String organizationId) throws Exception
	{
		String dirName = "";

		if (HiltonUtility.isEmpty(documentType))
		{
			documentType = "internal-document-path";
		}

		dirName = DictionaryManager.getInstance("host", organizationId).getProperty(documentType, "");

		if (HiltonUtility.isEmpty(dirName))
		{
			throw new Exception("The " + documentType + " was not specified.");
		}

		return dirName;
	}

	private void setParameters(HttpServletRequest request, Map incomingRequest) throws Exception
	{
		String organizationId = HiltonUtility.ckNull(request.getQueryString()).trim();

		if (HiltonUtility.isEmpty(organizationId))
		{
			throw new Exception("The organization id was not found.");
		}

		incomingRequest.put("organizationId", organizationId);

		this.setParametersFromRequest(request, incomingRequest, organizationId);
	}

	private void setParametersFromRequest(HttpServletRequest request, Map incomingRequest, String organizationId) throws Exception
	{
		String documentType = (String) incomingRequest.get("documentType");
		String dirName = this.getDirectoryName(documentType, organizationId);

		MultipartRequest multi = new MultipartRequest(request, dirName, 10 * 1024 * 1024); // 10MB
		Enumeration params = multi.getParameterNames();
		Enumeration files = multi.getFileNames();
		Map xlsFiles = new HashMap();

		while (params.hasMoreElements())
		{
			String name = (String) params.nextElement();
			String value = multi.getParameter(name);

			if (name.equalsIgnoreCase("successPage"))
			{
				incomingRequest.put("successPage", value);

			} else if (name.equalsIgnoreCase("failurePage"))
			{
				incomingRequest.put("failurePage", value);
			}

			request.setAttribute(name, value);
			incomingRequest.put(name, value);
		}

		while (files.hasMoreElements())
		{
			String name = (String) files.nextElement();
			String key = name.substring(name.lastIndexOf("_") + 1);

			Log.debug(this, "File loaded " + multi.getFilesystemName(name) + " - ContentType: " + multi.getContentType(name));

			xlsFiles.put(key, multi.getFile(name));
		}

		incomingRequest.put("xlsFiles", xlsFiles);
		incomingRequest.put("dirName", dirName);
	}

	private void checkUserSession(HttpServletRequest request, Map incomingRequest)
	{
		HttpSession userSession = request.getSession();
		String organizationId = (String) incomingRequest.get("organizationId");
		String userId = (String) incomingRequest.get("userId");

		try
		{
			boolean validSession = true;
			boolean userAuthenticated = false;
			long startRequest = System.currentTimeMillis();

			if (userSession == null || userSession.isNew())
			{
				validSession = false;
			} else
			{
				String sessionId = userSession.getId();
				incomingRequest.put("sessionId", sessionId);

				String ss_timoutSet = (String) userSession.getAttribute("timeoutSet");

				if (Utility.isEmpty(ss_timoutSet))
				{
					ss_timoutSet = "false";
				}

				if (!ss_timoutSet.equals("false"))
				{
					int maxInactiveInterval = userSession.getMaxInactiveInterval();
					long lastAccessed = userSession.getLastAccessedTime();
					long secondsInactive = (startRequest - lastAccessed) / 1000;

					if (secondsInactive > maxInactiveInterval)
					{
						userSession.invalidate();
						validSession = false;
					}
				} else
				{
					// Set number of seconds in which the session will time out
					int applicationIdleMinutes = Integer.valueOf(com.tsa.puridiom.property.PropertiesManager.getInstance(organizationId).getProperty("APPLICATION", "IdleMinutes", "30")).intValue();
					int userIdleMinutes = UserManager.getInstance().getUserRole(organizationId, userId).getIdleMinutes();
					int idleMinutes = 0;

					// If userIdleMinutes is set to 0 then use application idle
					// minutes
					if (userIdleMinutes <= 0)
					{
						idleMinutes = applicationIdleMinutes;
					} else
					{
						idleMinutes = userIdleMinutes;
					}
					// If sessionTimeout is set to 0 then use session default of
					// 30
					// minutes (1800 seconds)
					if (idleMinutes > 0)
					{
						// Convert minutes to seconds
						userSession.setMaxInactiveInterval(idleMinutes * 60);
					}

					userSession.setAttribute("timeoutSet", "true");
					userSession.setAttribute("organizationId", organizationId);
					userSession.setAttribute("userId", userId);
				}

				String authenticated = (String) incomingRequest.get("authenticated");
				if (!Utility.isEmpty(authenticated) && authenticated.equals("true"))
				{
					userSession.setAttribute("authenticated", "true");
					userSession.setAttribute("ss_uid", userId);
					userSession.setAttribute("ss_oid", organizationId);
				}
				authenticated = (String) userSession.getAttribute("authenticated");
				if (!Utility.isEmpty(authenticated) && authenticated.equals("true"))
				{
					userAuthenticated = true;
				}

				incomingRequest.put("authenticated", String.valueOf(userAuthenticated));
			}
		} catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}
}
