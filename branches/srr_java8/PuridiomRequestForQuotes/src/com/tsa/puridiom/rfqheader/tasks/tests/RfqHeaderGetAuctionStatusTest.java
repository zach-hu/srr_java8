package com.tsa.puridiom.rfqheader.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.rfqheader.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class RfqHeaderGetAuctionStatusTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			dbs.startTransaction();
			
			RfqHeaderGetAuctionStatus task = new RfqHeaderGetAuctionStatus();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			
			RfqHeader rfqHeader = new RfqHeader();
			rfqHeader.setIcRfqHeader(new BigDecimal("999999999"));
			rfqHeader.setAuctionType("O");
			rfqHeader.setBidAccess("R");
			//rfqHeader.setDueDate(new Date("2005/05/09"));
			//rfqHeader.setBidDueTime("12:30");
			rfqHeader.setDueDate(new Date("2005/05/10"));
			rfqHeader.setBidDueTime("11:11:45");
			
			incomingRequest.put("rfqHeader", rfqHeader) ;
			String	auctionStatus = (String) task.executeTask(incomingRequest);
			
			if (task.getStatus() == Status.SUCCEEDED) {
				dbs.commit();
			}
			System.out.println("Test Status: " + task.getStatus());
			System.out.println("auctionStatus: " + auctionStatus);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}