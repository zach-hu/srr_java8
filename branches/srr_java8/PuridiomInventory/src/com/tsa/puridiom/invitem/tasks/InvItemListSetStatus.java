package com.tsa.puridiom.invitem.tasks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.ReceiptType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvBinLocation;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

@SuppressWarnings(value = { "unchecked" })
public class InvItemListSetStatus extends Task {
	public Object executeTask (Object object) throws Exception {
		Object result = null;
		try{
			Map incomingRequest = (Map)object;
			String oid = (String)incomingRequest.get("organizationId");
			String userId = (String)incomingRequest.get("userId");

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(oid);
			Map historyIncominRequest = null;

			String msrLineStatus = "";
			RequisitionLine msrLine = null;

//			List invItemList = (List)incomingRequest.get("invItemList");
			List msrLineList = (List)incomingRequest.get("msrLineList");
			List invBinLocationList = (List)incomingRequest.get("invBinLocationList");

			RequisitionHeader msrHeader = (RequisitionHeader)incomingRequest.get("msrHeader");
			ReceiptHeader receiptHeader = (ReceiptHeader)incomingRequest.get("receiptHeader");
			RequisitionLine requisitionLine = (RequisitionLine)incomingRequest.get("requisitionLine");
			PoLine poLine = (PoLine)incomingRequest.get("poLine");
			ReceiptLine receiptLine = (ReceiptLine)incomingRequest.get("receiptLine");

			String msrKit = HiltonUtility.ckNull(msrHeader.getKit());
			BigDecimal qtyInvetory = new BigDecimal(0);

			boolean is_inventory = false;

			if (receiptLine != null) {

				if(invBinLocationList != null && invBinLocationList.size() >= 0)
				{
					for (int i = 0; i < invBinLocationList.size(); i++) 
					{
						List invBinLocationSubList = (List)invBinLocationList.get(i);
						if(invBinLocationSubList != null && invBinLocationSubList.size() >= 0)
						{
							for (int k = 0; k < invBinLocationSubList.size(); k++) 
							{
								InvBinLocation invBinLocation = (InvBinLocation)invBinLocationSubList.get(k);
								qtyInvetory = qtyInvetory.add(invBinLocation.getQtyOnHand());
								
								if(receiptLine.getIcRecLine().compareTo(invBinLocation.getIcRecLine()) == 0)
								{
									is_inventory = true;
								}
							}
							//if(is_inventory) break;
						}
					}
				}
				

				if(requisitionLine != null && receiptHeader.getReceiptType().equals(ReceiptType.TRANSFER)){
					BigDecimal quatity = requisitionLine.getQuantity();
					BigDecimal qtyAcepted = receiptLine.getQtyAccepted();
					
					if (qtyAcepted.compareTo(quatity) < 0) {
						msrLineStatus = DocumentStatus.REQ_PARTIALLYKITTED;
					} else if (qtyAcepted.compareTo(quatity) >= 0) {
						msrLineStatus = DocumentStatus.REQ_KITTED;
					}
				}
				
				if(poLine != null){
					BigDecimal quatity = poLine.getQuantity();
					
					if (qtyInvetory.compareTo(quatity) < 0) {
						msrLineStatus = DocumentStatus.REQ_PARTIALLYKITTED;
	            	} else if (qtyInvetory.compareTo(quatity) >= 0) {
	            		msrLineStatus = DocumentStatus.REQ_KITTED;
		            }
				}

				if(msrLineList != null && msrLineList.size() >= 0){
					PuridiomProcess process = processLoader.loadProcess("requisitionline-history.xml");

					for (int j = 0; j < msrLineList.size(); j++) {
						msrLine = (RequisitionLine)msrLineList.get(j);
						
						if(receiptLine.getIcLineHistory().compareTo(msrLine.getIcLineHistory()) == 0 && is_inventory &&
								msrKit.equalsIgnoreCase("Y")){
							
							historyIncominRequest = new HashMap();
							historyIncominRequest.put("requisitionHeader", incomingRequest.get("msrHeader"));
							historyIncominRequest.put("organizationId", oid);
							historyIncominRequest.put("userId", userId);
							historyIncominRequest.put("dbsession", incomingRequest.get("dbsession"));
							
							if(receiptLine.getStatus().compareToIgnoreCase(DocumentStatus.RCV_INPROGRESS) > 0 && msrLineStatus.compareToIgnoreCase("") != 0 && msrLine.getStatus().compareToIgnoreCase(msrLineStatus) != 0){
								String newMsrLineStatus = "";
								if(msrLine.getStatus().compareToIgnoreCase(msrLineStatus) > 0){
									newMsrLineStatus = msrLine.getStatus(); 
								}
								msrLine.setStatus(msrLineStatus);
								historyIncominRequest.put("newHistoryRequisitionLine", msrLine);
								process.executeProcess(historyIncominRequest);
								
								if(!HiltonUtility.isEmpty(newMsrLineStatus)){
									msrLine.setStatus(newMsrLineStatus);
								}
							}

							/*if(receiptLine.getStatus().compareToIgnoreCase(DocumentStatus.RCV_PARTIALLYRECEIVED) == 0 && msrLine.getStatus().compareToIgnoreCase(DocumentStatus.REQ_PARTIALLYKITTED) != 0){
								String newMsrLineStatus = "";
								if(msrLine.getStatus().compareToIgnoreCase(DocumentStatus.REQ_RECEIVED) > 0){
									newMsrLineStatus = msrLine.getStatus(); 
								}
								msrLine.setStatus(DocumentStatus.REQ_PARTIALLYKITTED);
								historyIncominRequest.put("newHistoryRequisitionLine", msrLine);
								process.executeProcess(historyIncominRequest);
								
								if(!HiltonUtility.isEmpty(newMsrLineStatus)){
									msrLine.setStatus(newMsrLineStatus);
								}
							}
							
							if(receiptLine.getStatus().compareToIgnoreCase(DocumentStatus.RCV_RECEIVED) == 0 && msrLine.getStatus().compareToIgnoreCase(DocumentStatus.REQ_KITTED) != 0){
								String newMsrLineStatus = "";
								if(msrLine.getStatus().compareToIgnoreCase(DocumentStatus.REQ_RECEIVED) > 0){
									newMsrLineStatus = msrLine.getStatus(); 
								}
								msrLine.setStatus(DocumentStatus.REQ_KITTED);
								historyIncominRequest.put("newHistoryRequisitionLine", msrLine);
								process.executeProcess(historyIncominRequest);
								
								if(!HiltonUtility.isEmpty(newMsrLineStatus)){
									msrLine.setStatus(newMsrLineStatus);
								}
							}*/
							break;
						}
					}
				}
			} else {
				Log.debug(this, "MSR Line was not updated because ReceiptLine is empty!");
			}

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
