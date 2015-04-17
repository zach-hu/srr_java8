package com.tsa.puridiom.reportqueue.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.ReportQueue;

public class ReportQueueSetValuesPK
{
	public void setValues(Map incomingRequest, ReportQueue reportQueue ) throws Exception
	{
		try
		{
			String s_icReportQueue = (String) incomingRequest.get("ReportQueue_icReportQueue");
			BigDecimal icReportQueue = new BigDecimal(s_icReportQueue);
			reportQueue.setIcReportQueue(icReportQueue);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}