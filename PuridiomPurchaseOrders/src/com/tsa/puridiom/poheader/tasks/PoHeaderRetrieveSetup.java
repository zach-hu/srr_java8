/**
 *
 * Created on Feb 9, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.PoHeaderRetrieveSetup.java
 *
 */
package com.tsa.puridiom.poheader.tasks;

import java.util.*;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.common.documents.OrderType;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;

public class PoHeaderRetrieveSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map) object;
		this.setStatus(Status.SUCCEEDED) ;

		PoHeader poh = (PoHeader) incomingRequest.get("poHeader") ;
		if (poh == null)
		{
			this.setStatus(Status.FAILED) ;
			throw new TsaException("PoHeader Entity was not found for PoHeaderRetrieveSetup!");
		}
		else
		{
			String icHeader = poh.getIcPoHeader().toString() ;
			String icLine = "0" ;
			String  newType = (String) incomingRequest.get("newType");
			String organizationId = (String)incomingRequest.get("organizationId");
			PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;

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
				incomingRequest.put("PoLine_icPoHeader",icHeader) ;

				if ( ( newType != null )  && poh.getPoType().equals(OrderType.CONTRACT) && propertiesManager.getProperty("PO OPTIONS", "CONTRACTSAVEASPO", "N").equals("Y")) {
					incomingRequest.put("PoHeader_poType", newType) ;
				}
				else{
					incomingRequest.put("PoHeader_poType",poh.getPoType()) ;
				}
				
				incomingRequest.put("Vendor_vendorId", poh.getVendorId());
				incomingRequest.put("ChecklistEntry_referenceType", "POH");
				incomingRequest.put("ChecklistResponse_icHeader", icHeader);
                incomingRequest.put("PerformanceDetail_icPoHeader", icHeader);
                incomingRequest.put("oldStatus", poh.getStatus()) ;
			}
		}
		return null;
	}
}