package com.tsa.puridiom.catalog.tasks;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.common.utility.HiltonUtility;

import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

public class CatalogItemUpdateStatusByCatalog extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
		    String	organizationId = (String) incomingRequest.get("Catalog_catalogId");
			String	catalogId = (String) incomingRequest.get("Catalog_catalogId");
			String	status = (String)  incomingRequest.get("Catalog_status");
			String	dateExpires = (String) incomingRequest.get("Catalog_dateExpires");
			
			dateExpires = HiltonUtility.getFormattedDate(dateExpires, organizationId, "MM-dd-yyyy");
			
			if (catalogId == null)
			{
				throw new Exception ("Catalog Id was not found.");
			}
		
			String	sqlUpdate = "update catalog_item set status = ?, date_expires = ? where catalog_id = ?";
			Object [] args = new Object[3];
			Integer [] types = new Integer[3];
			args[0] = status;
			types[0] = Types.VARCHAR;
			
			SimpleDateFormat format = new SimpleDateFormat("MM-DD-yyyy");
			args[1] = format.parseObject(dateExpires);
			types[1] = Types.DATE;
			args[2] = catalogId;
			types[2] = Types.VARCHAR;
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			dbs.sqlUpdate(sqlUpdate, args, types);
		
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}