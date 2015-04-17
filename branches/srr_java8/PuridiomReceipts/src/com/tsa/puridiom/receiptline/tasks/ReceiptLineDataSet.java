/*
 * Created on Nov 19, 2003
 */
package com.tsa.puridiom.receiptline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Kelli
 */
public class ReceiptLineDataSet extends Task {
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
			line.setPoLine((PoLine)incomingRequest.get("poLine"));
			RequisitionLine requisitionLine = (RequisitionLine)incomingRequest.get("requisitionLine");
			if (requisitionLine != null) {
				line.setRequisitionLine(requisitionLine);
			}
			line.setMsrLine((RequisitionLine)incomingRequest.get("msrLine"));
			line.setMsrHeader((RequisitionHeader)incomingRequest.get("msrHeader"));

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
