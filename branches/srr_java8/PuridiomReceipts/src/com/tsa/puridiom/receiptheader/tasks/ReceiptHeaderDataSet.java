/*
 * Created on Nov 19, 2003
 */
package com.tsa.puridiom.receiptheader.tasks;

import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsagate.foundation.processengine.*;
import java.util.List;
import java.util.Map;

/**
 * @author Kelli
 */
public class ReceiptHeaderDataSet extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ReceiptHeader header = (ReceiptHeader) incomingRequest.get("receiptHeader");
			if (header == null)
			{
				throw new Exception ("The ReceiptHeader entity was not found.");
			}
			header.setReceiptLineList((List) incomingRequest.get("receiptLineList"));
			header.setAccountList((List) incomingRequest.get("accountList"));
			header.setDocCommentList((List) incomingRequest.get("docCommentList"));
			header.setDocAttachmentList((List) incomingRequest.get("docAttachmentList"));

			header.setShipToAddress((Address) incomingRequest.get("shipToAddress"));
			header.setVendorAddress((Address) incomingRequest.get("vendorAddress"));

			result = header;
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
