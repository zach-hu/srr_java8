/*
 * Created on March 13, 2008
 */
package com.tsa.puridiom.emails.tasks;

import java.util.Map;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;



public class EmailsActiveSetup extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            String organizationId = (String)incomingRequest.get("organizationId");
            String emailId = (String) incomingRequest.get("SendQueue_sendto");
            String status = (String)incomingRequest.get("SendQueue_status");
            UserProfile	user = UserManager.getInstance().getUserByMailId(organizationId, emailId);

            if (user!=null)
            {
            	if (!user.getEmailsActive().equalsIgnoreCase("Y"))
            	{
            		status = "07"; //This is the status to let Support know that this user does not have emails active.
            		incomingRequest.put("SendQueue_status", status);
            	}
            }

            this.status = Status.SUCCEEDED;
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new Exception("Exception in EmailsActiveSetup!" + e.toString());
        }

        return ret;
    }
}