package com.tsa.puridiom.emails.tasks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class OfficeMaxEmailSendQueue extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map incomingRequest = (Map) object;
		try
		{
			String path = ((File) incomingRequest.get("officeMaxEmail")).getParent();
			Map emailRedirect = (Map) incomingRequest.get("OfficeMaxEmailRedirect");
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			String sendFrom = (String) incomingRequest.get("sendFrom");
			String organizationId = (String) incomingRequest.get("organizationId");
			FileOutputStream fo;
			PrintStream ps;
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();

			Map newIncomingRequest;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
			StringBuffer subject = new StringBuffer();
			String sendTo;
			String absolutePath;

			subject.append(" ");
			subject.append((String) incomingRequest.get("officeMaxSubject"));
			subject.append(": ");
			subject.append(poHeader.getPoNumber() + "/" + poHeader.getReleaseNumber());

			for (Iterator iter = emailRedirect.keySet().iterator(); iter.hasNext();)
			{
				sendTo = (String) iter.next();
				absolutePath = path + File.separator + ukg.getUniqueKey().toString() + ".txt";

				fo = new FileOutputStream(absolutePath);
				ps = new PrintStream(fo);
				ps.println(emailRedirect.get(sendTo));
				ps.close();
				fo.close();

				newIncomingRequest = new HashMap();

				newIncomingRequest.put("organizationId", organizationId);
				newIncomingRequest.put("SendQueue_action", EmailActionCodes.EMAIL);
				newIncomingRequest.put("SendQueue_sendto", sendTo);
				newIncomingRequest.put("SendQueue_sendtotype", "E");
				newIncomingRequest.put("SendQueue_subject", subject.toString());
				newIncomingRequest.put("SendQueue_sendfrom", sendFrom);
				newIncomingRequest.put("SendQueue_sendfromtype", "E");

				newIncomingRequest.put("SendQueue_doctype", "PO");
				newIncomingRequest.put("SendQueue_docic", poHeader.getIcPoHeader().toString());
				newIncomingRequest.put("SendQueue_attachment", absolutePath);

				process.executeProcess(newIncomingRequest);

				this.setStatus(process.getStatus());
			}

			Log.debug(this, "OfficeMaxEmailSendQueue done with: " + this.getStatus());
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return ret;
	}

	public StringBuffer getContents(File aFile, String organizationId)
	{
		// ...checks on aFile are elided
		StringBuffer contents = new StringBuffer();

		// declared here only to make visible to finally clause
		BufferedReader input = null;
		try
		{
			// use buffering, reading one line at a time
			// FileReader always assumes default encoding is OK!
			input = new BufferedReader(new FileReader(aFile));
			String line = null; // not declared within while loop
			/*
			 * readLine is a bit quirky : it returns the content of a line MINUS
			 * the newline. it returns null only for the END of the stream. it
			 * returns an empty String if two newlines appear in a row.
			 */
			while ((line = input.readLine()) != null)
			{
				contents.append(line);
				contents.append(System.getProperty("line.separator"));
			}
		} catch (FileNotFoundException ex)
		{
			ex.printStackTrace();
		} catch (IOException ex)
		{
			ex.printStackTrace();
		} finally
		{
			try
			{
				if (input != null)
				{
					// flush and close both "input" and its underlying
					// FileReader
					input.close();
				}
			} catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
		return contents;
	}

}
