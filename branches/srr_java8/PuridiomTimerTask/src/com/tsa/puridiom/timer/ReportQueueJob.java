package com.tsa.puridiom.timer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.ReportQueueFrequency;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.emails.EmailManager;
import com.tsa.puridiom.emails.exception.EmailsException;
import com.tsa.puridiom.entity.ReportQueue;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;

public class ReportQueueJob extends ScheduledJob
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
			List reportsList = this.getReportsToExecute();
			this.executeReports(reportsList);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public List getReportsToExecute()
	{
		List reportsList = null;
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
			PuridiomProcess process = processLoader.loadProcess("reportqueue-job-retrieve-by-status-date.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", this.getOrganizationId());
			incomingRequest.put("ReportQueue_status", DocumentStatus.SENDQUEUE_NEW);
			incomingRequest.put("ReportQueue_nextRun", Dates.getSqlDate(""));
			incomingRequest.put("ReportQueue_startDate", Dates.getSqlDate(""));
			incomingRequest.put("ReportQueue_endDate", Dates.getSqlDate(""));
			incomingRequest.put("ReportQueue_deliveryTime", Dates.getNow("", ""));

			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				reportsList = (List) incomingRequest.get("reportsList");
			}
		} catch (Exception e)
		{
			Log.error(this, "ReportQueueJob could not obtain a list: " + e.getMessage());
			this.errorEmail(Status.FAILED, null, e.getMessage());

		}
		return reportsList;
	}

	private void executeReports(List reportsList) throws Exception
	{
		if (reportsList == null)
		{
			Log.debug(this, "No Reports where found.");
			return;
		}
		Log.debug(this, "[" + reportsList.size() + "] were found.");
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
		PuridiomProcess reportProcess = processLoader.loadProcess("reportqueue-create.xml");
		Log.debug(this, "[" + reportsList.size() + "] Report Records were found.");
		for (Iterator iter = reportsList.iterator(); iter.hasNext();)
		{
			ReportQueue reportQueue = (ReportQueue) iter.next();
			Log.debug(this, "report: " + reportQueue.getName());
			if (reportQueue.getFrequency().equals(ReportQueueFrequency.ONCE) || Dates.getNow("", "").compareTo(reportQueue.getDeliveryTime()) > 0)
			{
				Map reportsIncomingRequest = new HashMap();
				reportsIncomingRequest.put("organizationId", this.getOrganizationId());
				reportsIncomingRequest.put("isReportQueueJob", "Y");
				reportsIncomingRequest.put("reportQueue", reportQueue);
				try
				{
					reportProcess.executeProcess(reportsIncomingRequest);
					int status = this.sendEmail(reportQueue, reportsIncomingRequest, reportProcess.getStatus());
					if (status == Status.SUCCEEDED)
					{
						this.updateReportQueue(reportProcess.getStatus(), reportsIncomingRequest, reportQueue);
					} else
					{
						this.errorEmail(Status.FAILED, reportQueue, "Report could not be emailed");
					}
				} catch (Exception e)
				{
					e.printStackTrace();
					Log.error(this, "ReportQueue could not be executed: " + e.getMessage());
					this.errorEmail(Status.FAILED, reportQueue, e.getMessage());
				}
			}
		}
	}

	protected void errorEmail(int status, ReportQueue rq, String exceptionMessage)
	{
		this.updateReportQueue(status, null, rq);
		StringBuffer text = new StringBuffer();
		if (rq != null)
		{
			text.append(" Alias: " + rq.getAlias());
			text.append(" where: " + rq.getWhereClause());
			text.append(" id: " + rq.getIcReportQueue());
			text.append(" could not be send.\r\n");
		} else
		{
			text.append("ReportQueueJob failed.\r\n");
		}
		text.append("reason: ");
		text.append(exceptionMessage);
		EmailManager.getInstance().sendErrorEmail(text.toString(), this.getOrganizationId());
	}

	private int sendEmail(ReportQueue reportQueue, Map reportsIncomingRequest, int status)
	{
		String reportName = null;
		BrowseObject browseObject = (BrowseObject) reportsIncomingRequest.get("browseObject");
		String messageText = "";
		String reportZipFile = (String) reportsIncomingRequest.get("reportZipFile");
		if (!HiltonUtility.isEmpty(reportZipFile) && reportZipFile.equalsIgnoreCase("N"))
		{
			reportName = (String) reportsIncomingRequest.get("report");
		}

		if (status == Status.SUCCEEDED)
		{
			messageText = (String) reportsIncomingRequest.get("messageText");
		} else
		{
			messageText = "Report could not be executed.";
		}
		try
		{
			EmailManager.getInstance().sendEmail(reportQueue.getSendFrom(), reportQueue.getSendTo(), null, browseObject.getTitle(), messageText, reportName, this.getOrganizationId());
			return Status.SUCCEEDED;
		} catch (EmailsException e)
		{
			reportQueue.setStatus(DocumentStatus.SENDQUEUE_PROCESSED);
			EmailManager.getInstance().sendErrorEmail("", this.getOrganizationId());
			e.printStackTrace();
			return Status.FAILED;
		}
	}

	private void updateReportQueue(int status, Map reportsIncomingRequest, ReportQueue reportQueue)
	{
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
		PuridiomProcess updateSQ = null;
		try
		{
			updateSQ = processLoader.loadProcess("reportqueue-execute-update.xml");
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		Map sqIncomingRequest = new HashMap();
		sqIncomingRequest.put("organizationId", this.getOrganizationId());

		// TODO add this to be part of the process.
		if (status == Status.SUCCEEDED)
		{
			reportQueue.setStatus(DocumentStatus.SENDQUEUE_PROCESSED);
		} else
		{
			reportQueue.setStatus(DocumentStatus.SENDQUEUE_ERROR);
		}

		reportQueue.setDateTimeSent();
		sqIncomingRequest.put("reportQueue", reportQueue);
		try
		{
			updateSQ.executeProcess(sqIncomingRequest);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
