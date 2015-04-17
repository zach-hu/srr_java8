package com.tsa.puridiom.requisition.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RequisitionCreateLinesFromPo extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		
		try {
			List	poLineList = (List) incomingRequest.get("poLineList");
			List	reqLineList = new ArrayList();
			String	icPoHeader = (String) incomingRequest.get("PoHeader_icPoHeader");
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("requisitionline-create-from-poline.xml");
			HashMap updateParameters = new HashMap();
			
			updateParameters.put("userId", incomingRequest.get("userId"));
			updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
			updateParameters.put("organizationId", incomingRequest.get("organizationId"));
			updateParameters.put("dbsession", incomingRequest.get("dbsession"));
			updateParameters.put("RequisitionHeader_icReqHeader", incomingRequest.get("RequisitionHeader_icReqHeader"));
			updateParameters.put("RequisitionHeader_icRevisedOrder", incomingRequest.get("RequisitionHeader_icRevisedOrder"));
			updateParameters.put("RequisitionHeader_requisitionNumber", incomingRequest.get("RequisitionHeader_requisitionNumber"));
			updateParameters.put("requisitionHeader", incomingRequest.get("requisitionHeader"));
			
			for (int i = 0; i < poLineList.size(); i++) {
			    PoLine poLine = (PoLine) poLineList.get(i);
				
				updateParameters.put("poLine", poLine);
				
				process.executeProcess(updateParameters);
				
				if (process.getStatus() == Status.SUCCEEDED)
				{
					reqLineList.add(updateParameters.get("requisitionLine"));
	
					updateParameters.remove("requisitionLine");
					updateParameters.remove("poLine");
				}
				else
				{
					this.setStatus(process.getStatus());
					throw new Exception("Process to create requisition lines from po lines failed.");
				}
			}
			
			incomingRequest.put("PoLine_icPoHeader", icPoHeader);
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return null ;
	}

}