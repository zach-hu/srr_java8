package com.tsa.puridiom.poline.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoLineSaveasList extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		try
		{
			List poLines = (List) incomingRequest.get("poLineList");
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("poline-saveas.xml");
			
			if (poLines != null) {
				for (int i = 0; i < poLines.size(); i++)
				{
					PoLine poLine = (PoLine)poLines.get(i);
					incomingRequest.put("poLine", poLine);
					incomingRequest.put("PoLine_icPoLine", poLine.getIcPoLine().toString());
					process.executeProcess(incomingRequest);
					this.setStatus(process.getStatus());
					if (process.getStatus() != Status.SUCCEEDED)
					{
						throw new TsaException("Po Line save as process failed.");
					}
					else
					{
					    poLine = (PoLine)incomingRequest.get("poLine");
					    poLines.set(i, poLine);
					}
				}
			} else {
			    poLines = new ArrayList();
			}
			result = poLines;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}
}