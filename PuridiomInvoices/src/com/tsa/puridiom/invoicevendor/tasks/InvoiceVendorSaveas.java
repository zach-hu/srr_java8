package com.tsa.puridiom.invoicevendor.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class InvoiceVendorSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain invoiceVendor */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			InvoiceVendor	originalInvoiceVendor = (InvoiceVendor) incomingRequest.get("invoiceVendor");
			InvoiceVendor	invoiceVendor = new InvoiceVendor();

			invoiceVendor.setVendorId(originalInvoiceVendor.getVendorId());
			invoiceVendor.setVendorName(originalInvoiceVendor.getVendorName());
			/*
			invoiceVendor.setAddressLine1(originalInvoiceVendor.getAddressLine1());
			invoiceVendor.setAddressLine2(originalInvoiceVendor.getAddressLine2());
			invoiceVendor.setAddressLine3(originalInvoiceVendor.getAddressLine3());
			invoiceVendor.setAddressLine4(originalInvoiceVendor.getAddressLine4());
			invoiceVendor.setCity(originalInvoiceVendor.getCity());
			invoiceVendor.setState(originalInvoiceVendor.getState());
			invoiceVendor.setPostalCode(originalInvoiceVendor.getPostalCode());
			invoiceVendor.setCountry(originalInvoiceVendor.getCountry());
			*/
			invoiceVendor.setFobCode(originalInvoiceVendor.getFobCode());
			invoiceVendor.setDateEntered(originalInvoiceVendor.getDateEntered());
			invoiceVendor.setStatus(originalInvoiceVendor.getStatus());
			invoiceVendor.setOwnerEmail(originalInvoiceVendor.getOwnerEmail());
			invoiceVendor.setNotes(originalInvoiceVendor.getNotes());

			incomingRequest.put("invoiceVendor", invoiceVendor);

			InvoiceVendorAdd addTask = new InvoiceVendorAdd();
			invoiceVendor = (InvoiceVendor) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = invoiceVendor;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}