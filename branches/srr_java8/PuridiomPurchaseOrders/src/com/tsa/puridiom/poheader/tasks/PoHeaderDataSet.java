/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.poheader.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoHeaderDataSet extends Task
{
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map)object;
		try
		{
			PoHeader poh = (PoHeader) incomingRequest.get("poHeader") ;

			if (poh != null) {
				poh.setPoLineList((List) incomingRequest.get("poLineList")) ;
				poh.setAccountList((List) incomingRequest.get("accountList")) ;
				poh.setDocCommentList((List) incomingRequest.get("docCommentList")) ;
				poh.setDocAttachmentList((List) incomingRequest.get("docAttachmentList")) ;
				poh.setScheduleList((List) incomingRequest.get("scheduleList")) ;

				poh.setBillToAddress((Address) incomingRequest.get("billToAddress"));
				poh.setShipToAddress((Address) incomingRequest.get("shipToAddress"));
				poh.setVendorAddress((Address) incomingRequest.get("vendorAddress"));
				poh.setReviewFinalizeList((List)incomingRequest.get("reviewFinalizeList"));
			}

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}

		return null  ;
	}
}
