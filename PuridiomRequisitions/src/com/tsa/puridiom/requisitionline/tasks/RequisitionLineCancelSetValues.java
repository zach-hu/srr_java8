/**
 * Created on Apr 12, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineCancelSetValues.java
 *
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionLineCancelSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map)object;
			RequisitionLine requisitionLine = (RequisitionLine)incomingRequest.get("requisitionLine");
			RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
			
			requisitionLine.setStatus(DocumentStatus.CANCELLED);
			
			requisitionLine.setTaxAmount(new BigDecimal(0));
			requisitionLine.setTaxPercent(new BigDecimal(0));
			requisitionLine.setDiscountPercent(new BigDecimal(0));
			requisitionLine.setDiscountAmount(new BigDecimal(0));
			requisitionLine.setShippingCharges(new BigDecimal(0));
			requisitionLine.setShippingTaxAmt(new BigDecimal(0));
			requisitionLine.setOtherCharges(new BigDecimal(0));
			requisitionLine.setOtherTaxAmount(new BigDecimal(0));
			requisitionLine.setUmFactor(new BigDecimal(0));
			
			incomingRequest.put("icLineCancelled", requisitionLine.getIcReqLine());
			incomingRequest.put("recalculateAccount","N" );
			
			ret = requisitionLine;
			
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
