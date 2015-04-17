package com.tsa.puridiom.rfqvendor.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class RfqVendorRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from RfqVendor as rfqvendor where 1=1 ");
		if(incomingRequest.containsKey("RfqVendor_icRfqHeader"))
		{			
			String icRfqHeader = (String) incomingRequest.get("RfqVendor_icRfqHeader");
			queryString.append(" AND rfqvendor.id.icRfqHeader = '" + icRfqHeader + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_vendorId"))
		{			
			String vendorId = (String) incomingRequest.get("RfqVendor_vendorId");
			queryString.append(" AND rfqvendor.id.vendorId = '" + vendorId + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_dateResponseRecv"))
		{			
			String dateResponseRecv = (String) incomingRequest.get("RfqVendor_dateResponseRecv");
			queryString.append(" AND rfqvendor.dateResponseRecv = '" + dateResponseRecv + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_bidResponseCode"))
		{			
			String bidResponseCode = (String) incomingRequest.get("RfqVendor_bidResponseCode");
			queryString.append(" AND rfqvendor.bidResponseCode = '" + bidResponseCode + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_contactId"))
		{			
			String contactId = (String) incomingRequest.get("RfqVendor_contactId");
			queryString.append(" AND rfqvendor.contactId = '" + contactId + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_discountSource"))
		{			
			String discountSource = (String) incomingRequest.get("RfqVendor_discountSource");
			queryString.append(" AND rfqvendor.discountSource = '" + discountSource + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_discountPercent"))
		{			
			String discountPercent = (String) incomingRequest.get("RfqVendor_discountPercent");
			queryString.append(" AND rfqvendor.discountPercent = '" + discountPercent + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_discountAmount"))
		{			
			String discountAmount = (String) incomingRequest.get("RfqVendor_discountAmount");
			queryString.append(" AND rfqvendor.discountAmount = '" + discountAmount + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_shippingCharges"))
		{			
			String shippingCharges = (String) incomingRequest.get("RfqVendor_shippingCharges");
			queryString.append(" AND rfqvendor.shippingCharges = '" + shippingCharges + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_otherCharges"))
		{			
			String otherCharges = (String) incomingRequest.get("RfqVendor_otherCharges");
			queryString.append(" AND rfqvendor.otherCharges = '" + otherCharges + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_otherDescription"))
		{			
			String otherDescription = (String) incomingRequest.get("RfqVendor_otherDescription");
			queryString.append(" AND rfqvendor.otherDescription = '" + otherDescription + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_taxShipping"))
		{			
			String taxShipping = (String) incomingRequest.get("RfqVendor_taxShipping");
			queryString.append(" AND rfqvendor.taxShipping = '" + taxShipping + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_taxOther"))
		{			
			String taxOther = (String) incomingRequest.get("RfqVendor_taxOther");
			queryString.append(" AND rfqvendor.taxOther = '" + taxOther + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_taxCode"))
		{			
			String taxCode = (String) incomingRequest.get("RfqVendor_taxCode");
			queryString.append(" AND rfqvendor.taxCode = '" + taxCode + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_datePromised"))
		{			
			String datePromised = (String) incomingRequest.get("RfqVendor_datePromised");
			queryString.append(" AND rfqvendor.datePromised = '" + datePromised + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_taxPercent"))
		{			
			String taxPercent = (String) incomingRequest.get("RfqVendor_taxPercent");
			queryString.append(" AND rfqvendor.taxPercent = '" + taxPercent + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_taxAmount"))
		{			
			String taxAmount = (String) incomingRequest.get("RfqVendor_taxAmount");
			queryString.append(" AND rfqvendor.taxAmount = '" + taxAmount + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_shippingTaxAmt"))
		{			
			String shippingTaxAmt = (String) incomingRequest.get("RfqVendor_shippingTaxAmt");
			queryString.append(" AND rfqvendor.shippingTaxAmt = '" + shippingTaxAmt + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_otherTaxAmount"))
		{			
			String otherTaxAmount = (String) incomingRequest.get("RfqVendor_otherTaxAmount");
			queryString.append(" AND rfqvendor.otherTaxAmount = '" + otherTaxAmount + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_vendCurrency"))
		{			
			String vendCurrency = (String) incomingRequest.get("RfqVendor_vendCurrency");
			queryString.append(" AND rfqvendor.vendCurrency = '" + vendCurrency + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_fob"))
		{			
			String fob = (String) incomingRequest.get("RfqVendor_fob");
			queryString.append(" AND rfqvendor.fob = '" + fob + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_paymentTerms"))
		{			
			String paymentTerms = (String) incomingRequest.get("RfqVendor_paymentTerms");
			queryString.append(" AND rfqvendor.paymentTerms = '" + paymentTerms + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_bidValidTo"))
		{			
			String bidValidTo = (String) incomingRequest.get("RfqVendor_bidValidTo");
			queryString.append(" AND rfqvendor.bidValidTo = '" + bidValidTo + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_addressCode"))
		{			
			String addressCode = (String) incomingRequest.get("RfqVendor_addressCode");
			queryString.append(" AND rfqvendor.addressCode = '" + addressCode + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_ediRfq"))
		{			
			String ediRfq = (String) incomingRequest.get("RfqVendor_ediRfq");
			queryString.append(" AND rfqvendor.ediRfq = '" + ediRfq + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_dateEdiXmit"))
		{			
			String dateEdiXmit = (String) incomingRequest.get("RfqVendor_dateEdiXmit");
			queryString.append(" AND rfqvendor.dateEdiXmit = '" + dateEdiXmit + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_ediDateResponse"))
		{			
			String ediDateResponse = (String) incomingRequest.get("RfqVendor_ediDateResponse");
			queryString.append(" AND rfqvendor.ediDateResponse = '" + ediDateResponse + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_ediStatus"))
		{			
			String ediStatus = (String) incomingRequest.get("RfqVendor_ediStatus");
			queryString.append(" AND rfqvendor.ediStatus = '" + ediStatus + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_discountTerms"))
		{			
			String discountTerms = (String) incomingRequest.get("RfqVendor_discountTerms");
			queryString.append(" AND rfqvendor.discountTerms = '" + discountTerms + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_notificationCode"))
		{			
			String notificationCode = (String) incomingRequest.get("RfqVendor_notificationCode");
			queryString.append(" AND rfqvendor.notificationCode = '" + notificationCode + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_contactName"))
		{			
			String contactName = (String) incomingRequest.get("RfqVendor_contactName");
			queryString.append(" AND rfqvendor.contactName = '" + contactName + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_bidsEntered"))
		{			
			String bidsEntered = (String) incomingRequest.get("RfqVendor_bidsEntered");
			queryString.append(" AND rfqvendor.bidsEntered = '" + bidsEntered + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_vendorClass"))
		{			
			String vendorClass = (String) incomingRequest.get("RfqVendor_vendorClass");
			queryString.append(" AND rfqvendor.vendorClass = '" + vendorClass + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_currencyFactor"))
		{			
			String currencyFactor = (String) incomingRequest.get("RfqVendor_currencyFactor");
			queryString.append(" AND rfqvendor.currencyFactor = '" + currencyFactor + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_evaluationStatus"))
		{			
			String evaluationStatus = (String) incomingRequest.get("RfqVendor_evaluationStatus");
			queryString.append(" AND rfqvendor.evaluationStatus = '" + evaluationStatus + "'");
		}
		if(incomingRequest.containsKey("RfqVendor_totalScore"))
		{			
			String totalScore = (String) incomingRequest.get("RfqVendor_totalScore");
			queryString.append(" AND rfqvendor.totalScore = '" + totalScore + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
