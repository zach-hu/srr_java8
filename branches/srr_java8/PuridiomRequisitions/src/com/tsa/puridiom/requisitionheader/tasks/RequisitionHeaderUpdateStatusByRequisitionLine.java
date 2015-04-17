package com.tsa.puridiom.requisitionheader.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

@SuppressWarnings(value = { "unchecked" })
public class RequisitionHeaderUpdateStatusByRequisitionLine extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Object result = null;
		try{
			Map incomingRequest = (Map)object;
			String oid = (String)incomingRequest.get("organizationId");
			String userId = (String)incomingRequest.get("userId");
			
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(oid);

			List requisitionLineList = (List)incomingRequest.get("requisitionLineList");
			RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
			StringBuffer status = new StringBuffer();
			int lowestStatus = 99999;

			if(requisitionLineList != null && requisitionLineList.size() > 0)
			{
				for (int i = 0; i < requisitionLineList.size(); i++) {
					RequisitionLine requisitionLine = (RequisitionLine)requisitionLineList.get(i);
					String requisitionLineStatus = requisitionLine.getStatus();
					if(lowestStatus > Integer.parseInt(requisitionLineStatus)){
						lowestStatus = Integer.parseInt(requisitionLineStatus);
					}
				}

				status.append(lowestStatus);

				if(!requisitionHeader.getStatus().equalsIgnoreCase(status.toString())){
					PuridiomProcess process = processLoader.loadProcess("requisition-history.xml");
					
					requisitionHeader.setStatus(status.toString());
	
					RequisitionHeaderUpdate update = new RequisitionHeaderUpdate();
					Map updateIncomingRequest = new HashMap();
					updateIncomingRequest.put("requisitionHeader", requisitionHeader);
					updateIncomingRequest.put("organizationId", oid);
					updateIncomingRequest.put("userId", userId);
					updateIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
					update.executeTask(updateIncomingRequest);
					
					updateIncomingRequest.put("newHistoryRequisitionHeader", requisitionHeader);
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