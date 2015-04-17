/*
 * Created on June 07, 2004
 */
package com.tsa.puridiom.disbline.tasks;

import java.util.*;

import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator 
 */
public class DisbLineAllocateAmount extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		String result = null ;

		try {
			DisbLine dbl = (DisbLine) incomingRequest.get("disbLine") ;
			result = dbl.getLineTotal().toString() ;
	        this.setStatus(Status.SUCCEEDED) ;
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
        
		return result  ;
	}
}
