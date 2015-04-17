/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.requisitionheader.tasks;

import java.math.BigDecimal;
import java.util.*;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator 
 */
public class RequisitionHeaderAllocateAmount extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		String result = null ;

		try {
			RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader") ;
			BigDecimal tot = rqh.getTotal() ;
			String allocated = (String) incomingRequest.get("allocatedTotal") ;
			if (allocated == null){
				allocated = "0" ;
			}
			BigDecimal alloc = new BigDecimal(allocated) ;
			result = tot.subtract(alloc).toString() ;
	        this.setStatus(Status.SUCCEEDED) ;
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
        
		return result  ;
	}
	
}
