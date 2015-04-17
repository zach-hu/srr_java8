package com.tsa.puridiom.account.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class AccountAddStandard extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			if (incomingRequest.containsKey("Account_icHeader"))
			{
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("account-add-standard-by-id.xml");
				Object accountIcObj = incomingRequest.get("Account_icHeader");

				if (accountIcObj instanceof String[])
				{
					//if the header ic is an array, loop through list of account header ic values and add each as an StdAccount
					String accountIcs[] = (String[]) accountIcObj;
					int	arraySize = accountIcs.length;

					for (int i=0; i < arraySize; i++)
					{
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
                        updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("Account_icHeader", incomingRequest.get("Account_icHeader"));
						updateParameters.put("Account_icLine", incomingRequest.get("Account_icLine"));
						updateParameters.put("Account_accountType", incomingRequest.get("Account_accountType"));
						updateParameters.put("Account_sequence", incomingRequest.get("Account_sequence"));
						updateParameters.put("Account_icHeader", accountIcs[i]);

						process.executeProcess(updateParameters);
					}
				}
				else
				{
						process.executeProcess(incomingRequest);
				}
			}
			else
			{
				throw new Exception("The value for StandardAccount_icHeader was not found.");
			}

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result;
	}

}