package com.tsa.puridiom.recentorder.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.recentorder.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Dates;
import java.math.BigDecimal;
import java.util.*;

public class RecentOrderAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			RecentOrderAdd test = new RecentOrderAdd();
			Map incomingRequest = new HashMap();

			RecentOrder recentOrder = new RecentOrder();
			RecentOrderPK pk = new RecentOrderPK();

			pk.setBuyerCode("KELLI");
			pk.setIcPoHeader(new BigDecimal("1234567892"));
			recentOrder.setComp_id(pk);
			recentOrder.setDateEntered(Dates.getDate(Dates.today("yyyy-MM-dd", "")));

			dbs.startTransaction();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("recentOrder", recentOrder);

			recentOrder = (RecentOrder) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("RecentOrderAddTest SUCCESS");
				dbs.commit();
			}

			System.out.println(incomingRequest);
			System.out.println("RecentOrderAddTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}