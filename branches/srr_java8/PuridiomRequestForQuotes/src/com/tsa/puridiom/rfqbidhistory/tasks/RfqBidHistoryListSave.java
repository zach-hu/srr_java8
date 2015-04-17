package com.tsa.puridiom.rfqbidhistory.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.tsa.puridiom.entity.*;
import java.util.ArrayList;

public class RfqBidHistoryListSave extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		List rfqBidHistoryList = new ArrayList();
		try
		{
			String organizationId	= (String) incomingRequest.get("organizationId");
			String userId			= (String) incomingRequest.get("userId");

			String RfqBidHistory_icRfqHeader	= (String)incomingRequest.get("RfqBidHistory_icRfqHeader");
			String RfqBidHistory_icRfqLine[]	= (String[])incomingRequest.get("RfqBidHistory_icRfqLine");
			String RfqBidHistory_unitPrice[]	= (String[])incomingRequest.get("RfqBidHistory_unitPrice");
			String RfqBidHistory_quantity[]		= (String[]) incomingRequest.get("RfqBidHistory_quantity");
			String RfqBidHistory_vendorId[]		= (String[])incomingRequest.get("RfqBidHistory_vendorId");
			String RfqBidHistory_bidSequence	= (String)incomingRequest.get("RfqBidHistory_bidSequence");
			String RfqBidHistory_processName	= (String)incomingRequest.get("RfqBidHistory_processName");
			if (Utility.isEmpty(RfqBidHistory_bidSequence)) {
				RfqBidHistory_bidSequence = "0";
			}

			for (int i=0; i<RfqBidHistory_icRfqLine.length;i++)
			{
				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("organizationId",organizationId);
				newIncomingRequest.put("userId",userId);
				newIncomingRequest.put("RfqBidHistory_icRfqHeader",RfqBidHistory_icRfqHeader);
				newIncomingRequest.put("RfqBidHistory_icRfqLine", RfqBidHistory_icRfqLine[i]);
				newIncomingRequest.put("RfqBidHistory_unitPrice",RfqBidHistory_unitPrice[i]);
				newIncomingRequest.put("RfqBidHistory_quantity",RfqBidHistory_quantity[i]);
				newIncomingRequest.put("RfqBidHistory_vendorId",RfqBidHistory_vendorId[i]);
				newIncomingRequest.put("RfqBidHistory_bidSequence",RfqBidHistory_bidSequence);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
				processLoader.setApplicationName(this.getApplicationName());
				PuridiomProcess process = processLoader.loadProcess(RfqBidHistory_processName);

				process.executeProcess(newIncomingRequest);
				RfqBidHistory  rfqBidHistory = (RfqBidHistory )newIncomingRequest.get("rfqBidHistory");
				//Log.debug(this, "RfqBidHistory : " + rfqBidHistory );

				rfqBidHistoryList.add(rfqBidHistory );
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return rfqBidHistoryList;
	}

}