/**
 *
 */
package com.tsa.puridiom.timer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.SendAlert;
import com.tsa.puridiom.entity.SendQueue;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;

/**
 * @author renzo
 *
 */
public class TimerUtility
{
	public static long getSeconds(long seconds)
	{
		return seconds * 1000;
	}

	public static long getMinutes(long minutes)
	{
		return minutes * (1000 * 60);
	}

	public static void executeProcess(String processName, String organizationId)
	{
		Map incomingRequest = new HashMap();
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
			PuridiomProcess process = processLoader.loadProcess(processName);
			incomingRequest.put("organizationId", organizationId);
			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				//TODO deal with a good process;
				List sendqueuelist = (List)incomingRequest.get("sendqueueList");
				for(int i = 0; i < sendqueuelist.size(); i++)
				{
					SendQueue sendQueue = (SendQueue)sendqueuelist.get(i);
					System.out.println("Subejct: " + sendQueue.getSubject() + ", Action: " + sendQueue.getAction());

				}
			}
			else
			{
//				TODO deal with a failed process;
			}
		}
		catch (Exception exception)
		{
//			TODO deal with a failed process;
		}
	}
}
