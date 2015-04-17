package com.tsa.puridiom.receipt.inspectorassignment.tasks;

import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AssignmentNotificationByLine extends Task {

    public Object  executeTask (Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map)object;
            String	assignTo = (String) incomingRequest.get("assignTo");
            String  userTimeZone = (String) incomingRequest.get("userTimeZone");
            Map receipts = (Map) incomingRequest.get("receiptsToNotify");


			//  Iterate the requisitionsToNotify to send the emmails (one by each Requisition)
            Iterator i = receipts.keySet().iterator();
            while(i.hasNext())
            {
            	BigDecimal icRecHeader = (BigDecimal) i.next();
				List recLines = (List) receipts.get(icRecHeader);
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("receiptheader-retrieve-by-id.xml");
				incomingRequest.put("ReceiptHeader_icRecHeader", icRecHeader.toString());
				process.executeProcess(incomingRequest);

				if (process.getStatus() < Status.SUCCEEDED) {
					throw new Exception("Receipt Notification failed getting for icRec " + icRecHeader + ". ");
				}
				else {
					//Send an email notification by each Requisition

					if (Utility.isEmpty(assignTo)) {
		                // Notification cannot be sent
		                throw new Exception("The notification cannot be sent because the assigned inspector user id was not specified (assignTo).");
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
		            ReceiptHeader receiptHeader =  (ReceiptHeader) incomingRequest.get("receiptHeader");

		            String assignType = (String)incomingRequest.get("assignType");
		            if(assignType ==  null)
		            {
		            	assignType = "MANUAL";
		            }

		            UserProfile assignedInspector = UserManager.getInstance().getUser(organizationId, assignTo);
		            UserProfile owner = UserManager.getInstance().getUser(organizationId, receiptHeader.getOwner());
		            StringBuffer	message = new StringBuffer();
		            StringBuffer	sendTo = new StringBuffer();
		            String	sendFrom = UserManager.getInstance().getUser(organizationId, userId).getMailId();

		            if (Utility.isEmpty(sendFrom)) {
		                sendFrom = PropertiesManager.getInstance(organizationId).getProperty("MAILEVENTS", "AdminEmailAddr","");
		            }
		            if (!Utility.isEmpty(assignedInspector.getMailId())) {
		                sendTo.append(assignedInspector.getMailId());
		            }
		            if (!Utility.isEmpty(owner.getMailId())) {
		                if (sendTo.length() > 0) {
		                    sendTo.append(";" + owner.getMailId());
		                } else {
		                    sendTo.append(owner.getMailId());
		                }
		            }

		            String pattern = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DateFormat", "yyyy-MM-dd") + " hh:mm:ss a";
	                message.append("The following items listed on Receipt " +
	                		receiptHeader.getReceiptNumber() +  " were assigned to " +
	                		assignedInspector.getDisplayName() + " on " +
	                		Dates.today(pattern, userTimeZone) + " " + userTimeZone + ".\r\n");

					for (int j = 0; j < recLines.size(); j++)
					{
						String recLine = (String) recLines.get(j);
						process = processLoader.loadProcess("receiptline-retrieve-by-id.xml");
						incomingRequest.put("ReceiptLine_icRecLine", recLine);
						process.executeProcess(incomingRequest);

						if (process.getStatus() < Status.SUCCEEDED) {
							throw new Exception("Receipt Notification failed getting for icRec " + icRecHeader + ". ");
						}
						else {
							// add to the message the information about this line
							ReceiptLine receiptLine =  (ReceiptLine) incomingRequest.get("receiptLine");
							UserProfile receiver = UserManager.getInstance().getUser(organizationId, receiptLine.getInspectorAssigned());
							if (!Utility.isEmpty(receiver.getMailId()) && assignType.equals("MANUAL")) {
								if (sendTo.length() > 0) {
									sendTo.append(";" + receiver.getMailId());
								} else {
									sendTo.append(receiver.getMailId());
								}
							}

//							if (!Utility.isEmpty(assignedInspector.getMailId())) {
//								message.append("\r\n");
//								message.append(receiptLine.getLineNumber() + "." + "  ");
//								if (!Utility.isEmpty(receiptLine.getItemNumber())) {
//									message.append(receiptLine.getItemNumber() + " - ");
//								}
//								message.append(receiptLine.getDescription());
//								message.append("\r");
//							}
						}
					}
					incomingRequest.put("SendQueue_subject","Assignment Notification: Receipt " + receiptHeader.getReceiptNumber());
					incomingRequest.put("SendQueue_sendfromtype", "E");
					incomingRequest.put("SendQueue_sendfrom", sendFrom);
					incomingRequest.put("SendQueue_sendtotype", "E");
					incomingRequest.put("SendQueue_action", "EN");
					incomingRequest.put("SendQueue_owner", userId);
					incomingRequest.put("SendQueue_doctype", "REC");
					incomingRequest.put("SendQueue_docic", String.valueOf(receiptHeader.getIcRecHeader()));
					incomingRequest.put("SendQueue_sendto", sendTo.toString());
					incomingRequest.put("SendQueue_messagetext", message.toString());

					process = processLoader.loadProcess("sendqueue-add.xml");
					process.executeProcess(incomingRequest);
					this.status = process.getStatus() ;

					if (this.getStatus() != Status.SUCCEEDED) {
						throw new Exception("The email record could not be written to the queue.");
					}

				}
            }
            /*

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
            RequisitionLine requisitionLine = (RequisitionLine) incomingRequest.get("requisitionLine");
            String assignType = (String)incomingRequest.get("assignType");
            if(assignType ==  null)
            {
                assignType = "MANUAL";
            }
            UserProfile assignedBuyer = UserManager.getInstance().getUser(organizationId, assignTo);
            UserProfile requisitioner = UserManager.getInstance().getUser(organizationId, requisitionLine.getRequisitionerCode());
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
                message.append("The following item listed on Requisition " + requisitionHeader.getRequisitionNumber() +  " was assigned to " + assignedBuyer.getDisplayName() + " on " + HiltonUtility.getFormattedDate(requisitionHeader.getAssignedDate(), organizationId) + ".");
                message.append("\r");
                message.append("\r");
                message.append(requisitionLine.getLineNumber() + "." + "  ");
                if (!Utility.isEmpty(requisitionLine.getItemNumber())) {
                    message.append(requisitionLine.getItemNumber() + " - ");
                }
                message.append(requisitionLine.getDescription());
                message.append("\r");

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
            */

            this.status = Status.SUCCEEDED;
        }
        catch (Exception e) {
            this.status = Status.FAILED;
            throw e;
        }

        return null ;
    }

}