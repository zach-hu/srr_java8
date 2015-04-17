package com.tsa.puridiom.requisition.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.tax.TaxManager;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class RequisitionDefaultShippingTaxable extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		this.setStatus(Status.SUCCEEDED);

		try
		{
			RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");

			// Sets "tax shipping" to YES
			incomingRequest.put("RequisitionHeader_taxShipping", "Y");
			// Sets "tax other" to YES
			incomingRequest.put("RequisitionHeader_taxOther", "Y");

			if(requisitionHeader.getShippingTaxAmt().compareTo(new BigDecimal(0)) <= 0) {
				// Calculates a value for "Shipping Tax Amount", to recalculate "Shipping Tax"
				BigDecimal shippingTaxAmt = requisitionHeader.getShippingCharges().multiply(requisitionHeader.getTaxPercent()).divide(new BigDecimal(100), BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
				requisitionHeader.setShippingTaxAmt(shippingTaxAmt);
			}

			if(requisitionHeader.getOtherTaxAmount().compareTo(new BigDecimal(0)) <= 0) {
				// Calculates a value for "Other Tax Amount" to recalculate "Other Tax"
				BigDecimal otherTaxAmt = requisitionHeader.getOtherCharges().multiply(requisitionHeader.getTaxPercent()).divide(new BigDecimal(100), BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
				requisitionHeader.setOtherTaxAmount(otherTaxAmt);
			}

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e.toString());
			throw e;
		}

		return null;
	}
}