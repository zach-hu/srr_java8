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

public class PoLineCancelSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map)object;
			PoLine poLine = (PoLine)incomingRequest.get("poLine");
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			poLine.setStatus(DocumentStatus.CANCELLED);
			poLine.setTaxAmount(new BigDecimal(0));
			poLine.setTaxPercent(new BigDecimal(0));
			poLine.setDiscountPercent(new BigDecimal(0));
			poLine.setDiscountAmount(new BigDecimal(0));
			poLine.setShippingCharges(new BigDecimal(0));
			poLine.setShippingTax(new BigDecimal(0));
			poLine.setOtherCharges(new BigDecimal(0));
			poLine.setOtherTax(new BigDecimal(0));
			poLine.setLineRevNo(poHeader.getRevisionNumber().toString());

			incomingRequest.put("icLineCancelled", poLine.getIcPoLine());
//			incomingRequest.put("recalculateAccount","N" );

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
