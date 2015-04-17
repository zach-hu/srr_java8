package com.tsa.puridiom.requisitionline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

public class RequisitionLineSetPoLineValues extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;

		try {
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			String oid = (String)incomingRequest.get("organizationId");
			PoLine poLine = (PoLine) incomingRequest.get("poLine");
			RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
			PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

			incomingRequest.put("RequisitionLine_asset", poLine.getAsset());
			incomingRequest.put("RequisitionLine_catalogId", poLine.getCatalogId());
			incomingRequest.put("RequisitionLine_commentFlag", poLine.getCommentFlag());
			incomingRequest.put("RequisitionLine_commodityCode", poLine.getCommodity());
			incomingRequest.put("RequisitionLine_description", poLine.getDescription());
			incomingRequest.put("RequisitionLine_icLineHistory", String.valueOf(poLine.getIcLineHistory()));
			incomingRequest.put("RequisitionLine_icPoLine", String.valueOf(poLine.getIcReqLine()));
			incomingRequest.put("RequisitionLine_icRevisedPoLine", poLine.getIcPoLine().toString());
			incomingRequest.put("RequisitionLine_itemLocation", poLine.getItemLocation());
			incomingRequest.put("RequisitionLine_itemNumber", poLine.getItemNumber());
			incomingRequest.put("RequisitionLine_itemSource", poLine.getItemSource());
			incomingRequest.put("RequisitionLine_mfgName", poLine.getMfgName());
			incomingRequest.put("RequisitionLine_modelNumber", poLine.getModelNumber());
			incomingRequest.put("RequisitionLine_quantity", String.valueOf(poLine.getQuantity()));
			incomingRequest.put("RequisitionLine_receiptRequired", poLine.getReceiptRequired());
			incomingRequest.put("RequisitionLine_requiredDate", String.valueOf(poLine.getRequiredDate()));
			incomingRequest.put("RequisitionLine_routing", poLine.getRouting());
			incomingRequest.put("RequisitionLine_shiptoFlag", poLine.getShiptoFlag());
			incomingRequest.put("RequisitionLine_splits", poLine.getSplits());
			incomingRequest.put("RequisitionLine_taxable",poLine.getTaxable());
			incomingRequest.put("RequisitionLine_useTaxable",poLine.getUseTaxable());
			incomingRequest.put("RequisitionLine_udf1Code", poLine.getUdf1Code());
			incomingRequest.put("RequisitionLine_udf2Code", poLine.getUdf2Code());
			incomingRequest.put("RequisitionLine_udf3Code", poLine.getUdf3Code());
			incomingRequest.put("RequisitionLine_udf4Code", poLine.getUdf4Code());
			incomingRequest.put("RequisitionLine_udf5Code", poLine.getUdf5Code());
			incomingRequest.put("RequisitionLine_udf6Code", poLine.getUdf6Code());
			incomingRequest.put("RequisitionLine_udf7Code", poLine.getUdf7Code());
			incomingRequest.put("RequisitionLine_udf8Code", poLine.getUdf8Code());
			incomingRequest.put("RequisitionLine_udf9Code", poLine.getUdf9Code());
			incomingRequest.put("RequisitionLine_udf10Code", poLine.getUdf10Code());
			incomingRequest.put("RequisitionLine_memoLine", poLine.getMemoLine());
			if (propertiesManager.getProperty("PO OPTIONS", "CHANGEASSIGNBUYER", "N").equalsIgnoreCase("Y") && requisitionHeader.getRequisitionType().equalsIgnoreCase("C"))
			{
				if (!Utility.isEmpty(requisitionHeader.getAssignedBuyer()))
				{
					incomingRequest.put("RequisitionLine_assignedBuyer", requisitionHeader.getAssignedBuyer());
				}
			}

			if (propertiesManager.getProperty("PO OPTIONS", "RELEASEASSIGNEDBUYER", "N").equalsIgnoreCase("Y") && requisitionHeader.getRequisitionType().equalsIgnoreCase("E"))
			{
				if (!Utility.isEmpty(requisitionHeader.getAssignedBuyer()))
				{
					incomingRequest.put("RequisitionLine_assignedBuyer", requisitionHeader.getAssignedBuyer());
				}
			}

			incomingRequest.put("RequisitionLine_umCode", String.valueOf(poLine.getUmCode()));
			incomingRequest.put("RequisitionLine_umFactor", String.valueOf(poLine.getUmFactor()));
			incomingRequest.put("RequisitionLine_unitPrice", String.valueOf(poLine.getUnitPrice()));

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}

}