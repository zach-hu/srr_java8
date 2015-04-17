/**
 *
 */
package com.tsa.puridiom.emails.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class EFaxEmailParser extends Task
{
	private final String REGEX_SUBJECT_PREFIX = "((.)+(Re:)\\s+)|((.)*(Re:)\\s+)";

	private final String REGEX_SUBJECT_SUFFIX = "\\s+(.)+";

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

			Log.error(this, "EFaxEmailParser error " + exception.getMessage());

			throw exception;
		}

		return result;
	}

	private PoHeader retrieveOrder(String subject, String organizationId) throws Exception
	{
		String companyName = PropertiesManager.getInstance(organizationId).getProperty("COMPANY", "Name", "Puridiom");
		PoHeader poHeader = null;

		if (subject.matches(REGEX_SUBJECT_PREFIX + companyName + REGEX_SUBJECT_SUFFIX))
		{
			String[] tokens = subject.split(companyName);
			String lastToken = tokens[tokens.length - 1].trim();
			String poNumber = "";
			String releaseNumber = "0";
			String revisionNumber = "0";

			String[] documentTokens = lastToken.split("\\s+");

			for (int i = 0; i < documentTokens.length; i++)
			{
				String documentToken = documentTokens[i].trim();

				if (documentToken.matches("\\d+((-)\\d+)?((-)\\d+)?"))
				{
					/*String[] number = documentToken.split("-");
					poNumber = number[0] + "-" + number[1];
					if (number.length > 2) {
						revisionNumber = number[2];
					}*/
					if (!HiltonUtility.isEmpty(poNumber))
					{
						revisionNumber = documentToken;
					} 
					else
					{
						String[] number = documentToken.split("-");
						poNumber = number[0] + "-" + number[1];
						if (number.length > 2) {
							releaseNumber = number[2];
						}
					}
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
