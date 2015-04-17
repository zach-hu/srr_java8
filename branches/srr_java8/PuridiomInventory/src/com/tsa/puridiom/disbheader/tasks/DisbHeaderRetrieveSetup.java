/**
 *
 * Created on Feb 9, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.disbheader.tasks.DisbHeaderRetrieveSetup.java
 *
 */
package com.tsa.puridiom.disbheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.DisbHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class DisbHeaderRetrieveSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		try
		{
			this.setStatus(Status.SUCCEEDED) ;

			DisbHeader dsbh = (DisbHeader) incomingRequest.get("disbHeader") ;
			if (dsbh == null)
			{
				this.setStatus(Status.FAILED) ;
				throw new TsaException("Disbursement Entity was not found!");
			}
			else
			{
				String icHeader = dsbh.getIcDsbHeader().toString() ;
				String icLine = "0" ;

				if (Utility.isEmpty(icHeader))
				{
					this.setStatus(Status.FAILED);
				}
				else
				{
					incomingRequest.put("Schedule_icHeader",icHeader) ;
					incomingRequest.put("Account_icHeader",icHeader) ;
					incomingRequest.put("Account_icLine",icLine) ;
					incomingRequest.put("DocComment_icHeader",icHeader) ;
					incomingRequest.put("DocComment_icLine",icLine) ;
					incomingRequest.put("DocAttachment_icHeader",icHeader) ;
					incomingRequest.put("DocAttachment_icLine",icLine) ;
					incomingRequest.put("DisbLine_icDsbHeader",icHeader) ;
					incomingRequest.put("RequisitionHeader_icReqHeader", dsbh.getIcReqHeader().toString());
					
					incomingRequest.put("DisbHeader_requisitionType", dsbh.getDisbType()) ;
					incomingRequest.put("RequisitionHeader_itemLocation", dsbh.getItemLocation());
					incomingRequest.put("ShipTo_icHeader",dsbh.getIcReqHeader().toString()) ;
					incomingRequest.put("ShipTo_icLine",icLine) ;
				}
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}
}