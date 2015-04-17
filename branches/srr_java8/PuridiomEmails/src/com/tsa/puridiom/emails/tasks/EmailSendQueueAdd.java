/**
 *
 */
package com.tsa.puridiom.emails.tasks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.properties.DictionaryManager;

/**
 * @author Johnny
 */
public class EmailSendQueueAdd extends Task
{
	/*
	 * (non-Javadoc)
	 *
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			String organizationId = (String) incomingRequest.get("organizationId");
			String docType = HiltonUtility.ckNull((String) incomingRequest.get("docType"));
			File emailFile = (File) incomingRequest.get("emailFile");

			String newEmailFilePath = this.writeNewEmailFile(incomingRequest, emailFile);

			this.addSendQueueRecord(incomingRequest, newEmailFilePath, organizationId, docType);

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception exception)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "EmailSendQueueAdd error " + exception.getMessage());

			throw exception;
		}

		return result;
	}

	private String writeNewEmailFile(Map incomingRequest, File emailFile) throws Exception
	{
		FileOutputStream fo;
		PrintStream ps;
		StringBuffer emailBody = (StringBuffer) incomingRequest.get("emailBody");
		String extFile = emailFile.getName().substring(emailFile.getName().lastIndexOf("."));
		String emailFilePath = emailFile.getParent() + File.separator + UniqueKeyGenerator.getInstance().getUniqueKey().toString() + extFile;

		fo = new FileOutputStream(emailFilePath);
		ps = new PrintStream(fo);
		ps.println(emailBody.toString());
		ps.close();
		fo.close();

		return emailFilePath;
	}

	private void addSendQueueRecord(Map incomingRequest, String newEmailFilePath, String organizationId, String docType) throws Exception
	{
		PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
		String sendFrom = (String) incomingRequest.get("sendFrom");
		String sendTo = (String) incomingRequest.get("sendTo");
		String jobType = (String) incomingRequest.get("jobType");
		String actionType = (String) incomingRequest.get("actionType");
		if(HiltonUtility.isEmpty(actionType))
		{
			actionType = EmailActionCodes.EMAIL;
		}

		String ccSendTo = HiltonUtility.ckNull((String) DictionaryManager.getInstance("emails", organizationId).getProperty(jobType + ".cc"));
		if(docType.equalsIgnoreCase("MSC"))
		{
			sendTo = UserManager.getInstance().getUser(organizationId, poHeader.getBuyerCode()).getMailId() + "; " +
					 UserManager.getInstance().getUser(organizationId, poHeader.getRequisitionerCode()).getMailId();
		}
		String subject = (String) incomingRequest.get("subject");

		if(!HiltonUtility.isEmpty(ccSendTo))
		{
			sendTo = sendTo + ";" + ccSendTo;
		}

		Map newIncomingRequest = new HashMap();
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
		PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");

		newIncomingRequest.put("organizationId", organizationId);
		newIncomingRequest.put("SendQueue_action",actionType);
		newIncomingRequest.put("SendQueue_sendto", sendTo);
		newIncomingRequest.put("SendQueue_sendtotype", "E");
		newIncomingRequest.put("SendQueue_subject", subject);
		newIncomingRequest.put("SendQueue_sendfrom", sendFrom);
		newIncomingRequest.put("SendQueue_sendfromtype", "E");

		newIncomingRequest.put("SendQueue_doctype", "PO");
		newIncomingRequest.put("SendQueue_docic", poHeader.getIcPoHeader().toString());
		newIncomingRequest.put("SendQueue_attachment", newEmailFilePath);

		process.executeProcess(newIncomingRequest);
	}

}