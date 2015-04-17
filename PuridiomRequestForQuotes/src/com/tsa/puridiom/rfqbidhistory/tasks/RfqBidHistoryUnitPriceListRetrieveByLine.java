package com.tsa.puridiom.rfqbidhistory.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.tsa.puridiom.entity.*;
import java.util.ArrayList;

public class RfqBidHistoryUnitPriceListRetrieveByLine extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		List bidHistoryUnitPricesList = new ArrayList();
		try
		{
			String organizationId	= (String) incomingRequest.get("organizationId");
			String userId			= (String) incomingRequest.get("userId");

			String RfqBidHistory_icRfqHeader	= (String)incomingRequest.get("RfqBidHistory_icRfqHeader");
			String RfqBidHistory_icRfqLine		= (String)incomingRequest.get("RfqBidHistory_icRfqLine");
			List   rfqVendorList				= (List)incomingRequest.get("rfqVendorList");

			for (int i=0; i<rfqVendorList.size(); i++)
			{
				RfqVendor rfqVendor = (RfqVendor) rfqVendorList.get(i);
				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("organizationId",organizationId);
				newIncomingRequest.put("userId",userId);
				newIncomingRequest.put("RfqBidHistory_icRfqHeader",RfqBidHistory_icRfqHeader);
				newIncomingRequest.put("RfqBidHistory_icRfqLine", RfqBidHistory_icRfqLine);
				newIncomingRequest.put("RfqBidHistory_vendorId",rfqVendor.getVendorId());

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
				processLoader.setApplicationName(this.getApplicationName());
				PuridiomProcess process = processLoader.loadProcess("rfqbidhistory-unitpricelist-retrieve-by-vendor.xml");
				process.executeProcess(newIncomingRequest);
				int a;
				List vendorUnitPricesList = new ArrayList();
				vendorUnitPricesList.add(rfqVendor.getVendorId());
				vendorUnitPricesList.add(newIncomingRequest.get("unitPriceList"));
				vendorUnitPricesList.add(newIncomingRequest.get("unitDatesList"));
				bidHistoryUnitPricesList.add(vendorUnitPricesList);

			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return bidHistoryUnitPricesList;
	}

}