/*
 * Created on Dec 2, 2003
 */
package com.tsa.puridiom.rfqline.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqLineRefTypeSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;

		incomingRequest.put("Default_referenceType", "RFL");

		this.setStatus(Status.SUCCEEDED) ;				
		return null  ;
	}
	
}
