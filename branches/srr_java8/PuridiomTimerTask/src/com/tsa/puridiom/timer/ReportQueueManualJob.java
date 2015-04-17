package com.tsa.puridiom.timer;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.ReportQueue;
import com.tsa.puridiom.emails.EmailManager;
import com.tsa.puridiom.emails.exception.EmailsException;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;

public class ReportQueueManualJob extends ScheduledJob
{

	public void execute()
	{
		this.getReports();
		Log.debug(this, "ReportQueueJob done");
	}

	protected void getReports()
	{
		try
		{
			this.getReportsToExecute();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void getReportsToExecute()
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
			PuridiomProcess process = processLoader.loadProcess("reportqueue-retrieve-by-status.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", this.getOrganizationId());
			incomingRequest.put("ReportQueue_status", DocumentStatus.SENDQUEUE_NEW);
			incomingRequest.put("ReportQueue_nextRun", new Date());
			incomingRequest.put("ReportQueue_startDate", new Date());
			incomingRequest.put("ReportQueue_endDate", new Date());
			incomingRequest.put("ReportQueue_deliveryTime", Dates.getNow("", ""));

			process.executeProcess(incomingRequest);
			if(process.getStatus() == Status.SUCCEEDED)
			{
				List reportsList = (List)incomingRequest.get("reportsList");
				PuridiomProcess reportProcess = processLoader.loadProcess("reportqueue-create.xml");
				Log.debug(this, "[" + reportsList.size() + "] Report Records were found.");
				for (Iterator iter = reportsList.iterator(); iter.hasNext();)
				{
					ReportQueue reportQueue = (ReportQueue) iter.next();
					Log.debug(this, "report: " + reportQueue.getName());
					Map reportsIncomingRequest = new HashMap();
					reportsIncomingRequest.put("organizationId", this.getOrganizationId());
					reportsIncomingRequest.put("reportQueue", reportQueue);
					//reportProcess.executeProcess(reportsIncomingRequest);
					this.updateReportQueue(reportProcess.getStatus(), reportsIncomingRequest, reportQueue);
					this.sendEmail(reportQueue, reportsIncomingRequest, reportProcess.getStatus());
				}
			}

		}
		catch (Exception e)
		{
			Log.error(this, "Reports could not be executed: " + e.getMessage());
		}

	}

	private void sendEmail(ReportQueue reportQueue, Map reportsIncomingRequest, int status)
	{
		String reportName = (String)reportsIncomingRequest.get("report");
//		BrowseObject browseObject = (BrowseObject)reportsIncomingRequest.get("browseObject");

		String messageText = "Where Clause: ";
		messageText = messageText + reportQueue.getWhereClause() + "\r\n";
		messageText = messageText + "SendTo: " + reportQueue.getSendTo() + "\r\n";
		//messageText = messageText + "SendTo: " + reportQueue.getSendTo();
		try
		{
			//EmailManager.getInstance().sendEmail(reportQueue.getSendFrom(), "ramosj@puridiom.com", null, browseObject.getTitle(), messageText, reportName, this.getOrganizationId());
			EmailManager.getInstance().sendEmail(reportQueue.getSendFrom(), "renzo@tsgate.com", null, reportQueue.getAlias(), messageText, reportName, this.getOrganizationId());

		}
		catch (EmailsException e)
		{
			reportQueue.setStatus(DocumentStatus.SENDQUEUE_PROCESSED);
			EmailManager.getInstance().sendErrorEmail("", this.getOrganizationId());
			e.printStackTrace();
		}
	}

	private void updateReportQueue(int status, Map reportsIncomingRequest, ReportQueue reportQueue)
	{
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
		PuridiomProcess updateSQ = null;
		try
		{
			updateSQ = processLoader.loadProcess("reportqueue-execute-update.xml");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		Map sqIncomingRequest = new HashMap();
		sqIncomingRequest.put("organizationId", this.getOrganizationId());

		//TODO add this to be part of the process.
		if(status == Status.SUCCEEDED)
		{
			reportQueue.setStatus(DocumentStatus.SENDQUEUE_PROCESSED);
		}
		else
		{
			reportQueue.setStatus(DocumentStatus.SENDQUEUE_ERROR);
		}

		reportQueue.setDateTimeSent();
		sqIncomingRequest.put("reportQueue", reportQueue);
		try
		{
			updateSQ.executeProcess(sqIncomingRequest);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
