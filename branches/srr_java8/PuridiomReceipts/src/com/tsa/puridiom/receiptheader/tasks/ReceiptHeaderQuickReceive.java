package com.tsa.puridiom.receiptheader.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

@SuppressWarnings(value = { "unchecked" })
public class ReceiptHeaderQuickReceive extends Task{

	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = new ArrayList();

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String organizationId = (String) incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");
			
			ReceiptHeader receiptHeader = (ReceiptHeader)incomingRequest.get("receiptHeader");
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			
			List poLineList = (List)incomingRequest.get("poLineList");
			List receiptLineList = (List)incomingRequest.get("receiptLineList");
			
			if(poLineList != null && receiptLineList != null)
			{
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
				PuridiomProcess process = processLoader.loadProcess("receiptline-quick-receive.xml");

				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("dbsession", dbs);
				newIncomingRequest.put("organizationId", organizationId);
				newIncomingRequest.put("userId", userId);
				newIncomingRequest.put("poHeader", poHeader);
				newIncomingRequest.put("receiptHeader", receiptHeader);

				for (int i = 0; i < receiptLineList.size(); i++) {

					ReceiptLine receiptLine = (ReceiptLine)receiptLineList.get(i);
					PoLine poLine = (PoLine)poLineList.get(i);

					newIncomingRequest.put("receiptLine", receiptLine);
					newIncomingRequest.put("poLine", poLine);

					process.executeProcess(newIncomingRequest);

				}

				String inspection = HiltonUtility.ckNull((String)newIncomingRequest.get("isRequiredOf"));
				
				if(inspection.equalsIgnoreCase("I")){
					
					incomingRequest.put("ReceiptHeader_receiptStatus", DocumentStatus.RCV_STEP_1);
				} else if(inspection.equalsIgnoreCase("M")){
					
					incomingRequest.put("ReceiptHeader_receiptStatus", DocumentStatus.RCV_STEP_2);
				} else {
				
					incomingRequest.put("ReceiptHeader_receiptStatus", DocumentStatus.RCV_RECEIVED);
					incomingRequest.put("PoHeader_status", DocumentStatus.RCV_RECEIVED);
				}
				
				incomingRequest.put("PoHeader_subType", "00");
				
				incomingRequest.put("historyStatus", "QR"); // Quick Receive
				incomingRequest.put("forwardedTo", UserManager.getInstance().getUserDisplayName(organizationId, userId)); // User

				this.setStatus(Status.SUCCEEDED) ;
			} else {
				Log.error(this, "ReceiptHeader has not been received because poLineList or receiptLineList is null");
				this.setStatus(Status.FAILED) ;
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e);
			throw e;
		}
		return result;
	}

}