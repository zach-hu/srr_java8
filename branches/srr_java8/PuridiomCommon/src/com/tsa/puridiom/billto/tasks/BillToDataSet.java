/*
 * Created on Jan 22, 2004 
 */
package com.tsa.puridiom.billto.tasks;

import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.BillTo;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

/**
 * @author Administrator 
 */
public class BillToDataSet extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		BillTo billTo = (BillTo) incomingRequest.get("billTo") ;

		billTo.setBillToAddress((Address) incomingRequest.get("billToAddress"));
					
		this.setStatus(Status.SUCCEEDED) ;
        
		return null  ;
	}
}
