package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.Map;

public class RfqLineSetupForLookup extends Task {

	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		this.setStatus(Status.COMPLETED);

		UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		incomingRequest.put("RfqLine_icRfqLine",	ukg.getUniqueKey().toString());
		incomingRequest.put("RfqLine_icLineHistory",	ukg.getUniqueKey().toString());

		if (!incomingRequest.containsKey("RfqLine_itemNumber"))	{
			incomingRequest.put("RfqLine_itemNumber", "");
		}
		if (!incomingRequest.containsKey("RfqLine_source"))	{
			incomingRequest.put("RfqLine_source", "");
		}
		if (!incomingRequest.containsKey("RfqLine_umCode"))	{
			incomingRequest.put("RfqLine_umCode", "EA");
		}
		if (!incomingRequest.containsKey("RfqLine_umFactor")) {
			incomingRequest.put("RfqLine_umFactor", "1");
		}
		if (!incomingRequest.containsKey("RfqLine_quantity"))	{
			incomingRequest.put("RfqLine_quantity", "1");
		}
		if (!incomingRequest.containsKey("RfqLine_commodity"))	{
			incomingRequest.put("RfqLine_commodity", "");
		}
		if (!incomingRequest.containsKey("RfqLine_taxable"))	{
			incomingRequest.put("RfqLine_taxable", "Y");
		}
		if (!incomingRequest.containsKey("RfqLine_asset"))	{
			incomingRequest.put("RfqLine_asset", "N");
		}
		if (!incomingRequest.containsKey("RfqLine_splits"))	{
			incomingRequest.put("RfqLine_splits", "N");
		}
		if (!incomingRequest.containsKey("RfqLine_status"))	{
			incomingRequest.put("RfqLine_status", "2000");
		}
		if (!incomingRequest.containsKey("RfqLine_commentFlag"))	{
			incomingRequest.put("RfqLine_commentFlag", "N");
		}
		if (!incomingRequest.containsKey("RfqLine_allocated"))	{
			incomingRequest.put("RfqLine_allocated", "0");
		}
		if (!incomingRequest.containsKey("RfqLine_mfgName"))	{
			incomingRequest.put("RfqLine_mfgName", "");
		}
		if (!incomingRequest.containsKey("RfqLine_modelNumber"))	{
			incomingRequest.put("RfqLine_modelNumber", "");
		}
		if (!incomingRequest.containsKey("RfqLine_udf1Code"))	{
			incomingRequest.put("RfqLine_udf1Code", "");
		}
		if (!incomingRequest.containsKey("RfqLine_udf2Code"))	{
			incomingRequest.put("RfqLine_udf2Code", "");
		}
		if (!incomingRequest.containsKey("RfqLine_udf3Code"))	{
			incomingRequest.put("RfqLine_udf3Code", "");
		}
		if (!incomingRequest.containsKey("RfqLine_udf4Code"))	{
			incomingRequest.put("RfqLine_udf4Code", "");
		}
		if (!incomingRequest.containsKey("RfqLine_udf5Code"))	{
			incomingRequest.put("RfqLine_udf5Code", "");
		}
		if (!incomingRequest.containsKey("RfqLine_routing"))	{
			incomingRequest.put("RfqLine_routing", "");
		}
		if (!incomingRequest.containsKey("RfqLine_description"))	{
			incomingRequest.put("RfqLine_description", "");
		}
		if (!incomingRequest.containsKey("RfqLine_icSource"))	{
			incomingRequest.put("RfqLine_icSource", "0");
		}
		if (!incomingRequest.containsKey("RfqLine_icReqLine"))	{
			incomingRequest.put("RfqLine_icReqLine", "0");
		}
		if (!incomingRequest.containsKey("RfqLine_shiptoFlag"))	{
			incomingRequest.put("RfqLine_shiptoFlag", "");
		}
		if (!incomingRequest.containsKey("RfqLine_lineRevNo"))	{
			incomingRequest.put("RfqLine_lineRevNo", "");
		}

		// The following should be passed
		//		incomingRequest.put("RfqLine_lineNumber", "0");

		RfqHeader  rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader") ;

		if (rfqHeader != null) {
			// Defaults from header
			incomingRequest.put("RfqLine_icRfqHeader", rfqHeader.getIcRfqHeader().toString());
			incomingRequest.put("RfqLine_rfqNumber", rfqHeader.getRfqNumber());
			if (!incomingRequest.containsKey("RfqLine_itemLocation"))	{
				incomingRequest.put("RfqLine_itemLocation", rfqHeader.getItemLocation());
			}
			incomingRequest.put("RfqLine_rfqType",rfqHeader.getRfqType()) ;
			incomingRequest.put("RfqLine_routing",rfqHeader.getRouting()) ;
		}

		return null ;
	}
}