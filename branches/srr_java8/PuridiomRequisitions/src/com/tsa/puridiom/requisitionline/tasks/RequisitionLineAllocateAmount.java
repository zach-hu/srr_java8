/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.math.BigDecimal;
import java.util.*;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator 
 */
public class RequisitionLineAllocateAmount extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		String result = null ;

		try {
			RequisitionLine rql = (RequisitionLine) incomingRequest.get("requisitionLine") ;
			BigDecimal bdTotal = rql.getLineTotal();
			//BigDecimal discount = rql.getDiscountAmount();
			
			if (bdTotal == null)
			{
				bdTotal = new BigDecimal(0);
			}
			//result = bdTotal.subtract(discount).toString();
			result = bdTotal.toString();
	        this.setStatus(Status.SUCCEEDED) ;
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
        
		return result  ;
	}
}
