/*
 * Created on Jul 14, 2003
 */
package com.tsa.puridiom;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tsa.puridiom.common.utility.TokenProcessor;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
/**
 * @author Administrator
 */
public class HiltonServletController extends HttpServlet
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
            throw new Exception("doGet is disabled - you must call the server from a Puridiom 4.0 Form");
        }
        catch (Exception e)
        {
            System.err.println("error: " + e);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	String organizationId = "SRR10P"; //validateOrganizationId((String) request.getParameter("organizationId"));
    	
    	request.setCharacterEncoding("UTF-8");
    	long startRequest = System.currentTimeMillis();
        Log.debug(this, request.getParameter("userId") + " - HiltonServletController.doPost BEGIN");
        try
        {
            HiltonController controller = new HiltonController();
            boolean validToken = false;
            request.setAttribute("requestURI", request.getRequestURI());
            request.setAttribute("ipAddress", request.getRemoteAddr());
            Map requestParameters = getRequestParametersAsHashMap(request);
            requestParameters.put("organizationId", organizationId);
            requestParameters.put("oid", organizationId);
            requestParameters.put("ipAddress", request.getRemoteAddr());
            requestParameters.put("sessionId", request.getSession().getId());
            
            //null out any redirect page that was submitted with the request
            requestParameters.put("redirectPage", null);
            
            String requestId = (String) requestParameters.get("requestId");
            
            //eliminate | characters from requestId
            if (requestId != null) {
	            while (requestId.indexOf("|") > -1) {
					int i = requestId.indexOf("|");
					requestId = requestId.substring(0, 1) + requestId.substring(i+1);
				}
            }
            
            requestParameters.put("ipAddress", request.getRemoteAddr());

            requestParameters.put("HttpServletResponse", response);

            if (TokenProcessor.getInstance().validToken(request))
			{
              	validToken = true;
               	Log.debug(this, "Token for Request is valid");
			}

            if (!Utility.isEmpty((String)request.getSession().getAttribute("userId"))) {
            	requestParameters.put("userId", (String)request.getSession().getAttribute("userId"));
            }
            if (!Utility.isEmpty((String)request.getSession().getAttribute("organizationId"))) {
            	requestParameters.put("organizationId", (String)request.getSession().getAttribute("organizationId"));
            }


			requestParameters.put("validToken", new Boolean(validToken));

            Map outgoingRequest = controller.handleRequest(requestParameters);
            if(outgoingRequest == null)
            {
                outgoingRequest = requestParameters;
            }

            String	userId = (String) outgoingRequest.get("userId");
            //organizationId = validateOrganizationId((String) outgoingRequest.get("organizationId"));
            String	logOff = (String) outgoingRequest.get("logOff");
                
            boolean validSession = true;
            boolean userAuthenticated = false;

           	if (!Utility.isEmpty(logOff) && logOff.equals("true")) {
           	    HttpSession userSession = request.getSession();
           	    userId = (String) request.getParameter("userId");

               	if ( userSession != null && !userSession.isNew() ) {
               	    userSession.invalidate();
               	    outgoingRequest.put("sessionId", "");
               	}
           	} else if (!Utility.isEmpty(userId)) {
           		outgoingRequest.put("userId", userId);
                if (!Utility.isEmpty(organizationId)) {
                  	outgoingRequest.put("organizationId", organizationId);
	                try {
	                    HttpSession userSession = request.getSession();
                    	if ( userSession == null ) {
                    		validSession = false;
                    	}
                    	else {
                    		String	sessionId = userSession.getId();
                    	    outgoingRequest.put("sessionId", sessionId);
                   			String	ss_timoutSet = (String) userSession.getAttribute("timeoutSet");

                   			if (Utility.isEmpty(ss_timoutSet)) {
                   			    ss_timoutSet = "false";
                   			}

                   			if (!ss_timoutSet.equals("false")) {
                   			    int	maxInactiveInterval = userSession.getMaxInactiveInterval();
                   			    long	lastAccessed = userSession.getLastAccessedTime();
                   			    long	secondsInactive = (startRequest - lastAccessed) / 1000;

                   			    if (secondsInactive > maxInactiveInterval) {
                   			        userSession.invalidate();
                   			        validSession = false;
                   			    }
                   			} else {
                   				//	Set number of seconds in which the session will time out
                   				int	applicationIdleMinutes = Integer.valueOf(com.tsa.puridiom.property.PropertiesManager.getInstance(organizationId).getProperty("APPLICATION", "IdleMinutes", "30")).intValue();
                   				int	userIdleMinutes =  UserManager.getInstance().getUserRole(organizationId, userId).getIdleMinutes();
                   				int	idleMinutes = 0;

                   				// If userIdleMinutes is set to 0 then use application idle minutes
                    			if (userIdleMinutes <= 0) {
                    			    idleMinutes = applicationIdleMinutes;
                    			} else {
                    			    idleMinutes = userIdleMinutes;
                    			}
                    			// If sessionTimeout is set to 0 then use session default of 30 minutes (1800 seconds)
                    			if (idleMinutes > 0) {
                    			    // Convert minutes to seconds
                        			userSession.setMaxInactiveInterval(idleMinutes * 60);
                    			}

                   				userSession.setAttribute("timeoutSet", "true");
                   				userSession.setAttribute("organizationId", organizationId);
                   				
                   				//clean up UserManager before setting the new userId
                   				String oldUserId = (String) userSession.getAttribute("userId"); 
                   				if (oldUserId != null && !oldUserId.equals(userId)) UserManager.getInstance().removeUserSession(organizationId, sessionId);
                   				userSession.setAttribute("userId", userId);

                   				UserLogged.getInstance(organizationId).logUser(sessionId, userId, organizationId);
                   			}
                   			String	authenticated = (String) outgoingRequest.get("authenticated");
                   			if (!Utility.isEmpty(authenticated) && authenticated.equals("true")) {
                   			    userSession.setAttribute("authenticated", "true");
                   			    userSession.setAttribute("ss_uid", userId);
                   			    userSession.setAttribute("ss_oid", organizationId);
                   			}
                   			authenticated = (String) userSession.getAttribute("authenticated");
                   			if (!Utility.isEmpty(authenticated) && authenticated.equals("true")) {
                   			    userAuthenticated = true;
                   			}
                    	}
                    } catch (Exception e) {
                    	e.printStackTrace();
                    }
                }
            }
            setRequestParametersFromHashMap(outgoingRequest, request);

          	String viewPage = null;
            if (validSession) {
                viewPage = (String) request.getAttribute("viewPage");
            } else {
   			    request.setAttribute("errorCode", "invalidSession");
   			    viewPage = "/system/error.jsp";
   			    userAuthenticated = false;
            }

            String module = "";
            String moduleType = "";
            if (viewPage == null) viewPage = "";
            if (viewPage.indexOf("requests/") > 0 || viewPage.startsWith("requests/")) {
              	module = "req";
              	// Get requisitionHeader from the request object to use to determine the Requisition type
               	RequisitionHeader requisitionHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");                	
               	if (requisitionHeader != null) moduleType = requisitionHeader.getRequisitionType();
               	else moduleType = request.getParameter("RequisitionHeader_requisitionType");
           	}
           	else if (viewPage.indexOf("orders/") > 0 || viewPage.startsWith("orders/")) {
            	module = "po";
            	// Get poHeader from the request object to use to determine the PO type
            	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
            	if (poHeader != null) moduleType = poHeader.getPoType();
               	else moduleType = request.getParameter("PoHeader_poType");
           	}
           	else if (viewPage.indexOf("rfq/") > 0 || viewPage.startsWith("rfq/")) {
            	module = "rfq";
            	// Get rfqHeader from the request object to use to determine the RFQ
            	RfqHeader rfqHeader = (RfqHeader)request.getAttribute("rfqHeader");
            	if (rfqHeader != null) moduleType = rfqHeader.getRfqType();
               	else moduleType = request.getParameter("RfqHeader_rfqType");	            	
           	} 
           	else if (viewPage.indexOf("receipts/") > 0 || viewPage.startsWith("receipts/")) {
            	module = "rec";
            	// Get receiptHeader from the request object to use to determine the receipt type
            	ReceiptHeader receiptHeader = (ReceiptHeader) request.getAttribute("receiptHeader");
            	if (receiptHeader != null) moduleType = receiptHeader.getReceiptType();
               	else moduleType = request.getParameter("ReceiptHeader_receiptType");	
           	} 
           	else {  // (Currently does not apply)            		            		
            	module = (String) ((request.getAttribute("module") != null) ? request.getAttribute("module") : "");
            	moduleType = (String) ((request.getAttribute("moduleType") != null) ? request.getAttribute("moduleType") : "");	            	
           	}
            request.setAttribute("module", module);
            request.setAttribute("moduleType", moduleType);
                
            request.setAttribute("authenticated", String.valueOf(userAuthenticated));

            if (Utility.isEmpty(viewPage)) {
                viewPage = "index.jsp";
            }
            
            String redirectPage = (String)request.getAttribute("redirectPage");
            if (redirectPage != null && redirectPage.length() > 0) {
                response.sendRedirect(redirectPage);
            } else {
                if (viewPage.indexOf("/") != 0) {
                    viewPage = "/" + viewPage;
                }
                getServletContext().getRequestDispatcher(viewPage).forward(request, response);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            request.setAttribute("exception", e);
            try
            {
                setRequestParametersFromHashMap(getRequestParametersAsHashMap(request), request);
                if (request.getParameter("failurePage") != null)
                {
                    getServletContext().getRequestDispatcher("/" + (String) request.getParameter("failurePage")).forward(request, response);
                }
                else
                {
                    getServletContext().getRequestDispatcher("/system/error.jsp").forward(request, response);
                }
            }
            catch (Exception e2)
            {
            	e2.printStackTrace();
                getServletContext().getRequestDispatcher("/system/error.jsp").forward(request, response);
            }
        }
        Log.debug(this, request.getParameter("userId") + " - HiltonServletController.doPost END ");
        long endRequest = System.currentTimeMillis();
        Log.debug(this, request.getParameter("userId") + " - servlet request (" + request.getParameter("handler") + ") COMPLETE in " + (endRequest - startRequest) + " milliseconds.");
    }
    private Map getSessionAttributesAsHashMap(HttpServletRequest request) throws java.lang.Exception
    {
        Map sessionAttributes = new HashMap();
        String key = null;
        Object value = null;
        try
        {
            Enumeration sessionAttributeNames = request.getSession().getAttributeNames();
            while (sessionAttributeNames.hasMoreElements())
            {
                key = sessionAttributeNames.nextElement().toString();
                value = request.getSession().getAttribute(key);
                sessionAttributes.put(key, value);
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            return sessionAttributes;
        }
    }
    private Map getRequestParametersAsHashMap(HttpServletRequest request) throws java.lang.Exception
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
                   	if (o != null && (o instanceof String) && o.toString().length() > 4096)
                   	{
                   		o = o.toString().substring(0, 4096);
                   	}
                    request.setAttribute(k, o);
                }
            }
        }
        catch (Exception e)
        {
            System.err.println("ERROR: HiltonServletController.setRequestParametersFromHashMap: " + e.toString());
            throw e;
        }
    }
    private void setSessionAttributesFromHashMap(Map map, HttpServletRequest request) throws java.lang.Exception
    {
        try
        {
            String key = null;
            Object value = null;
            Enumeration sessionAttributeNames = request.getSession().getAttributeNames();
            while (sessionAttributeNames.hasMoreElements())
            {
                key = sessionAttributeNames.nextElement().toString();
                value = request.getSession().getAttribute(key);
                request.getSession().removeAttribute(key);
            }
            Set keys = map.keySet();
            Iterator i = keys.iterator();
            while (i.hasNext())
            {
                key = (String) i.next();
                value = (Object) map.get(key);
                request.getSession().setAttribute(key, value);
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }
    
     private String validateOrganizationId(String oid) {
    	if (oid == null) {
    		return "";
    	}
    	
    	if (PropertiesManager.isValidOrganizationId(oid)) {
    		return oid;
    	} else {
    		return "";
    	}
    }
}

