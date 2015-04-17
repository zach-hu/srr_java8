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

public class InvoiceCheckMaxDollar extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        try
        {
        	PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId"));
        	String maxLimit = propertiesManager.getProperty("VOUCHER", "MAXDOLLAR", "0");
        	BigDecimal bdMaxLimit = new BigDecimal(maxLimit);

            List ivcLineList = (List) incomingRequest.get("invoiceLineList");
            List exceptionLineList = new ArrayList();

            for (int i = 0; i < ivcLineList.size(); i++)
            {
                InvoiceLine ivl = (InvoiceLine) ivcLineList.get(i);
                if (ivl.getIcPoLine().compareTo(new BigDecimal(0)) > 0)
                {
	                BigDecimal bdInvoiceTotal = ivl.getQuantity().multiply(ivl.getUnitPrice());
	                BigDecimal bdOrderTotal = ivl.getQuantity().multiply(ivl.getOrderUnitCost());

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
	                			BigDecimal bdDiff = bdOrderTotal.subtract(bdInvoiceTotal).abs();
	                			if (bdDiff.compareTo(bdMaxLimit) > 0)
	                			{
	                				exceptionLineList.add(String.valueOf(i + 1));
	                				incomingRequest.put("maxLimit", String.valueOf(bdMaxLimit));
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
