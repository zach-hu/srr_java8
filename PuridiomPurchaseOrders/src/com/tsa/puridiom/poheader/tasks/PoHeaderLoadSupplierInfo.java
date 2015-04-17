/**
 * 
 * Created on Dec, 2005
 * @author Tamy
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.PoHeaderLoadSupplierInfo.java
 * 
 * used in po-saveas 
 */
package com.tsa.puridiom.poheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.Vendor;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoHeaderLoadSupplierInfo extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		
		try
		{
			Vendor vendor =(Vendor)incomingRequest.get("vendor");
			if (vendor != null) {
				incomingRequest.put("PoHeader_vendorClass", vendor.getVendorClass());
			}

		}
		catch (Exception e)
		{
			throw new TsaException(e.toString());
		}
		return null;
	}

}
