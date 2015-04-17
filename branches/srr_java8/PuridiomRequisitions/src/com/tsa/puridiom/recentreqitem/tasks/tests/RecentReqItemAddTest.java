package com.tsa.puridiom.recentreqitem.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.recentreqitem.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Dates;
import java.util.*;

public class RecentReqItemAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			RecentReqItemAdd test = new RecentReqItemAdd();
			Map incomingRequest = new HashMap();

			RecentReqItem recentReqItem = new RecentReqItem();
			RecentReqItemPK pk = new RecentReqItemPK();

			pk.setRequisitionerCode("KELLI");
			pk.setItemNumber("1835000");
			pk.setItemLocation("COMPUSA");
			recentReqItem.setComp_id(pk);
			recentReqItem.setItemSource("CAT");
			recentReqItem.setDescription("29160N SCSI Kit, Internal SCSI Adapter (PCI), includes internal SCSI cable, Desktop Only (1835000)");
			recentReqItem.setDateEntered(Dates.getDate(Dates.today("yyyy-MM-dd", "")));

			dbs.startTransaction();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("recentReqItem", recentReqItem);

			recentReqItem = (RecentReqItem) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("RecentReqItemAddTest SUCCESS");
				dbs.commit();
			}

			System.out.println(incomingRequest);
			System.out.println("RecentReqItemAddTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}