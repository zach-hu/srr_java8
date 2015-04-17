/**
 *
 */
package com.tsa.puridiom.emails.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class DellEmailParser extends Task
{
	//Dell Order Has Been Confirmed  for PO Number: DDDDDDDD/N
	private final String REGEX_SUBJECT_PREFIX = "Dell Order(.)+(:)(.)+";

	private final String REGEX_SUBJECT_SUFFIX = "";

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
			String subject = (String) incomingRequest.get("subject");
			String organizationId = (String) incomingRequest.get("organizationId");
			PoHeader poHeader = this.retrieveOrder(subject, organizationId);

			result = poHeader;

		} catch (Exception exception)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "EmailParser error " + exception.getMessage());

			throw exception;
		}

		return result;
	}

	private PoHeader retrieveOrder(String subject, String organizationId) throws Exception
	{
		PoHeader poHeader = null;

		if (subject.matches(REGEX_SUBJECT_PREFIX + REGEX_SUBJECT_SUFFIX))
		{
			String[] tokens = subject.split(":");

			String poNumber = "";
			String releaseNumber = "0";
			String revisionNumber = "0";

			String[] documentTokens = tokens[1].trim().split("\\s+");

			for (int i = 0; i < documentTokens.length; i++)
			{
				String documentToken = documentTokens[i].trim();

				if (documentToken.matches("(\\d)?(\\d)*(/)?(\\d)*"))
				{
					String[] number = documentToken.split("/");

					poNumber = number[0];

					if (number.length > 1)
					{
						int eowIndex = number[1].length();

						if (number[1].indexOf(",") > 0)
						{
							eowIndex = number[1].indexOf(",");
						}

						releaseNumber = number[1].substring(0, eowIndex);
					}

					break;
				}
			}
			poHeader = this.getOrder(poNumber, releaseNumber, revisionNumber, organizationId);
		}

		return poHeader;
	}

	private PoHeader getOrder(String poNumber, String releaseNumber, String revisionNumber, String organizationId) throws Exception
	{
		PoHeader poHeader = null;
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
		PuridiomProcess process = processLoader.loadProcess("po-retrieve-by-full-number.xml");
		Map incomingRequest = new HashMap();

		if (!HiltonUtility.isEmpty(poNumber))
		{
			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("PoHeader_poNumber", poNumber);
			incomingRequest.put("PoHeader_releaseNumber", releaseNumber);
			incomingRequest.put("PoHeader_revisionNumber", revisionNumber);

			process.executeProcess(incomingRequest);

			poHeader = (PoHeader) incomingRequest.get("poHeader");

			poHeader.setPoLineList((List) incomingRequest.get("poLineList"));
		}

		return poHeader;
	}
}