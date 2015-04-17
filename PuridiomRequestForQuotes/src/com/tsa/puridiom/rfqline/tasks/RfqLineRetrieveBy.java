package com.tsa.puridiom.rfqline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class RfqLineRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from RfqLine as rfqline where 1=1 ");
		if(incomingRequest.containsKey("RfqLine_icRfqLine"))
		{
			String icRfqLine = (String) incomingRequest.get("RfqLine_icRfqLine");
			queryString.append(" AND rfqline.icRfqLine = '" + icRfqLine + "'");
		}
		if(incomingRequest.containsKey("RfqLine_icRfqHeader"))
		{
			String icRfqHeader = (String) incomingRequest.get("RfqLine_icRfqHeader");
			queryString.append(" AND rfqline.icRfqHeader = '" + icRfqHeader + "'");
		}
		if(incomingRequest.containsKey("RfqLine_rfqLine"))
		{
			String rfqLine = (String) incomingRequest.get("RfqLine_rfqLine");
			queryString.append(" AND rfqline.rfqLine = '" + rfqLine + "'");
		}
		if(incomingRequest.containsKey("RfqLine_rfqNumber"))
		{
			String rfqNumber = (String) incomingRequest.get("RfqLine_rfqNumber");
			queryString.append(" AND rfqline.rfqNumber = '" + rfqNumber + "'");
		}
		if(incomingRequest.containsKey("RfqLine_source"))
		{
			String source = (String) incomingRequest.get("RfqLine_source");
			queryString.append(" AND rfqline.source = '" + source + "'");
		}
		if(incomingRequest.containsKey("RfqLine_itemNumber"))
		{
			String itemNumber = (String) incomingRequest.get("RfqLine_itemNumber");
			queryString.append(" AND rfqline.itemNumber = '" + itemNumber + "'");
		}
		if(incomingRequest.containsKey("RfqLine_umCode"))
		{
			String umCode = (String) incomingRequest.get("RfqLine_umCode");
			queryString.append(" AND rfqline.umCode = '" + umCode + "'");
		}
		if(incomingRequest.containsKey("RfqLine_quantity"))
		{
			String quantity = (String) incomingRequest.get("RfqLine_quantity");
			queryString.append(" AND rfqline.quantity = '" + quantity + "'");
		}
		if(incomingRequest.containsKey("RfqLine_taxable"))
		{
			String taxable = (String) incomingRequest.get("RfqLine_taxable");
			queryString.append(" AND rfqline.taxable = '" + taxable + "'");
		}
		if(incomingRequest.containsKey("RfqLine_status"))
		{
			String status = (String) incomingRequest.get("RfqLine_status");
			queryString.append(" AND rfqline.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("RfqLine_commodity"))
		{
			String commodity = (String) incomingRequest.get("RfqLine_commodity");
			queryString.append(" AND rfqline.commodity = '" + commodity + "'");
		}
		if(incomingRequest.containsKey("RfqLine_icSource"))
		{
			String icSource = (String) incomingRequest.get("RfqLine_icSource");
			queryString.append(" AND rfqline.icSource = '" + icSource + "'");
		}
		if(incomingRequest.containsKey("RfqLine_commentFlag"))
		{
			String commentFlag = (String) incomingRequest.get("RfqLine_commentFlag");
			queryString.append(" AND rfqline.commentFlag = '" + commentFlag + "'");
		}
		if(incomingRequest.containsKey("RfqLine_asset"))
		{
			String asset = (String) incomingRequest.get("RfqLine_asset");
			queryString.append(" AND rfqline.asset = '" + asset + "'");
		}
		if(incomingRequest.containsKey("RfqLine_splits"))
		{
			String splits = (String) incomingRequest.get("RfqLine_splits");
			queryString.append(" AND rfqline.splits = '" + splits + "'");
		}
		if(incomingRequest.containsKey("RfqLine_catalogId"))
		{
			String catalogId = (String) incomingRequest.get("RfqLine_catalogId");
			queryString.append(" AND rfqline.catalogId = '" + catalogId + "'");
		}
		if(incomingRequest.containsKey("RfqLine_umFactor"))
		{
			String umFactor = (String) incomingRequest.get("RfqLine_umFactor");
			queryString.append(" AND rfqline.umFactor = '" + umFactor + "'");
		}
		if(incomingRequest.containsKey("RfqLine_icReqLine"))
		{
			String icReqLine = (String) incomingRequest.get("RfqLine_icReqLine");
			queryString.append(" AND rfqline.icReqLine = '" + icReqLine + "'");
		}
		if(incomingRequest.containsKey("RfqLine_shiptoFlag"))
		{
			String shiptoFlag = (String) incomingRequest.get("RfqLine_shiptoFlag");
			queryString.append(" AND rfqline.shiptoFlag = '" + shiptoFlag + "'");
		}
		if(incomingRequest.containsKey("RfqLine_allocated"))
		{
			String allocated = (String) incomingRequest.get("RfqLine_allocated");
			queryString.append(" AND rfqline.allocated = '" + allocated + "'");
		}
		if(incomingRequest.containsKey("RfqLine_mfgName"))
		{
			String mfgName = (String) incomingRequest.get("RfqLine_mfgName");
			queryString.append(" AND rfqline.mfgName = '" + mfgName + "'");
		}
		if(incomingRequest.containsKey("RfqLine_modelNumber"))
		{
			String modelNumber = (String) incomingRequest.get("RfqLine_modelNumber");
			queryString.append(" AND rfqline.modelNumber = '" + modelNumber + "'");
		}
		if(incomingRequest.containsKey("RfqLine_udf1Code"))
		{
			String udf1Code = (String) incomingRequest.get("RfqLine_udf1Code");
			queryString.append(" AND rfqline.udf1Code = '" + udf1Code + "'");
		}
		if(incomingRequest.containsKey("RfqLine_udf2Code"))
		{
			String udf2Code = (String) incomingRequest.get("RfqLine_udf2Code");
			queryString.append(" AND rfqline.udf2Code = '" + udf2Code + "'");
		}
		if(incomingRequest.containsKey("RfqLine_udf3Code"))
		{
			String udf3Code = (String) incomingRequest.get("RfqLine_udf3Code");
			queryString.append(" AND rfqline.udf3Code = '" + udf3Code + "'");
		}
		if(incomingRequest.containsKey("RfqLine_udf4Code"))
		{
			String udf4Code = (String) incomingRequest.get("RfqLine_udf4Code");
			queryString.append(" AND rfqline.udf4Code = '" + udf4Code + "'");
		}
		if(incomingRequest.containsKey("RfqLine_udf5Code"))
		{
			String udf5Code = (String) incomingRequest.get("RfqLine_udf5Code");
			queryString.append(" AND rfqline.udf5Code = '" + udf5Code + "'");
		}
		if(incomingRequest.containsKey("RfqLine_udf6Code"))
		{
			String udf6Code = (String) incomingRequest.get("RfqLine_udf6Code");
			queryString.append(" AND rfqline.udf6Code = '" + udf6Code + "'");
		}
		if(incomingRequest.containsKey("RfqLine_udf7Code"))
		{
			String udf7Code = (String) incomingRequest.get("RfqLine_udf7Code");
			queryString.append(" AND rfqline.udf7Code = '" + udf7Code + "'");
		}
		if(incomingRequest.containsKey("RfqLine_udf8Code"))
		{
			String udf8Code = (String) incomingRequest.get("RfqLine_udf8Code");
			queryString.append(" AND rfqline.udf8Code = '" + udf8Code + "'");
		}
		if(incomingRequest.containsKey("RfqLine_udf9Code"))
		{
			String udf9Code = (String) incomingRequest.get("RfqLine_udf9Code");
			queryString.append(" AND rfqline.udf9Code = '" + udf9Code + "'");
		}
		if(incomingRequest.containsKey("RfqLine_udf10Code"))
		{
			String udf10Code = (String) incomingRequest.get("RfqLine_udf10Code");
			queryString.append(" AND rfqline.udf10Code = '" + udf10Code + "'");
		}
		if(incomingRequest.containsKey("RfqLine_memoLine"))
		{
			String memoLine = (String) incomingRequest.get("RfqLine_memoLine");
			queryString.append(" AND rfqline.memoLine = '" + memoLine + "'");
		}

		if(incomingRequest.containsKey("RfqLine_lineRevNo"))
		{
			String lineRevNo = (String) incomingRequest.get("RfqLine_lineRevNo");
			queryString.append(" AND rfqline.lineRevNo = '" + lineRevNo + "'");
		}
		if(incomingRequest.containsKey("RfqLine_icLineHistory"))
		{
			String icLineHistory = (String) incomingRequest.get("RfqLine_icLineHistory");
			queryString.append(" AND rfqline.icLineHistory = '" + icLineHistory + "'");
		}
		if(incomingRequest.containsKey("RfqLine_itemLocation"))
		{
			String itemLocation = (String) incomingRequest.get("RfqLine_itemLocation");
			queryString.append(" AND rfqline.itemLocation = '" + itemLocation + "'");
		}
		if(incomingRequest.containsKey("RfqLine_description"))
		{
			String description = (String) incomingRequest.get("RfqLine_description");
			queryString.append(" AND rfqline.description = '" + description + "'");
		}
		if(incomingRequest.containsKey("RfqLine_taxCode"))
		{
			String taxCode = (String) incomingRequest.get("RfqLine_taxCode");
			queryString.append(" AND rfqline.taxCode = '" + taxCode + "'");
		}
		if(incomingRequest.containsKey("RfqLine_routing"))
		{
			String routing = (String) incomingRequest.get("RfqLine_routing");
			queryString.append(" AND rfqline.routing = '" + routing + "'");
		}
		if(incomingRequest.containsKey("RfqLine_receiptRequired"))
		{
			String receiptRequired = (String) incomingRequest.get("RfqLine_receiptRequired");
			queryString.append(" AND rfqline.receiptRequired = '" + receiptRequired + "'");
		}
		if(incomingRequest.containsKey("RfqLine_icAccount"))
		{
			String icAccount = (String) incomingRequest.get("RfqLine_icAccount");
			queryString.append(" AND rfqline.icAccount = '" + icAccount + "'");
		}
		if(incomingRequest.containsKey("RfqLine_vendorAwarded"))
		{
			String vendorAwarded = (String) incomingRequest.get("RfqLine_vendorAwarded");
			queryString.append(" AND rfqline.vendorAwarded = '" + vendorAwarded + "'");
		}
		if(incomingRequest.containsKey("RfqLine_icXls"))
		{
			String icXls = (String) incomingRequest.get("RfqLine_icXls");
			queryString.append(" AND rfqline.icXls = '" + icXls + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
