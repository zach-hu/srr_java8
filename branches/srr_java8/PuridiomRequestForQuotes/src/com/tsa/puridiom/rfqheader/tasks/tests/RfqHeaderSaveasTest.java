package com.tsa.puridiom.rfqheader.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.rfqheader.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class RfqHeaderSaveasTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			dbs.startTransaction();
			
			RfqHeaderSaveas saveasTask = new RfqHeaderSaveas();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			
			RfqHeader rfqHeader = new RfqHeader();
			rfqHeader.setIcRfqHeader(new BigDecimal("999999999"));
			rfqHeader.setIcHeaderHistory(new BigDecimal("999999990"));
			rfqHeader.setAuctionType("O");
			rfqHeader.setBidAccess("R");
			rfqHeader.setBidItemOptions("L");
			rfqHeader.setFiscalYear("2003");
			rfqHeader.setLanguage("[Default]");
			rfqHeader.setLastChgBy("MARK");
			rfqHeader.setLowestBidReq("N");
			rfqHeader.setLowestDisplay("N");
			
			incomingRequest.put("rfqHeader", rfqHeader) ;
			incomingRequest.put("RfqHeader_icRfqHeader", String.valueOf(rfqHeader.getIcRfqHeader()));
			saveasTask.executeTask(incomingRequest);
			
			if (saveasTask.getStatus() == Status.SUCCEEDED) {
				dbs.commit();
			}
			System.out.println("Status: " + saveasTask.getStatus());			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}