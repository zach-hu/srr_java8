package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ReceiptLineCreateReturn extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    /* Setup to create a Return for rejected items immediately after receiving */
		    Map updateParameters = new HashMap();
			
		    updateParameters.put("userId", incomingRequest.get("userId"));
		    updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
		    updateParameters.put("organizationId", incomingRequest.get("organizationId"));
		    updateParameters.put("createReturn", incomingRequest.get("createReturn"));
		    
		    updateParameters.put("ReceiptHeader_receiptType", "R");
            updateParameters.put("receiptMethod", "Return");
            updateParameters.put("createAction", "SAVE");
        	updateParameters.put("receiptAction", "FORWARD");
		    updateParameters.put("ReceiptHeader_notes", incomingRequest.get("returnReceiptHeader_notes"));
		    updateParameters.put("ReceiptHeader_pkgsReceived", incomingRequest.get("returnReceiptHeader_pkgsReceived"));
		    updateParameters.put("ReceiptHeader_packingSlip", incomingRequest.get("returnReceiptHeader_packingSlip"));
		    updateParameters.put("ReceiptHeader_carrierCode", incomingRequest.get("returnReceiptHeader_carrierCode"));
			updateParameters.put("ReceiptHeader_returnDate", incomingRequest.get("returnReceiptHeader_returnDate"));
			updateParameters.put("ReceiptHeader_icRecHeader", incomingRequest.get("returnReceiptHeader_icRecHeader"));
			updateParameters.put("ReceiptHeader_icPoHeader", incomingRequest.get("returnReceiptHeader_icPoHeader"));
			updateParameters.put("ReceiptLine_dispositionCode", incomingRequest.get("returnReceiptLine_dispositionCode"));
			updateParameters.put("ReceiptLine_receiptNotes", incomingRequest.get("returnReceiptLine_receiptNotes"));
			updateParameters.put("ReceiptLine_qtyReceived", incomingRequest.get("returnReceiptLine_qtyReceived"));
			updateParameters.put("ReceiptLine_qtyReturned", incomingRequest.get("returnReceiptLine_qtyReturned"));
			updateParameters.put("ReceiptLine_icPoLine", incomingRequest.get("returnReceiptLine_icPoLine"));
			updateParameters.put("ReceiptLine_icRecHeader", incomingRequest.get("returnReceiptLine_icRecHeader"));
			updateParameters.put("originalReceiptLine", incomingRequest.get("receiptLine"));
			updateParameters.put("originalReceiptHeader", incomingRequest.get("receiptHeader"));
			
			Set keySet = incomingRequest.keySet();
			Iterator iterator = keySet.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				if (key.indexOf("PoLine_") == 0 || key.indexOf("PoHeader_") == 0) {
				    //PoLine_ attributes are needed for asset and inventory updates
					Object obj =  incomingRequest.get(key);
					updateParameters.put(key, obj);
				}
			}
			
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			processLoader.setApplicationName(this.getApplicationName());
			PuridiomProcess process = processLoader.loadProcess("receipt-update.xml");
			
			process.executeProcess(updateParameters);
			
			ReceiptHeader receiptHeader = (ReceiptHeader) updateParameters.get("receiptHeader");
			ReceiptLine receiptLine = (ReceiptLine) updateParameters.get("receiptLine");
			
			incomingRequest.put("returnReceiptHeader", receiptHeader);
			incomingRequest.put("returnReceiptLine", receiptLine);
			
			this.status = process.getStatus();
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
