package com.tsa.puridiom.requisition.buyerassignment.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

import java.util.List;
import java.util.Map;

public class AssignmentNotificationByHeader extends Task{

	public Object  executeTask (Object object) throws Exception
	{
		try
		{

			Map incomingRequest = (Map)object;
			String	assignTo = (String) incomingRequest.get("assignTo");

			if (Utility.isEmpty(assignTo)) {
			    // Notification cannot be sent
			    throw new Exception("The notification cannot be sent because the assigned buyer user id was not specified (assignTo).");
			}

			if (assignTo.equalsIgnoreCase("AUTORELEASE")) {
			    // Notification should not be sent
			    this.setStatus(Status.SUCCEEDED);
			    return null;
			}

			if (assignTo.equalsIgnoreCase("UNASSIGNED")) {
			    assignTo = "PURCHASING";
			}

			String	userId = (String) incomingRequest.get("userId") ;
			String	organizationId = (String) incomingRequest.get("organizationId") ;
			RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			List requisitionLineList = (List) incomingRequest.get("requisitionLineList");
            String assignType = (String)incomingRequest.get("assignType");
            if(assignType ==  null)
            {
                assignType = "MANUAL";
            }
			UserProfile assignedBuyer = UserManager.getInstance().getUser(organizationId, assignTo);
			UserProfile requisitioner = UserManager.getInstance().getUser(organizationId, requisitionHeader.getRequisitionerCode());
			UserProfile owner = UserManager.getInstance().getUser(organizationId, requisitionHeader.getOwner());
			StringBuffer	message = new StringBuffer();
			StringBuffer	sendTo = new StringBuffer();
            String	sendFrom = UserManager.getInstance().getUser(organizationId, userId).getMailId();

            if (Utility.isEmpty(sendFrom)) {
                sendFrom = PropertiesManager.getInstance(organizationId).getProperty("MAILEVENTS", "AdminEmailAddr","");
            }
			if (!Utility.isEmpty(assignedBuyer.getMailId())) {
			    sendTo.append(assignedBuyer.getMailId());
			}
			if (!Utility.isEmpty(requisitioner.getMailId()) && assignType.equals("MANUAL")) {
			    if (sendTo.length() > 0) {
			        sendTo.append(";" + requisitioner.getMailId());
			    } else {
			        sendTo.append(requisitioner.getMailId());
			    }
			}
			if (!Utility.isEmpty(owner.getMailId())) {
			    if (sendTo.length() > 0) {
			        sendTo.append(";" + owner.getMailId());
			    } else {
			        sendTo.append(owner.getMailId());
			    }
			}

		    if (!Utility.isEmpty(assignedBuyer.getMailId())) {
		        message.append("Request " + requisitionHeader.getRequisitionNumber() +  " was assigned to " + assignedBuyer.getDisplayName() + " on " + HiltonUtility.getFormattedDate(requisitionHeader.getAssignedDate(), organizationId) + ".");
		        message.append("\r");
		        message.append("\r");
		        message.append("This request contains the following items:");
		        message.append("\r");

		        for (int il = 0; il < requisitionLineList.size(); il++) {
		            RequisitionLine requisitionLine = (RequisitionLine) requisitionLineList.get(il);
		            message.append(requisitionLine.getLineNumber() + "." + "  ");
		            if (!Utility.isEmpty(requisitionLine.getItemNumber())) {
		                message.append(requisitionLine.getItemNumber() + " - ");
		            }
		            message.append(requisitionLine.getDescription());
			        message.append("\r");
		        }

				incomingRequest.put("SendQueue_subject","Assignment Notification: Requisition " + requisitionHeader.getRequisitionNumber());
				incomingRequest.put("SendQueue_sendfromtype", "E");
				incomingRequest.put("SendQueue_sendfrom", sendFrom);
				incomingRequest.put("SendQueue_sendtotype", "E");
				incomingRequest.put("SendQueue_action", "EN");
				incomingRequest.put("SendQueue_messagetext", message.toString());
				incomingRequest.put("SendQueue_owner", userId);
				incomingRequest.put("SendQueue_doctype", "REQ");
				incomingRequest.put("SendQueue_docic", String.valueOf(requisitionHeader.getIcReqHeader()));
				incomingRequest.put("SendQueue_sendto", sendTo.toString());

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
				process.executeProcess(incomingRequest);
				this.status = process.getStatus() ;

				if (this.getStatus() != Status.SUCCEEDED) {
				    throw new Exception("The email record could not be written to the queue.");
				}
			}

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e) {
			this.status = Status.FAILED;
			throw e;
		}

		return null ;
	}

}