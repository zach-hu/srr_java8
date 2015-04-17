/**
 *
 * Created on Jan 19, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.PoHeaderSetTax.java
 *
 */
package com.tsa.puridiom.poheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.TaxCode;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;


public class PoHeaderSetTax extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			TaxCode taxCode = (TaxCode)incomingRequest.get("taxCode");
			if(taxCode != null)
			{
				poHeader.setTaxCode(taxCode.getTaxCode());
				poHeader.setTaxPercent(taxCode.getTaxRate());
			}
			ret = poHeader;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Tax Code could not be retrieved.", e);
		}

		return ret;
	}

}
