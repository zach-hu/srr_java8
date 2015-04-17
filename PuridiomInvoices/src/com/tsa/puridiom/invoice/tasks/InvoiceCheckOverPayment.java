package com.tsa.puridiom.invoice.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
//import com.tsagate.foundation.utility.TsaException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class InvoiceCheckOverPayment extends Task
{
    public Object executeTask(Object object) throws Exception
    {
    	Map incomingRequest = (Map)object;
        try
        {
        	InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
        	BigDecimal bdInvoiced = HiltonUtility.ckNull((BigDecimal) incomingRequest.get("totalAmountInvoiced"));
            List exceptionLineList = new ArrayList();

            if (invoiceHeader.getIcPoHeader().compareTo(new BigDecimal(0)) > 0)
            {
            	PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId"));
                String maxDollarException = propertiesManager.getProperty("VOUCHER", "MAXDOLLAREXCEPTION", "Y");
                String maxDollar = propertiesManager.getProperty("VOUCHER", "MAXDOLLAR", "0");
            	BigDecimal bdMaxDollar = new BigDecimal(maxDollar);

            	//  current invoice total + amount previously invoiced
            	BigDecimal  bdRejecting = invoiceHeader.getInvoiceRejecting();
            	BigDecimal bdTotal = invoiceHeader.getInvoiceTotal().add(bdInvoiced);
            	bdTotal = bdTotal.subtract(bdRejecting);
            	
            	//  add max dollar to po total if max dollar exception is turned on
            	BigDecimal bdAllowableTotal = invoiceHeader.getPoTotal();
            	if (maxDollarException.equalsIgnoreCase("Y"))
            	{
            		bdAllowableTotal = bdAllowableTotal.add(bdMaxDollar);
            	}

                if (bdTotal.compareTo(bdAllowableTotal) > 0)
                {
                	exceptionLineList.add("1");
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
        return null;
    }
}
