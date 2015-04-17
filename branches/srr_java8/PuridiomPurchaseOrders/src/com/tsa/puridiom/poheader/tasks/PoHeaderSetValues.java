package com.tsa.puridiom.poheader.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.vendor.VendorManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class PoHeaderSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		String organizationId = (String)incomingRequest.get("organizationId") ;
        String userDateFormat = (String) incomingRequest.get("userDateFormat");

        if (Utility.isEmpty(userDateFormat)) {
            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
        }

		try
		{
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			if (poHeader == null)
			{
				poHeader = new PoHeader();
			}

			if (incomingRequest.containsKey("PoHeader_icPoHeader"))
			{
				String icPoHeaderString = (String) incomingRequest.get("PoHeader_icPoHeader");
				if (Utility.isEmpty(icPoHeaderString))
				{
					icPoHeaderString = "0";
				}
				BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );
				poHeader.setIcPoHeader(icPoHeader);
			}
			if (incomingRequest.containsKey("PoHeader_poType"))
			{
				String poType = (String) incomingRequest.get("PoHeader_poType");
				poHeader.setPoType(poType);
			}
			if (incomingRequest.containsKey("PoHeader_poNumber"))
			{
				String poNumber = (String) incomingRequest.get("PoHeader_poNumber");
				poHeader.setPoNumber(poNumber);
			}
			if (incomingRequest.containsKey("PoHeader_releaseNumber"))
			{
				String releaseNumberString = (String) incomingRequest.get("PoHeader_releaseNumber");
				if (Utility.isEmpty(releaseNumberString))
				{
					releaseNumberString = "0";
				}
				BigDecimal releaseNumber = new BigDecimal ( releaseNumberString );
				poHeader.setReleaseNumber(releaseNumber);
			}
			if (incomingRequest.containsKey("PoHeader_revisionNumber"))
			{
				String revisionNumberString = (String) incomingRequest.get("PoHeader_revisionNumber");
				if (Utility.isEmpty(revisionNumberString))
				{
					revisionNumberString = "0";
				}
				BigDecimal revisionNumber = new BigDecimal ( revisionNumberString );
				poHeader.setRevisionNumber(revisionNumber);
			}
			if (incomingRequest.containsKey("PoHeader_contractNo"))
			{
				String contractNo = (String) incomingRequest.get("PoHeader_contractNo");
				poHeader.setContractNo(contractNo);
			}
			if (incomingRequest.containsKey("PoHeader_poDate"))
			{
				String poDateString = (String) incomingRequest.get("PoHeader_poDate");
				Date poDate = Dates.getSqlDate(poDateString, userDateFormat);
				poHeader.setPoDate(poDate);
			}
			if (incomingRequest.containsKey("PoHeader_requiredDate"))
			{
				String requiredDateString = (String) incomingRequest.get("PoHeader_requiredDate");
				Date requiredDate = Dates.getSqlDate(requiredDateString, userDateFormat);
				poHeader.setRequiredDate(requiredDate);
			}
			if (incomingRequest.containsKey("PoHeader_promisedDate"))
			{
				String promisedDateString = (String) incomingRequest.get("PoHeader_promisedDate");
				Date promisedDate = Dates.getSqlDate(promisedDateString, userDateFormat);
				poHeader.setPromisedDate(promisedDate);
			}
			if (incomingRequest.containsKey("PoHeader_revisionDate"))
			{
				String revisionDateString = (String) incomingRequest.get("PoHeader_revisionDate");
				Date revisionDate = Dates.getSqlDate(revisionDateString, userDateFormat);
				poHeader.setRevisionDate(revisionDate);
			}
			if (incomingRequest.containsKey("PoHeader_vendorId"))
			{
				String vendorId = (String) incomingRequest.get("PoHeader_vendorId");
				poHeader.setVendorId(vendorId);

				String vendorName = VendorManager.getInstance().getVendorName(organizationId, vendorId);
				poHeader.setVendorName(vendorName);
			}
			if (incomingRequest.containsKey("PoHeader_vendContactCode"))
			{
				String vendContactCode = (String) incomingRequest.get("PoHeader_vendContactCode");
				poHeader.setVendContactCode(vendContactCode);
			}
			if (incomingRequest.containsKey("PoHeader_buyerCode"))
			{
				String buyerCode = (String) incomingRequest.get("PoHeader_buyerCode");
				if (Utility.isEmpty(buyerCode))
				{
					buyerCode = (String) incomingRequest.get("userId");
				}
				poHeader.setBuyerCode(buyerCode);
			}
			if (incomingRequest.containsKey("PoHeader_termsCode"))
			{
				String termsCode = (String) incomingRequest.get("PoHeader_termsCode");
				poHeader.setTermsCode(termsCode);
			}
			if (incomingRequest.containsKey("PoHeader_fobCode"))
			{
				String fobCode = (String) incomingRequest.get("PoHeader_fobCode");
				poHeader.setFobCode(fobCode);
			}
			if (incomingRequest.containsKey("PoHeader_shipViaCode"))
			{
				String shipViaCode = (String) incomingRequest.get("PoHeader_shipViaCode");
				poHeader.setShipViaCode(shipViaCode);
			}
			if (incomingRequest.containsKey("PoHeader_currencyCode"))
			{
				String currencyCode = (String) incomingRequest.get("PoHeader_currencyCode");
				poHeader.setCurrencyCode(currencyCode);
			}
			if (incomingRequest.containsKey("PoHeader_shipToCode"))
			{
				String shipToCode = (String) incomingRequest.get("PoHeader_shipToCode");
				poHeader.setShipToCode(shipToCode);
			}
			if (incomingRequest.containsKey("PoHeader_shipToContact"))
			{
				String shipToContact = (String) incomingRequest.get("PoHeader_shipToContact");
				poHeader.setShipToContact(shipToContact);
			}
			if (incomingRequest.containsKey("PoHeader_billToCode"))
			{
				String billToCode = (String) incomingRequest.get("PoHeader_billToCode");
				poHeader.setBillToCode(billToCode);
			}
			if (incomingRequest.containsKey("PoHeader_billToContact"))
			{
				String billToContact = (String) incomingRequest.get("PoHeader_billToContact");
				poHeader.setBillToContact(billToContact);
			}

			if (incomingRequest.containsKey("PoHeader_effectiveDate"))
			{
				String effectiveDateString = (String) incomingRequest.get("PoHeader_effectiveDate");

				if(effectiveDateString.equalsIgnoreCase("") && organizationId.equalsIgnoreCase("BSC04P")){
					poHeader.setEffectiveDate(null);
				}
				else
				{
					Date effectiveDate = Dates.getSqlDate(effectiveDateString, userDateFormat);
					poHeader.setEffectiveDate(effectiveDate);
				}
			}
			if (incomingRequest.containsKey("PoHeader_expirationDate"))
			{
				String expirationDateString = (String) incomingRequest.get("PoHeader_expirationDate");

				if( expirationDateString.equalsIgnoreCase("") && organizationId.equalsIgnoreCase("BSC04P")){
					poHeader.setExpirationDate(null);
				}
				else
				{
					Date expirationDate = Dates.getSqlDate(expirationDateString, userDateFormat);
					poHeader.setExpirationDate(expirationDate);
				}
			}
			if (incomingRequest.containsKey("PoHeader_blanketLimit"))
			{
				String blanketLimitString = (String) incomingRequest.get("PoHeader_blanketLimit");
				if (Utility.isEmpty(blanketLimitString))
				{
					blanketLimitString = "0";
				}
				BigDecimal blanketLimit = new BigDecimal ( blanketLimitString );
				poHeader.setBlanketLimit(blanketLimit);
			}
			if (incomingRequest.containsKey("PoHeader_releaseLimit"))
			{
				String releaseLimitString = (String) incomingRequest.get("PoHeader_releaseLimit");
				if (Utility.isEmpty(releaseLimitString))
				{
					releaseLimitString = "0";
				}
				BigDecimal releaseLimit = new BigDecimal ( releaseLimitString );
				poHeader.setReleaseLimit(releaseLimit);
			}
			if (incomingRequest.containsKey("PoHeader_confirming"))
			{
				String confirming = (String) incomingRequest.get("PoHeader_confirming");
				poHeader.setConfirming(confirming);
			}
			if (incomingRequest.containsKey("PoHeader_confirmDate"))
			{
				String confirmDateString = (String) incomingRequest.get("PoHeader_confirmDate");
				Date confirmDate = Dates.getSqlDate(confirmDateString, userDateFormat);
				poHeader.setConfirmDate(confirmDate);
			}
			if (incomingRequest.containsKey("PoHeader_confirmNameCode"))
			{
				String confirmNameCode = (String) incomingRequest.get("PoHeader_confirmNameCode");
				poHeader.setConfirmNameCode(confirmNameCode);
			}
			if (incomingRequest.containsKey("PoHeader_poCopies"))
			{
				String poCopiesString = (String) incomingRequest.get("PoHeader_poCopies");
				if (Utility.isEmpty(poCopiesString))
				{
					poCopiesString = "0";
				}
				BigDecimal poCopies = new BigDecimal ( poCopiesString );
				poHeader.setPoCopies(poCopies);
			}
			if (incomingRequest.containsKey("PoHeader_ediOrder"))
			{
				String ediOrder = (String) incomingRequest.get("PoHeader_ediOrder");
				poHeader.setEdiOrder(ediOrder);
			}
			if (incomingRequest.containsKey("PoHeader_datePrinted"))
			{
				String datePrintedString = (String) incomingRequest.get("PoHeader_datePrinted");
				Date datePrinted = Dates.getSqlDate(datePrintedString, userDateFormat);
				poHeader.setDatePrinted(datePrinted);
			}
			if (incomingRequest.containsKey("PoHeader_dateFaxed"))
			{
				String dateFaxedString = (String) incomingRequest.get("PoHeader_dateFaxed");
				Date dateFaxed = Dates.getSqlDate(dateFaxedString, userDateFormat);
				poHeader.setDateFaxed(dateFaxed);
			}
			if (incomingRequest.containsKey("PoHeader_dateEdiXmit"))
			{
				String dateEdiXmitString = (String) incomingRequest.get("PoHeader_dateEdiXmit");
				Date dateEdiXmit = Dates.getSqlDate(dateEdiXmitString, userDateFormat);
				poHeader.setDateEdiXmit(dateEdiXmit);
			}
			if (incomingRequest.containsKey("PoHeader_taxCode"))
			{
				String taxCode = (String) incomingRequest.get("PoHeader_taxCode");
				poHeader.setTaxCode(taxCode);
			}
			if (incomingRequest.containsKey("PoHeader_taxPercent"))
			{
				String taxPercentString = (String) incomingRequest.get("PoHeader_taxPercent");
				if (Utility.isEmpty(taxPercentString))
				{
					taxPercentString = "0";
				}
				BigDecimal taxPercent = new BigDecimal ( taxPercentString );
				poHeader.setTaxPercent(taxPercent);
			}
			if (incomingRequest.containsKey("PoHeader_taxAmount"))
			{
				String taxAmountString = (String) incomingRequest.get("PoHeader_taxAmount");
				if (Utility.isEmpty(taxAmountString))
				{
					taxAmountString = "0";
				}
				BigDecimal taxAmount = new BigDecimal ( taxAmountString );
				poHeader.setTaxAmount(taxAmount);
			}
			if (incomingRequest.containsKey("PoHeader_useTaxCode"))
			{
				String taxCode = (String) incomingRequest.get("PoHeader_useTaxCode");
				poHeader.setUseTaxCode(taxCode);
			}
			if (incomingRequest.containsKey("PoHeader_useTaxPercent"))
			{
				String taxPercentString = (String) incomingRequest.get("PoHeader_useTaxPercent");
				if (Utility.isEmpty(taxPercentString))
				{
					taxPercentString = "0";
				}
				BigDecimal taxPercent = new BigDecimal ( taxPercentString );
				poHeader.setUseTaxPercent(taxPercent);
			}
			if (incomingRequest.containsKey("PoHeader_useTaxAmount"))
			{
				String taxAmountString = (String) incomingRequest.get("PoHeader_useTaxAmount");
				if (Utility.isEmpty(taxAmountString))
				{
					taxAmountString = "0";
				}
				BigDecimal taxAmount = new BigDecimal ( taxAmountString );
				poHeader.setUseTaxAmount(taxAmount);
			}
			if (incomingRequest.containsKey("PoHeader_discountCode"))
			{
				String discountCode = (String) incomingRequest.get("PoHeader_discountCode");
				poHeader.setDiscountCode(discountCode);
			}
			if (incomingRequest.containsKey("PoHeader_discountPercent"))
			{
				String discountPercentString = (String) incomingRequest.get("PoHeader_discountPercent");
				if (Utility.isEmpty(discountPercentString))
				{
					discountPercentString = "0";
				}
				BigDecimal discountPercent = new BigDecimal ( discountPercentString );
				poHeader.setDiscountPercent(discountPercent);
			}
			if (incomingRequest.containsKey("PoHeader_discountAmount"))
			{
				String discountAmountString = (String) incomingRequest.get("PoHeader_discountAmount");
				if (Utility.isEmpty(discountAmountString))
				{
					discountAmountString = "0";
				}
				BigDecimal discountAmount = new BigDecimal ( discountAmountString );
				poHeader.setDiscountAmount(discountAmount);
			}
			if (incomingRequest.containsKey("PoHeader_shippingCharges"))
			{
				String shippingChargesString = (String) incomingRequest.get("PoHeader_shippingCharges");
				if (Utility.isEmpty(shippingChargesString))
				{
					shippingChargesString = "0";
				}
				BigDecimal shippingCharges = new BigDecimal ( shippingChargesString );
				poHeader.setShippingCharges(shippingCharges);
			}
			if (incomingRequest.containsKey("PoHeader_shippingTaxable"))
			{
				String shippingTaxable = (String) incomingRequest.get("PoHeader_shippingTaxable");
				poHeader.setShippingTaxable(shippingTaxable);
			}
			if (incomingRequest.containsKey("PoHeader_shippingTax"))
			{
				String shippingTaxString = (String) incomingRequest.get("PoHeader_shippingTax");
				if (Utility.isEmpty(shippingTaxString))
				{
					shippingTaxString = "0";
				}
				BigDecimal shippingTax = new BigDecimal ( shippingTaxString );
				poHeader.setShippingTax(shippingTax);
			}
			if (incomingRequest.containsKey("PoHeader_otherCharges"))
			{
				String otherChargesString = (String) incomingRequest.get("PoHeader_otherCharges");
				if (Utility.isEmpty(otherChargesString))
				{
					otherChargesString = "0";
				}
				BigDecimal otherCharges = new BigDecimal ( otherChargesString );
				poHeader.setOtherCharges(otherCharges);
			}
			if (incomingRequest.containsKey("PoHeader_otherDescription"))
			{
				String otherDescription = (String) incomingRequest.get("PoHeader_otherDescription");
				poHeader.setOtherDescription(otherDescription);
			}
			if (incomingRequest.containsKey("PoHeader_otherTaxable"))
			{
				String otherTaxable = (String) incomingRequest.get("PoHeader_otherTaxable");
				poHeader.setOtherTaxable(otherTaxable);
			}
			if (incomingRequest.containsKey("PoHeader_otherTax"))
			{
				String otherTaxString = (String) incomingRequest.get("PoHeader_otherTax");
				if (Utility.isEmpty(otherTaxString))
				{
					otherTaxString = "0";
				}
				BigDecimal otherTax = new BigDecimal ( otherTaxString );
				poHeader.setOtherTax(otherTax);
			}
			if (incomingRequest.containsKey("PoHeader_prePaid"))
			{
				String prePaid = (String) incomingRequest.get("PoHeader_prePaid");
				poHeader.setPrePaid(prePaid);
			}
			if (incomingRequest.containsKey("PoHeader_udf1Code"))
			{
				String udf1Code = (String) incomingRequest.get("PoHeader_udf1Code");
				poHeader.setUdf1Code(udf1Code);
			}
			if (incomingRequest.containsKey("PoHeader_udf2Code"))
			{
				String udf2Code = (String) incomingRequest.get("PoHeader_udf2Code");
				poHeader.setUdf2Code(udf2Code);
			}
			if (incomingRequest.containsKey("PoHeader_udf3Code"))
			{
				String udf3Code = (String) incomingRequest.get("PoHeader_udf3Code");
				poHeader.setUdf3Code(udf3Code);
			}
			if (incomingRequest.containsKey("PoHeader_udf4Code"))
			{
				String udf4Code = (String) incomingRequest.get("PoHeader_udf4Code");
				poHeader.setUdf4Code(udf4Code);
			}
			if (incomingRequest.containsKey("PoHeader_udf5Code"))
			{
				String udf5Code = (String) incomingRequest.get("PoHeader_udf5Code");
				poHeader.setUdf5Code(udf5Code);
			}
			if (incomingRequest.containsKey("PoHeader_udf6Code"))
			{
				String udf6Code = (String) incomingRequest.get("PoHeader_udf6Code");
				poHeader.setUdf6Code(udf6Code);
			}
			if (incomingRequest.containsKey("PoHeader_udf7Code"))
			{
				String udf7Code = (String) incomingRequest.get("PoHeader_udf7Code");
				poHeader.setUdf7Code(udf7Code);
			}
			if (incomingRequest.containsKey("PoHeader_udf8Code"))
			{
				String udf8Code = (String) incomingRequest.get("PoHeader_udf8Code");
				poHeader.setUdf8Code(udf8Code);
			}
			if (incomingRequest.containsKey("PoHeader_udf9Code"))
			{
				String udf9Code = (String) incomingRequest.get("PoHeader_udf9Code");
				poHeader.setUdf9Code(udf9Code);
			}
			if (incomingRequest.containsKey("PoHeader_udf10Code"))
			{
				String udf10Code = (String) incomingRequest.get("PoHeader_udf10Code");
				poHeader.setUdf10Code(udf10Code);
			}
			if (incomingRequest.containsKey("PoHeader_udf11Code"))
			{
				String udf11Code = (String) incomingRequest.get("PoHeader_udf11Code");
				poHeader.setUdf11Code(udf11Code);
			}
			if (incomingRequest.containsKey("PoHeader_udf12Code"))
			{
				String udf12Code = (String) incomingRequest.get("PoHeader_udf12Code");
				poHeader.setUdf12Code(udf12Code);
			}
			if (incomingRequest.containsKey("PoHeader_udf13Code"))
			{
				String udf13Code = (String) incomingRequest.get("PoHeader_udf13Code");
				poHeader.setUdf13Code(udf13Code);
			}
			if (incomingRequest.containsKey("PoHeader_udf14Code"))
			{
				String udf14Code = (String) incomingRequest.get("PoHeader_udf14Code");
				poHeader.setUdf14Code(udf14Code);
			}
			if (incomingRequest.containsKey("PoHeader_udf15Code"))
			{
				String udf15Code = (String) incomingRequest.get("PoHeader_udf15Code");
				poHeader.setUdf15Code(udf15Code);
			}
			if (incomingRequest.containsKey("PoHeader_status"))
			{
				String status = (String) incomingRequest.get("PoHeader_status");
				poHeader.setStatus(status);
			}
			if (incomingRequest.containsKey("PoHeader_internalComments"))
			{
				String internalComments = (String) incomingRequest.get("PoHeader_internalComments");
				poHeader.setInternalComments(internalComments);
			}
			if (incomingRequest.containsKey("PoHeader_owner"))
			{
				String owner = (String) incomingRequest.get("PoHeader_owner");
				poHeader.setOwner(owner);
			}
			if (incomingRequest.containsKey("PoHeader_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("PoHeader_dateEntered");
				Date dateEntered = Dates.getSqlDate(dateEnteredString, userDateFormat);
				poHeader.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("PoHeader_lastRelease"))
			{
				String lastRelease = (String) incomingRequest.get("PoHeader_lastRelease");
				poHeader.setLastRelease(lastRelease);
			}
			if (incomingRequest.containsKey("PoHeader_lastRevision"))
			{
				String lastRevision = (String) incomingRequest.get("PoHeader_lastRevision");
				poHeader.setLastRevision(lastRevision);
			}
			if (incomingRequest.containsKey("PoHeader_requisitionNumber"))
			{
				String requisitionNumber = (String) incomingRequest.get("PoHeader_requisitionNumber");
				poHeader.setRequisitionNumber(requisitionNumber);
			}
			if (incomingRequest.containsKey("PoHeader_fiscalYear"))
			{
				String fiscalYear = (String) incomingRequest.get("PoHeader_fiscalYear");
				poHeader.setFiscalYear(fiscalYear);
			}
			if (incomingRequest.containsKey("PoHeader_language"))
			{
				String language = (String) incomingRequest.get("PoHeader_language");
				poHeader.setLanguage(language);
			}
			if (incomingRequest.containsKey("PoHeader_currencyFactor"))
			{
				String currencyFactorString = (String) incomingRequest.get("PoHeader_currencyFactor");
				if (Utility.isEmpty(currencyFactorString))
				{
					currencyFactorString = "1";
				}
				BigDecimal currencyFactor = new BigDecimal ( currencyFactorString );
				poHeader.setCurrencyFactor(currencyFactor);
			}
			if (incomingRequest.containsKey("PoHeader_total"))
			{
				String totalString = (String) incomingRequest.get("PoHeader_total");
				if (Utility.isEmpty(totalString))
				{
					totalString = "0";
				}
				BigDecimal total = new BigDecimal ( totalString );
				poHeader.setTotal(total);
			}
			if (incomingRequest.containsKey("PoHeader_subtotal"))
			{
				String subtotalString = (String) incomingRequest.get("PoHeader_subtotal");
				if (Utility.isEmpty(subtotalString))
				{
					subtotalString = "0";
				}
				BigDecimal subtotal = new BigDecimal ( subtotalString );
				poHeader.setSubtotal(subtotal);
			}
			if (incomingRequest.containsKey("PoHeader_icReqHeader"))
			{
				String icReqHeaderString = (String) incomingRequest.get("PoHeader_icReqHeader");
				if (Utility.isEmpty(icReqHeaderString))
				{
					icReqHeaderString = "0";
				}
				BigDecimal icReqHeader = new BigDecimal ( icReqHeaderString );
				poHeader.setIcReqHeader(icReqHeader);
			}
			if (incomingRequest.containsKey("PoHeader_estimatedCost"))
			{
				String estimatedCostString = (String)incomingRequest.get("PoHeader_estimatedCost");
				if (Utility.isEmpty(estimatedCostString))
				{
					estimatedCostString = "0";
				}
				BigDecimal estimatedCost = new BigDecimal(estimatedCostString);
				poHeader.setEstimatedCost(estimatedCost);
			}
			if (incomingRequest.containsKey("PoHeader_icRfqHeader"))
			{
				String icRfqHeaderString = (String) incomingRequest.get("PoHeader_icRfqHeader");
				if (Utility.isEmpty(icRfqHeaderString))
				{
					icRfqHeaderString = "0";
				}
				BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );
				poHeader.setIcRfqHeader(icRfqHeader);
			}
			if (incomingRequest.containsKey("PoHeader_approved"))
			{
				String approved = (String) incomingRequest.get("PoHeader_approved");
				poHeader.setApproved(approved);
			}
			if (incomingRequest.containsKey("PoHeader_appBy"))
			{
				String appBy = (String) incomingRequest.get("PoHeader_appBy");
				poHeader.setAppBy(appBy);
			}
			if (incomingRequest.containsKey("PoHeader_appDate"))
			{
				String appDateString = (String) incomingRequest.get("PoHeader_appDate");
				Date appDate = Dates.getSqlDate(appDateString, userDateFormat);
				poHeader.setAppDate(appDate);
			}
			if (incomingRequest.containsKey("PoHeader_appSigned"))
			{
				String appSigned = (String) incomingRequest.get("PoHeader_appSigned");
				poHeader.setAppSigned(appSigned);
			}
			if (incomingRequest.containsKey("PoHeader_lastChgBy"))
			{
				String lastChgBy = (String) incomingRequest.get("PoHeader_lastChgBy");
				poHeader.setLastChgBy(lastChgBy);
			}
			if (incomingRequest.containsKey("PoHeader_lastChgDate"))
			{
				String lastChgDateString = (String) incomingRequest.get("PoHeader_lastChgDate");
				Date lastChgDate = Dates.getSqlDate(lastChgDateString, userDateFormat);
				poHeader.setLastChgDate(lastChgDate);
			}
			if (incomingRequest.containsKey("PoHeader_rfqNumber"))
			{
				String rfqNumber = (String) incomingRequest.get("PoHeader_rfqNumber");
				poHeader.setRfqNumber(rfqNumber);
			}
			if (incomingRequest.containsKey("PoHeader_ediStatus"))
			{
				String ediStatus = (String) incomingRequest.get("PoHeader_ediStatus");
				poHeader.setEdiStatus(ediStatus);
			}
			if (incomingRequest.containsKey("PoHeader_receiptRequired"))
			{
				String receiptRequired = (String) incomingRequest.get("PoHeader_receiptRequired");
				poHeader.setReceiptRequired(receiptRequired);
			}
			if (incomingRequest.containsKey("PoHeader_contactAddr"))
			{
				String contactAddr = (String) incomingRequest.get("PoHeader_contactAddr");
				poHeader.setContactAddr(contactAddr);
			}
			if (incomingRequest.containsKey("PoHeader_contactName"))
			{
				String contactName = (String) incomingRequest.get("PoHeader_contactName");
				if (Utility.isEmpty(contactName))
				{
					contactName = (String) incomingRequest.get("PoHeader_vendContactCode");
				}
				poHeader.setContactName(contactName);
			}
			if (incomingRequest.containsKey("PoHeader_pyStatus"))
			{
				String pyStatus = (String) incomingRequest.get("PoHeader_pyStatus");
				poHeader.setPyStatus(pyStatus);
			}
			if (incomingRequest.containsKey("PoHeader_savings"))
			{
				String savingsString = (String) incomingRequest.get("PoHeader_savings");
				if (Utility.isEmpty(savingsString))
				{
					savingsString = "0";
				}
				BigDecimal savings = new BigDecimal ( savingsString );
				poHeader.setSavings(savings);
			}
			if (incomingRequest.containsKey("PoHeader_savingsReason"))
			{
				String savingsReason = (String) incomingRequest.get("PoHeader_savingsReason");
				poHeader.setSavingsReason(savingsReason);
			}
			if (incomingRequest.containsKey("PoHeader_linkNextOrder"))
			{
				String linkNextOrder = (String) incomingRequest.get("PoHeader_linkNextOrder");
				poHeader.setLinkNextOrder(linkNextOrder);
			}
			if (incomingRequest.containsKey("PoHeader_linkPriorOrder"))
			{
				String linkPriorOrder = (String) incomingRequest.get("PoHeader_linkPriorOrder");
				poHeader.setLinkPriorOrder(linkPriorOrder);
			}
			if (incomingRequest.containsKey("PoHeader_apBatchid"))
			{
				String apBatchid = (String) incomingRequest.get("PoHeader_apBatchid");
				poHeader.setApBatchid(apBatchid);
			}
			if (incomingRequest.containsKey("PoHeader_icNextOrderLink"))
			{
				String icNextOrderLinkString = (String) incomingRequest.get("PoHeader_icNextOrderLink");
				if (Utility.isEmpty(icNextOrderLinkString))
				{
					icNextOrderLinkString = "0";
				}
				BigDecimal icNextOrderLink = new BigDecimal ( icNextOrderLinkString );
				poHeader.setIcNextOrderLink(icNextOrderLink);
			}
			if (incomingRequest.containsKey("PoHeader_icPriorOrderLink"))
			{
				String icPriorOrderLinkString = (String) incomingRequest.get("PoHeader_icPriorOrderLink");
				if (Utility.isEmpty(icPriorOrderLinkString))
				{
					icPriorOrderLinkString = "0";
				}
				BigDecimal icPriorOrderLink = new BigDecimal ( icPriorOrderLinkString );
				poHeader.setIcPriorOrderLink(icPriorOrderLink);
			}
			if (incomingRequest.containsKey("PoHeader_pcardOrder"))
			{
				String pcardOrder = (String) incomingRequest.get("PoHeader_pcardOrder");
				poHeader.setPcardOrder(pcardOrder);
			}
			if (incomingRequest.containsKey("PoHeader_pcardName"))
			{
				String pcardName = (String ) incomingRequest.get("PoHeader_pcardName");
				poHeader.setPcardName(pcardName);
			}
			if (incomingRequest.containsKey("PoHeader_pcardHolder"))
			{
				String pcardHolder = (String ) incomingRequest.get("PoHeader_pcardHolder");
				poHeader.setPcardHolder(pcardHolder);
			}
			if (incomingRequest.containsKey("PoHeader_pcardNumber"))
			{
				String pcardNumber = (String ) incomingRequest.get("PoHeader_pcardNumber");
				poHeader.setPcardNumber(pcardNumber);
			}
			if (incomingRequest.containsKey("PoHeader_pcardExpires"))
			{
				String pcardExpires = (String ) incomingRequest.get("PoHeader_pcardExpires");
				poHeader.setPcardExpires(pcardExpires);
			}
			if (incomingRequest.containsKey("PoHeader_lockStatus"))
			{
				String lockStatus = (String) incomingRequest.get("PoHeader_lockStatus");
				poHeader.setLockStatus(lockStatus);
			}
			if (incomingRequest.containsKey("PoHeader_poRecalc"))
			{
				String poRecalc = (String) incomingRequest.get("PoHeader_poRecalc");
				poHeader.setPoRecalc(poRecalc);
			}
			if (incomingRequest.containsKey("PoHeader_actionAlertFlag"))
			{
				String actionAlertFlag = (String) incomingRequest.get("PoHeader_actionAlertFlag");
				poHeader.setActionAlertFlag(actionAlertFlag);
			}
			if (incomingRequest.containsKey("PoHeader_obligAmt"))
			{
				String obligAmtString = (String) incomingRequest.get("PoHeader_obligAmt");
				if (Utility.isEmpty(obligAmtString))
				{
					obligAmtString = "0";
				}
				BigDecimal obligAmt = new BigDecimal ( obligAmtString );
				poHeader.setObligAmt(obligAmt);
			}
			if (incomingRequest.containsKey("PoHeader_obligDate"))
			{
				String obligDateString = (String) incomingRequest.get("PoHeader_obligDate");
				Date obligDate = Dates.getSqlDate(obligDateString, userDateFormat);
				poHeader.setObligDate(obligDate);
			}
			if (incomingRequest.containsKey("PoHeader_vendorEval"))
			{
				String vendorEval = (String) incomingRequest.get("PoHeader_vendorEval");
				poHeader.setVendorEval(vendorEval);
			}
			if (incomingRequest.containsKey("PoHeader_requisitionerCode"))
			{
				String requisitionerCode = (String) incomingRequest.get("PoHeader_requisitionerCode");
				poHeader.setRequisitionerCode(requisitionerCode);
			}
			if (incomingRequest.containsKey("PoHeader_departmentCode"))
			{
				String departmentCode = (String) incomingRequest.get("PoHeader_departmentCode");
				poHeader.setDepartmentCode(departmentCode);
			}
			if (incomingRequest.containsKey("PoHeader_priorityCode"))
			{
				String priorityCode = (String) incomingRequest.get("PoHeader_priorityCode");
				poHeader.setPriorityCode(priorityCode);
			}
			if (incomingRequest.containsKey("PoHeader_routing"))
			{
				String routing = (String ) incomingRequest.get("PoHeader_routing");
				poHeader.setRouting(routing);
			}
			if (incomingRequest.containsKey("PoHeader_itemLocation"))
			{
				String itemLocation = (String ) incomingRequest.get("PoHeader_itemLocation");
				poHeader.setItemLocation(itemLocation);
			}
			if (incomingRequest.containsKey("PoHeader_authorizationCode"))
			{
				String authorizationCode = (String ) incomingRequest.get("PoHeader_authorizationCode");
				poHeader.setAuthorizationCode(authorizationCode);
			}
			if (incomingRequest.containsKey("PoHeader_icHeaderHistory"))
			{
				String icHeaderHistoryString = (String) incomingRequest.get("PoHeader_icHeaderHistory");
				if (Utility.isEmpty(icHeaderHistoryString))
				{
					icHeaderHistoryString = "0";
				}
				BigDecimal icHeaderHistory = new BigDecimal ( icHeaderHistoryString );
				poHeader.setIcHeaderHistory(icHeaderHistory);
			}
			if (incomingRequest.containsKey("PoHeader_revisionValue"))
			{
				String revisionValueString = (String) incomingRequest.get("PoHeader_revisionValue");
				if (Utility.isEmpty(revisionValueString))
				{
					revisionValueString = "0";
				}
				BigDecimal revisionValue = new BigDecimal ( revisionValueString );
				poHeader.setRevisionValue(revisionValue);
			}
			if (incomingRequest.containsKey("PoHeader_autoRelease"))
			{
				String autoRelease = (String) incomingRequest.get("PoHeader_autoRelease");
				poHeader.setAutoRelease(autoRelease);
			}
			if (incomingRequest.containsKey("PoHeader_autoInterval"))
			{
				String autoIntervalString = (String) incomingRequest.get("PoHeader_autoInterval");
				if (Utility.isEmpty(autoIntervalString))
				{
					autoIntervalString = "0";
				}
				BigDecimal autoInterval = new BigDecimal ( autoIntervalString );
				poHeader.setAutoInterval(autoInterval);
			}
			if (incomingRequest.containsKey("PoHeader_autoReleased"))
			{
				String autoReleased = (String) incomingRequest.get("PoHeader_autoReleased");
				poHeader.setAutoReleased(autoReleased);
			}
			if (incomingRequest.containsKey("PoHeader_autoPayment"))
			{
				String autoPayment = (String) incomingRequest.get("PoHeader_autoPayment");
				poHeader.setAutoPayment(autoPayment);
			}
			if (incomingRequest.containsKey("PoHeader_autoImport"))
			{
				String autoImport = (String) incomingRequest.get("PoHeader_autoImport");
				poHeader.setAutoImport(autoImport);
			}
			if (incomingRequest.containsKey("PoHeader_vendorClass"))
			{
				String vendorClass = (String) incomingRequest.get("PoHeader_vendorClass");
				poHeader.setVendorClass(vendorClass);
			}
			if (incomingRequest.containsKey("PoHeader_inspectionReqd"))
			{
				String inspectionReqd = (String) incomingRequest.get("PoHeader_inspectionReqd");
				poHeader.setInspectionReqd(inspectionReqd);
			}
			if (incomingRequest.containsKey("PoHeader_contactPhone"))
			{
				String contactPhone = (String) incomingRequest.get("PoHeader_contactPhone");
				poHeader.setContactPhone(contactPhone);
			}
			if (incomingRequest.containsKey("PoHeader_contactMobilePhone"))
			{
				String contactMobilePhone = (String) incomingRequest.get("PoHeader_contactMobilePhone");
				poHeader.setContactMobilePhone(contactMobilePhone);
			}
			if (incomingRequest.containsKey("PoHeader_dateAcknowledged"))
			{
				String dateAcknowledgedString = (String) incomingRequest.get("PoHeader_dateAcknowledged");
				Date dateAcknowledged = Dates.getSqlDate(dateAcknowledgedString, userDateFormat);
				poHeader.setDateAcknowledged(dateAcknowledged);
			}
            if (incomingRequest.containsKey("PoHeader_acknowledgedBy"))
            {
                String acknowledgedBy = (String) incomingRequest.get("PoHeader_acknowledgedBy");
                poHeader.setAcknowledgedBy(acknowledgedBy);
            }
            if (incomingRequest.containsKey("PoHeader_shipToInv"))
            {
                String shipToInv = (String) incomingRequest.get("PoHeader_shipToInv");
                poHeader.setShipToInv(shipToInv);
            }
            if (incomingRequest.containsKey("PoHeader_printMode")) {
                String printMode = (String) incomingRequest.get("PoHeader_printMode");
                poHeader.setPrintMode(printMode);
            }
            if (incomingRequest.containsKey("PoHeader_icHeaderKey"))
			{
				String icHeaderKeyString = (String) incomingRequest.get("PoHeader_icHeaderKey");
				if (Utility.isEmpty(icHeaderKeyString))
				{
					icHeaderKeyString = "0";
				}
				BigDecimal icHeaderKey = new BigDecimal ( icHeaderKeyString );
				poHeader.setIcHeaderKey(icHeaderKey);
			}
            if (incomingRequest.containsKey("PoHeader_timeZone"))
            {
                String timeZone = (String) incomingRequest.get("PoHeader_timeZone");
                poHeader.setTimeZone(timeZone);
            }
            if (incomingRequest.containsKey("PoHeader_bidWaiver"))
            {
                String bidWaiver = "";
                if(incomingRequest.get("PoHeader_bidWaiver") instanceof String)
                {
                	bidWaiver = (String) incomingRequest.get("PoHeader_bidWaiver");
                	poHeader.setBidWaiver(bidWaiver);
                }
            }
            if (incomingRequest.containsKey("PoHeader_iclLevel"))
            {
                String iclLevelString = (String) incomingRequest.get("PoHeader_iclLevel");
                if (Utility.isEmpty(iclLevelString))
                {
                	iclLevelString = "0";
                }
                BigDecimal iclLevel = new BigDecimal(iclLevelString);
                poHeader.setIclLevel(iclLevel);
            }
            if (incomingRequest.containsKey("PoHeader_gfpGfm"))
			{
				String gfpGfm = (String) incomingRequest.get("PoHeader_gfpGfm");
				poHeader.setGfpGfm(gfpGfm);
			}
            if (incomingRequest.containsKey("PoHeader_kit"))
			{
				String kit = (String ) incomingRequest.get("PoHeader_kit");
				poHeader.setKit(kit);
			}
            if (incomingRequest.containsKey("PoHeader_workOrder"))
			{
				String workOrder = (String ) incomingRequest.get("PoHeader_workOrder");
				poHeader.setWorkOrder(workOrder);
			}
            if (incomingRequest.containsKey("PoHeader_requestCat"))
			{
				String cat = (String) incomingRequest.get("PoHeader_requestCat");
				poHeader.setRequestCat(cat);
			}
            if (incomingRequest.containsKey("PoHeader_subType"))
			{
				String subType = (String) incomingRequest.get("PoHeader_subType");
				poHeader.setSubType(subType);
			}
            if (incomingRequest.containsKey("PoHeader_naics"))
			{
				String naics = (String) incomingRequest.get("PoHeader_naics");
				poHeader.setNaics(naics);
			}
            if (incomingRequest.containsKey("PoHeader_insuranceRqd"))
			{
				String insurance = (String) incomingRequest.get("PoHeader_insuranceRqd");
				poHeader.setInsuranceRqd(insurance);
			}
            if (incomingRequest.containsKey("PoHeader_originalReqType"))
            {
            	String originalReqType = (String) incomingRequest.get("PoHeader_originalReqType");
            	poHeader.setOriginalReqType(originalReqType);
            }
            if (incomingRequest.containsKey("PoHeader_icMsrHeader"))
			{
            	Object icMsrObj = incomingRequest.get("PoHeader_icMsrHeader");
            	if (icMsrObj instanceof BigDecimal) {
    				poHeader.setIcMsrHeader((BigDecimal) icMsrObj);
            	} else {
					String icMsrHeaderString = (String) incomingRequest.get("PoHeader_icMsrHeader");
					if (Utility.isEmpty(icMsrHeaderString))
					{
						icMsrHeaderString = "0";
					}
					BigDecimal icMsrHeader = new BigDecimal ( icMsrHeaderString );
					poHeader.setIcMsrHeader(icMsrHeader);
            	}
			}
            if (incomingRequest.containsKey("PoHeader_msrNumber"))
			{
				String msrNumber = (String) incomingRequest.get("PoHeader_msrNumber");
				poHeader.setMsrNumber(msrNumber);
			}
            if (incomingRequest.containsKey("PoHeader_corrosionEval"))
			{
				String corrosionEval = (String) incomingRequest.get("PoHeader_corrosionEval");
				poHeader.setCorrosionEval(corrosionEval);
			}

			result = poHeader;
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
