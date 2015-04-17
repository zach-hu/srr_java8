/**
 * Created on Jan 22, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoCreateFromRfqSetup.java
 */
package com.tsa.puridiom.po.tasks;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.entity.RfqVendor;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.vendor.VendorManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.*;
import com.tsa.puridiom.common.utility.HiltonUtility;
import java.util.Map;

public class PoCreateFromRfqSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String	organizationId = (String) incomingRequest.get("organizationId");
		String	userId = (String) incomingRequest.get("userId");
        String  userTimeZone = (String) incomingRequest.get("userTimeZone");
        String	vendorId = (String) incomingRequest.get("RfqVendor_vendorId");

		RfqHeader rfqHeader = (RfqHeader)incomingRequest.get("rfqHeader");
		RfqVendor rfqVendor = (RfqVendor)incomingRequest.get("rfqVendor");

		String userDateFormat = (String) incomingRequest.get("userDateFormat");
        if (HiltonUtility.isEmpty(userDateFormat)) {
            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
        }

		incomingRequest.put("RfqLine_icRfqHeader", rfqHeader.getIcRfqHeader().toString());
		incomingRequest.put("RfqVendor_icRfqHeader", rfqHeader.getIcRfqHeader().toString());
		incomingRequest.put("RfqBid_icRfqHeader", rfqHeader.getIcRfqHeader().toString());
		incomingRequest.put("RequisitionHeader_icReqHeader", rfqHeader.getIcReqHeader().toString());
		incomingRequest.put("Vendor_vendorId", vendorId);

		if(rfqHeader.getIcReqHeader() != null)
		{
			incomingRequest.put("PoHeader_icReqHeader", rfqHeader.getIcReqHeader().toString());
		}

		incomingRequest.put("PoHeader_authorizationCode", rfqHeader.getAuthorizationCode());
		incomingRequest.put("PoHeader_buyerCode", userId);
		incomingRequest.put("PoHeader_currencyCode", rfqHeader.getCurrencyCode());
		if (Utility.isEmpty(rfqHeader.getFiscalYear())) {
			incomingRequest.put("PoHeader_fiscalYear", HiltonUtility.getFiscalYear(organizationId, userTimeZone));
		}
		else {
			incomingRequest.put("PoHeader_fiscalYear", rfqHeader.getFiscalYear());
		}
		incomingRequest.put("PoHeader_icRfqHeader", rfqHeader.getIcRfqHeader().toString());
		incomingRequest.put("PoHeader_internalComments", rfqHeader.getRfqDescription());
		incomingRequest.put("PoHeader_itemLocation", rfqHeader.getItemLocation());
		incomingRequest.put("PoHeader_receiptRequired", rfqHeader.getReceiptRequired());
		incomingRequest.put("PoHeader_requisitionNumber", rfqHeader.getRequisitionNumber());
		incomingRequest.put("PoHeader_requisitionerCode", rfqHeader.getRequisitionerCode());
		incomingRequest.put("PoHeader_rfqNumber", rfqHeader.getRfqNumber());
		incomingRequest.put("PoHeader_routing", rfqHeader.getRouting()) ;
		incomingRequest.put("PoHeader_shipToCode", rfqHeader.getShipToCode());
		incomingRequest.put("PoHeader_shipToContact", rfqHeader.getShipToContact());
		incomingRequest.put("PoHeader_shipViaCode", rfqHeader.getShippingCode());
		incomingRequest.put("PoHeader_taxCode", rfqHeader.getTaxCode());
		incomingRequest.put("PoHeader_vendorId", vendorId);
		incomingRequest.put("PoHeader_priorityCode", rfqHeader.getPriorityCode());
		incomingRequest.put("PoHeader_vendorName", VendorManager.getInstance().getVendorName(organizationId, vendorId));
		incomingRequest.put("PoHeader_vendContactCode", "001");
		incomingRequest.put("PoHeader_kit", rfqHeader.getKit());
		incomingRequest.put("PoHeader_gfpGfm", rfqHeader.getGfpGfm());
		incomingRequest.put("PoHeader_estimatedCost", rfqHeader.getEstimatedCost().toString());
		incomingRequest.put("PoHeader_udf10Code", rfqHeader.getUdf10Code());
		incomingRequest.put("PoHeader_requestCat", rfqHeader.getRequestCat());
		incomingRequest.put("PoHeader_corrosionEval", rfqHeader.getCorrosionEval());
		incomingRequest.put("PoHeader_originalReqType", rfqHeader.getOriginalReqType());
		if ( rfqHeader.getRequiredDate()!= null)
		{
			incomingRequest.put("PoHeader_requiredDate", HiltonUtility.getFormattedDate(rfqHeader.getRequiredDate(), organizationId, userDateFormat));
		}
		if (rfqVendor != null) {
			incomingRequest.put("PoHeader_fobCode", rfqVendor.getFob());
			incomingRequest.put("PoHeader_termsCode", rfqVendor.getPaymentTerms());
			incomingRequest.put("PoHeader_shippingCharges", rfqVendor.getShippingCharges().toString());
			incomingRequest.put("PoHeader_otherCharges", rfqVendor.getOtherCharges().toString());
			incomingRequest.put("PoHeader_taxAmount", rfqVendor.getTaxAmount().toString());
			incomingRequest.put("PoHeader_taxCode", rfqVendor.getTaxCode());
			if (rfqVendor.getDatePromised() != null) {
				incomingRequest.put("PoHeader_promisedDate", HiltonUtility.getFormattedDate(rfqVendor.getDatePromised(), organizationId, userDateFormat));
			}
		}

		this.setStatus(Status.SUCCEEDED);

		return null;
	}
}