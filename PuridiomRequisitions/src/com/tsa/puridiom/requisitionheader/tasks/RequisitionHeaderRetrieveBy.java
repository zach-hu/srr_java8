package com.tsa.puridiom.requisitionheader.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;

import java.util.List;
import java.util.Map;

public class RequisitionHeaderRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from RequisitionHeader as requisitionheader where 1=1 ");
		if(incomingRequest.containsKey("RequisitionHeader_icReqHeader"))
		{
			String icReqHeader = (String) incomingRequest.get("RequisitionHeader_icReqHeader");
			queryString.append(" AND requisitionheader.id.icReqHeader = '" + icReqHeader + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_requisitionNumber"))
		{
			String requisitionNumber = (String) incomingRequest.get("RequisitionHeader_requisitionNumber");
			queryString.append(" AND requisitionheader.requisitionNumber = '" + requisitionNumber + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_requisitionType"))
		{
			String requisitionType = (String) incomingRequest.get("RequisitionHeader_requisitionType");
			queryString.append(" AND requisitionheader.requisitionType = '" + requisitionType + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_requisitionDate"))
		{
			String requisitionDate = (String) incomingRequest.get("RequisitionHeader_requisitionDate");
			queryString.append(" AND requisitionheader.requisitionDate = '" + requisitionDate + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_status"))
		{
			String status = (String) incomingRequest.get("RequisitionHeader_status");
			queryString.append(" AND requisitionheader.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_discountSource"))
		{
			String discountSource = (String) incomingRequest.get("RequisitionHeader_discountSource");
			queryString.append(" AND requisitionheader.discountSource = '" + discountSource + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_discountPercent"))
		{
			String discountPercent = (String) incomingRequest.get("RequisitionHeader_discountPercent");
			queryString.append(" AND requisitionheader.discountPercent = '" + discountPercent + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_discountAmount"))
		{
			String discountAmount = (String) incomingRequest.get("RequisitionHeader_discountAmount");
			queryString.append(" AND requisitionheader.discountAmount = '" + discountAmount + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_shippingCharges"))
		{
			String shippingCharges = (String) incomingRequest.get("RequisitionHeader_shippingCharges");
			queryString.append(" AND requisitionheader.shippingCharges = '" + shippingCharges + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_taxShipping"))
		{
			String taxShipping = (String) incomingRequest.get("RequisitionHeader_taxShipping");
			queryString.append(" AND requisitionheader.taxShipping = '" + taxShipping + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_otherCharges"))
		{
			String otherCharges = (String) incomingRequest.get("RequisitionHeader_otherCharges");
			queryString.append(" AND requisitionheader.otherCharges = '" + otherCharges + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_taxOther"))
		{
			String taxOther = (String) incomingRequest.get("RequisitionHeader_taxOther");
			queryString.append(" AND requisitionheader.taxOther = '" + taxOther + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_otherChargDesc"))
		{
			String otherChargDesc = (String) incomingRequest.get("RequisitionHeader_otherChargDesc");
			queryString.append(" AND requisitionheader.otherChargDesc = '" + otherChargDesc + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_internalComments"))
		{
			String internalComments = (String) incomingRequest.get("RequisitionHeader_internalComments");
			queryString.append(" AND requisitionheader.internalComments = '" + internalComments + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_fiscalYear"))
		{
			String fiscalYear = (String) incomingRequest.get("RequisitionHeader_fiscalYear");
			queryString.append(" AND requisitionheader.fiscalYear = '" + fiscalYear + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_owner"))
		{
			String owner = (String) incomingRequest.get("RequisitionHeader_owner");
			queryString.append(" AND requisitionheader.owner = '" + owner + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("RequisitionHeader_dateEntered");
			queryString.append(" AND requisitionheader.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_taxPercent"))
		{
			String taxPercent = (String) incomingRequest.get("RequisitionHeader_taxPercent");
			queryString.append(" AND requisitionheader.taxPercent = '" + taxPercent + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_taxAmount"))
		{
			String taxAmount = (String) incomingRequest.get("RequisitionHeader_taxAmount");
			queryString.append(" AND requisitionheader.taxAmount = '" + taxAmount + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_otherTaxAmount"))
		{
			String otherTaxAmount = (String) incomingRequest.get("RequisitionHeader_otherTaxAmount");
			queryString.append(" AND requisitionheader.otherTaxAmount = '" + otherTaxAmount + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_shippingTaxAmt"))
		{
			String shippingTaxAmt = (String) incomingRequest.get("RequisitionHeader_shippingTaxAmt");
			queryString.append(" AND requisitionheader.shippingTaxAmt = '" + shippingTaxAmt + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_language"))
		{
			String language = (String) incomingRequest.get("RequisitionHeader_language");
			queryString.append(" AND requisitionheader.language = '" + language + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_subtotal"))
		{
			String subtotal = (String) incomingRequest.get("RequisitionHeader_subtotal");
			queryString.append(" AND requisitionheader.subtotal = '" + subtotal + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_total"))
		{
			String total = (String) incomingRequest.get("RequisitionHeader_total");
			queryString.append(" AND requisitionheader.total = '" + total + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_appDate"))
		{
			String appDate = (String) incomingRequest.get("RequisitionHeader_appDate");
			queryString.append(" AND requisitionheader.appDate = '" + appDate + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_appSigned"))
		{
			String appSigned = (String) incomingRequest.get("RequisitionHeader_appSigned");
			queryString.append(" AND requisitionheader.appSigned = '" + appSigned + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_lastChgBy"))
		{
			String lastChgBy = (String) incomingRequest.get("RequisitionHeader_lastChgBy");
			queryString.append(" AND requisitionheader.lastChgBy = '" + lastChgBy + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_lastChgDate"))
		{
			String lastChgDate = (String) incomingRequest.get("RequisitionHeader_lastChgDate");
			queryString.append(" AND requisitionheader.lastChgDate = '" + lastChgDate + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_approved"))
		{
			String approved = (String) incomingRequest.get("RequisitionHeader_approved");
			queryString.append(" AND requisitionheader.approved = '" + approved + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_appBy"))
		{
			String appBy = (String) incomingRequest.get("RequisitionHeader_appBy");
			queryString.append(" AND requisitionheader.appBy = '" + appBy + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_assignedBuyer"))
		{
			String assignedBuyer = (String) incomingRequest.get("RequisitionHeader_assignedBuyer");
			queryString.append(" AND requisitionheader.assignedBuyer = '" + assignedBuyer + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_assignedDate"))
		{
			String assignedDate = (String) incomingRequest.get("RequisitionHeader_assignedDate");
			queryString.append(" AND requisitionheader.assignedDate = '" + assignedDate + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_estimatedCost"))
		{
			String estimatedCost = (String) incomingRequest.get("RequisitionHeader_estimatedCost");
			queryString.append(" AND requisitionheader.estimatedCost = '" + estimatedCost + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_bidWaiver"))
		{
			String bidWaiver = (String) incomingRequest.get("RequisitionHeader_bidWaiver");
			queryString.append(" AND requisitionheader.bidWaiver = '" + bidWaiver + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_shipAttn"))
		{
			String shipAttn = (String) incomingRequest.get("RequisitionHeader_shipAttn");
			queryString.append(" AND requisitionheader.shipAttn = '" + shipAttn + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_vendorAttn"))
		{
			String vendorAttn = (String) incomingRequest.get("RequisitionHeader_vendorAttn");
			queryString.append(" AND requisitionheader.vendorAttn = '" + vendorAttn + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_rqHeaderKey"))
		{
			String rqHeaderKey = (String) incomingRequest.get("RequisitionHeader_rqHeaderKey");
			queryString.append(" AND requisitionheader.rqHeaderKey = '" + rqHeaderKey + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_rqSubType"))
		{
			String rqSubType = (String) incomingRequest.get("RequisitionHeader_rqSubType");
			queryString.append(" AND requisitionheader.rqSubType = '" + rqSubType + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_pcardReq"))
		{
			String pcardReq = (String) incomingRequest.get("RequisitionHeader_pcardReq");
			queryString.append(" AND requisitionheader.pcardReq = '" + pcardReq + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_pcardName"))
		{
			String pcardName = (String) incomingRequest.get("RequisitionHeader_pcardName");
			queryString.append(" AND requisitionheader.pcardName = '" + pcardName + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_pcardHolder"))
		{
			String pcardHolder = (String) incomingRequest.get("RequisitionHeader_pcardHolder");
			queryString.append(" AND requisitionheader.pcardHolder = '" + pcardHolder + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_pcardNumber"))
		{
			String pcardNumber = (String) incomingRequest.get("RequisitionHeader_pcardNumber");
			queryString.append(" AND requisitionheader.pcardNumber = '" + pcardNumber + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_pcardExpires"))
		{
			String pcardExpires = (String) incomingRequest.get("RequisitionHeader_pcardExpires");
			queryString.append(" AND requisitionheader.pcardExpires = '" + pcardExpires + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_icRevisedOrder"))
		{
			String icRevisedOrder = (String) incomingRequest.get("RequisitionHeader_icRevisedOrder");
			queryString.append(" AND requisitionheader.icRevisedOrder = '" + icRevisedOrder + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_reqRecalc"))
		{
			String reqRecalc = (String) incomingRequest.get("RequisitionHeader_reqRecalc");
			queryString.append(" AND requisitionheader.reqRecalc = '" + reqRecalc + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_actionAlertFlag"))
		{
			String actionAlertFlag = (String) incomingRequest.get("RequisitionHeader_actionAlertFlag");
			queryString.append(" AND requisitionheader.actionAlertFlag = '" + actionAlertFlag + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_maxStatus"))
		{
			String maxStatus = (String) incomingRequest.get("RequisitionHeader_maxStatus");
			queryString.append(" AND requisitionheader.maxStatus = '" + maxStatus + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_buyerRemarks"))
		{
			String buyerRemarks = (String) incomingRequest.get("RequisitionHeader_buyerRemarks");
			queryString.append(" AND requisitionheader.buyerRemarks = '" + buyerRemarks + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_ammendment"))
		{
			String ammendment = (String) incomingRequest.get("RequisitionHeader_ammendment");
			queryString.append(" AND requisitionheader.ammendment = '" + ammendment + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_requisitionerCode"))
		{
			String requisitionerCode = (String) incomingRequest.get("RequisitionHeader_requisitionerCode");
			queryString.append(" AND requisitionheader.requisitionerCode = '" + requisitionerCode + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_departmentCode"))
		{
			String departmentCode = (String) incomingRequest.get("RequisitionHeader_departmentCode");
			queryString.append(" AND requisitionheader.departmentCode = '" + departmentCode + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_authorizationCode"))
		{
			String authorizationCode = (String) incomingRequest.get("RequisitionHeader_authorizationCode");
			queryString.append(" AND requisitionheader.authorizationCode = '" + authorizationCode + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_vendorId"))
		{
			String vendorId = (String) incomingRequest.get("RequisitionHeader_vendorId");
			queryString.append(" AND requisitionheader.vendorId = '" + vendorId + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_shippingCode"))
		{
			String shippingCode = (String) incomingRequest.get("RequisitionHeader_shippingCode");
			queryString.append(" AND requisitionheader.shippingCode = '" + shippingCode + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_buyer"))
		{
			String buyer = (String) incomingRequest.get("RequisitionHeader_buyer");
			queryString.append(" AND requisitionheader.buyer = '" + buyer + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_shipToCode"))
		{
			String shipToCode = (String) incomingRequest.get("RequisitionHeader_shipToCode");
			queryString.append(" AND requisitionheader.shipToCode = '" + shipToCode + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_routing"))
		{
			String routing = (String) incomingRequest.get("RequisitionHeader_routing");
			queryString.append(" AND requisitionheader.routing = '" + routing + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_priorityCode"))
		{
			String priorityCode = (String) incomingRequest.get("RequisitionHeader_priorityCode");
			queryString.append(" AND requisitionheader.priorityCode = '" + priorityCode + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_requiredDate"))
		{
			String requiredDate = (String) incomingRequest.get("RequisitionHeader_requiredDate");
			queryString.append(" AND requisitionheader.requiredDate = '" + requiredDate + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_taxCode"))
		{
			String taxCode = (String) incomingRequest.get("RequisitionHeader_taxCode");
			queryString.append(" AND requisitionheader.taxCode = '" + taxCode + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_udf1Code"))
		{
			String udf1Code = (String) incomingRequest.get("RequisitionHeader_udf1Code");
			queryString.append(" AND requisitionheader.udf1Code = '" + udf1Code + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_udf2Code"))
		{
			String udf2Code = (String) incomingRequest.get("RequisitionHeader_udf2Code");
			queryString.append(" AND requisitionheader.udf2Code = '" + udf2Code + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_udf3Code"))
		{
			String udf3Code = (String) incomingRequest.get("RequisitionHeader_udf3Code");
			queryString.append(" AND requisitionheader.udf3Code = '" + udf3Code + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_udf4Code"))
		{
			String udf4Code = (String) incomingRequest.get("RequisitionHeader_udf4Code");
			queryString.append(" AND requisitionheader.udf4Code = '" + udf4Code + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_udf5Code"))
		{
			String udf5Code = (String) incomingRequest.get("RequisitionHeader_udf5Code");
			queryString.append(" AND requisitionheader.udf5Code = '" + udf5Code + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_udf6Code"))
		{
			String udf6Code = (String) incomingRequest.get("RequisitionHeader_udf6Code");
			queryString.append(" AND requisitionheader.udf6Code = '" + udf6Code + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_udf7Code"))
		{
			String udf7Code = (String) incomingRequest.get("RequisitionHeader_udf7Code");
			queryString.append(" AND requisitionheader.udf7Code = '" + udf7Code + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_udf8Code"))
		{
			String udf8Code = (String) incomingRequest.get("RequisitionHeader_udf8Code");
			queryString.append(" AND requisitionheader.udf8Code = '" + udf8Code + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_udf9Code"))
		{
			String udf9Code = (String) incomingRequest.get("RequisitionHeader_udf9Code");
			queryString.append(" AND requisitionheader.udf9Code = '" + udf9Code + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_udf10Code"))
		{
			String udf10Code = (String) incomingRequest.get("RequisitionHeader_udf10Code");
			queryString.append(" AND requisitionheader.udf10Code = '" + udf10Code + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_udf11Code"))
		{
			String udf11Code = (String) incomingRequest.get("RequisitionHeader_udf11Code");
			queryString.append(" AND requisitionheader.udf11Code = '" + udf11Code + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_udf12Code"))
		{
			String udf12Code = (String) incomingRequest.get("RequisitionHeader_udf12Code");
			queryString.append(" AND requisitionheader.udf12Code = '" + udf12Code + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_udf13Code"))
		{
			String udf13Code = (String) incomingRequest.get("RequisitionHeader_udf13Code");
			queryString.append(" AND requisitionheader.udf13Code = '" + udf13Code + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_udf14Code"))
		{
			String udf14Code = (String) incomingRequest.get("RequisitionHeader_udf14Code");
			queryString.append(" AND requisitionheader.udf14Code = '" + udf14Code + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_udf5Code"))
		{
			String udf15Code = (String) incomingRequest.get("RequisitionHeader_udf15Code");
			queryString.append(" AND requisitionheader.udf15Code = '" + udf15Code + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_accountCode"))
		{
			String accountCode = (String) incomingRequest.get("RequisitionHeader_accountCode");
			queryString.append(" AND requisitionheader.accountCode = '" + accountCode + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_vendContactCode"))
		{
			String vendContactCode = (String) incomingRequest.get("RequisitionHeader_vendContactCode");
			queryString.append(" AND requisitionheader.vendContactCode = '" + vendContactCode + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_contactAddr"))
		{
			String contactAddr = (String) incomingRequest.get("RequisitionHeader_contactAddr");
			queryString.append(" AND requisitionheader.contactAddr = '" + contactAddr + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_shipToContact"))
		{
			String shipToContact = (String) incomingRequest.get("RequisitionHeader_shipToContact");
			queryString.append(" AND requisitionheader.shipToContact = '" + shipToContact + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_billToCode"))
		{
			String billToCode = (String) incomingRequest.get("RequisitionHeader_billToCode");
			queryString.append(" AND requisitionheader.billToCode = '" + billToCode + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_billToContact"))
		{
			String billToContact = (String) incomingRequest.get("RequisitionHeader_billToContact");
			queryString.append(" AND requisitionheader.billToContact = '" + billToContact + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_icHeaderHistory"))
		{
			String icHeaderHistory = (String) incomingRequest.get("RequisitionHeader_icHeaderHistory");
			queryString.append(" AND requisitionheader.icHeaderHistory = '" + icHeaderHistory + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_itemLocation"))
		{
			String itemLocation = (String) incomingRequest.get("RequisitionHeader_itemLocation");
			queryString.append(" AND requisitionheader.itemLocation = '" + itemLocation + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_vendorName"))
		{
			String vendorName = (String) incomingRequest.get("RequisitionHeader_vendorName");
			queryString.append(" AND requisitionheader.vendorName = '" + vendorName + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_currencyCode"))
		{
			String currencyCode = (String) incomingRequest.get("RequisitionHeader_currencyCode");
			queryString.append(" AND requisitionheader.currencyCode = '" + currencyCode + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_receiptRequired"))
		{
			String receiptRequired = (String) incomingRequest.get("RequisitionHeader_receiptRequired");
			queryString.append(" AND requisitionheader.receiptRequired = '" + receiptRequired + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_contactPhone"))
		{
			String contactPhone = (String) incomingRequest.get("RequisitionHeader_contactPhone");
			queryString.append(" AND requisitionheader.contactPhone = '" + contactPhone + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_contactMobilePhone"))
		{
			String contactMobilePhone = (String) incomingRequest.get("RequisitionHeader_contactMobilePhone");
			queryString.append(" AND requisitionheader.contactMobilePhone = '" + contactMobilePhone + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_useTaxCode"))
		{
			String useTaxCode = (String) incomingRequest.get("RequisitionHeader_useTaxCode");
			queryString.append(" AND requisitionheader.useTaxCode = '" + useTaxCode + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_useTaxPercent"))
		{
			String useTaxPercent = (String) incomingRequest.get("RequisitionHeader_useTaxPercent");
			queryString.append(" AND requisitionheader.useTaxPercent = '" + useTaxPercent + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_useTaxAmount"))
		{
			String useTaxAmount = (String) incomingRequest.get("RequisitionHeader_useTaxAmount");
			queryString.append(" AND requisitionheader.useTaxAmount = '" + useTaxAmount + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_currencyFactor"))
		{
			String currencyFactor = (String) incomingRequest.get("RequisitionHeader_currencyFactor");
			queryString.append(" AND requisitionheader.currencyFactor = '" + currencyFactor + "'");
		}
		if(incomingRequest.containsKey("RequisitionHeader_originalReqType"))
		{
			String originalReqType = (String) incomingRequest.get("RequisitionHeader_originalReqType");
			queryString.append(" AND requisitionheader.originalReqType = '" + originalReqType + '"');
		}
		if (incomingRequest.containsKey("RequisitionHeader_plannedDate"))
		{
			String plannedDate = (String)incomingRequest.get("RequisitionHeader_plannedDate");
			queryString.append(" AND requisitionheader.plannedDate = '" + plannedDate + '"');
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
