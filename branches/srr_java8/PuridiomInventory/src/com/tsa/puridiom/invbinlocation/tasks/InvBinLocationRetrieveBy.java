package com.tsa.puridiom.invbinlocation.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class InvBinLocationRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        String organizationId = (String) incomingRequest.get("organizationId");
        PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId);
        String disburseOrder = propertiesManager.getProperty("INVENTORY", "DISBORDER", "FIFO") ;

		StringBuffer queryString = new StringBuffer("from InvBinLocation as invbinlocation where 1=1 ");
		if(incomingRequest.containsKey("InvBinLocation_icRc"))
		{
			String icRc = (String) incomingRequest.get("InvBinLocation_icRc");
			queryString.append(" AND invbinlocation.id.icRc = '" + icRc + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_itemNumber"))
		{
			String itemNumber = (String) incomingRequest.get("InvBinLocation_itemNumber");
			queryString.append(" AND invbinlocation.itemNumber = '" + itemNumber + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_itemLocation"))
		{
			String itemLocation = (String) incomingRequest.get("InvBinLocation_itemLocation");
			queryString.append(" AND invbinlocation.itemLocation = '" + itemLocation + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_vendorId"))
		{
			String vendorId = (String) incomingRequest.get("InvBinLocation_vendorId");
			queryString.append(" AND invbinlocation.vendorId = '" + vendorId + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_manufactNo"))
		{
			String manufactNo = (String) incomingRequest.get("InvBinLocation_manufactNo");
			queryString.append(" AND invbinlocation.manufactNo = '" + manufactNo + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_lot"))
		{
			String lot = (String) incomingRequest.get("InvBinLocation_lot");
			queryString.append(" AND invbinlocation.lot = '" + lot + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_cost"))
		{
			String cost = (String) incomingRequest.get("InvBinLocation_cost");
			queryString.append(" AND invbinlocation.cost = '" + cost + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_itemDate"))
		{
			String itemDate = (String) incomingRequest.get("InvBinLocation_itemDate");
			queryString.append(" AND invbinlocation.itemDate = '" + itemDate + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_aisle"))
		{
			String aisle = (String) incomingRequest.get("InvBinLocation_aisle");
			queryString.append(" AND invbinlocation.aisle = '" + aisle + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_locrow"))
		{
			String locrow = (String) incomingRequest.get("InvBinLocation_locrow");
			queryString.append(" AND invbinlocation.locrow = '" + locrow + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_tier"))
		{
			String tier = (String) incomingRequest.get("InvBinLocation_tier");
			queryString.append(" AND invbinlocation.tier = '" + tier + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_bin"))
		{
			String bin = (String) incomingRequest.get("InvBinLocation_bin");
			queryString.append(" AND invbinlocation.bin = '" + bin + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_udf1Code"))
		{
			String udf1Code = (String) incomingRequest.get("InvBinLocation_udf1Code");
			queryString.append(" AND invbinlocation.udf1Code = '" + udf1Code + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_udf2Code"))
		{
			String udf2Code = (String) incomingRequest.get("InvBinLocation_udf2Code");
			queryString.append(" AND invbinlocation.udf2Code = '" + udf2Code + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_udf3Code"))
		{
			String udf3Code = (String) incomingRequest.get("InvBinLocation_udf3Code");
			queryString.append(" AND invbinlocation.udf3Code = '" + udf3Code + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_udf4Code"))
		{
			String udf4Code = (String) incomingRequest.get("InvBinLocation_udf4Code");
			queryString.append(" AND invbinlocation.udf4Code = '" + udf4Code + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_udf5Code"))
		{
			String udf5Code = (String) incomingRequest.get("InvBinLocation_udf5Code");
			queryString.append(" AND invbinlocation.udf5Code = '" + udf5Code + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_owner"))
		{
			String owner = (String) incomingRequest.get("InvBinLocation_owner");
			queryString.append(" AND invbinlocation.owner = '" + owner + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_qtyOnHand"))
		{
			String qtyOnHand = (String) incomingRequest.get("InvBinLocation_qtyOnHand");
			queryString.append(" AND invbinlocation.qtyOnHand = '" + qtyOnHand + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_qtyAlloc"))
		{
			String qtyAlloc = (String) incomingRequest.get("InvBinLocation_qtyAlloc");
			queryString.append(" AND invbinlocation.qtyAlloc = '" + qtyAlloc + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_hdrIc"))
		{
			String hdrIc = (String) incomingRequest.get("InvBinLocation_hdrIc");
			queryString.append(" AND invbinlocation.hdrIc = '" + hdrIc + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_icRecLine"))
		{
			String icRecLine = (String) incomingRequest.get("InvBinLocation_icRecLine");
			queryString.append(" AND invbinlocation.icRecLine = '" + icRecLine + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_physActual"))
		{
			String physActual = (String) incomingRequest.get("InvBinLocation_physActual");
			queryString.append(" AND invbinlocation.physActual = '" + physActual + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_physOriginal"))
		{
			String physOriginal = (String) incomingRequest.get("InvBinLocation_physOriginal");
			queryString.append(" AND invbinlocation.physOriginal = '" + physOriginal + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_originalAlloc"))
		{
			String originalAlloc = (String) incomingRequest.get("InvBinLocation_originalAlloc");
			queryString.append(" AND invbinlocation.originalAlloc = '" + originalAlloc + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_status"))
		{
			String status = (String) incomingRequest.get("InvBinLocation_status");
			queryString.append(" AND invbinlocation.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_tempIc"))
		{
			String tempIc = (String) incomingRequest.get("InvBinLocation_tempIc");
			queryString.append(" AND invbinlocation.tempIc = '" + tempIc + "'");
		}
		/*if (!incomingRequest.containsKey("InvBinLocation_status") && !incomingRequest.containsKey("InvBinLocation_tempIc"))
		{
		    // Default to not include temporary records, unless specified otherwise
		    queryString.append(" AND (invbinlocation.status is null OR invbinlocation.status <> '00')");
		}*/
		if(incomingRequest.containsKey("InvBinLocation_source"))
		{
			String source = (String) incomingRequest.get("InvBinLocation_source");
			queryString.append(" AND invbinlocation.source = '" + source + "'");
		}
		if(incomingRequest.containsKey("InvBinLocation_chargeCode"))
		{
			String chargeCode = (String) incomingRequest.get("InvBinLocation_chargeCode");
			queryString.append(" AND invbinlocation.chargeCode = '" + chargeCode + "'");
		}

		queryString.append(" order by invbinlocation.itemDate " ) ;
		if (disburseOrder.equalsIgnoreCase("LIFO")) {
			queryString.append("ASC") ;
		} else {
			queryString.append("DESC") ;
		}


		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
