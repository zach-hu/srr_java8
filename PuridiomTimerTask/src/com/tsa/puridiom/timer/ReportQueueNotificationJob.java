/**
 *
 */
package com.tsa.puridiom.timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.emails.EmailManager;
import com.tsa.puridiom.emails.exception.EmailsException;
import com.tsa.puridiom.entity.ReportQueue;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny Zapana
 *
 */
public class ReportQueueNotificationJob extends ScheduledJob {

	public void execute() {
		this.sendReportsQueue();
		Log.debug(this, "ReportQueueJob done");
	}

	private void sendReportsQueue() {
		List reportsQueue = new ArrayList();

		try {
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(
					this.getOrganizationId());
			PuridiomProcess process = processLoader
					.loadProcess("reportqueue-retrieve-by-status.xml");
			Map incomingRequest = new HashMap();
			int status;

			incomingRequest.put("organizationId", this.getOrganizationId());
			incomingRequest.put("ReportQueue_status",
					DocumentStatus.SENDQUEUE_NEW);

			process.executeProcess(incomingRequest);

			if (process.getStatus() == Status.SUCCEEDED) {
				reportsQueue = (List) incomingRequest.get("reportsList");
			}

			for (Iterator iter = reportsQueue.iterator(); iter.hasNext();) {
				ReportQueue reportQueue = (ReportQueue) iter.next();

				Log.debug(this, "report: " + reportQueue.getName());

				status = this.sendEmail(reportQueue);

				this.updateReportQueue(status, reportQueue);

				if (status == Status.FAILED) {
					this.errorEmail(reportQueue, "Report could not be emailed");
				}
			}

		} catch (Exception e) {
			Log.error(this, "ReportQueueJob could not obtain a list: "
					+ e.getMessage());

			this.errorEmail(null, e.getMessage());
		}
	}

	private int sendEmail(ReportQueue reportQueue) {
		StringBuffer messageText = new StringBuffer();

		messageText.append(this.getMessageText(reportQueue));

		try {
			EmailManager.getInstance().sendEmail(reportQueue.getSendFrom(),
					reportQueue.getSendTo(), "",
					reportQueue.getAlias() + " - " + reportQueue.getName(),
					messageText.toString(), null, this.getOrganizationId());

			return Status.SUCCEEDED;
		} catch (EmailsException e) {
			e.printStackTrace();

			return Status.FAILED;
		}
	}

	private void errorEmail(ReportQueue reportQueue, String exceptionMessage) {
		StringBuffer messageText = new StringBuffer();

		messageText.append("Reason: ");
		messageText.append(exceptionMessage);

		if (reportQueue != null) {
			messageText.append(this.getMessageText(reportQueue));
			messageText.append(" \r\n could not be send.\r\n");
		} else {
			messageText.append("ReportQueueJob failed.\r\n");
		}

		EmailManager.getInstance().sendErrorEmail(messageText.toString(),
				this.getOrganizationId());
	}

	private void updateReportQueue(int status, ReportQueue reportQueue) {
		Map incomingRequest = new HashMap();
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this
				.getOrganizationId());
		PuridiomProcess updateReportQueue = null;

		if (status == Status.SUCCEEDED) {
			reportQueue.setStatus(DocumentStatus.SENDQUEUE_PROCESSED);
		} else {
			reportQueue.setStatus(DocumentStatus.SENDQUEUE_ERROR);
		}

		reportQueue.setDateTimeSent();

		incomingRequest.put("organizationId", this.getOrganizationId());
		incomingRequest.put("reportQueue", reportQueue);

		try {
			updateReportQueue = processLoader
					.loadProcess("reportqueue-execute-update.xml");

			updateReportQueue.executeProcess(incomingRequest);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private String getMessageText(ReportQueue reportQueue) {
		StringBuffer messageText = new StringBuffer();

		messageText.append(" \r\n Send From: " + reportQueue.getSendFrom());
		messageText.append(" \r\n Send To: " + reportQueue.getSendTo());
		messageText.append(" \r\n Name: " + reportQueue.getName());
		messageText.append(" \r\n Alias: " + reportQueue.getAlias());
		messageText.append(" \r\n Start Date: " + reportQueue.getStartDate());
		messageText.append(" \r\n End Date: " + reportQueue.getEndDate());
		messageText.append(" \r\n Frecuency: " + reportQueue.getFrequency());
		messageText.append(" \r\n Type: " + reportQueue.getType());
		messageText.append(" \r\n Where Clausule: "
				+ reportQueue.getWhereClause());

		return messageText.toString();
	}

}
