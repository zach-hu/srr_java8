package com.tsa.puridiom.reportqueue.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.ReportQueue;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class ReportQueueSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();

			ReportQueue originalReportQueue = (ReportQueue) incomingRequest.get("reportQueue");
			ReportQueue reportQueue = new ReportQueue();

			reportQueue.setIcReportQueue(new BigDecimal(ukg.getUniqueKey().toString()));
			reportQueue.setModule(originalReportQueue.getModule());
			reportQueue.setType(originalReportQueue.getType());
			reportQueue.setFrequency(originalReportQueue.getFrequency());
			reportQueue.setStartDate(originalReportQueue.getStartDate());
			reportQueue.setEndDate(originalReportQueue.getEndDate());
			reportQueue.setStatus("Incomplete");
			reportQueue.setOwner(originalReportQueue.getOwner());
			reportQueue.setSendFrom(originalReportQueue.getSendFrom());
			reportQueue.setSendTo(originalReportQueue.getSendTo());
			reportQueue.setDateAdded(originalReportQueue.getDateAdded());
			reportQueue.setTimeAdded(originalReportQueue.getTimeAdded());
			reportQueue.setDateSent(originalReportQueue.getDateSent());
			reportQueue.setTimeSent(originalReportQueue.getTimeSent());
			reportQueue.setWhereClause(originalReportQueue.getWhereClause());
			reportQueue.setNextRun(originalReportQueue.getNextRun());
			reportQueue.setName(originalReportQueue.getName());
			reportQueue.setAlias(originalReportQueue.getAlias());
			reportQueue.setPublicView(originalReportQueue.getPublicView());

			incomingRequest.put("reportQueue", reportQueue);

			ReportQueueAdd addTask = new ReportQueueAdd();
			reportQueue = (ReportQueue) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = reportQueue;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}