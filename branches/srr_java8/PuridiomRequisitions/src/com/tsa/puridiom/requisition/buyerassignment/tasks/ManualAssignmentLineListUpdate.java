package com.tsa.puridiom.requisition.buyerassignment.tasks;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.processengine.*;

import java.util.*;

public class ManualAssignmentLineListUpdate extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			List	requisitionLineList = (List) incomingRequest.get("requisitionLineList");
				
			for (int i=requisitionLineList.size() - 1; i >= 0 ; i--) {
			    RequisitionLine requisitionLine = (RequisitionLine) requisitionLineList.get(i);
			    String	status = requisitionLine.getStatus();
			    
			    if (status.equals(DocumentStatus.REQ_FORWARDED) || status.equals(DocumentStatus.REQ_APPROVED) || status.equals(DocumentStatus.RFQ_PURCHASING)) {
				    PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
					PuridiomProcess process = processLoader.loadProcess("requisitionline-buyer-assignment-update.xml");
					Map updateParameters = new HashMap();
					
					updateParameters.put("dbsession", incomingRequest.get("dbsession"));
	                updateParameters.put("organizationId", incomingRequest.get("organizationId"));
	                updateParameters.put("userId", incomingRequest.get("userId"));
	                updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
	                updateParameters.put("requisitionLine", requisitionLine);
	                updateParameters.put("assignTo", (String) incomingRequest.get("assignTo"));
	                
					process.executeProcess(updateParameters);
					
					if (process.getStatus() < Status.SUCCEEDED) {
						throw new Exception("Requisition Line Update process failed.");
					}
			    } else {
			        requisitionLineList.remove(i);
			    }
			}

			result = requisitionLineList;			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}