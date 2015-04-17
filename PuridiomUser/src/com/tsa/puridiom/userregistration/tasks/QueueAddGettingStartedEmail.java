package com.tsa.puridiom.userregistration.tasks;

import java.util.Map;
import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class QueueAddGettingStartedEmail extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            String organizationId = (String)incomingRequest.get("UserProfile_organizationId") ;
            String userId = (String)incomingRequest.get("UserProfile_userId") ;
        	UserProfile userProfile = UserManager.getInstance().getUser(organizationId, userId);

            if(Utility.isEmpty(userId))
            {
            	this.setStatus(Status.FAILED);
            	throw new TsaException(this.getName() + " User ID was not found!");
            }

            Log.debug(this, "Writting send_queue record for Getting Started email; User ID: " + userId + ", Organization ID " + organizationId);


            StringBuffer subject = new StringBuffer("Getting started with Puridiom 4.0 e-procurement!");

            String	duplicateNotification = (String) incomingRequest.get("duplicateNotification") ;
            if (!Utility.isEmpty(duplicateNotification) && duplicateNotification.equalsIgnoreCase("Y")) {
                subject.append(" [Reminder]") ;
            }

            incomingRequest.put("SendQueue_doctype", "UGS");
            incomingRequest.put("SendQueue_docic", "");
            incomingRequest.put("SendQueue_subject",subject.toString() );
            incomingRequest.put("SendQueue_sendfromtype", "E");
            incomingRequest.put("SendQueue_sendfrom", "support@puridiom.com" );
            incomingRequest.put("SendQueue_sendtotype", "E");
            incomingRequest.put("SendQueue_sendto", userProfile.getMailId());
            incomingRequest.put("SendQueue_action", EmailActionCodes.HTMLEMAIL);

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
            process.executeProcess(incomingRequest);
            this.status = process.getStatus() ;
        }
        catch (Exception e)
        {
           this.setStatus(Status.FAILED);
            throw new TsaException("Error processing HTML approvals.");
        }
        return result;
    }
}