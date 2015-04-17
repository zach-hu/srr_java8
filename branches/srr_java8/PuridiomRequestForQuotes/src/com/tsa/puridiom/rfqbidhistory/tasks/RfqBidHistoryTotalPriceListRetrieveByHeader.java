package com.tsa.puridiom.rfqbidhistory.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.tsa.puridiom.entity.*;
import java.util.ArrayList;

public class RfqBidHistoryTotalPriceListRetrieveByHeader extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		List bidHistoryTotalPricesList = new ArrayList();
		try
		{
			String organizationId	= (String) incomingRequest.get("organizationId");
			String userId			= (String) incomingRequest.get("userId");

			String RfqBidHistory_icRfqHeader	= (String)incomingRequest.get("RfqBidHistory_icRfqHeader");
			List   rfqVendorList				= (List)incomingRequest.get("rfqVendorList");

			for (int i=0; i<rfqVendorList.size(); i++)
			{
				RfqVendor rfqVendor = (RfqVendor) rfqVendorList.get(i);
				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("organizationId",organizationId);
				newIncomingRequest.put("userId",userId);
				newIncomingRequest.put("RfqBidHistory_icRfqHeader",RfqBidHistory_icRfqHeader);
				newIncomingRequest.put("RfqBidHistory_vendorId",rfqVendor.getVendorId());

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
				processLoader.setApplicationName(this.getApplicationName());
				PuridiomProcess process = processLoader.loadProcess("rfqbidhistory-totalpricelist-retrieve-by-vendor.xml");
				process.executeProcess(newIncomingRequest);

				List vendorTotalPricesList = new ArrayList();
				vendorTotalPricesList.add(rfqVendor.getVendorId());
				vendorTotalPricesList.add(newIncomingRequest.get("totalPriceList"));
				vendorTotalPricesList.add(newIncomingRequest.get("totalDateList"));

				bidHistoryTotalPricesList.add(vendorTotalPricesList);

			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return bidHistoryTotalPricesList;
	}

}