/**
 *
 * Created on Jan 26, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineFromRfqSetup.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RfqBid;
import com.tsa.puridiom.entity.RfqLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

public class PoLineFromRfqSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			RfqLine rfqLine = (RfqLine)incomingRequest.get("rfqLine");
			RequisitionLine requisitionLine = (RequisitionLine)incomingRequest.get("requisitionLine");
			if(rfqLine == null)
			{
				throw new TsaException("No rfqLine entity was found!");
			}
			RfqBid rfqBid = (RfqBid)incomingRequest.get("rfqBid");
			if(rfqBid== null)
			{
				throw new TsaException("No rfqBid entity was found!");
			}
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			incomingRequest.put("PoLine_icPoLine",ukg.getUniqueKey().toString());
			incomingRequest.put("PoLine_icPoHeader", poHeader.getIcPoHeader().toString());
			incomingRequest.put("PoLine_poNumber", poHeader.getPoNumber());
			incomingRequest.put("PoLine_icRfqLine", Utility.tsaToString(rfqLine.getIcRfqLine()));
			incomingRequest.put("PoLine_icLineKey", Utility.tsaToString(rfqLine.getIcRfqLine()));
			incomingRequest.put("PoLine_lineNumber", incomingRequest.get("lineNumber"));
			incomingRequest.put("PoLine_discountSource", " ");
			incomingRequest.put("PoLine_itemNumber", rfqLine.getItemNumber());
			incomingRequest.put("PoLine_description", rfqLine.getDescription());
			incomingRequest.put("PoLine_catalogId", rfqLine.getCatalogId());
			incomingRequest.put("PoLine_itemSource", rfqLine.getSource());
			incomingRequest.put("PoLine_receiptRequired", rfqLine.getReceiptRequired());
			incomingRequest.put("PoLine_umCode", rfqLine.getUmCode());
			incomingRequest.put("PoLine_umFactor", String.valueOf(rfqLine.getUmFactor()));
			incomingRequest.put("PoLine_quantity", Utility.tsaToString(rfqBid.getQuantity()));
			incomingRequest.put("PoLine_unitPrice", Utility.tsaToString(rfqBid.getUnitPrice()));
			incomingRequest.put("PoLine_commodity", rfqLine.getCommodity());
			incomingRequest.put("PoLine_taxable", rfqLine.getTaxable());
			incomingRequest.put("PoLine_asset", rfqLine.getAsset());
			incomingRequest.put("PoLine_discountSource", rfqBid.getDiscountSource());
			incomingRequest.put("PoLine_discountPercent", Utility.tsaToString(rfqBid.getDiscountPercent()));
			incomingRequest.put("PoLine_discountAmount", Utility.tsaToString(rfqBid.getDiscountAmount()));
			incomingRequest.put("PoLine_shippingCharges", Utility.tsaToString(rfqBid.getShippingCharges()));
			incomingRequest.put("PoLine_shippingTaxable", Utility.tsaToString(rfqBid.getTaxShipping()));
			incomingRequest.put("PoLine_otherCharges", Utility.tsaToString(rfqBid.getOtherCharges()));
			incomingRequest.put("PoLine_otherDescription", rfqBid.getOtherDescript());

			incomingRequest.put("PoLine_otherTaxable", rfqBid.getTaxOther());
			incomingRequest.put("PoLine_splits", rfqLine.getSplits());
			incomingRequest.put("PoLine_commentFlag", rfqBid.getCommentFlag());
			incomingRequest.put("PoLine_mfgName", rfqLine.getMfgName());
			incomingRequest.put("PoLine_modelNumber", rfqLine.getModelNumber());
			incomingRequest.put("PoLine_udf1Code", rfqLine.getUdf1Code());
			incomingRequest.put("PoLine_udf2Code", rfqLine.getUdf2Code());
			incomingRequest.put("PoLine_udf3Code", rfqLine.getUdf3Code());
			incomingRequest.put("PoLine_udf4Code", rfqLine.getUdf4Code());
			incomingRequest.put("PoLine_udf5Code", rfqLine.getUdf5Code());
			incomingRequest.put("PoLine_udf6Code", rfqLine.getUdf6Code());
			incomingRequest.put("PoLine_udf7Code", rfqLine.getUdf7Code());
			incomingRequest.put("PoLine_udf8Code", rfqLine.getUdf8Code());
			incomingRequest.put("PoLine_shelfLifeRqd", rfqLine.getShelfLifeRqd());
			incomingRequest.put("PoLine_udf9Code", rfqLine.getUdf9Code());
			incomingRequest.put("PoLine_udf10Code", rfqLine.getUdf10Code());
			incomingRequest.put("PoLine_memoLine", rfqLine.getMemoLine());

			incomingRequest.put("PoLine_itemLocation", rfqLine.getItemLocation());
			incomingRequest.put("PoLine_routing", rfqLine.getRouting());
			incomingRequest.put("PoLine_icLineHistory", rfqLine.getIcLineHistory().toString());

			incomingRequest.put("PoLine_taxCode", rfqLine.getTaxCode());

			if (requisitionLine != null) {
			    incomingRequest.put("PoLine_requisitionerCode", requisitionLine.getRequisitionerCode());
			    incomingRequest.put("PoLine_departmentCode", requisitionLine.getDepartmentCode());
			    incomingRequest.put("PoLine_requisitionNumber", requisitionLine.getRequisitionNumber());
			    incomingRequest.put("PoLine_icReqLine", String.valueOf(requisitionLine.getIcReqLine()));
			}

			incomingRequest.put("createdfrom", "RFQ");

			this.setStatus(Status.SUCCEEDED);

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(e.toString(), e);
		}
		return null;
	}

}