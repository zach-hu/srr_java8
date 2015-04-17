package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.common.documents.RequisitionType;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.math.BigDecimal;
import java.util.*;

public class RequisitionSplitPurchaseItems extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		incomingRequest.put("splitCount",new BigDecimal(0)) ;
		
		try {
			RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			List reqLineList = (List) incomingRequest.get("requisitionLineList");
			List splitReqList = new ArrayList();
			List inventoryItemList = new ArrayList();
			List purchaseItemList = new ArrayList();
			List deleteLineList = new ArrayList();
			String changeReqType = "N";
			
			for (int i=0; i < reqLineList.size(); i++) {
				RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i);
		
				if (reqLine.getItemSource().equals("INV")) {
					inventoryItemList.add(reqLine);
					
					//Setup original purchase request item to be deleted
					deleteLineList.add(String.valueOf(reqLine.getIcReqLine()));					
				} else {
					BigDecimal newLineNumber = new BigDecimal(purchaseItemList.size() + 1);
					reqLine.setLineNumber(newLineNumber);
					purchaseItemList.add(reqLine);
				}
			}
			
			if (inventoryItemList.size() > 0) {
				if (purchaseItemList.size() > 0) {
					// Create new Supply Request based on original Purchase Request
					//	Move all inventory type items from Purchase Req to Supply Req
					// 	Recalculate and update original RequisitionHeader for Purchase Request
					//	Renumber and update remaining line items for original Purchase Request
					
				    RequisitionHeader splitReq = new RequisitionHeader();
				    splitReq.setRequisitionType(RequisitionType.SUPPLY_REQUEST);
				    splitReq.setRequisitionLineList(inventoryItemList);
				    splitReqList.add(splitReq);
				    
				    requisitionHeader.setRequisitionLineList(purchaseItemList);
				    incomingRequest.put("requisitionLineList", purchaseItemList);
				    
				    if (deleteLineList.size() > 1) {
				    	String[] deleteLineIcs = new String[deleteLineList.size()];
					    
					    deleteLineIcs = (String[]) deleteLineList.toArray(deleteLineIcs);		        
					    
						incomingRequest.put("deleteLine_lineIc", deleteLineIcs);
				    } else if (deleteLineList.size() == 1) {
						incomingRequest.put("deleteLine_lineIc", (String) deleteLineList.get(0));
				    }

				} else {
					// All items are in inventory - convert Purchase Request to Supply Request
					changeReqType = "Y";
				}
			}

			incomingRequest.put("changeReqType", changeReqType);
			incomingRequest.put("newReqType", RequisitionType.SUPPLY_REQUEST);
			incomingRequest.put("splitCount", new BigDecimal(splitReqList.size() + 1)) ;
			
			result = splitReqList ;			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}