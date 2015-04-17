/**
 * Created on Apr 12, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineCancelSetValues.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoLineCloseSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map)object;
			PoLine poLine = (PoLine)incomingRequest.get("poLine");

			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			String oid = (String)incomingRequest.get("organizationId");
			boolean closeLine = false;
			if ((oid.equalsIgnoreCase("DTN07P")) && (poHeader != null) && (poHeader.getRevisionNumber().compareTo(new BigDecimal(0)) > 0))
				closeLine = true;

			if (closeLine)
			{
				incomingRequest.put("icLineCancelled", poLine.getIcPoLine());
				incomingRequest.put("recalculateAccount","N");
				incomingRequest.put("PoHeader_revisionNumber", poHeader.getRevisionNumber());
			}

			poLine.setStatus(DocumentStatus.CLOSED);

			ret = poLine;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return ret;
	}

}
