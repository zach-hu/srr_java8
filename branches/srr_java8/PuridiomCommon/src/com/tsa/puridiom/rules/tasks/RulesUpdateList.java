package com.tsa.puridiom.rules.tasks;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RulesUpdateList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			if (incomingRequest.containsKey("Rules_icRule"))
			{
				Object icRuleObj = incomingRequest.get("Rules_icRule");

				if (icRuleObj instanceof String[])
				{
					String[] icRuleArray = ((String[]) icRuleObj);
					Set keySet = incomingRequest.keySet();

					for (int i = 0; i < icRuleArray.length; i++)
					{
						if (!HiltonUtility.isEmpty(icRuleArray[i]))
						{
							PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
							PuridiomProcess process = processLoader.loadProcess("rules-update.xml");

							Map updateParameters = new HashMap();
							updateParameters.put("userId", incomingRequest.get("userId"));
							updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
							updateParameters.put("organizationId", incomingRequest.get("organizationId"));
							updateParameters.put("dbsession", incomingRequest.get("dbsession"));

							Iterator iterator = keySet.iterator();
							while (iterator.hasNext())
							{
								String key = (String) iterator.next();
								if (key.indexOf("Rules_") == 0)
								{
									Object obj =  incomingRequest.get(key);
									if (obj instanceof String[])
									{
										String arrayObj[] = (String[]) obj;
										updateParameters.put(key, arrayObj[i]);
									}
									else if (obj instanceof String)
									{
										updateParameters.put(key, (String)obj);
									}
								}
							}

							process.executeProcess(updateParameters);
							if(process.getStatus() != Status.SUCCEEDED)
							{
								this.setStatus(Status.FAILED);
								throw new TsaException("Error ocurred updating rules!");
							}
						}
					}
				}
				else
				{
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
					PuridiomProcess process = processLoader.loadProcess("rules-update.xml");

					process.executeProcess(incomingRequest);

					if(process.getStatus() != Status.SUCCEEDED)
					{
						this.setStatus(Status.FAILED);
					}
				}
			}
			else
			{
				throw new Exception("The value for Rules_icRule was not found.");
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
