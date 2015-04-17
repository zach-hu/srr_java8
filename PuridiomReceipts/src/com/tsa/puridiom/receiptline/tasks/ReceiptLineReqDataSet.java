/*
 * Created on Nov 19, 2003
 */
package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

/**
 * @author Kelli
 */
public class ReceiptLineReqDataSet extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ReceiptLine line = (ReceiptLine) incomingRequest.get("receiptLine") ;
			if (line == null)
			{
				throw new Exception ("The ReceiptLine entity was not found.");
			}
			line.setRequisitionLine((RequisitionLine) incomingRequest.get("requisitionLine")) ;

			result = line;
			this.setStatus(Status.SUCCEEDED) ;
		}
        catch (Exception e)
        {
        	this.setStatus(Status.FAILED);
        	throw e;
        }
		return result;
	}
}
