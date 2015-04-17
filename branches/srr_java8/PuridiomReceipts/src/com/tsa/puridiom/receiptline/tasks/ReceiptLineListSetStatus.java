package com.tsa.puridiom.receiptline.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

@SuppressWarnings(value = { "unchecked" })
public class ReceiptLineListSetStatus extends Task {
	public Object executeTask (Object object) throws Exception {
		Object result = null;
		try{
			Map incomingRequest = (Map)object;
			String oid = (String)incomingRequest.get("organizationId");
			String userId = (String)incomingRequest.get("userId");
			
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(oid);
			Map historyIncominRequest = null;
			
			ReceiptLine receiptLine = null;
			PoLine poLine = null;
			RequisitionLine requisitionLine = null;
			RequisitionLine msrLine = null;
			
			List receiptLineList = (List)incomingRequest.get("receiptLineList");
			List poLineList = (List)incomingRequest.get("poLineList");
			//List invBinLocationList = (List)incomingRequest.get("invBinLocationList");
			List requisitionLineList = (List)incomingRequest.get("requisitionLineList");
			List msrLineList = (List)incomingRequest.get("msrLineList");
			
			//RequisitionHeader msrHeader = (RequisitionHeader)incomingRequest.get("msrHeader");
			
			/*String msrKit = "N";
			boolean is_kitted = false;
			
			if(msrHeader != null){
				msrKit = HiltonUtility.ckNull(msrHeader.getKit());
			}*/
			
			for (int i = 0; i < receiptLineList.size(); i++) {
				receiptLine = (ReceiptLine)receiptLineList.get(i);
				/* **** commented because the kitted status is no updating since receipt, uncomment if this is necessary (uncomment in rec-documents-retrieve.xml too) ****
				 * is_kitted = false;
				
				if(invBinLocationList != null && invBinLocationList.size() > 0)
				{
					for (int j = 0; j < invBinLocationList.size(); j++) 
					{
						List invBinLocationSubList = (List)invBinLocationList.get(j);
						if(invBinLocationSubList != null && invBinLocationSubList.size() > 0)
						{
							for (int k = 0; k < invBinLocationSubList.size(); k++) 
							{
								InvBinLocation invBinLocation = (InvBinLocation)invBinLocationSubList.get(k);
								
								if(receiptLine.getIcRecLine().compareTo(invBinLocation.getIcRecLine()) == 0)
								{
									is_kitted = true;
									break;
								}
							}
							if(is_kitted) break;
						}
					}
				}*/
				
				if(poLineList != null && poLineList.size() >= 0 && receiptLine.getStatus().compareTo(DocumentStatus.RCV_RECEIVED) == 0){
					PuridiomProcess process = processLoader.loadProcess("poline-history.xml");
					
					poLine = (PoLine)poLineList.get(i);
					if(receiptLine.getIcLineHistory().compareTo(poLine.getIcLineHistory()) == 0 && 
							poLine.getStatus().compareToIgnoreCase(DocumentStatus.RCV_RECEIVED) < 0){
						
						poLine.setStatus(DocumentStatus.RCV_RECEIVED);
						
						historyIncominRequest = new HashMap();
						historyIncominRequest.put("newHistoryPoLine", poLine);
						historyIncominRequest.put("poHeader", incomingRequest.get("poHeader"));
						historyIncominRequest.put("organizationId", oid);
						historyIncominRequest.put("userId", userId);
						historyIncominRequest.put("dbsession", incomingRequest.get("dbsession"));
						process.executeProcess(historyIncominRequest);
					}
				}
				
				if(requisitionLineList != null && requisitionLineList.size() >= 0 && receiptLine.getStatus().compareTo(DocumentStatus.RCV_RECEIVED) == 0){
					PuridiomProcess process = processLoader.loadProcess("requisitionline-history.xml");
					
					requisitionLine = (RequisitionLine)requisitionLineList.get(i);
					if(receiptLine.getIcLineHistory().compareTo(requisitionLine.getIcLineHistory()) == 0 && 
							requisitionLine.getStatus().compareToIgnoreCase(DocumentStatus.RCV_RECEIVED) < 0){
						
						requisitionLine.setStatus(DocumentStatus.RCV_RECEIVED);
						
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
						
						if(receiptLine.getIcLineHistory().compareTo(msrLine.getIcLineHistory()) == 0){
							
							historyIncominRequest = new HashMap();
							historyIncominRequest.put("requisitionHeader", incomingRequest.get("msrHeader"));
							historyIncominRequest.put("organizationId", oid);
							historyIncominRequest.put("userId", userId);
							historyIncominRequest.put("dbsession", incomingRequest.get("dbsession"));
							
							if(receiptLine.getStatus().compareTo(DocumentStatus.RCV_PARTIALLYRECEIVED) == 0 &&
									msrLine.getStatus().compareToIgnoreCase(DocumentStatus.REQ_PARTIALLYRECEIVED) != 0){
								String msrLineStatus = "";
								if(msrLine.getStatus().compareToIgnoreCase(DocumentStatus.REQ_PARTIALLYRECEIVED) > 0 && msrLine.getStatus().compareToIgnoreCase(DocumentStatus.REQ_APPROVED) != 0){
									msrLineStatus = msrLine.getStatus(); 
								}
								msrLine.setStatus(DocumentStatus.REQ_PARTIALLYRECEIVED);
								historyIncominRequest.put("newHistoryRequisitionLine", msrLine);
								process.executeProcess(historyIncominRequest);
								
								if(!HiltonUtility.isEmpty(msrLineStatus)){
									msrLine.setStatus(msrLineStatus);
								}
							}
							
							if(receiptLine.getStatus().compareTo(DocumentStatus.RCV_RECEIVED) == 0 &&
									msrLine.getStatus().compareToIgnoreCase(DocumentStatus.REQ_RECEIVED) != 0){
								String msrLineStatus = "";
								if(msrLine.getStatus().compareToIgnoreCase(DocumentStatus.REQ_RECEIVED) > 0 && msrLine.getStatus().compareToIgnoreCase(DocumentStatus.REQ_APPROVED) != 0){
									msrLineStatus = msrLine.getStatus(); 
								}
								msrLine.setStatus(DocumentStatus.REQ_RECEIVED);
								historyIncominRequest.put("newHistoryRequisitionLine", msrLine);
								process.executeProcess(historyIncominRequest);
								
								if(!HiltonUtility.isEmpty(msrLineStatus)){
									msrLine.setStatus(msrLineStatus);
								}
							}
							
							/*if(msrKit.equalsIgnoreCase("Y") && is_kitted && msrLine.getStatus().compareToIgnoreCase(DocumentStatus.REQ_KITTED) < 0){
								msrLine.setStatus(DocumentStatus.REQ_KITTED);
								
								historyIncominRequest.put("newHistoryRequisitionLine", msrLine);
								process.executeProcess(historyIncominRequest);
							}
							
							if(!msrKit.equalsIgnoreCase("Y") && receiptLine.getStatus().compareTo(DocumentStatus.RCV_RECEIVED) == 0 && msrLine.getStatus().compareToIgnoreCase(DocumentStatus.REQ_CLOSED) < 0){
								msrLine.setStatus(DocumentStatus.REQ_CLOSED);
								
								historyIncominRequest.put("newHistoryRequisitionLine", msrLine);
								process.executeProcess(historyIncominRequest);
							}*/
							
							break;
						}
					}
				}
			}
			
			incomingRequest.put("receiptLineList", receiptLineList);
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
