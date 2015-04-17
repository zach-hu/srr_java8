package com.tsa.puridiom.requisition.tasks;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RequisitionForwardSplitList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
		    List	splitReqList = (List) incomingRequest.get("splitRequisitionList");
			
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process;
			String	userId = (String) incomingRequest.get("userId");
			String	organizationId = (String) incomingRequest.get("organizationId");
			DBSession dbsession = (DBSession) incomingRequest.get("dbsession");
			
			for (int i=0; i < splitReqList.size(); i++) {
			    RequisitionHeader splitReq = (RequisitionHeader) splitReqList.get(i);
				HashMap requestParameters = new HashMap();
				
				requestParameters.put("userId", userId);
				requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
				requestParameters.put("organizationId", organizationId);
				requestParameters.put("dbsession", dbsession);
			    requestParameters.put("RequisitionHeader_icReqHeader", String.valueOf(splitReq.getIcReqHeader())) ;
			    requestParameters.put("RequisitionHeader_status", String.valueOf(splitReq.getStatus())) ;
			    requestParameters.put("RequisitionHeader_requisitionType", splitReq.getRequisitionType()) ;
			    requestParameters.put("RequisitionHeader_fiscalYear", splitReq.getFiscalYear());
			    requestParameters.put("reqaction", "FORWARD");
			    
			    process = processLoader.loadProcess("requisition-recalculate.xml");
			    process.executeProcess(requestParameters);
			    
			    if (process.getStatus() != Status.SUCCEEDED) {
			        throw new Exception ("The recalculation process failed for Intelligent Requisition Split process.");
			    }

			    process = processLoader.loadProcess("requisition-routing-list.xml");
			    process.executeProcess(requestParameters);
			    
			    if (process.getStatus() != Status.SUCCEEDED) {
			        throw new Exception ("The routing list process failed for Intelligent Requisition Split process.");
			    }
			    
			    process = processLoader.loadProcess("requisition-forward.xml");
			    process.executeProcess(requestParameters);
			    
				RequisitionHeader newReqHeader = (RequisitionHeader) requestParameters.get("requisitionHeader");
				List newReqLineList = (List) requestParameters.get("requisitionLineList");
				newReqHeader.setRequisitionLineList(newReqLineList);
			    newReqHeader.setForwardedTo((String) requestParameters.get("forwardedTo"));
				
				splitReqList.set(i, newReqHeader);
				
				if (process.getStatus() < Status.SUCCEEDED) {
					throw new Exception("The forward process failed for Intelligent Requisition Split process.");
				}
				
			}
			
			incomingRequest.put("splitRequisitionList", splitReqList);
			incomingRequest.put("newStatus",DocumentStatus.HISTORY) ;

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}