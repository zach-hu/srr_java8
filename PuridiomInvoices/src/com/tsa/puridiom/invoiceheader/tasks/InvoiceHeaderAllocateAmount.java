/*
 * Created on September 20, 2005
 */
package com.tsa.puridiom.invoiceheader.tasks;

import java.math.BigDecimal;
import java.util.*;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author kathleen
 */
public class InvoiceHeaderAllocateAmount extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		String result = null ;

		try {
			InvoiceHeader ivh = (InvoiceHeader) incomingRequest.get("invoiceHeader");
			BigDecimal bdSubtotal = ivh.getInvoiceSubtotal();
			BigDecimal bdDiscount = ivh.getInvoiceDiscount();
			BigDecimal bdTax = ivh.getInvoiceTax();
			BigDecimal bdShipping = ivh.getInvoiceShipping();
			BigDecimal bdOther = ivh.getInvoiceOther();
			BigDecimal bdUseTax = ivh.getUseTax();

			String formType = HiltonUtility.ckNull((String) incomingRequest.get("formType"));
			if (formType.equals("IVT"))
			{
				incomingRequest.put("Account_icLine", "1");
				return bdTax.toString();
			}
			else if (formType.equals("IVS"))
			{
				incomingRequest.put("Account_icLine", "2");
				return bdShipping.toString();
			}
			if (formType.equals("IVO"))
			{
				incomingRequest.put("Account_icLine", "3");
				return bdOther.toString();
			}
			if (formType.equals("IVU"))
			{
				incomingRequest.put("Account_icLine", "4");
				return bdUseTax.negate().toString();
			}

			String allocated = (String) incomingRequest.get("allocatedTotal");
			if (allocated == null)
			{
				allocated = "0" ;
			}
			BigDecimal alloc = new BigDecimal(allocated);
			if(HiltonUtility.ckNull(ivh.getUdf1Code()).equals("C"))
			{
				bdSubtotal = ivh.getInvoiceTotal();
			}
			BigDecimal bdTotal = bdSubtotal.subtract(bdDiscount).add(bdTax).add(bdUseTax).add(bdShipping).add(bdOther);

			List taxAccountList = (List) incomingRequest.get("taxAccountList");
			if (taxAccountList != null && taxAccountList.size() > 0)
			{
				bdTotal = bdTotal.subtract(bdTax);
			}
			List shippingAccountList = (List) incomingRequest.get("shippingAccountList");
			if (shippingAccountList != null && shippingAccountList.size() > 0)
			{
				bdTotal = bdTotal.subtract(bdShipping);
			}
			List otherAccountList = (List) incomingRequest.get("otherAccountList");
			if (otherAccountList != null && otherAccountList.size() > 0)
			{
				bdTotal = bdTotal.subtract(bdOther);
			}
			/*
			List usetaxAccountList = (List) incomingRequest.get("usetaxAccountList");
			if (usetaxAccountList != null && usetaxAccountList.size() > 0)
			{
				bdTotal = bdTotal.subtract(bdUseTax);
			}
			*/
			bdTotal = bdTotal.subtract(alloc);
			System.out.print("YES");
			//danzm - Removed this check because the negative amount should be showing as a credit amount.
			/*if (bdTotal.compareTo(new BigDecimal(0)) < 0)
			{
				bdTotal = new BigDecimal(0);
			}*/
			result = bdTotal.toString();
			//result = bdTotal.subtract(alloc).toString();

	        this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result;
	}

}
