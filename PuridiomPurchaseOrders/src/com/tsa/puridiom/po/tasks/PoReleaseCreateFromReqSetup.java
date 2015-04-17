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
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PoReleaseCreateFromReqSetup extends Task
{

    public Object executeTask(Object object) throws Exception
    {
		try
		{
		    Map incomingRequest = (Map)object;
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
		    PoHeader poHeader = (PoHeader)incomingRequest.get("blanket");
		    if(poHeader == null)
		    {
		        this.setStatus(Status.FAILED);
		        throw new TsaException("Blanket Order was not Found.");
		    }

		    RequisitionHeader rqh = (RequisitionHeader)incomingRequest.get("requisitionHeader");
		    if(rqh == null)
		    {
		        this.setStatus(Status.FAILED);
		        throw new TsaException("Requisition was not Found!.");
		    }

		    Dates dates = new Dates();
			int offsetDays = dates.getOffsetDays(10, false);
			String promised = Dates.add(Dates.today("", userTimeZone), offsetDays);

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

			RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");

			incomingRequest.put("RequisitionHeader_icReqHeader", requisitionHeader.getIcReqHeader().toString());
			incomingRequest.put("PoHeader_requisitionNumber", requisitionHeader.getRequisitionNumber());
			incomingRequest.put("PoHeader_fiscalYear", requisitionHeader.getFiscalYear());
			incomingRequest.put("PoHeader_total", requisitionHeader.getTotal().toString());
			incomingRequest.put("PoHeader_subtotal", requisitionHeader.getSubtotal().toString());
			incomingRequest.put("PoHeader_otherCharges", requisitionHeader.getOtherCharges().toString());
			incomingRequest.put("PoHeader_otherDescription", requisitionHeader.getOtherChargDesc());
			incomingRequest.put("PoHeader_otherTaxable", requisitionHeader.getTaxOther());
			incomingRequest.put("PoHeader_otherTax", requisitionHeader.getOtherTaxAmount().toString());
			incomingRequest.put("PoHeader_discountAmount", requisitionHeader.getDiscountAmount().toString());
			incomingRequest.put("PoHeader_discountPercent", requisitionHeader.getDiscountPercent().toString());
			incomingRequest.put("PoHeader_taxCode", requisitionHeader.getTaxCode());
			incomingRequest.put("PoHeader_taxPercent", requisitionHeader.getTaxPercent().toString());
			incomingRequest.put("PoHeader_taxAmount", requisitionHeader.getTaxAmount().toString());
			incomingRequest.put("PoHeader_shippingCharges", requisitionHeader.getShippingCharges().toString());
			incomingRequest.put("PoHeader_shippingTaxable", requisitionHeader.getTaxShipping());
			incomingRequest.put("PoHeader_shippingTax", requisitionHeader.getShippingTaxAmt().toString());
			incomingRequest.put("PoHeader_icHeaderHistory", requisitionHeader.getIcHeaderHistory().toString());
			incomingRequest.put("PoHeader_shipToCode", requisitionHeader.getShipToCode());
			incomingRequest.put("PoHeader_shipToContact", requisitionHeader.getShipToContact());
			incomingRequest.put("PoHeader_shipToCode", requisitionHeader.getShipToCode());
			incomingRequest.put("PoHeader_kit", requisitionHeader.getKit());
			incomingRequest.put("PoHeader_vendorName", "");
			incomingRequest.put("PoHeader_requisitionerCode", "");

			incomingRequest.put("PoHeader_releaseLimit", "0");

			incomingRequest.put("PoHeader_confirming", "N");
			incomingRequest.put("PoHeader_owner", userId);
			incomingRequest.put("PoHeader_dateEntered", today);
			incomingRequest.put("PoHeader_lastRelease", "");
			incomingRequest.put("PoHeader_lastRevision", "C");

			incomingRequest.put("PoHeader_currencyFactor", "0");
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
			incomingRequest.put("PoHeader_poRecalc", "");
			incomingRequest.put("PoHeader_actionAlertFlag", "");
			incomingRequest.put("PoHeader_obligAmt", "0");
			incomingRequest.put("PoHeader_obligDate", null);
            incomingRequest.put("PoHeader_timeZone", userTimeZone);


			incomingRequest.put("PoHeader_status", DocumentStatus.PO_INPROGRESS);

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
