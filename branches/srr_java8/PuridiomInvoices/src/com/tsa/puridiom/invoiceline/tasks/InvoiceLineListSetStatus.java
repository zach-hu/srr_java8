package com.tsa.puridiom.invoiceline.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

@SuppressWarnings(value = { "unchecked" })
public class InvoiceLineListSetStatus extends Task {
	public Object executeTask (Object object) throws Exception {
		Object result = null;
		try{
			Map incomingRequest = (Map)object;
			String oid = (String)incomingRequest.get("organizationId");
			String userId = (String)incomingRequest.get("userId");
			
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(oid);
			Map historyIncominRequest = null;
			
			InvoiceLine invoiceLine = null;
//			ReceiptLine receiptLine = null;
			PoLine poLine = null;
			RequisitionLine requisitionLine = null;
			RequisitionLine msrLine = null;
			
			List invoiceLineList = (List)incomingRequest.get("invoiceLineList");
//			List receiptLineList = (List)incomingRequest.get("receiptLineList");
			List poLineList = (List)incomingRequest.get("poLineList");
			List requisitionLineList = (List)incomingRequest.get("requisitionLineList");
			List msrLineList = (List)incomingRequest.get("msrLineList");
			
			for (int i = 0; i < invoiceLineList.size(); i++) {
				invoiceLine = (InvoiceLine)invoiceLineList.get(i);
				
//				if(receiptLineList != null && receiptLineList.size() >= 0){
//					receiptLine = (ReceiptLine)receiptLineList.get(i);
//					if(invoiceLine.getIcLineHistory().compareTo(receiptLine.getIcLineHistory()) == 0 && 
//							receiptLine.getStatus().compareToIgnoreCase(invoiceLine.getStatus()) != 0){
//						receiptLine.setStatus(invoiceLine.getStatus());
//					}
//				}
				
				if(poLineList != null && poLineList.size() >= 0){
					PuridiomProcess process = processLoader.loadProcess("poline-history.xml");
					
					poLine = (PoLine)poLineList.get(i);
					if(invoiceLine.getIcLineHistory().compareTo(poLine.getIcLineHistory()) == 0 && 
							poLine.getStatus().compareToIgnoreCase(invoiceLine.getStatus()) != 0){
						
						poLine.setStatus(invoiceLine.getStatus());
						
						historyIncominRequest = new HashMap();
						historyIncominRequest.put("newHistoryPoLine", poLine);
						historyIncominRequest.put("poHeader", incomingRequest.get("poHeader"));
						historyIncominRequest.put("organizationId", oid);
						historyIncominRequest.put("userId", userId);
						historyIncominRequest.put("dbsession", incomingRequest.get("dbsession"));
						process.executeProcess(historyIncominRequest);
					}
				}
				
				if(requisitionLineList != null && requisitionLineList.size() >= 0){
					PuridiomProcess process = processLoader.loadProcess("requisitionline-history.xml");
					
					requisitionLine = (RequisitionLine)requisitionLineList.get(i);
					if(invoiceLine.getIcLineHistory().compareTo(requisitionLine.getIcLineHistory()) == 0 && 
							requisitionLine.getStatus().compareToIgnoreCase(invoiceLine.getStatus()) != 0){
						
						requisitionLine.setStatus(invoiceLine.getStatus());
						
						historyIncominRequest = new HashMap();
						historyIncominRequest.put("newHistoryRequisitionLine", requisitionLine);
						historyIncominRequest.put("requisitionHeader", incomingRequest.get("requisitionHeader"));
						historyIncominRequest.put("organizationId", oid);
						historyIncominRequest.put("userId", userId);
						historyIncominRequest.put("dbsession", incomingRequest.get("dbsession"));
						process.executeProcess(historyIncominRequest);
					}
				}
				
				if(msrLineList != null && msrLineList.size() >= 0){
					PuridiomProcess process = processLoader.loadProcess("requisitionline-history.xml");
					
					for (int j = 0; j < msrLineList.size(); j++) {
						msrLine = (RequisitionLine)msrLineList.get(j);
						
						if(invoiceLine.getIcLineHistory().compareTo(msrLine.getIcLineHistory()) == 0 && 
								msrLine.getStatus().compareToIgnoreCase(invoiceLine.getStatus()) != 0){
							
							msrLine.setStatus(invoiceLine.getStatus());
							
							historyIncominRequest = new HashMap();
							historyIncominRequest.put("newHistoryRequisitionLine", msrLine);
							historyIncominRequest.put("requisitionHeader", incomingRequest.get("msrHeader"));
							historyIncominRequest.put("organizationId", oid);
							historyIncominRequest.put("userId", userId);
							historyIncominRequest.put("dbsession", incomingRequest.get("dbsession"));
							process.executeProcess(historyIncominRequest);
						}
					}
				}
			}
			
			incomingRequest.put("invoiceLineList", invoiceLineList);
//			incomingRequest.put("receiptLineList", receiptLineList);
			incomingRequest.put("poLineList", poLineList);
			incomingRequest.put("requisitionLineList", requisitionLineList);
			incomingRequest.put("msrLineList", msrLineList);
			
			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e) {
			this.setStatus(Status.FAILED);
			Log.error(this, e);
			e.printStackTrace();
		}
		return result;
	}
}
