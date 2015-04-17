package com.tsa.puridiom.invoiceheader.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.math.BigDecimal;
import java.util.*;

public class InvoiceHeaderSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain invoiceHeader */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			InvoiceHeader	originalInvoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
			InvoiceHeader	invoiceHeader = new InvoiceHeader();

			invoiceHeader.setIcIvcHeader(new BigDecimal(ukg.getUniqueKey().toString()));
			invoiceHeader.setIcPoHeader(originalInvoiceHeader.getIcPoHeader());
			invoiceHeader.setInvoiceNumber(originalInvoiceHeader.getInvoiceNumber());
			invoiceHeader.setInvoiceDate(originalInvoiceHeader.getInvoiceDate());
			invoiceHeader.setInvoiceTime(originalInvoiceHeader.getInvoiceTime());
			invoiceHeader.setInvoiceDesc(originalInvoiceHeader.getInvoiceDesc());
			invoiceHeader.setInvoiceSubtotal(originalInvoiceHeader.getInvoiceSubtotal());
			invoiceHeader.setInvoiceDiscount(originalInvoiceHeader.getInvoiceDiscount());
			invoiceHeader.setInvoiceTax(originalInvoiceHeader.getInvoiceTax());
			invoiceHeader.setInvoiceShipping(originalInvoiceHeader.getInvoiceShipping());
			invoiceHeader.setInvoiceOther(originalInvoiceHeader.getInvoiceOther());
			invoiceHeader.setInvoiceRejecting(originalInvoiceHeader.getInvoiceRejecting());
			invoiceHeader.setInvoiceTotal(originalInvoiceHeader.getInvoiceTotal());
			invoiceHeader.setSpecialInst(originalInvoiceHeader.getSpecialInst());
			invoiceHeader.setStatus(originalInvoiceHeader.getStatus());
			invoiceHeader.setDateEntered(originalInvoiceHeader.getDateEntered());
			invoiceHeader.setPaymentDueDate(originalInvoiceHeader.getPaymentDueDate());
			invoiceHeader.setTermsCode(originalInvoiceHeader.getTermsCode());
			invoiceHeader.setTermsDiscperc(originalInvoiceHeader.getTermsDiscperc());
			invoiceHeader.setTermsDiscdays(originalInvoiceHeader.getTermsDiscdays());
			invoiceHeader.setTerms2Discperc(originalInvoiceHeader.getTerms2Discperc());
			invoiceHeader.setTerms2Discdays(originalInvoiceHeader.getTerms2Discdays());
			invoiceHeader.setTermsNetdays(originalInvoiceHeader.getTermsNetdays());
			invoiceHeader.setPoNumber(originalInvoiceHeader.getPoNumber());
			invoiceHeader.setPoRelease(originalInvoiceHeader.getPoRelease());
			invoiceHeader.setPoDate(originalInvoiceHeader.getPoDate());
			invoiceHeader.setPoTotal(originalInvoiceHeader.getPoTotal());
			invoiceHeader.setVendorId(originalInvoiceHeader.getVendorId());
			invoiceHeader.setVendorName(originalInvoiceHeader.getVendorName());
			invoiceHeader.setContactName(originalInvoiceHeader.getContactName());
			invoiceHeader.setContactEmail(originalInvoiceHeader.getContactEmail());
			invoiceHeader.setContactPhone(originalInvoiceHeader.getContactPhone());
			invoiceHeader.setContactFax(originalInvoiceHeader.getContactFax());
			invoiceHeader.setShipToCode(originalInvoiceHeader.getShipToCode());
			invoiceHeader.setShipToContact(originalInvoiceHeader.getShipToContact());
			invoiceHeader.setBillToCode(originalInvoiceHeader.getBillToCode());
			invoiceHeader.setBillToContact(originalInvoiceHeader.getBillToContact());
			invoiceHeader.setFobCode(originalInvoiceHeader.getFobCode());
			invoiceHeader.setPcardName(originalInvoiceHeader.getPcardName());
			invoiceHeader.setPcardHolder(originalInvoiceHeader.getPcardHolder());
			invoiceHeader.setPcardNumber(originalInvoiceHeader.getPcardNumber());
			invoiceHeader.setPcardExpires(originalInvoiceHeader.getPcardExpires());
			invoiceHeader.setEftBankName(originalInvoiceHeader.getEftBankName());
			invoiceHeader.setEftBankAccount(originalInvoiceHeader.getEftBankAccount());
			invoiceHeader.setEftAbaAch(originalInvoiceHeader.getEftAbaAch());
			invoiceHeader.setEftAbaWire(originalInvoiceHeader.getEftAbaWire());
			invoiceHeader.setEftHolderAccount(originalInvoiceHeader.getEftHolderAccount());
			invoiceHeader.setFederalId(originalInvoiceHeader.getFederalId());
			invoiceHeader.setOrderByName(originalInvoiceHeader.getOrderByName());
			invoiceHeader.setOrderByEmail(originalInvoiceHeader.getOrderByEmail());
			invoiceHeader.setOrderByPhone(originalInvoiceHeader.getOrderByPhone());
			invoiceHeader.setOwnerEmail(originalInvoiceHeader.getOwnerEmail());
			invoiceHeader.setPrePaid(originalInvoiceHeader.getPrePaid());
			invoiceHeader.setUdf1Code(originalInvoiceHeader.getUdf1Code());
			invoiceHeader.setUdf2Code(originalInvoiceHeader.getUdf2Code());
			invoiceHeader.setUdf3Code(originalInvoiceHeader.getUdf3Code());
			invoiceHeader.setUdf4Code(originalInvoiceHeader.getUdf4Code());
			invoiceHeader.setUdf5Code(originalInvoiceHeader.getUdf5Code());
			invoiceHeader.setUdf6Code(originalInvoiceHeader.getUdf6Code());
			invoiceHeader.setUdf7Code(originalInvoiceHeader.getUdf7Code());
			invoiceHeader.setUdf8Code(originalInvoiceHeader.getUdf8Code());
			invoiceHeader.setUdf9Code(originalInvoiceHeader.getUdf9Code());
			invoiceHeader.setUdf10Code(originalInvoiceHeader.getUdf10Code());
			invoiceHeader.setReasonCode(originalInvoiceHeader.getReasonCode());
			invoiceHeader.setApReference(originalInvoiceHeader.getApReference());
			invoiceHeader.setInvoiceType(originalInvoiceHeader.getInvoiceType());
			invoiceHeader.setFiscalYear(originalInvoiceHeader.getFiscalYear());
			invoiceHeader.setLastExtract("");

			incomingRequest.put("invoiceHeader", invoiceHeader);

			InvoiceHeaderAdd addTask = new InvoiceHeaderAdd();
			invoiceHeader = (InvoiceHeader) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = invoiceHeader;
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
