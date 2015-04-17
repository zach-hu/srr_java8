package com.tsa.puridiom.receipt.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

@SuppressWarnings(value = { "unchecked" })
public class QueueAddQuickReceive extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			String organizationId = (String)incomingRequest.get("organizationId") ;
			String owner = (String) incomingRequest.get("userId") ;

			ReceiptHeader receiptHeader = (ReceiptHeader)incomingRequest.get("receiptHeader");
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");

			List poLineList = (List)incomingRequest.get("poLineList");
			List receiptLineList = (List)incomingRequest.get("receiptLineList");

			PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;
			String supportEmail = propertiesManager.getProperty("MAILEVENTS", "ADMINEMAILADDR", "procurement@srr.gov");
			String userEmail = UserManager.getInstance().getUser(organizationId, poHeader.getUdf6Code()).getMailId();

			StringBuffer subject = new StringBuffer("Puridiom Alert. Receipt Notification PO Number: ");
			subject.append(poHeader.getPoNumber()) ;

			StringBuffer nextUser = new StringBuffer(supportEmail);
			if(!HiltonUtility.isEmpty(userEmail)){
				nextUser.append(";" + HiltonUtility.ckNull(userEmail));
			}
			
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
			
			if(poLineList != null && receiptLineList != null)
			{
				for (int i = 0; i < receiptLineList.size(); i++) {
					
					PoLine poLine = (PoLine) poLineList.get(i);
					
					StringBuffer message = new StringBuffer("\n\nPO Number: " + poHeader.getPoNumber() + "\n");
					message.append("Receipt Date: " + receiptHeader.getReceiptDate() + "\n") ;
					message.append("Description: " + poLine.getDescription() + "\n") ;
					message.append("Quantity: " + poLine.getQtyReceived() + " (" + poLine.getUmCode() + ")\n\n");

					incomingRequest.put("SendQueue_doctype", "REC");
					incomingRequest.put("SendQueue_docic", receiptHeader.getIcRecHeader().toString());
					incomingRequest.put("SendQueue_subject", subject.toString() );
					incomingRequest.put("SendQueue_messagetext", message.toString() );
					incomingRequest.put("SendQueue_owner", owner );
					incomingRequest.put("SendQueue_sendfromtype", "E");
					incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser(organizationId, owner).getMailId() );
					incomingRequest.put("SendQueue_sendtotype", "E");
					incomingRequest.put("SendQueue_sendto", nextUser.toString());
					incomingRequest.put("SendQueue_action", EmailActionCodes.EMAIL);

					process.executeProcess(incomingRequest);
				}
			}
			
			this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
           this.setStatus(Status.FAILED);
           Log.error(this, e);
           throw new TsaException("Quick Receipt Email couldn't be processed.", e);
        }
        return result;
    }
}