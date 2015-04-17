package com.tsa.puridiom.poheader.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

@SuppressWarnings(value = { "unchecked" })
public class PoHeaderUpdateStatusByPoLine extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object result = null;
		try{
			Map incomingRequest = (Map)object;
			String oid = (String)incomingRequest.get("organizationId");
			String userId = (String)incomingRequest.get("userId");
			
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(oid);

			List poLineList = (List)incomingRequest.get("poLineList");
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			StringBuffer status = new StringBuffer();
			int lowestStatus = 99999;
			if(poLineList != null && poLineList.size() > 0)
			{
				for (int i = 0; i < poLineList.size(); i++) {
					PoLine poLine = (PoLine)poLineList.get(i);
					String poLineStatus = poLine.getStatus();
					if(lowestStatus > Integer.parseInt(poLineStatus)){
						lowestStatus = Integer.parseInt(poLineStatus);
					}
				}

				status.append(lowestStatus);

				if(!poHeader.getStatus().equalsIgnoreCase(status.toString())){
					PuridiomProcess process = processLoader.loadProcess("po-history.xml");
					
					poHeader.setStatus(status.toString());
	
					PoHeaderUpdate update = new PoHeaderUpdate();
					Map updateIncomingRequest = new HashMap();
					updateIncomingRequest.put("poHeader", poHeader);
					updateIncomingRequest.put("organizationId", oid);
					updateIncomingRequest.put("userId", userId);
					updateIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
					update.executeTask(updateIncomingRequest);
					
					updateIncomingRequest.put("newHistoryPoHeader", poHeader);
					process.executeProcess(updateIncomingRequest);
				}
			}
			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e) {
			this.setStatus(Status.FAILED);
			Log.error(this, e);
			e.printStackTrace();
		}
		return result;
	}

}
