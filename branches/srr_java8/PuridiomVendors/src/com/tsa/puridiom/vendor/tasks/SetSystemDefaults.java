package com.tsa.puridiom.vendor.tasks;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class SetSystemDefaults extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			String organizationId = (String)incomingRequest.get("organizationId");
			Map supplierDefaults = PropertiesManager.getInstance(organizationId).getSection("VENDOR DEFAULTS");
			PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;
			
			incomingRequest.put("Vendor_currencyCode", supplierDefaults.get("CURRENCYCODE"));
			incomingRequest.put("Vendor_inspectionReqd", supplierDefaults.get("INSPECTIONREQD"));
			
			if ( propertiesManager.getProperty("SUPPLIER DEFAULTS", "SUPPLIERVALUESDEFAULT", "N").equals("Y")){			
				incomingRequest.put("Vendor_shipVia", (String)incomingRequest.get("Vendor_shipVia"));
				incomingRequest.put("Vendor_vendTerms", (String)incomingRequest.get("Vendor_vendTerms"));
				incomingRequest.put("Vendor_fobId", (String)incomingRequest.get("Vendor_fobId"));
			}
			else
			{
				incomingRequest.put("Vendor_shipVia", supplierDefaults.get("SHIPVIA"));
				incomingRequest.put("Vendor_vendTerms", supplierDefaults.get("VENDTERMS"));
				incomingRequest.put("Vendor_fobId", supplierDefaults.get("FOBID"));
			}
			incomingRequest.put("Vendor_vendUdf1", supplierDefaults.get("VENDUDF1"));
			incomingRequest.put("Vendor_vendUdf2", supplierDefaults.get("VENDUDF2"));
			incomingRequest.put("Vendor_vendUdf3", supplierDefaults.get("VENDUDF3"));
			incomingRequest.put("Vendor_vendUdf4", supplierDefaults.get("VENDUDF4"));
			incomingRequest.put("Vendor_vendUdf5", supplierDefaults.get("VENDUDF5"));
			incomingRequest.put("Vendor_vendUdf6", supplierDefaults.get("VENDUDF6"));
			incomingRequest.put("Vendor_vendUdf7", supplierDefaults.get("VENDUDF7"));
			incomingRequest.put("Vendor_vendUdf8", supplierDefaults.get("VENDUDF8"));
			incomingRequest.put("Vendor_vendUdf9", supplierDefaults.get("VENDUDF9"));
			incomingRequest.put("Vendor_vendUdf10", supplierDefaults.get("VENDUDF10"));

			/*
			Vendor vendor = (Vendor)incomingRequest.get("vendor");
			if (vendor == null)
			{
				throw new Exception ("Vendor was not found.");
			}
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(vendor);

			result = vendor;
			*/
			//this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}