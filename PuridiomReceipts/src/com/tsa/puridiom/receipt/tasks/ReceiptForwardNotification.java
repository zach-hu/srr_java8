package com.tsa.puridiom.receipt.tasks;

import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.mail.SendMail;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceiptForwardNotification extends Task{

	public Object  executeTask (Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map)object;
			String	userId = (String) incomingRequest.get("userId") ;
			String	organizationId = (String) incomingRequest.get("organizationId") ;
			ReceiptHeader receiptHeader = (ReceiptHeader) incomingRequest.get("receiptHeader") ;
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			UserProfile receiptOwner = UserManager.getInstance().getUser(organizationId, receiptHeader.getOwner());
			String	message = "Receipt # " + receiptHeader.getReceiptNumber() + " is pending your finalization for Order # " + receiptHeader.getRefNumber() + ".\n\n" +
				"  Packing Slip #: " + receiptHeader.getPackingSlip() + ".\n" +
				"  # of Packages Received: " + receiptHeader.getPkgsReceived() + ".\n\n";
			message += "Notes: " + receiptHeader.getReceiptNotes()+".\n" ;

			String sendTo="";

			if (receiptHeader.getForwardTo().equals("END-USERS") && poHeader != null)
			{
				List	poLineList = poHeader.getPoLineList();
				Map users = new HashMap();

				if (poLineList != null)
				{
					for (int il = 0; il < poLineList.size(); il++)
					{
						PoLine poLine = (PoLine) poLineList.get(il);
						String	endUserId = poLine.getRequisitionerCode();
						if (Utility.isEmpty(endUserId))
						{
							endUserId = poHeader.getRequisitionerCode();
						}
						if (Utility.isEmpty(endUserId))
						{
							endUserId = poHeader.getOwner();
						}
						if (!users.containsKey(endUserId))
						{
							users.put(endUserId, String.valueOf(il));
							UserProfile endUser = UserManager.getInstance().getUser(organizationId, endUserId);

							if (!Utility.isEmpty(endUser.getMailId()))
							{
								sendTo = endUser.getMailId();
							}
						}
					}
				}
			}
			else if (!Utility.isEmpty(receiptHeader.getForwardTo()))
			{
				UserProfile endUser = UserManager.getInstance().getUser(organizationId, receiptHeader.getForwardTo());

				if (!Utility.isEmpty(endUser.getMailId()))
				{
					sendTo = endUser.getMailId();
				}
			}

			incomingRequest.put("SendQueue_doctype", "REC");
            incomingRequest.put("SendQueue_docic", receiptHeader.getIcRecHeader().toString());
            incomingRequest.put("SendQueue_subject","Receipt Notification" );
            incomingRequest.put("SendQueue_sendfromtype", "E");
            incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser(organizationId, userId).getMailId() );
            incomingRequest.put("SendQueue_sendtotype", "E");
            incomingRequest.put("SendQueue_sendto", sendTo);
            incomingRequest.put("SendQueue_action", "EN");
            incomingRequest.put("SendQueue_messagetext", message.toString());

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
            process.executeProcess(incomingRequest);
            this.status = process.getStatus();

		}
		catch (Exception e) {
			this.status = Status.FAILED;
			throw e;
		}

		return null ;
	}

}