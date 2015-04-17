package com.tsa.puridiom.invoiceheader.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InvoiceHeaderSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		String organizationId = (String) incomingRequest.get("organizationId");
        String userDateFormat = (String) incomingRequest.get("userDateFormat");

        if (Utility.isEmpty(userDateFormat)) {
            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
        }

		try
		{
			InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
			if (invoiceHeader == null)
			{
				invoiceHeader = new InvoiceHeader();
			}

			if (incomingRequest.containsKey("InvoiceHeader_icIvcHeader"))
			{
				String icIvcHeaderString = (String) incomingRequest.get("InvoiceHeader_icIvcHeader");
				if (Utility.isEmpty(icIvcHeaderString))
				{
					icIvcHeaderString = "0";
				}
				BigDecimal icIvcHeader = new BigDecimal ( icIvcHeaderString );
				invoiceHeader.setIcIvcHeader(icIvcHeader);
			}
			if (incomingRequest.containsKey("InvoiceHeader_icPoHeader"))
			{
				String icPoHeaderString = (String) incomingRequest.get("InvoiceHeader_icPoHeader");
				if (Utility.isEmpty(icPoHeaderString))
				{
					icPoHeaderString = "0";
				}
				BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );
				invoiceHeader.setIcPoHeader(icPoHeader);
			}
			if (incomingRequest.containsKey("InvoiceHeader_invoiceNumber"))
			{
				String invoiceNumber = (String) incomingRequest.get("InvoiceHeader_invoiceNumber");
				invoiceHeader.setInvoiceNumber(invoiceNumber);
			}
			if (incomingRequest.containsKey("InvoiceHeader_invoiceDate"))
			{
				String invoiceDateString = (String) incomingRequest.get("InvoiceHeader_invoiceDate");
				Date invoiceDate = Dates.getSqlDate(invoiceDateString, userDateFormat);
				invoiceHeader.setInvoiceDate(invoiceDate);
			}
			if (incomingRequest.containsKey("InvoiceHeader_invoiceTime"))
			{
				String invoiceTime = (String) incomingRequest.get("InvoiceHeader_invoiceTime");
				invoiceHeader.setInvoiceTime(invoiceTime);
			}
			if (incomingRequest.containsKey("InvoiceHeader_invoiceDesc"))
			{
				String invoiceDesc = (String) incomingRequest.get("InvoiceHeader_invoiceDesc");
				invoiceHeader.setInvoiceDesc(invoiceDesc);
			}
			if (incomingRequest.containsKey("InvoiceHeader_invoiceSubtotal"))
			{
				String invoiceSubtotalString = (String) incomingRequest.get("InvoiceHeader_invoiceSubtotal");
				if (Utility.isEmpty(invoiceSubtotalString))
				{
					invoiceSubtotalString = "0";
				}
				BigDecimal invoiceSubtotal = new BigDecimal ( invoiceSubtotalString );
				invoiceHeader.setInvoiceSubtotal(invoiceSubtotal);
			}
			if (incomingRequest.containsKey("InvoiceHeader_invoiceDiscount"))
			{
				String invoiceDiscountString = (String) incomingRequest.get("InvoiceHeader_invoiceDiscount");
				if (Utility.isEmpty(invoiceDiscountString))
				{
					invoiceDiscountString = "0";
				}
				BigDecimal invoiceDiscount = new BigDecimal ( invoiceDiscountString );
				invoiceHeader.setInvoiceDiscount(invoiceDiscount);
			}
			if (incomingRequest.containsKey("InvoiceHeader_invoiceTax"))
			{
				String invoiceTaxString = (String) incomingRequest.get("InvoiceHeader_invoiceTax");
				if (Utility.isEmpty(invoiceTaxString))
				{
					invoiceTaxString = "0";
				}
				BigDecimal invoiceTax = new BigDecimal ( invoiceTaxString );
				invoiceHeader.setInvoiceTax(invoiceTax);
			}
			if (incomingRequest.containsKey("InvoiceHeader_invoiceShipping"))
			{
				String invoiceShippingString = (String) incomingRequest.get("InvoiceHeader_invoiceShipping");
				if (Utility.isEmpty(invoiceShippingString))
				{
					invoiceShippingString = "0";
				}
				BigDecimal invoiceShipping = new BigDecimal ( invoiceShippingString );
				invoiceHeader.setInvoiceShipping(invoiceShipping);
			}
			if (incomingRequest.containsKey("InvoiceHeader_invoiceOther"))
			{
				String invoiceOtherString = (String) incomingRequest.get("InvoiceHeader_invoiceOther");
				if (Utility.isEmpty(invoiceOtherString))
				{
					invoiceOtherString = "0";
				}
				BigDecimal invoiceOther = new BigDecimal ( invoiceOtherString );
				invoiceHeader.setInvoiceOther(invoiceOther);
			}
			if (incomingRequest.containsKey("InvoiceHeader_invoiceRejecting"))
			{
				String invoiceRejectingString = (String) incomingRequest.get("InvoiceHeader_invoiceRejecting");
				if (Utility.isEmpty(invoiceRejectingString))
				{
					invoiceRejectingString = "0";
				}
				BigDecimal invoiceRejecting = new BigDecimal ( invoiceRejectingString );
				invoiceHeader.setInvoiceRejecting(invoiceRejecting);
			}
			if (incomingRequest.containsKey("InvoiceHeader_invoiceTotal"))
			{
				String invoiceTotalString = (String) incomingRequest.get("InvoiceHeader_invoiceTotal");
				if (Utility.isEmpty(invoiceTotalString))
				{
					invoiceTotalString = "0";
				}
				BigDecimal invoiceTotal = new BigDecimal ( invoiceTotalString );
				invoiceHeader.setInvoiceTotal(invoiceTotal);
			}
			if (incomingRequest.containsKey("InvoiceHeader_invoiceSubTotal"))
			{
				String invoiceSubTotalString = (String) incomingRequest.get("InvoiceHeader_invoiceSubTotal");
				if (Utility.isEmpty(invoiceSubTotalString))
				{
					invoiceSubTotalString = "0";
				}
				BigDecimal invoiceSubTotal = new BigDecimal ( invoiceSubTotalString );
				invoiceHeader.setInvoiceSubtotal(invoiceSubTotal);
			}
			if (incomingRequest.containsKey("InvoiceHeader_invoicePaid"))
			{
				String invoicePaidString = (String) incomingRequest.get("InvoiceHeader_invoicePaid");
				if (Utility.isEmpty(invoicePaidString))
				{
					invoicePaidString = "0";
				}
				BigDecimal invoicePaid = new BigDecimal ( invoicePaidString );
				invoiceHeader.setInvoicePaid(invoicePaid);
			}
			if (incomingRequest.containsKey("InvoiceHeader_invoiceAdjustment"))
			{
				String invoiceAdjustmentString = (String) incomingRequest.get("InvoiceHeader_invoiceAdjustment");
				if (Utility.isEmpty(invoiceAdjustmentString))
				{
					invoiceAdjustmentString = "0";
				}
				BigDecimal invoiceAdjustment = new BigDecimal ( invoiceAdjustmentString );
				invoiceHeader.setInvoiceAdjustment(invoiceAdjustment);
			}
			if (incomingRequest.containsKey("InvoiceHeader_specialInst"))
			{
				String specialInst = (String) incomingRequest.get("InvoiceHeader_specialInst");
				invoiceHeader.setSpecialInst(specialInst);
			}
			if (incomingRequest.containsKey("InvoiceHeader_status"))
			{
				String status = (String) incomingRequest.get("InvoiceHeader_status");
				invoiceHeader.setStatus(status);
			}
			if (incomingRequest.containsKey("InvoiceHeader_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("InvoiceHeader_dateEntered");
				Date dateEntered = Dates.getSqlDate(dateEnteredString, userDateFormat);
				invoiceHeader.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("InvoiceHeader_paymentDueDate"))
			{
				String paymentDueDateString = (String) incomingRequest.get("InvoiceHeader_paymentDueDate");
				Date paymentDueDate = Dates.getSqlDate(paymentDueDateString, userDateFormat);
				invoiceHeader.setPaymentDueDate(paymentDueDate);
			}
			if (incomingRequest.containsKey("InvoiceHeader_paymentDate"))
			{
				String paymentDateString = (String) incomingRequest.get("InvoiceHeader_paymentDate");
				Date paymentDate = Dates.getSqlDate(paymentDateString, userDateFormat);
				invoiceHeader.setPaymentDate(paymentDate);
			}
			if (incomingRequest.containsKey("InvoiceHeader_termsCode"))
			{
				String termsCode = (String) incomingRequest.get("InvoiceHeader_termsCode");
				invoiceHeader.setTermsCode(termsCode);
			}
			if (incomingRequest.containsKey("InvoiceHeader_termsDiscperc"))
			{
				String termsDiscpercString = (String) incomingRequest.get("InvoiceHeader_termsDiscperc");
				if (Utility.isEmpty(termsDiscpercString))
				{
					termsDiscpercString = "0";
				}
				BigDecimal termsDiscperc = new BigDecimal ( termsDiscpercString );
				invoiceHeader.setTermsDiscperc(termsDiscperc);
			}
			if (incomingRequest.containsKey("InvoiceHeader_termsDiscdays"))
			{
				String termsDiscdaysString = (String) incomingRequest.get("InvoiceHeader_termsDiscdays");
				if (Utility.isEmpty(termsDiscdaysString))
				{
					termsDiscdaysString = "0";
				}
				BigDecimal termsDiscdays = new BigDecimal ( termsDiscdaysString );
				invoiceHeader.setTermsDiscdays(termsDiscdays);
			}
			if (incomingRequest.containsKey("InvoiceHeader_terms2Discperc"))
			{
				String terms2DiscpercString = (String) incomingRequest.get("InvoiceHeader_terms2Discperc");
				if (Utility.isEmpty(terms2DiscpercString))
				{
					terms2DiscpercString = "0";
				}
				BigDecimal terms2Discperc = new BigDecimal ( terms2DiscpercString );
				invoiceHeader.setTerms2Discperc(terms2Discperc);
			}
			if (incomingRequest.containsKey("InvoiceHeader_terms2Discdays"))
			{
				String terms2DiscdaysString = (String) incomingRequest.get("InvoiceHeader_terms2Discdays");
				if (Utility.isEmpty(terms2DiscdaysString))
				{
					terms2DiscdaysString = "0";
				}
				BigDecimal terms2Discdays = new BigDecimal ( terms2DiscdaysString );
				invoiceHeader.setTerms2Discdays(terms2Discdays);
			}
			if (incomingRequest.containsKey("InvoiceHeader_termsNetdays"))
			{
				String termsNetdaysString = (String) incomingRequest.get("InvoiceHeader_termsNetdays");
				if (Utility.isEmpty(termsNetdaysString))
				{
					termsNetdaysString = "0";
				}
				BigDecimal termsNetdays = new BigDecimal ( termsNetdaysString );
				invoiceHeader.setTermsNetdays(termsNetdays);
			}
			if (incomingRequest.containsKey("InvoiceHeader_poNumber"))
			{
				String poNumber = (String) incomingRequest.get("InvoiceHeader_poNumber");
				invoiceHeader.setPoNumber(poNumber);
			}
			if (incomingRequest.containsKey("InvoiceHeader_poRelease"))
			{
				String poReleaseString = (String) incomingRequest.get("InvoiceHeader_poRelease");
				if (Utility.isEmpty(poReleaseString))
				{
					poReleaseString = "0";
				}
				BigDecimal poRelease = new BigDecimal ( poReleaseString );
				invoiceHeader.setPoRelease(poRelease);
			}
			if (incomingRequest.containsKey("InvoiceHeader_poDate"))
			{
				String poDateString = (String) incomingRequest.get("InvoiceHeader_poDate");
				Date poDate = Dates.getSqlDate(poDateString, userDateFormat);
				invoiceHeader.setPoDate(poDate);
			}
			if (incomingRequest.containsKey("InvoiceHeader_poTotal"))
			{
				String poTotalString = (String) incomingRequest.get("InvoiceHeader_poTotal");
				if (Utility.isEmpty(poTotalString))
				{
					poTotalString = "0";
				}
				BigDecimal poTotal = new BigDecimal ( poTotalString );
				invoiceHeader.setPoTotal(poTotal);
			}
			if (incomingRequest.containsKey("InvoiceHeader_vendorId"))
			{
				String vendorId = (String ) incomingRequest.get("InvoiceHeader_vendorId");
				invoiceHeader.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("InvoiceHeader_vendorName"))
			{
				String vendorName = (String ) incomingRequest.get("InvoiceHeader_vendorName");
				invoiceHeader.setVendorName(vendorName);
			}
			if (incomingRequest.containsKey("InvoiceHeader_contactName"))
			{
				String contactName = (String ) incomingRequest.get("InvoiceHeader_contactName");
				invoiceHeader.setContactName(contactName);
			}
			if (incomingRequest.containsKey("InvoiceHeader_contactEmail"))
			{
				String contactEmail = (String ) incomingRequest.get("InvoiceHeader_contactEmail");
				invoiceHeader.setContactEmail(contactEmail);
			}
			if (incomingRequest.containsKey("InvoiceHeader_contactPhone"))
			{
				String contactPhone = (String ) incomingRequest.get("InvoiceHeader_contactPhone");
				invoiceHeader.setContactPhone(contactPhone);
			}
			if (incomingRequest.containsKey("InvoiceHeader_contactFax"))
			{
				String contactFax = (String ) incomingRequest.get("InvoiceHeader_contactFax");
				invoiceHeader.setContactFax(contactFax);
			}
			if (incomingRequest.containsKey("InvoiceHeader_shipToCode"))
			{
				String shipToCode = (String ) incomingRequest.get("InvoiceHeader_shipToCode");
				invoiceHeader.setShipToCode(shipToCode);
			}
			if (incomingRequest.containsKey("InvoiceHeader_shipToContact"))
			{
				String shipToContact = (String ) incomingRequest.get("InvoiceHeader_shipToContact");
				invoiceHeader.setShipToContact(shipToContact);
			}
			if (incomingRequest.containsKey("InvoiceHeader_billToCode"))
			{
				String billToCode = (String ) incomingRequest.get("InvoiceHeader_billToCode");
				invoiceHeader.setBillToCode(billToCode);
			}
			if (incomingRequest.containsKey("InvoiceHeader_billToContact"))
			{
				String billToContact = (String ) incomingRequest.get("InvoiceHeader_billToContact");
				invoiceHeader.setBillToContact(billToContact);
			}
			if (incomingRequest.containsKey("InvoiceHeader_fobCode"))
			{
				String fobCode = (String ) incomingRequest.get("InvoiceHeader_fobCode");
				invoiceHeader.setFobCode(fobCode);
			}
			if (incomingRequest.containsKey("InvoiceHeader_pcardName"))
			{
				String pcardName = (String ) incomingRequest.get("InvoiceHeader_pcardName");
				invoiceHeader.setPcardName(pcardName);
			}
			if (incomingRequest.containsKey("InvoiceHeader_pcardHolder"))
			{
				String pcardHolder = (String ) incomingRequest.get("InvoiceHeader_pcardHolder");
				invoiceHeader.setPcardHolder(pcardHolder);
			}
			if (incomingRequest.containsKey("InvoiceHeader_pcardNumber"))
			{
				String pcardNumber = (String ) incomingRequest.get("InvoiceHeader_pcardNumber");
				invoiceHeader.setPcardNumber(pcardNumber);
			}
			if (incomingRequest.containsKey("InvoiceHeader_pcardExpires"))
			{
				String pcardExpires = (String ) incomingRequest.get("InvoiceHeader_pcardExpires");
				invoiceHeader.setPcardExpires(pcardExpires);
			}
			if (incomingRequest.containsKey("InvoiceHeader_eftBankName"))
			{
				String eftBankName = (String ) incomingRequest.get("InvoiceHeader_eftBankName");
				invoiceHeader.setEftBankName(eftBankName);
			}
			if (incomingRequest.containsKey("InvoiceHeader_eftBankAccount"))
			{
				String eftBankAccount = (String ) incomingRequest.get("InvoiceHeader_eftBankAccount");
				invoiceHeader.setEftBankAccount(eftBankAccount);
			}
			if (incomingRequest.containsKey("InvoiceHeader_eftAbaAch"))
			{
				String eftAbaAch = (String ) incomingRequest.get("InvoiceHeader_eftAbaAch");
				invoiceHeader.setEftAbaAch(eftAbaAch);
			}
			if (incomingRequest.containsKey("InvoiceHeader_eftAbaWire"))
			{
				String eftAbaWire = (String ) incomingRequest.get("InvoiceHeader_eftAbaWire");
				invoiceHeader.setEftAbaWire(eftAbaWire);
			}
			if (incomingRequest.containsKey("InvoiceHeader_eftHolderAccount"))
			{
				String eftHolderAccount = (String ) incomingRequest.get("InvoiceHeader_eftHolderAccount");
				invoiceHeader.setEftHolderAccount(eftHolderAccount);
			}
			if (incomingRequest.containsKey("InvoiceHeader_federalId"))
			{
				String federalId = (String ) incomingRequest.get("InvoiceHeader_federalId");
				invoiceHeader.setFederalId(federalId);
			}
			if (incomingRequest.containsKey("InvoiceHeader_orderByName"))
			{
				String orderByName = (String ) incomingRequest.get("InvoiceHeader_orderByName");
				invoiceHeader.setOrderByName(orderByName);
			}
			if (incomingRequest.containsKey("InvoiceHeader_orderByEmail"))
			{
				String orderByEmail = (String ) incomingRequest.get("InvoiceHeader_orderByEmail");
				invoiceHeader.setOrderByEmail(orderByEmail);
			}
			if (incomingRequest.containsKey("InvoiceHeader_orderByPhone"))
			{
				String orderByPhone = (String ) incomingRequest.get("InvoiceHeader_orderByPhone");
				invoiceHeader.setOrderByPhone(orderByPhone);
			}
			if (incomingRequest.containsKey("InvoiceHeader_ownerEmail"))
			{
				String ownerEmail = (String ) incomingRequest.get("InvoiceHeader_ownerEmail");
				invoiceHeader.setOwnerEmail(ownerEmail);
			}
			if (incomingRequest.containsKey("InvoiceHeader_enteredBy"))
			{
				String enteredBy = (String) incomingRequest.get("InvoiceHeader_enteredBy");
				invoiceHeader.setEnteredBy(enteredBy);
			}
			if (incomingRequest.containsKey("InvoiceHeader_prePaid"))
			{
				String prePaid = (String) incomingRequest.get("InvoiceHeader_prePaid");
				invoiceHeader.setPrePaid(prePaid);
			}
			if (incomingRequest.containsKey("InvoiceHeader_udf1Code"))
			{
				String udf1Code = (String) incomingRequest.get("InvoiceHeader_udf1Code");
				invoiceHeader.setUdf1Code(udf1Code);
			}
			if (incomingRequest.containsKey("InvoiceHeader_udf2Code"))
			{
				String udf2Code = (String) incomingRequest.get("InvoiceHeader_udf2Code");
				invoiceHeader.setUdf2Code(udf2Code);
			}
			if (incomingRequest.containsKey("InvoiceHeader_udf3Code"))
			{
				String udf3Code = (String) incomingRequest.get("InvoiceHeader_udf3Code");
				invoiceHeader.setUdf3Code(udf3Code);
			}
			if (incomingRequest.containsKey("InvoiceHeader_udf4Code"))
			{
				String udf4Code = (String) incomingRequest.get("InvoiceHeader_udf4Code");
				invoiceHeader.setUdf4Code(udf4Code);
			}
			if (incomingRequest.containsKey("InvoiceHeader_udf5Code"))
			{
				String udf5Code = (String) incomingRequest.get("InvoiceHeader_udf5Code");
				invoiceHeader.setUdf5Code(udf5Code);
			}
			if (incomingRequest.containsKey("InvoiceHeader_udf6Code"))
			{
				String udf6Code = (String) incomingRequest.get("InvoiceHeader_udf6Code");
				invoiceHeader.setUdf6Code(udf6Code);
			}
			if (incomingRequest.containsKey("InvoiceHeader_udf7Code"))
			{
				String udf7Code = (String) incomingRequest.get("InvoiceHeader_udf7Code");
				invoiceHeader.setUdf7Code(udf7Code);
			}
			if (incomingRequest.containsKey("InvoiceHeader_udf8Code"))
			{
				String udf8Code = (String) incomingRequest.get("InvoiceHeader_udf8Code");
				invoiceHeader.setUdf8Code(udf8Code);
			}
			if (incomingRequest.containsKey("InvoiceHeader_udf9Code"))
			{
				String udf9Code = (String) incomingRequest.get("InvoiceHeader_udf9Code");
				invoiceHeader.setUdf9Code(udf9Code);
			}
			if (incomingRequest.containsKey("InvoiceHeader_udf10Code"))
			{
				String udf10Code = (String) incomingRequest.get("InvoiceHeader_udf10Code");
				invoiceHeader.setUdf10Code(udf10Code);
			}
			if (incomingRequest.containsKey("InvoiceHeader_reasonCode"))
			{
				String reasonCode = (String) incomingRequest.get("InvoiceHeader_reasonCode");
				invoiceHeader.setReasonCode(reasonCode);
			}
			if (incomingRequest.containsKey("InvoiceHeader_apReference"))
			{
				String apReference = (String) incomingRequest.get("InvoiceHeader_apReference");
				invoiceHeader.setApReference(apReference);
			}
			if (incomingRequest.containsKey("InvoiceHeader_invoiceType"))
			{
				String invoiceType = (String) incomingRequest.get("InvoiceHeader_invoiceType");
				invoiceHeader.setInvoiceType(invoiceType);
			}
			if (incomingRequest.containsKey("InvoiceHeader_vendorAddrCode"))
			{
				String vendorAddrCode = (String) incomingRequest.get("InvoiceHeader_vendorAddrCode");
				invoiceHeader.setVendorAddrCode(vendorAddrCode);
			}
			if (incomingRequest.containsKey("InvoiceHeader_lastExtract"))
			{
				String lastExtract = (String) incomingRequest.get("InvoiceHeader_lastExtract");
				invoiceHeader.setLastExtract(lastExtract);
			}
			if (incomingRequest.containsKey("InvoiceHeader_invoiceReceivedDate"))
			{
				String invoiceReceivedDateString = (String) incomingRequest.get("InvoiceHeader_invoiceReceivedDate");
				Date invoiceReceivedDate = Dates.getSqlDate(invoiceReceivedDateString, userDateFormat);
				invoiceHeader.setInvoiceReceivedDate(invoiceReceivedDate);
			}
			if (incomingRequest.containsKey("InvoiceHeader_invoicePrintedDate"))
			{
				String invoicePrintedDateString = (String) incomingRequest.get("InvoiceHeader_invoicePrintedDate");
				Date invoicePrintedDate = Dates.getSqlDate(invoicePrintedDateString, userDateFormat);
				invoiceHeader.setInvoicePrintedDate(invoicePrintedDate);
			}
			if (incomingRequest.containsKey("InvoiceHeader_icHeaderHistory"))
			{
				String icHeaderHistoryString = (String) incomingRequest.get("InvoiceHeader_icHeaderHistory");
				if (Utility.isEmpty(icHeaderHistoryString))
				{
					icHeaderHistoryString = "0";
				}
				BigDecimal icHeaderHistory = new BigDecimal ( icHeaderHistoryString );
				invoiceHeader.setIcHeaderHistory(icHeaderHistory);
			}
			if (incomingRequest.containsKey("InvoiceHeader_currencyFactor"))
			{
				String currencyFactorString = (String) incomingRequest.get("InvoiceHeader_currencyFactor");
				if (Utility.isEmpty(currencyFactorString))
				{
				    currencyFactorString = "1";
				}
				BigDecimal currencyFactor = new BigDecimal ( currencyFactorString );
				invoiceHeader.setCurrencyFactor(currencyFactor);
			}
			if (incomingRequest.containsKey("InvoiceHeader_currencyCode"))
			{
				String currencyCode = (String) incomingRequest.get("InvoiceHeader_currencyCode");
				invoiceHeader.setCurrencyCode(currencyCode);
			}
			if (incomingRequest.containsKey("InvoiceHeader_departmentCode"))
			{
				String departmentCode = (String) incomingRequest.get("InvoiceHeader_departmentCode");
				invoiceHeader.setDepartmentCode(departmentCode);
			}
			if (incomingRequest.containsKey("InvoiceHeader_vendorAccount"))
			{
				String vendorAccount = (String) incomingRequest.get("InvoiceHeader_vendorAccount");
				invoiceHeader.setVendorAccount(vendorAccount);
			}
			if (incomingRequest.containsKey("InvoiceHeader_useTax"))
			{
				String useTaxString = (String) incomingRequest.get("InvoiceHeader_useTax");
				if (Utility.isEmpty(useTaxString))
				{
					useTaxString = "0";
				}
				BigDecimal useTax = new BigDecimal ( useTaxString );
				invoiceHeader.setUseTax(useTax);
			}
			if (incomingRequest.containsKey("InvoiceHeader_fiscalYear"))
			{
				String fiscalYear = (String) incomingRequest.get("InvoiceHeader_fiscalYear");
				invoiceHeader.setFiscalYear(fiscalYear);
			}
			if (incomingRequest.containsKey("InvoiceHeader_obmoNumber"))
			{
				String obmoNumber = (String) incomingRequest.get("InvoiceHeader_obmoNumber");
				invoiceHeader.setObmoNumber(obmoNumber);
			}
			if (incomingRequest.containsKey("InvoiceHeader_obmoDate"))
			{
				String obmoDateString = (String) incomingRequest.get("InvoiceHeader_obmoDate");
				if (!HiltonUtility.isEmpty(obmoDateString))
				{
					Date obmoDate = Dates.getSqlDate(obmoDateString, userDateFormat);
					invoiceHeader.setObmoDate(obmoDate);
				}
			}
			if (incomingRequest.containsKey("InvoiceHeader_obmoTotal"))
			{
				String obmoTotalString = (String) incomingRequest.get("InvoiceHeader_obmoTotal");
				if (Utility.isEmpty(obmoTotalString))
				{
					obmoTotalString = "0";
				}
				BigDecimal obmoTotal = new BigDecimal ( obmoTotalString );
				invoiceHeader.setObmoTotal(obmoTotal);
			}
			if (incomingRequest.containsKey("InvoiceHeader_inspectionDate"))
			{
				String inspectionDateString = (String) incomingRequest.get("InvoiceHeader_inspectionDate");
				if (!HiltonUtility.isEmpty(inspectionDateString))
				{
					Date inspectionDate = Dates.getSqlDate(inspectionDateString, userDateFormat);
					invoiceHeader.setInspectionDate(inspectionDate);
				}
			}
			if (incomingRequest.containsKey("InvoiceHeader_processedDate"))
			{
				String processedDateString = (String) incomingRequest.get("InvoiceHeader_processedDate");
				if (!HiltonUtility.isEmpty(processedDateString))
				{
					Date processedDate = Dates.getSqlDate(processedDateString, userDateFormat);
					invoiceHeader.setProcessedDate(processedDate);
				}
			}
			if (incomingRequest.containsKey("InvoiceHeader_imisApprovedDate"))
			{
				String imisApprovedDateString = (String) incomingRequest.get("InvoiceHeader_imisApprovedDate");
				if (!HiltonUtility.isEmpty(imisApprovedDateString))
				{
					Date imisApprovedDate = Dates.getSqlDate(imisApprovedDateString, userDateFormat);
					invoiceHeader.setImisApprovedDate(imisApprovedDate);
				}
			}
			if (incomingRequest.containsKey("InvoiceHeader_imisPaymentDate"))
			{
				String imisPaymentDateString = (String) incomingRequest.get("InvoiceHeader_imisPaymentDate");
				if (!HiltonUtility.isEmpty(imisPaymentDateString))
				{
					Date imisPaymentDate = Dates.getSqlDate(imisPaymentDateString, userDateFormat);
					invoiceHeader.setImisPaymentDate(imisPaymentDate);
				}
			}
			if (incomingRequest.containsKey("InvoiceHeader_discountDate"))
			{
				String discountDateString = (String) incomingRequest.get("InvoiceHeader_discountDate");
				Date discountDate = Dates.getSqlDate(discountDateString, userDateFormat);
				invoiceHeader.setDiscountDate(discountDate);
			}
			if (incomingRequest.containsKey("InvoiceHeader_checkBatchId"))
			{
				String checkBatchId = (String ) incomingRequest.get("InvoiceHeader_checkBatchId");
				invoiceHeader.setCheckBatchId(checkBatchId);
			}

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
