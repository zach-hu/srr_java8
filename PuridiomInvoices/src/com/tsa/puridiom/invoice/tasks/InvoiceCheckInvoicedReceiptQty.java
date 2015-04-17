package com.tsa.puridiom.invoice.tasks;

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

public class InvoiceCheckInvoicedReceiptQty extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        try
        {
            List ivcLineList = (List) incomingRequest.get("invoiceLineList");
            List exceptionLineList = new ArrayList();

            PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId"));

            for (int i = 0; i < ivcLineList.size(); i++)
            {
                InvoiceLine ivl = (InvoiceLine) ivcLineList.get(i);

                String receiptRequired = "";
                if(ivl.getPoLine() != null)
                	receiptRequired = ivl.getPoLine().getReceiptRequired();

                boolean allowReceiptRequired = false;
                if (receiptRequired.equalsIgnoreCase("R") || receiptRequired.equalsIgnoreCase("E"))
                	allowReceiptRequired = true;

                if ((ivl.getIcPoLine().compareTo(new BigDecimal(0)) > 0) && allowReceiptRequired)
                {
	                if (ivl.getVchFailsafe().equalsIgnoreCase("Y") || propertiesManager.getProperty("VOUCHER", "RECEIPTQUANTITYEXCEPTION", "Y").equalsIgnoreCase("Y"))
	                {
	                	/*if (ivl.getQtyOrdered().compareTo(new BigDecimal(0)) > 0)
	                	{
	                		BigDecimal bQuantity = ivl.getQuantity();
	                		if (bQuantity.compareTo(ivl.getQtyOrdered()) > 0)
	                		{
	                			exceptionLineList.add(String.valueOf(i + 1));
	                			break;
	                		}
	                		BigDecimal bQtyRemaining = ivl.getQtyOrdered().subtract(ivl.getQtyInvoiced());
	                		if (bQuantity.compareTo(bQtyRemaining) > 0)
	                		{
	                			exceptionLineList.add(String.valueOf(i + 1));
	                		}
	                	}*/
	                	BigDecimal bdQty = ivl.getQuantity();
	                    BigDecimal bdReceived = ivl.getQtyReceived();
	                    BigDecimal bdInvoiced = ivl.getQtyInvoiced();
	                    bdInvoiced = bdInvoiced.add(bdQty);

	                    if (bdInvoiced.compareTo(bdReceived) > 0)
	                    {
	                    	exceptionLineList.add(String.valueOf(i + 1));
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
