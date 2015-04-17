package com.tsa.puridiom.recentrequisition.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.recentrequisition.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Dates;
import java.math.BigDecimal;
import java.util.*;

public class RecentRequisitionAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			RecentRequisitionAdd test = new RecentRequisitionAdd();
			Map incomingRequest = new HashMap();

			RecentRequisition recentRequisition = new RecentRequisition();
			RecentRequisitionPK pk = new RecentRequisitionPK();

			pk.setIcReqHeader(new BigDecimal("1234567891"));
			pk.setRequisitionerCode("KELLI");
			recentRequisition.setComp_id(pk);
			recentRequisition.setDateEntered(Dates.getDate(Dates.today("yyyy-MM-dd","")));

			dbs.startTransaction();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("recentRequisition", recentRequisition);

			recentRequisition = (RecentRequisition) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				//system.out.println("RecentRequisitionAddTest SUCCESS");
				dbs.commit();
			}

			//system.out.println(incomingRequest);
			//system.out.println("RecentRequisitionAddTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}