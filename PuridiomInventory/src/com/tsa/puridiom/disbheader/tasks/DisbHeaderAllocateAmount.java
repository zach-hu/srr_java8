/*
 * Created on June 07, 2004
 */
package com.tsa.puridiom.disbheader.tasks;

import java.math.BigDecimal;
import java.util.*;

import com.tsa.puridiom.entity.DisbHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator 
 */
public class DisbHeaderAllocateAmount extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		String result = null ;

		try {
			DisbHeader dbh = (DisbHeader) incomingRequest.get("disbHeader") ;
			BigDecimal tot = dbh.getSubtotal() ;
			if (tot == null)
			{
				tot = new BigDecimal(0);
			}
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
