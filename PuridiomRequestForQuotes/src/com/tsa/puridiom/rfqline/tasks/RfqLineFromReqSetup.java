/**
 *
 * Created on Jan 26, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.RfqLine.tasks.RfqLineFromReqSetup.java
 *
 */
package com.tsa.puridiom.rfqline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.RfqLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.ShipTo;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsa.puridiom.common.documents.ItemLookup;


public class RfqLineFromReqSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
	    Object result = null;

	    try
	    {
			RequisitionLine reqLine = (RequisitionLine)incomingRequest.get("requisitionLine");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
			if(reqLine == null)
			{
			    this.setStatus(Status.FAILED);
			    throw new TsaException("RequisitionLine can not be Empty!");
			}
			incomingRequest.put("RfqLine_icReqLine", reqLine.getIcReqLine().toString());
			incomingRequest.put("RfqLine_lineNumber", incomingRequest.get("lineNumber"));
			incomingRequest.put("RfqLine_discountSource", reqLine.getDiscountSource());
			incomingRequest.put("RfqLine_itemNumber", reqLine.getItemNumber());
			incomingRequest.put("RfqLine_catalogId", reqLine.getCatalogId());
			incomingRequest.put("RfqLine_itemSource", reqLine.getItemSource());
			incomingRequest.put("RfqLine_requisitionerCode", reqLine.getRequisitionerCode());
			incomingRequest.put("RfqLine_receiptRequired", reqLine.getReceiptRequired());
			incomingRequest.put("RfqLine_umCode", reqLine.getUmCode());
			incomingRequest.put("RfqLine_umFactor", reqLine.getUmFactor().toString());
			incomingRequest.put("RfqLine_otherOvr", reqLine.getOtherOvr());
			incomingRequest.put("RfqLine_shipOvr", reqLine.getShipOvr());
			incomingRequest.put("RfqLine_discOvr", reqLine.getDiscOvr());
			incomingRequest.put("RfqLine_taxOvr", reqLine.getTaxOvr());
			incomingRequest.put("RfqLine_taxPercent", reqLine.getTaxPercent().toString());
			incomingRequest.put("RfqLine_taxAmount", reqLine.getTaxAmount().toString());
			incomingRequest.put("RfqLine_taxCode", reqLine.getTaxCode());
			incomingRequest.put("RfqLine_shippingTax", reqLine.getShippingTaxAmt().toString());
			incomingRequest.put("RfqLine_otherTax", reqLine.getOtherTaxAmount().toString());
			incomingRequest.put("RfqLine_quantity", reqLine.getQuantity().toString());
			incomingRequest.put("RfqLine_unitPrice", reqLine.getUnitPrice().toString());
			incomingRequest.put("RfqLine_commodity", reqLine.getCommodityCode());
			incomingRequest.put("RfqLine_taxable", reqLine.getTaxable());
			incomingRequest.put("RfqLine_asset", reqLine.getAsset());
			incomingRequest.put("RfqLine_discountSource", reqLine.getDiscountSource());
			incomingRequest.put("RfqLine_discountPercent", reqLine.getDiscountPercent().toString());
			incomingRequest.put("RfqLine_discountAmount", reqLine.getDiscountAmount().toString());
			incomingRequest.put("RfqLine_shippingCharges", reqLine.getShippingCharges().toString());
			incomingRequest.put("RfqLine_shippingTaxable", reqLine.getTaxShipping().toString());
			incomingRequest.put("RfqLine_otherCharges", reqLine.getOtherCharges().toString());
			incomingRequest.put("RfqLine_otherDescription", reqLine.getOtherDescription());
			incomingRequest.put("RfqLine_otherTaxable", reqLine.getTaxOther());
			incomingRequest.put("RfqLine_splits", reqLine.getSplits());
			incomingRequest.put("RfqLine_commentFlag", reqLine.getCommentFlag());
			incomingRequest.put("RfqLine_mfgName", reqLine.getMfgName());
			incomingRequest.put("RfqLine_modelNumber", reqLine.getModelNumber());
			incomingRequest.put("RfqLine_udf1Code", reqLine.getUdf1Code());
			incomingRequest.put("RfqLine_udf2Code", reqLine.getUdf2Code());
			incomingRequest.put("RfqLine_udf3Code", reqLine.getUdf3Code());
			incomingRequest.put("RfqLine_udf4Code", reqLine.getUdf4Code());
			incomingRequest.put("RfqLine_udf5Code", reqLine.getUdf5Code());
			incomingRequest.put("RfqLine_icLineHistory", reqLine.getIcLineHistory().toString());
			incomingRequest.put("RfqLine_itemLocation", reqLine.getItemLocation());
			incomingRequest.put("RfqLine_routing", reqLine.getRouting());
			incomingRequest.put("RfqLine_description", reqLine.getDescription());
			incomingRequest.put("RfqLine_requisitionNumber",reqLine.getRequisitionNumber()) ;
			incomingRequest.put("RfqLine_departmentCode",reqLine.getDepartmentCode()) ;
            incomingRequest.put("RfqLine_dateEntered", Dates.today("", userTimeZone));

			incomingRequest.put("RfqLine_udf6Code", reqLine.getUdf6Code());
			incomingRequest.put("RfqLine_udf7Code", reqLine.getUdf7Code());
			incomingRequest.put("RfqLine_udf8Code", reqLine.getUdf8Code());
			incomingRequest.put("RfqLine_udf9Code", reqLine.getUdf9Code());
			incomingRequest.put("RfqLine_udf10Code", reqLine.getUdf10Code());
			incomingRequest.put("RfqLine_memoLine", reqLine.getMemoLine());


			incomingRequest.put("createdfrom", "REQ");

			this.setStatus(Status.SUCCEEDED);
	    }
	    catch (Exception e)
	    {
            this.setStatus(Status.FAILED);
            throw e;
        }
		return result;
	}

}
