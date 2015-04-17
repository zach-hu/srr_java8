package com.tsa.puridiom.timer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.SendQueue;
import com.tsa.puridiom.emails.EmailManager;
import com.tsa.puridiom.sendqueue.tasks.SendQueueUpdate;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.DictionaryManager;

public class ReportsJob extends ScheduledJob
{

	public void execute()
	{
		this.getReports();
		System.out.println("job done");
		System.out.println(this.toString());
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
		Log.debug(this, "retrieve list for alert type : " + SendQueue.REPORT_ACTION);
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
			PuridiomProcess process = processLoader.loadProcess("sendqueue-retrieve-by-action-status.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", this.getOrganizationId());
			incomingRequest.put("SendQueue_action", SendQueue.REPORT_ACTION);
			incomingRequest.put("SendQueue_status", DocumentStatus.SENDQUEUE_NEW);

			process.executeProcess(incomingRequest);
			if(process.getStatus() == Status.SUCCEEDED)
			{
				List alertsList = (List)incomingRequest.get("alertsList");
				PuridiomProcess reportProcess = processLoader.loadProcess("report-create.xml");

				for(int i = 0; i< alertsList.size(); i++)
				{
					SendQueue sq = (SendQueue)alertsList.get(i);
					Log.debug(this, "Execute alert for : " + sq.getSubject());
					Map reportsIncomingRequest = new HashMap();
					reportsIncomingRequest.put("organizationId", this.getOrganizationId());
					reportsIncomingRequest.put("sqlWhere", sq.getMessage());
					reportsIncomingRequest.put("browseName", sq.getSubject());
					reportsIncomingRequest.put("reportName", sq.getSubject());
					reportsIncomingRequest.put("format", sq.getDoctype());
					reportsIncomingRequest.put("webreport", "R");
					/* provide extra agruments
					String  args = sq.getArgs();
					if(args.length() > 0)
					{
						String tempArray[] = args.split("=");
						if(tempArray[0].equalsIgnoreCase("userId"))
						{
							reportsIncomingRequest.put("userId", tempArray[1]);
						}
					}
					*/
					reportsIncomingRequest.put("userId", sq.getOwner());

					reportProcess.executeProcess(reportsIncomingRequest);
					PuridiomProcess updateSQ = processLoader.loadProcess("sendqueue-update.xml");
					Map sqIncomingRequest = new HashMap();
					sqIncomingRequest.put("organizationId", this.getOrganizationId());
					//TODO add this to be part of the process.
					if(reportProcess.getStatus() == Status.SUCCEEDED)
					{
						String reportName = (String)reportsIncomingRequest.get("report");
						String messageText = (String)reportsIncomingRequest.get("messageText");
						BrowseObject browseObject = (BrowseObject)reportsIncomingRequest.get("browseObject");

						EmailManager.getInstance().sendEmail(sq.getSendfrom(), sq.getSendto(), null, browseObject.getTitle(), messageText, reportName, this.getOrganizationId());
						sq.setStatus(DocumentStatus.SENDQUEUE_PROCESSED);

					}
					else
					{
						sq.setStatus(DocumentStatus.SENDQUEUE_ERROR);
					}
					sq.setDateTimeSent();
					sqIncomingRequest.put("sendQueue", sq);
					updateSQ.executeProcess(sqIncomingRequest);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
