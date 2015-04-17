package com.tsa.puridiom.rfqheader.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class RfqHeaderSetRequisitionValues extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;

		try {
			RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			String organizationId = (String) incomingRequest.get("organizationId");
			String userDateFormat = (String) incomingRequest.get("userDateFormat");

	        if (Utility.isEmpty(userDateFormat)) {
	            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
	        }
			if (reqHeader.getRequisitionType().equalsIgnoreCase("N")) {
				//Pricing RFQ
				incomingRequest.put("RfqHeader_rfqType", "PR") ;
			}
			incomingRequest.put("RfqHeader_authorizationCode", reqHeader.getAuthorizationCode()) ;
			incomingRequest.put("RfqHeader_billToCode", reqHeader.getBillToCode()) ;
			incomingRequest.put("RfqHeader_billToContact", reqHeader.getBillToContact()) ;
			incomingRequest.put("RfqHeader_departmentCode", reqHeader.getDepartmentCode()) ;
			incomingRequest.put("RfqHeader_fiscalYear", reqHeader.getFiscalYear());
			incomingRequest.put("RfqHeader_icHeaderHistory", String.valueOf(reqHeader.getIcHeaderHistory()));
			incomingRequest.put("RfqHeader_icReqHeader", String.valueOf(reqHeader.getIcReqHeader()));
			incomingRequest.put("RfqHeader_itemLocation", reqHeader.getItemLocation()) ;
			incomingRequest.put("RfqHeader_language", reqHeader.getLanguage());
			incomingRequest.put("RfqHeader_priorityCode", reqHeader.getPriorityCode()) ;
			incomingRequest.put("RfqHeader_requisitionerCode",reqHeader.getRequisitionerCode()) ;
			incomingRequest.put("RfqHeader_requisitionNumber", reqHeader.getRequisitionNumber());
			incomingRequest.put("RfqHeader_routing", reqHeader.getRouting()) ;
			incomingRequest.put("RfqHeader_shippingCode", reqHeader.getShippingCode()) ;
			incomingRequest.put("RfqHeader_shipToCode", reqHeader.getShipToCode()) ;
			incomingRequest.put("RfqHeader_shipToContact", reqHeader.getShipAttn()) ;
			incomingRequest.put("RfqHeader_taxCode", reqHeader.getTaxCode()) ;
			incomingRequest.put("RfqHeader_rfqCurrency", reqHeader.getCurrencyCode());
			incomingRequest.put("RfqHeader_receiptRequired", reqHeader.getReceiptRequired());
			if (reqHeader.getRequiredDate() != null) {
				incomingRequest.put("RfqHeader_requiredDate", HiltonUtility.getFormattedDate(reqHeader.getRequiredDate(), organizationId, userDateFormat));
			}
			incomingRequest.put("RfqHeader_rfqDescription", reqHeader.getInternalComments());
			incomingRequest.put("RfqHeader_estimatedCost", reqHeader.getEstimatedCost().toString());
			incomingRequest.put("RfqHeader_requestCat", reqHeader.getRequestCat());
			incomingRequest.put("RfqHeader_gfpGfm", reqHeader.getGfpGfm());
			incomingRequest.put("RfqHeader_kit", reqHeader.getKit());
			incomingRequest.put("RfqHeader_corrosionEval", reqHeader.getCorrosionEval());
			incomingRequest.put("RfqHeader_originalReqType", reqHeader.getOriginalReqType());

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}

}