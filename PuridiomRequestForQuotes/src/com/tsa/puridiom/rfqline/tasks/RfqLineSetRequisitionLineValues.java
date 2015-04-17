package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.math.BigDecimal;
import java.util.Map;

public class RfqLineSetRequisitionLineValues extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;

		try {
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			RequisitionLine reqLine = (RequisitionLine) incomingRequest.get("requisitionLine");
			incomingRequest.put("RfqLine_source", reqLine.getItemSource());
			incomingRequest.put("RfqLine_itemNumber", reqLine.getItemNumber());
			incomingRequest.put("RfqLine_umCode", reqLine.getUmCode());
			incomingRequest.put("RfqLine_quantity", String.valueOf(reqLine.getQuantity()));
			incomingRequest.put("RfqLine_taxable",reqLine.getTaxable());
			incomingRequest.put("RfqLine_commodity", reqLine.getCommodityCode());
			incomingRequest.put("RfqLine_icReqLine", String.valueOf(reqLine.getIcReqLine()));
			incomingRequest.put("RfqLine_icSource", String.valueOf(reqLine.getIcReqLine()));
			incomingRequest.put("RfqLine_commentFlag", reqLine.getCommentFlag());
			incomingRequest.put("RfqLine_asset", reqLine.getAsset());
			incomingRequest.put("RfqLine_splits", reqLine.getSplits());
			incomingRequest.put("RfqLine_catalogId", reqLine.getCatalogId());
			incomingRequest.put("RfqLine_umFactor", String.valueOf(reqLine.getUmFactor()));
			incomingRequest.put("RfqLine_shiptoFlag", reqLine.getShiptoFlag());
			incomingRequest.put("RfqLine_mfgName", reqLine.getMfgName());
			incomingRequest.put("RfqLine_modelNumber", reqLine.getModelNumber());
			incomingRequest.put("RfqLine_udf1Code", reqLine.getUdf1Code());
			incomingRequest.put("RfqLine_udf2Code", reqLine.getUdf2Code());
			incomingRequest.put("RfqLine_udf3Code", reqLine.getUdf3Code());
			incomingRequest.put("RfqLine_udf4Code", reqLine.getUdf4Code());
			incomingRequest.put("RfqLine_udf5Code", reqLine.getUdf5Code());
			incomingRequest.put("RfqLine_udf6Code", reqLine.getUdf6Code());
			incomingRequest.put("RfqLine_udf7Code", reqLine.getUdf7Code());
			incomingRequest.put("RfqLine_udf8Code", reqLine.getUdf8Code());
			incomingRequest.put("RfqLine_udf9Code", reqLine.getUdf9Code());
			incomingRequest.put("RfqLine_udf10Code", reqLine.getUdf10Code());
			incomingRequest.put("RfqLine_memoLine", reqLine.getMemoLine());

			incomingRequest.put("RfqLine_icLineHistory", String.valueOf(reqLine.getIcLineHistory()));
			incomingRequest.put("RfqLine_itemLocation", reqLine.getItemLocation());
			incomingRequest.put("RfqLine_description", reqLine.getDescription());
			incomingRequest.put("RfqLine_receiptRequired", reqLine.getReceiptRequired());
			incomingRequest.put("RfqLine_routing", reqLine.getRouting());
			incomingRequest.put("RfqLine_shelfLifeRqd", reqLine.getShelfLifeRqd());

			if (reqLine.getIcAccount().compareTo(new BigDecimal(0)) > 0) {
				incomingRequest.put("RfqLine_icAccount", reqLine.getIcAccount().toString());
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}

}