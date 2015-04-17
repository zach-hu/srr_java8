package com.tsa.puridiom.invoice.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;

public class InvoiceCheckInvoiceBalance extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        try
        {
            InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
            List exceptionLineList = new ArrayList();

            BigDecimal  bdSubtotal = invoiceHeader.getInvoiceSubtotal();
            BigDecimal  bdDiscount = invoiceHeader.getInvoiceDiscount();
        	//BigDecimal  bdAdjustment = invoiceHeader.getInvoiceAdjustment();
        	BigDecimal  bdTax = invoiceHeader.getInvoiceTax();
        	//BigDecimal  bdUseTax = invoiceHeader.getUseTax() ;
        	BigDecimal  bdShipping = invoiceHeader.getInvoiceShipping();
        	BigDecimal  bdOther = invoiceHeader.getInvoiceOther();
        	BigDecimal  bdRejecting = invoiceHeader.getInvoiceRejecting().abs();

        	BigDecimal bdTotal = bdSubtotal.subtract(bdDiscount);
        	//bdTotal = bdTotal.add(bdTax).add(bdUseTax).add(bdShipping).add(bdOther);
        	bdTotal = bdTotal.add(bdTax).add(bdShipping).add(bdOther);
        	bdTotal = bdTotal.add(bdRejecting);

        	if (bdTotal.compareTo(invoiceHeader.getInvoiceTotal()) != 0)
        	{
        		if(!HiltonUtility.ckNull(invoiceHeader.getUdf1Code()).equals("C"))
        		exceptionLineList.add("1");
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
        }
        return null ;
    }
}
