package com.tsa.puridiom.invoice.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class InvoiceCheckReceiptOrderQty extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        try
        {
        	PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
            List ivcLineList = (List) incomingRequest.get("invoiceLineList");
            List exceptionLineList = new ArrayList();

            if (poHeader == null) poHeader = new PoHeader();

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
                	BigDecimal bdOrdered = ivl.getQtyOrdered();
                	BigDecimal bdReceived = ivl.getQtyReceived();

                	if (poHeader.getSubType().equalsIgnoreCase("20"))
                	{
                		if (bdReceived.compareTo(bdOrdered) > 0)
                    	{
                    		exceptionLineList.add(String.valueOf(i + 1));
                    	}
                	}
                	else if (poHeader.getSubType().equalsIgnoreCase("21"))
                	{
                		if (bdReceived.compareTo(bdOrdered.multiply(new BigDecimal(1.1))) > 0)
                		{
                			exceptionLineList.add(String.valueOf(i + 1));
                		}
                	}
                	else
                	{
                		if  (bdReceived.compareTo(bdOrdered) > 0)
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
