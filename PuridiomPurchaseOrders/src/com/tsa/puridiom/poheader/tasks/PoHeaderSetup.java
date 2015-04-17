package com.tsa.puridiom.poheader.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.property.PropertiesManager;

public class PoHeaderSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		String	userId = (String) incomingRequest.get("userId") ;
		String oid = (String)incomingRequest.get("organizationId") ;
		String organizationId = (String) incomingRequest.get("organizationId");
        String userTimeZone = (String) incomingRequest.get("userTimeZone");
        String userDateFormat = (String) incomingRequest.get("userDateFormat");

        if (Utility.isEmpty(userDateFormat)) {
            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
        }
        String today = Dates.today(userDateFormat, userTimeZone);

		try
		{
			incomingRequest.put("PoHeader_icPoHeader", "0");
			//incomingRequest.put("PoHeader_poType", "");
			incomingRequest.put("PoHeader_poNumber", "");
			incomingRequest.put("PoHeader_releaseNumber", "0");
			incomingRequest.put("PoHeader_revisionNumber", "0");
			incomingRequest.put("PoHeader_contractNo", "");
			incomingRequest.put("PoHeader_poDate", today);
			incomingRequest.put("PoHeader_requiredDate", today);
			incomingRequest.put("PoHeader_promisedDate", "");
			incomingRequest.put("PoHeader_revisionDate", today);
			incomingRequest.put("PoHeader_vendorId", "");
			incomingRequest.put("PoHeader_vendContactCode", "");
			incomingRequest.put("PoHeader_buyerCode", userId);
			incomingRequest.put("PoHeader_termsCode", "");
			incomingRequest.put("PoHeader_fobCode", "");
			incomingRequest.put("PoHeader_shipViaCode", "");
			incomingRequest.put("PoHeader_currencyCode", "");
			incomingRequest.put("PoHeader_shipToCode", "");
			incomingRequest.put("PoHeader_shipToContact", "");
			incomingRequest.put("PoHeader_billToCode", "");
			incomingRequest.put("PoHeader_billToContact", "");
			incomingRequest.put("PoHeader_effectiveDate", "");
			incomingRequest.put("PoHeader_expirationDate", "");
			incomingRequest.put("PoHeader_blanketLimit", "0");
			incomingRequest.put("PoHeader_releaseLimit", "0");
			incomingRequest.put("PoHeader_confirming", "N");
			incomingRequest.put("PoHeader_confirmDate", today);
			incomingRequest.put("PoHeader_confirmNameCode", "");
			incomingRequest.put("PoHeader_poCopies", "0");
			incomingRequest.put("PoHeader_ediOrder", "");
//			incomingRequest.put("PoHeader_datePrinted", "01/01/01");
//			incomingRequest.put("PoHeader_dateFaxed", "01/01/01");
			incomingRequest.put("PoHeader_dateEdiXmit", today);
			incomingRequest.put("PoHeader_taxCode", "");
			incomingRequest.put("PoHeader_taxPercent", "0");
			incomingRequest.put("PoHeader_taxAmount", "0");
			incomingRequest.put("PoHeader_discountCode", "");
			incomingRequest.put("PoHeader_discountPercent", "0");
			incomingRequest.put("PoHeader_discountAmount", "0");
			incomingRequest.put("PoHeader_shippingCharges", "0");
			incomingRequest.put("PoHeader_shippingTaxable", "");
			incomingRequest.put("PoHeader_shippingTax", "0");
			incomingRequest.put("PoHeader_otherCharges", "0");
			incomingRequest.put("PoHeader_otherDescription", "");
			incomingRequest.put("PoHeader_otherTaxable", "");
			incomingRequest.put("PoHeader_otherTax", "0");
			incomingRequest.put("PoHeader_estimatedCost", "0");
			incomingRequest.put("PoHeader_prePaid", "");
			incomingRequest.put("PoHeader_udf1Code", "");
			incomingRequest.put("PoHeader_udf2Code", "");
			incomingRequest.put("PoHeader_udf3Code", "");
			incomingRequest.put("PoHeader_udf4Code", "");
			incomingRequest.put("PoHeader_udf5Code", "");
			incomingRequest.put("PoHeader_udf6Code", "");
			incomingRequest.put("PoHeader_udf7Code", "");
			incomingRequest.put("PoHeader_udf8Code", "");
			incomingRequest.put("PoHeader_udf9Code", "");
			incomingRequest.put("PoHeader_udf10Code", "");
			incomingRequest.put("PoHeader_udf11Code", "");
			incomingRequest.put("PoHeader_udf12Code", "");
			incomingRequest.put("PoHeader_udf13Code", "");
			incomingRequest.put("PoHeader_udf14Code", "");
			incomingRequest.put("PoHeader_udf15Code", "");
			incomingRequest.put("PoHeader_status", "");
			incomingRequest.put("PoHeader_internalComments", "");
			incomingRequest.put("PoHeader_owner", userId);
			incomingRequest.put("PoHeader_dateEntered", today);
			incomingRequest.put("PoHeader_lastRelease", "");
			incomingRequest.put("PoHeader_lastRevision", "C");
			incomingRequest.put("PoHeader_requisitionNumber", "");
			incomingRequest.put("PoHeader_fiscalYear", "");
			incomingRequest.put("PoHeader_language", "");
			incomingRequest.put("PoHeader_currencyFactor", "1");
			incomingRequest.put("PoHeader_total", "0");
			incomingRequest.put("PoHeader_subtotal", "0");
			incomingRequest.put("PoHeader_icReqHeader", "0");
			incomingRequest.put("PoHeader_icRfqHeader", "0");
			incomingRequest.put("PoHeader_approved", "");
			incomingRequest.put("PoHeader_appBy", "");
			incomingRequest.put("PoHeader_appDate", today);
			incomingRequest.put("PoHeader_appSigned", "");
			incomingRequest.put("PoHeader_lastChgBy", userId);
			incomingRequest.put("PoHeader_lastChgDate", today);
			incomingRequest.put("PoHeader_rfqNumber", "");
			incomingRequest.put("PoHeader_ediStatus", "");
			incomingRequest.put("PoHeader_receiptRequired", "");
			incomingRequest.put("PoHeader_contactAddr", "");
			incomingRequest.put("PoHeader_pyStatus", DocumentStatus.PY_NOTINVOICED);
			incomingRequest.put("PoHeader_savings", "0");
			incomingRequest.put("PoHeader_savingsReason", "");
			incomingRequest.put("PoHeader_linkNextOrder", "");
			incomingRequest.put("PoHeader_linkPriorOrder", "");
			incomingRequest.put("PoHeader_apBatchid", "");
			incomingRequest.put("PoHeader_icNextOrderLink", "0");
			incomingRequest.put("PoHeader_icPriorOrderLink", "0");
			incomingRequest.put("PoHeader_pcardOrder", "");
			incomingRequest.put("PoHeader_lockStatus", "U");
			incomingRequest.put("PoHeader_vendorName", "");
			incomingRequest.put("PoHeader_poRecalc", "");
			incomingRequest.put("PoHeader_actionAlertFlag", "");
			incomingRequest.put("PoHeader_obligAmt", "0");
			incomingRequest.put("PoHeader_obligDate", today);
			incomingRequest.put("PoHeader_vendorEval", "");
			incomingRequest.put("PoHeader_requisitionerCode", "");
			incomingRequest.put("PoHeader_departmentCode", "");
			incomingRequest.put("PoHeader_priorityCode", "");
			incomingRequest.put("PoHeader_icHeaderHistory", "0");
			incomingRequest.put("PoHeader_routing", "");
			incomingRequest.put("PoHeader_authorizationCode", "");
			incomingRequest.put("PoHeader_itemLocation", "");
			incomingRequest.put("PoHeader_vendorClass", "");
			incomingRequest.put("PoHeader_inspectionReqd", "");
			incomingRequest.put("PoHeader_contactPhone", "");
			incomingRequest.put("PoHeader_icHeaderKey", "0");
            incomingRequest.put("PoHeader", userTimeZone);
            incomingRequest.put("PoHeader_bidWaiver", "");
            incomingRequest.put("PoHeader_iclLevel", "0");
            incomingRequest.put("PoHeader_gfpGfm", "");
            incomingRequest.put("PoHeader_kit", "");
            incomingRequest.put("PoHeader_workOrder", "");
            incomingRequest.put("PoHeader_requestCat", "");
            incomingRequest.put("PoHeader_originalReqType", "");
            incomingRequest.put("PoHeader_icMsrHeader", "0");
            incomingRequest.put("PoHeader_msrNumber", "");

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
