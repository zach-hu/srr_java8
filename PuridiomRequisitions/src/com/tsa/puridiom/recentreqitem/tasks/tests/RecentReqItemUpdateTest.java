package com.tsa.puridiom.recentreqitem.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.recentreqitem.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Dates;

import java.math.BigDecimal;
import java.util.*;

public class RecentReqItemUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			RecentReqItemUpdate test = new RecentReqItemUpdate();
			Map incomingRequest = new HashMap();

			RecentReqItem recentReqItem = new RecentReqItem();
			RecentReqItemPK pk = new RecentReqItemPK();

			pk.setRequisitionerCode("KELLI");
			pk.setItemNumber("1835000");
			pk.setItemLocation("COMPUSA");
			recentReqItem.setComp_id(pk);
			recentReqItem.setItemSource("CAT");
			recentReqItem.setDescription("SlimSCSI 1460 PCMCIA Adapter (required only for external SCSI devices such as external Jaz drive or scanner-Laptop Only (1807600)");
			recentReqItem.setDateEntered(Dates.getDate(Dates.today("yyyy-MM-dd", "")));
			recentReqItem.setRequestCount(new BigDecimal("2"));

			dbs.startTransaction();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("recentReqItem", recentReqItem);

			recentReqItem = (RecentReqItem) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("RecentReqItemUpdateTest SUCCESS");
				dbs.commit();
			}

			System.out.println(incomingRequest);
			System.out.println("RecentReqItemUpdateTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}