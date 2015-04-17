package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.common.documents.SourcingType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.requisition.tasks.RequisitionMsrBlanketInfo;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.math.BigDecimal;
import java.util.*;

public class RequisitionSplitSourcingItems extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		String oid = (String) incomingRequest.get("organizationId") ;
		Object result = null;
		incomingRequest.put("splitCount",new BigDecimal(0)) ;
		PropertiesManager pm = PropertiesManager.getInstance(oid) ;
		UserManager um = UserManager.getInstance() ;
		RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader") ;
		incomingRequest.put("createdFrom","SOURCING") ;

		try {
			List	reqLineList = (List) incomingRequest.get("requisitionLineList");

			List splitReqList = new ArrayList();
			Hashtable lineTable = new Hashtable() ;
			for (int ix=1;ix < 7;ix++ ) {
				String icArray[] = null ;
				String bucketType = (String) incomingRequest.get("source_type_cmb" + ix) ;
				Object obj =  incomingRequest.get("source_ic_history_bucket" + ix);
				if (obj == null) continue ;
				if (obj instanceof String[]) {
					String array[] = (String[]) obj;
					icArray = array ;
				} else {
					String array[] = { (String) obj } ;
					icArray = array ;
				}

				List itemList = (List) lineTable.get(ix);
				for (int i=0; i < reqLineList.size(); i++) {
					RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i);
					boolean found = false ;
					for (int ia = 0; ia < icArray.length; ia++) {
						if (reqLine.getIcLineHistory().compareTo(new BigDecimal(icArray[ia])) != 0) continue ;
						if (itemList == null) {
						    itemList  = new ArrayList();
						}
						itemList.add(reqLine);
						reqLine.setStatus(DocumentStatus.REQ_PLANNING_SOURCED) ;
						lineTable.put(bucketType + "-" + ix, itemList) ;
					}
				}
			}

			Vector vector = new Vector(lineTable.keySet());
			Collections.sort(vector);
			Iterator iterator = vector.iterator();
			while (iterator.hasNext()) {
			    String key = (String) iterator.next();
			    List itemList = (List) lineTable.get(key);
			    String bucketInfo[] = key.split("-") ;
			    String bucketType = bucketInfo[0] ;
			    String blanketHeaderIc = null ;
			    String itemLocation = "" ;
			    if (itemList.size() > 0) {
			    	RequisitionLine reqLine = (RequisitionLine) itemList.get(0) ;
			    	String blanketOrder = reqLine.getBlanketOrder() ;
			    	itemLocation = reqLine.getItemLocation() ;
			    	if (! HiltonUtility.isEmpty(blanketOrder)) {
			        	incomingRequest.put("PoHeader_poNumber", blanketOrder) ;
			        	incomingRequest.put("PoHeader_releaseNumber", "0") ;
				    	RequisitionMsrBlanketInfo b =  new RequisitionMsrBlanketInfo() ;
				    	Object pohObj = b.executeTask(incomingRequest) ;
				    	String pohIc = (String) pohObj ;
				    	if (! HiltonUtility.isEmpty(pohIc)) {

				    		blanketHeaderIc = pohIc ;
				    	}
			    	}
			    }

			    RequisitionHeader splitReq = new RequisitionHeader();
			    if (bucketType.contains(SourcingType.INVENTORY)) {
			    	// Create Supply Request
			    	if(!HiltonUtility.isEmpty(reqHeader.getItemLocation()) && reqHeader.getKit().equalsIgnoreCase("Y"))
			    	{
			    		splitReq.setRequisitionType("T") ;
			    		splitReq.setShipToCode(reqHeader.getItemLocation());
			    	}
			    	else
			    	{
			    		splitReq.setRequisitionType("S") ;
			    	}
			    	splitReq.setItemLocation(itemLocation) ;
			    } else if (bucketType.contains(SourcingType.STORE_CATALOG)) {
			    	// Create Release Request
			    	if (! HiltonUtility.isEmpty(blanketHeaderIc)) {
			    		splitReq.setRequisitionType(RequisitionType.RELEASE_REQUEST) ;
			    		BigDecimal ic = new BigDecimal(blanketHeaderIc) ;
			    		splitReq.setIcRevisedOrder(ic) ;
			    	} else {
			    		splitReq.setRequisitionType(RequisitionType.PURCHASE_REQUEST) ;
			    	}
			    	splitReq.setRqSubType("PA") ;
			    } else if (bucketType.contains(SourcingType.PURCHASE_FOR_SOURCING)) {
			    	// Create normal purchase request
			    	splitReq.setRequisitionType(RequisitionType.PURCHASE_REQUEST) ;
			    } else if (bucketType.contains(SourcingType.DIRECT_PURCHASE)) {
			    	// Create Purchase request - requires - credit card
			    	splitReq.setRequisitionType(RequisitionType.PURCHASE_REQUEST) ;
					UserProfile up = um.getUser(oid, reqHeader.getOwner()) ;
					up.getpCardNumber() ;
					splitReq.setPcardNumber(up.getpCardNumber()) ;
					splitReq.setPcardExpires(up.getpCardExpires()) ;
					splitReq.setPcardHolder(up.getpCardHolder()) ;
			    	splitReq.setRqSubType("PA") ;
			    } else if (bucketType.contains(SourcingType.INTERNAL_CATALOG)) {
			    	// Blanket order selected by user
			    	if (! HiltonUtility.isEmpty(blanketHeaderIc)) {
			    		splitReq.setRequisitionType(RequisitionType.RELEASE_REQUEST) ;
			    		BigDecimal ic = new BigDecimal(blanketHeaderIc) ;
			    		splitReq.setIcRevisedOrder(ic) ;
			    	} else {
			    		splitReq.setRequisitionType(RequisitionType.PURCHASE_REQUEST) ;
			    	}
			    	splitReq.setRqSubType("PA") ;
			    } else {
			    	splitReq.setRequisitionType(RequisitionType.PURCHASE_REQUEST) ;
			    }
			    splitReq.setRequisitionLineList(itemList);

			    splitReqList.add(splitReq);
			}

			incomingRequest.put("splitCount", new BigDecimal(splitReqList.size())) ;
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