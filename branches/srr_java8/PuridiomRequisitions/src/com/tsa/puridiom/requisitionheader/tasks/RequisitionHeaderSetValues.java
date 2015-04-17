package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.vendor.VendorManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class RequisitionHeaderSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		String organizationId = (String) incomingRequest.get("organizationId");
        String userDateFormat = (String) incomingRequest.get("userDateFormat");

        if (HiltonUtility.isEmpty(userDateFormat)) {
            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DateFormat", "MM-dd-yyyy") ;
        }
		try
		{
			RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			if (requisitionHeader == null)
			{
				requisitionHeader = new RequisitionHeader();
			}

			if (incomingRequest.containsKey("RequisitionHeader_icReqHeader"))
			{
				String icReqHeaderString = (String) incomingRequest.get("RequisitionHeader_icReqHeader");
				if (Utility.isEmpty(icReqHeaderString))
				{
					icReqHeaderString = "0";
				}
				BigDecimal icReqHeader = new BigDecimal ( icReqHeaderString );
				requisitionHeader.setIcReqHeader(icReqHeader);
			}
			if (incomingRequest.containsKey("RequisitionHeader_requisitionNumber"))
			{
				String requisitionNumber = (String ) incomingRequest.get("RequisitionHeader_requisitionNumber");
				requisitionHeader.setRequisitionNumber(requisitionNumber);
			}
			if (incomingRequest.containsKey("RequisitionHeader_requisitionType"))
			{
				String requisitionType = (String ) incomingRequest.get("RequisitionHeader_requisitionType");
				requisitionHeader.setRequisitionType(requisitionType);
			}
			if (incomingRequest.containsKey("RequisitionHeader_requisitionDate"))
			{
				String requisitionDateString = (String) incomingRequest.get("RequisitionHeader_requisitionDate");
				Date requisitionDate = Dates.getSqlDate(requisitionDateString, userDateFormat);
				requisitionHeader.setRequisitionDate(requisitionDate);
			}
			if (incomingRequest.containsKey("RequisitionHeader_status"))
			{
				String status = (String ) incomingRequest.get("RequisitionHeader_status");
				requisitionHeader.setStatus(status);
			}
			if (incomingRequest.containsKey("RequisitionHeader_discountSource"))
			{
				String discountSource = (String ) incomingRequest.get("RequisitionHeader_discountSource");
				requisitionHeader.setDiscountSource(discountSource);
			}
			if (incomingRequest.containsKey("RequisitionHeader_discountPercent"))
			{
				String discountPercentString = (String) incomingRequest.get("RequisitionHeader_discountPercent");
				if (Utility.isEmpty(discountPercentString))
				{
					discountPercentString = "0";
				}
				BigDecimal discountPercent = new BigDecimal ( discountPercentString );
				requisitionHeader.setDiscountPercent(discountPercent);
			}
			if (incomingRequest.containsKey("RequisitionHeader_discountAmount"))
			{
				String discountAmountString = (String) incomingRequest.get("RequisitionHeader_discountAmount");
				if (Utility.isEmpty(discountAmountString))
				{
					discountAmountString = "0";
				}
				BigDecimal discountAmount = new BigDecimal ( discountAmountString );
				requisitionHeader.setDiscountAmount(discountAmount);
			}
			if (incomingRequest.containsKey("RequisitionHeader_shippingCharges"))
			{
				String shippingChargesString = (String) incomingRequest.get("RequisitionHeader_shippingCharges");
				if (Utility.isEmpty(shippingChargesString))
				{
					shippingChargesString = "0";
				}
				BigDecimal shippingCharges = new BigDecimal ( shippingChargesString );
				requisitionHeader.setShippingCharges(shippingCharges);
			}
			if (incomingRequest.containsKey("RequisitionHeader_taxShipping"))
			{
				String taxShipping = (String ) incomingRequest.get("RequisitionHeader_taxShipping");
				requisitionHeader.setTaxShipping(taxShipping);
			}
			if (incomingRequest.containsKey("RequisitionHeader_otherCharges"))
			{
				String otherChargesString = (String) incomingRequest.get("RequisitionHeader_otherCharges");
				if (Utility.isEmpty(otherChargesString))
				{
					otherChargesString = "0";
				}
				BigDecimal otherCharges = new BigDecimal ( otherChargesString );
				requisitionHeader.setOtherCharges(otherCharges);
			}
			if (incomingRequest.containsKey("RequisitionHeader_taxOther"))
			{
				String taxOther = (String ) incomingRequest.get("RequisitionHeader_taxOther");
				requisitionHeader.setTaxOther(taxOther);
			}
			if (incomingRequest.containsKey("RequisitionHeader_otherChargDesc"))
			{
				String otherChargDesc = (String ) incomingRequest.get("RequisitionHeader_otherChargDesc");
				requisitionHeader.setOtherChargDesc(otherChargDesc);
			}
			if (incomingRequest.containsKey("RequisitionHeader_internalComments"))
			{
				String internalComments = (String ) incomingRequest.get("RequisitionHeader_internalComments");
				requisitionHeader.setInternalComments(internalComments);
			}
			if (incomingRequest.containsKey("RequisitionHeader_fiscalYear"))
			{
				String fiscalYear = (String ) incomingRequest.get("RequisitionHeader_fiscalYear");
				requisitionHeader.setFiscalYear(fiscalYear);
			}
			if (incomingRequest.containsKey("RequisitionHeader_owner"))
			{
				String owner = (String ) incomingRequest.get("RequisitionHeader_owner");
				requisitionHeader.setOwner(owner);
			}
			if (incomingRequest.containsKey("RequisitionHeader_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("RequisitionHeader_dateEntered");
				Date dateEntered = Dates.getSqlDate(dateEnteredString, userDateFormat);
				requisitionHeader.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("RequisitionHeader_taxPercent"))
			{
				String taxPercentString = (String) incomingRequest.get("RequisitionHeader_taxPercent");
				if (Utility.isEmpty(taxPercentString))
				{
					taxPercentString = "0";
				}
				BigDecimal taxPercent = new BigDecimal ( taxPercentString );
				requisitionHeader.setTaxPercent(taxPercent);
			}
			if (incomingRequest.containsKey("RequisitionHeader_taxAmount"))
			{
				String taxAmountString = (String) incomingRequest.get("RequisitionHeader_taxAmount");
				if (Utility.isEmpty(taxAmountString))
				{
					taxAmountString = "0";
				}
				BigDecimal taxAmount = new BigDecimal ( taxAmountString );
				requisitionHeader.setTaxAmount(taxAmount);
			}
			if (incomingRequest.containsKey("RequisitionHeader_otherTaxAmount"))
			{
				String otherTaxAmountString = (String) incomingRequest.get("RequisitionHeader_otherTaxAmount");
				if (Utility.isEmpty(otherTaxAmountString))
				{
					otherTaxAmountString = "0";
				}
				BigDecimal otherTaxAmount = new BigDecimal ( otherTaxAmountString );
				requisitionHeader.setOtherTaxAmount(otherTaxAmount);
			}
			if (incomingRequest.containsKey("RequisitionHeader_shippingTaxAmt"))
			{
				String shippingTaxAmtString = (String) incomingRequest.get("RequisitionHeader_shippingTaxAmt");
				if (Utility.isEmpty(shippingTaxAmtString))
				{
					shippingTaxAmtString = "0";
				}
				BigDecimal shippingTaxAmt = new BigDecimal ( shippingTaxAmtString );
				requisitionHeader.setShippingTaxAmt(shippingTaxAmt);
			}
			if (incomingRequest.containsKey("RequisitionHeader_language"))
			{
				String language = (String ) incomingRequest.get("RequisitionHeader_language");
				requisitionHeader.setLanguage(language);
			}
			if (incomingRequest.containsKey("RequisitionHeader_subtotal"))
			{
				String subtotalString = (String) incomingRequest.get("RequisitionHeader_subtotal");
				if (Utility.isEmpty(subtotalString))
				{
					subtotalString = "0";
				}
				BigDecimal subtotal = new BigDecimal ( subtotalString );
				requisitionHeader.setSubtotal(subtotal);
			}
			if (incomingRequest.containsKey("RequisitionHeader_total"))
			{
				String totalString = (String) incomingRequest.get("RequisitionHeader_total");
				if (Utility.isEmpty(totalString))
				{
					totalString = "0";
				}
				BigDecimal total = new BigDecimal ( totalString );
				requisitionHeader.setTotal(total);
			}
			if (incomingRequest.containsKey("RequisitionHeader_appDate"))
			{
				String appDateString = (String) incomingRequest.get("RequisitionHeader_appDate");
				Date appDate = Dates.getSqlDate(appDateString, userDateFormat);
				requisitionHeader.setAppDate(appDate);
			}
			if (incomingRequest.containsKey("RequisitionHeader_appSigned"))
			{
				String appSigned = (String ) incomingRequest.get("RequisitionHeader_appSigned");
				requisitionHeader.setAppSigned(appSigned);
			}
			if (incomingRequest.containsKey("RequisitionHeader_lastChgBy"))
			{
				String lastChgBy = (String ) incomingRequest.get("RequisitionHeader_lastChgBy");
				requisitionHeader.setLastChgBy(lastChgBy);
			}
			if (incomingRequest.containsKey("RequisitionHeader_lastChgDate"))
			{
				String lastChgDateString = (String) incomingRequest.get("RequisitionHeader_lastChgDate");
				Date lastChgDate = Dates.getSqlDate(lastChgDateString, userDateFormat);
				requisitionHeader.setLastChgDate(lastChgDate);
			}
			if (incomingRequest.containsKey("RequisitionHeader_approved"))
			{
				String approved = (String ) incomingRequest.get("RequisitionHeader_approved");
				requisitionHeader.setApproved(approved);
			}
			if (incomingRequest.containsKey("RequisitionHeader_appBy"))
			{
				String appBy = (String ) incomingRequest.get("RequisitionHeader_appBy");
				requisitionHeader.setAppBy(appBy);
			}
			if (incomingRequest.containsKey("RequisitionHeader_assignedBuyer"))
			{
				String assignedBuyer = (String ) incomingRequest.get("RequisitionHeader_assignedBuyer");
				requisitionHeader.setAssignedBuyer(assignedBuyer);
			}
			if (incomingRequest.containsKey("RequisitionHeader_assignedDate"))
			{
				String assignedDateString = (String) incomingRequest.get("RequisitionHeader_assignedDate");
				Date assignedDate = Dates.getSqlDate(assignedDateString, userDateFormat);
				requisitionHeader.setAssignedDate(assignedDate);
			}
			if (incomingRequest.containsKey("RequisitionHeader_estimatedCost"))
			{
				String estimatedCostString = (String) incomingRequest.get("RequisitionHeader_estimatedCost");
				if (Utility.isEmpty(estimatedCostString))
				{
					estimatedCostString = "0";
				}
				BigDecimal estimatedCost = new BigDecimal ( estimatedCostString );
				requisitionHeader.setEstimatedCost(estimatedCost);
			}
			if (incomingRequest.containsKey("RequisitionHeader_bidWaiver"))
			{
				String bidWaiver = (String ) incomingRequest.get("RequisitionHeader_bidWaiver");
				requisitionHeader.setBidWaiver(bidWaiver);
			}
			if (incomingRequest.containsKey("RequisitionHeader_shipAttn"))
			{
				String shipAttn = (String ) incomingRequest.get("RequisitionHeader_shipAttn");
				requisitionHeader.setShipAttn(shipAttn);
			}
			if (incomingRequest.containsKey("RequisitionHeader_vendorAttn"))
			{
				String vendorAttn = (String ) incomingRequest.get("RequisitionHeader_vendorAttn");
				requisitionHeader.setVendorAttn(vendorAttn);
			}
			if (incomingRequest.containsKey("RequisitionHeader_rqHeaderKey"))
			{
				String rqHeaderKey = (String ) incomingRequest.get("RequisitionHeader_rqHeaderKey");
				requisitionHeader.setRqHeaderKey(rqHeaderKey);
			}
			if (incomingRequest.containsKey("RequisitionHeader_rqSubType"))
			{
				String rqSubType = (String ) incomingRequest.get("RequisitionHeader_rqSubType");
				requisitionHeader.setRqSubType(rqSubType);
			}
			if (incomingRequest.containsKey("RequisitionHeader_pcardReq"))
			{
				String pcardReq = (String ) incomingRequest.get("RequisitionHeader_pcardReq");
				requisitionHeader.setPcardReq(pcardReq);
			}
			if (incomingRequest.containsKey("RequisitionHeader_pcardName"))
			{
				String pcardName = (String ) incomingRequest.get("RequisitionHeader_pcardName");
				requisitionHeader.setPcardName(pcardName);
			}
			if (incomingRequest.containsKey("RequisitionHeader_pcardHolder"))
			{
				String pcardHolder = (String ) incomingRequest.get("RequisitionHeader_pcardHolder");
				requisitionHeader.setPcardHolder(pcardHolder);
			}
			if (incomingRequest.containsKey("RequisitionHeader_pcardNumber"))
			{
				String pcardNumber = (String ) incomingRequest.get("RequisitionHeader_pcardNumber");
				requisitionHeader.setPcardNumber(pcardNumber);
			}
			if (incomingRequest.containsKey("RequisitionHeader_pcardExpires"))
			{
				String pcardExpires = (String ) incomingRequest.get("RequisitionHeader_pcardExpires");
				requisitionHeader.setPcardExpires(pcardExpires);
			}
			if (incomingRequest.containsKey("RequisitionHeader_icRevisedOrder"))
			{
				String icRevisedOrderString = (String) incomingRequest.get("RequisitionHeader_icRevisedOrder");
				if (Utility.isEmpty(icRevisedOrderString))
				{
					icRevisedOrderString = "0";
				}
				BigDecimal icRevisedOrder = new BigDecimal ( icRevisedOrderString );
				requisitionHeader.setIcRevisedOrder(icRevisedOrder);
			}
			if (incomingRequest.containsKey("RequisitionHeader_reqRecalc"))
			{
				String reqRecalc = (String ) incomingRequest.get("RequisitionHeader_reqRecalc");
				requisitionHeader.setReqRecalc(reqRecalc);
			}
			if (incomingRequest.containsKey("RequisitionHeader_actionAlertFlag"))
			{
				String actionAlertFlag = (String ) incomingRequest.get("RequisitionHeader_actionAlertFlag");
				requisitionHeader.setActionAlertFlag(actionAlertFlag);
			}
			if (incomingRequest.containsKey("RequisitionHeader_maxStatus"))
			{
				String maxStatus = (String ) incomingRequest.get("RequisitionHeader_maxStatus");
				requisitionHeader.setMaxStatus(maxStatus);
			}
			if (incomingRequest.containsKey("RequisitionHeader_buyerRemarks"))
			{
				String buyerRemarks = (String ) incomingRequest.get("RequisitionHeader_buyerRemarks");
				requisitionHeader.setBuyerRemarks(buyerRemarks);
			}
			if (incomingRequest.containsKey("RequisitionHeader_ammendment"))
			{
				String ammendmentString = (String) incomingRequest.get("RequisitionHeader_ammendment");
				if (Utility.isEmpty(ammendmentString))
				{
					ammendmentString = "0";
				}
				BigDecimal ammendment = new BigDecimal ( ammendmentString );
				requisitionHeader.setAmmendment(ammendment);
			}
			if (incomingRequest.containsKey("RequisitionHeader_requisitionerCode"))
			{
				String requisitionerCode = (String ) incomingRequest.get("RequisitionHeader_requisitionerCode");
				requisitionHeader.setRequisitionerCode(requisitionerCode);
			}
			if (incomingRequest.containsKey("RequisitionHeader_departmentCode"))
			{
				String departmentCode = (String ) incomingRequest.get("RequisitionHeader_departmentCode");
				requisitionHeader.setDepartmentCode(departmentCode);
			}
			if (incomingRequest.containsKey("RequisitionHeader_authorizationCode"))
			{
				String authorizationCode = (String ) incomingRequest.get("RequisitionHeader_authorizationCode");
				requisitionHeader.setAuthorizationCode(authorizationCode);
			}
			if (incomingRequest.containsKey("RequisitionHeader_vendorId"))
			{
				String vendorId = (String ) incomingRequest.get("RequisitionHeader_vendorId");
				requisitionHeader.setVendorId(vendorId);

				String vendorName = VendorManager.getInstance().getVendorName(organizationId, vendorId);
				requisitionHeader.setVendorName(vendorName);
			}
			if (incomingRequest.containsKey("RequisitionHeader_shippingCode"))
			{
				String shippingCode = (String ) incomingRequest.get("RequisitionHeader_shippingCode");
				requisitionHeader.setShippingCode(shippingCode);
			}
			if (incomingRequest.containsKey("RequisitionHeader_buyer"))
			{
				String buyer = (String ) incomingRequest.get("RequisitionHeader_buyer");
				requisitionHeader.setBuyer(buyer);
			}
			if (incomingRequest.containsKey("RequisitionHeader_shipToCode"))
			{
				String shipToCode = (String ) incomingRequest.get("RequisitionHeader_shipToCode");
				requisitionHeader.setShipToCode(shipToCode);
			}
			if (incomingRequest.containsKey("RequisitionHeader_fobCode"))
			{
				String fobCode = (String ) incomingRequest.get("RequisitionHeader_fobCode");
				requisitionHeader.setFobCode(fobCode);
			}
			if (incomingRequest.containsKey("RequisitionHeader_routing"))
			{
				String routing = (String ) incomingRequest.get("RequisitionHeader_routing");
				requisitionHeader.setRouting(routing);
			}
			if (incomingRequest.containsKey("RequisitionHeader_priorityCode"))
			{
				String priorityCode = (String ) incomingRequest.get("RequisitionHeader_priorityCode");
				requisitionHeader.setPriorityCode(priorityCode);
			}
			if (incomingRequest.containsKey("RequisitionHeader_requiredDate"))
			{
				String requiredDateString = (String) incomingRequest.get("RequisitionHeader_requiredDate");
				Date requiredDate = Dates.getSqlDate(requiredDateString, userDateFormat);
				requisitionHeader.setRequiredDate(requiredDate);
			}
			if (incomingRequest.containsKey("RequisitionHeader_taxCode"))
			{
				String taxCode = (String ) incomingRequest.get("RequisitionHeader_taxCode");
				requisitionHeader.setTaxCode(taxCode);
			}
			if (incomingRequest.containsKey("RequisitionHeader_udf1Code"))
			{
				String udf1Code = (String ) incomingRequest.get("RequisitionHeader_udf1Code");
				requisitionHeader.setUdf1Code(udf1Code);
			}
			if (incomingRequest.containsKey("RequisitionHeader_udf2Code"))
			{
				String udf2Code = (String ) incomingRequest.get("RequisitionHeader_udf2Code");
				requisitionHeader.setUdf2Code(udf2Code);
			}
			if (incomingRequest.containsKey("RequisitionHeader_udf3Code"))
			{
				String udf3Code = (String ) incomingRequest.get("RequisitionHeader_udf3Code");
				requisitionHeader.setUdf3Code(udf3Code);
			}
			if (incomingRequest.containsKey("RequisitionHeader_udf4Code"))
			{
				String udf4Code = (String ) incomingRequest.get("RequisitionHeader_udf4Code");
				requisitionHeader.setUdf4Code(udf4Code);
			}
			if (incomingRequest.containsKey("RequisitionHeader_udf5Code"))
			{
				String udf5Code = (String ) incomingRequest.get("RequisitionHeader_udf5Code");
				requisitionHeader.setUdf5Code(udf5Code);
			}
			if (incomingRequest.containsKey("RequisitionHeader_udf6Code"))
			{
				String udf6Code = (String ) incomingRequest.get("RequisitionHeader_udf6Code");
				requisitionHeader.setUdf6Code(udf6Code);
			}
			if (incomingRequest.containsKey("RequisitionHeader_udf7Code"))
			{
				String udf7Code = (String ) incomingRequest.get("RequisitionHeader_udf7Code");
				requisitionHeader.setUdf7Code(udf7Code);
			}
			if (incomingRequest.containsKey("RequisitionHeader_udf8Code"))
			{
				String udf8Code = (String ) incomingRequest.get("RequisitionHeader_udf8Code");
				requisitionHeader.setUdf8Code(udf8Code);
			}
			if (incomingRequest.containsKey("RequisitionHeader_udf9Code"))
			{
				String udf9Code = (String ) incomingRequest.get("RequisitionHeader_udf9Code");
				requisitionHeader.setUdf9Code(udf9Code);
			}
			if (incomingRequest.containsKey("RequisitionHeader_udf10Code"))
			{
				String udf10Code = (String ) incomingRequest.get("RequisitionHeader_udf10Code");
				requisitionHeader.setUdf10Code(udf10Code);
			}
			if (incomingRequest.containsKey("RequisitionHeader_udf11Code"))
			{
				String udf11Code = (String ) incomingRequest.get("RequisitionHeader_udf11Code");
				requisitionHeader.setUdf11Code(udf11Code);
			}
			if (incomingRequest.containsKey("RequisitionHeader_udf12Code"))
			{
				String udf12Code = (String ) incomingRequest.get("RequisitionHeader_udf12Code");
				requisitionHeader.setUdf12Code(udf12Code);
			}
			if (incomingRequest.containsKey("RequisitionHeader_udf13Code"))
			{
				String udf13Code = (String ) incomingRequest.get("RequisitionHeader_udf13Code");
				requisitionHeader.setUdf13Code(udf13Code);
			}
			if (incomingRequest.containsKey("RequisitionHeader_udf14Code"))
			{
				String udf14Code = (String ) incomingRequest.get("RequisitionHeader_udf14Code");
				requisitionHeader.setUdf14Code(udf14Code);
			}
			if (incomingRequest.containsKey("RequisitionHeader_udf15Code"))
			{
				String udf15Code = (String ) incomingRequest.get("RequisitionHeader_udf15Code");
				requisitionHeader.setUdf15Code(udf15Code);
			}
			if (incomingRequest.containsKey("RequisitionHeader_accountCode"))
			{
				String accountCode = (String ) incomingRequest.get("RequisitionHeader_accountCode");
				requisitionHeader.setAccountCode(accountCode);
			}
			if (incomingRequest.containsKey("RequisitionHeader_vendContactCode"))
			{
				String vendContactCode = (String ) incomingRequest.get("RequisitionHeader_vendContactCode");
				requisitionHeader.setVendContactCode(vendContactCode);
			}
			if (incomingRequest.containsKey("RequisitionHeader_contactAddr"))
			{
				String contactAddr = (String ) incomingRequest.get("RequisitionHeader_contactAddr");
				requisitionHeader.setContactAddr(contactAddr);
			}
			if (incomingRequest.containsKey("RequisitionHeader_contactPhone"))
			{
				String contactPhone = (String ) incomingRequest.get("RequisitionHeader_contactPhone");
				requisitionHeader.setContactPhone(contactPhone);
			}
			if (incomingRequest.containsKey("RequisitionHeader_contactMobilePhone"))
			{
				String contactMobilePhone = (String) incomingRequest.get("RequisitionHeader_contactMobilePhone");
				requisitionHeader.setContactMobilePhone(contactMobilePhone);
			}
			if (incomingRequest.containsKey("RequisitionHeader_shipToContact"))
			{
				String shipToContact = (String ) incomingRequest.get("RequisitionHeader_shipToContact");
				requisitionHeader.setShipToContact(shipToContact);
			}
			if (incomingRequest.containsKey("RequisitionHeader_billToCode"))
			{
				String billToCode = (String ) incomingRequest.get("RequisitionHeader_billToCode");
				requisitionHeader.setBillToCode(billToCode);
			}
			if (incomingRequest.containsKey("RequisitionHeader_billToContact"))
			{
				String billToContact = (String ) incomingRequest.get("RequisitionHeader_billToContact");
				requisitionHeader.setBillToContact(billToContact);
			}
			if (incomingRequest.containsKey("RequisitionHeader_icHeaderHistory"))
			{
				String icHeaderHistoryString = (String) incomingRequest.get("RequisitionHeader_icHeaderHistory");
				if (Utility.isEmpty(icHeaderHistoryString))
				{
					icHeaderHistoryString = "0";
				}
				BigDecimal icHeaderHistory = new BigDecimal ( icHeaderHistoryString );
				requisitionHeader.setIcHeaderHistory(icHeaderHistory);
			}
			if (incomingRequest.containsKey("RequisitionHeader_itemLocation") )
			{
				String itemLocation = (String ) incomingRequest.get("RequisitionHeader_itemLocation");
				if (!HiltonUtility.isEmpty(itemLocation))
				{
					requisitionHeader.setItemLocation(itemLocation);
				}
			}
			if (incomingRequest.containsKey("RequisitionHeader_currencyCode"))
			{
				String currencyCode = (String ) incomingRequest.get("RequisitionHeader_currencyCode");
				requisitionHeader.setCurrencyCode(currencyCode);
			}
			if (incomingRequest.containsKey("RequisitionHeader_receiptRequired"))
			{
				String receiptRequired = (String ) incomingRequest.get("RequisitionHeader_receiptRequired");
				requisitionHeader.setReceiptRequired(receiptRequired);
			}
			if (incomingRequest.containsKey("RequisitionHeader_useTaxPercent"))
			{
				String taxPercentString = (String) incomingRequest.get("RequisitionHeader_useTaxPercent");
				if (Utility.isEmpty(taxPercentString))
				{
					taxPercentString = "0";
				}
				BigDecimal taxPercent = new BigDecimal ( taxPercentString );
				requisitionHeader.setUseTaxPercent(taxPercent);
			}
			if (incomingRequest.containsKey("RequisitionHeader_useTaxAmount"))
			{
				String taxAmountString = (String) incomingRequest.get("RequisitionHeader_useTaxAmount");
				if (Utility.isEmpty(taxAmountString))
				{
					taxAmountString = "0";
				}
				BigDecimal taxAmount = new BigDecimal ( taxAmountString );
				requisitionHeader.setUseTaxAmount(taxAmount);
			}
			if (incomingRequest.containsKey("RequisitionHeader_useTaxCode"))
			{
				String taxCode = (String ) incomingRequest.get("RequisitionHeader_useTaxCode");
				requisitionHeader.setUseTaxCode(taxCode);
			}
			if (incomingRequest.containsKey("RequisitionHeader_currencyFactor"))
			{
				String currencyFactorString = (String) incomingRequest.get("RequisitionHeader_currencyFactor");
				if (Utility.isEmpty(currencyFactorString))
				{
				    currencyFactorString = "1";
				}
				BigDecimal currencyFactor = new BigDecimal ( currencyFactorString );
				requisitionHeader.setCurrencyFactor(currencyFactor);
			}
			if (incomingRequest.containsKey("RequisitionHeader_servicesStartDate"))
			{
				String servicesStartDateString = (String) incomingRequest.get("RequisitionHeader_servicesStartDate");
				Date servicesStartDate = null;
				if (!organizationId.equals("BSC04P") || !HiltonUtility.isEmpty(servicesStartDateString))
				{
					servicesStartDate = Dates.getSqlDate(servicesStartDateString, userDateFormat);
				}
				requisitionHeader.setServicesStartDate(servicesStartDate);
			}
			if (incomingRequest.containsKey("RequisitionHeader_servicesEndDate"))
			{
				String servicesEndDateString = (String) incomingRequest.get("RequisitionHeader_servicesEndDate");
				Date servicesEndDate = null;
				if (!organizationId.equals("BSC04P") || !HiltonUtility.isEmpty(servicesEndDateString))
				{
					servicesEndDate = Dates.getSqlDate(servicesEndDateString, userDateFormat);
				}
				requisitionHeader.setServicesEndDate(servicesEndDate);
			}
			if (incomingRequest.containsKey("RequisitionHeader_budgetFlag"))
			{
				String budgetFlag = (String) incomingRequest.get("RequisitionHeader_budgetFlag");
				requisitionHeader.setBudgetFlag(budgetFlag);
			}
            if (incomingRequest.containsKey("RequisitionHeader_timeZone"))
            {
                String timeZone = (String) incomingRequest.get("RequisitionHeader_timeZone");
                requisitionHeader.setTimeZone(timeZone);
            }
            if (incomingRequest.containsKey("RequisitionHeader_gfpGfm"))
			{
				String gfpGfm = (String) incomingRequest.get("RequisitionHeader_gfpGfm");
				requisitionHeader.setGfpGfm(gfpGfm);
			}
            if (incomingRequest.containsKey("RequisitionHeader_kit"))
			{
				String kit = (String ) incomingRequest.get("RequisitionHeader_kit");
				requisitionHeader.setKit(kit);
			}
            if (incomingRequest.containsKey("RequisitionHeader_workOrder"))
			{
				String workOrder = (String ) incomingRequest.get("RequisitionHeader_workOrder");
				requisitionHeader.setWorkOrder(workOrder);
			}
            if (incomingRequest.containsKey("RequisitionHeader_requestCat"))
			{
				String cat = (String) incomingRequest.get("RequisitionHeader_requestCat");
				requisitionHeader.setRequestCat(cat);
			}
            if (incomingRequest.containsKey("RequisitionHeader_originalReqType"))
            {
            	String originalReqType = (String) incomingRequest.get("RequisitionHeader_originalReqType");
            	requisitionHeader.setOriginalReqType(originalReqType);
            }
            if (incomingRequest.containsKey("RequisitionHeader_icMsrHeader"))
			{
				String icMsrHeaderString = (String) incomingRequest.get("RequisitionHeader_icMsrHeader");
				if (Utility.isEmpty(icMsrHeaderString))
				{
					icMsrHeaderString = "0";
				}
				BigDecimal icMsrHeader = new BigDecimal ( icMsrHeaderString );
				requisitionHeader.setIcMsrHeader(icMsrHeader);
			}
            if (incomingRequest.containsKey("RequisitionHeader_msrNumber"))
			{
				String msrNumber = (String) incomingRequest.get("RequisitionHeader_msrNumber");
				requisitionHeader.setMsrNumber(msrNumber);
			}
            if (incomingRequest.containsKey("RequisitionHeader_corrosionEval"))
			{
				String corrosionEval = (String) incomingRequest.get("RequisitionHeader_corrosionEval");
				requisitionHeader.setCorrosionEval(corrosionEval);
			}
            if (incomingRequest.containsKey("RequisitionHeader_plannedDate"))
            {
            	String plannedDateString = (String)incomingRequest.get("RequisitionHeader_plannedDate");
            	Date plannedDate = Dates.getSqlDate(plannedDateString, userDateFormat);
            	requisitionHeader.setPlannedDate(plannedDate);
            }

			result = requisitionHeader;
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