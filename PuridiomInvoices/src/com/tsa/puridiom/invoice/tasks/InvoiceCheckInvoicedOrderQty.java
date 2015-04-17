package com.tsa.puridiom.invoice.tasks;

import com.tsa.puridiom.entity.InvoiceLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
//import com.tsagate.foundation.utility.TsaException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;

public class InvoiceCheckInvoicedOrderQty extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        try
        {
            List ivcLineList = (List) incomingRequest.get("invoiceLineList");
            List exceptionLineList = new ArrayList();
            List varianceList = new ArrayList();

            for (int i = 0; i < ivcLineList.size(); i++)
            {
                InvoiceLine ivl = (InvoiceLine) ivcLineList.get(i);
                if (ivl.getIcPoLine().compareTo(new BigDecimal(0)) > 0)
                {
	                if (ivl.getVchFailsafe().equalsIgnoreCase("Y"))
	                {
	                	BigDecimal bdQty = ivl.getQuantity();
	                    BigDecimal bdOrdered = ivl.getQtyOrdered();

	                    if (bdQty.compareTo(bdOrdered) > 0)
	                	{
	                		BigDecimal bdVariance = ivl.getVchVariance();
	                		bdVariance = bdVariance.divide(new BigDecimal(100), BigDecimal.ROUND_HALF_UP);
	                		BigDecimal newqty = new BigDecimal(0);
	                		newqty = bdOrdered.multiply(bdVariance);

	                		if (bdQty.subtract(bdOrdered).compareTo(newqty) > 0)
	                		{
	                			exceptionLineList.add(String.valueOf(i + 1));
	                			varianceList.add(String.valueOf(bdVariance));
	                		}
	                	}
	                }
                }
            }

            incomingRequest.put("exceptionLineList", exceptionLineList);
            incomingRequest.put("varianceList", varianceList);

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
