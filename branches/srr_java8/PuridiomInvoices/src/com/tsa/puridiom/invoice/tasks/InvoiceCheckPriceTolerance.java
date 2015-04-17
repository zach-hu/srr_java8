package com.tsa.puridiom.invoice.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
//import com.tsagate.foundation.utility.TsaException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;

public class InvoiceCheckPriceTolerance extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        try
        {
        	PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId"));

            List ivcLineList = (List) incomingRequest.get("invoiceLineList");
            List exceptionLineList = new ArrayList();

            for (int i = 0; i < ivcLineList.size(); i++)
            {
                InvoiceLine ivl = (InvoiceLine) ivcLineList.get(i);
                if (ivl.getIcPoLine().compareTo(new BigDecimal(0)) > 0)
                {
                	BigDecimal bdInvoiceTotal = ivl.getQuantity().multiply(HiltonUtility.getFormattedPrice(ivl.getUnitPrice()    ,(String)incomingRequest.get("organizationId")));
                	BigDecimal bdOrderTotal   = ivl.getQuantity().multiply(HiltonUtility.getFormattedPrice(ivl.getOrderUnitCost(),(String)incomingRequest.get("organizationId")));
	                /*
	                if (ivl.getDiscountPercent().compareTo(new BigDecimal(0)) > 0)
	                {
	                	bdOrderTotal = bdOrderTotal.subtract(bdOrderTotal.multiply(ivl.getDiscountPercent().multiply(new BigDecimal(.01))));
	                }
	                */

	                if (bdOrderTotal.compareTo(bdInvoiceTotal) != 0)
	                {
	                	if (bdOrderTotal.compareTo(bdInvoiceTotal) < 0)
	                	{
	                		String tolerance = propertiesManager.getProperty("VOUCHER", "TOLERANCE", " ");
	                		if (!HiltonUtility.isEmpty(tolerance))
	                		{
	                			BigDecimal bdTolerance = new BigDecimal(tolerance);
	                			BigDecimal bdTol = bdOrderTotal.multiply(bdTolerance).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
	                			BigDecimal bdDiff = bdOrderTotal.subtract(bdInvoiceTotal).abs();
	                			if (bdDiff.compareTo(bdTol) > 0)
	                			{
	                				exceptionLineList.add(String.valueOf(i + 1));
	                				incomingRequest.put("tolerance", String.valueOf(bdTolerance));
	                			}
	                		}
	                	}
	                	else
	                	{
	                		String tolerance = propertiesManager.getProperty("VOUCHER", "TOLERANCENEG", " ");
	                		if (!HiltonUtility.isEmpty(tolerance))
	                		{
	                			BigDecimal bdTolerance = new BigDecimal(tolerance);
	                			BigDecimal bdTol = bdOrderTotal.multiply(bdTolerance).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
	                			BigDecimal bdDiff = bdInvoiceTotal.subtract(bdOrderTotal).abs();
	                			if (bdDiff.compareTo(bdTol) > 0)
	                			{
	                				exceptionLineList.add(String.valueOf(i + 1));
	                				incomingRequest.put("tolerance", String.valueOf(bdTolerance));
	                			}
	                		}
	                	}
	                }
                }
            }

            incomingRequest.put("exceptionLineList", exceptionLineList);

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
        	Log.error(this, e.toString());
            this.setStatus(Status.FAILED);
            e.printStackTrace();
            throw e;
            //throw new TsaException("An Error ocurred submitting the current Invoice ", e);
        }
        return null ;
    }
}
