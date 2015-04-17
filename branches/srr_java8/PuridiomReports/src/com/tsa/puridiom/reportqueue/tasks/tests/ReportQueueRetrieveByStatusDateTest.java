package com.tsa.puridiom.reportqueue.tasks.tests;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RecentRequisition;
import com.tsa.puridiom.entity.ReportQueue;
import com.tsa.puridiom.reportqueue.tasks.ReportQueueRetrieveByStatusDate;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;

public class ReportQueueRetrieveByStatusDateTest extends Task {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println("start");
		try
		{
			DBSession dbs = new DBSession("TEST");
			ReportQueueRetrieveByStatusDate reportQueueRetrieveByStatusDate = new ReportQueueRetrieveByStatusDate();
			Map incomingRequest = new HashMap();
			dbs.startTransaction();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("organizationId", "TEST");
			Calendar cal = Calendar.getInstance();
			Date today = Dates.getSqlDate(cal.getTime());
			System.out.println("date: " + today);
			incomingRequest.put("ReportQueue_status", "00");
			incomingRequest.put("ReportQueue_nextRun", today);
			incomingRequest.put("ReportQueue_startDate", today);
			incomingRequest.put("ReportQueue_endDate", today);
			incomingRequest.put("ReportQueue_deliveryTime", "05:50");

			List queue = (List) reportQueueRetrieveByStatusDate.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("queue SUCCESS, size: " + queue.size());
				for (Iterator iter = queue.iterator(); iter.hasNext();) {
					ReportQueue element = (ReportQueue) iter.next();
					System.out.println(element.getName() + ", " + element.getDeliveryTime());
				}
			}


		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			System.out.println("done");
		}

	}

}
