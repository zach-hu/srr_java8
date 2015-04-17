package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

import java.math.BigDecimal;
import java.util.*;

public class RequisitionSplitItems extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		incomingRequest.put("splitCount",new BigDecimal(0)) ;
		
		try {
			List	reqLineList = (List) incomingRequest.get("requisitionLineList");
			List splitReqList = new ArrayList();
			Hashtable lineTable = new Hashtable() ;
			
			for (int i=0; i < reqLineList.size(); i++) {
				RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i);
				String location = reqLine.getItemLocation() ;
		
				if (Utility.isEmpty(location)) {
				    location = "-" ;
				}
				
				List itemListByLocation = (List) lineTable.get(location);
				if (itemListByLocation == null) {
				    itemListByLocation  = new ArrayList();
				}
				itemListByLocation.add(reqLine);
				lineTable.put(location, itemListByLocation);
			}
			
			Enumeration keys = lineTable.keys();
			while (keys.hasMoreElements()) {
			    String location = (String) keys.nextElement();
			    
			    List itemList = (List) lineTable.get(location);
			    
			    RequisitionHeader splitReq = new RequisitionHeader();
			    splitReq.setItemLocation(location);
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