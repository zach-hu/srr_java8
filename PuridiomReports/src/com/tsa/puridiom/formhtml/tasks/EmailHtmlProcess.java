/*
 * Created on May 18, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.formhtml.tasks;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

//import com.tsa.puridiom.punchoutcatalog.container.SendQueue;
import com.tsa.puridiom.entity.SendQueue;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.jasperreports.ReportUtils;
import com.tsa.puridiom.usermanager.UserManager;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Utility;


public class EmailHtmlProcess
{
   public Map createMe(Map incomingRequest, String organizationId) throws Exception
    {
	     PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
	 	 PuridiomProcess process = null;
             try
    		{
        	 	//incomingRequest.put("sendQueue", sendQueue);

            	 //processLoader.setApplicationName(this.getApplicationName());
				process = processLoader.loadProcess("html-email-setup.xml");

				process.executeProcess(incomingRequest);




    			if (process.getStatus() == Status.SUCCEEDED)
    			{
    				incomingRequest.put("viewPage", (String) incomingRequest.get("successPage"));
    			}
    			else
    			{
    				incomingRequest.put("viewPage", (String) incomingRequest.get("failurePage"));
    			}
    		}
    		catch (Exception exception)
    		{
    			incomingRequest.put("errorMsg", exception.getMessage());
    			incomingRequest.put("viewPage", (String) incomingRequest.get("failurePage"));
    			throw exception;
    		}
    		finally
    		{
    			if (incomingRequest.get("viewPage") == null)
    			{
    				incomingRequest.put("viewPage", (String) incomingRequest.get("failurePage"));
    			}
    		}
    		return incomingRequest;





        }
}
