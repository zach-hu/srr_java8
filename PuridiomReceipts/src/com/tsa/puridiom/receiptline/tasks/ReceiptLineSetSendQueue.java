package com.tsa.puridiom.receiptline.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.entity.InvBinLocation;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.sendqueue.tasks.SendQueueRetrieveBy;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

@SuppressWarnings(value = { "unchecked" })
public class ReceiptLineSetSendQueue extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			List invBinLocationList = (List) incomingRequest.get("invBinLocationList");
			RequisitionLine msrLine = (RequisitionLine) incomingRequest.get("msrLine");
			RequisitionHeader msrHeader = (RequisitionHeader) incomingRequest.get("msrHeader");
			String userId = (String)incomingRequest.get("userId");
			String oid = (String)incomingRequest.get("organizationId");
			
			if(invBinLocationList != null && msrLine != null && msrHeader != null){
			
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(oid);
				PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
				
				incomingRequest.put("SendQueue_doctype", "MSR");
				incomingRequest.put("SendQueue_docic", msrLine.getIcReqLine().toString());
				
				SendQueueRetrieveBy sendQueue = new SendQueueRetrieveBy();
				List sendQueueList = (List)sendQueue.executeTask(incomingRequest);
				
				if(sendQueueList.size()<=0){
					for (int i = 0; i < invBinLocationList.size(); i++) {
						InvBinLocation invBinLocation = (InvBinLocation)invBinLocationList.get(i);
						
						String subject = "MSR #"+msrLine.getRequisitionNumber()+" Line #"+msrLine.getLineNumber()+
										": Item #"+msrLine.getItemNumber()+" has been stored at "+invBinLocation.getItemLocation()+
										" - Bin: "+invBinLocation.getAisle()+"."+invBinLocation.getLocrow()+"."+invBinLocation.getTier()+"."+invBinLocation.getBin();
						
						String message = "\n\nMSR #\t\t:\t"+msrLine.getRequisitionNumber()+
										"\nItem #\t\t:\t"+msrLine.getItemNumber()+
										"\nQuantity\t:\t"+ invBinLocation.getQtyOnHand()+
										"\nLocation\t:\t"+invBinLocation.getItemLocation()+
										"\nBin\t\t\t:\t"+invBinLocation.getAisle()+"."+invBinLocation.getLocrow()+"."+invBinLocation.getTier()+"."+invBinLocation.getBin()+
										"\nDescription\t:\t"+msrLine.getDescription();
						
						Map newIncomingRequest = new HashMap();
						
						newIncomingRequest.put("userId", userId);
						newIncomingRequest.put("organizationId", oid);
						newIncomingRequest.put("SendQueue_doctype", "MSR");
						newIncomingRequest.put("SendQueue_docic", msrLine.getIcReqLine().toString());
						newIncomingRequest.put("SendQueue_subject", subject);
						newIncomingRequest.put("SendQueue_messagetext", message);
						newIncomingRequest.put("SendQueue_owner", userId );
						newIncomingRequest.put("SendQueue_sendfromtype", "E");
						newIncomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser(oid, userId).getMailId() );
						newIncomingRequest.put("SendQueue_sendtotype", "E");
						newIncomingRequest.put("SendQueue_sendto", UserManager.getInstance().getUser(oid, msrHeader.getOwner()).getMailId() );
						newIncomingRequest.put("SendQueue_action", EmailActionCodes.EMAIL);
						
			            process.executeProcess(newIncomingRequest);

					}
				}
			}
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}