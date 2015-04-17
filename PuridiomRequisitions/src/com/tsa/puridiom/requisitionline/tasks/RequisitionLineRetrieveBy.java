package com.tsa.puridiom.requisitionline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class RequisitionLineRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from RequisitionLine as requisitionline where 1=1 ");
		if(incomingRequest.containsKey("RequisitionLine_icReqLine"))
		{
			String icReqLine = (String) incomingRequest.get("RequisitionLine_icReqLine");
			queryString.append(" AND requisitionline.id.icReqLine = '" + icReqLine + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_lineNumber"))
		{
			String lineNumber = (String) incomingRequest.get("RequisitionLine_lineNumber");
			queryString.append(" AND requisitionline.lineNumber = '" + lineNumber + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_icReqHeader"))
		{
			String icReqHeader = (String) incomingRequest.get("RequisitionLine_icReqHeader");
			queryString.append(" AND requisitionline.icReqHeader = '" + icReqHeader + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_requisitionNumber"))
		{
			String requisitionNumber = (String) incomingRequest.get("RequisitionLine_requisitionNumber");
			queryString.append(" AND requisitionline.requisitionNumber = '" + requisitionNumber + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_itemNumber"))
		{
			String itemNumber = (String) incomingRequest.get("RequisitionLine_itemNumber");
			queryString.append(" AND requisitionline.itemNumber = '" + itemNumber + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_itemSource"))
		{
			String itemSource = (String) incomingRequest.get("RequisitionLine_itemSource");
			queryString.append(" AND requisitionline.itemSource = '" + itemSource + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_umCode"))
		{
			String umCode = (String) incomingRequest.get("RequisitionLine_umCode");
			queryString.append(" AND requisitionline.umCode = '" + umCode + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_quantity"))
		{
			String quantity = (String) incomingRequest.get("RequisitionLine_quantity");
			queryString.append(" AND requisitionline.quantity = '" + quantity + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_unitPrice"))
		{
			String unitPrice = (String) incomingRequest.get("RequisitionLine_unitPrice");
			queryString.append(" AND requisitionline.unitPrice = '" + unitPrice + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_commodityCode"))
		{
			String commodityCode = (String) incomingRequest.get("RequisitionLine_commodityCode");
			queryString.append(" AND requisitionline.commodityCode = '" + commodityCode + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_taxable"))
		{
			String taxable = (String) incomingRequest.get("RequisitionLine_taxable");
			queryString.append(" AND requisitionline.taxable = '" + taxable + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_asset"))
		{
			String asset = (String) incomingRequest.get("RequisitionLine_asset");
			queryString.append(" AND requisitionline.asset = '" + asset + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_discountSource"))
		{
			String discountSource = (String) incomingRequest.get("RequisitionLine_discountSource");
			queryString.append(" AND requisitionline.discountSource = '" + discountSource + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_discountPercent"))
		{
			String discountPercent = (String) incomingRequest.get("RequisitionLine_discountPercent");
			queryString.append(" AND requisitionline.discountPercent = '" + discountPercent + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_discountAmount"))
		{
			String discountAmount = (String) incomingRequest.get("RequisitionLine_discountAmount");
			queryString.append(" AND requisitionline.discountAmount = '" + discountAmount + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_shippingCharges"))
		{
			String shippingCharges = (String) incomingRequest.get("RequisitionLine_shippingCharges");
			queryString.append(" AND requisitionline.shippingCharges = '" + shippingCharges + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_taxShipping"))
		{
			String taxShipping = (String) incomingRequest.get("RequisitionLine_taxShipping");
			queryString.append(" AND requisitionline.taxShipping = '" + taxShipping + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_otherCharges"))
		{
			String otherCharges = (String) incomingRequest.get("RequisitionLine_otherCharges");
			queryString.append(" AND requisitionline.otherCharges = '" + otherCharges + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_otherDescription"))
		{
			String otherDescription = (String) incomingRequest.get("RequisitionLine_otherDescription");
			queryString.append(" AND requisitionline.otherDescription = '" + otherDescription + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_taxOther"))
		{
			String taxOther = (String) incomingRequest.get("RequisitionLine_taxOther");
			queryString.append(" AND requisitionline.taxOther = '" + taxOther + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_icPoLine"))
		{
			String icPoLine = (String) incomingRequest.get("RequisitionLine_icPoLine");
			queryString.append(" AND requisitionline.icPoLine = '" + icPoLine + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_splits"))
		{
			String splits = (String) incomingRequest.get("RequisitionLine_splits");
			queryString.append(" AND requisitionline.splits = '" + splits + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_status"))
		{
			String status = (String) incomingRequest.get("RequisitionLine_status");
			queryString.append(" AND requisitionline.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_commentFlag"))
		{
			String commentFlag = (String) incomingRequest.get("RequisitionLine_commentFlag");
			queryString.append(" AND requisitionline.commentFlag = '" + commentFlag + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_taxPercent"))
		{
			String taxPercent = (String) incomingRequest.get("RequisitionLine_taxPercent");
			queryString.append(" AND requisitionline.taxPercent = '" + taxPercent + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_taxAmount"))
		{
			String taxAmount = (String) incomingRequest.get("RequisitionLine_taxAmount");
			queryString.append(" AND requisitionline.taxAmount = '" + taxAmount + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_shippingTaxAmt"))
		{
			String shippingTaxAmt = (String) incomingRequest.get("RequisitionLine_shippingTaxAmt");
			queryString.append(" AND requisitionline.shippingTaxAmt = '" + shippingTaxAmt + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_otherTaxAmount"))
		{
			String otherTaxAmount = (String) incomingRequest.get("RequisitionLine_otherTaxAmount");
			queryString.append(" AND requisitionline.otherTaxAmount = '" + otherTaxAmount + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_reqType"))
		{
			String reqType = (String) incomingRequest.get("RequisitionLine_reqType");
			queryString.append(" AND requisitionline.reqType = '" + reqType + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_catalogId"))
		{
			String catalogId = (String) incomingRequest.get("RequisitionLine_catalogId");
			queryString.append(" AND requisitionline.catalogId = '" + catalogId + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_umFactor"))
		{
			String umFactor = (String) incomingRequest.get("RequisitionLine_umFactor");
			queryString.append(" AND requisitionline.umFactor = '" + umFactor + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_lineTotal"))
		{
			String lineTotal = (String) incomingRequest.get("RequisitionLine_lineTotal");
			queryString.append(" AND requisitionline.lineTotal = '" + lineTotal + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_taxOvr"))
		{
			String taxOvr = (String) incomingRequest.get("RequisitionLine_taxOvr");
			queryString.append(" AND requisitionline.taxOvr = '" + taxOvr + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_discOvr"))
		{
			String discOvr = (String) incomingRequest.get("RequisitionLine_discOvr");
			queryString.append(" AND requisitionline.discOvr = '" + discOvr + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_shipOvr"))
		{
			String shipOvr = (String) incomingRequest.get("RequisitionLine_shipOvr");
			queryString.append(" AND requisitionline.shipOvr = '" + shipOvr + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_otherOvr"))
		{
			String otherOvr = (String) incomingRequest.get("RequisitionLine_otherOvr");
			queryString.append(" AND requisitionline.otherOvr = '" + otherOvr + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_shiptoFlag"))
		{
			String shiptoFlag = (String) incomingRequest.get("RequisitionLine_shiptoFlag");
			queryString.append(" AND requisitionline.shiptoFlag = '" + shiptoFlag + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_autoRelease"))
		{
			String autoRelease = (String) incomingRequest.get("RequisitionLine_autoRelease");
			queryString.append(" AND requisitionline.autoRelease = '" + autoRelease + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_lastQtyEntered"))
		{
			String lastQtyEntered = (String) incomingRequest.get("RequisitionLine_lastQtyEntered");
			queryString.append(" AND requisitionline.lastQtyEntered = '" + lastQtyEntered + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_assignedBuyer"))
		{
			String assignedBuyer = (String) incomingRequest.get("RequisitionLine_assignedBuyer");
			queryString.append(" AND requisitionline.assignedBuyer = '" + assignedBuyer + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_assignedDate"))
		{
			String assignedDate = (String) incomingRequest.get("RequisitionLine_assignedDate");
			queryString.append(" AND requisitionline.assignedDate = '" + assignedDate + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_allocated"))
		{
			String allocated = (String) incomingRequest.get("RequisitionLine_allocated");
			queryString.append(" AND requisitionline.allocated = '" + allocated + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_backordered"))
		{
			String backordered = (String) incomingRequest.get("RequisitionLine_backordered");
			queryString.append(" AND requisitionline.backordered = '" + backordered + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_mfgName"))
		{
			String mfgName = (String) incomingRequest.get("RequisitionLine_mfgName");
			queryString.append(" AND requisitionline.mfgName = '" + mfgName + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_modelNumber"))
		{
			String modelNumber = (String) incomingRequest.get("RequisitionLine_modelNumber");
			queryString.append(" AND requisitionline.modelNumber = '" + modelNumber + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_udf1Code"))
		{
			String udf1Code = (String) incomingRequest.get("RequisitionLine_udf1Code");
			queryString.append(" AND requisitionline.udf1Code = '" + udf1Code + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_udf2Code"))
		{
			String udf2Code = (String) incomingRequest.get("RequisitionLine_udf2Code");
			queryString.append(" AND requisitionline.udf2Code = '" + udf2Code + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_udf3Code"))
		{
			String udf3Code = (String) incomingRequest.get("RequisitionLine_udf3Code");
			queryString.append(" AND requisitionline.udf3Code = '" + udf3Code + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_udf4Code"))
		{
			String udf4Code = (String) incomingRequest.get("RequisitionLine_udf4Code");
			queryString.append(" AND requisitionline.udf4Code = '" + udf4Code + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_udf5Code"))
		{
			String udf5Code = (String) incomingRequest.get("RequisitionLine_udf5Code");
			queryString.append(" AND requisitionline.udf5Code = '" + udf5Code + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_udf6Code"))
		{
			String udf6Code = (String) incomingRequest.get("RequisitionLine_udf6Code");
			queryString.append(" AND requisitionline.udf6Code = '" + udf6Code + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_udf7Code"))
		{
			String udf7Code = (String) incomingRequest.get("RequisitionLine_udf7Code");
			queryString.append(" AND requisitionline.udf7Code = '" + udf7Code + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_udf8Code"))
		{
			String udf8Code = (String) incomingRequest.get("RequisitionLine_udf8Code");
			queryString.append(" AND requisitionline.udf8Code = '" + udf8Code + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_udf9Code"))
		{
			String udf9Code = (String) incomingRequest.get("RequisitionLine_udf9Code");
			queryString.append(" AND requisitionline.udf9Code = '" + udf9Code + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_udf10Code"))
		{
			String udf10Code = (String) incomingRequest.get("RequisitionLine_udf10Code");
			queryString.append(" AND requisitionline.udf10Code = '" + udf10Code + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_memoLine"))
		{
			String memoLine = (String) incomingRequest.get("RequisitionLine_memoLine");
			queryString.append(" AND requisitionline.memoLine = '" + memoLine + "'");
		}

		if(incomingRequest.containsKey("RequisitionLine_rqLineKey"))
		{
			String rqLineKey = (String) incomingRequest.get("RequisitionLine_rqLineKey");
			queryString.append(" AND requisitionline.rqLineKey = '" + rqLineKey + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_receiptRequired"))
		{
			String receiptRequired = (String) incomingRequest.get("RequisitionLine_receiptRequired");
			queryString.append(" AND requisitionline.receiptRequired = '" + receiptRequired + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_roFlag"))
		{
			String roFlag = (String) incomingRequest.get("RequisitionLine_roFlag");
			queryString.append(" AND requisitionline.roFlag = '" + roFlag + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_routing"))
		{
			String routing = (String) incomingRequest.get("RequisitionLine_routing");
			queryString.append(" AND requisitionline.routing = '" + routing + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_itemLocation"))
		{
			String itemLocation = (String) incomingRequest.get("RequisitionLine_itemLocation");
			queryString.append(" AND requisitionline.itemLocation = '" + itemLocation + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_description"))
		{
			String description = (String) incomingRequest.get("RequisitionLine_description");
			queryString.append(" AND requisitionline.description = '" + description + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_icLineHistory"))
		{
			String icLineHistory = (String) incomingRequest.get("RequisitionLine_icLineHistory");
			queryString.append(" AND requisitionline.icLineHistory = '" + icLineHistory + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_taxCode"))
		{
			String taxCode = (String) incomingRequest.get("RequisitionLine_taxCode");
			queryString.append(" AND requisitionline.taxCode = '" + taxCode + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_vendorId"))
		{
			String vendorId = (String) incomingRequest.get("RequisitionLine_vendorId");
			queryString.append(" AND requisitionline.vendorId = '" + vendorId + "'");
		}
		if(incomingRequest.containsKey("RequisitionLine_requisitionerCode"))
		{
			String requisitionerCode = (String) incomingRequest.get("RequisitionLine_requisitionerCode");
			queryString.append(" AND requisitionline.requisitionerCode = '" + requisitionerCode + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
