/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator 
 */
public class RequisitionLineRefTypeSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        this.setStatus(Status.SUCCEEDED) ;
        
		incomingRequest.put("Default_referenceType", "RQL");
				
		return null  ;
	}
	
}
