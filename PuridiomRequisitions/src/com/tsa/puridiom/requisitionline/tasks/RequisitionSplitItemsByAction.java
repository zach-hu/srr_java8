package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import java.math.BigDecimal;
import java.util.*;

public class RequisitionSplitItemsByAction extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		incomingRequest.put("splitCount",new BigDecimal(0)) ;
		
		try {
		    DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			List	splitRequisitionList = (List) incomingRequest.get("splitRequisitionList");
			List newSplitReqList = new ArrayList();
			boolean purchaseReq = false;
		
			ArrayList bLineList = null ;
			ArrayList mLineList = null ;
			ArrayList fLineList = null ;
			
			for (int i=0; i < splitRequisitionList.size(); i++) {
			    RequisitionHeader splitReq = (RequisitionHeader) splitRequisitionList.get(i);
			    List reqLineList = splitReq.getRequisitionLineList();
			    Hashtable lineTable = new Hashtable() ;
				
			    for (int il=0; il < reqLineList.size(); il++) {
					RequisitionLine requisitionLine = (RequisitionLine) reqLineList.get(il);
					String itemNo = requisitionLine.getItemNumber() ;
					String location = requisitionLine.getItemLocation() ;
					String	actionCode = "B";
					
					if (Utility.isEmpty(itemNo)) {
						actionCode = "B" ;
					} else {
						if (Utility.isEmpty(location)) {
							actionCode = "B" ;
						} else {
							String queryString = "Select item.actionCode from InvItem as item where item.itemNumber = ? ";
							List resultList = dbs.query(queryString, new Object[] {itemNo } , new Type[] { Hibernate.STRING}) ;
							if (resultList != null && resultList.size() > 0) {
								actionCode = (String) resultList.get(0);
							}
						}
					}
					
					if (Utility.isEmpty(location) || Utility.isEmpty(actionCode)) {
					    actionCode = "B" ;
					}
					
					List itemListByAction = new ArrayList();
					if (lineTable != null && lineTable.containsKey(actionCode)) {
					    itemListByAction = (List) lineTable.get(actionCode);
					}
					if (itemListByAction == null) {
					    itemListByAction  = new ArrayList();
					}
					itemListByAction.add(requisitionLine);
					lineTable.put(actionCode, itemListByAction);
			    }

			    Enumeration keys = lineTable.keys();
				while (keys.hasMoreElements()) {
				    String actionCode = (String) keys.nextElement();
				    List itemList = (List) lineTable.get(actionCode);
				    
				    RequisitionHeader newSplitReq = new RequisitionHeader();
				    newSplitReq.setItemLocation(splitReq.getItemLocation());
				    newSplitReq.setRequisitionLineList(itemList);

				    if (actionCode.equals("F")) {
					    newSplitReq.setRequisitionType("S");
					} else if (actionCode.equals("M")) {
					    newSplitReq.setRequisitionType("S");
					} else {
					    newSplitReq.setRequisitionType("P");
					    purchaseReq = true;
					}
				    
				    newSplitReqList.add(newSplitReq);
				}
			}
			
			int	splitCount = newSplitReqList.size();
			if (purchaseReq && splitCount == 1) {
			    // in case there is only one item and it is a Buy item
			    splitCount = 2;
			}
			incomingRequest.put("splitCount", new BigDecimal(splitCount)) ;
			result = newSplitReqList ;		
		    
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}