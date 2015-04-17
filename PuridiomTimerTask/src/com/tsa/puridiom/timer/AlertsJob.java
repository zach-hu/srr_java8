package com.tsa.puridiom.timer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.SendQueue;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.DictionaryManager;

public class AlertsJob extends ScheduledJob
{

	public void execute()
	{
		this.getAlerts();
		System.out.println("job done");
		System.out.println(this.toString());
	}

	protected void getAlerts()
	{
		try
		{
			//find and log records that meet alert criteria
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
			PuridiomProcess process = processLoader.loadProcess("alerts-read.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", this.getOrganizationId());
			process.executeProcess(incomingRequest);
			if(process.getStatus() == Status.SUCCEEDED)
			{
				this.processAlerts();
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void processAlerts()
	{

		try
		{
			Log.debug(this, "retrieve list for alert type : " + SendQueue.ALERT_ACTION_EXECUTE);
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
			PuridiomProcess process = processLoader.loadProcess("sendqueue-retrieve-by-action-status.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", this.getOrganizationId());
			incomingRequest.put("SendQueue_action", SendQueue.ALERT_ACTION_EXECUTE);
			incomingRequest.put("SendQueue_status", DocumentStatus.SENDQUEUE_NEW);

			process.executeProcess(incomingRequest);

			if(process.getStatus() == Status.SUCCEEDED)
			{
				List alertsList = (List)incomingRequest.get("alertsList");
				PuridiomProcess alertsProcess = processLoader.loadProcess("alerts-execute.xml");

				for(int i = 0; i< alertsList.size(); i++)
				{
					SendQueue sq = (SendQueue)alertsList.get(i);
					Log.debug(this, "Execute alert for : " + sq.getSubject());
					Map alertsIncomingRequest = new HashMap();
					alertsIncomingRequest.put("organizationId", this.getOrganizationId());
					alertsIncomingRequest.put("mainIc", sq.getDocic());
					alertsIncomingRequest.put("mainArgument", sq.getMessagetext());
					alertsIncomingRequest.put("alerttype", sq.getDoctype());
					alertsIncomingRequest.put("alertname", sq.getSubject());
					alertsIncomingRequest.put("alertaction", sq.getArgs());
					alertsIncomingRequest.put("alertSendQueue", sq);

					alertsProcess.executeProcess(alertsIncomingRequest);

					if(process.getStatus() == Status.SUCCEEDED)
					{
						Map emailIncomingRequest = new HashMap();
						String messagetext = (String) alertsIncomingRequest.get("SendQueue_messagetext");
						
						this.updateSendQueue(alertsIncomingRequest);
						
						if (!HiltonUtility.isEmpty(messagetext))
						{
							emailIncomingRequest.put("organizationId", this.getOrganizationId());
							emailIncomingRequest.put("SendQueue_action", "EN");
							emailIncomingRequest.put("SendQueue_doctype", sq.getDoctype());
							emailIncomingRequest.put("SendQueue_docic", alertsIncomingRequest.get("SendQueue_docic"));
							emailIncomingRequest.put("SendQueue_sendfrom", DictionaryManager.getInstance("emails", this.getOrganizationId()).getProperty("mail.from", "test1@puridiom.com"));
							emailIncomingRequest.put("SendQueue_sendto", alertsIncomingRequest.get("SendQueue_sendto"));
							emailIncomingRequest.put("SendQueue_messagetext", alertsIncomingRequest.get("SendQueue_messagetext"));
							emailIncomingRequest.put("SendQueue_subject", alertsIncomingRequest.get("SendQueue_subject"));
							emailIncomingRequest.put("SendQueue_attachment", alertsIncomingRequest.get("SendQueue_attachment"));
							PuridiomProcessLoader processEmailLoader = new PuridiomProcessLoader();
							PuridiomProcess emailProcess = processEmailLoader.loadProcess("sendqueue-add.xml");
							emailProcess.executeProcess(emailIncomingRequest);
						}
					}
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void updateSendQueue(Map incomingRequest) throws Exception
	{
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
		PuridiomProcess process = processLoader.loadProcess("sendqueue-update.xml");

		process.executeProcess(incomingRequest);
	}
}
