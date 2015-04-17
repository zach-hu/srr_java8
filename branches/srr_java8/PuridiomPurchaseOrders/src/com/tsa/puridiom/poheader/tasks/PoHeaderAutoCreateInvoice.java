package com.tsa.puridiom.poheader.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

@SuppressWarnings(value = { "unchecked" })
public class PoHeaderAutoCreateInvoice extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			String oid = (String)incomingRequest.get("organizationId");
			String userId = (String)incomingRequest.get("userId");
			List receiptHeaderList = (List) incomingRequest.get("receiptHeaderList");						

			PoHeaderRetrieveById poHeaderProcess = new PoHeaderRetrieveById();
			
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
//          PuridiomProcess poHeaderProcess = processLoader.loadProcess("poheader-retrieve-by-id.xml");
            PuridiomProcess invoiceCreateProcess = processLoader.loadProcess("invoice-autocreate-from-order.xml");
            PuridiomProcess invoiceRoutingList = processLoader.loadProcess("invoice-routing-services-list.xml");

            for (int i = 0; i < receiptHeaderList.size(); i++) {

            	ReceiptHeader receiptHeader = (ReceiptHeader) receiptHeaderList.get(i);
            	Log.debug(receiptHeader, "ReceiptHeader_icRecHeader = "+receiptHeader.getIcRecHeader());

            	String icPoHeader = receiptHeader.getIcPoHeader().toString();
            	Map newIncomingRequest = new HashMap();
            	newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
            	newIncomingRequest.put("PoHeader_icPoHeader", icPoHeader);

//				poHeaderProcess.executeProcess(newIncomingRequest);
//				PoHeader poHeader = (PoHeader)newIncomingRequest.get("poHeader");
            	PoHeader poHeader = (PoHeader) poHeaderProcess.executeTask(newIncomingRequest);

            	Log.debug(poHeader, "PoHeader_icPoHeader = "+ poHeader.getIcPoHeader());
            	Map newOtherIncomingRequest = new HashMap();
//				newOtherIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
            	newOtherIncomingRequest.put("organizationId", oid);
            	newOtherIncomingRequest.put("userId", userId);
            	newOtherIncomingRequest.put("PoHeader_icPoHeader", poHeader.getIcPoHeader().toString());
            	newOtherIncomingRequest.put("PoHeader_poNumber", poHeader.getPoNumber());
            	newOtherIncomingRequest.put("receiptHeader", receiptHeader);
            	newOtherIncomingRequest.put("ReceiptLine_icRecHeader", receiptHeader.getIcRecHeader().toString());

            	try {
            		invoiceCreateProcess.executeProcess(newOtherIncomingRequest);
            	} catch (Exception e) {
            		Log.error(this, e);
            		continue;
            	}

            	InvoiceHeader invoiceHeader = (InvoiceHeader) newOtherIncomingRequest.get("invoiceHeader");
            	if(invoiceHeader != null && invoiceCreateProcess.getStatus() == Status.SUCCEEDED)
            	{
            		Map incoming = new HashMap();
            		String  status = invoiceHeader.getStatus();
            		if(status.equalsIgnoreCase(DocumentStatus.IVC_APPROVING))
            		{
            			incoming.put("InvoiceHeader_icIvcHeader", invoiceHeader.getIcIvcHeader().toString());
            			incoming.put("organizationId", oid);
            			incoming.put("userId", userId);
            			incoming.put("formtype", "IVC");
            			incoming.put("invoiceaction", "FORWARD");

            			invoiceRoutingList.executeProcess(incoming);
            			this.setStatus(invoiceRoutingList.getStatus());
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