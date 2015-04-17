package com.tsa.puridiom.rfqbidhistory.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import com.tsa.puridiom.entity.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;

public class RfqBidHistorySubmit extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object ret = null;
		try
		{
			BigDecimal icRfqHeader = new BigDecimal ((String) incomingRequest.get("RfqHeader_icRfqHeader"));
			BigDecimal icRfqLine = new BigDecimal ((String) incomingRequest.get("RfqLine_icRfqLine"));
			String organizationId = (String) incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");
			List vendorList = (List) incomingRequest.get("vendorList");

			for (int i=0; i<vendorList.size(); i++) {

				List vendorData = (List) vendorList.get(i);
				List rfqBidHistoryList = GetRfqBidHistoryList(icRfqHeader.toString(), (String) vendorData.get(0), (DBSession)incomingRequest.get("dbsession"));

				for (int j=0; j<rfqBidHistoryList.size(); j++) {

					RfqBidHistory rfqBidHistory = (RfqBidHistory) rfqBidHistoryList.get(j);
					RfqBidHistoryPK rfqBidHistoryPK = rfqBidHistory.getComp_id();
					String sBidSequence = rfqBidHistoryPK.getBidSequence().add(new BigDecimal("1")).toString();
					String sUnitPrice = rfqBidHistory.getUnitPrice().toString();
					String sVendorId = (String) vendorData.get(0);

					if (icRfqLine.compareTo(rfqBidHistoryPK.getIcRfqLine()) == 0){
						sUnitPrice = (String) vendorData.get(1);
					}

					Map newIncomingRequest = new HashMap();
					newIncomingRequest.put("organizationId", organizationId);
					newIncomingRequest.put("userId", userId);
					newIncomingRequest.put("RfqBidHistory_icRfqHeader", icRfqHeader.toString());
					newIncomingRequest.put("RfqBidHistory_icRfqLine", icRfqLine.toString());
					newIncomingRequest.put("RfqBidHistory_vendorId", sVendorId);
					newIncomingRequest.put("RfqBidHistory_bidSequence", sBidSequence);
					newIncomingRequest.put("RfqBidHistory_unitPrice", sUnitPrice);
					newIncomingRequest.put("rfqBidHistory", rfqBidHistory);
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
					PuridiomProcess process = processLoader.loadProcess("rfqbidhistory-add.xml");
					process.executeProcess(newIncomingRequest);
				}
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}

	private List GetRfqBidHistoryList(String icRfqHeader, String vendorId, DBSession dbsession) {
		List rfqBidHistoryList = new ArrayList();
		try
		{
			Map incomingRequest = new HashMap();
			incomingRequest.put("RfqBid_icRfqHeader", icRfqHeader);
			incomingRequest.put("RfqBid_vendorId", vendorId);
			incomingRequest.put("dbsession", dbsession);
			RfqBidHistoryListRetrieveByHeaderVendor rfqBidHistoryRetrieveTask = new RfqBidHistoryListRetrieveByHeaderVendor();
			rfqBidHistoryList = (List) rfqBidHistoryRetrieveTask.executeTask(incomingRequest);
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