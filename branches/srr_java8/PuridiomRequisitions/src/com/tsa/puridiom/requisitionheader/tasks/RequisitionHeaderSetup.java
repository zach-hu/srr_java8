package com.tsa.puridiom.requisitionheader.tasks;

import java.util.Map;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class RequisitionHeaderSetup extends Task	{
		public Object  executeTask (Object object) throws Exception

	{
		Map incomingRequest = (Map)object;
		String	userId = (String) incomingRequest.get("userId") ;
        String userTimeZone = (String) incomingRequest.get("userTimeZone");
        String  today = Dates.today("yyyy/MM/dd", userTimeZone);

			// Create new ic codes
		UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		incomingRequest.put("RequisitionHeader_icReqHeader",ukg.getUniqueKey().toString());
		incomingRequest.put("RequisitionHeader_icHeaderHistory",ukg.getUniqueKey().toString());

		String requisitionType = (String) incomingRequest.get("RequisitionHeader_requisitionType") ;
		if (requisitionType == null) {
			requisitionType = "P" ;
		}

		incomingRequest.put("RequisitionHeader_requisitionType",requisitionType) ;
		incomingRequest.put("RequisitionHeader_owner",userId) ;
		incomingRequest.put("RequisitionHeader_requisitionDate",today) ;
		incomingRequest.put("RequisitionHeader_ammendment", "0");
		incomingRequest.put("RequisitionHeader_status","1000") ;
		incomingRequest.put("RequisitionHeader_discountPercent", "0");
		incomingRequest.put("RequisitionHeader_discountAmount", "0");
		incomingRequest.put("RequisitionHeader_shippingCharges", "0");
		incomingRequest.put("RequisitionHeader_taxShipping", "N");
		incomingRequest.put("RequisitionHeader_otherCharges", "0");
		incomingRequest.put("RequisitionHeader_taxOther", "N");
		incomingRequest.put("RequisitionHeader_dateEntered",today) ;
		incomingRequest.put("RequisitionHeader_taxPercent", "0");
		incomingRequest.put("RequisitionHeader_taxAmount", "0");
		incomingRequest.put("RequisitionHeader_otherTaxAmount", "0");
		incomingRequest.put("RequisitionHeader_shippingTaxAmt", "0");
		incomingRequest.put("RequisitionHeader_subtotal", "0");
		incomingRequest.put("RequisitionHeader_total", "0");
		incomingRequest.put("RequisitionHeader_estimatedCost", "0");
		incomingRequest.put("RequisitionHeader_discountSource","S") ;
		incomingRequest.put("RequisitionHeader_language","(Default)") ;
		incomingRequest.put("RequisitionHeader_appSigned","N") ;
		incomingRequest.put("RequisitionHeader_approved","N") ;
		incomingRequest.put("RequisitionHeader_lastChgBy",userId) ;
		incomingRequest.put("RequisitionHeader_lastChgDate",today) ;
		incomingRequest.put("RequisitionHeader_assignedBuyer","PURCHASING") ;
		incomingRequest.put("RequisitionHeader_assignedDate",today) ;
		incomingRequest.put("RequisitionHeader_pcardReq","N") ;
		incomingRequest.put("RequisitionHeader_reqRecalc","N");
		incomingRequest.put("RequisitionHeader_requisitionerCode","") ;
		incomingRequest.put("RequisitionHeader_departmentCode","") ;
		incomingRequest.put("RequisitionHeader_currencyFactor","1") ;
		incomingRequest.put("RequisitionHeader_fobCode","") ;
		incomingRequest.put("RequisitionHeader_gfpGfm", "N") ;
		incomingRequest.put("RequisitionHeader_kit", "N") ;
		incomingRequest.put("RequisitionHeader_originalReqType", "");
		incomingRequest.put("RequisitionHeader_icMsrHeader", "0");
		incomingRequest.put("RequisitionHeader_msrNumber", "");


		return null ;
	}
}
