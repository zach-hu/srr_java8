/**
 *
 */
package com.tsa.puridiom.emails.tasks;

import java.io.File;
import java.io.FileReader;
import java.io.StreamTokenizer;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public abstract class EmailBuildMessage extends Task
{
	protected String bofNewEmail = "";

	protected String eofNewEmail = "";

	protected String bofEmail = "";

	protected String eofEmail = "";

	protected String auxEmail = "";

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
			File emailFile = (File) incomingRequest.get("emailFile");
			String sendTo;
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			StringBuffer emailBody = new StringBuffer();

			this.setEmailData(incomingRequest, organizationId);

			emailBody = this.getEmailBody(emailFile);
			sendTo = this.getEmailSenTo(poHeader, organizationId);

			incomingRequest.put("emailBody", emailBody);
			incomingRequest.put("sendTo", sendTo);

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception exception)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "EEmailBuildMessage error " + exception.getMessage());

			throw exception;
		}

		return result;
	}

	private String getEmailSenTo(PoHeader poHeader, String organizationId) throws Exception
	{
		StringBuffer sendTo = new StringBuffer();
		Set sendToSet = new HashSet();
		String[] aSendTo;
		UserManager userManager = UserManager.getInstance();

		sendToSet.add(userManager.getUser(organizationId, poHeader.getRequisitionerCode()).getMailId());
		sendToSet.add(userManager.getUser(organizationId, poHeader.getBuyerCode()).getMailId());

		aSendTo = (String[]) sendToSet.toArray(new String[sendToSet.size()]);

		for (int i = 0; i < aSendTo.length; i++)
		{
			if (i > 0)
			{
				sendTo.append(";");
			}

			sendTo.append(aSendTo[i]);
		}

		return sendTo.toString();
	}

	private StringBuffer getEmailBody(File emailFile) throws Exception
	{
		StringBuffer emailBody = new StringBuffer();
		StringBuffer emailFileContent = this.getEmailFileContent(emailFile);

		if (!HiltonUtility.isEmpty(this.bofNewEmail))
		{
			emailBody.append(this.bofNewEmail);
		}

		emailBody.append(emailFileContent);

		if (!HiltonUtility.isEmpty(this.eofNewEmail))
		{
			emailBody.append(this.eofNewEmail);
		}

		return emailBody;
	}

	private StringBuffer getEmailFileContent(File emailFile) throws Exception
	{
		StreamTokenizer tokenizer = new StreamTokenizer(new FileReader(emailFile));

		int type;
		StringBuffer sbBody = new StringBuffer();
		boolean bof = HiltonUtility.isEmpty(this.bofEmail) ? true : false;
		boolean nbof = HiltonUtility.isEmpty(this.auxEmail) ? true : false;

		String value = "";
		String tmp = "";
		String aux = "";

		tokenizer.ordinaryChar(32);
		tokenizer.wordChars(32, 126);
		tokenizer.eolIsSignificant(true);

		while ((type = tokenizer.nextToken()) != StreamTokenizer.TT_EOF)
		{
			value = "";

			switch (type)
			{
				case StreamTokenizer.TT_EOL:

					value = (value.length() == 0) ? "\r\n" : value;

				case StreamTokenizer.TT_NUMBER:

					value = (value.length() == 0) ? String.valueOf(tokenizer.nval) : value;

				case StreamTokenizer.TT_WORD:

					value = (value.length() == 0) ? tokenizer.sval : value;

					tmp = (tokenizer.sval != null) ? tokenizer.sval.trim() : "";

					if ((!HiltonUtility.isEmpty(this.bofEmail)) && tmp.matches(this.bofEmail))
					{
						bof = true;
						aux = (tokenizer.sval != null) ? tokenizer.sval.trim() : "";

					}
					else if(bof && !HiltonUtility.isEmpty(this.auxEmail))
					{
						if(aux.matches(this.bofEmail) && tmp.matches(this.auxEmail))
						{
							nbof = true;
							this.auxEmail = "";
						}
						else if (aux.matches(this.auxEmail))
						{
							nbof = true;
							this.auxEmail = "";
						}
					}
					else if ((!HiltonUtility.isEmpty(this.eofEmail)) && tmp.matches(this.eofEmail))
					{
						bof = false;
					}
					else if (bof && nbof)
					{
						sbBody.append(value);

					}
					break;
			}
		}
		return sbBody;
	}
	protected abstract void setEmailData(Map incomingRequest, String organizationId);
}