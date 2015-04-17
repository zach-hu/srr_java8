package com.tsa.puridiom.invapprovals.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvItem;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class QueueAddInvRejectEmail extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
        	DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
            String newStatus = (String)incomingRequest.get("newStatus") ;
            String rejectedBy = (String)incomingRequest.get("rejectedBy") ;
            InvItem invItem = (InvItem) incomingRequest.get("invItem");
            String notes = HiltonUtility.ckNull((String) incomingRequest.get("ApprovalLog_approverNotes"));
            String organizationId = (String)incomingRequest.get("organizationId");
            StringBuffer sendTo = new StringBuffer();
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
            UserProfile user = (UserProfile) UserManager.getInstance().getUser(organizationId, rejectedBy);
            if(user == null)
            {
            	user = new UserProfile();
            }

            Log.debug(this, "Writting send_queue record for item: " + invItem.getItemNumber() + ", status " + newStatus);

            if(newStatus.equals(DocumentStatus.INV_RETURNED))
            {
                incomingRequest.put("SendQueue_doctype", "INV");
                incomingRequest.put("SendQueue_docic", invItem.getIcHeaderHistory().toString());

                StringBuffer subject = new StringBuffer("");
                subject.append("Inventory Item ");
                subject.append(invItem.getItemNumber()) ;
                subject.append(" reject by " + user.getFirstName() + "-" + user.getLastName() + ".");

                incomingRequest.put("SendQueue_subject",subject.toString() );
                incomingRequest.put("SendQueue_sendfromtype", "E");
                incomingRequest.put("SendQueue_sendfrom", user.getMailId());
                
                StringBuffer rejectMessage = new StringBuffer();
                rejectMessage.append("Approver Notes:\n");
                rejectMessage.append(notes);
                incomingRequest.put("SendQueue_messagetext", rejectMessage.toString());
                String owner = HiltonUtility.ckNull(invItem.getOwner());
                if (Utility.isEmpty(sendTo.toString())) {
                	this.setSendToEmailFromUser(sendTo, owner, organizationId);
                }
                incomingRequest.put("SendQueue_sendtotype", "E");
                incomingRequest.put("SendQueue_sendto", sendTo.toString());
                incomingRequest.put("SendQueue_action", EmailActionCodes.EMAIL);

                process.executeProcess(incomingRequest);
                this.status = process.getStatus() ;
            }    
            else
            {
                this.setStatus(Status.SUCCEEDED);
                Log.debug(this, "No record will be written for this Invoice: " + invItem.getItemNumber());
            }
        }
        catch (Exception e)
        {
           this.setStatus(Status.FAILED);
            throw new TsaException("Error processing HTML approvals.");
        }
        return result;
    }

    private void setSendToEmailFromUser(StringBuffer sendTo, String nextUser, String organizationId) throws Exception
	{
		String[] users = nextUser.split(";");

		for (int i = 0; i < users.length; i++)
		{
			String user = users[i];

			if (!HiltonUtility.isEmpty(user))
			{
				sendTo.append(UserManager.getInstance().getUser(organizationId, user).getMailId() + ";");
			}
		}

	}
}