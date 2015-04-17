package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RequisitionLineSetRequisitionNumberFromHeader extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			List	requisitionLineList = (List) incomingRequest.get("requisitionLineList");
			String	requisitionNumber = (String) incomingRequest.get("RequisitionHeader_requisitionNumber");
			
			for (int i=0; i < requisitionLineList.size(); i++) {
				RequisitionLine requisitionLine = (RequisitionLine) requisitionLineList.get(i);
				requisitionLine.setRequisitionNumber(requisitionNumber);
				requisitionLineList.set(i, requisitionLine);
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