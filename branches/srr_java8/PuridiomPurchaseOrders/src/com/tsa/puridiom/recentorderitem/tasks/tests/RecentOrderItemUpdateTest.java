package com.tsa.puridiom.recentorderitem.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.recentorderitem.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Dates;
import java.math.BigDecimal;
import java.util.*;

public class RecentOrderItemUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			RecentOrderItemUpdate test = new RecentOrderItemUpdate();
			Map incomingRequest = new HashMap();

			RecentOrderItem recentOrderItem = new RecentOrderItem();
			RecentOrderItemPK pk = new RecentOrderItemPK();

			pk.setBuyerCode("KELLI");
			pk.setItemNumber("1807600");
			pk.setItemLocation("COMPUSA");
			recentOrderItem.setComp_id(pk);
			recentOrderItem.setItemSource("CAT");
			recentOrderItem.setDescription("SlimSCSI 1460 PCMCIA Adapter (required only for external SCSI devices such as external Jaz drive or scanner-Laptop Only (1807600)");
			recentOrderItem.setDateEntered(Dates.getDate(Dates.today("yyyy-MM-dd", "")));
			recentOrderItem.setOrderCount(new BigDecimal("2"));
			dbs.startTransaction();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("recentOrderItem", recentOrderItem);

			recentOrderItem = (RecentOrderItem) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("RecentOrderItemUpdateTest SUCCESS");
				dbs.commit();
			}

			System.out.println(incomingRequest);
			System.out.println("RecentOrderItemUpdateTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}