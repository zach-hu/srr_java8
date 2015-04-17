/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.disbheader.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.DisbHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class DisbHeaderDataSet extends Task 
{
	public Object executeTask(Object object) throws Exception 
	{

		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			DisbHeader disbh = (DisbHeader) incomingRequest.get("disbHeader") ;
	
			disbh.setDisbLineList((List) incomingRequest.get("disbLineList")) ;		
			disbh.setAccountList((List) incomingRequest.get("accountList")) ;
			disbh.setDocCommentList((List) incomingRequest.get("docCommentList")) ;
			disbh.setDocAttachmentList((List) incomingRequest.get("docAttachmentList")) ;
			disbh.setShipToAddress((Address) incomingRequest.get("shipToAddress"));
			disbh.setInventoryAddress((Address) incomingRequest.get("inventoryAddress"));
						
			this.setStatus(Status.SUCCEEDED) ;
			ret = disbh;
			incomingRequest.put("disbHeader", disbh);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
        
		return ret ;
	}
}
