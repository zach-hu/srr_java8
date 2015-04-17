/*
 * Created on Aug 25, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.po.tasks.PoReleaseCreateSetup.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 */
public class PoReleaseCreateSetup extends Task
{

    public Object executeTask(Object object) throws Exception
    {
		try
		{
		    Map incomingRequest = (Map)object;
		    String organizationId = (String)incomingRequest.get("organizationId");
            PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;
		    BigDecimal blanketIcDecimal = (BigDecimal)incomingRequest.get("blanketIc");
		    String blanketIc = blanketIcDecimal.toString();
		    String	userId = (String) incomingRequest.get("userId") ;
            String userTimeZone  = (String) incomingRequest.get("userTimeZone") ;
            String today = Dates.today("", userTimeZone);

		    if(Utility.isEmpty(blanketIc))
		    {
		        this.setStatus(Status.FAILED);
		        throw new TsaException("Blanket Ic can not be Empty");
		    }
		    incomingRequest.put("old_PoHeader_icPoHeader", blanketIc);
		    PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
		    if(poHeader == null)
		    {
		        this.setStatus(Status.FAILED);
		        throw new TsaException("Blanket Order was not Found.");
		    }

		    Dates dates = new Dates();
			int offsetDays = dates.getOffsetDays(10, false);
			String promised = Dates.add(today, offsetDays);

			incomingRequest.put("PoHeader_promisedDate", promised);

			String poType = (String)incomingRequest.get("releaseType");
			if(poType == null)
			{
				incomingRequest.put("PoHeader_poType", "PO");
			}
			else
			{
			    incomingRequest.put("PoHeader_poType", poType);
			}

			incomingRequest.put("PoHeader_icPoHeader", poHeader.getIcPoHeader().toString());
			incomingRequest.put("PoHeader_poNumber", incomingRequest.get("release_poNumber"));

			incomingRequest.put("RequisitionHeader_icReqHeader", blanketIc); //faking load of schedules			incomingRequest.put("PoHeader_blanketLimit", "0");
			incomingRequest.put("PoHeader_confirming", "N");
			incomingRequest.put("PoHeader_taxCode", poHeader.getTaxCode());
			incomingRequest.put("PoHeader_taxPercent", poHeader.getTaxPercent().toString());
			incomingRequest.put("PoHeader_discountCode", "");

			if (propertiesManager.getProperty("PO OPTIONS", "RESETTOTALAMOUNTSFORRELEASE", "Y").equalsIgnoreCase("Y") ||
					!(poType.equalsIgnoreCase("SR") || poType.equalsIgnoreCase("DR") || poType.equalsIgnoreCase("RO")))
			{
				incomingRequest.put("PoHeader_taxAmount", "0");
				incomingRequest.put("PoHeader_discountPercent", "0");
				incomingRequest.put("PoHeader_discountAmount", "0");
				incomingRequest.put("PoHeader_shippingCharges", "0");
				incomingRequest.put("PoHeader_shippingTaxable", "");
				incomingRequest.put("PoHeader_shippingTax", "0");
				incomingRequest.put("PoHeader_otherCharges", "0");
				incomingRequest.put("PoHeader_otherDescription", "");
				incomingRequest.put("PoHeader_otherTaxable", "");
				incomingRequest.put("PoHeader_otherTax", "0");
				incomingRequest.put("PoHeader_total", "0");
				incomingRequest.put("PoHeader_subtotal", "0");
			}

			incomingRequest.put("PoHeader_owner", userId);
			incomingRequest.put("PoHeader_dateEntered", today);
			incomingRequest.put("PoHeader_lastRelease", "");
			incomingRequest.put("PoHeader_lastRevision", "C");
			incomingRequest.put("PoHeader_requisitionNumber", "");
			incomingRequest.put("PoHeader_icReqHeader", "0");
			incomingRequest.put("PoHeader_icRfqHeader", "0");
			incomingRequest.put("PoHeader_approved", "N");
			incomingRequest.put("PoHeader_appBy", "");
			incomingRequest.put("PoHeader_appDate", today);
			incomingRequest.put("PoHeader_appSigned", "");
			incomingRequest.put("PoHeader_lastChgBy", userId);
			incomingRequest.put("PoHeader_lastChgDate", today);
			incomingRequest.put("PoHeader_rfqNumber", "");
			incomingRequest.put("PoHeader_ediStatus", "");
			incomingRequest.put("PoHeader_linkNextOrder", "");
			incomingRequest.put("PoHeader_linkPriorOrder", "");
			incomingRequest.put("PoHeader_apBatchid", "");
			incomingRequest.put("PoHeader_icNextOrderLink", "0");
			incomingRequest.put("PoHeader_icPriorOrderLink", "0");
			incomingRequest.put("PoHeader_lockStatus", "U");
			incomingRequest.put("PoHeader_vendorName", "");
			incomingRequest.put("PoHeader_poRecalc", "");
			incomingRequest.put("PoHeader_actionAlertFlag", "");
			incomingRequest.put("PoHeader_obligAmt", "0");
			incomingRequest.put("PoHeader_obligDate", null);
            incomingRequest.put("PoHeader_timeZone", userTimeZone);
            incomingRequest.put("PoHeader_internalComments", poHeader.getInternalComments());
			incomingRequest.put("PoHeader_requisitionerCode", poHeader.getRequisitionerCode());
			incomingRequest.put("PoHeader_icHeaderHistory", incomingRequest.get("historyIc"));
			incomingRequest.put("PoHeader_status", DocumentStatus.PO_INPROGRESS);
			incomingRequest.put("PoHeader_buyerCode", poHeader.getBuyerCode());
			incomingRequest.put("PoHeader_estimatedCost", poHeader.getEstimatedCost().toString());
			incomingRequest.put("PoHeader_corrosionEval", poHeader.getCorrosionEval());

		    this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
		    this.setStatus(Status.FAILED);
	        throw new TsaException(this.getName(), e);
		}
        return super.executeTask(object);
    }
}
