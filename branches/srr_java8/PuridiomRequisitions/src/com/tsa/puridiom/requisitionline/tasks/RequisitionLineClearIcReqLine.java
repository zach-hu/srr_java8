package com.tsa.puridiom.requisitionline.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class RequisitionLineClearIcReqLine extends Task {

	public Object executeTask(Object object) throws Exception {

	    try {
	        Map incomingRequest = (Map)object;
	        
	        incomingRequest.remove("RequisitionLine_icReqLine");
	        
	        this.setStatus(Status.SUCCEEDED);
	    }
	    catch (Exception e) {
	        throw e;
	    }		

		return null ;
	}
}