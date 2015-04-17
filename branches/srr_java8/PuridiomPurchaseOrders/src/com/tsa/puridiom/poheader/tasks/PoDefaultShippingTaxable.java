package com.tsa.puridiom.poheader.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class PoDefaultShippingTaxable extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		this.setStatus(Status.SUCCEEDED);

		try
		{
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");

			// Sets "tax shipping" to YES
			poHeader.setShippingTaxable("Y");
			// Sets "tax other" to YES
			poHeader.setOtherTaxable("Y");

			if (poHeader.getShippingTax().compareTo(new BigDecimal(0)) <= 0)
			{
				// Calculates a value for "Shipping Tax Amount", to recalculate "Shipping Tax"
				BigDecimal shippingTax = poHeader.getShippingCharges().multiply(poHeader.getTaxPercent()).divide(new BigDecimal(100), BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
				poHeader.setShippingTax(shippingTax);
			}

			if (poHeader.getOtherTax().compareTo(new BigDecimal(0)) <= 0)
			{
				// Calculates a value for "Other Tax Amount" to recalculate "Other Tax"
				BigDecimal otherTax = poHeader.getOtherCharges().multiply(poHeader.getTaxPercent()).divide(new BigDecimal(100), BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
				poHeader.setOtherTax(otherTax);
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